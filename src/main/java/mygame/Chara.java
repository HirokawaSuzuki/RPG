package mygame;

import java.util.List;
import java.util.Scanner;

public class Chara {
    public String name;
    public int hp;
    public int atk;
    public int def;
    public String text;
    public int num;
    public int b_def;

    // ユーザー入力を待つためのメソッド
    // これを使うことで、speakメソッドの後にEnterキーを押すまでプログラムが進まないようにする
    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        // scannerはここで閉じない（System.inが閉じられるため）
    }

    // コンストラクタ
    public Chara(String name, int hp, int atk, int def) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.b_def = 0;
    }

    // speakメソッド
    public void speak(String text) {
        System.out.println(this.name + ":" + text);
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
        int damage = this.atk - (target.def + target.b_def);
        target.hp -= damage;
        String attackText = this.name + "の攻撃！" + target.name + "に" + damage + "のダメージ！";
        this.speak(attackText);
        target.b_def = 0; // 防御状態を解除
        target.alve();
        if(b_def != 0)target.speak(target.name + "の防御が解けた！");
        return 0;
    }

    public void defence() {
        this.b_def = this.def;
        String defenceText = this.name + "は防御態勢をとった！\n" +
                "次の攻撃まで防御力" + this.def + " -> " + (this.def + this.b_def);
        this.speak(defenceText);
    }

    public void alve() {
        if (this.hp <= 0) {
            speak(this.name + "は倒れた！");
        } else {
            System.out.println(this.name + "の残りHPは" + this.hp + "だ！");
        }
    }
}
