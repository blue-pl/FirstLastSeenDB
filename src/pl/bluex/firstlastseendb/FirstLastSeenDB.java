package pl.bluex.firstlastseendb;

import com.avaje.ebean.EbeanServer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstLastSeenDB extends JavaPlugin {
    protected static final Logger logger = Logger.getLogger("Minecraft");
    protected static String pluginName;
    protected static EbeanServer database;

    private PlayerLogin playerLogin = null;

    @Override
    public void onEnable() {
        pluginName = getDescription().getName();
        PluginManager pm = getServer().getPluginManager();
        setupDatabase();
        playerLogin = new PlayerLogin(this);
        log(Level.INFO, String.format("%s is enabled.", getDescription().getFullName()));
    }

    @Override
    public void onDisable() {
        log(Level.INFO, String.format("%s is disabled.", getDescription().getFullName()));
    }

    protected void log(Level level, String msg) {
        logger.log(level, String.format("[%s] %s", pluginName, msg));
    }

    private void setupDatabase() {
        try {
            getDatabase().find(PlayerTimeStamp.class).findRowCount();
        } catch (PersistenceException ex) {
            log(Level.INFO, "Setting up database");
            installDDL();
        }
        database = getDatabase();
    }

    @Override
    public List<Class<?>> getDatabaseClasses() {
        List<Class<?>> list = new ArrayList<Class<?>>();
        list.add(PlayerTimeStamp.class);
        return list;
    }
}
