package mygame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SerifManager {
    private Map<String, Serif> dialogueMap = new HashMap<>();
    public String currentId;

    public SerifManager(List<Serif> dialogues) {
        for (Serif line : dialogues) {
            dialogueMap.put(line.id, line);
        }
        this.currentId = null;
    }

    public void playScene(String startId, List<Chara> allies, List<Chara> enemies) {
        Scanner scanner = new Scanner(System.in);
        this.currentId = startId;

        while (this.currentId != null) {
            Serif line = dialogueMap.get(this.currentId);
            if (line == null)
                break;

            int acted = processLine(line, allies, enemies, this.currentId);
            if (acted == 0) {
                if (line.speaker == null || line.speaker.isEmpty()) {
                    System.out.println(line.text);
                } else {
                    System.out.println(line.speaker + "\n「" + line.text + "」");
                }
            }
            if (acted != 2)this.currentId = processChoices(line, scanner);

            scanner.nextLine();
        }

        System.out.println("会話終了");
    }

    // 1行分の処理（喋る／メソッド呼び出し）
    private int processLine(Serif line, List<Chara> allies, List<Chara> enemies, String currentId) {
        List<Chara> allCharacters = new ArrayList<>();
        allCharacters.addAll(allies);
        allCharacters.addAll(enemies);

        for (Chara c : allCharacters) {
            if (line.speaker.equals(c.name)) {
                Chara target = allies.contains(c) ? c.chooseTarget(enemies) : c.chooseAllyTarget(allies);
                invokeMethodOrSpeak(c, line.text, target);
                if (c.next != null && !c.next.isEmpty()) {
                    this.currentId = c.next;
                    c.next = null;
                    return 2;
                }
                return 1;
            }
        }
        return 0;
    }

    // メソッド呼び出しか喋るか
    private void invokeMethodOrSpeak(Chara c, String methodName, Chara target) {
        try {
            Method method = c.getClass().getMethod(methodName, Chara.class);

            method.invoke(c, target);
        } catch (NoSuchMethodException e1) {
            try {
                Method methodNoArg = c.getClass().getMethod(methodName);
                methodNoArg.invoke(c);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e2) {
                c.speak(methodName);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    // 選択肢処理（共通化）
    private String processChoices(Serif line, Scanner scanner) {
        if (line.choices != null && !line.choices.isEmpty()) {
            for (int i = 0; i < line.choices.size(); i++) {
                System.out.println((i + 1) + ". " + line.choices.get(i));
            }
            System.out.print("選択肢番号を入力: ");
            int choice = scanner.nextInt() - 1;
            System.out.println("");
            if (choice >= 0 && choice < line.next.size()) {
                return line.next.get(choice);
            } else {
                System.out.println("無効な選択です。終了します。");
                return null;
            }
        } else if ((line.choices == null || line.choices.isEmpty()) && line.next.size() == 1) {
            return line.next.get(0);
        } else {
            return null;
        }
    }

}
