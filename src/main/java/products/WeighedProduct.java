package products;


public class WeighedProduct extends Product {
    public WeighedProduct(String name, String description) throws ProductException {
        super(name, description);
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WeighedProduct)) return false;
        WeighedProduct weighedProduct = (WeighedProduct) o;
        return name.equals(weighedProduct.name) &&
                description.equals(weighedProduct.description);
    }
    
    
    @Override
    public String toString() {
        return String.format("Weighed product {“%s”, description: “%s”}", name, description);
    }
}
