package battle;

import champion.specification.champion.Champion;

import java.util.ArrayList;
import java.util.List;

// Team<T> 로 제네릭 타입을 선언시
// 컴파일 단계에서 대부분 타입 혼란으로 후속 코드와 호환이 되지 않지만
// 어떻게든 컴파일 단계를 코드 수정으로 해결 했더라도
// 타입 안정성을 보장해주지 않는 문제 발생 (불공변 유지 안됨)
// 예를 들어 Team<Object> 였다고 Integer 데이터를 받아 String 처럼 사용할 수 없듯이
// 강제 형 변환으로 컴파일러를 넘어가도.. Integer가 Champion 타입으로 형 변환 문제 발생할 것임

//public class Team<T> {
 public class Team<T extends Champion> {
    private final String teamName;
    private final List<T> championList = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<T> getTeamChampionList() {
        return championList;
    }

    public void addTeam(T champion) {
        System.out.printf("%s 팀에 %s 이(가) 합류합니다\n", teamName, champion.getName());
        championList.add(champion);
    }

    // 제네릭 와일드 카드 Producer Extends 테스트
    public static void printTeamMembers(List<? extends Champion> ChampionList) {
        ChampionList.forEach(champion -> {
            System.out.print(champion.toString() + "\n");
        });
    }
}
