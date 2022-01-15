package idv.ron.oogame_poke.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import idv.ron.oogame_poke.R;
import idv.ron.oogame_poke.model.action.Fight;
import idv.ron.oogame_poke.model.skill.Move;

/**
 * 寶可夢精靈，參看寶可夢Go全圖鑑（http://www.otaku-hk.com/pkmgo/en/pokedex）
 */
public class Pokemon implements Fight, Serializable {
    private static List<Pokemon> myMan = new ArrayList<>();

    // 圖片
    private int image;
    // 名稱
    private String name;
    // 等級
    private int level;
    // 耐力
    private int stamina;
    // 血
    private int population;
    // 攻擊力
    private int attack;
    // 防禦力
    private int defense;
    // 捕捉率
    private int catchChance;
    // 基本技能
    private Move fastMove;
    // 特別技能
    private Move chargeMove;

    /**
     * 建立寶可夢精靈
     *
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
    public Pokemon(int image, String name, int level, int stamina, int attack, int defense, int catchChance, Move fastMove, Move chargeMove) {
        this.image = image;
        this.name = name;
        this.level = level;
        this.stamina = stamina;
        // 血 = 等級 x 耐力
        this.population = getFullPopulation();
        this.attack = attack;
        this.defense = defense;
        this.catchChance = catchChance;
        this.fastMove = fastMove;
        this.chargeMove = chargeMove;
    }

    @Override
    public int attack(Pokemon enemy, Move move) {
        // 總傷害 = 攻擊力 + 技能傷害
        int totalDamage = getAttack() + move.getPower();
        // 敵人結果傷害 = 總傷害 - 敵人防禦，結果傷害為負值則改為0
        int resultDamage = totalDamage - enemy.getDefense();
        resultDamage = resultDamage >= 0 ? resultDamage : 0;

        // 敵人依照結果傷害計算損失的HP，HP為負值則改為0
        int population = enemy.getPopulation() - resultDamage;
        enemy.setPopulation(population > 0 ? population : 0);
        return resultDamage;
    }

    @Override
    public String attackResult(Pokemon enemy, Move move) {
        double resultDamage = this.attack(enemy, move);
        String text = String.format(
                "[%s][%s]攻擊[%s]造成[%s]傷害, [%3$s]人氣剩下[%s]",
                this.getName(), move.getName(), enemy.getName(), resultDamage, enemy.getPopulation());
        return text;
    }

    /**
     * 取得野生寶可夢
     * @return 回傳野生寶可夢
     */
    public static List<Pokemon> getFieldPokemons() {
        List<Pokemon> pokemons = new ArrayList<>();
        Pokemon kangpeach = new Pokemon(R.drawable.kangpeachwbody,
                "丹尼爾",
                2,
                80,
                83,
                76,
                40,
                new Move("姜式眨眼", 10),
                new Move("薩摩耶微笑", 50)
        );
        Pokemon doujung = new Pokemon(R.drawable.doujungwbody,
                "斗俊",
                2,
                88,
                94,
                112,
                16,
                new Move("斗式壁咚", 10),
                new Move("斗式傲嬌", 45)
        );
        Pokemon zaihsian = new Pokemon(R.drawable.sunkueawbody,
                "聖圭",
                2,
                78,
                116,
                96,
                16,
                new Move("聖圭超瞇眼", 6),
                new Move("聖圭主唱實力", 70)
        );
        Pokemon ong = new Pokemon(R.drawable.fourwbody,
                "四阿哥",
                2,
                104,
                80,
                50,
                10,
                new Move("四阿哥編頭髮", 14),
                new Move("四阿哥變回吳奇隆", 60)
        );

        pokemons.add(kangpeach);
        pokemons.add(doujung);
        pokemons.add(zaihsian);
        pokemons.add(ong);
        return pokemons;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getPopulation() {
        return population;
    }

    public int getFullPopulation() {
        return level * stamina;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCatchChance() {
        return catchChance;
    }

    public void setCatchChance(int catchChance) {
        this.catchChance = catchChance;
    }

    public Move getFastMove() {
        return fastMove;
    }

    public void setFastMove(Move fastMove) {
        this.fastMove = fastMove;
    }

    public Move getChargeMove() {
        return chargeMove;
    }

    public void setChargeMove(Move chargeMove) {
        this.chargeMove = chargeMove;
    }


    public static List<Pokemon> getMyPokemons() {
        return myMan;
    }


    public static void addMan(Pokemon pokemon) {
        myMan.add(pokemon);
    }

}
