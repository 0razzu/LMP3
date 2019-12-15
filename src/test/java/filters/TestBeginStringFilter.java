package filters;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestBeginStringFilter {
    @Test
    public void testBeginStringFilter1() {
        Filter filter1 = new BeginStringFilter("Мама");
        Filter filter2 = new BeginStringFilter("мыла");
        
        String str = "Мама мыла раму";
        
        assertAll(
                () -> assertTrue(filter1.apply(str)),
                () -> assertFalse(filter2.apply(str))
        );
    }
    
    
    @Test
    public void testBeginStringFilter2() {
        Filter filter1 = new BeginStringFilter("");
        Filter filter2 = new BeginStringFilter(null);
        
        String str1 = "Мама мыла раму";
        String str2 = "";
        
        assertAll(
                () -> assertTrue(filter1.apply(str1)),
                () -> assertTrue(filter1.apply(str2)),
                () -> assertFalse(filter1.apply(null)),
                () -> assertFalse(filter2.apply(str1)),
                () -> assertFalse(filter2.apply(str2)),
                () -> assertTrue(filter2.apply(null))
        );
    }
    
    
    @Test
    public void testBeginStringFilterEquals() {
        Filter filter1 = new BeginStringFilter("abc");
        Filter filter2 = new BeginStringFilter("abc");
        Filter filter3 = new BeginStringFilter("ab");
        Filter filter4 = new BeginStringFilter("");
        
        assertAll(
                () -> assertEquals(filter1, filter2),
                () -> assertNotEquals(filter1, filter3),
                () -> assertNotEquals(filter1, filter4),
                () -> assertNotEquals(filter1, null),
                () -> assertNotEquals(filter1, "abc")
        );
    }
    
    
    @Test
    public void testBeginStringFilterToString() {
        Filter filter1 = new BeginStringFilter("Мама");
        Filter filter2 = new BeginStringFilter(null);
        
        assertAll(
                () -> assertEquals("BeginStringFilter {pattern: “Мама”}", filter1.toString()),
                () -> assertEquals("BeginStringFilter {pattern: null}", filter2.toString())
        );
    }
}
