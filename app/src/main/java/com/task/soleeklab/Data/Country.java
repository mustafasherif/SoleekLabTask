package com.task.soleeklab.Data;

import com.google.gson.annotations.SerializedName;

public class Country {


    @SerializedName("Code")
    private String Code;
    @SerializedName("Name")
    private String Name;

    public Country(String Code,String Name){
        this.Code=Code;
        this.Name=Name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        this.Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }


}