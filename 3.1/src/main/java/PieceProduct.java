public class PieceProduct extends Product {
    private static final double EPS = 10E-6;
    private double mass;
    
    
    public PieceProduct(String name, String description, double mass) throws ProductException {
        super(name, description);
        
        setMass(mass);
    }
    
    
    public void setMass(double mass) throws ProductException {
        if (mass < EPS)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_MASS);
    
        this.mass = mass;
    }
    
    
    public double getMass() {
        return mass;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceProduct)) return false;
        PieceProduct pieceProduct = (PieceProduct) o;
        return name.equals(pieceProduct.name) &&
                description.equals(pieceProduct.description) &&
                Math.abs(pieceProduct.mass - mass) <= EPS;
    }
    
    
    @Override
    public String toString() {
        return String.format("Piece product «%s», description: «%s», piece mass: %.3f kg", name, description, mass);
    }
}
