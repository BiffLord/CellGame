package net.biff.molecules;

public class Stack {
    private final Macromolecule molecule;
    private long count;

    public Stack(Macromolecule molecule, long count) {
        this.molecule = molecule;
        this.count = count;
    }
    public Macromolecule getMolecule(){
        return molecule;
    }
    public long getCount(){
        return count;
    }
    public void setCount(long stackSize){
        count = stackSize;
    }

    @Override
    public String toString(){
        return molecule.name()+": "+count;
    }
}
