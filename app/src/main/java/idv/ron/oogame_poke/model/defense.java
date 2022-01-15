package idv.ron.oogame_poke.model;
import idv.ron.oogame_poke.model.skill.Move;

public class defense extends Pokemon {
    private int Defense;

    /*** 建立巨龜級男人
     * @param image       圖片
     * @param name        名稱
     * @param level       等級
     * @param stamina     耐力
     * @param attack      攻擊力
     * @param defense     防禦力
     * @param catchChance 捕捉率
     * @param fastMove    基本技能
     * @param chargeMove  特別技能*/
    
    public defense(int image, String name, int level, int stamina, int attack, int defense, int catchChance, Move fastMove, Move chargeMove) {
        super(image, name, level, stamina, attack, defense, catchChance, fastMove, chargeMove);
        this.Defense = 50;
    }

    /*** 取得總防禦
     ** @return 回傳本身防禦+巨殼防禦*/
    @Override
    public int getDefense() {
        return super.getDefense() + Defense;
    }
}
