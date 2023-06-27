import java.util.LinkedList;

public class Program extends LinkedList<Instruction> implements Context{

    void run(){
        forEach(i->i.run(this));
    }

    @Override
    public void setVariable(String name, Integer value) {

    }

    @Override
    public Integer getVariable(String name) {
        return null;
    }
}
