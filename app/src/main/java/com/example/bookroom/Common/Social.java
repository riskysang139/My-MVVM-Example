package com.example.bookroom.Common;

import java.io.Serializable;

public class Social implements Serializable {
    private int ID;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String name;
    private String icon;
    private String link;
    private int flag;


    public Social(String name, String icon, String link, int flag) {
        this.name = name;
        this.icon = icon;
        this.link = link;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Social(String name, String icon, String link) {
        this.name = name;
        this.icon = icon;
        this.link = link;
    }

    public Social() {
    }

    public Social(int ID, String name, String icon, String link, int flag) {
        this.ID = ID;
        this.name = name;
        this.icon = icon;
        this.link = link;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Social{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                ", flag=" + flag +
                '}';
    }
}
