public class LiteralNode extends Node {
    String atom = null;
    public LiteralNode(String atom){
        this.atom = atom;
    }

    public String getAtom() {
        return atom;
    }

}
