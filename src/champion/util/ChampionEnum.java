package champion.util;

import champion.Ashe;
import champion.Garen;
import champion.Sion;
import champion.Veigar;
import champion.specification.champion.Champion;

public enum ChampionEnum {
    Garen(new Garen(GameConstants.garenNameEN,
            GameConstants.garenInitHp,
            GameConstants.garenInitAttack,
            GameConstants.garenInitDefense))
    , Ashe(new Ashe(
            GameConstants.asheNameEN,
            GameConstants.asheInitHp,
            GameConstants.asheInitAttack,
            GameConstants.asheInitDefense))
    , Sion(new Sion(
            GameConstants.sionNameEN,
            GameConstants.sionInitHp,
            GameConstants.sionInitAttack,
            GameConstants.sionInitDefense))
    , Veigar(new Veigar(
            GameConstants.veigarNameEN,
            GameConstants.veigarInitHp,
            GameConstants.veigarInitAttack,
            GameConstants.veigarInitDefense
    ));

    public final Champion champion;

    ChampionEnum(Champion champion) {
        this.champion = champion;
    }

    public Champion getChampion() {
        return champion;
    }
}
