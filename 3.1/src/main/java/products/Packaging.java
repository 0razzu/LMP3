package products;


import java.util.Objects;


public class Packaging {
    private String name;
    private int mass;
    
    
    public Packaging(String name, int mass) throws ProductException {
        setName(name);
        setMass(mass);
    }
    
    
    public void setName(String name) throws ProductException {
        if ((name == null) || (name.trim().equals("")))
            throw new ProductException(ProductErrorCode.EMPTY_NAME);
        
        this.name = name.trim();
    }
    
    
    public void setMass(int mass) throws ProductException {
        if (mass <= 0)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_MASS);
        
        this.mass = mass;
    }
    
    
    public String getName() {
        return name;
    }
    
    
    public int getMass() {
        return mass;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Packaging)) return false;
        Packaging that = (Packaging) o;
        return that.mass == mass &&
                name.equals(that.name);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(name, mass);
    }
    
    
    @Override
    public String toString() {
        return String.format("Packaging {“%s”, mass: %d.%03d kg}", name, mass / 1000, mass % 1000);
    }
}
