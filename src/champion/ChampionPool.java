package champion;

import java.util.HashMap;
import java.util.Optional;

import static system.util.GameConstants.*;

public class ChampionPool {
    HashMap<String, Champion> champions = new HashMap<>();

    ChampionPool() {
        champions.put(URGOT_NAME, new Urgot(ChampionType.WARRIOR, URGOT_NAME, URGOT_INIT_HP, URGOT_INIT_HP, URGOT_INIT_ATK, URGOT_INIT_DEF));
    }

    public Optional<Champion> getChampion(String name) {
        return Optional.ofNullable(champions.get(name));
    }
}
