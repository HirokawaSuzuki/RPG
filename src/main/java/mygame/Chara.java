package mygame;

import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Chara {
    public String name;
    public int hp;
    public int atk;
    public int def;
    public String faction;
    public int b_def;
    public String next = null; // 戦闘後の遷移先ID
    public Random rand = new Random();

    // ユーザー入力を待つためのメソッド
    // これを使うことで、speakメソッドの後にEnterキーを押すまでプログラムが進まないようにする
    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        // scannerはここで閉じない（System.inが閉じられるため）
    }

    // コンストラクタ
    public Chara(String name, int hp, int atk, int def, String faction) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.b_def = 0;
        this.faction = faction;
    }

    // speakメソッド
    public void speak(String text) {
        System.out.println(this.name + "\n「" + text + "」");
    }

    // 敵キャラからターゲットを選ぶ（味方の場合）
    public Chara chooseTarget(List<Chara> enemies) {
        if (enemies.isEmpty())
            return null;
        // ここではシンプルにランダムで1体選ぶ例
        return enemies.get((int) (Math.random() * enemies.size()));
    }

    // 味方キャラからターゲットを選ぶ（敵の場合）
    public Chara chooseAllyTarget(List<Chara> allies) {
        if (allies.isEmpty())
            return null;
        return allies.get((int) (Math.random() * allies.size()));
    }

    public int attack(Chara target) {
        // 攻撃する関数を作成\
        int atkdice = this.atk + rand.nextInt(6);
        int damage = atkdice - (target.def + target.b_def);
        if (damage < 0)
            damage = 0; // ダメージがマイナスにならないようにする
        target.hp -= damage;
        String attackText = this.name + "の攻撃力:" + atkdice + "\n" + target.name + "の防御力:" + (target.def + target.b_def)
                + "\n" + damage + "のダメージ！\n" + target.name + "の残りHPは" + target.hp;
        System.out.println(attackText);
        if (this.b_def > 0) {
            this.speak(this.name + "の防御" + (this.b_def + this.def) + " -> " + (this.def + this.b_def - 1));
            this.b_def--; // 防御状態を解除
        }
        return 0;
    }

    public void defence() {
        this.b_def = this.def;
        String defenceText = this.name + "は防御態勢をとった！\n" +
                "次の攻撃まで防御力" + this.def + " -> " + (this.def + this.b_def);
        this.speak(defenceText);
    }

    public void escape(){

    }

    public void battleStatus(Chara target) {
        if (this.hp <= 0) {
            speak(this.name + "は倒れた！");
            if (this.faction.equals("ally")) {
                // 味方が倒れた場合の処理
                this.next = "lose001";
            } else {
                // 敵が倒れた場合の処理
                this.next = "win001";
            }
        } else {
            System.out.println(this.name + "HP:" + this.hp + "\n" + target.name + "HP:" + target.hp);
        }
    }
}
