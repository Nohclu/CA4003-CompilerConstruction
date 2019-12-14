import java.util.*;

public class SymbolTable {
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
        System.out.println("--------------ADDED SYMBOL(S)--------------");
        printSymbolTable();
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
            if (sym != null) {
                ll.addFirst(ll.remove(ll.indexOf(sym)));
            } else {
                ll.addFirst(sw);
            }
        }
    }

    public void closeScope() {
        System.out.println("Scope Closing...");
        System.out.println("--------------Current SYMBOL(S)--------------");
        printSymbolTable();
        while (this.undoStack.peekFirst() != marker && !this.undoStack.isEmpty()) {
            System.out.println(this.undoStack.peekFirst());
            String removedSymbol = this.undoStack.removeFirst();
            SymbolWrapper sw = getSymbol(removedSymbol);
            if (sw != null) {
                if (sw.used == false){
                System.out.println(String.format("WARNING: %s was declared but never used", sw.id));
                }
                LinkedList<SymbolWrapper> ll = symbolTable.get(sw.id);
                ll.remove(sw);
            }
        }
        if (!this.undoStack.isEmpty()) {
            this.undoStack.removeFirst();
        }
        System.out.println("Scope Closed...");
        System.out.println("--------------REMOVE SYMBOL(S)--------------");
        printSymbolTable();
    }

    public SymbolWrapper getSymbol(String symbol) {
        System.out.println("Looking for: " + symbol);
        if (this.symbolTable.containsKey(symbol)) {
            LinkedList<SymbolWrapper> ll = this.symbolTable.get(symbol);
            for (SymbolWrapper s : ll) {
                if (s.id.equals(symbol)) {
                    return s;
                }
            }
        }
        return null;
    }

    public boolean searchScope(String symbol) {
        // Iterator<String> it = this.undoStack.iterator();
        System.out.println("Searching scope...");
        try {

            for (Iterator itr = undoStack.iterator(); itr.hasNext();) {
                if (itr.next().toString().equals(marker)) {
                    return false;
                }
                if (itr.next().toString().equals(symbol)) {
                    System.out.println("Found symbol...");
                    return true;
                }
            }
            System.out.println("Nothing found...");
            return false;
        } catch (NoSuchElementException e) {
            System.out.println("Element doesn't exist");
        }
        return false;
    }

    public void printSymbolTable() {
        System.out.println("SymbolTableKeys");
        symbolTable.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " " + entry.getValue());
            for (SymbolWrapper sw : entry.getValue()) {
                System.out.println(sw.id + " " + sw.type + " " + sw.value);
            }
        });
        System.out.println("\nUndoStack");
        for (Iterator<String> itr = undoStack.iterator(); itr.hasNext();) {
            System.out.println(itr.next());
        }
        System.out.println("\n\n");
    }
}
