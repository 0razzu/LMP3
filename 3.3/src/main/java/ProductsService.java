import filters.Filter;
import products.*;


public class ProductsService {
    public static int countByFilter(ProductBatch batch, Filter filter) throws ProductsServiceException {
        if (batch == null)
            throw new ProductsServiceException(ProductsServiceErrorCode.NULL_BATCH);
        
        if (filter == null)
            throw new ProductsServiceException(ProductsServiceErrorCode.NULL_FILTER);
        
        int counter = 0;
        
        for (Packed packed: batch.getPackeds())
            if (packed instanceof Product)
                if (filter.apply(((Product) packed).getName()))
                    counter++;
        
        return counter;
    }
    
    
    public static int countByFilterDeep(ProductBatch batch, Filter filter) throws ProductsServiceException, ProductException {
        if (batch == null)
            throw new ProductsServiceException(ProductsServiceErrorCode.NULL_BATCH);
    
        if (filter == null)
            throw new ProductsServiceException(ProductsServiceErrorCode.NULL_FILTER);
    
        int counter = 0;
    
        for (Packed packed: batch.getPackeds()) {
            if (packed instanceof Product) {
                if (filter.apply(((Product) packed).getName()))
                    counter++;
            }
    
            else if (packed instanceof PackedProducts)
                counter += countByFilterDeep(new ProductBatch("description", ((PackedProducts) packed).getPackeds()), filter);
        }
    
        return counter;
    }
    
    
    public static boolean checkAllWeighted(ProductBatch batch) throws ProductsServiceException, ProductException {
        if (batch == null)
            throw new ProductsServiceException(ProductsServiceErrorCode.NULL_BATCH);
    
        for (Packed packed: batch.getPackeds()) {
            if (packed instanceof PackedProducts)
                checkAllWeighted(new ProductBatch("description", ((PackedProducts) packed).getPackeds()));
                
            else if (!(packed instanceof PackedWeighedProduct)) {
                return false;
            }
        }
        
        return true;
    }
}
