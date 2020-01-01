public enum ProductsServiceErrorCode {
    NULL_BATCH("A batch must not be null"),
    NULL_FILTER("A filter must not be null");
    
    
    private final String errorString;
    
    
    ProductsServiceErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
