import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackage {
    private static final double EPS = 10E-6;
    
    
    @Test
    public void testPackage() throws ProductException {
        Package package1 = new Package("Bubble wrap", 0.005);
        Package package2 = new Package("   Box ", 10);
        
        assertAll(
                () -> assertEquals("Bubble wrap", package1.getName()),
                () -> assertEquals(0.005, package1.getWeight(), EPS),
                () -> assertEquals("Box", package2.getName()),
                () -> assertEquals(10, package2.getWeight(), EPS)
        );
    }
    
    
    @Test
    public void testPackageExceptions() {
        try {
            Package package1 = new Package(null, 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            Package package2 = new Package("", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            Package package3 = new Package("   ", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            Package package4 = new Package("Bubble wrap", -1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_WEIGHT, e.getErrorCode());
        }
    }
    
    
    @Test
    public void testPackageSetters() throws ProductException {
        Package package1 = new Package("Bubble wrap", 0.005);
        
        package1.setName("Box");
        package1.setWeight(10);
        
        assertAll(
                () -> assertEquals("Box", package1.getName()),
                () -> assertEquals(10, package1.getWeight(), EPS)
        );
    }
    
    
    @Test
    public void testPackageEquals() throws ProductException {
        Package package1 = new Package("Bubble wrap", 0.005);
        Package package2 = new Package("Box", 10);
        Package package3 = new Package("Bubble wrap", 0.005);
        Package package4 = package1;
        Package package5 = new Package("Bubble wrap", 0.0050001);
        
        assertAll(
                () -> assertEquals(package1, package1),
                () -> assertNotEquals(package1, package2),
                () -> assertEquals(package1, package3),
                () -> assertEquals(package1, package4),
                () -> assertNotEquals(package1, null),
                () -> assertEquals(package1, package5)
        );
    }
    
    
    @Test
    public void testPackageToString() throws ProductException {
        Locale.setDefault(Locale.ENGLISH);
        
        assertAll(
                () -> assertEquals("Package «Bubble wrap», weight: 0.005 kg", new Package("Bubble wrap", 0.005).toString()),
                () -> assertEquals("Package «Box», weight: 10.000 kg", new Package("Box", 10).toString())
        );
    }
}
