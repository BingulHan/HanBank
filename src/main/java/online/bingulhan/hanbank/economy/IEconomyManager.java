package online.bingulhan.hanbank.economy;

import org.bukkit.OfflinePlayer;

public interface IEconomyManager {

    public void deposit(OfflinePlayer player, Double money);
    public void withdraw(OfflinePlayer player, Double money);
    public boolean hasMoney(OfflinePlayer player, Double money);

}
