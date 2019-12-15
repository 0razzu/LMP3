package Products;


import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackaging {
    private static final double EPS = 10E-6;
    
    
    @Test
    public void testPackaging() throws ProductException {
        Packaging packaging1 = new Packaging("Bubble wrap", 0.005);
        Packaging packaging2 = new Packaging("   Box ", 10);
        
        assertAll(
                () -> assertEquals("Bubble wrap", packaging1.getName()),
                () -> assertEquals(0.005, packaging1.getMass(), EPS),
                () -> assertEquals("Box", packaging2.getName()),
                () -> assertEquals(10, packaging2.getMass(), EPS)
        );
    }
    
    
    @Test
    public void testPackagingExceptions() {
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
    public void testPackagingSetters() throws ProductException {
        Packaging packaging1 = new Packaging("Bubble wrap", 0.005);
        
        packaging1.setName("Box");
        packaging1.setMass(10);
        
        assertAll(
                () -> assertEquals("Box", packaging1.getName()),
                () -> assertEquals(10, packaging1.getMass(), EPS)
        );
    }
    
    
    @Test
    public void testPackagingEquals() throws ProductException {
        Packaging packaging1 = new Packaging("Bubble wrap", 0.005);
        Packaging packaging2 = new Packaging("Box", 10);
        Packaging Packaging3 = new Packaging("Bubble wrap", 0.005);
        Packaging Packaging4 = packaging1;
        Packaging Packaging5 = new Packaging("Bubble wrap", 0.0050001);
        
        assertAll(
                () -> assertEquals(packaging1, packaging1),
                () -> assertNotEquals(packaging1, packaging2),
                () -> assertEquals(packaging1, Packaging3),
                () -> assertEquals(packaging1, Packaging4),
                () -> assertNotEquals(packaging1, null),
                () -> assertEquals(packaging1, Packaging5)
        );
    }
    
    
    @Test
    public void testPackagingToString() throws ProductException {
        Locale.setDefault(Locale.ENGLISH);
        
        assertAll(
                () -> assertEquals("Packaging {«Bubble wrap», mass: 0.005 kg}", new Packaging("Bubble wrap", 0.005).toString()),
                () -> assertEquals("Packaging {«Box», mass: 10.000 kg}", new Packaging("Box", 10).toString())
        );
    }
}
