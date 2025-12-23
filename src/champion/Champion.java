package champion;

import champion.type.ChampionType;
import system.exceptions.DeathException;
import system.exceptions.MinusDamageException;
import system.exceptions.MinusHpException;
import system.exceptions.MinusMpException;

import static system.benefit.LevelUpBenefit.*;
import static system.util.GameConstants.*;

public abstract class Champion {
    private final ChampionType championType;
    private final String name;
    private int level;
    private final int maxHp;
    private int hp;
    private final int maxMp;
    private int mp;
    private int attackDamage;
    private int defense;
    public static int createdCount = 0;

    //region 생성자, getter, setter
    protected Champion(ChampionType championType, String name, int maxHp, int hp, int attackDamage, int defense) {
        this.championType = championType;
        this.name = name;
        this.level = MIN_LEVEL;
        this.maxHp = maxHp;
        this.hp = hp;
        this.maxMp = MP_MAX_VALUE;
        this.mp = COMMON_MP_INIT_VALUE;
        this.attackDamage = attackDamage;
        this.defense = defense;
    }

    protected Champion(ChampionType championType, String name, int maxHp, int hp, int mp, int attackDamage, int defense) {
        this.championType = championType;
        this.name = name;
        this.level = MIN_LEVEL;
        this.maxHp = maxHp;
        this.hp = hp;
        this.maxMp = MP_MAX_VALUE;
        this.mp = mp;
        this.attackDamage = attackDamage;
        this.defense = defense;
    }

    public ChampionType getChampionType() {
        return championType;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxMp() {
        return maxMp;
    }

    public int getMp() {
        return mp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }
    //endregion

    //region 챔피언 사망 여부 체크
    private void checkDeath() {
        if (this.getHp() <= 0) {
            throw new DeathException(this);
        }
    }

    private void checkAllDeath(Champion champion) {
        if (this.getHp() <= 0) {
            throw new DeathException(this);
        }

        if (champion.getHp() <= 0) {
            throw new DeathException(champion);
        }
    }
    //region

    //region 챔피언 Hp, Mp 마이너스 체크
    private void checkMp() {
        if (this.mp < 0) {
            throw new MinusMpException(this);
        }
    }

    private void checkHp(Champion target) {
        if (target.getHp() < 0) {
            throw new MinusHpException(target);
        }
    }
    //region

    //region
    private void checkMinusDamage(Champion target, int damage) {
        if (damage <= 0) {
            throw new MinusDamageException(target, damage);
        }
    }
    //endregion

    public void basicAttack(Champion target) {
        checkAllDeath(target);
        System.out.printf("< %s > 이(가) < %s > 에게 기본 공격!\n", this.name, target.getName());
        takeDamage(target, this.attackDamage);
    }

    public void takeDamage(Champion target, int damage) {
        int takeDamage = damage - target.defense;

        try {
            checkAllDeath(target);
            checkMinusDamage(target, takeDamage);
            target.hp = target.hp - takeDamage;
            System.out.printf("< %s > 이(가) %d 의 대미지를 입습니다!\n", target.name, takeDamage);
            checkHp(target);
        } catch (MinusDamageException e) {
            target.hp = target.hp - e.getOneDamage();
            System.out.printf("< %s > 이(가) %d 의 대미지를 입습니다!\n", target.name, e.getOneDamage());
            checkHp(target);
        } finally {
            System.out.printf("< %s > 의 HP : %d\n", target.name, target.hp);
        }
    }

    public void heal(Champion target, int heal) {
        checkAllDeath(target);
        this.hp = Math.min(this.hp + heal, target.maxHp);
        System.out.printf("< %s > 이(가) %d 만큼 HP를 회복합니다!\n", target.name, heal);
        System.out.printf("< %s > 의 HP : %d\n", target.name, target.hp);
    }

    public void levelUp() {
        if (this.getLevel() < MAX_LEVEL) {
            checkDeath();
            getBenefit().getBenefit(this);
        } else {
            System.out.printf("< %s > 가 레벨이 이미 최대이므로 HP와 MP를 전부 회복합니다!", this.name);
            this.hp = this.getMaxHp();
            this.mp = this.getMaxMp();
        }
    }

    public abstract void skillQ(Champion target);

    public void useQSkill(Champion target) {
        try {
            checkAllDeath(target);
            this.mp -= 1;
            checkMp();
            skillQ(target);
            System.out.printf("< %s > 의 MP를 1 소모합니다. 남은 MP: %d\n", this.name, this.mp);
        } catch (MinusMpException e) {
            basicAttack(target);
        }
    }

    public abstract void skillW(Champion target);

    public void useWSkill(Champion target) {
        try {
            checkAllDeath(target);
            this.mp -= 1;
            checkMp();
            skillW(target);
            System.out.printf("< %s > 의 MP를 1 소모합니다. (남은 MP: %d)\n", this.name, this.mp);
        } catch (MinusMpException e) {
            basicAttack(target);
        }
    }

    public abstract void skillE(Champion target);

    public void useESkill(Champion target) {
        try {
            checkAllDeath(target);
            this.mp -= 1;
            checkMp();
            skillE(target);
            System.out.printf("< %s > 의 MP를 1 소모합니다. (남은 MP: %d)\n", this.name, this.mp);
        } catch (MinusMpException e) {
            basicAttack(target);
        }
    }

    public abstract void skillR(Champion target);

    public void useRSkill(Champion target) {
        try {
            checkAllDeath(target);
            this.mp -= 1;
            checkMp();
            skillR(target);
            System.out.printf("< %s > 의 MP를 1 소모합니다. (남은 MP: %d)\n", this.name, this.mp);
        } catch (MinusMpException e) {
            basicAttack(target);
        }
    }
}
