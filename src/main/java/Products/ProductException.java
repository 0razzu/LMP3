package Products;


public class ProductException extends Exception {
    private ProductErrorCode errorCode;
    
    
    public ProductException(ProductErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public ProductErrorCode getErrorCode() {
        return errorCode;
    }
}
