public class ProductException extends Exception {
    public ProductErrorCode errorCode;
    
    
    public ProductException(ProductErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public ProductErrorCode getErrorCode() {
        return errorCode;
    }
}
