public enum ProductErrorCode {
    EMPTY_DESCRIPTION("A description must not be null or empty string"),
    EMPTY_NAME("A name must not be null or empty string"),
    NONPOSITIVE_WEIGHT("Weight must not be negative or zero");
    
    
    private String errorString;
    
    
    private ProductErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
