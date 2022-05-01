package online.bingulhan.hanbank.economy;

import net.milkbowl.vault.economy.Economy;
import online.bingulhan.hanbank.HanBank;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultEconomyManager implements IEconomyManager{

    Economy econ = null;

    public VaultEconomyManager() {
        if (HanBank.getInstance().getServer().getPluginManager().getPlugin("Vault") == null) {
            HanBank.getInstance().getServer().getPluginManager().disablePlugin(HanBank.getInstance());
        }
        RegisteredServiceProvider<Economy> rsp = HanBank.getInstance().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            HanBank.getInstance().getServer().getPluginManager().disablePlugin(HanBank.getInstance());
        }
        econ = rsp.getProvider();

    }
    @Override
    public void deposit(OfflinePlayer player, Double money) {

        econ.depositPlayer(player, money);

    }


    @Override
    public void withdraw(OfflinePlayer player, Double money) {
        econ.withdrawPlayer(player, money);

    }

    @Override
    public boolean hasMoney(OfflinePlayer player, Double money) {

        if (econ.getBalance(player)>=money) return true;
        return false;
    }
}
