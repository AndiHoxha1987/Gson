package com.example.gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  List<FamilyMember> familyMembers ;
    private EditText role,age;
    private RecyclerView recyclerView;
    private FamilyAdapter familyAdapter;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        role = findViewById(R.id.role_edit_text);
        age = findViewById(R.id.age_edit_text);
        loadPrefData();
        recyclerView = findViewById(R.id.members);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        familyAdapter = new FamilyAdapter(familyMembers);
        recyclerView.setAdapter(familyAdapter);


        final Button addMember = findViewById(R.id.add_member_button);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMember();
            }
        });

        save = findViewById(R.id.save_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePrefArrayData();
            }
        });


        //fromEmployeeGsonToJson();

        //fromEmployeeJsonToGson();

        //fromEmployeeJsonToGsonArrayList();

        //savePrefData();
    }

    private void fromEmployeeGsonToJson(){
        //read at debugging
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Address address = new Address("Albania","Tirane");

        FamilyMember family = new FamilyMember("wife",30);

        familyMembers.add(family);

        Employee employee = new Employee(
                "Andi",
                30,
                "andial@gmail.com",
                "fffaaa",
                address,
                familyMembers
        );
        //string with json result from Gson
        String jsonResult = gson.toJson(employee);
    }

    private void fromEmployeeJsonToGson(){
        //read at debugging
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = "{\"address\":{\"city\":\"Tirane\",\"country\":\"Albania\"},\"family\"\"[{\"age\":30,\"role\":\"Wife\"},{\"age\":2,\"role\":\"Daughter\"}]\",\"age\":30,\"first_name\":\"Andi\",\"mail\":\"andial@gmail.com\"}";

        Employee employee1 = gson.fromJson(json, Employee.class);
    }

    private void fromEmployeeJsonToGsonArrayList(){
        //read at debugging
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = "[{\"age\":30,\"role\":\"Wife\"},{\"age\":5,\"role\":\"Daughter\"}]";
        Type familyType = new TypeToken<ArrayList<FamilyMember>>() {}.getType();
        familyMembers = gson.fromJson(json, familyType);
    }

    private void savePrefData(){
        //read at debugging
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Address address = new Address("Albania","Tirane");

        FamilyMember family = new FamilyMember("wife",30);

        familyMembers.add(family);

        Employee employee = new Employee(
                "Andi",
                30,
                "andial@gmail.com",
                "fffaaa",
                address,
                familyMembers
        );
        String jsonResult = gson.toJson(employee);
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("arrays",jsonResult);
        editor.apply();
    }

    private void addMember(){
        String sRole = role.getText().toString();
        String sAge = age.getText().toString();
        int iAge = Integer.parseInt(sAge);

        FamilyMember member = new FamilyMember(sRole,iAge);
        familyMembers.add(member);
        familyAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(familyAdapter);
        save.setText(String.valueOf(familyMembers.size()));
    }

    private void savePrefArrayData(){
        Gson gson = new Gson();

        String jsonResult = gson.toJson(familyMembers);
        save.setText(jsonResult);

        SharedPreferences sharedPreferences = getSharedPreferences("data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("arrays",jsonResult);
        editor.apply();
    }

    private void loadPrefData(){
        SharedPreferences sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        Gson gson = new Gson();

        String json = sharedPreferences.getString("arrays",null);
        Type familyType = new TypeToken<ArrayList<FamilyMember>>() {}.getType();
        familyMembers = gson.fromJson(json, familyType);
        if(familyMembers == null){
            familyMembers = new ArrayList<>();
        }
        //sharedPreferences.edit().clear().apply();
    }
}
