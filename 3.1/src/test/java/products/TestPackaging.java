package products;


import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackaging {
    @Test
    void testPackaging() throws ProductException {
        Packaging packaging1 = new Packaging("Bubble wrap", 5);
        Packaging packaging2 = new Packaging("   Box ", 1000);
        
        assertAll(
                () -> assertEquals("Bubble wrap", packaging1.getName()),
                () -> assertEquals(5, packaging1.getMass()),
                () -> assertEquals("Box", packaging2.getName()),
                () -> assertEquals(1000, packaging2.getMass())
        );
    }
    
    
    @Test
    void testPackagingExceptions() {
        try {
            Packaging packaging1 = new Packaging(null, 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            Packaging packaging2 = new Packaging("", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            Packaging Packaging3 = new Packaging("   ", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            Packaging Packaging4 = new Packaging("Bubble wrap", -1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_MASS, e.getErrorCode());
        }
    }
    
    
    @Test
    void testPackagingSetters() throws ProductException {
        Packaging packaging1 = new Packaging("Bubble wrap", 5);
        
        packaging1.setName("Box");
        packaging1.setMass(1000);
        
        assertAll(
                () -> assertEquals("Box", packaging1.getName()),
                () -> assertEquals(1000, packaging1.getMass())
        );
    }
    
    
    @Test
    void testPackagingEquals() throws ProductException {
        Packaging packaging1 = new Packaging("Bubble wrap", 5);
        Packaging packaging2 = new Packaging("Box", 10);
        Packaging Packaging3 = new Packaging("Bubble wrap", 5);
        Packaging Packaging4 = packaging1;
        
        assertAll(
                () -> assertEquals(packaging1, packaging1),
                () -> assertNotEquals(packaging1, packaging2),
                () -> assertEquals(packaging1, Packaging3),
                () -> assertEquals(packaging1, Packaging4),
                () -> assertNotEquals(packaging1, null)
        );
    }
    
    
    @Test
    void testPackagingToString() {
        Locale.setDefault(Locale.ENGLISH);
        
        assertAll(
                () -> assertEquals("Packaging {“Bubble wrap”, mass: 0.005 kg}", new Packaging("Bubble wrap", 5).toString()),
                () -> assertEquals("Packaging {“Box”, mass: 10.000 kg}", new Packaging("Box", 10000).toString())
        );
    }
}
