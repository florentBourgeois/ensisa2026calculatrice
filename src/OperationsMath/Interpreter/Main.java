package OperationsMath.Interpreter;

import OperationsMath.Element;
import OperationsMath.visitor.VisitorMathInfixe;
import OperationsMath.visitor.VisitorMathPrefixe;
import OperationsMath.visitor.VisitorOperation;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // juste gestion des Entiers
        System.out.println("interpreter + parser simple -> 579");
        Element expression = Parser.parse("579");
        affichageResultat(expression, 1);


        // expression fait + -  et entiers
        System.out.println("\n---------\nInterpreter + parser expressions simple + : 6+4");
        expression = Parser.parse("6+4");
        affichageResultat(expression, 1);

        System.out.println("\n---------\nInterpreter + parser expressions simple - : 6-4");
        expression = Parser.parse("6-4");
        affichageResultat(expression, 1);

        System.out.println("\n---------\nInterpreter + parser expressions + ou - multiple : 6+4+7-3-2+4");
        expression = Parser.parse("6+4+7-3-2+4");
        affichageResultat(expression, 1);


        System.out.println("\n---------\nExemple d'utilisation du pattern Interpreter + parser complet pour évaluer une expression arithmétique : 6+3*7-(2+2)");
         expression = Parser.parse("6+3*7-(2+2)");
        affichageResultat(expression, 3);
    }



    public static void affichageResultat(Element expression, int visiteurMode) {
        System.out.println(expression);
        System.out.println("\nRésultat de l'expression : " + expression.eval());
        VisitorOperation visitor;
        List<VisitorOperation> visitors = new ArrayList<>();
        if (visiteurMode == 1 || visiteurMode == 3)
            visitors.add(new VisitorMathPrefixe());
        if (visiteurMode == 2 || visiteurMode == 3)
            visitors.add(new VisitorMathInfixe());

        System.out.println("\nRésultat de l'expression avec les visiteur : ");
        for (VisitorOperation v : visitors) {
            expression.accept(v);
            System.out.println("\t" + v.getResult());
        }
    }
}
