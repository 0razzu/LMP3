package products;


import java.util.Objects;


public class PackedWeighedProduct implements Packed {
    private static final double EPS = 10E-6;
    private Packaging packaging;
    private WeighedProduct product;
    private double mass;
    
    
    public PackedWeighedProduct(WeighedProduct product, double mass, Packaging packaging) throws ProductException {
        setProduct(product);
        setMass(mass);
        setPackaging(packaging);
    }
    
    
    private void setProduct(WeighedProduct product) throws ProductException {
        if (product == null)
            throw new ProductException(ProductErrorCode.NULL_PRODUCT);
        
        this.product = product;
    }
    
    
    private void setMass(double mass) throws ProductException {
        if (mass < EPS)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_MASS);
        
        this.mass = mass;
    }
    
    
    private void setPackaging(Packaging packaging) throws ProductException {
        if (packaging == null)
            throw new ProductException(ProductErrorCode.NULL_PACKAGING);
        
        this.packaging = packaging;
    }
    
    
    public WeighedProduct getProduct() {
        return product;
    }
    
    
    @Override
    public double getNetMass() {
        return mass;
    }
    
    
    @Override
    public double getGrossMass() {
        return mass + packaging.getMass();
    }
    
    
    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedWeighedProduct)) return false;
        PackedWeighedProduct that = (PackedWeighedProduct) o;
        return Double.compare(that.mass, mass) == 0 &&
                packaging.equals(that.packaging) &&
                product.equals(that.product);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(packaging, product, mass);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packed weighed product {%s, mass: %.3f kg, %s}",
                product, mass, packaging);
    }
}
