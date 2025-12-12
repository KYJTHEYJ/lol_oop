package champion.specification.resurrection;

import champion.Sion;
import champion.util.BattleUtil;
import champion.util.GameConstants;

import static champion.util.GameConstants.sionGloriousDeathAddAttPoint;
import static champion.util.GameConstants.sionGloriousDeathResurrectHP;

public class SionGloriousDeath implements Resurrection {

    Sion sion;

    public SionGloriousDeath(Sion sion) {
        this.sion = sion;
    }

    @Override
    public void resurrect() {
        GameConstants.battleCount++;
        BattleUtil.Log.print(sion.getName() + " 의 영광스러운 죽음!");
        BattleUtil.Log.print(String.format(sion.getName() + " 이 %d 공격력을 얻지만 HP가 %d으로 부활합니다! (현재 공격력 : %d)"
                , sionGloriousDeathAddAttPoint, sionGloriousDeathResurrectHP, sion.getAttackPoint() + sionGloriousDeathAddAttPoint));
        sion.setHp(sionGloriousDeathResurrectHP);
        sion.setAttackPoint(sion.getAttackPoint() + sionGloriousDeathAddAttPoint);
    }
}
