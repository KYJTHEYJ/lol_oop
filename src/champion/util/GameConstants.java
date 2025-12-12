package champion.util;

public final class GameConstants {

    // region 숫자 관련 상수들
    // region 공통 관련
    public static int battleCount = 0;

    // 만약 battleCount의 static 없이 클래스의 인스턴스 필드로 사용할 경우
    // 컨텍스트가 달라질 때마다 클래스의 객체를 선언해 battleCount 를 사용해야 하는데
    // 이럴 경우 각자의 객체를 가지는 것이므로 전체 공유가 불가능
    //public int battleCount = 0;

    public static final int criticalDamageMultiple = 2;

    // 서로 참조시 우선 컴파일 에러 발생
    // Cannot read value of field 'wrongBasicAttackActPercent2' before the field's definition
    // 초기화 전에 변수를 사용했다는 문제가 발생함
    // public static double wrongBasicAttackActPercent1 = wrongBasicAttackActPercent2;
    // public static double wrongBasicAttackActPercent2 = wrongBasicAttackActPercent1;

    // 메서드 테스트를 해보면 컴파일러도 체크 불가, 이대로 테스트를 Main 같은 static 메서드내에서 진행하면
    // wrongBasicAttacActPercent1 은 0.0 출력됨
    // public static double wrongBasicAttackActPercent1 = getwrongBasicAttack2();
    // public static double wrongBasicAttackActPercent2 = 0.2;
    // public static double getwrongBasicAttack2() {
    //     return wrongBasicAttackActPercent2;
    // }

    public static final int resurrectionBuffAddAtt = 100;
    public static final int resurrectionBuffAddDef = 20;
    public static final int resurrectionBuffHealHp = 300;
    public static final int resurrectionBuffSionHealHp = 100;

    // endregion

    // region 공통 확률 관련
    public static final double QSkillActPercent = 0.2;
    public static final double basicAttackActPercent = 0.5;
    public static final double specialSkillActPercent = 0.3;
    public static final double commonResurrectHpPercent = 0.5;
    public static final double initCriticalPercent = 0.05;
    public static final double kitePercent = 0.3;
    public static final double doubleSkillPercent = 0.5;
    public static final double resurrectionSkillPercentLess = 0.3;
    public static final double resurrectionSkillPercentMajor = 0.4;
    // endregion

    //region 챔피언 초기 HP 설정 값
    public static final int garenInitHp = 655;
    public static final int asheInitHp = 575;
    public static final int sionInitHp = 750;
    public static final int veigarInitHp = 550;
    //endregion

    //region 챔피언 초기 공격력 설정 값
    public static final int garenInitAttack = 65;
    public static final int asheInitAttack = 58;
    public static final int sionInitAttack = 58;
    public static final int veigarInitAttack = 55;
    //endregion

    //region 챔피언 초기 방어력 설정 값
    public static final int garenInitDefense = 10;
    public static final int asheInitDefense = 8;
    public static final int sionInitDefense = 15;
    public static final int veigarInitDefense = 9;
    //endregion

    //region 챔피언 스킬 관련
    public static final int garenQSkillDamage = 70;
    public static final int garenDashAddAtt = 15;
    public static final int asheAfterQSkillPlusAttPoint = 3;
    public static final int asheAfterQSkillAttTime = 3;
    public static final double sionQSkillPercent1 = 0.4;
    public static final double sionQSkillPercent2 = 0.3;
    public static final double sionQSkillPercent3 = 0.3;
    public static final double sionQSkillDamageMultiple1 = 1.5;
    public static final double sionQSkillDamageMultiple2 = 2;
    public static final double sionQSkillDamageMultiple3 = 2.5;
    public static final double sionSpecialSkillRandomPercent = 0.5;
    public static final int sionGloriousDeathResurrectHP = 200;
    public static final int sionGloriousDeathAddAttPoint = 100;
    public static final int sionDashAddAtt = 5;
    public static final int sionDefenceUpDef = 8;
    public static final int veigarQSkillDamge = 150;
    public static final int veigarQSkillAfterAddQDamage = 20;
    //endregion
    //endregion

    //region 이름 관련자
    //region 챔피언 이름 관련
    public static final String garenNameEN = "Garen";
    public static final String asheNameEN = "Ashe";
    public static final String sionNameEN = "Sion";
    public static final String veigarNameEN = "Veigar";
    //endregion
    //endregion
    
    
}
