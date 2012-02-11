package pl.bluex.firstlastseendb;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "firstlastseendb")
public class PlayerTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "player", unique=true, nullable = false, length = 255)
    private String player;
    @Column(name = "last_seen", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSeen;
    @Column(name = "first_seen", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstSeen;
    @Column(name = "holiday", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date holiday;

    public PlayerTimeStamp() {
    }

    public PlayerTimeStamp(String player) {
        this.player = player;
        this.firstSeen = this.lastSeen = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Date getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Date firstSeen) {
        this.firstSeen = firstSeen;
    }

    public Date getHoliday() {
        return holiday;
    }

    public void setHoliday(Date holidays) {
        this.holiday = holidays;
    }
}
