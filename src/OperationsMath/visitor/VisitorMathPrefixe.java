package OperationsMath.visitor;

import OperationsMath.Value;
import OperationsMath.adapter.Bool2Valuable;
import OperationsMath.composite.Div;
import OperationsMath.composite.Moins;
import OperationsMath.composite.Mult;
import OperationsMath.composite.Plus;

public class VisitorMathPrefixe implements VisitorOperation{

    private StringBuffer result;

    public VisitorMathPrefixe(){
        this.result = new StringBuffer();
    }

    public void reset() {
        this.result = new StringBuffer();
    }

    public String getResult() {
        return this.result.toString();
    }

    public void visitePlus(Plus op){
        result.append("+ ");
        op.a.accept(this);
        result.append(" ");
        op.b.accept(this);
        result.append(" ");
    }

    public void visiteMult(Mult op) {
        result.append("* ");
        op.a.accept(this);
        result.append(" ");
        op.b.accept(this);
        result.append(" ");
    }

    public void visiteMoins(Moins op) {
        result.append("- ");
        op.a.accept(this);
        result.append(" ");
        op.b.accept(this);
        result.append(" ");
    }

    public void visiteDiv(Div op) {
        result.append("/");
        op.a.accept(this);
        result.append(" ");
        op.b.accept(this);
        result.append(" ");
    }

    public void visiteValue(Value op) {
        result.append(op.eval());
    }

    @Override
    public void visitBool2Valuable(Bool2Valuable bool2Valuable) {
        result.append(bool2Valuable.getValue());
    }
}
