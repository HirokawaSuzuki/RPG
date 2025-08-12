public class serif {
    
    public String[] heroSerif = {
        "ぼうけんを始めよう！",
        "お？スライムだ！",
        "スライムは襲い掛かってきた！",
        "スライムを倒した！"
    };

    public void talk(Chara chara, int i) {
        chara.speak(heroSerif[i]);
    }
}
