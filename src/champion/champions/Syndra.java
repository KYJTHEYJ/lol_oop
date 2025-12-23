package champion.champions;

import champion.Champion;
import champion.type.ChampionType;

import static system.util.GameConstants.*;

public class Syndra extends Champion {

    public Syndra(ChampionType championType, String name, int maxHp, int hp, int mp, int attackDamage, int defense) {
        super(championType, name, maxHp, hp, mp, attackDamage, defense);
        createdCount++;
    }

    @Override
    public void skillQ(Champion target) {
        System.out.println("<<<< 어둠의 구체 >>>>");

        int baseDamage = SYNDRA_INIT_SKILL_Q_DAMAGE + (int) Math.floor(this.getAttackDamage() * SYNDRA_INIT_SKILL_Q_COEFFICIENT);
        int mageBonus = this.getChampionType().calculateSkillBonus(this.getLevel());
        int totalDamage = baseDamage + mageBonus;

        System.out.printf("< %s > 에게 대미지를 %d 를 입힙니다!\n", target.getName(), totalDamage);
        takeDamage(target, totalDamage);
    }

    @Override
    public void skillW(Champion target) {
        System.out.println("<<<< 의지의 힘 >>>>");

        int baseDamage = SYNDRA_INIT_SKILL_W_DAMAGE + (int) Math.floor(this.getAttackDamage() * SYNDRA_INIT_SKILL_W_COEFFICIENT);
        int mageBonus = this.getChampionType().calculateSkillBonus(this.getLevel());
        int totalDamage = baseDamage + mageBonus;

        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 공격력을 %d 감소 시킵니다!\n"
                , target.getName(), totalDamage, SYNDRA_INIT_SKILL_W_ATK_MINUS);

        takeDamage(target, totalDamage);
        target.setAttackDamage(target.getAttackDamage() - SYNDRA_INIT_SKILL_W_ATK_MINUS);
        System.out.printf("< %s > 의 공격력 : %d\n", target.getName(), target.getAttackDamage());
    }

    @Override
    public void skillE(Champion target) {
        System.out.println("<<<< 적군 와해 >>>>");

        int baseDamage = SYNDRA_INIT_SKILL_E_DAMAGE + (int) Math.floor(this.getAttackDamage() * SYNDRA_INIT_SKILL_E_COEFFICIENT);
        int mageBonus = this.getChampionType().calculateSkillBonus(this.getLevel());
        int totalDamage = baseDamage + mageBonus;

        System.out.printf("< %s > 에게 %d차례 대미지를 %d 를 입힙니다!\n"
                , target.getName(), SYNDRA_INIT_SKILL_E_HITS, totalDamage);

        for (int index = 0; index < SYNDRA_INIT_SKILL_E_HITS; index++) {
            if (target.getHp() >= 0) {
                takeDamage(target, totalDamage);
            }
        }
    }

    @Override
    public void skillR(Champion target) {
        System.out.println("<<<< 풀려난 힘 >>>>");

        int baseDamage = SYNDRA_INIT_SKILL_R_DAMAGE + (int) Math.floor(this.getAttackDamage() * SYNDRA_INIT_SKILL_R_COEFFICIENT);
        int mageBonus = this.getChampionType().calculateSkillBonus(this.getLevel());
        int totalDamage = baseDamage + mageBonus;

        System.out.printf("< %s > 에게 %d차례 대미지를 %d 를 입힙니다!\n"
                , target.getName(), SYNDRA_INIT_SKILL_R_HITS, totalDamage);

        for (int index = 0; index < SYNDRA_INIT_SKILL_R_HITS; index++) {
            if (target.getHp() >= 0) {
                takeDamage(target, totalDamage);
            }
        }
    }
}
