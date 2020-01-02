package products;


import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;


public class TestProductBatch {
    private static final double EPS = 1E-6;
    
    
    @Test
    void testProductBatch() throws ProductException {
        String description = "Pretty crunchy";
        PieceProduct pieceProduct = new PieceProduct("Huge pack of cookies", description, 12500);
        Packaging packagingPiece = new Packaging("Box", 250);
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(pieceProduct, 2, packagingPiece);
        
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packagingWeighed);
        
        Packaging packagingPacked = new Packaging("Big box", 100);
        PackedProducts packedProducts = new PackedProducts(packagingPacked, packedPieceProduct, packedWeighedProduct);
        
        ProductBatch productBatch = new ProductBatch("Batch #123", packedPieceProduct, packedWeighedProduct, packedProducts);
        
        assertAll(
                () -> assertEquals("Batch #123", productBatch.getDescription()),
                () -> assertArrayEquals(new Packed[]{packedPieceProduct, packedWeighedProduct, packedProducts},
                        productBatch.getPackeds()),
                () -> assertEquals(pieceProduct,
                        ((PieceProduct) (((PackedProducts) (productBatch.getPackeds()[2])).getPackeds()[0]))),
                () -> assertEquals(57800, productBatch.getMass(), EPS)
        );
    }
    
    
    @Test
    void testProductBatchExceptions() throws ProductException {
        String description = "Pretty crunchy";
        PieceProduct pieceProduct = new PieceProduct("Huge pack of cookies", description, 12500);
        Packaging packagingPiece = new Packaging("Box", 200);
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(pieceProduct, 2, packagingPiece);
        
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3, packagingWeighed);
        
        Packed[] packeds = {packedPieceProduct, packedWeighedProduct};
        
        try {
            ProductBatch productBatch1 = new ProductBatch(null, packeds);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
        
        try {
            ProductBatch productBatch2 = new ProductBatch("  ", packeds);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
        
        try {
            ProductBatch productBatch3 = new ProductBatch("Batch #3", (Packed[]) null);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKEDS, e.getErrorCode());
        }
        
        try {
            ProductBatch productBatch4 = new ProductBatch("Batch #4");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PACKEDS, e.getErrorCode());
        }
        
        try {
            ProductBatch productBatch5 = new ProductBatch("Batch #5", packedPieceProduct, null, packedWeighedProduct);
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.NULL_PRODUCT, e.getErrorCode());
        }
    }
    
    
    @Test
    void testProductBatchEquals() throws ProductException {
        String description = "Pretty crunchy";
        PieceProduct pieceProduct = new PieceProduct("Huge pack of cookies", description, 12500);
        Packaging packagingPiece = new Packaging("Box", 250);
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(pieceProduct, 2, packagingPiece);
        
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packagingWeighed);
        
        Packaging packagingPacked = new Packaging("Big box", 100);
        PackedProducts packedProducts = new PackedProducts(packagingPacked, packedPieceProduct, packedWeighedProduct);
        
        ProductBatch productBatch1 = new ProductBatch("Batch #1", packedPieceProduct, packedWeighedProduct, packedProducts);
        ProductBatch productBatch2 = new ProductBatch("Batch #1", packedPieceProduct, packedWeighedProduct, packedProducts);
        ProductBatch productBatch3 = new ProductBatch("Batch #2", packedPieceProduct, packedWeighedProduct, packedProducts);
        ProductBatch productBatch4 = new ProductBatch("Batch #1", packedWeighedProduct, packedPieceProduct, packedProducts);
        ProductBatch productBatch5 = new ProductBatch("Batch #1", packedProducts);
        
        assertAll(
                () -> assertEquals(productBatch1, productBatch2),
                () -> assertNotEquals(productBatch1, productBatch3),
                () -> assertNotEquals(productBatch1, productBatch4),
                () -> assertNotEquals(productBatch1, productBatch5),
                () -> assertNotEquals(productBatch1, null),
                () -> assertNotEquals(productBatch1, "")
        );
    }
    
    
    @Test
    void testProductBatchToString() throws ProductException {
        Locale.setDefault(Locale.ENGLISH);
        
        String description = "Pretty crunchy";
        PieceProduct pieceProduct = new PieceProduct("Huge pack of cookies", description, 12500);
        Packaging packagingPiece = new Packaging("Box", 250);
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(pieceProduct, 2, packagingPiece);
        
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packagingWeighed);
        
        Packaging packagingPacked = new Packaging("Big box", 100);
        PackedProducts packedProducts = new PackedProducts(packagingPacked, packedPieceProduct, packedWeighedProduct);
        
        ProductBatch productBatch1 = new ProductBatch("Batch #1", packedWeighedProduct);
        ProductBatch productBatch2 = new ProductBatch("Batch #2", packedPieceProduct, packedWeighedProduct, packedProducts);
        
        assertAll(
                () -> assertEquals("Product batch: [" +
                        "description: “Batch #1”, " +
                        "Packed weighed product {" +
                        "Weighed product {“Candies”, description: “Liquorice & salt”}, " +
                        "mass: 3.550 kg, Packaging {“Cardboard box”, mass: 0.050 kg}}]", productBatch1.toString()),
                () -> assertEquals("Product batch: [" +
                        "description: “Batch #2”, " +
                        "Packed piece product {" +
                        "Piece product {“Huge pack of cookies”, description: “Pretty crunchy”, mass: 12.500 kg}, " +
                        "quantity: 2, Packaging {“Box”, mass: 0.250 kg}}, " +
                        "Packed weighed product {" +
                        "Weighed product {“Candies”, description: “Liquorice & salt”}, " +
                        "mass: 3.550 kg, Packaging {“Cardboard box”, mass: 0.050 kg}}, " +
                        "Packed products: [" +
                        "Packaging {“Big box”, mass: 0.100 kg}, " +
                        "Packed piece product {Piece product {“Huge pack of cookies”, description: “Pretty crunchy”, " +
                        "mass: 12.500 kg}, quantity: 2, Packaging {“Box”, mass: 0.250 kg}}, " +
                        "Packed weighed product {" +
                        "Weighed product {“Candies”, description: “Liquorice & salt”}, " +
                        "mass: 3.550 kg, Packaging {“Cardboard box”, mass: 0.050 kg}}]]", productBatch2.toString())
        );
    }
}
