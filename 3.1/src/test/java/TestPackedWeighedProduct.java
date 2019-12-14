import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackedWeighedProduct {
    private static final double EPS = 10E-6;
    
    
    @Test
    public void testPackedWeighedProduct() throws ProductException {
        Package pack = new Package("Cardboard box", 0.005);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(pack, product, 3.55);
        
        assertAll(
                () -> assertEquals(pack, packedWeighedProduct.getPackage()),
                () -> assertEquals(product, packedWeighedProduct.getProduct()),
                () -> assertEquals(3.55, packedWeighedProduct.getNetMass(), EPS),
                () -> assertEquals(3.555, packedWeighedProduct.getGrossMass(), EPS)
        );
    }
    
    
    @Test
    public void testPackedWeighedProductExceptions() {
        try {
            PackedWeighedProduct product1 = new PackedWeighedProduct(null, new WeighedProduct("Candies", "Liquorice & salt"), 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKAGE, e.getErrorCode());
        }
        
        try {
            PackedWeighedProduct product2 = new PackedWeighedProduct(new Package("Cardboard box", 0.005), null, 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
        
        try {
            PackedWeighedProduct product3 = new PackedWeighedProduct(new Package("Box", 0.005),
                    new WeighedProduct("Candies", "Liquorice & salt"), 0);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_WEIGHT, e.getErrorCode());
        }
    
        try {
            PackedWeighedProduct product4 = new PackedWeighedProduct(new Package("Box", 0.005),
                    new WeighedProduct("Candies", "Liquorice & salt"), -2.5);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_WEIGHT, e.getErrorCode());
        }
    }
    
    
    @Test
    public void testPackedWeighedProductEquals() throws ProductException {
        Package pack1 = new Package("Cardboard box", 0.01);
        Package pack2 = new Package("Wooden box", 0.2);
        WeighedProduct product1 = new WeighedProduct("Apples", "Manufacturer: Russia");
        WeighedProduct product2 = new WeighedProduct("Apples", "Manufacturer: China");
        WeighedProduct product3 = new WeighedProduct("Tangerines", "Manufacturer: Morocco");
        WeighedProduct product4 = new WeighedProduct("Tangerines", "Manufacturer: China");
        
        PackedWeighedProduct packedWeighedProduct1 = new PackedWeighedProduct(pack1, product1, 10);
        PackedWeighedProduct packedWeighedProduct2 = packedWeighedProduct1;
        PackedWeighedProduct packedWeighedProduct3 = new PackedWeighedProduct(pack2, product1, 10);
        PackedWeighedProduct packedWeighedProduct4 = new PackedWeighedProduct(pack1, product2, 10);
        PackedWeighedProduct packedWeighedProduct5 = new PackedWeighedProduct(pack1, product3, 10);
        PackedWeighedProduct packedWeighedProduct6 = new PackedWeighedProduct(pack1, product4, 10);
        PackedWeighedProduct packedWeighedProduct7 = new PackedWeighedProduct(pack1, product1, 15);
        
        assertAll(
                () -> assertEquals(packedWeighedProduct1, packedWeighedProduct2),
                () -> assertNotEquals(packedWeighedProduct1, null),
                () -> assertNotEquals(packedWeighedProduct1, ""),
                () -> assertNotEquals(packedWeighedProduct1, packedWeighedProduct3),
                () -> assertNotEquals(packedWeighedProduct1, packedWeighedProduct4),
                () -> assertNotEquals(packedWeighedProduct5, packedWeighedProduct6),
                () -> assertNotEquals(packedWeighedProduct2, packedWeighedProduct5),
                () -> assertNotEquals(packedWeighedProduct1, packedWeighedProduct7)
        );
    }
}
