import java.util.Objects;


public class PackedWeighedProduct implements PackedProduct {
    private static final double EPS = 10E-6;
    private Packaging packaging;
    private WeighedProduct product;
    private double productMass;
    
    
    public PackedWeighedProduct(Packaging packaging, WeighedProduct product, double productMass) throws ProductException {
        setPackaging(packaging);
        setProduct(product);
        setProductMass(productMass);
    }
    
    
    private void setPackaging(Packaging packaging) throws ProductException {
        if (packaging == null)
            throw new ProductException(ProductErrorCode.NULL_PACKAGING);
        
        this.packaging = packaging;
    }
    
    
    private void setProduct(WeighedProduct product) throws ProductException {
        if (product == null)
            throw new ProductException(ProductErrorCode.NULL_PRODUCT);
        
        this.product = product;
    }
    
    
    private void setProductMass(double productMass) throws ProductException {
        if (productMass < EPS)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_MASS);
        
        this.productMass = productMass;
    }
    
    
    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    
    
    public WeighedProduct getProduct() {
        return product;
    }
    
    
    @Override
    public double getNetMass() {
        return productMass;
    }
    
    
    @Override
    public double getGrossMass() {
        return productMass + packaging.getMass();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedWeighedProduct)) return false;
        PackedWeighedProduct that = (PackedWeighedProduct) o;
        return Math.abs(that.productMass - productMass) < EPS &&
                packaging.equals(that.packaging) &&
                product.equals(that.product);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(packaging, product, productMass);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packed weighed product «%s» in «%s», description: «%s», net mass: %.3f kg",
                product.name, packaging.getName(), product.description, productMass);
    }
}
