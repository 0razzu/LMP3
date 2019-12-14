package Products;


import Products.ProductErrorCode;
import Products.ProductException;
import org.junit.jupiter.api.Test;
import Products.Product;
import Products.WeighedProduct;

import static org.junit.jupiter.api.Assertions.*;


public class TestWeighedProduct {
    @Test
    public void testWeighedProduct() throws ProductException {
        String description1 = "Pretty little things that gonna make you fat.";
        String description2 = "Not a juice indeed.";
        WeighedProduct product1 = new WeighedProduct("Cookies", description1);
        WeighedProduct product2 = new WeighedProduct(" Juice  ", description2);
        
        assertAll(
                () -> assertEquals("Cookies", product1.getName()),
                () -> assertEquals(description1, product1.getDescription()),
                () -> assertEquals("Juice", product2.getName()),
                () -> assertEquals(description2, product2.getDescription())
        );
    }
    
    
    @Test
    public void testProductExceptions() {
        try {
            WeighedProduct product1 = new WeighedProduct(null, "Something");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            WeighedProduct product2 = new WeighedProduct("", "Something");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            WeighedProduct product3 = new WeighedProduct("   ", "Something");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
        
        try {
            WeighedProduct product4 = new WeighedProduct("Something", "");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
        
        try {
            WeighedProduct product5 = new WeighedProduct("Something", "");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
        
        try {
            WeighedProduct product6 = new WeighedProduct("Something", "   ");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
    }
    
    
    @Test
    public void testWeighedProductSetters() throws ProductException {
        String description1 = "Pretty little things that gonna make you fat.";
        String description2 = "Not a juice indeed.";
        WeighedProduct product1 = new WeighedProduct("Cookies", description1);
        
        product1.setName("Juice");
        product1.setDescription(description2);
        
        assertAll(
                () -> assertEquals("Juice", product1.getName()),
                () -> assertEquals(description2, product1.getDescription())
        );
    }
    
    
    @Test
    public void testWeighedProductEquals() throws ProductException {
        String name1 = "name1";
        String name2 = "name2";
        String description1 = "description1";
        String description2 = "description2";
        WeighedProduct product1 = new WeighedProduct(name1, description1);
        WeighedProduct product2 = new WeighedProduct(name1, description1);
        WeighedProduct product3 = product1;
        WeighedProduct product4 = new WeighedProduct(name2, description1);
        WeighedProduct product5 = new WeighedProduct(name1, description2);
        Product product6 = new Product(name1, description1);
        
        assertAll(
                () -> assertEquals(product1, product1),
                () -> assertEquals(product1, product2),
                () -> assertEquals(product1, product3),
                () -> assertNotEquals(product1, product4),
                () -> assertNotEquals(product1, product5),
                () -> assertNotEquals(product1, product6)
        );
    }
    
    
    @Test
    public void testWeighedProductToString() throws ProductException {
        assertAll(
                () -> assertEquals("Weighed product «Juice», description: «Not a juice indeed.»",
                        new WeighedProduct("Juice", "Not a juice indeed.").toString()),
                () -> assertEquals("Weighed product «Cookies», description: «Pretty little things that gonna make you fat.»",
                        new WeighedProduct("Cookies", "Pretty little things that gonna make you fat.").toString())
        );
    }
}
