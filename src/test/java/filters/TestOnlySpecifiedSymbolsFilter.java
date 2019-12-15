package filters;


import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class TestOnlySpecifiedSymbolsFilter {
    @Test
    public void testOnlySpecifiedSymbolsFilter1() {
        Filter filter1 = new OnlySpecifiedSymbolsFilter("Мама");
        Filter filter2 = new OnlySpecifiedSymbolsFilter("Мам ылру");
    
        String str = "Мама мыла раму";
        
        assertAll(
                () -> assertFalse(filter1.apply(str)),
                () -> assertTrue(filter2.apply(str))
        );
    }
    
    
    @Test
    public void testOnlySpecifiedSymbolsFilter2() {
        Filter filter1 = new OnlySpecifiedSymbolsFilter((String) null);
    
        Set<Character> characters = new HashSet<>();
        characters.add('а');
        characters.add('М');
        characters.add('м');
        
        Filter filter2 = new OnlySpecifiedSymbolsFilter(characters);
    
        String str1 = "Мама";
        String str2 = "";
        
        assertAll(
                () -> assertFalse(filter1.apply(str1)),
                () -> assertTrue(filter1.apply(str2)),
                () -> assertTrue(filter1.apply(null)),
                () -> assertTrue(filter2.apply(str1)),
                () -> assertTrue(filter2.apply(str2)),
                () -> assertTrue(filter2.apply(null))
        );
    }
    
    
    @Test
    public void testOnlySpecifiedSymbolsEquals() {
        Filter filter1 = new OnlySpecifiedSymbolsFilter((String) null);
        Filter filter2 = new OnlySpecifiedSymbolsFilter((Set<Character>) null);
        Filter filter3 = new OnlySpecifiedSymbolsFilter("");
        Filter filter4 = new OnlySpecifiedSymbolsFilter("Мама мыла раму");
        Filter filter5 = new OnlySpecifiedSymbolsFilter("Мам ылру");
        
        assertAll(
                () -> assertEquals(filter1, filter2),
                () -> assertEquals(filter1, filter3),
                () -> assertNotEquals(filter1, filter4),
                () -> assertEquals(filter4, filter5),
                () -> assertNotEquals(filter3, "")
        );
    }
    
    
    @Test
    public void testOnlySpecifiedSymbolsToString() {
        Filter filter1 = new OnlySpecifiedSymbolsFilter("М");
        Filter filter2 = new OnlySpecifiedSymbolsFilter((String) null);
        
        assertAll(
                () -> assertEquals("OnlySpecifiedSymbolsFilter {М}", filter1.toString()),
                () -> assertEquals("OnlySpecifiedSymbolsFilter {}", filter2.toString())
        );
    }
}
