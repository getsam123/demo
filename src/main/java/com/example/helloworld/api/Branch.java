package com.example.helloworld.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Branch {
    private Integer id;
    private String name;

    public Branch(){

    }

    public Branch(List<String> data){
        this.id = Integer.parseInt(data.get(0));
        this.name = data.get(1);
    }

    @JsonProperty
    public Integer getId(){
        return id;
    }

    @JsonProperty
    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return "Branch{id="+id+",name="+name+"}";
    }


}
