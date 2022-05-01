package online.bingulhan.hanbank.cmd;

import online.bingulhan.hanbank.BankAccount;
import online.bingulhan.hanbank.HanBank;
import online.bingulhan.hanbank.bll.PlayerManager;
import online.bingulhan.hanbank.gui.BankAccountGui;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class CMDBank implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length<1) {
            for (String s : HanBank.getInstance().lang.listWords.get("help")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
            }
        }else {
            if (args.length>0) {
                if (args[0].equals("menu")) {
                    Player player = (Player) sender;
                    new PlayerManager().openGui(player, new BankAccountGui());
                }
                if (args[0].equals("miktar") || args[0].equals("hesap")) {

                    Player player = (Player) sender;
                    String s = HanBank.getInstance().lang.words.get("bank-info");
                    s = ChatColor.translateAlternateColorCodes('&', s);
                    s = StringUtils.replace(s, "%miktar%", ""+new PlayerManager().getBalance(((Player) sender).getPlayer()).toString());

                    sender.sendMessage(s);
                }

                if (args[0].equals("cek")) {
                    Player player = (Player) sender;
                    if (args.length>1) {
                        Double b = Double.parseDouble(args[1]);
                        PlayerManager manager= new PlayerManager();

                        if (manager.getBalance(player.getPlayer())>=b) {

                            manager.getAccount(player.getPlayer()).balance=manager.getAccount(player.getPlayer()).balance-b;
                            HanBank.getInstance().economyManager.deposit(player, b);



                            try {
                                String s = HanBank.getInstance().lang.words.get("bank-withdraw");
                                s = ChatColor.translateAlternateColorCodes('&', s);
                                s = StringUtils.replace(s, "%miktar%", ""+b);

                                sender.sendMessage(s);

                            }catch (Exception exception) {
                            }


                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', HanBank.getInstance().lang.words.get("nobankmoney")));
                        }
                    }

                }

                if (args[0].equals("yatir")) {
                    Player player = (Player) sender;
                    if (args.length>1) {
                        Double b = Double.parseDouble(args[1]);

                        PlayerManager manager= new PlayerManager();


                        if (HanBank.getInstance().economyManager.hasMoney(player, b)) {

                            manager.getAccount(player.getPlayer()).balance=manager.getAccount(player.getPlayer()).balance+b;

                           HanBank.getInstance().economyManager.withdraw(player, b);

                            try {
                                String s = HanBank.getInstance().lang.words.get("bank-deposite");
                                s = ChatColor.translateAlternateColorCodes('&', s);
                                s = StringUtils.replace(s, "%miktar%", ""+b);

                                sender.sendMessage(s);
                            }catch (Exception exception) {

                            }


                        }else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', HanBank.getInstance().lang.words.get("nobankmoney")));
                        }
                    }

                }
            }
        }
        return true;
    }
}
