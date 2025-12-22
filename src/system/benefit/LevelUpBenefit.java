package system.benefit;

import champion.Champion;

import java.util.Random;

public enum LevelUpBenefit {
    ALL_RESTORE(30) {
        @Override
        public void getBenefit(Champion target) {
            System.out.printf("< %s > 레벨 업! Lv.%d -> Lv.%d\n", target.getName(), target.getLevel(), target.getLevel() + 1);
            System.out.printf("< %s > 레벨 업하여 HP와 MP가 전부 회복됩니다!\n", target.getName());
            target.setHp(target.getMaxHp());
            target.setMp(target.getMaxMp());
        }
    }
    , ATK_UP(40) {
        @Override
        public void getBenefit(Champion target) {
            System.out.printf("< %s > 레벨 업! Lv.%d -> Lv.%d\n", target.getName(), target.getLevel(), target.getLevel() + 1);
            System.out.printf("< %s > 레벨 업하여 공격력이 2배가 됩니다! MP가 전부 회복됩니다!\n", target.getName());
            target.setAttackDamage(target.getAttackDamage() * 2);
            target.setMp(target.getMaxMp());
        }
    }
    , DEF_UP(40) {
        @Override
        public void getBenefit(Champion target) {
            System.out.printf("< %s > 레벨 업! Lv.%d -> Lv.%d\n", target.getName(), target.getLevel(), target.getLevel() + 1);
            System.out.printf("< %s > 레벨 업하여 공격력이 2배가 됩니다! MP가 전부 회복됩니다!\n", target.getName());
            target.setDefense(target.getDefense() * 2);
            target.setMp(target.getMaxMp());
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
