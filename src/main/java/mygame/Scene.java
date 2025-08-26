package mygame;
import java.util.List;

public class Scene {
    public String id;
    public String title;
    public List<String> pCharas; // このシーンに出る味方
    public List<String> eCharas;      // このシーンに出る敵
    public String startDialogue;  // 会話開始ID
}
