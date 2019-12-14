import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackedPieceProducts {
    private static final double EPS = 10E-6;
    
    
    @Test
    public void testPackedPieceProduct1() throws ProductException {
        String description1 = "A huge pack of pretty little things that gonna make you fat.";
        String description2 = "A carton of hidden happiness.";
        PieceProduct product1 = new PieceProduct("Huge pack of cookies", description1, 20.5);
        PieceProduct product2 = new PieceProduct("Carton of juice", description2, 1);
        Packaging packaging = new Packaging("Box", 0.25);
        
        PackedPieceProducts packedPieceProducts = new PackedPieceProducts(packaging, product1, product2);
        
        assertAll(
                () -> assertEquals(packaging, packedPieceProducts.getPackaging()),
                () -> assertEquals(product1, packedPieceProducts.getProducts()[0]),
                () -> assertEquals(product2, packedPieceProducts.getProducts()[1])
        );
    }
    
    
    @Test
    public void testPackedPieceProduct2() throws ProductException {
        String description1 = "A huge pack of pretty little things that gonna make you fat.";
        String description2 = "A carton of hidden happiness.";
        PieceProduct product1 = new PieceProduct("Huge pack of cookies", description1, 20.5);
        PieceProduct product2 = new PieceProduct("Carton of juice", description2, 1);
        PieceProduct[] products = {product1, product2};
        Packaging packaging = new Packaging("Box", 0.25);
        
        PackedPieceProducts packedPieceProducts = new PackedPieceProducts(packaging, products);
        
        assertAll(
                () -> assertEquals(packaging, packedPieceProducts.getPackaging()),
                () -> assertEquals(products, packedPieceProducts.getProducts())
        );
    }
    
    
    @Test
    public void testPackedPieceProductExceptions() throws ProductException {
        String description = "A huge pack of pretty little things that gonna make you fat.";
        PieceProduct product = new PieceProduct("Huge pack of cookies", description, 20.5);
        Packaging packaging = new Packaging("Box", 0.25);
        
        try {
            PackedPieceProducts packedPieceProducts1 = new PackedPieceProducts(null, product);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKAGING, e.getErrorCode());
        }
    
        try {
            PackedPieceProducts packedPieceProducts2 = new PackedPieceProducts(packaging, (PieceProduct) null);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
    
        try {
            PackedPieceProducts packedPieceProducts3 = new PackedPieceProducts(packaging);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
        
        try {
            PieceProduct[] products = {product, null};
            PackedPieceProducts packedPieceProducts4 = new PackedPieceProducts(packaging, products);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
    }
    
    
    @Test
    public void testPackedPieceProductsGetMasses() throws ProductException {
        String description1 = "A huge pack of pretty little things that gonna make you fat.";
        String description2 = "A carton of hidden happiness.";
        PieceProduct product1 = new PieceProduct("Huge pack of cookies", description1, 20.5);
        PieceProduct product2 = new PieceProduct("Carton of juice", description2, 1);
        Packaging packaging1 = new Packaging("Box", 0.25);
        Packaging packaging2 = new Packaging("Plastic bag", 0.003);
    
        PackedPieceProducts packedPieceProducts1 = new PackedPieceProducts(packaging1, product1, product2);
        PackedPieceProducts packedPieceProducts2 = new PackedPieceProducts(packaging2, product2, product2, product2);
    
        assertAll(
                () -> assertEquals(21.5, packedPieceProducts1.getNetMass(), EPS),
                () -> assertEquals(21.75, packedPieceProducts1.getGrossMass(), EPS),
                () -> assertEquals(3, packedPieceProducts2.getNetMass(), EPS),
                () -> assertEquals(3.003, packedPieceProducts2.getGrossMass(), EPS)
        );
    }
    
    
    @Test
    public void testPackedPieceProductsEquals() throws ProductException {
        String description1 = "A huge pack of pretty little things that gonna make you fat.";
        String description2 = "A carton of hidden happiness.";
        PieceProduct product1 = new PieceProduct("Huge pack of cookies", description1, 20.5);
        PieceProduct product2 = new PieceProduct("Carton of juice", description2, 1);
        Packaging packaging1 = new Packaging("Box", 0.25);
        Packaging packaging2 = new Packaging("Plastic bag", 0.003);
    
        PackedPieceProducts packedPieceProducts1 = new PackedPieceProducts(packaging1, product1, product2);
        PackedPieceProducts packedPieceProducts2 = new PackedPieceProducts(packaging1, product1, product2);
        PackedPieceProducts packedPieceProducts3 = new PackedPieceProducts(packaging1, product2, product2);
        PackedPieceProducts packedPieceProducts4 = new PackedPieceProducts(packaging1, product2, product2, product2);
        PackedPieceProducts packedPieceProducts5 = new PackedPieceProducts(packaging2, product1, product2);
        
        assertAll(
                () -> assertEquals(packedPieceProducts1, packedPieceProducts2),
                () -> assertNotEquals(packedPieceProducts1, packedPieceProducts3),
                () -> assertNotEquals(packedPieceProducts2, packedPieceProducts4),
                () -> assertNotEquals(packedPieceProducts1, packedPieceProducts5),
                () -> assertNotEquals(packedPieceProducts1, ""),
                () -> assertNotEquals(packedPieceProducts1, null)
        );
    }
    
    
    @Test
    public void testPackedPieceProductsToString() throws ProductException {
        Locale.setDefault(Locale.ENGLISH);
        
        String description1 = "Pretty crunchy.";
        String description2 = "A carton of hidden happiness.";
        PieceProduct product1 = new PieceProduct("Huge pack of cookies", description1, 20.5);
        PieceProduct product2 = new PieceProduct("Carton of juice", description2, 1);
        Packaging packaging1 = new Packaging("Box", 0.25);
        Packaging packaging2 = new Packaging("Plastic bag", 0.003);
    
        PackedPieceProducts packedPieceProducts1 = new PackedPieceProducts(packaging1, product1, product2);
        PackedPieceProducts packedPieceProducts2 = new PackedPieceProducts(packaging2, product2, product2, product2);
        
        assertAll(
                () -> assertEquals("Packed piece products:\n" +
                        "    Piece product «Huge pack of cookies», description: «Pretty crunchy.», piece mass: 20.500 kg,\n" +
                        "    Piece product «Carton of juice», description: «A carton of hidden happiness.», piece mass: 1.000 kg,\n" +
                        "Packaging «Box», mass: 0.250 kg", packedPieceProducts1.toString()),
                () -> assertEquals("Packed piece products:\n" +
                        "    Piece product «Carton of juice», description: «A carton of hidden happiness.», piece mass: 1.000 kg,\n" +
                        "    Piece product «Carton of juice», description: «A carton of hidden happiness.», piece mass: 1.000 kg,\n" +
                        "    Piece product «Carton of juice», description: «A carton of hidden happiness.», piece mass: 1.000 kg,\n" +
                        "Packaging «Plastic bag», mass: 0.003 kg", packedPieceProducts2.toString())
        );
    }
}
