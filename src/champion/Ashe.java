package champion;

import champion.specification.champion.*;
import champion.specification.resurrection.CommonResurrection;
import champion.util.BattleUtil;

import static champion.util.GameConstants.*;

public class Ashe extends Champion implements Ranged {
    // Ranged 특성 스택
    private int kiteStack = 0;

    public Ashe(String name, int hp, int attackPoint, int defensePoint) {
        super(name, hp, attackPoint, defensePoint);
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
        BattleUtil.Log.print(getName() + " 의 카이팅! 1번당 공격을 " + (int) (kitePercent * 100)
                             + "% 확률로 회피합니다! 남은 카이팅 : " + kiteStack);
    }

    @Override
    protected void actAddBuffResurrect() {
        if(Math.random() <= resurrectionSkillPercentMajor) {
            setAttackPoint(getAttackPoint() + resurrectionBuffAddAtt);
            BattleUtil.Log.print(getName() + " 이(가) 부활 버프로 " + resurrectionBuffAddAtt + " 공격력을 얻습니다! (현재 공격력 : " + getAttackPoint() + ")");
        } else if(Math.random() <= resurrectionSkillPercentLess) {
            setAttackPoint(getAttackPoint() + resurrectionBuffAddDef);
            BattleUtil.Log.print(getName() + " 이(가) 부활 버프로 " + resurrectionBuffAddDef + " 방어력을 얻습니다! (현재 방어력 : " + getDefensePoint() + ")");
        } else if(Math.random() <= resurrectionSkillPercentLess) {
            int resultHp = getHp() + resurrectionBuffHealHp;
            setHp(Math.min(resultHp, getMaxHp()));
            BattleUtil.Log.print(getName() + " 이(가) 부활 버프로 HP를 추가로 " + resurrectionBuffHealHp + " 회복합니다! (현재 HP : " + getHp() + ")");
        }
    }
}
