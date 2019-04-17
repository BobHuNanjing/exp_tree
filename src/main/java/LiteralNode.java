public class LiteralNode extends Node {
    String atom = null;

    public LiteralNode(String atom){
        this.atom = atom;
    }

    public String getAtom() {
        return atom;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    public String getNodeElement() {
        return getAtom();
    }

    @Override
    public String toString() {
        return "LiteralNode [atom="
                + atom
                + ", nodeid="
                + getId();
    }
}
