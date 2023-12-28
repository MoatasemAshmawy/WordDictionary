package org.example;

import javafx.scene.control.Button;

public class Word implements java.io.Serializable {
    String name;
    String meaning;
    Button delete;

    public Word(String name, String meaning){
        this.name = name;
        this.meaning = meaning;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
    public Button getDelete() {
        return delete;
    }

    public void setDelete(String meaning) {
        this.delete = delete;
    }
}
