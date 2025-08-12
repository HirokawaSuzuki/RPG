public class play {
    public static void main(String[] arge){
        hero kentaro = new hero("けんたろ",8,3,1);
        Chara monster = new Chara("スライム",10,5,2);
        serif Serif = new serif();
        for(int i = 0; i < Serif.heroSerif.length; i++) {
            Serif.talk(kentaro, i);
        }
        kentaro.battle(monster);
    }
}
