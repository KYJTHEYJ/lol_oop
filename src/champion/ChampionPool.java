package champion;

import champion.champions.Malphite;
import champion.champions.Soraka;
import champion.champions.Syndra;
import champion.champions.Urgot;
import champion.type.ChampionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static system.util.GameConstants.*;

public class ChampionPool {
    List<Champion> champions = new ArrayList<>();

    public ChampionPool() {
        champions.add(new Urgot(ChampionType.WARRIOR, URGOT_NAME, URGOT_INIT_HP, URGOT_INIT_HP, URGOT_INIT_ATK, URGOT_INIT_DEF));
        champions.add(new Malphite(ChampionType.TANK, MALPHITE_NAME, MALPHITE_INIT_HP, MALPHITE_INIT_HP, MALPHITE_INIT_ATK, MALPHITE_INIT_DEF));
        champions.add(new Syndra(ChampionType.MAGE, SYNDRA_NAME, SYNDRA_INIT_HP, SYNDRA_INIT_HP, MAGE_SUPPORT_MP_INIT_VALUE, SYNDRA_INIT_ATK, SYNDRA_INIT_DEF));
        champions.add(new Soraka(ChampionType.SUPPORT, SORAKA_NAME, SORAKA_INIT_HP, SORAKA_INIT_HP, MAGE_SUPPORT_MP_INIT_VALUE, SORAKA_INIT_ATK, SORAKA_INIT_DEF));
    }

    public List<String> getChampionsNames() {
        return champions.stream().map(champion -> champion.getName()).toList();
    }

    public Optional<Champion> getChampion(String name) {
        return champions.stream().filter(searchChampion -> searchChampion.getName().equals(name)).findFirst();
    }

    public Champion getRandomChampion() {
        int index = new Random().nextInt(champions.size());
        return champions.get(index);
    }
}
