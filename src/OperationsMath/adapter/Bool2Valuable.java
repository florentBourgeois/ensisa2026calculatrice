package OperationsMath.adapter;


import OperationsMath.Element;
import OperationsMath.visitor.VisitorOperation;

public class Bool2Valuable implements Element {

    private MyBoolean bool;

    public Bool2Valuable(MyBoolean bool) {
        this.bool = bool;
    }

    public int getValue() {
        if (bool.isTrue())
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "Bool2Valuable{" +
                "bool=" + bool +
                '}';
    }


    @Override
    public int eval() {
        return this.getValue();
    }

    @Override
    public void accept(VisitorOperation visitor) {
        visitor.visitBool2Valuable(this);
    }
}
