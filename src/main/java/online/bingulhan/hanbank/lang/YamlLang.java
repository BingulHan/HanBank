package online.bingulhan.hanbank.lang;

import online.bingulhan.hanbank.HanBank;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class YamlLang extends HanLang{

    File file = new File(HanBank.getInstance().getDataFolder(), "lang.yml");

    public YamlLang() {
        load();
    }

    @Override
    public void load() {

        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileConfiguration a = YamlConfiguration.loadConfiguration(file);


        //Default
        a.addDefault("words.bank-info", "&eBanka'daki miktar: &f%miktar%");
        a.addDefault("words.nobankmoney", "&cBankada para yetersiz.");
        a.addDefault("words.nomoneyhas", "&cYeterli paran yok.");
        a.addDefault("words.bank-withdraw", "&eBankadan para cekildi &f%miktar%");
        a.addDefault("words.bank-deposite", "&eBankaya para yatirildi &f%miktar%");

        a.addDefault("gui.title", "&cBanka");
        a.addDefault("gui.info", "&cBanka'daki miktar: &f%miktar%");

        a.addDefault("gui.withdraw", "&cPara Çekmek için");
        a.addDefault("gui.deposite", "&cPara Yatırmak için");

        ArrayList<String> help = new ArrayList<>();
        help.add("&e/banka yatır <miktar>");
        help.add("&e/banka çek <miktar>");
        help.add("&e/banka menu");

        a.addDefault("help", help);

        a.options().copyDefaults(true);
        try {
            a.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key: a.getConfigurationSection("words").getKeys(false)) {
            words.put(key, a.getString("words."+key));
        }

        for (String gui : a.getConfigurationSection("gui").getKeys(false)) {
            guiWords.put(gui, a.getString("gui."+gui));
        }

        listWords.put("help", (ArrayList<String>) a.get("help"));

    }

    @Override
    public HashMap<String, String> getWords() {
        return super.words;
    }

    @Override
    public HashMap<String, ArrayList<String>> getListStringWords() {
        return super.listWords;
    }

}
