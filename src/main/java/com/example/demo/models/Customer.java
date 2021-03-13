package com.example.demo.models;


public class Customer {

    private long id;
    private String name;
    private String duetime;
    private String jointime;

    public Customer(long id, String name, String duetime, String jointime) {
        this.id = id;
        this.name = name;
        this.duetime = duetime;
        this.jointime = jointime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuetime() {
        return duetime;
    }

    public String getJointime() {
        return jointime;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duetime=" + duetime +
                ", jointime=" + jointime +
                '}';
    }
}
