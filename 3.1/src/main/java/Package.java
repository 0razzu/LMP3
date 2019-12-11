import java.util.Objects;


public class Package {
    private static final double EPS = 10E-6;
    private String name;
    private double weight;
    
    
    public Package(String name, double weight) throws ProductException {
        setName(name);
        setWeight(weight);
    }
    
    
    public void setName(String name) throws ProductException {
        if ((name == null) || (name.trim().equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_NAME);
        
        this.name = name.trim();
    }
    
    
    public void setWeight(double weight) throws ProductException {
        if (weight <= EPS)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_WEIGHT);
        
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
        return String.format("Package «%s», weight: %.3f kg", name, weight);
    }
}
