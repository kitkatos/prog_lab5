package dataLayer.models;

import java.util.Date;
import jakarta.xml.bind.annotation.*;

public class Person implements Validatable{
    private String name;

    private Date birthday;

    private Long height;

    private int weight;

    private String passportID;

    public Person(String name, java.util.Date birthday, Long height, Integer weight, String passportID){
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public java.util.Date getBirthday(){
        return birthday;
    }
    public void setBirthday(java.util.Date birthday){
        this.birthday = birthday;
    }
    public Long getHeight(){
        return height;
    }
    public void setHeight(Long height){
        this.height = height;
    }
    public int getWeight(){
        return weight;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public String getPassportID(){
        return passportID;
    }
    public void setPassportID(String passportID){
        this.passportID = passportID;
    }

    @Override
    public String toString(){
        return "Person{name=" + name + ", birthday=" + birthday 
        + ", height=" + height + ", weight=" + weight 
        + ", passportID=" + passportID + "}";
    }

    @Override
    public boolean validate(){
        if (name == null || name.isEmpty()) return false;
        if (birthday == null) return false;
        if (height <= 0) return false;
        if (weight <= 0) return false;
        return (passportID.length() >= 6 && passportID.length() <= 47 && passportID != null);
    }
}