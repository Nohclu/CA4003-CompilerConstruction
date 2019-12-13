import java.util.*;

public class SymbolTable{
    private Hashtable<String, LinkedList<SymbolWrapper>> symbolTable;
    private Deque<String> undoStack;
    private String marker = " --- ";

    SymbolTable() {
        this.symbolTable = new Hashtable<>();
        this.undoStack = new ArrayDeque<>();
    }

    public void openScope() {
        undoStack.addFirst(marker);
        System.out.println("Scope Opened...");
    }

    public void addSymbol(SymbolWrapper sw) {
        this.undoStack.addFirst(sw.id);
        if (!this.symbolTable.containsKey(sw.id)) {
            LinkedList<SymbolWrapper> ll = new LinkedList<SymbolWrapper>();
            ll.addFirst(sw);
            this.symbolTable.put(sw.id, ll);
        } else {
            LinkedList<SymbolWrapper> ll = symbolTable.get(sw.id);
            SymbolWrapper sym = getSymbol(sw.id);
            if(sym != null){
                ll.addFirst(ll.remove(ll.indexOf(sym)));
            } else {
                ll.addFirst(sw);
            }
        }
        System.out.println("--------------------------------ADDED SYMBOL(S)--------------------------------");
        printSymbolTable();
    }

    public void closeScope() {
        while (this.undoStack.peekFirst() != marker) {
            String removedSymbol = this.undoStack.removeFirst();
            SymbolWrapper sw = getSymbol(removedSymbol);
            if (sw != null) {
                LinkedList<SymbolWrapper> ll = symbolTable.get(sw.id);
                ll.remove(sw);
            }
        }
        this.undoStack.removeFirst();
        System.out.println("--------------------------------REMOVE SYMBOL(S)--------------------------------");
        printSymbolTable();
    }

    public SymbolWrapper getSymbol(String symbol) {
        if (this.symbolTable.containsKey(symbol)) {
            LinkedList<SymbolWrapper> ll = this.symbolTable.get(symbol);
            for(SymbolWrapper s: ll){
                if (s.id == symbol) {
                    return s;
                }
            }
        }
        return null;
    }

    public boolean searchScope(String symbol) {
        Iterator<String> it = this.undoStack.iterator();
        while (it.hasNext() && it.next() != marker){
            if (it.next() == symbol){
                return true;
            }
        }
        return false;
    }

    private void printSymbolTable(){
        System.out.println("SymbolTableKeys");
        symbolTable.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());  
         });
        System.out.println("\nUndoStack");
        for(Iterator<String> itr = undoStack.iterator(); itr.hasNext();) 
        { 
            System.out.println(itr.next()); 
        }
        System.out.println("\n\n");
    }
}
