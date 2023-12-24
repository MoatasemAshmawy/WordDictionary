package org.example;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientExample {
    private ClientExample() {}
    public static void main(String[] args) {
        try {
            // Getting the registry
            // Looking up the registry for the remote object
            WordDictionary stub = (WordDictionary) Naming.lookup("rmi://127.0.0.1:5432/WordDictionary");

            // Calling the remote method using the obtained object
            stub.addWord(new Word("Fire","To Remove Someone from a job"));
            stub.addWord(new Word("Fire","Burning"));
            stub.getWordsList().forEach((word)-> System.out.println(word.name + ": "+ word.meaning));

            // System.out.println("Remote method invoked");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}