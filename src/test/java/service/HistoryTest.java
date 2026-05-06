package service;

import History.HistoryManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistoryTest {

    @Test
    void testAddToHistory() {
        HistoryManager manager = new HistoryManager();
        ArrayList<String> history = new ArrayList<>();

        manager.addToHistory(history, "1+1=2");

        assertEquals(1, history.size());
        assertEquals("1+1=2", history.get(0));
    }

    @Test
    void testHistoryLimit() {
        HistoryManager manager = new HistoryManager();
        ArrayList<String> history = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            manager.addToHistory(history, "entry " + i);
        }

        assertEquals(10, history.size());
        assertEquals("entry 2", history.get(0));
    }

    @Test
    void testGetLast() {
        HistoryManager manager = new HistoryManager();
        ArrayList<String> history = new ArrayList<>();

        history.add("1. 1 + 1 = 2");
        history.add("2. 2 + 2 = 4");

        assertEquals( "2. 2 + 2 = 4", manager.last(history));
    }

    @Test
    void testGetLastEmpty() {
        HistoryManager manager = new HistoryManager();
        ArrayList<String> history = new ArrayList<>();

        assertEquals("История пуста.", manager.last(history));
    }

    @Test
    void testClearHistory() {
        HistoryManager manager = new HistoryManager();
        ArrayList<String> history = new ArrayList<>();

        history.add("test");
        manager.clearHistory(history);

        assertTrue(history.isEmpty());
    }
}
