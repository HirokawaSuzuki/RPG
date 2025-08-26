package mygame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.util.List;

public class SceneReader {
    public static List<Scene> load(String path) {
        Gson gson = new Gson();
        try (InputStreamReader reader =
                     new InputStreamReader(SceneReader.class.getResourceAsStream(path))) {
            return gson.fromJson(reader, new TypeToken<List<Scene>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
