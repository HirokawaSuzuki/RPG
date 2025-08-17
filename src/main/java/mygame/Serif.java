package mygame;
import java.util.List;

public class Serif {
    public String id;
    public String speaker;
    public String scene;
    public String text;
    public List<Choice> choices;

    public static class Choice {
        public String id;
        public String text;
        public String next;
    }
}
