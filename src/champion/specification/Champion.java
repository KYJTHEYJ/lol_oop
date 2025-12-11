package champion.specification;

import static champion.util.GameConstants.criticalDamageMultiple;
import static champion.util.GameConstants.initCriticalPercent;

public abstract class Champion {
    // 접근 제어자를 통한 외부 직접 속성 접근 차단
    private String name;
    private int maxHp;
    private int hp;
    private int attackPoint;
    private int defensePoint;

    //region Getter and Setter
    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public void setAttackPoint(int attackPoint) {
        this.attackPoint = attackPoint;
    }

    public int getDefensePoint() {
        return defensePoint;
    }

    public void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    @Override
    public String toString() {
        return "Champion{" +
               "name='" + name + '\'' +
               ", maxHp=" + maxHp +
               ", hp=" + hp +
               ", attackPoint=" + attackPoint +
               ", defensePoint=" + defensePoint +
               '}';
    }
    //endregion

    public Champion(String name
            , int hp
            , int attackPoint
            , int defensePoint) {
        this.name = name;
        this.maxHp = hp;
        this.hp = hp;
        this.attackPoint = attackPoint;
        this.defensePoint = defensePoint;
    }

    // 자신의 HP 체크
    public boolean checkHp() {
        return getHp() <= 0;
    }

    // 기본 공격
    public boolean basicAttackChampion(Champion target) {
        System.out.println(getName() + " -> " + target.getName() + "을 공격!");
        if (Math.random() <= initCriticalPercent) {
            System.out.println("치명타 공격! 2배 대미지!");
            return target.takeDamage(attackPoint * criticalDamageMultiple);
        } else {
            return target.takeDamage(attackPoint);
        }
    }

    // 자신의 HP를 체크해야함
    public boolean takeDamage(int damage) {
        int actualDamage = damage - defensePoint;
        if (actualDamage < 0) {
            actualDamage = 0;
        }

        hp -= actualDamage;

        System.out.println(name + " 이(가) " + actualDamage + " 대미지를 입었습니다!");
        System.out.println(name + " 의 현재 체력 : " + hp);

        return hp <= 0;
    }

    // Q 스킬
    public abstract boolean useQ(Champion target);

    // 챔피언 특성 별 고유 스킬
    public abstract void specialSkill();
}
