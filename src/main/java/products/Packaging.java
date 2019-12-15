package products;


import java.util.Objects;


public class Packaging {
    private static final double EPS = 10E-6;
    private String name;
    private double mass;
    
    
    public Packaging(String name, double mass) throws ProductException {
        setName(name);
        setMass(mass);
    }
    
    
    public void setName(String name) throws ProductException {
        if ((name == null) || (name.trim().equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_NAME);
        
        this.name = name.trim();
    }
    
    
    public void setMass(double mass) throws ProductException {
        if (mass <= EPS)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_MASS);
        
        this.mass = mass;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public double getMass() {
        return mass;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Packaging)) return false;
        Packaging aPackaging = (Packaging) o;
        return Math.abs(aPackaging.mass - mass) <= EPS &&
                name.equals(aPackaging.name);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, mass);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packaging {«%s», mass: %.3f kg}", name, mass);
    }
}
