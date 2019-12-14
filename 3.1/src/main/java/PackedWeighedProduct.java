import java.util.Objects;


public class PackedWeighedProduct {
    private static final double EPS = 10E-6;
    private Package pack;
    private WeighedProduct product;
    private double productWeight;
    
    
    public PackedWeighedProduct(Package pack, WeighedProduct product, double productWeight) throws ProductException {
        setPackage(pack);
        setProduct(product);
        setProductWeight(productWeight);
    }
    
    
    private void setPackage(Package pack) throws ProductException {
        if (pack == null)
            throw new ProductException(ProductErrorCode.NULL_PACKAGE);
        
        this.pack = pack;
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
    
    
    public Package getPackage() {
        return pack;
    }
    
    
    public WeighedProduct getProduct() {
        return product;
    }
    
    
    public double getNetMass() {
        return productWeight;
    }
    
    
    public double getGrossMass() {
        return productWeight + pack.getWeight();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedWeighedProduct)) return false;
        PackedWeighedProduct that = (PackedWeighedProduct) o;
        return Math.abs(that.productWeight - productWeight) < EPS &&
                pack.equals(that.pack) &&
                product.equals(that.product);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(pack, product, productWeight);
    }
}
