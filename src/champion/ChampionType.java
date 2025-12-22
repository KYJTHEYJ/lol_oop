package champion;

import static system.util.GameConstants.*;

// 챔피언 타입 별 enum
// 전사 -> 레벨업 시 공격력 증가 옵션 발동시 추가 증가
// 마법사 -> 스킬 데미지 증가 (* 레벨)
// 탱커 -> 레벨업 시 방어력 증가 옵션 발동시 추가 증가
// 서포터 -> 체력 회복 스킬 발동에 추가 증가
public enum ChampionType {
    WARRIOR(WARRIOR_ATK_PLUS)
    ,MAGE(MAGE_SKILL_DAMAGE_PLUS)
    ,TANK(TANK_DEF_PLUS)
    ,SUPPORT(SUP_HEAL_PLUS);

    public final int merit;

    ChampionType(int merit) {
        this.merit = merit;
    }

    public int getMerit() {
        return merit;
    }
}
