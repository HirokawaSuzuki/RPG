package mygame;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GameTest {

    @Test
    void testLoadCharacters() throws Exception {
        Gson gson = new Gson();
        try (InputStreamReader reader =
                     new InputStreamReader(getClass().getResourceAsStream("/characters.json"))) {
            List<Chara> chars = gson.fromJson(reader, new TypeToken<List<Chara>>(){}.getType());
            assertEquals(2, chars.size());
            assertEquals("勇者", chars.get(0).name);
            assertEquals(100, chars.get(0).hp);
        }
    }

    @Test
    void testCharacterAttack() {
        Chara hero = new Chara("勇者", 100, 20, 10, "ally");

        Chara mage = new Chara("魔法使い", 80, 30, 5, "ally");

        hero.attack(mage);
        assertTrue(mage.hp < 80, "攻撃でHPが減っているはず");
    }

    @Test
    void testLoadScenesAndTransition() throws Exception {
        Gson gson = new Gson();
        try (InputStreamReader reader =
                     new InputStreamReader(getClass().getResourceAsStream("/dialogues.json"))) {
            List<Serif> scenes = gson.fromJson(reader, new TypeToken<List<Serif>>(){}.getType());

            Map<String, Serif> sceneMap = new HashMap<>();
            for (Serif s : scenes) {
                sceneMap.put(s.id, s);
            }

            assertTrue(sceneMap.containsKey("001"));
            Serif scene = sceneMap.get("001");
            assertEquals("勇者", scene.speaker);
            assertEquals("宿屋に入る", scene.choices.get(0));
            assertEquals("002", scene.next.get(0));
        }
    }
}
