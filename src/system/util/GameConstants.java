package system.util;

public class GameConstants {

    private GameConstants() {}

    //region 스킬 확률 설정
    public static final int basicAttackPercent = 25;
    public static final int useQSkillPercent = 15;
    public static final int useWSkillPercent = 15;
    public static final int useESkillPercent = 15;
    public static final int useRSkillPercent = 10;
    public static final int levelUpPercent = 20;
    //endregion

    //region 레벨 관련 및 스탯 설정
    public final static int MIN_LEVEL = 1;
    public final static int MAX_LEVEL = 3;
    public final static int MP_MAX_VALUE = 5;
    public final static int COMMON_MP_INIT_VALUE = 3;
    public final static int MAGE_SUPPORT_MP_INIT_VALUE = MP_MAX_VALUE;

    public final static int LVUP_MP_HEAL = 3;
    public final static int LVUP_ATK_PLUS = 10;
    public final static int LVUP_DEF_PLUS = 5;
    public final static int WARRIOR_ATK_PLUS = 20;
    public final static int TANK_DEF_PLUS = 10;
    public final static int MAGE_SKILL_DAMAGE_PLUS = 20;
    public final static int SUP_HEAL_PLUS = 20;
    //endregion

    //region 챔피언 설정
    public final static String URGOT_NAME = "우르곳";
    public final static int URGOT_INIT_HP = 655;
    public final static int URGOT_INIT_ATK = 63;
    public final static int URGOT_INIT_DEF = 36;
    public final static int URGOT_INIT_SKILL_Q_DAMAGE = 80;
    public final static double URGOT_INIT_SKILL_Q_COEFFICIENT = 0.7;
    public final static int URGOT_INIT_SKILL_Q_DEFENCE_MINUS = 5;
    public final static double URGOT_INIT_SKILL_W_COEFFICIENT = 0.5;
    public final static int URGOT_INIT_SKILL_W_DAMAGE = 50;
    public final static int URGOT_INIT_SKILL_W_HITS = 6;
    public final static int URGOT_INIT_SKILL_E_DAMAGE = 70;
    public final static double URGOT_INIT_SKILL_E_COEFFICIENT = 1;
    public final static int URGOT_INIT_SKILL_E_HEAL_COEFFICIENT = 3;
    public final static int URGOT_INIT_SKILL_R_DAMAGE = 200;
    public final static double URGOT_INIT_SKILL_R_COEFFICIENT = 0.7;
    public final static double URGOT_INIT_SKILL_R_EXECUTE = 0.4;

    public final static String MALPHITE_NAME = "말파이트";
    public final static int MALPHITE_INIT_HP = 665;
    public final static int MALPHITE_INIT_ATK = 62;
    public final static int MALPHITE_INIT_DEF = 40;
    public final static int MALPHITE_INIT_SKILL_Q_DAMAGE = 70;
    public final static double MALPHITE_INIT_SKILL_Q_COEFFICIENT = 0.6;
    public final static int MALPHITE_INIT_SKILL_W_DAMAGE = 70;
    public final static double MALPHITE_INIT_SKILL_W_COEFFICIENT = 0.45;
    public final static int MALPHITE_INIT_SKILL_E_DAMAGE = 80;
    public final static double MALPHITE_INIT_SKILL_E_COEFFICIENT = 0.6;
    public final static int MALPHITE_INIT_SKILL_E_DEFENCE_MINUS = 3;
    public final static int MALPHITE_INIT_SKILL_R_DAMAGE = 200;
    public final static double MALPHITE_INIT_SKILL_R_COEFFICIENT = 0.9;
    public final static int MALPHITE_INIT_SKILL_R_DEFENCE_MINUS = 10;

    public final static String SYNDRA_NAME = "신드라";
    public final static int SYNDRA_INIT_HP = 523;
    public final static int SYNDRA_INIT_ATK = 54;
    public final static int SYNDRA_INIT_DEF = 30;
    public final static int SYNDRA_INIT_SKILL_Q_DAMAGE = 100;
    public final static double SYNDRA_INIT_SKILL_Q_COEFFICIENT = 0.8;
    public final static int SYNDRA_INIT_SKILL_W_DAMAGE = 80;
    public final static double SYNDRA_INIT_SKILL_W_COEFFICIENT = 0.9;
    public final static int SYNDRA_INIT_SKILL_W_ATK_MINUS = 5;
    public final static int SYNDRA_INIT_SKILL_E_DAMAGE = 90;
    public final static double SYNDRA_INIT_SKILL_E_COEFFICIENT = 0.75;
    public final static int SYNDRA_INIT_SKILL_E_HITS = 2;
    public final static int SYNDRA_INIT_SKILL_R_DAMAGE = 120;
    public final static int SYNDRA_INIT_SKILL_R_HITS = 3;
    public final static double SYNDRA_INIT_SKILL_R_COEFFICIENT = 1.2;

    public final static String SORAKA_NAME = "소라카";
    public final static int SORAKA_INIT_HP = 535;
    public final static int SORAKA_INIT_ATK = 50;
    public final static int SORAKA_INIT_DEF = 32;
    public final static int SORAKA_INIT_SKILL_Q_DAMAGE = 60;
    public final static double SORAKA_INIT_SKILL_Q_COEFFICIENT = 0.5;
    public final static int SORAKA_INIT_SKILL_Q_HEAL = 70;
    public final static int SORAKA_INIT_SKILL_W_HEAL = 100;
    public final static double SORAKA_INIT_SKILL_W_COEFFICIENT = 1.5;
    public final static int SORAKA_INIT_SKILL_E_DAMAGE = 50;
    public final static double SORAKA_INIT_SKILL_E_COEFFICIENT = 0.4;
    public final static int SORAKA_INIT_SKILL_E_DEF_PLUS = 5;
    public final static int SORAKA_INIT_SKILL_R_HEAL = 200;
    public final static double SORAKA_INIT_SKILL_R_COEFFICIENT = 2;
    public final static int SORAKA_INIT_SKILL_R_ATK_DEF_PLUS = 8;
    //endregion
}
