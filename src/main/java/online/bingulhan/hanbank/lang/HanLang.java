package online.bingulhan.hanbank.lang;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class HanLang {

    public HashMap<String, String> words = new HashMap<>();
    public HashMap<String, String> guiWords = new HashMap<>();
    public HashMap<String, ArrayList<String>> listWords = new HashMap<>();

    public abstract void load();

    public abstract HashMap<String, String> getWords();
    public abstract HashMap<String, ArrayList<String>> getListStringWords();


}
