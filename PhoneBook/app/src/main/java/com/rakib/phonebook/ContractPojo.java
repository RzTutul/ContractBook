package com.rakib.phonebook;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Contract_List")
public class ContractPojo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private String email;
    private String image;

    public ContractPojo(String name, String phone, String email, String image) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }


    @Ignore
    public ContractPojo(int id, String name, String phone, String email, String image) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
