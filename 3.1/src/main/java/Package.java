import java.util.Objects;


public class Package {
    private String name;
    private double weight;
    
    
    public Package(String name, double weight) throws ProductException {
        if ((name == null) || (name.equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_NAME);
        
        if (weight < 0)
            throw new ProductException(ProductErrorCode.NEGATIVE_WEIGHT);
        
        this.name = name;
        this.weight = weight;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public double getWeight() {
        return weight;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Package)) return false;
        Package aPackage = (Package) o;
        return Double.compare(aPackage.weight, weight) == 0 &&
                name.equals(aPackage.name);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
    
    
    @Override
    public String toString() {
        return String.format("Package %s, weight: %f", name, weight);
    }
}
