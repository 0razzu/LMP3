package filters;


import java.util.Objects;


public class EndStringFilter implements Filter {
    private final String pattern;
    
    
    public EndStringFilter(String pattern) {
        this.pattern = pattern;
    }
    
    
    @Override
    public boolean apply(String str) {
        if ((pattern == null) && (str == null))
            return true;
    
        return (str != null) && (pattern != null) && (str.endsWith(pattern));
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndStringFilter)) return false;
        EndStringFilter that = (EndStringFilter) o;
        return Objects.equals(pattern, that.pattern);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(pattern);
    }
    
    
    @Override
    public String toString() {
        return String.format("EndStringFilter {pattern: %s}", (pattern == null? null : "“" + pattern + "”"));
    }
}
