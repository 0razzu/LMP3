import java.util.Objects;


public class PackedWeighedProduct implements PackedProduct {
    private static final double EPS = 10E-6;
    private Packaging packaging;
    private WeighedProduct product;
    private double productWeight;
    
    
    public PackedWeighedProduct(Packaging packaging, WeighedProduct product, double productWeight) throws ProductException {
        setPackaging(packaging);
        setProduct(product);
        setProductWeight(productWeight);
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
    
    
    private void setProductWeight(double productWeight) throws ProductException {
        if (productWeight < EPS)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_WEIGHT);
        
        this.productWeight = productWeight;
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
        return productWeight;
    }
    
    
    @Override
    public double getGrossMass() {
        return productWeight + packaging.getWeight();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedWeighedProduct)) return false;
        PackedWeighedProduct that = (PackedWeighedProduct) o;
        return Math.abs(that.productWeight - productWeight) < EPS &&
                packaging.equals(that.packaging) &&
                product.equals(that.product);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(packaging, product, productWeight);
    }
}
