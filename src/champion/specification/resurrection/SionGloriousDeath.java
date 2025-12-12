package champion.specification.resurrection;

import champion.Sion;
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
        System.out.println(sion.getName() + " 의 영광스러운 죽음!");
        System.out.printf(sion.getName() + " 이 %d 공격력을 얻지만 HP가 %d으로 부활합니다! (현재 공격력 : %d)\n"
                , sionGloriousDeathAddAttPoint, sionGloriousDeathResurrectHP, sion.getAttackPoint() + sionGloriousDeathAddAttPoint);
        sion.setHp(sionGloriousDeathResurrectHP);
        sion.setAttackPoint(sion.getAttackPoint() + sionGloriousDeathAddAttPoint);
    }
}
