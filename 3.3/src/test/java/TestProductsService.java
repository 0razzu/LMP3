import filters.BeginStringFilter;
import filters.EndStringFilter;
import filters.Filter;
import org.junit.jupiter.api.Test;
import products.*;

import static org.junit.jupiter.api.Assertions.*;


public class TestProductsService {
    @Test
    void testProductServiceCountByFilter() throws ProductException {
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
        
        Filter filter1 = new BeginStringFilter("Huge");
        Filter filter2 = new EndStringFilter("");
        
        assertAll(
                () -> assertEquals(0, ProductsService.countByFilter(productBatch1, filter1)),
                () -> assertEquals(1, ProductsService.countByFilter(productBatch2, filter1)),
                () -> assertEquals(1, ProductsService.countByFilter(productBatch1, filter2)),
                () -> assertEquals(2, ProductsService.countByFilter(productBatch2, filter2))
        );
    }
    
    
    @Test
    void testProductServiceCountByFilterDeep() throws ProductException {
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
        
        Filter filter1 = new BeginStringFilter("Huge");
        Filter filter2 = new EndStringFilter("");
        
        assertAll(
                () -> assertEquals(0, ProductsService.countByFilterDeep(productBatch1, filter1)),
                () -> assertEquals(2, ProductsService.countByFilterDeep(productBatch2, filter1)),
                () -> assertEquals(1, ProductsService.countByFilterDeep(productBatch1, filter2)),
                () -> assertEquals(4, ProductsService.countByFilterDeep(productBatch2, filter2))
        );
    }
    
    
    @Test
    void testProductServiceCheckAllWeighed() throws ProductException {
        String description = "Pretty crunchy";
        PieceProduct pieceProduct = new PieceProduct("Huge pack of cookies", description, 12500);
        Packaging packagingPiece = new Packaging("Box", 250);
        PackedPieceProduct packedPieceProduct = new PackedPieceProduct(pieceProduct, 2, packagingPiece);
        
        Packaging packagingWeighed = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packagingWeighed);
        
        Packaging packagingPacked = new Packaging("Big box", 100);
        PackedProducts packedProducts = new PackedProducts(packagingPacked, packedPieceProduct, packedWeighedProduct);
        PackedProducts packedWeighedProducts = new PackedProducts(packagingPacked, packedWeighedProduct, packedWeighedProduct);
        
        ProductBatch productBatch1 = new ProductBatch("Batch #1", packedWeighedProduct);
        ProductBatch productBatch2 = new ProductBatch("Batch #2", packedPieceProduct, packedWeighedProduct, packedProducts);
        ProductBatch productBatch3 = new ProductBatch("Batch #3", packedWeighedProduct, packedWeighedProducts);
        
        assertAll(
                () -> assertTrue(ProductsService.checkAllWeighted(productBatch1)),
                () -> assertFalse(ProductsService.checkAllWeighted(productBatch2)),
                () -> assertTrue(ProductsService.checkAllWeighted(productBatch3))
        );
    }
    
    
    @Test
    void testProductServiceExceptions() throws ProductException {
        Packaging packaging = new Packaging("Cardboard box", 50);
        WeighedProduct product = new WeighedProduct("Candies", "Liquorice & salt");
        PackedWeighedProduct packedWeighedProduct = new PackedWeighedProduct(product, 3550, packaging);
        ProductBatch productBatch = new ProductBatch("Batch #1", packedWeighedProduct);
        Filter filter = new BeginStringFilter("Huge");
        
        try {
            int num1 = ProductsService.countByFilter(null, filter);
            fail();
        } catch (ProductsServiceException e) {
            assertEquals(ProductsServiceErrorCode.NULL_BATCH, e.getErrorCode());
        }
        
        try {
            int num2 = ProductsService.countByFilter(productBatch, null);
            fail();
        } catch (ProductsServiceException e) {
            assertEquals(ProductsServiceErrorCode.NULL_FILTER, e.getErrorCode());
        }
        
        try {
            int num3 = ProductsService.countByFilterDeep(null, filter);
            fail();
        } catch (ProductsServiceException e) {
            assertEquals(ProductsServiceErrorCode.NULL_BATCH, e.getErrorCode());
        }
        
        try {
            int num4 = ProductsService.countByFilterDeep(productBatch, null);
            fail();
        } catch (ProductsServiceException e) {
            assertEquals(ProductsServiceErrorCode.NULL_FILTER, e.getErrorCode());
        }
        
        try {
            boolean ok = ProductsService.checkAllWeighted(null);
            fail();
        } catch (ProductsServiceException e) {
            assertEquals(ProductsServiceErrorCode.NULL_BATCH, e.getErrorCode());
        }
    }
}
