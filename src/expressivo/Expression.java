/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

/**
 * An immutable data type representing a polynomial expression of:
 *   + and *
 *   nonnegative integers and floating-point numbers
 *   variables (case-sensitive nonempty strings of letters)
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {

    //Expression = Number(n: int)
    //+ Variable(s: String)
    //+ Plus(left: Expression, right: Expression)
    //+ Multiply (left: Expression, right: Expression)



    // Safety from rep exposure
    //    all fields are immutable and final


    /**
     * Parses a string into an Expression object.
     * Strings must satisfy the follow requirements:
     * Allowed operations are addition and multiplication represented by '+' and '*'
     * Variable names must be composed only of [a-zA-Z] are case sensitive
     * All parentheses must be closed
     * Whitespace and redundant parentheses are allowed
     * Numbers should be positive and in the decimal form. They be represented as integers and floating
     * point numbers. For floating point numbers there only needs to be a number
     * on one side of the floating point e.g. 19. as well as .19 are allowed*
     * Sensitive to ordering and grouping so that a + 1 != 1 + a, a + (1 + b) ! = (a + 1) + b
     * @param input string containing a valid polynomial expression, as described below:
     * @return an Expression object representing the input expression
     */

    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout.
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is invalid
     */
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
    }


    public static Expression make(double num){
        return new Number(num);
    }

    public static Expression make(String var){
        return new Variable(var);
    }

    /**
     * @return a parsable representation of this expression, such that
     * for all e:Expression, e.equals(Expression.parse(e.toString())).
     */
    @Override 
    public String toString();

    /**
     * @param thatObject any object
     * @return true if and only if this and thatObject are structurally-equal
     * Expressions, as defined in the PS3 handout.
     */
    @Override
    public boolean equals(Object thatObject);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     * equality, such that for all e1,e2:Expression,
     *     e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();

    // TODO more instance methods


    public static Expression sum(Expression left, Expression right){
        return left;
    }

    public static String make(Expression inputExpression){
        return inputExpression.toString();
    }

    public static Expression multiply(Expression left, Expression right){
        return left;
    }


}
