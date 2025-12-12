package champion.util;

import champion.specification.champion.Champion;

import java.util.ArrayList;
import java.util.List;

public class BattleUtil {
    // 챔피언 2개를 입력받아 더 체력이 높은 챔피언을 반환하기
    // 두 챔피언 중 더 체력이 높은 챔피언의 이름을 반환
    public static String pickHigherHp(Champion a, Champion b) {
        if (a.getHp() >= b.getHp()) {
            return a.getName();
        }
        return b.getName();
    }

    public static class Log {
        static List<String> logList = new ArrayList<>();

        public static void print(String log) {
            logList.add(log);
            System.out.println(log);
        }

        public static void showAllLog() {
            System.out.println("\n < 전투 로그를 출력합니다 > \n");
            logList.forEach(log -> System.out.println(log));
        }
    }
}
