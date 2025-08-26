package mygame;
import java.util.Scanner;

public class Chara {
    public String name;
    public int hp;
    public int atk;
    public int def;
    public String text;
    public int num;

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
    }

    // speakメソッド
    public void speak(String text){
        System.out.println(this.name + ":" + text);
        Chara.waitForEnter();
    }

    // 各パラメータを取得するためのゲッター
    public String getName() {
        return this.name;
    }
    public int getHP() {
        return this.hp;
    }
    public int getAtk() {
        return this.atk;
    }
    public int getDef() {
        return this.def;
    }

    public int attack(Chara target){
        // 攻撃する関数を作成\
        int damage = this.atk - target.def;
        target.hp -= damage;
        String attackText = this.name + "の攻撃！" + target.name + "に" + damage + "のダメージ！";
        this.speak(attackText);
        target.alve();
        return 0;
    }

    public void alve(){
        if(this.hp <= 0){
            speak(this.name + "は倒れた！");
        } else {
            System.out.println(this.name + "の残りHPは" + this.hp + "だ！");
        }
    }
}
