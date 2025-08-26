package mygame;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SerifManager {
    private Map<String, Serif> dialogueMap = new HashMap<>();

    public SerifManager(List<Serif> dialogues) {
        for (Serif line : dialogues) {
            dialogueMap.put(line.id, line);
        }
    }

    // 8/21 Jsonのスピーカーとキャラが一致した場合キャラのクラスの関数を呼び出せるようにしたい。
    public void playScene(String startId, List<Chara> characters) {
        Scanner scanner = new Scanner(System.in);
        String currentId = startId;

        while (currentId != null) {
            Serif line = dialogueMap.get(currentId);
            for (Chara c : characters) {
                if (line.speaker.equals(c.name)) {
                    try {
                        // セリフ内容と同じ名前のメソッドを探す
                        Method method = c.getClass().getMethod(line.text);

                        // 見つかったら実行！
                        method.invoke(c);
                    } catch (NoSuchMethodException e) {
                        // メソッドが無ければ普通に喋る
                        c.speak(line.text);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (line == null)
                break;

            if (line.choices.size() == 0 && line.next.size() == 1) {
                currentId = line.next.get(0);
                scanner.nextLine();
                continue;
            }

            System.out.println(line.speaker + "：「" + line.text + "」");

            if (line.choices != null && !line.choices.isEmpty()) {
                for (int i = 0; i < line.choices.size(); i++) {
                    System.out.println((i + 1) + ". " + line.choices.get(i));
                }
                System.out.print("選択肢番号を入力: ");
                int choice = scanner.nextInt() - 1;
                if (choice >= 0 && choice < line.choices.size()) {
                    currentId = line.next.get(choice);
                } else {
                    System.out.println("無効な選択です。終了します。");
                    break;
                }
            }

            else {
                currentId = null;
            }
        }
        System.out.println("会話終了");
    }
}
