import java.util.Scanner;

public class hero extends Chara {
    public hero(String name, int hp, int atk, int def) {
        super(name, hp, atk, def);
    }  
    
    public int choice(int num){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.たたかう 2.ぼうぎょ 3.にげる");
        num = scanner.nextInt();
        switch(num){
            case 1:
                speak("たたかう！");
                break;
            case 2:
                this.speak("ぼうぎょ を せんたくした！");
                break;
            case 3:
                speak("逃げるんだよぉ～～！");
                break;
            default:
                speak("1～3でえらんでください");
                return choice(num);
        }
        return num;
    }

    public void battle(Chara monster){
        int m_hp = monster.getHP();
        int p_hp = this.getHP();
        int b_def = 0;
        
        while(p_hp >= 0 && m_hp >= 0){
            m_hp = monster.getHP();
            p_hp = this.getHP();
            System.out.println("どうする？");
            int action = this.choice(0);
            if(action == 1){
                this.attack(monster);
                if(m_hp <= 0){
                    monster.speak("せんとうにかった！");
                    break;
                }
            } else if(action == 2){
                b_def = 3;
                this.speak("ぼうぎょ力があがった！" , this.getDef(), this.getDef()+b_def);
                this.def += b_def; // 防御力を上げる
            } else if(action == 3){
                if(p_hp <= 5){
                    this.speak("逃げることに成功した！");
                    break;
                } else {
                    this.speak("逃げることはできません！");
                    continue;
                }
            }
            int lose = monster.attack(this);
            if(b_def > 0){
                this.speak("ぼうぎょがとけた！", this.getDef(), this.getDef() - b_def);
                this.def -= b_def; // 防御力を元に戻す
                b_def = 0; // 防御力のバフをリセット
            }
            if(lose <=0){
                this.speak("HPが0になった！");
                this.speak("ゲ-ムオ-バ-！");
                break;
            } 
        }
    }   

}
