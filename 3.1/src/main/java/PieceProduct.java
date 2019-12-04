public class PieceProduct extends Product {
    private static final double EPS = 10E-6;
    private double weight;
    
    
    public PieceProduct(String name, String description, double weight) throws ProductException {
        super(name, description);
        
        setWeight(weight);
    }
    
    
    public void setWeight(double weight) throws ProductException {
        if (weight <= EPS)
            throw new ProductException(ProductErrorCode.NEGATIVE_WEIGHT);
    
        this.weight = weight;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PieceProduct)) return false;
        PieceProduct pieceProduct = (PieceProduct) o;
        return name.equals(pieceProduct.name) &&
                description.equals(pieceProduct.description) &&
                Math.abs(pieceProduct.weight - weight) <= EPS;
    }
    
    
    @Override
    public String toString() {
        return String.format("Piece product %s, description: %s", name, description);
    }
}
