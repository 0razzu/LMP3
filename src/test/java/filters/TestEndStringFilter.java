package filters;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestEndStringFilter {
    @Test
    public void testEndStringFilter1() {
        Filter filter1 = new EndStringFilter("раму");
        Filter filter2 = new EndStringFilter("мыла");
        
        String str = "Мама мыла раму";
        
        assertAll(
                () -> assertTrue(filter1.apply(str)),
                () -> assertFalse(filter2.apply(str))
        );
    }
    
    
    @Test
    public void testEndStringFilter2() {
        Filter filter1 = new EndStringFilter("");
        Filter filter2 = new EndStringFilter(null);
        
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
    public void testEndStringFilterEquals() {
        Filter filter1 = new EndStringFilter("abc");
        Filter filter2 = new EndStringFilter("abc");
        Filter filter3 = new EndStringFilter("ab");
        Filter filter4 = new EndStringFilter("");
        
        assertAll(
                () -> assertEquals(filter1, filter2),
                () -> assertNotEquals(filter1, filter3),
                () -> assertNotEquals(filter1, filter4),
                () -> assertNotEquals(filter1, null),
                () -> assertNotEquals(filter1, "abc")
        );
    }
    
    
    @Test
    public void testEndStringFilterToString() {
        Filter filter1 = new EndStringFilter("раму");
        Filter filter2 = new EndStringFilter(null);
        
        assertAll(
                () -> assertEquals("EndStringFilter {pattern: “раму”}", filter1.toString()),
                () -> assertEquals("EndStringFilter {pattern: null}", filter2.toString())
        );
    }
}
