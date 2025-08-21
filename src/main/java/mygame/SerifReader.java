package mygame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class SerifReader {
    public static List<Serif> load(String filePath) {
        try {
            // クラスパスからリソースを取得
            InputStream inputStream = SerifReader.class.getResourceAsStream(filePath);
            if (inputStream == null) {
                System.err.println("ファイルが見つかりません: " + filePath);
                return null;
            }
            
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Serif>>() {}.getType();
            List<Serif> result = gson.fromJson(reader, listType);
            
            reader.close();
            inputStream.close();
            
            return result;
        } catch (Exception e) {
            System.err.println("JSONファイルの読み込みでエラーが発生しました: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}