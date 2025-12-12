package champion;

import champion.specification.champion.Champion;
import champion.specification.champion.Ranged;
import champion.specification.resurrection.CommonResurrection;
import champion.util.BattleUtil;

import static champion.util.GameConstants.*;

public class Ashe extends Champion implements Ranged {
    // Ranged 특성 스택
    private int kiteStack = 0;

    public Ashe(String name, int hp, int attackPoint, int defensePoint) {
        super(name, hp, attackPoint, defensePoint);
        setResurrection(new CommonResurrection(this));
    }

    @Override
    public boolean takeDamage(int damage) {
        if(kiteStack > 0 && Math.random() <= kitePercent) {
            kiteStack--;
            BattleUtil.Log.print(getName() + " 이(가) 카이팅으로 공격을 회피했습니다! 남은 카이팅 : " + kiteStack);
            return checkHp();
        } else {
            return super.takeDamage(damage);
        }
    }

    @Override
    public boolean useQ(Champion target) {
        setAttackPoint(getAttackPoint() + asheAfterQSkillPlusAttPoint);
        BattleUtil.Log.print(getName() + " 의 궁사의 집중 공격! 공격력이 " + asheAfterQSkillPlusAttPoint
                             + " 증가하고 " + asheAfterQSkillAttTime + "번 공격합니다! 현재 공격력 : " + getAttackPoint());
        for(int index = 0; index < asheAfterQSkillAttTime; index++) {
            basicAttackChampion(target);
        }

        return target.checkHp();
    }

    @Override
    public void specialSkill() {
        kite();
    }

    @Override
    public void kite() {
        kiteStack++;
        BattleUtil.Log.print(getName() + " 의 카이팅! 1번당 공격을 " + (kitePercent * 100)
                             + "% 확률로 회피합니다! 남은 카이팅 : " + kiteStack);
    }
}
