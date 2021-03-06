package Model.Statements;

import Model.DataStructures.MyHeap;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIHeap;
import Model.DataStructures.MyIStack;
import Model.Expressions.Exp;
import Model.ProgramState.PrgState;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import UserDefinedExceptions.MyException;
import com.sun.jdi.IntegerValue;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt() {
    }

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }


    public String toString() {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getExeStack();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Value> hp = state.getHeap();
        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl, hp);
            Type typId = (symTbl.lookup(id)).getType();
            if ((val.getType()).equals(typId)) {
                symTbl.update(id, val);
            }
            else throw new MyException("the type of variable" + id + " and type of the assigned expression do not match");
        }

        else throw new MyException("the used variable" + id + " was not declared before");

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(this.id, this.exp);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type type_var = typeEnv.lookup(id);
        Type type_exp = exp.typeCheck(typeEnv);
        if(type_var.equals(type_exp)) {
            return typeEnv;
        } else throw new MyException("the type of variable" + id + " and type of the assigned expression do not match");
    }
}
