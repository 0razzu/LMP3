package products;


import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackedPieceProduct {
    private static final double EPS = 10E-6;
    
    
    @Test
    public void testPackedPieceProduct1() throws ProductException {
        String description = "A huge pack of pretty little things that gonna make you fat.";
        PieceProduct product = new PieceProduct("Huge pack of cookies", description, 20.5);
        Packaging packaging = new Packaging("Box", 0.25);
        
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(product, 2, packaging);
        
        assertAll(
                () -> assertEquals(packaging, packedPieceProduct.getPackaging()),
                () -> assertEquals(product, packedPieceProduct.getProduct()),
                () -> assertEquals(2, packedPieceProduct.getQuantity())
        );
    }
    
    
    @Test
    public void testPackedPieceProduct2() throws ProductException {
        String description = "A carton of hidden happiness.";
        PieceProduct product = new PieceProduct("Carton of juice", description, 1);
        Packaging packaging = new Packaging("Box", 0.25);
        
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(product, 1, packaging);
        
        assertAll(
                () -> assertEquals(packaging, packedPieceProduct.getPackaging()),
                () -> assertEquals(product, packedPieceProduct.getProduct()),
                () -> assertEquals(1, packedPieceProduct.getQuantity())
        );
    }
    
    
    @Test
    public void testPackedPieceProductExceptions() throws ProductException {
        String description = "A huge pack of pretty little things that gonna make you fat.";
        PieceProduct product = new PieceProduct("Huge pack of cookies", description, 20.5);
        Packaging packaging = new Packaging("Box", 0.25);
        
        try {
            PackedPieceProduct packedPieceProduct1 = new PackedPieceProduct(null, 5, packaging);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
        
        try {
            PackedPieceProduct packedPieceProduct2 = new PackedPieceProduct(product, 0, packaging);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_QUANTITY, e.getErrorCode());
        }
        
        try {
            PackedPieceProduct packedPieceProduct3 = new PackedPieceProduct(product, -6, packaging);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NONPOSITIVE_QUANTITY, e.getErrorCode());
        }
        
        try {
            PackedPieceProduct packedPieceProduct4 = new PackedPieceProduct(product, 3, null);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKAGING, e.getErrorCode());
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
        
        PackedPieceProduct packedPieceProduct1 = new PackedPieceProduct(product1, 8, packaging1);
        PackedPieceProduct packedPieceProduct2 = new PackedPieceProduct(product2, 3, packaging2);
        
        assertAll(
                () -> assertEquals(164, packedPieceProduct1.getNetMass(), EPS),
                () -> assertEquals(164.25, packedPieceProduct1.getGrossMass(), EPS),
                () -> assertEquals(3, packedPieceProduct2.getNetMass(), EPS),
                () -> assertEquals(3.003, packedPieceProduct2.getGrossMass(), EPS)
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
        
        PackedPieceProduct packedPieceProduct1 = new PackedPieceProduct(product1, 5, packaging1);
        PackedPieceProduct packedPieceProduct2 = new PackedPieceProduct(product1, 5, packaging1);
        PackedPieceProduct packedPieceProduct3 = new PackedPieceProduct(product1, 5, packaging2);
        PackedPieceProduct packedPieceProduct4 = new PackedPieceProduct(product2, 5, packaging1);
        
        assertAll(
                () -> assertEquals(packedPieceProduct1, packedPieceProduct2),
                () -> assertNotEquals(packedPieceProduct1, packedPieceProduct3),
                () -> assertNotEquals(packedPieceProduct1, packedPieceProduct4),
                () -> assertNotEquals(packedPieceProduct1, ""),
                () -> assertNotEquals(packedPieceProduct1, null)
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
        
        PackedPieceProduct packedPieceProduct1 = new PackedPieceProduct(product1, 7, packaging1);
        PackedPieceProduct packedPieceProduct2 = new PackedPieceProduct(product2, 3, packaging2);
        
        assertAll(
                () -> assertEquals("Packed piece product {Piece product {" +
                        "“Huge pack of cookies”, description: “Pretty crunchy.”, mass: 20.500 kg}, quantity: 7, " +
                        "Packaging {“Box”, mass: 0.250 kg}}", packedPieceProduct1.toString()),
                () -> assertEquals("Packed piece product {Piece product {" +
                        "“Carton of juice”, description: “A carton of hidden happiness.”, mass: 1.000 kg}, quantity: 3, " +
                        "Packaging {“Plastic bag”, mass: 0.003 kg}}", packedPieceProduct2.toString())
        );
    }
}
