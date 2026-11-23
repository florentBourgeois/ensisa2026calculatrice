package OperationsMath.Interpreter;

import OperationsMath.Element;
import OperationsMath.Value;
import OperationsMath.composite.Div;
import OperationsMath.composite.Moins;
import OperationsMath.composite.Mult;
import OperationsMath.composite.Plus;

/**
 * Analyse une expression arithmétique et construit l'arbre Composite correspondant.
 **/

public class Parser {
    private final String source;
    private int position;

    /**
     * Crée un parseur initialisé au début de la chaîne source.
     *
     * @param source expression à analyser
     */
    private Parser(String source) {
        this.source = source;
    }

    /**
     * Analyse entièrement une expression et retourne la racine de son arbre Composite.
     *
     * @param source expression arithmétique, avec ou sans espaces
     * @return l'arbre composé de {@link Value} et d'opérations Composite
     * @throws IllegalArgumentException si la source est nulle ou contient une erreur de syntaxe
     */
    public static Element parse(String source) {
        if (source == null) {
            throw new IllegalArgumentException("L'expression ne peut pas être null.");
        }

        Parser parser = new Parser(source);
        Element result = parser.parseExpression();
        parser.skipWhitespace(); // si on a placé des espaces à la fin de l'expression, on les ignore

        if (!parser.isAtEnd()) {
            throw parser.error("Caractère inattendu");
        }

        return result;
    }

    /**
     * Analyse une expression composée d'entiers ... wait what ???
     *
     * <p>La boucle traite les opérateurs de gauche à droite
    * et délègue chaque opérande à {@link #parseTerme()} ()}.</p>
     *
     * @return l'arbre Composite representant l'expression
     */
    private Element parseExpression() {
        System.out.println("A implémenter");
        return parseEntier();
    }

    /**
     * Analyse un terme composé de facteurs reliés par une multiplication ou une division. (meme fonctionnement que parseExpression mais pour les * et /)
     *
     * @return l'arbre Composite representant le terme
     */
    private Element parseTerme() {
        System.out.println("A implémenter");
        return parseEntier();
    }

    /**
     * Analyse un facteur : un entier ou une expression entourée de parenthèses.
     *
     * @return l'arbre Composite du facteur
     * @throws IllegalArgumentException si la parenthese fermante manque ou si le facteur est invalide
     */
    private Element parseFacteur() {
        System.out.println("A implémenter");
        return parseEntier();
    }

    /**
     * Lit une suite de chiffres et crée la "feuille" {@link Value}.
     *
     * @return la valeur entière lue
     * @throws IllegalArgumentException si aucun chiffre n'est présent ou si le nombre dépasse la capacité d'un {@code int}
     */
    private Element parseEntier() {
        skipWhitespace();
        int debut = position;

        // tant que c'est chiffre, on avance la position. On ne consomme pas le caractère, on le lit juste pour savoir où s'arrêter.
        while (!isAtEnd() && isDigit(source.charAt(position))) {
            position++;
        }

        if (debut == position) {
            throw error("Un entier ou une parenthèse ouvrante était attendu");
        }

        try {
            int valeur = Integer.parseInt(source.substring(debut, position));
            return new Value(valeur);
        } catch (NumberFormatException exception) {
            throw error("L'entier dépasse la valeur maximale autorisée");
        }
    }

    /**
     * Exige le prochain caractère indiqué et le consomme. Est utilisé dans parseFacteur qui attend une parenthèse fermante après une expression débutée par une parenthèse ouvrante.
     *
     * @param expected caractère attendu
     * @throws IllegalArgumentException si le prochain caractère est différent ou absent
     */
    private void expect(char expected) {
        if (!consume(expected)) {
            throw error("Le caractère '" + expected + "' était attendu");
        }
    }

    /**
     * Consomme le caractère indiqué s'il est présent à la position courante.
     * Permet de vérifier si un caractère est présent et de l'avancer si c'est le cas, sans lever d'exception.
     * Exemple : dans parseExpression(), on consomme '+' ou '-' si présent, sinon on retourne le résultat actuel (sous entend que l'expression est terminée).
     *
     * @param expected caractère à rechercher
     * @return {@code true} si le caractère a été consommé, {@code false} sinon
     */
    private boolean consume(char expected) {
        if (!isAtEnd() && source.charAt(position) == expected) {
            position++;
            return true;
        }
        return false;
    }

    /**
     * Avance la position de lecture en ignorant les espaces et autres caractères blancs.
     */
    private void skipWhitespace() {
        while (!isAtEnd() && Character.isWhitespace(source.charAt(position))) {
            position++;
        }
    }

    /**
     * Indique si tous les caractères de la source ont été lus.
     *
     * @return {@code true} si la position a atteint la fin de la source
     */
    private boolean isAtEnd() {
        return position >= source.length();
    }

    /**
     * Vérifie si un caractère est un chiffre décimal ASCII.
     *
     * @param character caractère à vérifier
     * @return {@code true} pour les caracteres de {@code '0'} a {@code '9'}
     */
    private static boolean isDigit(char character) {
        return character >= '0' && character <= '9';
    }

    /**
     * Crée une exception de syntaxe située à la position courante.
     *
     * @param message explication de l'erreur détectée
     * @return exception contenant le message, la position et l'expression source
     */
    private IllegalArgumentException error(String message) {
        return new IllegalArgumentException(
                message + " à la position " + position + " dans : " + source
        );
    }
}