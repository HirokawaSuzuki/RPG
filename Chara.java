import java.util.Scanner;

public class Chara {
    private String name;
    private int hp;
    private int atk;
    private int def;
    public String text;
    public int num;

    
    public static void waitForEnter() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        // scannerはここで閉じない（System.inが閉じられるため）
    }

    public Chara(String name, int hp, int atk, int def) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }

    public void speak(String text){
        System.out.println(this.name + ":" + text);
        Chara.waitForEnter();
    }
    public int getHP() {
        return this.hp;
    }
    public void hp(){
        System.out.println(this.name + "HP:" + this.hp);
    }
    
    public int choice(int num){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.たたかう 2.ぼうぎょ 3.にげる");
        speak("おれは...");
        num = scanner.nextInt();
        switch(num){
            case 1:
                speak("たたかう！");
                break;
            case 2:
                speak("ぼうぎょだ！\n" + this.name + "の防御:");
                break;
            case 3:
                speak("逃げる");
                break;
            default:
                speak("1～3で選んでください");
                return choice(num);
        }
        return num;
    }

    public void attack(Chara target){
        int damage = this.atk - target.def;
        if (damage < 0){
            damage = 0;
        }
        target.hp -= damage;
        if(target.hp <= 0){
            target.speak(target.name + "はたおれた！\nせんとうはおわりだ！");
        }else{
            speak(this.name + " は " + target.name + " にこうげき！");
            speak("ダメージは " + damage);
        }
    }
}
