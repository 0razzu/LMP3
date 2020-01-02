package products;


public interface Packed {
    Packaging getPackaging();
    
    int getNetMass();
    int getGrossMass();
}
