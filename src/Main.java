import battle.Battle;
import battle.Team;
import champion.specification.champion.Champion;

import static champion.util.ChampionEnum.*;

public class Main {
    public static void main(String[] args) {
        Champion garen = Garen.getChampion();
        Champion ashe = Ashe.getChampion();
        Champion sion = Sion.getChampion();
        Champion veigar = Veigar.getChampion();

        // 전략 패턴에 대한 확장 수정
        // 기존엔 챔피언 생성자에서 setter 를 통해 선언했으나
        // Main 에서 사용할 수 있게 확장하면.. 요건이 있었다면 게임 실행 과정에서 선택할 수 있게..
        garen.setResurrection(garen.createResurrection());
        ashe.setResurrection(ashe.createResurrection());
        sion.setResurrection(sion.createResurrection());
        veigar.setResurrection(veigar.createResurrection());

        Team<Champion> redTeam = new Team<>("Red");
        Team<Champion> blueTeam = new Team<>("Blue");

        redTeam.addTeam(garen);
        redTeam.addTeam(ashe);
        blueTeam.addTeam(sion);
        blueTeam.addTeam(veigar);

        /// 제네릭 와일드 카드 Producer Extends 테스트
        /*
        System.out.println("\n======== Wild Card TEST =========\n");
        List<Garen> garens = new ArrayList<>();
        garens.add(new Garen("가렌1", 100, 100, 100));
        garens.add(new Garen("가렌2", 100, 100, 100));

        Team.printTeamMembers(garens);
        Team.printTeamMembers(redTeam.getTeamChampionList());
        Team.printTeamMembers(blueTeam.getTeamChampionList());
        System.out.println("\n======== Wild Card TEST =========\n");
        */

        Battle.oneVsOne(redTeam, blueTeam);
    }
}
