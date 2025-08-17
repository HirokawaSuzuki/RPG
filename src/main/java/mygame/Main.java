package mygame;

import java.util.List;

public class Main {
    public static void main(String[] arge){
        
        List<Serif> dialogues = SerifReader.load("/dialogues.json");
        if (dialogues == null) {
            System.out.println("JSONの読み込みに失敗しました。");
            return;
        }
        SerifManager manager = new SerifManager(dialogues);

        // 序盤：老人との会話シーン
        manager.playScene("line001");

        Hero kentaro = new Hero("けんたろ",8,3,1);
        Chara monster = new Chara("スライム",10,5,2);
        kentaro.battle(monster);
    }
}
