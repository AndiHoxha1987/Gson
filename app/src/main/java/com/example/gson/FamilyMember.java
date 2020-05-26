package com.example.gson;

import com.google.gson.annotations.SerializedName;

public class FamilyMember {
    @SerializedName("role")
    private String mRole;
    @SerializedName("age")
    private int mAge;

    public FamilyMember(String role, int age) {
        mRole = role;
        mAge = age;
    }

    public String getmRole() {
        return mRole;
    }

    public int getmAge() {
        return mAge;
    }
}