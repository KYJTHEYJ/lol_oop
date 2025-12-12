package champion.specification.champion;

import champion.specification.resurrection.CommonResurrection;
import champion.specification.resurrection.Resurrection;
import champion.util.BattleUtil;
import champion.util.GameConstants;

import static champion.util.GameConstants.*;

public abstract class Champion {
    // 접근 제어자를 통한 외부 직접 속성 접근 차단
    private String name;
    private int maxHp;
    private int hp;
    private int attackPoint;
    private int defensePoint;
    private Resurrection resurrection;
    private boolean isResurrected = false;

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

    public Resurrection getResurrection() {
        return resurrection;
    }

    // 부활 전략 패턴 사용
    // 일반 챔피언들은 CommonResurrection 을 set하여 사용
    // 특정 챔피언은 특정 Resurrection 을 set하여 사용
    public void setResurrection(Resurrection resurrection) {
        this.resurrection = resurrection;
    }

    @Override
    public String toString() {
        return "Champion{" +
               "name='" + name + '\'' +
               ", maxHp=" + maxHp +
               ", hp=" + hp +
               ", attackPoint=" + attackPoint +
               ", defensePoint=" + defensePoint +
               ", resurrection=" + resurrection +
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

    // 기본 공격 + battleCount 증가 추가
    public boolean basicAttackChampion(Champion target) {
        GameConstants.battleCount++;
        BattleUtil.Log.print(getName() + " -> " + target.getName() + "을 공격!");
        if (Math.random() <= initCriticalPercent) {
            BattleUtil.Log.print("치명타 공격! 2배 공격력으로 주는 대미지!");
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

        BattleUtil.Log.print(name + " 이(가) " + actualDamage + " 대미지를 입었습니다!");
        BattleUtil.Log.print(name + " 의 현재 체력 : " + hp);

        actResurrect();

        return hp <= 0;
    }

    // Q 스킬
    public abstract boolean useQ(Champion target);

    // Q 스킬 + BattleCount 증가
    public final boolean useQWithBattleCount(Champion target) {
        GameConstants.battleCount++;
        return useQ(target);
    }

    // 챔피언 특성 별 고유 스킬
    public abstract void specialSkill();

    // 챔피언 특성 별 고유 스킬 + BattleCount 증가
    public final void specialSkillWithBattleCount() {
        GameConstants.battleCount++;
        specialSkill();
    }

    public Resurrection createResurrection() {
        return new CommonResurrection(this);
    }

    // 부활 했는지 체크하여 1번만 부활
    // 템플릿 메서드 확장
    public final void actResurrect() {
        if(hp <= 0 && !isResurrected) {
            isResurrected = true;
            resurrection.resurrect();

            actAddBuffResurrect();
        }
    }

    // 챔피언 별 부활시 버프 제공
    protected void actAddBuffResurrect() {}
}
