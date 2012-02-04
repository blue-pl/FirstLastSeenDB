package pl.bluex.firstlastseendb;

import org.bukkit.event.player.*;


public class PlayerLogin extends PlayerListener {
	private static FirstLastSeenDB plugin;

	public PlayerLogin(FirstLastSeenDB instance){
		plugin = instance;
	}

    @Override
    public void onPlayerJoin(PlayerJoinEvent event) {
        String player_name = event.getPlayer().getName();
        PlayerTimeStamp pts = PlayerTimeStamp.get(player_name);
        if(pts == null) {
            FirstLastSeenDB.database.insert(new PlayerTimeStamp(player_name));
        }
    }

    @Override
    public void onPlayerQuit(final PlayerQuitEvent event) {
        onPlayerAction(event);
    }

    @Override
    public void onPlayerKick(final PlayerKickEvent event) {
        onPlayerAction(event);
    }

    private void onPlayerAction(PlayerEvent event) {
        String player_name = event.getPlayer().getName();
        PlayerTimeStamp pts = PlayerTimeStamp.get(player_name);
        pts.setLastSeen();
        FirstLastSeenDB.database.save(pts);
    }
}