package mygame;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Serif> dialogues = SerifReader.load("/dialogues.json");
        if (dialogues == null) {
            System.err.println("JSONの読み込みに失敗しました。");
            return;
        }
        System.err.println("JSON読み込み成功。台詞数: " + dialogues.size());
        List<Chara> allChars = CharaReader.load("/characters.json");
        if (allChars == null) {
            System.err.println("キャラクターJSONの読み込みに失敗");
            return;
        }
        List<Scene> scenes = SceneReader.load("/scenes.json");
        if (scenes == null) {
            System.err.println("シーンJSONの読み込みに失敗");
            return;
        }

        SerifManager manager = new SerifManager(dialogues);
        // 味方と敵に分ける（例えば1人目を味方、それ以降を敵）
        List<Chara> pCharas = allChars.stream().filter(c -> c.faction.equals("ally")).toList();
        List<Chara> eCharas = allChars.stream().filter(c -> c.faction.equals("enemy")).toList();
        manager.playScene("oldman001", pCharas, eCharas);
    }
}
