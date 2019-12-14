package Products;


import java.util.Arrays;
import java.util.Objects;


public class PackedProducts implements Packed {
    private Packaging packaging;
    private Packed[] packeds;
    
    
    public PackedProducts(Packaging packaging, Packed... packeds) throws ProductException {
        setPackaging(packaging);
        setPackeds(packeds);
    }
    
    
    private void setPackaging(Packaging packaging) throws ProductException {
        if (packaging == null)
            throw new ProductException(ProductErrorCode.NULL_PACKAGING);
        
        this.packaging = packaging;
    }
    
    
    private void setPackeds(Packed[] packeds) throws ProductException {
        if ((packeds == null) || (packeds.length == 0))
            throw new ProductException(ProductErrorCode.NULL_PRODUCT);
        
        for (Packed packed: packeds)
            if (packed == null)
                throw new ProductException(ProductErrorCode.NULL_PRODUCT);
        
        this.packeds = packeds;
    }
    
    
    @Override
    public Packaging getPackaging() {
        return packaging;
    }
    
    
    public Packed[] getPackeds() {
        return packeds;
    }
    
    
    @Override
    public double getNetMass() {
        double mass = 0;
        
        for (Packed packed: packeds)
            mass += packed.getGrossMass();
        
        return mass;
    }
    
    
    @Override
    public double getGrossMass() {
        return getNetMass() + packaging.getMass();
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackedProducts)) return false;
        PackedProducts that = (PackedProducts) o;
        return packaging.equals(that.packaging) &&
                Arrays.equals(packeds, that.packeds);
    }
    
    
    @Override
    public int hashCode() {
        int result = Objects.hash(packaging);
        result = 31 * result + Arrays.hashCode(packeds);
        return result;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Packed products:\n");
    
        for (Packed packed: packeds)
            sb.append("    ").append(packed).append(",\n");
    
        sb.append(packaging);
    
        return sb.toString();
    }
}
