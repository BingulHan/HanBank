package online.bingulhan.hanbank.backup;

import online.bingulhan.hanbank.BankAccount;
import online.bingulhan.hanbank.BankServer;
import online.bingulhan.hanbank.HanBank;
import online.bingulhan.hanbank.util.FileUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class YamlBackupManager implements IBackupManager{

    File file = new File(HanBank.getInstance().getDataFolder(), "backups.yml");

    @Override
    public void backup(BankServer server) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        for (BankAccount account : server.accounts) {
            configuration.set("backups."+account.name+".balance", account.balance);
        }

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public BankServer getBackup() {
        if (!file.exists()) {

            return new BankServer();

        }

        ArrayList<BankAccount> accounts = new ArrayList<>();
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        for (String key : configuration.getConfigurationSection("backups").getKeys(false)) {
            try {

                Double balance = configuration.getDouble("backups."+key+".balance");
                BankAccount account = new BankAccount(key, balance);
                accounts.add(account);


            }catch (Exception exception){

            }
        }

        FileUtil.delete(file);

        return new BankServer(accounts);

    }
}
