package Model.Statement;
import UserDefinedExceptions.MyException;

public interface IStmt{
    PrgState execute(PrgState state) throws MyException;
//which is the execution method for a statement.
}