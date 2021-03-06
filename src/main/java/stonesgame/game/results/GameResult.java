package stonesgame.game.results;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Class representing the result of a game played by a specific player.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class GameResult {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * The name of the player.
     */
    @Column(nullable = false)
    private String player;

    /**
     * Indicated whether the player completed the objective of 3 in a row.
     */
    private boolean solved;

    /**
     * The number of turns taken by the player;
     */
    private int turns;

    /**
     * The duration of the game.
     */
    @Column(nullable = false)
    private Duration duration;

    /**
     * The timestamp of when the game was created.
     */
    @Column(nullable = false)
    private ZonedDateTime created;

    /**
     * The timestamp of when the game session was persisted to the database.
     */
    @PrePersist
    protected void onPersist() {
        created = ZonedDateTime.now();
    }

    public String[] getHeaders() {
        Field[] names = GameResult.class.getDeclaredFields();
        StringBuilder s = new StringBuilder();
        for(Field x : names) {
            s.append(x.getName().toUpperCase());
            s.append(" ");
        }

        return s.toString().split(" ");
    }

}
