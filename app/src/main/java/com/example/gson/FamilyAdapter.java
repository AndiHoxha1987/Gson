package com.example.gson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FamilyAdapter extends RecyclerView.Adapter<FamilyAdapter.FamilyViewHolder> {

    private List<FamilyMember>familyMembers;

    public FamilyAdapter(List<FamilyMember> familyMembers) {
        this.familyMembers = familyMembers;
    }

    @SuppressWarnings("WeakerAccess")
    public class FamilyViewHolder extends RecyclerView.ViewHolder {
        public final TextView role,age;


        @SuppressWarnings("WeakerAccess")
        public FamilyViewHolder(@NonNull View itemView) {
            super(itemView);

            role = itemView.findViewById(R.id.role_item);
            age = itemView.findViewById(R.id.age_item);
        }
    }

    @NonNull
    @Override
    public FamilyAdapter.FamilyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.family_item, parent, false);

        return new FamilyAdapter.FamilyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyAdapter.FamilyViewHolder holder, int position) {
        FamilyMember current = familyMembers.get(position);

        final String sRole= current.getmRole();
        final int iAge = current.getmAge();

        holder.role.setText(sRole);
        holder.age.setText(String.valueOf(iAge));
    }

    @Override
    public int getItemCount() {
        if(familyMembers == null){
            return 0;
        }
        return familyMembers.size();
    }
}
