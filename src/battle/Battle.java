package battle;

import champion.specification.champion.Champion;
import champion.util.BattleUtil;

import java.util.*;

import static champion.util.GameConstants.*;

public class Battle {

    public static void oneVsOne(Team<Champion> t1, Team<Champion> t2) {
        Random rand = new Random();
        Champion t1RandomChampion = t1.getTeamChampionList().get(rand.nextInt(t1.getTeamChampionList().size()));
        Champion t2RandomChampion = t2.getTeamChampionList().get(rand.nextInt(t2.getTeamChampionList().size()));

        // 전투하는 챔피언들
        HashMap<Champion, String> BattleChapions = new HashMap<>();
        BattleChapions.put(t1RandomChampion, t1.getTeamName());
        BattleChapions.put(t2RandomChampion, t2.getTeamName());

        // 전투는 확률 별 행위를 진행하고 HP 0 이하가 한 쪽의 승리로 결정
        BattleUtil.Log.print("==== " + t1.getTeamName() + " Team Champion : " + t1RandomChampion.getName() + " ====");
        BattleUtil.Log.print("==== " + t2.getTeamName() + " Team Champion : " + t2RandomChampion.getName() + " ====");
        BattleUtil.Log.print("==== 소환사의 협곡에 오신 것을 환영합니다. ====");
        BattleUtil.Log.print("=== 전투 시작 ===");

        do {
            if(Math.random() <= QSkillActPercent) {
                if(t1RandomChampion.useQWithBattleCount(t2RandomChampion)) break;
                if(t2RandomChampion.useQWithBattleCount(t1RandomChampion)) break;
                BattleUtil.Log.print("현재 체력 우세 : " + BattleUtil.pickHigherHp(t1RandomChampion, t2RandomChampion));
            } else if(Math.random() <= specialSkillActPercent) {
                t1RandomChampion.specialSkillWithBattleCount();
                t2RandomChampion.specialSkillWithBattleCount();
                BattleUtil.Log.print("현재 체력 우세 : " + BattleUtil.pickHigherHp(t1RandomChampion, t2RandomChampion));
            } else if(Math.random() <= basicAttackActPercent){
                if(t1RandomChampion.basicAttackChampion(t2RandomChampion)) break;
                if(t2RandomChampion.basicAttackChampion(t1RandomChampion)) break;
                BattleUtil.Log.print("현재 체력 우세 : " + BattleUtil.pickHigherHp(t1RandomChampion, t2RandomChampion));
            }
        } while(!t1RandomChampion.checkHp() && !t2RandomChampion.checkHp());

        BattleUtil.Log.print("=== 전투 종료 ===");
        BattleUtil.Log.print("=== 전투 결과 ===");
        BattleChapions.keySet().forEach(champion -> BattleUtil.Log.print(champion.toString()));

        for(Champion champion : BattleChapions.keySet()) {
            if(!champion.checkHp()) {
                BattleUtil.Log.print(BattleChapions.get(champion) + "Team / " + champion.getName() + " 승리!");
            }
        }

        System.out.print("\n로그를 다시 보시려면 y 를 입력하시고 종료하려면 다른 키워드를 입력해주세요 : ");
        String logShowYn = new Scanner(System.in).nextLine();

        if(logShowYn.equals("y".toUpperCase()) || logShowYn.equals("y")) {
            BattleUtil.Log.showAllLog();
        }
    }
}
