package champion;

import champion.specification.champion.Champion;
import champion.specification.champion.Mage;
import champion.specification.resurrection.CommonResurrection;
import champion.util.BattleUtil;

import static champion.util.GameConstants.*;

public class Veigar extends Champion implements Mage {
    // Q 스킬을 사용할 때마다 강해짐
    private int QStack = 0;
    // Mage용 특성 스택
    private int doubleSkillStack = 0;

    public Veigar(String name, int hp, int attackPoint, int defensePoint) {
        super(name, hp, attackPoint, defensePoint);
    }

    @Override
    public boolean useQ(Champion target) {
        // 더블 스킬에 따른 스킬 발동 카운트
        int skillCount = 1;

        if(doubleSkillStack > 0 && Math.random() <= doubleSkillPercent) {
            BattleUtil.Log.print(getName() + "의 더블 스킬 사용! 남은 더블 스킬 : " + doubleSkillStack);
            skillCount = 2;

            if(doubleSkillStack > 0) {
                doubleSkillStack--;
            }
        }

        for (int index = 0; index < skillCount; index++) {
            BattleUtil.Log.print(String.format(getName() + " 이(가) 사악한 일격을 사용합니다! %d의 데미지를 가합니다! 사용한 후마다 %d씩 강해집니다!"
                    , veigarQSkillDamge + veigarQSkillAfterAddQDamage * QStack, veigarQSkillAfterAddQDamage));
            BattleUtil.Log.print(getName() + " -> " + target.getName() + "에게 사악한 일격!");

            target.takeDamage(veigarQSkillDamge + veigarQSkillAfterAddQDamage * QStack);

            QStack++;
        }

        return target.checkHp();
    }

    @Override
    public void specialSkill() {
        doubleSkill();
    }

    @Override
    public void doubleSkill() {
        doubleSkillStack++;
        BattleUtil.Log.print(getName() + " 의 더블 스킬! 1번당 " + (int) (doubleSkillPercent * 100)
                             + "% 확률로 스킬을 두번 사용합니다! 남은 더블 스킬 : " + doubleSkillStack);
    }

    @Override
    protected void actAddBuffResurrect() {
        if(Math.random() <= resurrectionSkillPercentMajor) {
            int resultHp = getHp() + resurrectionBuffHealHp;
            setHp(Math.min(resultHp, getMaxHp()));
            BattleUtil.Log.print(getName() + " 이(가) 부활 버프로 HP를 추가로 " + resurrectionBuffHealHp + " 회복합니다! (현재 HP : " + getHp() + ")");
        } else if(Math.random() <= resurrectionSkillPercentLess) {
            setAttackPoint(getAttackPoint() + resurrectionBuffAddDef);
            BattleUtil.Log.print(getName() + " 이(가) 부활 버프로 " + resurrectionBuffAddDef + " 방어력을 얻습니다! (현재 방어력 : " + getDefensePoint() + ")");
        } else if(Math.random() <= resurrectionSkillPercentLess) {
            setAttackPoint(getAttackPoint() + resurrectionBuffAddAtt);
            BattleUtil.Log.print(getName() + " 이(가) 부활 버프로 " + resurrectionBuffAddAtt + " 공격력을 얻습니다! (현재 공격력 : " + getAttackPoint() + ")");
        }
    }
}
