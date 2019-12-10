public class SymbolWrapper{
    String id;
    String value;
    String type;
    String scope;

    public SymbolWrapper() {
        this.id = "";
        this.value = "";
        this.type = "";
        this.scope = "";
    }

    public SymbolWrapper(String id, String value, String type) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.scope = "global";
    }

    public SymbolWrapper(String id, String value, String type, String scope){
        this.id = id;
        this.value = value;
        this.type = type;
        this.scope = scope;
    }

    public void printSymbolWrapper(){
        System.out.println(this.id + "\t" +
                            this.value + "\t" + 
                            this.type + "\t" +
                            this.scope);
    }
}