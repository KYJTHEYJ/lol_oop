package system.battle;

import champion.Champion;
import champion.ChampionPool;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static champion.Champion.createdCount;
import static system.util.GameConstants.*;

public class Battle {
    ChampionPool championPool = new ChampionPool();
    Scanner sc = new Scanner(System.in);

    public Champion pickChampion(Champion pickedChampion) {
        do {
            List<String> canPickChampionNameList =
                    pickedChampion != null ? championPool.getChampionsNames().stream().filter(championName -> !championName.equals(pickedChampion.getName())).toList()
                            : championPool.getChampionsNames();

            System.out.printf("\n< 현재 선택 가능한 %d 명의 챔피언 >\n", createdCount);
            canPickChampionNameList.forEach(championName -> System.out.println(championName));
            System.out.print("전투할 챔피언의 이름을 입력해주세요 (무작위를 원하면 ? 를 입력해주세요) : ");
            String championName = sc.nextLine();
            Champion pickChampion;

            if (championName.equals("?")) {
                pickChampion = championPool.getRandomChampion();
                System.out.println("\n< " + pickChampion.getName() + " > 이 선택 되었습니다!");
                System.out.println("챔피언 타입 : " + pickChampion.getChampionType());
                System.out.println("HP : " + pickChampion.getMaxHp());
                System.out.println("MP / 최대 MP : " + pickChampion.getMp() + " / " + pickChampion.getMaxMp());
                System.out.println("방어력 : " + pickChampion.getDefense());
                return championPool.getRandomChampion();
            }

            if (!canPickChampionNameList.contains(championName) && championPool.getChampion(championName).isEmpty()) {
                System.out.println("없는 챔피언 이름을 입력하셨습니다");
                continue;
            }

            pickChampion = championPool.getChampion(championName).get();
            System.out.println("\n< " + pickChampion.getName() + " > 이 선택 되었습니다!");
            System.out.println("챔피언 타입 : " + pickChampion.getChampionType());
            System.out.println("HP : " + pickChampion.getMaxHp());
            System.out.println("MP / 최대 MP : " + pickChampion.getMp() + " / " + pickChampion.getMaxMp());
            System.out.println("방어력 : " + pickChampion.getDefense());
            return pickChampion;
        }
        while(true);
    }

    public void battleAct(Champion champion, Champion target) {
        int[] actPercentArray = {basicAttackPercent, useQSkillPercent, useWSkillPercent, useESkillPercent, useRSkillPercent, levelUpPercent};
        int randomActPercent = new Random().nextInt(100);
        int actPercent = 0;

        for(int i = 0; true; i++) {
            actPercent += actPercentArray[i];

            if (randomActPercent < actPercent) {
                switch (i) {
                    case 0:
                        champion.basicAttack(target);
                        return;
                    case 1:
                        champion.useQSkill(target);
                        return;
                    case 2:
                        champion.useWSkill(target);
                        return;
                    case 3:
                        champion.useESkill(target);
                        return;
                    case 4:
                        champion.useRSkill(target);
                        return;
                    case 5:
                        champion.levelUp();
                        return;
                }
            }
        }
    }

    public void oneVsOne() {
        Champion champion1 = pickChampion(null);
        Champion champion2 = pickChampion(champion1);

        if(champion1.equals(champion2)) {
            champion2 = championPool.getRandomChampion();
        }

        System.out.println("\n[ 소환사의 협곡 - 1대1 전투를 시작합니다 ]");

        int turn = 1;

        do {
            System.out.printf("\n[ %d 턴 ]\n", turn);
            battleAct(champion1, champion2);
            battleAct(champion2, champion1);
            turn++;
        } while(champion1.getHp() > 0 && champion2.getHp() > 0);

    }
}
