import java.util.*;

public class SymbolTable {
    private Hashtable<String, LinkedList<SymbolWrapper>> symbolTable;
    private Deque<String> undoStack;
    private String marker = " ";

    SymbolTable() {
        this.symbolTable = new Hashtable<>();
        this.undoStack = new ArrayDeque();
    }

    public void openScope() {
        undoStack.addFirst(marker);
    }

    public void addSymbol(SymbolWrapper sw) {
        undoStack.addFirst(sw.id);
        if (!symbolTable.containsKey(sw.id)) {
            LinkedList<SymbolWrapper> ll = new LinkedList<SymbolWrapper>();
            ll.addFirst(sw);
            symbolTable.put(sw.id, ll);
        } else {
            LinkedList<SymbolWrapper> ll = symbolTable.get(sw.id);
            SymbolWrapper sym = getSymbol(sw.id);
            if(sym != null){
                ll.addFirst(ll.remove(ll.indexOf(sym)));
            } else {
                ll.addFirst(sw);
            }
        }
        printSymbolTable();
    }

    public void closeScope() {
        while (undoStack.peekFirst() != marker) {
            String removedSymbol = undoStack.removeFirst();
            SymbolWrapper sw = getSymbol(removedSymbol);
            if (sw != null) {
                LinkedList<SymbolWrapper> ll = symbolTable.get(sw.id);
                ll.remove(sw);
            }
        }
        undoStack.removeFirst();
    }

    private SymbolWrapper getSymbol(String symbol) {
        if (symbolTable.containsKey(symbol)) {
            LinkedList<SymbolWrapper> ll = symbolTable.get(symbol);
            for(SymbolWrapper s: ll){
                if (s.id == symbol) {
                    return s;
                }
            }
        }
        return null;
    }

    private void printSymbolTable(){
        System.out.println("SymbolTableKeys");
        symbolTable.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());  
         });
        System.out.println("\nUndoStack");
        for(Iterator itr = undoStack.iterator(); itr.hasNext();) 
        { 
            System.out.println(itr.next()); 
        }
        System.out.println("------------------------------- \n\n\n");
    }
}
