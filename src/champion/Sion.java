package champion;

import champion.specification.champion.Champion;
import champion.specification.champion.Tank;
import champion.specification.champion.Warrior;

import static champion.util.GameConstants.*;

public class Sion extends Champion implements Tank, Warrior {

    public Sion(String name, int hp, int attackPoint, int defensePoint) {
        super(name, hp, attackPoint, defensePoint);
    }

    @Override
    public boolean useQ(Champion target) {
        System.out.printf(getName() + " 이(가) 대량 학살 강타를 사용합니다! 각각 %d%%, %d%%, %d%% 확률 별로 %d, %d, %d의 공격을 가합니다!\n"
                , (int) (sionQSkillPercent1 * 100)
                , (int) (sionQSkillPercent2 * 100)
                , (int) (sionQSkillPercent3 * 100)
                , (int) Math.round(getAttackPoint() * sionQSkillDamageMultiple1)
                , (int) Math.round(getAttackPoint() * sionQSkillDamageMultiple2)
                , (int) Math.round(getAttackPoint() * sionQSkillDamageMultiple3));
        System.out.println(getName() + " -> " + target.getName() + "에게 대량 학살 강타!");
        if(Math.random() <= sionQSkillPercent1) {
            System.out.println("약한 대량 학살 강타!");
            return target.takeDamage((int) Math.round(getAttackPoint() * sionQSkillDamageMultiple1));
        } else if(Math.random() <= sionQSkillPercent2) {
            System.out.println("어중간한 대량 학살 강타!");
            return target.takeDamage((int) Math.round(getAttackPoint() * sionQSkillDamageMultiple2));
        } else {
            System.out.println("강한 대량 학살 강타!");
            return target.takeDamage((int) Math.round(getAttackPoint() * sionQSkillDamageMultiple3));
        }
    }

    @Override
    public void specialSkill() {
        if(Math.random() <= sionSpecialSkillRandomPercent) {
            defenceUp();
        } else {
            dash();
        }
    }

    @Override
    public void defenceUp() {
        setDefensePoint(getDefensePoint() + sionDefenceUpDef);
        System.out.println(getName() + " 의 방어력 증가! 방어력을 "
                           + sionDefenceUpDef + " 증가 시킵니다! 현재 방어력 : " + getDefensePoint());
    }

    @Override
    public void dash() {
        setAttackPoint(getAttackPoint() + sionDashAddAtt);
        System.out.println(getName() + " 의 돌진! 공격을 "
                           + sionDashAddAtt + " 증가 시킵니다! 현재 공격력 : " + getAttackPoint());
    }
}
