package filters;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class OnlySpecifiedSymbolsFilter implements Filter {
    Set<Character> symbols;
    
    
    public OnlySpecifiedSymbolsFilter(Set<Character> symbols) {
        if (symbols == null)
            this.symbols = new HashSet<>();
        
        else
            this.symbols = symbols;
    }
    
    
    public OnlySpecifiedSymbolsFilter(String symbolsStr) {
        if (symbolsStr == null)
            symbols = new HashSet<>();
        
        else {
            symbols = new HashSet<>();
    
            for (int i = 0; i < symbolsStr.length(); i++)
                symbols.add(symbolsStr.charAt(i));
        }
    }
    
    
    @Override
    public boolean apply(String str) {
        if (str == null)
            return true;
    
        for (int i = 0; i < str.length(); i++) {
            if (!symbols.contains(str.charAt(i)))
                return false;
        }
        
        return true;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OnlySpecifiedSymbolsFilter)) return false;
        OnlySpecifiedSymbolsFilter that = (OnlySpecifiedSymbolsFilter) o;
        return symbols.equals(that.symbols);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(symbols);
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OnlySpecifiedSymbolsFilter {");
        
        if (!symbols.isEmpty()) {
            for (Character c: symbols)
                sb.append(c).append(", ");
    
            sb.delete(sb.length() - 2, sb.length());
        }
        
        sb.append("}");
        
        return sb.toString();
    }
}
