package champion.champions;

import champion.Champion;
import champion.type.ChampionType;

import static system.util.GameConstants.*;

public class Malphite extends Champion {

    public Malphite(ChampionType championType, String name, int maxHp, int hp, int attackDamage, int defense) {
        super(championType, name, maxHp, hp, attackDamage, defense);
        createdCount++;
    }

    @Override
    public void skillQ(Champion target) {
        System.out.println("<<<< 지진의 파편! >>>>");
        System.out.printf("< %s > 에게 대미지를 %d 를 입힙니다!\n"
                , target.getName()
                , MALPHITE_INIT_SKILL_Q_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_Q_COEFFICIENT));
        takeDamage(target, MALPHITE_INIT_SKILL_Q_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_Q_COEFFICIENT));
    }

    @Override
    public void skillW(Champion target) {
        System.out.println("<<<< 천둥소리! >>>>");
        System.out.printf("< %s > 에게 대미지를 %d 를 입힙니다!\n"
                , target.getName()
                , MALPHITE_INIT_SKILL_W_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_W_COEFFICIENT));
        takeDamage(target, MALPHITE_INIT_SKILL_W_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_W_COEFFICIENT));
    }

    @Override
    public void skillE(Champion target) {
        System.out.println("<<<< 지면강타! >>>>");
        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 방어력을 %d 감소 시킵니다!\n"
                , target.getName()
                , MALPHITE_INIT_SKILL_E_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_E_COEFFICIENT)
                , MALPHITE_INIT_SKILL_E_DEFENCE_MINUS);

        takeDamage(target, MALPHITE_INIT_SKILL_E_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_E_COEFFICIENT));

        target.setDefense(target.getDefense() - MALPHITE_INIT_SKILL_E_DEFENCE_MINUS);
        System.out.printf("< %s > 의 방어력 : %d\n", target.getName(), target.getDefense());
    }

    @Override
    public void skillR(Champion target) {
        System.out.println("<<<< 멈출 수 없는 힘! >>>>");
        System.out.printf("< %s > 에게 대미지를 %d 를 입히고 방어력을 %d 감소 시킵니다!\n"
                , target.getName()
                , MALPHITE_INIT_SKILL_R_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_R_COEFFICIENT)
                , MALPHITE_INIT_SKILL_R_DEFENCE_MINUS);

        takeDamage(target, MALPHITE_INIT_SKILL_R_DAMAGE + (int) Math.floor(this.getAttackDamage() * MALPHITE_INIT_SKILL_R_COEFFICIENT));

        target.setDefense(target.getDefense() - MALPHITE_INIT_SKILL_R_DEFENCE_MINUS);
        System.out.printf("< %s > 의 방어력 : %d\n", target.getName(), target.getDefense());
    }
}
