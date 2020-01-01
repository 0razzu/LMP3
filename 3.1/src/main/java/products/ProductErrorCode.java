package products;


public enum ProductErrorCode {
    EMPTY_DESCRIPTION("A description must not be null or empty string"),
    EMPTY_NAME("A name must not be null or empty string"),
    NONPOSITIVE_MASS("Mass must not be negative or zero"),
    NONPOSITIVE_QUANTITY("Quantity must not be negative or zero"),
    NULL_PACKAGING("A packaging must not be null"),
    NULL_PACKEDS("Packeds must not be null"),
    NULL_PRODUCT("A product must not be null");
    
    
    private final String errorString;
    
    
    ProductErrorCode(String errorString) {
        this.errorString = errorString;
    }
    
    
    public String getErrorString() {
        return errorString;
    }
}
