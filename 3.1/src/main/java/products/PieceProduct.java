package products;


public class PieceProduct extends Product {
    protected int mass;
    
    
    public PieceProduct(String name, String description, int mass) throws ProductException {
        super(name, description);
        
        setMass(mass);
    }
    
    
    public PieceProduct(PieceProduct product) throws ProductException {
        super(product);
        
        setMass(product.mass);
    }
    
    
    public void setMass(int mass) throws ProductException {
        if (mass <= 0)
            throw new ProductException(ProductErrorCode.NONPOSITIVE_MASS);
        
        this.mass = mass;
    }
    
    
    public int getMass() {
        return mass;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceProduct)) return false;
        PieceProduct pieceProduct = (PieceProduct) o;
        return name.equals(pieceProduct.name) &&
                description.equals(pieceProduct.description) &&
                pieceProduct.mass == mass;
    }
    
    
    @Override
    public String toString() {
        return String.format("Piece product {“%s”, description: “%s”, mass: %d.%03d kg}",
                name, description, mass / 1000, mass % 1000);
    }
}
