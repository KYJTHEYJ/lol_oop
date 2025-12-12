package champion;

import champion.specification.champion.Champion;
import champion.specification.champion.Warrior;
import champion.specification.resurrection.CommonResurrection;
import champion.util.BattleUtil;

import static champion.util.GameConstants.*;
import static champion.util.GameConstants.resurrectionBuffAddAtt;
import static champion.util.GameConstants.resurrectionBuffAddDef;
import static champion.util.GameConstants.resurrectionBuffHealHp;
import static champion.util.GameConstants.resurrectionSkillPercentLess;

public class Garen extends Champion implements Warrior {

    public Garen(String name, int hp, int attackPoint, int defensePoint) {
        super(name, hp, attackPoint, defensePoint);
    }

    @Override
    public boolean useQ(Champion target) {
        BattleUtil.Log.print(getName() + " 이(가) 결정타를 사용합니다! 상대에게 기본 공격력 ("
                             + getAttackPoint() + ") 에 추가로 " + garenQSkillDamage + " 대미지를 더한 공격을 가합니다!");
        BattleUtil.Log.print(getName() + " -> " + target.getName() + "에게 결정타!");
        return target.takeDamage(getAttackPoint() + garenQSkillDamage);
    }

    @Override
    public void specialSkill() {
        dash();
    }

    @Override
    public void dash() {
        setAttackPoint(getAttackPoint() + garenDashAddAtt);
        BattleUtil.Log.print(getName() + " 의 돌진! 공격력을 " + garenDashAddAtt
                             + " 증가 시킵니다! 현재 공격력 : " + getAttackPoint());
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
