import java.util.Arrays;
import java.util.Objects;


public class PackedPieceProducts implements PackedProduct {
    private Packaging packaging;
    private PieceProduct[] products;
    
    
    public PackedPieceProducts(Packaging packaging, PieceProduct[] products) throws ProductException {
        setPackaging(packaging);
        setProducts(products);
    }
    
    
    private void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }
    
    
    private void setProducts(PieceProduct[] products) {
        this.products = products;
    }
    
    
    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    
    
    public PieceProduct[] getProducts() {
        return products;
    }
    
    
    @Override
    public double getNetMass() {
        double mass = 0;
        
        for (PieceProduct product: products)
            mass += product.getMass();
        
        return mass;
    }
    
    
    @Override
    public double getGrossMass() {
        return getNetMass() + packaging.getMass();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedPieceProducts)) return false;
        PackedPieceProducts that = (PackedPieceProducts) o;
        return packaging.equals(that.packaging) &&
                Arrays.equals(products, that.products);
    }
    
    
    @Override
    public int hashCode() {
        int result = Objects.hash(packaging);
        result = 31 * result + Arrays.hashCode(products);
        return result;
    }
}
