import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPieceProduct {
    private static final double EPS = 10E-6;
    
    
    @Test
    public void testPieceProduct() throws ProductException {
        String description1 = "A huge pack of pretty little things that gonna make you fat.";
        String description2 = "A carton of hidden happiness.";
        PieceProduct product1 = new PieceProduct("Huge pack of cookies", description1, 20.5);
        PieceProduct product2 = new PieceProduct(" Carton of juice  ", description2, 1);
    
        assertAll(
                () -> assertEquals("Huge pack of cookies", product1.getName()),
                () -> assertEquals(description1, product1.getDescription()),
                () -> assertEquals(20.5, product1.getMass(), EPS),
                () -> assertEquals("Carton of juice", product2.getName()),
                () -> assertEquals(description2, product2.getDescription()),
                () -> assertEquals(1, product2.getMass(), EPS)
        );
    }
    
    
    @Test
    public void testPieceProductExceptions() {
        try {
            PieceProduct product1 = new PieceProduct(null, "Something", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            PieceProduct product2 = new PieceProduct("", "Something", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            PieceProduct product3 = new PieceProduct("   ", "Something", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            PieceProduct product4 = new PieceProduct("Something", "", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
    
        try {
            PieceProduct product5 = new PieceProduct("Something", "", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
    
        try {
            PieceProduct product6 = new PieceProduct("Something", "   ", 1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
    
        try {
            PieceProduct product7 = new PieceProduct("Something", "Something", -1);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_MASS, e.getErrorCode());
        }
    
        try {
            PieceProduct product8 = new PieceProduct("Something", "Something", 0);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_MASS, e.getErrorCode());
        }
    }
    
    @Test
    public void testPieceProductEquals() throws ProductException {
        String name1 = "name1";
        String name2 = "name2";
        String description1 = "description1";
        String description2 = "description2";
        PieceProduct product1 = new PieceProduct(name1, description1, 1.2);
        PieceProduct product2 = new PieceProduct(name1, description1, 1.2);
        PieceProduct product3 = product1;
        PieceProduct product4 = new PieceProduct(name2, description1, 1.2);
        PieceProduct product5 = new PieceProduct(name1, description2, 1.2);
        PieceProduct product6 = new PieceProduct(name1, description1, 2.8);
        Product product7 = new Product(name1, description1);
        WeighedProduct product8 = new WeighedProduct(name1, description1);
    
        assertAll(
                () -> assertEquals(product1, product1),
                () -> assertEquals(product1, product2),
                () -> assertEquals(product1, product3),
                () -> assertNotEquals(product1, product4),
                () -> assertNotEquals(product1, product5),
                () -> assertNotEquals(product1, product6),
                () -> assertNotEquals(product1, product7),
                () -> assertNotEquals(product1, product8)
        );
    }
    
    
    @Test
    public void testPieceProductToString() {
        Locale.setDefault(Locale.ENGLISH);
        
        assertAll(
                () -> assertEquals("Piece product «Huge pack of cookies», description: «You’ll explode if you eat them all.», " +
                                "piece mass: 20.500 kg",
                        new PieceProduct("Huge pack of cookies", "You’ll explode if you eat them all.", 20.5).toString()),
                () -> assertEquals("Piece product «Carton of juice», description: «A carton of hidden happiness.», piece mass: 1.000 kg",
                        new PieceProduct("Carton of juice", "A carton of hidden happiness.", 1).toString())
        );
    }
}
