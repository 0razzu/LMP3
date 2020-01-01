package products;


public class ProductException extends Exception {
    private final ProductErrorCode errorCode;
    
    
    public ProductException(ProductErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public ProductErrorCode getErrorCode() {
        return errorCode;
    }
}
