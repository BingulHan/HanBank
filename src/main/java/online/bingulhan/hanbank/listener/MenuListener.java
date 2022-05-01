package online.bingulhan.hanbank.listener;

import online.bingulhan.hanbank.HanBank;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void event(InventoryClickEvent event) {
        if (event.getWhoClicked().getOpenInventory().getTitle().equals(ChatColor.translateAlternateColorCodes('&', HanBank.getInstance().lang.guiWords.get("title")))) {
            event.setCancelled(true);



        }


    }
}
