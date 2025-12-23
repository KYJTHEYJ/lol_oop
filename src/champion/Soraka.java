package champion;

import static system.util.GameConstants.*;

public class Soraka extends Champion {

    protected Soraka(ChampionType championType, String name, int maxHp, int hp, int attackDamage, int defense) {
        super(championType, name, maxHp, hp, attackDamage, defense);
        createdCount++;
    }

    @Override
    public void skillQ(Champion target) {
        System.out.println("별부름!");

        int damage = SORAKA_INIT_SKILL_Q_DAMAGE + (int) Math.floor(this.getAttackDamage() * SORAKA_INIT_SKILL_Q_COEFFICIENT);
        int baseHeal = SORAKA_INIT_SKILL_Q_HEAL;
        int supportBonus = this.getChampionType().calculateHealBonus(this.getLevel());
        int totalHeal = baseHeal + supportBonus;

        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 자신의 HP를 %d 회복합니다!\n"
                , target.getName(), damage, totalHeal);

        takeDamage(target, damage);
        heal(this, totalHeal);
    }

    @Override
    public void skillW(Champion target) {
        System.out.println("은하의 마력!");

        int baseHeal = SORAKA_INIT_SKILL_W_HEAL + (int) Math.floor(this.getAttackDamage() * SORAKA_INIT_SKILL_W_COEFFICIENT);
        int supportBonus = this.getChampionType().calculateHealBonus(this.getLevel());
        int totalHeal = baseHeal + supportBonus;

        heal(target, totalHeal);
    }

    @Override
    public void skillE(Champion target) {
        System.out.println("별의 균형!");
        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 방어력을 %d 증가 시킵니다!\n"
                , target.getName()
                , SORAKA_INIT_SKILL_E_DAMAGE + (int) Math.floor(this.getAttackDamage() * SORAKA_INIT_SKILL_E_COEFFICIENT)
                , SORAKA_INIT_SKILL_E_DEF_PLUS);

        takeDamage(target, SORAKA_INIT_SKILL_E_DAMAGE + (int) Math.floor(this.getAttackDamage() * SORAKA_INIT_SKILL_E_COEFFICIENT));
        this.setDefense(this.getDefense() + SORAKA_INIT_SKILL_E_DEF_PLUS);
        System.out.printf("< %s > 의 방어력 : %d\n", target.getName(), target.getDefense());
    }

    @Override
    public void skillR(Champion target) {
        System.out.println("기원!");

        int baseHeal = SORAKA_INIT_SKILL_R_HEAL + (int) Math.floor(this.getAttackDamage() * SORAKA_INIT_SKILL_R_COEFFICIENT);
        int supportBonus = this.getChampionType().calculateHealBonus(this.getLevel());
        int totalHeal = baseHeal + supportBonus;

        heal(target, totalHeal);
    }
}
