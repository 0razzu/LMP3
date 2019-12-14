import java.util.Objects;


public class Packaging {
    private static final double EPS = 10E-6;
    private String name;
    private double weight;
    
    
    public Packaging(String name, double weight) throws ProductException {
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
        if (!(o instanceof Packaging)) return false;
        Packaging aPackaging = (Packaging) o;
        return Math.abs(aPackaging.weight - weight) <= EPS &&
                name.equals(aPackaging.name);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packaging «%s», weight: %.3f kg", name, weight);
    }
}
