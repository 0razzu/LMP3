package products;


import java.util.Objects;


public class PackedWeighedProduct extends WeighedProduct implements Packed {
    private Packaging packaging;
    private int mass;
    
    
    public PackedWeighedProduct(WeighedProduct product, int mass, Packaging packaging) throws ProductException {
        super(product);
        setMass(mass);
        setPackaging(packaging);
    }
    
    
    private void setMass(int mass) throws ProductException {
        if (mass <= 0)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_MASS);
        
        this.mass = mass;
    }
    
    
    private void setPackaging(Packaging packaging) throws ProductException {
        if (packaging == null)
            throw new ProductException(ProductErrorCode.NULL_PACKAGING);
        
        this.packaging = packaging;
    }
    
    
    @Override
    public int getNetMass() {
        return mass;
    }
    
    
    @Override
    public int getGrossMass() {
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
        if (!super.equals(o)) return false;
        PackedWeighedProduct that = (PackedWeighedProduct) o;
        return that.mass == mass &&
                packaging.equals(that.packaging);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), packaging, mass);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packed weighed product {%s, mass: %d.%03d kg, %s}",
                super.toString(), mass / 1000, mass % 1000, packaging);
    }
}
