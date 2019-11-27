public enum ProductErrorCode {
    EMPTY_NAME("A name must not be null or empty string"),
    NEGATIVE_WEIGHT("Weight must not be negative");
    
    
    private String errorString;
    
    
    private ProductErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
