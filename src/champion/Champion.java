package champion;

import system.exceptions.DeathException;
import system.exceptions.MinusHpException;

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
        this.mp = MP_INIT_VALUE;
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
            throw new DeathException(this, "< " + this.name + " > 챔피언은 이미 사망하였습니다");
        }
    }

    private void checkDeath(Champion champion) {
        if (champion.getHp() <= 0) {
            throw new DeathException(champion, "< " + champion.name + " > 챔피언은 이미 사망하였습니다");
        }
    }

    private void checkAllDeath(Champion champion) {
        if (this.getHp() <= 0) {
            throw new DeathException(this, "< " + this.name + " > 챔피언은 이미 사망하였습니다");
        }

        if (champion.getHp() <= 0) {
            throw new DeathException(champion, "< " + champion.name + " > 챔피언은 이미 사망하였습니다");
        }
    }
    //region

    //region 챔피언 Hp, Mp 마이너스 체크
    private void checkHp() {
        if (this.hp < 0) {
            throw new MinusHpException("해당 < " + this.name + " > 챔피언의 hp가 - 수치로 설정 되고 있습니다");
        }
    }

    private void checkMp() {
        if (this.mp < 0) {
            throw new MinusHpException("해당 < " + this.name + " > 챔피언의 mp가 - 수치로 설정 되고 있습니다");
        }
    }

    private void checkHp(Champion target) {
        if (target.getHp() < 0) {
            throw new MinusHpException("해당 < " + target.name + " > 챔피언의 hp가 - 수치로 설정 되고 있습니다");
        }
    }

    private void checkMp(Champion target) {
        if (target.getMp() < 0) {
            throw new MinusHpException("해당 < " + target.name + " > 챔피언의 mp가 - 수치로 설정 되고 있습니다");
        }
    }

    private void checkTargetHpMp() {
        checkHp();
        checkMp();
    }

    private void checkTargetHpMp(Champion champion) {
        checkHp(champion);
        checkMp(champion);
    }
    //region

    public void basicAttack(Champion target) {
        try {
            checkAllDeath(target);
            System.out.printf("< %s > 이 < %s > 에게 기본 공격!\n", this.name, target.getName());
            takeDamage(target, this.attackDamage);
        } catch (DeathException e) {
            System.out.println(e.getMessage());
        }
    }

    public void takeDamage(Champion target, int damage) {
        int takeDamage = damage - target.defense;

        try {
            checkAllDeath(target);
            target.hp = target.hp - takeDamage;
            System.out.printf("< %s > 이 %d 의 대미지를 입습니다!\n", target.name, takeDamage);
            checkHp(target);
        } catch (DeathException e) {
            System.out.println(e.getMessage());
        } catch (MinusHpException e) {
            target.hp = e.getZeroHp();
        } finally {
            System.out.printf("< %s > 의 HP : %d\n", target.name, target.hp);
        }
    }

    public void heal(Champion target, int heal) {
        try {
            checkAllDeath(target);

            this.hp = Math.min(this.hp + heal, target.maxHp);

            System.out.printf("< %s > 이 %d 만큼 HP를 회복합니다!\n", target.name, heal);
            System.out.printf("< %s > 의 HP : %d\n", target.name, target.hp);
        } catch (DeathException e) {
            System.out.println(e.getMessage());
        }
    }

    public void levelUp() {
        try {
            if (this.getLevel() < MAX_LEVEL) {
                checkDeath(this);
                getBenefit().getBenefit(this);
            }
        } catch (DeathException e) {
            System.out.println(e.getMessage());
        }
    }

    public abstract void skillQ(Champion target);

    public abstract void skillW(Champion target);

    public abstract void skillE(Champion target);

    public abstract void skillR(Champion target);
}
