package Model.Values;

import Model.Types.IntType;
import Model.Types.Type;

public class IntValue implements Value {
    private int val;

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public IntValue() {}

    public IntValue(int v) {
        val = v;
    }

    public String toString() {
        return Integer.toString(val);
    }

    public Type getType() {
        return new IntType();
    }
}