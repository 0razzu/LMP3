package Products;


import java.util.Objects;


public class Product {
    protected String name;
    protected String description;
    
    
    public Product(String name, String description) throws ProductException {
        setName(name);
        setDescription(description);
    }
    
    
    protected void setName(String name) throws ProductException {
        if ((name == null) || (name.trim().equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_NAME);
    
        this.name = name.trim();
    }
    
    
    protected void setDescription(String description) throws ProductException {
        if ((description == null) || (description.trim().equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_DESCRIPTION);
    
        this.description = description.trim();
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public String getDescription() {
        return description;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                description.equals(product.description);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
    
    
    @Override
    public String toString() {
        return String.format("Product {«%s», description: «%s»}", name, description);
    }
}
