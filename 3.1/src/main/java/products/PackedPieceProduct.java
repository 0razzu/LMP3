package products;


import java.util.Objects;


public class PackedPieceProduct extends PieceProduct implements Packed {
    private int quantity;
    private Packaging packaging;
    
    
    public PackedPieceProduct(PieceProduct product, int quantity, Packaging packaging) throws ProductException {
        super(product);
        setQuantity(quantity);
        setPackaging(packaging);
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
    
    
    public int getQuantity() {
        return quantity;
    }
    
    
    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    
    
    @Override
    public double getNetMass() {
        return quantity * getMass();
    }
    
    
    @Override
    public double getGrossMass() {
        return getNetMass() + packaging.getMass();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedPieceProduct)) return false;
        if (!super.equals(o)) return false;
        PackedPieceProduct that = (PackedPieceProduct) o;
        return quantity == that.quantity &&
                packaging.equals(that.packaging);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, packaging);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packed piece product {%s, quantity: %d, %s}",
                super.toString(), quantity, packaging);
    }
}
