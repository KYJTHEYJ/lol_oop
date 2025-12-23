package champion.champions;

import champion.Champion;
import champion.type.ChampionType;

import static system.util.GameConstants.*;

public class Urgot extends Champion {

    public Urgot(ChampionType championType, String name, int maxHp, int hp, int attackDamage, int defense) {
        super(championType, name, maxHp, hp, attackDamage, defense);
        createdCount++;
    }

    @Override
    public void skillQ(Champion target) {
        System.out.println("<<<< 부식성 폭약 >>>>");
        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 방어력을 %d 감소 시킵니다!\n"
                , target.getName()
                , URGOT_INIT_SKILL_Q_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_Q_COEFFICIENT)
                , URGOT_INIT_SKILL_Q_DEFENCE_MINUS);

        takeDamage(target, URGOT_INIT_SKILL_Q_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_Q_COEFFICIENT));

        target.setDefense(target.getDefense() - URGOT_INIT_SKILL_Q_DEFENCE_MINUS);
        System.out.printf("< %s > 의 방어력 : %d\n", target.getName(), target.getDefense());
    }

    @Override
    public void skillW(Champion target) {
        System.out.println("<<<< 심판의 원 >>>>");
        System.out.printf("< %s > 에게 %d차례 대미지를 %d 를 입힙니다!\n"
                , target.getName()
                , URGOT_INIT_SKILL_W_HITS
                , URGOT_INIT_SKILL_W_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_W_COEFFICIENT));

        for (int index = 0; index < URGOT_INIT_SKILL_W_HITS; index++) {
            takeDamage(target, URGOT_INIT_SKILL_W_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_W_COEFFICIENT));
        }
    }

    @Override
    public void skillE(Champion target) {
        System.out.println("<<<< 경멸 >>>>");
        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 방어력의 3배 만큼 HP를 회복합니다!\n"
                , target.getName()
                , URGOT_INIT_SKILL_E_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_E_COEFFICIENT));

        takeDamage(target, URGOT_INIT_SKILL_E_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_E_COEFFICIENT));
        this.heal(this, this.getDefense() * URGOT_INIT_SKILL_E_HEAL_COEFFICIENT);
    }

    @Override
    public void skillR(Champion target) {
        System.out.println("<<<< 불사의 공포 >>>>");
        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 입힌 후 상대의 체력이 %d%% 이하라면 상대를 처치합니다\n"
                , target.getName()
                , URGOT_INIT_SKILL_R_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_R_COEFFICIENT)
                , (int) (URGOT_INIT_SKILL_R_EXECUTE * 100));

        takeDamage(target, URGOT_INIT_SKILL_R_DAMAGE + (int) Math.floor(this.getAttackDamage() * URGOT_INIT_SKILL_R_COEFFICIENT));

        if(target.getHp() <= (target.getMaxHp() * URGOT_INIT_SKILL_R_EXECUTE)) {
            System.out.println("< 불사의 공포 효과가 발동합니다! 처치합니다! >");
            takeDamage(target, target.getHp() + target.getDefense());
        }
    }
}
