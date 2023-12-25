package org.example;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordDictionaryImpl extends UnicastRemoteObject implements WordDictionary{
    private static final long serialVersionUID = 6529685098267757690L;
    ArrayList<Word> wordList;
    public WordDictionaryImpl() throws RemoteException{
        super();
        this.wordList = getSavedList();
    }

    public ArrayList<Word> getSavedList(){
        ArrayList<Word> list = null;
        try (FileInputStream fis = new FileInputStream("wordDatabase");
             ObjectInputStream ois = new ObjectInputStream(fis);) {

            list = (ArrayList<Word>) ois.readObject();
            if(list!=null){
                return list;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return new ArrayList<>();
    }
    public void saveList(){
        try (FileOutputStream fos = new FileOutputStream("wordDatabase");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            oos.writeObject(wordList);

        } catch (FileNotFoundException e) {
            System.err.println("File not found : " + e);

        } catch (IOException e) {
            System.err.println("Error while writing data : "+ e);

        }
    }
    @Override
    public ArrayList<Word> getWordsList() throws RemoteException {
        return wordList;
    }

    @Override
    public boolean addWord(Word word) {
        HashMap<Word,Integer> map = searchByName(word.name);
        for(Map.Entry<Word,Integer> set: map.entrySet()){
            if(set.getKey().meaning.toLowerCase().equals(word.meaning.toLowerCase())){
                return false;
            }
        }
        if(wordList.add(word)){
            saveList();
            return true;
        }
        return false;
    }



    @Override
    public boolean removeWordByIndex(int index) {
        try{
            wordList.remove(index);
            saveList();
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public HashMap<Word, Integer> searchByName(String name) {
        HashMap<Word, Integer> searchMap = new HashMap<>();
        if (name.equals(".")) {
            // Return all words if the search term is "."
            for (int i = 0; i < wordList.size(); i++) {
                searchMap.put(wordList.get(i), i);
            }
        } else {
            // Perform the regular search
            for (int i = 0; i < wordList.size(); i++) {
                if (wordList.get(i).name.toLowerCase().contains(name.toLowerCase())) {
                    searchMap.put(wordList.get(i), i);
                }
            }
        }
        return searchMap;
}

}