package filters;


import java.util.Objects;


public class BeginStringFilter implements Filter {
    private String pattern;
    
    
    public BeginStringFilter(String pattern) {
        this.pattern = pattern;
    }
    
    
    @Override
    public boolean apply(String str) {
        if ((pattern == null) && (str == null))
            return true;
    
        return (str != null) && (pattern != null) && (str.startsWith(pattern));
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeginStringFilter)) return false;
        BeginStringFilter that = (BeginStringFilter) o;
        return Objects.equals(pattern, that.pattern);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(pattern);
    }
    
    
    @Override
    public String toString() {
        return String.format("BeginStringFilter {pattern: %s}", (pattern == null? null : "“" + pattern + "”"));
    }
}
