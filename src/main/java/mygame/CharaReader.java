package mygame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.util.List;

public class CharaReader {
    public static List<Chara> load(String path) {
        Gson gson = new Gson();
        try (InputStreamReader reader =
                     new InputStreamReader(CharaReader.class.getResourceAsStream(path))) {
            return gson.fromJson(reader, new TypeToken<List<Chara>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
