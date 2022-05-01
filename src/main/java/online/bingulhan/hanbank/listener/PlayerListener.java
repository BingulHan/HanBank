package online.bingulhan.hanbank.listener;

import online.bingulhan.hanbank.bll.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

     @EventHandler
     public void event(PlayerJoinEvent e) {

          new PlayerManager().createAccount(e.getPlayer());



     }
}
