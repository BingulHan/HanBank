package online.bingulhan.hanbank.bll;

import online.bingulhan.hanbank.BankAccount;
import online.bingulhan.hanbank.HanBank;
import online.bingulhan.hanbank.gui.BaseGui;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerManager {

    public boolean isAccountRegister(OfflinePlayer player) {

        for (BankAccount account : HanBank.getInstance().bankServer.accounts) {
            if (account.name.equals(player.getName())) {
                return true;
            }
        }

        return false;

    }

    public BankAccount getAccount(OfflinePlayer player) throws NullPointerException{
        for (BankAccount account : HanBank.getInstance().bankServer.accounts) {
            if (account.name.equals(player.getName())) {
                return account;
            }
        }

        return null;
    }

    public void createAccount(OfflinePlayer player) {
        if (!isAccountRegister(player)) {

            BankAccount account = new BankAccount(player.getName(), 0.0);
            HanBank.getInstance().bankServer.accounts.add(account);

        }
    }

    public Double getBalance(OfflinePlayer player) {
        if (isAccountRegister(player)) {

            return getAccount(player).balance;

        }else {
            return 0.0;
        }
    }

    public void openGui(Player player, BaseGui gui) {
        gui.open(player);
    }
}
