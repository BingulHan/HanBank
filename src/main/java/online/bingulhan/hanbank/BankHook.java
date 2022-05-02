package online.bingulhan.hanbank;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import online.bingulhan.hanbank.bll.PlayerManager;
import org.bukkit.entity.Player;

public class BankHook extends PlaceholderExpansion {

    /*
    The identifier, shouldn't contain any _ or %
     */
    public String getIdentifier() {
        return "hanbank";
    }

    public String getPlugin() {
        return null;
    }


    /*
     The author of the Placeholder
     This cannot be null
     */
    public String getAuthor() {
        return "BingulHan";
    }

    /*
     Same with #getAuthor() but for versioon
     This cannot be null
     */

    public String getVersion() {
        return "1.0";
    }

    /*
    Use this method to setup placeholders
    This is somewhat similar to EZPlaceholderhook
     */
    public String onPlaceholderRequest(Player player, String identifier) {
        /*
         %tutorial_onlines%
         Returns the number of online players
          */
        if(identifier.equalsIgnoreCase("hesap")){

            return ""+new PlayerManager().getBalance(player.getPlayer());


        }

        if(player == null){
            return "";
        }




        return null;
    }
}
