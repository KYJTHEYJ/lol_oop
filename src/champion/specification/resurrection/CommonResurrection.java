package champion.specification.resurrection;

import champion.specification.champion.Champion;
import champion.util.BattleUtil;
import champion.util.GameConstants;

import static champion.util.GameConstants.commonResurrectHpPercent;

public class CommonResurrection implements Resurrection {
    private final Champion champion;

    public CommonResurrection(Champion champion) {
        this.champion = champion;
    }

    @Override
    public void resurrect() {
        GameConstants.battleCount++;
        BattleUtil.Log.print(champion.getName() + " 이(가) 치명적인 피해를 입었습니다!");
        BattleUtil.Log.print(String.format(champion.getName() + " 이(가) HP %d%% (%d)인 상태로 부활하여 다시 전투 합니다!"
                , (int) (commonResurrectHpPercent * 100), (int) Math.round(champion.getMaxHp() * commonResurrectHpPercent)));
        champion.setHp((int) Math.round(champion.getMaxHp() * commonResurrectHpPercent));
    }
}
