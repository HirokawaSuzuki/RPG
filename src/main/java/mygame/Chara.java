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
    public void speak(String text, int num){
        System.out.println(this.name + ":" + text + num);
        Chara.waitForEnter();
    }
    public void speak(String text, int num1, int num2){
        System.out.println(this.name + ":" + text + num1 + "->" + num2);
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
        int damage = this.atk - target.def;
        if (damage < 0) {
            damage = 0; // 防御力が攻撃力を上回る場合、ダメージは0
        }
        speak(this.name + "のこうげき！" + target.name + "に" + damage + "のダメ-ジ！");
        target.speak(target.name + "のHP" , target.hp, target.hp-damage);
        target.hp -= damage;   
        if (target.hp <= 0) {
            target.hp = 0; // HPがマイナスにならないようにする
            target.speak(target.name + "はたおれた！");
            return 0;
        }
        return target.hp; // 攻撃後のHPを返す
        
    }
}
