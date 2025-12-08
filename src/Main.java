import champion.Ashe;
import champion.Sion;
import champion.Veigar;
import champion.specification.Champion;
import champion.Garen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Champion garen = new Garen("Garen", 1, 655, 65, 10);
        Champion ashe = new Ashe("Ashe", 1, 575, 58, 8);
        Champion sion = new Sion("Sion", 1, 750, 58, 15);
        Champion veigar = new Veigar("Veigar", 1, 550, 55, 9);

        // 챔피언 목록
        List<Champion> championList = Arrays.asList(garen, ashe, sion, veigar);

        // 랜덤 2명 선택하기
        Random rand = new Random();
        int randomIndex1 = rand.nextInt(championList.size());
        int randomIndex2 = rand.nextInt(championList.size());

        if(randomIndex1 == randomIndex2) {
            do {
                randomIndex2 = rand.nextInt(championList.size());
            } while (randomIndex2 == randomIndex1);
        }

        List<Champion> BattleChapions = new ArrayList<>();
        BattleChapions.add(championList.get(randomIndex1));
        BattleChapions.add(championList.get(randomIndex2));

        // 전투는 확률 별 행위를 진행하고 HP 0 이하가 한 쪽의 승리로 결정
        System.out.println("==== 소환사의 협곡에 오신 것을 환영합니다. ====");
        System.out.println("=== 전투 시작 ===");

        do {
            double act = Math.random();

            if(act <= 0.2) {
                if(championList.get(randomIndex1).useQ(championList.get(randomIndex2))) break;
                if(championList.get(randomIndex2).useQ(championList.get(randomIndex1))) break;
            } else if(act <= 0.3) {
                championList.get(randomIndex1).specialSkill();
                championList.get(randomIndex2).specialSkill();
            } else if(act <= 0.5) {
                if(championList.get(randomIndex1).basicAttackChampion(championList.get(randomIndex2))) break;
                if(championList.get(randomIndex2).basicAttackChampion(championList.get(randomIndex1))) break;
            }
        } while(!championList.get(randomIndex1).checkHp() && !championList.get(randomIndex2).checkHp());

        System.out.println("=== 전투 종료 ===");
        System.out.println("=== 전투 결과 ===");
        BattleChapions.forEach(champion -> System.out.println(champion));
        for(Champion champion : BattleChapions) {

            if(!champion.checkHp()) {
                System.out.println(champion.getName() + " 승리!");
            }
        }

    }
}
