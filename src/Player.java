public class Player {
    private char xo;
    private String identity;
    public Player(String identity, char xo){
        this.xo = xo;
        this.identity = identity;
    }

    public char getXo(){
        return this.xo;
    }
    public String getIdentity(){
        return this.identity;
    }
}
