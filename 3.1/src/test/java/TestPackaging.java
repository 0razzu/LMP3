import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackaging {
    private static final double EPS = 10E-6;
    
    
    @Test
    public void testPackaging() throws ProductException {
        Packaging Packaging1 = new Packaging("Bubble wrap", 0.005);
        Packaging Packaging2 = new Packaging("   Box ", 10);
        
        assertAll(
                () -> assertEquals("Bubble wrap", Packaging1.getName()),
                () -> assertEquals(0.005, Packaging1.getMass(), EPS),
                () -> assertEquals("Box", Packaging2.getName()),
                () -> assertEquals(10, Packaging2.getMass(), EPS)
        );
    }
    
    
    @Test
    public void testPackagingExceptions() {
        try {
            Packaging Packaging1 = new Packaging(null, 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            Packaging Packaging2 = new Packaging("", 1);
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
            assertEquals(ProductErrorCode.NONPOSITIVE_WEIGHT, e.getErrorCode());
        }
    }
    
    
    @Test
    public void testPackagingSetters() throws ProductException {
        Packaging Packaging1 = new Packaging("Bubble wrap", 0.005);
        
        Packaging1.setName("Box");
        Packaging1.setMass(10);
        
        assertAll(
                () -> assertEquals("Box", Packaging1.getName()),
                () -> assertEquals(10, Packaging1.getMass(), EPS)
        );
    }
    
    
    @Test
    public void testPackagingEquals() throws ProductException {
        Packaging Packaging1 = new Packaging("Bubble wrap", 0.005);
        Packaging Packaging2 = new Packaging("Box", 10);
        Packaging Packaging3 = new Packaging("Bubble wrap", 0.005);
        Packaging Packaging4 = Packaging1;
        Packaging Packaging5 = new Packaging("Bubble wrap", 0.0050001);
        
        assertAll(
                () -> assertEquals(Packaging1, Packaging1),
                () -> assertNotEquals(Packaging1, Packaging2),
                () -> assertEquals(Packaging1, Packaging3),
                () -> assertEquals(Packaging1, Packaging4),
                () -> assertNotEquals(Packaging1, null),
                () -> assertEquals(Packaging1, Packaging5)
        );
    }
    
    
    @Test
    public void testPackagingToString() throws ProductException {
        Locale.setDefault(Locale.ENGLISH);
        
        assertAll(
                () -> assertEquals("Packaging «Bubble wrap», mass: 0.005 kg", new Packaging("Bubble wrap", 0.005).toString()),
                () -> assertEquals("Packaging «Box», mass: 10.000 kg", new Packaging("Box", 10).toString())
        );
    }
}
