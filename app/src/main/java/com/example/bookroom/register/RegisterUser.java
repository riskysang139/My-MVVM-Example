package com.example.bookroom.register;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Patterns;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


public class RegisterUser {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String strEmailAddress;

    private String strPassword;

    private String strUsername;

    private String strPasswordConfirm;

    private String capCha;


    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public void setStrEmailAddress(String strEmailAddress) {
        this.strEmailAddress = strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public void setStrPassword(String strPassword) {
        this.strPassword = strPassword;
    }

    public String getStrUsername() {
        return strUsername;
    }

    public void setStrUsername(String strUsername) {
        this.strUsername = strUsername;
    }

    public String getStrPasswordConfirm() {
        return strPasswordConfirm;
    }

    public void setStrPasswordConfirm(String strPasswordConfirm) {
        this.strPasswordConfirm = strPasswordConfirm;
    }

    public String getCapCha() {
        return capCha;
    }

    public void setCapCha(String capCha) {
        this.capCha = capCha;
    }


    public RegisterUser(String strUsername,String strEmailAddress, String strPassword) {
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
        this.strUsername = strUsername;
    }

    public RegisterUser(int id, String strEmailAddress, String strPassword, String strUsername) {
        this.id = id;
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
        this.strUsername = strUsername;

    }

    public RegisterUser(String strEmailAddress, String strPassword, String strUsername, String strPasswordConfirm, String capCha) {
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
        this.strUsername = strUsername;
        this.strPasswordConfirm = strPasswordConfirm;
        this.capCha = capCha;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }

    public boolean comparePassword() {
        return (getStrPassword().equals(getStrPasswordConfirm()));
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "strEmailAddress='" + strEmailAddress + '\'' +
                ", strPassword='" + strPassword + '\'' +
                ", strUsername='" + strUsername + '\'' +
                '}';
    }

    public RegisterUser(int id, String strEmailAddress, String strPassword, String strUsername, String strPasswordConfirm, String capCha) {
        this.id = id;
        this.strEmailAddress = strEmailAddress;
        this.strPassword = strPassword;
        this.strUsername = strUsername;
        this.strPasswordConfirm = strPasswordConfirm;
        this.capCha = capCha;
    }
}
