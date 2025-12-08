package champion;

import champion.specification.Champion;
import champion.specification.Ranged;

public class Ashe extends Champion implements Ranged {
    // Ranged 특성 스택
    private int kiteStack = 0;

    public Ashe(String name, int level, int hp, int attackPoint, int defensePoint) {
        super(name, level, hp, attackPoint, defensePoint);
    }

    @Override
    public boolean takeDamage(int damage) {
        if(kiteStack > 0 && Math.random() <= 0.3) {
            kiteStack--;
            System.out.println(getName() + " 이(가) 카이팅으로 공격을 회피했습니다! 남은 카이팅 : " + kiteStack);
            return checkHp();
        } else {
            return super.takeDamage(damage);
        }
    }

    @Override
    public boolean useQ(Champion target) {
        setAttackPoint(getAttackPoint() + 3);
        System.out.println(getName() + " 의 궁사의 집중 공격! 공격력이 3 증가하고 3번 공격합니다! 현재 공격력 : " + getAttackPoint());
        for(int index = 0; index < 3; index++) {
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
        System.out.println(getName() + " 의 카이팅! 1번당 공격을 30% 확률로 회피합니다! 남은 카이팅 : " + kiteStack);
    }
}
