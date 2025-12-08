package champion;

import champion.specification.Champion;
import champion.specification.Tank;
import champion.specification.Warrior;

import java.math.RoundingMode;

public class Sion extends Champion implements Tank, Warrior {

    public Sion(String name, int level, int hp, int attackPoint, int defensePoint) {
        super(name, level, hp, attackPoint, defensePoint);
    }

    @Override
    public boolean useQ(Champion target) {
        System.out.printf(getName() + " 이(가) 대량 학살 강타를 사용합니다! 각각 30%%, 30%%, 40%% 확률 별로 %d, %d, %d의 공격을 가합니다!\n"
                , (int) Math.round(getAttackPoint() * 1.5), (int) Math.round(getAttackPoint() * 2), (int) Math.round(getAttackPoint() * 2.5));
        System.out.println(getName() + " -> " + target.getName() + "에게 대량 학살 강타!");
        if(Math.random() <= 0.4) {
            System.out.println("약한 대량 학살 강타!");
            return target.takeDamage((int) Math.round(getAttackPoint() * 1.5));
        } else if(Math.random() <= 0.3) {
            System.out.println("어중간한 대량 학살 강타!");
            return target.takeDamage((int) Math.round(getAttackPoint() * 2));
        } else {
            System.out.println("강한 대량 학살 강타!");
            return target.takeDamage((int) Math.round(getAttackPoint() * 2.5));
        }
    }

    @Override
    public void specialSkill() {
        if(Math.random() <= 0.5) {
            defenceUp();
        } else {
            dash();
        }
    }

    @Override
    public void defenceUp() {
        setDefensePoint(getDefensePoint() + 8);
        System.out.println(getName() + " 의 방어력 증가! 방어력을 8 증가 시킵니다! 현재 방어력 : " + getDefensePoint());
    }

    @Override
    public void dash() {
        setAttackPoint(getAttackPoint() + 5);
        System.out.println(getName() + " 의 돌진! 공격을 5 증가 시킵니다! 현재 공격력 : " + getAttackPoint());
    }
}
