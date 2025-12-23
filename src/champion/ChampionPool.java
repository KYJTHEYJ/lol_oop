package champion;

import java.util.HashMap;
import java.util.Optional;

import static system.util.GameConstants.*;

public class ChampionPool {
    HashMap<String, Champion> champions = new HashMap<>();

    ChampionPool() {
        champions.put(URGOT_NAME, new Urgot(ChampionType.WARRIOR, URGOT_NAME, URGOT_INIT_HP, URGOT_INIT_HP, URGOT_INIT_ATK, URGOT_INIT_DEF));
        champions.put(MALPHITE_NAME, new Malphite(ChampionType.TANK, MALPHITE_NAME, MALPHITE_INIT_HP, MALPHITE_INIT_HP, MALPHITE_INIT_ATK, MALPHITE_INIT_DEF));
        champions.put(SYNDRA_NAME, new Syndra(ChampionType.MAGE, SYNDRA_NAME, SYNDRA_INIT_HP, SYNDRA_INIT_HP, SYNDRA_INIT_ATK, SYNDRA_INIT_DEF));
        champions.put(SORAKA_NAME, new Soraka(ChampionType.SUPPORT, SORAKA_NAME, SORAKA_INIT_HP, SORAKA_INIT_HP, SORAKA_INIT_ATK, SORAKA_INIT_DEF));
    }

    public Optional<Champion> getChampion(String name) {
        return Optional.ofNullable(champions.get(name));
    }
}
