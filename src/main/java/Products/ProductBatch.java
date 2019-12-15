package Products;


import java.util.Arrays;
import java.util.Objects;


public class ProductBatch {
    private String description;
    private Packed[] packeds;
    
    
    public ProductBatch(String description, Packed... packeds) throws ProductException {
        setDescription(description);
        setPackeds(packeds);
    }
    
    
    private void setDescription(String description) throws ProductException {
        if ((description == null) || (description.trim().equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_DESCRIPTION);
        
        this.description = description.trim();
    }
    
    
    private void setPackeds(Packed[] packeds) throws ProductException {
        if ((packeds == null) || (packeds.length == 0))
            throw new ProductException(ProductErrorCode.NULL_PRODUCT);
        
        for (Packed packed: packeds)
            if (packed == null)
                throw new ProductException(ProductErrorCode.NULL_PRODUCT);
        
        this.packeds = packeds;
    }
    
    
    public String getDescription() {
        return description;
    }
    
    
    public Packed[] getPackeds() {
        return packeds;
    }
    
    
    public double getMass() {
        double mass = 0;
        
        for (Packed packed: packeds)
            mass += packed.getGrossMass();
        
        return mass;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductBatch)) return false;
        ProductBatch that = (ProductBatch) o;
        return description.equals(that.description) &&
                Arrays.equals(packeds, that.packeds);
    }
    
    
    @Override
    public int hashCode() {
        int result = Objects.hash(description);
        result = 31 * result + Arrays.hashCode(packeds);
        return result;
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product batch: [");
        
        for (Packed packed: packeds)
            sb.append(packed).append(", ");
        
        sb.append("description: ").append(description).append("]");
        
        return sb.toString();
    }
}
