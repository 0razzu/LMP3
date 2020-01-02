package products;


import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestPackedProducts {
    @Test
    void testPackedProducts() throws ProductException {
        String description = "Pretty crunchy";
        PieceProduct pieceProduct = new PieceProduct("Huge pack of cookies", description, 12500);
        Packaging packagingPiece = new Packaging("Box", 250);
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(pieceProduct, 2, packagingPiece);
        
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packagingWeighed);
        
        Packaging packagingPacked1 = new Packaging("Big packet", 200);
        PackedProducts packedProducts1 = new PackedProducts(packagingPacked1, packedPieceProduct);
        
        Packaging packagingPacked2 = new Packaging("Big box", 500);
        PackedProducts packedProducts2 = new PackedProducts(packagingPacked2, packedPieceProduct, packedWeighedProduct);
        
        PackedProducts packedProducts3 = new PackedProducts(packagingPacked2, packedProducts1);
        
        assertAll(
                () -> assertEquals(packagingPacked1, packedProducts1.getPackaging()),
                () -> assertArrayEquals(new Packed[]{packedPieceProduct}, packedProducts1.getPackeds()),
                () -> assertEquals(25250, packedProducts1.getNetMass()),
                () -> assertEquals(25450, packedProducts1.getGrossMass())
        );
        
        assertAll(
                () -> assertEquals(packagingPacked2, packedProducts2.getPackaging()),
                () -> assertArrayEquals(new Packed[]{packedPieceProduct, packedWeighedProduct}, packedProducts2.getPackeds()),
                () -> assertEquals(28850, packedProducts2.getNetMass()),
                () -> assertEquals(29350, packedProducts2.getGrossMass())
        );
        
        assertAll(
                () -> assertEquals(packagingPacked2, packedProducts3.getPackaging()),
                () -> assertArrayEquals(new Packed[]{packedProducts1}, packedProducts3.getPackeds()),
                () -> assertEquals(25450, packedProducts3.getNetMass()),
                () -> assertEquals(25950, packedProducts3.getGrossMass())
        );
    }
    
    
    @Test
    void testPackedProductsExceptions() throws ProductException {
        Packaging packaging1 = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packaging1);
        
        Packaging packaging2 = new Packaging("Big packet", 200);
        
        try {
            PackedProducts packedProducts1 = new PackedProducts(null, packedWeighedProduct);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKAGING, e.getErrorCode());
        }
        
        try {
            PackedProducts packedProducts2 = new PackedProducts(packaging2, (Packed[]) null);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKEDS, e.getErrorCode());
        }
        
        try {
            PackedProducts packedProducts3 = new PackedProducts(packaging2, (Packed) null);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
        
        try {
            PackedProducts packedProducts3 = new PackedProducts(packaging2, packedWeighedProduct, null, packedWeighedProduct);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
    }
    
    
    @Test
    void testPackedProductsEquals() throws ProductException {
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct1 = new PackedWeighedProduct(product, 3550, packagingWeighed);
        PackedWeighedProduct packedWeighedProduct2 = new PackedWeighedProduct(product, 3900, packagingWeighed);
        
        Packaging packaging1 = new Packaging("Big packet", 200);
        Packaging packaging2 = new Packaging("Big box", 500);
        
        PackedProducts packedProducts1 = new PackedProducts(packaging1, packedWeighedProduct1, packedWeighedProduct2);
        PackedProducts packedProducts2 = new PackedProducts(packaging1, packedWeighedProduct1, packedWeighedProduct2);
        PackedProducts packedProducts3 = new PackedProducts(packaging2, packedWeighedProduct1, packedWeighedProduct2);
        PackedProducts packedProducts4 = new PackedProducts(packaging1, packedWeighedProduct2, packedWeighedProduct2);
        PackedProducts packedProducts5 = new PackedProducts(packaging1, packedWeighedProduct1);
        
        assertAll(
                () -> assertEquals(packedProducts1, packedProducts2),
                () -> assertNotEquals(packedProducts1, packedProducts3),
                () -> assertNotEquals(packedProducts1, packedProducts4),
                () -> assertNotEquals(packedProducts1, packedProducts5),
                () -> assertNotEquals(packedProducts1, null),
                () -> assertNotEquals(packedProducts1, "")
        );
    }
    
    
    @Test
    void testPackedProductsToString() throws ProductException {
        Locale.setDefault(Locale.ENGLISH);
        
        String description = "Pretty crunchy";
        PieceProduct pieceProduct = new PieceProduct("Huge pack of cookies", description, 12500);
        Packaging packagingPiece = new Packaging("Box", 250);
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(pieceProduct, 2, packagingPiece);
        
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct weighedProduct = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(weighedProduct, 3550, packagingWeighed);
        
        Packaging packaging1 = new Packaging("Big packet", 200);
        Packaging packaging2 = new Packaging("Big box", 500);
        
        PackedProducts packedProducts1 = new PackedProducts(packaging1, packedWeighedProduct);
        PackedProducts packedProducts2 = new PackedProducts(packaging2, packedPieceProduct, packedProducts1);
        
        assertAll(
                () -> assertEquals("Packed products: [" +
                        "Packaging {“Big packet”, mass: 0.200 kg}, " +
                        "Packed weighed product {Weighed product {“Candies”, description: “Liquorice & salt”}, " +
                        "mass: 3.550 kg, Packaging {“Cardboard box”, mass: 0.050 kg}}]", packedProducts1.toString()),
                () -> assertEquals("Packed products: [" +
                        "Packaging {“Big box”, mass: 0.500 kg}, " +
                        "Packed piece product {Piece product {“Huge pack of cookies”, " +
                        "description: “Pretty crunchy”, mass: 12.500 kg}, quantity: 2, Packaging {“Box”, mass: 0.250 kg}}, " +
                        "Packed products: [Packaging {“Big packet”, mass: 0.200 kg}, " +
                        "Packed weighed product {Weighed product {“Candies”, description: “Liquorice & salt”}, " +
                        "mass: 3.550 kg, Packaging {“Cardboard box”, mass: 0.050 kg}}]]", packedProducts2.toString())
        );
    }
}
