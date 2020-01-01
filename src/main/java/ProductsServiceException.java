public class ProductsServiceException extends Exception {
    private final ProductsServiceErrorCode errorCode;
    
    
    public ProductsServiceException(ProductsServiceErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    
    public ProductsServiceErrorCode getErrorCode() {
        return errorCode;
    }
}
