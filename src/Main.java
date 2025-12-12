import champion.Sion;
import champion.specification.champion.Champion;
import champion.specification.resurrection.CommonResurrection;
import champion.specification.resurrection.SionGloriousDeath;
import champion.util.BattleUtil;
import champion.util.ChampionEnum;

import java.util.*;

import static champion.util.ChampionEnum.*;
import static champion.util.GameConstants.*;

public class Main {
    public static void main(String[] args) {
        Champion garen = Garen.getChampion();
        Champion ashe = Ashe.getChampion();
        Champion sion = Sion.getChampion();
        Champion veigar = Veigar.getChampion();

        // 전략 패턴에 대한 확장 수정
        // 기존엔 챔피언 생성자에서 setter 를 통해 선언했으나
        // Main 에서 사용할 수 있게 확장하면.. 추후에 게임 실행 과정에서 선택할 수 있게..
        garen.setResurrection(garen.createResurrection());
        ashe.setResurrection(ashe.createResurrection());
        sion.setResurrection(sion.createResurrection());
        veigar.setResurrection(veigar.createResurrection());

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
        BattleUtil.Log.print("==== 소환사의 협곡에 오신 것을 환영합니다. ====");
        BattleUtil.Log.print("=== 전투 시작 ===");

        // static 참조 오류 발생 시켜보기
        // System.out.println("error 1 : " + wrongBasicAttackActPercent1);
        // System.out.println("error 2 : " + wrongBasicAttackActPercent2);

        do {
            if(Math.random() <= QSkillActPercent) {
                if(championList.get(randomIndex1).useQWithBattleCount(championList.get(randomIndex2))) break;
                if(championList.get(randomIndex2).useQWithBattleCount(championList.get(randomIndex1))) break;
                BattleUtil.Log.print("현재 체력 우세 : " + BattleUtil.pickHigherHp(championList.get(randomIndex1), championList.get(randomIndex2)));
            } else if(Math.random() <= specialSkillActPercent) {
                championList.get(randomIndex1).specialSkillWithBattleCount();
                championList.get(randomIndex2).specialSkillWithBattleCount();
                BattleUtil.Log.print("현재 체력 우세 : " + BattleUtil.pickHigherHp(championList.get(randomIndex1), championList.get(randomIndex2)));
            } else if(Math.random() <= basicAttackActPercent){
                if(championList.get(randomIndex1).basicAttackChampion(championList.get(randomIndex2))) break;
                if(championList.get(randomIndex2).basicAttackChampion(championList.get(randomIndex1))) break;
                BattleUtil.Log.print("현재 체력 우세 : " + BattleUtil.pickHigherHp(championList.get(randomIndex1), championList.get(randomIndex2)));
            }
        } while(!championList.get(randomIndex1).checkHp() && !championList.get(randomIndex2).checkHp());

        BattleUtil.Log.print("=== 전투 종료 ===");
        BattleUtil.Log.print("=== 전투 결과 ===");
        BattleChapions.forEach(champion -> BattleUtil.Log.print(champion.toString()));
        //BattleUtil.Log.print("총 전투 횟수 : " + GameConstants.battleCount);
        for(Champion champion : BattleChapions) {
            if(!champion.checkHp()) {
                BattleUtil.Log.print(champion.getName() + " 승리!");
            }
        }

        System.out.print("\n로그를 다시 보시려면 y 를 입력하시고 종료하려면 다른 키워드를 입력해주세요 : ");
        String logShowYn = new Scanner(System.in).nextLine();

        if(logShowYn.equals("y".toUpperCase()) || logShowYn.equals("y")) {
            BattleUtil.Log.showAllLog();
        }
    }
}
