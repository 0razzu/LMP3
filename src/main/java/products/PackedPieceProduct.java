package products;


import java.util.Objects;


public class PackedPieceProduct implements Packed {
    private PieceProduct product;
    private int quantity;
    private Packaging packaging;
    
    
    public PackedPieceProduct(PieceProduct product, int quantity, Packaging packaging) throws ProductException {
        setProduct(product);
        setQuantity(quantity);
        setPackaging(packaging);
    }
    
    
    private void setProduct(PieceProduct product) throws ProductException {
        if (product == null)
            throw new ProductException(ProductErrorCode.NULL_PRODUCT);
        
        this.product = product;
    }
    
    
    private void setQuantity(int quantity) throws ProductException {
        if (quantity <= 0)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_QUANTITY);
        
        this.quantity = quantity;
    }
    
    
    private void setPackaging(Packaging packaging) throws ProductException {
        if (packaging == null)
            throw new ProductException(ProductErrorCode.NULL_PACKAGING);
        
        this.packaging = packaging;
    }
    
    
    public PieceProduct getProduct() {
        return product;
    }
    
    
    public int getQuantity() {
        return quantity;
    }
    
    
    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    
    
    @Override
    public double getNetMass() {
        return quantity * product.getMass();
    }
    
    
    @Override
    public double getGrossMass() {
        return getNetMass() + packaging.getMass();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedPieceProduct)) return false;
        PackedPieceProduct that = (PackedPieceProduct) o;
        return quantity == that.quantity &&
                product.equals(that.product) &&
                packaging.equals(that.packaging);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, packaging);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packed piece product {%s, quantity: %d, %s}",
                product, quantity, packaging);
    }
}
