package org.example;

public class Value <V,A>{

    public A act;
    public V value;

    public Value(V value, A act){
        this.act = act;
        this.value = value;
    }
}
