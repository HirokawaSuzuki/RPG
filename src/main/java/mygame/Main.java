package mygame;

import java.util.List;

public class Main {
    public static void main(String[] args){
        
        List<Serif> dialogues = SerifReader.load("/dialogues.json");
        if (dialogues == null) {
            System.out.println("JSONの読み込みに失敗しました。");
            return;
        }
        // JSONの読み込み成功を確認
        System.out.println("JSON読み込み成功。台詞数: " + dialogues.size());

        SerifManager manager = new SerifManager(dialogues);

        // 序盤：老人との会話シーン

        Hero kentaro = new Hero("けんたろ",8,3,1);
        Chara monster = new Chara("スライム",10,5,2);
        List<Chara> characters = List.of(kentaro, monster);
        manager.playScene("line001", characters);
    }
}
