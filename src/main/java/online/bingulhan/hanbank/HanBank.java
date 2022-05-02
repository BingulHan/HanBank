package online.bingulhan.hanbank;

import online.bingulhan.hanbank.backup.IBackupManager;
import online.bingulhan.hanbank.backup.YamlBackupManager;
import online.bingulhan.hanbank.bll.PlayerManager;
import online.bingulhan.hanbank.cmd.CMDBank;
import online.bingulhan.hanbank.economy.IEconomyManager;
import online.bingulhan.hanbank.economy.VaultEconomyManager;
import online.bingulhan.hanbank.lang.HanLang;
import online.bingulhan.hanbank.lang.YamlLang;
import online.bingulhan.hanbank.listener.MenuListener;
import online.bingulhan.hanbank.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class HanBank extends JavaPlugin {

    public BankServer bankServer;

    private static HanBank instance;

    public IBackupManager backupManager;
    public IEconomyManager economyManager;

    public HanLang lang;


    @Override
    public void onEnable() {
        instance=this;

        getConfig().options().copyDefaults(true);
        saveConfig();



        backupManager=new YamlBackupManager();

        bankServer=backupManager.getBackup();

        economyManager=new VaultEconomyManager();


        lang= new YamlLang();

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new MenuListener(), this);


        getCommand("banka").setExecutor(new CMDBank());

        PlayerManager playerManager = new PlayerManager();
        for (Player player: getServer().getOnlinePlayers()) {

            playerManager.createAccount(player);
        }

        if( Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")){
            //Registering placeholder will be use here
            new BankHook().register();
        }

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+" ");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"-------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+" ");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"HanBank Versiyon: 1.0.0");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Yapimci: BingulHan");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+" ");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"-------------------------");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+" ");



    }

    @Override
    public void onDisable() {

        backupManager.backup(bankServer);

    }

    public static HanBank getInstance() {
        return instance;

    }
}
