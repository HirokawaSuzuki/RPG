public class play {
    public static void main(String[] arge){
        int p_hp = 8;
        int m_hp =10;

        Chara hero = new Chara("けんたろ",p_hp,3,1);
        Chara monster = new Chara("スライム",m_hp,5,2);
        hero.speak("ぼうけんを始めよう！");
        hero.speak("お？スライムだ！");
        monster.speak("スライムは襲い掛かってきた！");
        

        while(p_hp >= 0 && m_hp >= 0){
            p_hp = hero.getHP();
            m_hp = monster.getHP();
            hero.hp();
            monster.hp();
            hero.speak("どうする？");
            hero.attack(monster);
            if(m_hp <= 0){
                monster.speak("スライムは倒れた！");
                monster.speak("戦闘終了！");
                break;
            }
            monster.attack(hero);

        }
    }
}
