import champion.Ashe;
import champion.Sion;
import champion.Veigar;
import champion.specification.champion.Champion;
import champion.Garen;
import champion.specification.resurrection.CommonResurrection;
import champion.util.BattleUtil;
import champion.util.GameConstants;

import java.util.*;

import static champion.util.GameConstants.*;

public class Main {
    public static void main(String[] args) {
        Champion garen = new Garen(
                GameConstants.garenNameEN,
                GameConstants.garenInitHp,
                GameConstants.garenInitAttack,
                GameConstants.garenInitDefense
        );
        Champion ashe = new Ashe(
                GameConstants.asheNameEN,
                GameConstants.asheInitHp,
                GameConstants.asheInitAttack,
                GameConstants.asheInitDefense
        );
        Champion sion = new Sion(
                GameConstants.sionNameEN,
                GameConstants.sionInitHp,
                GameConstants.sionInitAttack,
                GameConstants.sionInitDefense
        );
        Champion veigar = new Veigar(
                GameConstants.veigarNameEN,
                GameConstants.veigarInitHp,
                GameConstants.veigarInitAttack,
                GameConstants.veigarInitDefense
        );

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
            if(Math.random() <= QSkillActPercent) {
                if(championList.get(randomIndex1).useQWithBattleCount(championList.get(randomIndex2))) break;
                if(championList.get(randomIndex2).useQWithBattleCount(championList.get(randomIndex1))) break;
                System.out.println("현재 체력 우세 : " + BattleUtil.pickHigherHp(championList.get(randomIndex1), championList.get(randomIndex2)));
            } else if(Math.random() <= specialSkillActPercent) {
                championList.get(randomIndex1).specialSkillWithBattleCount();
                championList.get(randomIndex2).specialSkillWithBattleCount();
                System.out.println("현재 체력 우세 : " + BattleUtil.pickHigherHp(championList.get(randomIndex1), championList.get(randomIndex2)));
            } else if(Math.random() <= basicAttackActPercent){
                if(championList.get(randomIndex1).basicAttackChampion(championList.get(randomIndex2))) break;
                if(championList.get(randomIndex2).basicAttackChampion(championList.get(randomIndex1))) break;
                System.out.println("현재 체력 우세 : " + BattleUtil.pickHigherHp(championList.get(randomIndex1), championList.get(randomIndex2)));
            }
        } while(!championList.get(randomIndex1).checkHp() && !championList.get(randomIndex2).checkHp());

        System.out.println("=== 전투 종료 ===");
        System.out.println("=== 전투 결과 ===");
        BattleChapions.forEach(champion -> System.out.println(champion));
        System.out.println("총 전투 횟수 : " + GameConstants.battleCount);
        for(Champion champion : BattleChapions) {
            if(!champion.checkHp()) {
                System.out.println(champion.getName() + " 승리!");
            }
        }
    }
}
