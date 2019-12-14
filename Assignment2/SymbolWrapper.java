public class SymbolWrapper{
    String id;
    String value;
    String type;
    Boolean used;

    public SymbolWrapper() {
        this.id = "";
        this.value = "";
        this.type = "";
        this.used = false;
    }

    public SymbolWrapper(String id, String value, String type) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.used = false;
    }

    public SymbolWrapper(String id, String value, String type, String scope){
        this.id = id;
        this.value = value;
        this.type = type;
        this.used = false;
    }

    public void printSymbolWrapper(){
        System.out.println(this.id + "\t" +
                            this.value + "\t" + 
                            this.type + "\t"
                            );
    }
}