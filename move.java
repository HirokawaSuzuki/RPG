import java.util.Random;
import java.util.Scanner;

public class move{

    map map = new map();
    Random rand = new Random();
    battle battle = new battle();

    public void walk() {
        Scanner scanner = new Scanner(System.in);
        String dire = scanner.next();
        scanner.close();

        int num;

        if(dire == "W" || dire == "w") {
            for(int i = 0; i < 13; i++){
                for(int j = 0; j < 22; j++){
                    if(map.gps[i][j] == "@"){

                        break;
                    }
                }
                System.out.println("\n");
            }
        } else if(dire == "S" || dire == "s") {
            
        } else if(dire == "A" || dire == "a") {
            
        } else if(dire == "D" || dire == "d") {
            
        } else {
            
        }
    }
    
    public void ahead(){
        System.out.println("Wキー : 上に進む");
        System.out.println("Sキー : 下に進む");
        System.out.println("Aキー : 左に進む");
        System.out.println("Dキー : 右に進む");
        
        

        while(true){
            map.position();
            int num = rand.nextInt(100);

            if(num < 13){
                System.out.println("敵が現れた！");
                // ここに敵との戦闘処理を追加することができます
            } else {
                
            }
        }
    }
}