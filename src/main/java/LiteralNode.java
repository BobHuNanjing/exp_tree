import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    public String getNodeElement() {
        return getAtom();
    }
}
