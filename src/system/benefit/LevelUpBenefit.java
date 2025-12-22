package system.benefit;

import champion.Champion;
import champion.ChampionType;

import java.util.Objects;
import java.util.Random;

import static system.util.GameConstants.*;

public enum LevelUpBenefit {
    ALL_RESTORE(20) {
        @Override
        public void getBenefit(Champion target) {
            target.setHp(target.getMaxHp());
            target.setMp(target.getMaxMp());

            System.out.printf("< %s > 레벨 업! Lv.%d -> Lv.%d\n", target.getName(), target.getLevel(), target.getLevel() + 1);
            System.out.printf("< %s > 레벨 업하여 HP와 MP가 전부 회복됩니다!\n", target.getName());
        }
    }
    , ATK_UP(40) {
        @Override
        public void getBenefit(Champion target) {
            target.setAttackDamage(target.getAttackDamage() + LVUP_ATK_PLUS);
            target.setMp(Math.min(target.getMp(), target.getMp() + LVUP_MP_HEAL));

            System.out.printf("< %s > 레벨 업! Lv.%d -> Lv.%d\n", target.getName(), target.getLevel(), target.getLevel() + 1);
            System.out.printf("< %s > 레벨 업하여 공격력이 %d 증가가 됩니다! MP가 %d 회복됩니다! 현재 공격력 : %d\n", target.getName(), LVUP_ATK_PLUS, LVUP_MP_HEAL, target.getAttackDamage());
            if (target.getChampionType().equals(ChampionType.WARRIOR)) {
                target.setAttackDamage(target.getAttackDamage() + ChampionType.WARRIOR.getMerit());
                System.out.printf("< %s > 의 전사 특성으로 공격력이 %d 추가로 증가합니다! 현재 공격력 : %d\n", target.getName(), ChampionType.WARRIOR.getMerit(), target.getAttackDamage());
            }
        }
    }
    , DEF_UP(40) {
        @Override
        public void getBenefit(Champion target) {
            target.setAttackDamage(target.getDefense() + LVUP_DEF_PLUS);
            target.setMp(Math.min(target.getMp(), target.getMp() + LVUP_MP_HEAL));

            System.out.printf("< %s > 레벨 업! Lv.%d -> Lv.%d\n", target.getName(), target.getLevel(), target.getLevel() + 1);
            System.out.printf("< %s > 레벨 업하여 방어력이 %d 증가 됩니다! MP가 %d 회복됩니다! 현재 방어력 : %d\n", target.getName(), LVUP_DEF_PLUS , LVUP_MP_HEAL, target.getDefense());

            if (target.getChampionType().equals(ChampionType.TANK)) {
                target.setAttackDamage(target.getAttackDamage() + ChampionType.TANK.getMerit());
                System.out.printf("< %s > 의 탱커 특성으로 방어력이 %d 추가로 증가합니다! 현재 방어력 : %d\n", target.getName(), ChampionType.TANK.getMerit(), target.getDefense());
            }
        }
    };

    public final int percentage;

    LevelUpBenefit(int percentage) {
        this.percentage = percentage;
    }

    public static LevelUpBenefit getBenefit() {
        int randomPercent = new Random().nextInt(100);
        int percent = 0;

        for(LevelUpBenefit levelUpBenefit : LevelUpBenefit.values()) {
            percent += levelUpBenefit.percentage;

            if(randomPercent < percent)
                return levelUpBenefit;
        }

        return DEF_UP;
    }

    public abstract void getBenefit(Champion target);
}
