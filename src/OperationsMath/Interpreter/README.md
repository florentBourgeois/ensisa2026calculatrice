# Interpreter 

Le but du patern interpreter est de définir une grammaire pour un langage et d'implémenter un interpréteur qui peut exécuter des instructions dans ce langage.

Le diagramme de classes du pattern interpreter est le suivant :
![Diagramme de classes du pattern Interpreter](interpreter.png)

Le package `composite` contient en réalité des classes qui représentent les différents éléments de la grammaire, tels que les expressions, les opérateurs et les instructions. Chaque classe implémente l'interface `Element`, qui définit une méthode `eval()` pour exécuter le calcul correspondant. 

Ce package propose donc une classe `Parser` qui permet de parser une expression mathématique et de construire un arbre d'expressions à partir de cette expression. L'arbre d'expressions est ensuite évalué en appelant la méthode `eval()` ou en passant un `visiteur` sur la racine de l'arbre.