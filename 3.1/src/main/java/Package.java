import java.util.Objects;


public class Package {
    private String name;
    private double weight;
    private static final double EPS = 10E-6;
    
    
    public Package(String name, double weight) throws ProductException {
        if ((name == null) || (name.trim().equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_NAME);
        
        if (weight <= EPS)
            throw new ProductException(ProductErrorCode.NEGATIVE_WEIGHT);
        
        this.name = name.trim();
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
        return Math.abs(aPackage.weight - weight) <= EPS &&
                name.equals(aPackage.name);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
    
    
    @Override
    public String toString() {
        return String.format("Package %s, weight: %.3f kg", name, weight);
    }
}
