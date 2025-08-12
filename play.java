public class play {
    public static void main(String[] arge){
        hero kentaro = new hero("けんたろ",8,3,1);
        Chara monster = new Chara("スライム",10,5,2);
        kentaro.speak("ぼうけんを始めよう！");
        kentaro.speak("お？スライムだ！");
        monster.speak("スライムは襲い掛かってきた！");

        kentaro.battle(monster);
    }
}
