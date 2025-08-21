package mygame;
import java.util.List;

public class Serif {
    public String id;
    public Chara speaker;
    public String scene;
    public String text;
    public List<Choice> choices;

    public static class Choice {
        public int choiceId;
        public String text;
        public String next;
    }
}
