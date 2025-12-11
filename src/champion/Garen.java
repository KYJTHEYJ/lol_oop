package champion;

import champion.specification.Champion;
import champion.specification.Warrior;

import static champion.util.GameConstants.garenDashAddAtt;
import static champion.util.GameConstants.garenQSkillDamage;

public class Garen extends Champion implements Warrior {

    public Garen(String name, int hp, int attackPoint, int defensePoint) {
        super(name, hp, attackPoint, defensePoint);
    }

    @Override
    public boolean useQ(Champion target) {
        System.out.println(getName() + " 이(가) 결정타를 사용합니다! 상대에게 기본 공격력 ("
                           + getAttackPoint() +") 에 추가로 " + garenQSkillDamage + " 대미지를 더한 공격을 가합니다!");
        System.out.println(getName() + " -> " + target.getName() + "에게 결정타!");
        return target.takeDamage(getAttackPoint() + garenQSkillDamage);
    }

    @Override
    public void specialSkill() {
        dash();
    }

    @Override
    public void dash() {
        setAttackPoint(getAttackPoint() + garenDashAddAtt);
        System.out.println(getName() + " 의 돌진! 공격력을 " + garenDashAddAtt
                           + " 증가 시킵니다! 현재 공격력 : " + getAttackPoint());
    }
}
