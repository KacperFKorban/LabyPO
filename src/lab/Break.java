package lab;

public class Break {
    private BasicTerm term;
    public Break(BasicTerm term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "Przerwa: " + term.toString();
    }

    public BasicTerm getTerm() {
        return term;
    }
}
