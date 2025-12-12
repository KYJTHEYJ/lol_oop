package champion.util;

import champion.specification.champion.Champion;

public class BattleUtil {
    // 챔피언 2개를 입력받아 더 체력이 높은 챔피언을 반환하기
    // 두 챔피언 중 더 체력이 높은 챔피언의 이름을 반환
    public static String pickHigherHp(Champion a, Champion b) {
        if (a.getHp() >= b.getHp()) {
            return a.getName();
        }
        return b.getName();
    }
}
