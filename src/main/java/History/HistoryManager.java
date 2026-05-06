package History;

import java.util.ArrayList;

public class HistoryManager {
    private static final String EMPTY_HISTORY = "История пуста.";
    private static final int HISTORY_LIMIT = 10;

    public void addToHistory(ArrayList<String> history, String entry) {
        if (history.size() == HISTORY_LIMIT) {
            history.remove(0);
        }
        history.add(entry);
    }

    public void historyGet(ArrayList<String> history) {
        if (history.isEmpty()) {
            System.out.println(EMPTY_HISTORY);
            return;
        }

        int start = Math.max(0, history.size() - HISTORY_LIMIT);
        for (int i = start; i < history.size(); i++) {
            System.out.println((i - start + 1) + ". " + history.get(i));
        }
    }

    public String last(ArrayList<String> history) {
        if (history.isEmpty()) {
            return "История пуста.";
        }
        return history.get(history.size() - 1);
    }

    public void clearHistory(ArrayList<String> history) {
        history.clear();
        System.out.print("История очищена.");
    }

    public void HelpMenu() {
        System.out.print("Доступные команды:\n\n");
        System.out.print("<число> <оператор> <число>\n\n");
        System.out.print("Операторы:\n" +
                "+  сложение\n" +
                "-  вычитание\n" +
                "*  умножение\n" +
                "/  деление\n");
        System.out.print("Команды:\n" +
                "history  показать историю\n" +
                "last     показать последний результат\n" +
                "clear    очистить историю\n" +
                "exit     выход");
    }
}
