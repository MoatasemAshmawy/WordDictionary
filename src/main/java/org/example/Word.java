package org.example;

public class Word implements java.io.Serializable {
    String name;
    String meaning;
    public Word(String name, String meaning){
        this.name = name;
        this.meaning = meaning;
    }
}
