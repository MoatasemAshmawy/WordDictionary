package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordDictionaryImpl extends UnicastRemoteObject implements WordDictionary{
    ArrayList<Word> wordList = new ArrayList<Word>();
    public WordDictionaryImpl() throws RemoteException{
        super();
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
       return wordList.add(word);
    }

    @Override
    public boolean removeWordByIndex(int index) {
        try{
            wordList.remove(index);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public HashMap<Word,Integer> searchByName(String name) {
        HashMap<Word,Integer> searchMap = new HashMap<Word, Integer>();
        for(int i =0;i<wordList.size();i++){
            if(wordList.get(i).name.toLowerCase().contains(name.toLowerCase()))
                searchMap.put(wordList.get(i),i);
        }
        return searchMap;
    }
}
