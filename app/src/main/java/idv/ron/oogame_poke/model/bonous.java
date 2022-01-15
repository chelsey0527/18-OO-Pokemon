package idv.ron.oogame_poke.model;
import idv.ron.oogame_poke.model.skill.Move;
/*** 升級成巨龍級增加「巨龍爪擊」技巧*/

public class bonous extends Pokemon {
    private Move scrachMove;

    /**
     * 建立超強男人
     * @param image       圖片
     * @param name        名稱
     * @param level       等級
     * @param stamina     耐力
     * @param attack      攻擊力
     * @param defense     防禦力
     * @param catchChance 捕捉率
     * @param fastMove    基本技能
     * @param chargeMove  特別技能
     */

    public bonous(int image, String name, int level, int stamina, int attack, int defense, int catchChance, Move fastMove, Move chargeMove) {
        super(image, name, level, stamina, attack, defense, catchChance, fastMove, chargeMove);
        setScrachMove(new Move("性感摸大腿舞", 50));
    }

    @Override
    public int attack(Pokemon enemy, Move move) {
        // 總傷害 = 攻擊力 + 技能傷害 + 性感摸大腿舞
        int totalDamage = getAttack() + move.getPower() + getScrachMove().getPower();
        // 敵人結果傷害 = 總傷害 - 敵人防禦，計算結果如果為負值則為0
        int resultDamage = totalDamage - enemy.getDefense();
        resultDamage = resultDamage >= 0 ? resultDamage : 0;

        // 敵人依照結果傷害計算損失的人氣，人氣為負值則改為0
        int population = enemy.getPopulation() - resultDamage;
        enemy.setPopulation(population > 0 ? population : 0);
        return resultDamage;
    }

    @Override
    public String attackResult(Pokemon enemy, Move move) {
        String moveName = move.getName() + ", " + getScrachMove().getName();
        double resultDamage = this.attack(enemy, move);
        String text = String.format(
                "[%s][%s]攻擊[%s]造成[%s]傷害, [%3$s]HP剩下[%s]",
                this.getName(), moveName, enemy.getName(), resultDamage, enemy.getPopulation());
        return text;
    }

    public Move getScrachMove() {
        return scrachMove;
    }

    public void setScrachMove(Move scrachMove) {
        this.scrachMove = scrachMove;
    }
}
