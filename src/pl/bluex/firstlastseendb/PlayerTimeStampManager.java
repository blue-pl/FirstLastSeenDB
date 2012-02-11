package pl.bluex.firstlastseendb;

import com.avaje.ebean.ExpressionList;

public class PlayerTimeStampManager {
    public static void add(PlayerTimeStamp pts) {
        FirstLastSeenDB.database.insert(pts);
    }

    public static void remove(PlayerTimeStamp pts) {
        FirstLastSeenDB.database.delete(pts);
    }

    public static void save(PlayerTimeStamp pts) {
        FirstLastSeenDB.database.save(pts);
    }

    public static PlayerTimeStamp get(String player_name){
        return FirstLastSeenDB.database
                .find(PlayerTimeStamp.class)
                .where()
                .ieq("player", player_name)
                .findUnique();
    }

    public static ExpressionList<PlayerTimeStamp> getHolidays(){
        return FirstLastSeenDB.database
                .find(PlayerTimeStamp.class)
                .where()
                .isNotNull("holiday");
    }
}
