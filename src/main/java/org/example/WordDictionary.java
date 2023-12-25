package org.example;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public interface WordDictionary extends Remote{
    boolean addWord(Word word) throws RemoteException;
    boolean removeWordByIndex(int index) throws RemoteException;
    HashMap<Word,Integer> searchByName(String name) throws RemoteException;
    ArrayList<Word> getWordsList() throws RemoteException;
}


