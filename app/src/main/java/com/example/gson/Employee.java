package com.example.gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Employee {

    //SerializedName is used when the name in gson is not the same with model class
    @Expose
    private String firstName;
    @Expose(serialize = false)
    private int age;
    @Expose(deserialize = false)
    private String mail;
    private String password;

    @SerializedName("address")
    private Address mAddress;
    @SerializedName("family")
    private List<FamilyMember> mFamily;

    public Employee(String firstName, int age, String mail,String password ,Address address, List<FamilyMember> family) {
        this.firstName = firstName;
        this.age = age;
        this.mail = mail;
        this.password = password;
        mAddress = address;
        mFamily = family;
    }
}