import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestProduct {
    @Test
    public void testProduct() throws ProductException {
        String description1 = "The best laptop in the world.";
        String description2 = "This robot can get rid of you someday. No doubt you need it.";
        Product product1 = new Product("Laptop 3", description1);
        Product product2 = new Product("  Robot cleaner   ", description2);
        
        assertAll(
                () -> assertEquals("Laptop 3", product1.getName()),
                () -> assertEquals(description1, product1.getDescription()),
                () -> assertEquals("Robot cleaner", product2.getName()),
                () -> assertEquals(description2, product2.getDescription())
        );
    }
    
    
    @Test
    public void testProductExceptions() {
        try {
            Product product1 = new Product(null, "Something");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            Product product2 = new Product("", "Something");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            Product product3 = new Product("   ", "Something");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_NAME, e.getErrorCode());
        }
    
        try {
            Product product4 = new Product("Something", "");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
    
        try {
            Product product5 = new Product("Something", "");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
    
        try {
            Product product6 = new Product("Something", "   ");
            fail();
        } catch (ProductException e) {
            assertEquals(ProductErrorCode.EMPTY_DESCRIPTION, e.getErrorCode());
        }
    }
    
    
    @Test
    public void testProductSetters() throws ProductException {
        String description1 = "The best laptop in the world.";
        String description2 = "This robot can get rid of you someday. No doubt you need it.";
        Product product1 = new Product("Laptop 3", description1);
        
        product1.setName("Robot cleaner");
        product1.setDescription(description2);
        
        assertAll(
                () -> assertEquals("Robot cleaner", product1.getName()),
                () -> assertEquals(description2, product1.getDescription())
        );
    }
    
    
    @Test
    public void testProductEquals() throws ProductException {
        String name1 = "name1";
        String name2 = "name2";
        String description1 = "description1";
        String description2 = "description2";
        Product product1 = new Product(name1, description1);
        Product product2 = new Product(name1, description1);
        Product product3 = product1;
        Product product4 = new Product(name2, description1);
        Product product5 = new Product(name1, description2);
        
        assertAll(
                () -> assertEquals(product1, product1),
                () -> assertEquals(product1, product2),
                () -> assertEquals(product1, product3),
                () -> assertNotEquals(product1, product4),
                () -> assertNotEquals(product1, product5)
        );
    }
    
    
    @Test
    public void testProductToString() throws ProductException {
        assertAll(
                () -> assertEquals("Product «Juice», description: «Not a juice indeed.»",
                        new Product("Juice", "Not a juice indeed.").toString()),
                () -> assertEquals("Product «Washer», description: «Gonna set a flood.»",
                        new Product("Washer", "Gonna set a flood.").toString())
        );
    }
}
