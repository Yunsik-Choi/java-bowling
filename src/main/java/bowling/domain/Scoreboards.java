package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public class Scoreboards {
    private final Map<Name, Scoreboard> scoreboards;

    public Scoreboards() {
        this.scoreboards = new HashMap<>();
    }

    public void add(Name name, Scoreboard scoreboard) {
        this.scoreboards.put(name, scoreboard);
    }

    public boolean isEndTurn(Name name, Round round) {
        Scoreboard scoreboard = this.scoreboards.get(name);
        return !scoreboard.frame(round).isRemainChance();
    }
}
