package pl.bluex.firstlastseendb;

import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerLogin implements Listener {

	public PlayerLogin(FirstLastSeenDB plugin){
		Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
	}

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        onPlayerAction(event);
    }

    @EventHandler
    public void onPlayerQuit(final PlayerQuitEvent event) {
        onPlayerAction(event);
    }

    private void onPlayerAction(PlayerEvent event) {
        String player_name = event.getPlayer().getName();
        PlayerTimeStamp pts = PlayerTimeStampManager.get(player_name);
        if(pts == null) {
            pts = new PlayerTimeStamp(player_name);
        }
        pts.setLastSeen(new Date());
        PlayerTimeStampManager.save(pts);
    }
}