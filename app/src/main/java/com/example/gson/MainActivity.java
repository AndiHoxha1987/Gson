package com.example.gson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromEmployeeGsonToJson();

        fromEmployeeJsonToGson();

        fromEmployeeJsonToGsonArrayList();
    }

    private void fromEmployeeGsonToJson(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        Address address = new Address("Albania","Tirane");

        List<FamilyMember> familyMembers = new ArrayList<>();

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
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = "{\"address\":{\"city\":\"Tirane\",\"country\":\"Albania\"},\"family\"\"[{\"age\":30,\"role\":\"Wife\"},{\"age\":2,\"role\":\"Daughter\"}]\",\"age\":30,\"first_name\":\"Andi\",\"mail\":\"andial@gmail.com\"}";

        Employee employee1 = gson.fromJson(json, Employee.class);
    }

    private void fromEmployeeJsonToGsonArrayList(){
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        String json = "[{\"age\":30,\"role\":\"Wife\"},{\"age\":5,\"role\":\"Daughter\"}]";
        Type familyType = new TypeToken<ArrayList<FamilyMember>>() {}.getType();
        ArrayList<FamilyMember> family = gson.fromJson(json, familyType);
    }
}
