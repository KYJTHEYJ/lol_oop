package system.battle;

import champion.Champion;
import champion.ChampionPool;
import system.exceptions.DeathException;
import system.exceptions.MinusHpException;

import java.util.Arrays;
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
                return pickChampionInfo(pickChampion);
            }

            if (!canPickChampionNameList.contains(championName) && championPool.getChampion(championName).isEmpty()) {
                System.out.println("없는 챔피언 이름을 입력하셨습니다");
                continue;
            }

            pickChampion = championPool.getChampion(championName).get();
            return pickChampionInfo(pickChampion);
        }
        while(true);
    }

    private Champion pickChampionInfo(Champion pickChampion) {
        System.out.println("\n< " + pickChampion.getName() + " > 이 선택 되었습니다!");
        System.out.println("챔피언 타입 : " + pickChampion.getChampionType());
        System.out.println("HP : " + pickChampion.getMaxHp());
        System.out.println("MP / 최대 MP : " + pickChampion.getMp() + " / " + pickChampion.getMaxMp());
        System.out.println("공격력 : " + pickChampion.getAttackDamage());
        System.out.println("방어력 : " + pickChampion.getDefense());
        return pickChampion;
    }

    public void battleAct(Champion champion, Champion target) {
        int[] actPercentArray = {basicAttackPercent, useQSkillPercent, useWSkillPercent, useESkillPercent, useRSkillPercent, levelUpPercent};
        int allPercent = Arrays.stream(actPercentArray).sum();
        int randomActPercent = new Random().nextInt(allPercent);
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

    private Champion winner(Champion champion1, Champion champion2) {
        if(champion1.getHp() > champion2.getHp()) {
            return champion1;
        }

        return champion2;
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
            try {
                battleAct(champion1, champion2);
                battleAct(champion2, champion1);
            } catch (DeathException | MinusHpException e) {
                break;
            }
            turn++;
        } while(champion1.getHp() > 0 && champion2.getHp() > 0);

        System.out.println("====== 전투 결과 ======");
        System.out.println("< " + winner(champion1, champion2).getName() + " > 승리!");
    }
}
