package products;


import java.util.Objects;


public class PackedWeighedProduct extends WeighedProduct implements Packed {
    private static final double EPS = 1E-6;
    private Packaging packaging;
    private double mass;
    
    
    public PackedWeighedProduct(WeighedProduct product, double mass, Packaging packaging) throws ProductException {
        super(product);
        setMass(mass);
        setPackaging(packaging);
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
        if (!super.equals(o)) return false;
        PackedWeighedProduct that = (PackedWeighedProduct) o;
        return Math.abs(that.mass - mass) < EPS &&
                packaging.equals(that.packaging);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), packaging, mass);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packed weighed product {%s, mass: %.3f kg, %s}",
                super.toString(), mass, packaging);
    }
}
