package online.bingulhan.hanbank.gui;

import com.cryptomorin.xseries.XMaterial;
import online.bingulhan.hanbank.HanBank;
import online.bingulhan.hanbank.bll.PlayerManager;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BankAccountGui extends BaseGui{

    @Override
    public void open(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', HanBank.getInstance().lang.guiWords.get("title")));

        player.closeInventory();

        ItemStack withDraw = new ItemStack(XMaterial.GOLD_INGOT.parseItem());
        ItemMeta metawithdraw = withDraw.getItemMeta();
        metawithdraw.setDisplayName(ChatColor.translateAlternateColorCodes('&', HanBank.getInstance().lang.guiWords.get("withdraw")));
        ArrayList<String> loreW = new ArrayList<>();
        loreW.add(ChatColor.translateAlternateColorCodes('&', "&f/banka cek <miktar>"));
        metawithdraw.setLore(loreW);

        withDraw.setItemMeta(metawithdraw);

        ItemStack deposite = new ItemStack(XMaterial.GOLD_INGOT.parseItem());
        ItemMeta metadeposite = deposite.getItemMeta();
        metadeposite.setDisplayName(ChatColor.translateAlternateColorCodes('&', HanBank.getInstance().lang.guiWords.get("deposite")));
        ArrayList<String> loreD = new ArrayList<>();
        loreD.add(ChatColor.translateAlternateColorCodes('&', "&f/banka yatir <miktar>"));
        metadeposite.setLore(loreD);

        deposite.setItemMeta(metadeposite);

        ItemStack info = new ItemStack(XMaterial.BOOK.parseItem());
        ItemMeta metainfo = info.getItemMeta();
        String s = HanBank.getInstance().lang.guiWords.get("info");
        s = StringUtils.replace(s, "%miktar%", ""+new PlayerManager().getBalance(player));
        metainfo.setDisplayName(ChatColor.translateAlternateColorCodes('&', s));
        info.setItemMeta(metainfo);

        inventory.setItem(12, withDraw);
        inventory.setItem(13, info);
        inventory.setItem(14, deposite);


        HanBank.getInstance().getServer().getScheduler().runTaskLater(HanBank.getInstance(), () -> {
            player.openInventory(inventory);
        }, 2);


    }
}
