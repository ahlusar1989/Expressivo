package expressivo;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import expressivo.parser.ExpressionLexer;
import expressivo.parser.ExpressionListener;
import expressivo.parser.ExpressionParser;
import java.util.Stack;
import java.util.List;

/**
 * Created by saranahluwalia on 7/2/17.
 */
public class Parser {

    /**
     * Parses a string into an Expression object,
     * called by Expression.parse.
     * Input string must satisfy the requirements of
     * Expression.parse
     * @param stringExpression containing a polynomial expression
     * @return an Expression object which represents the expression
     * contained in the string
     */

    public static String parse(String stringExpression){
        CharStream input = new ANTLRInputStream(stringExpression);

        try {
            ExpressionParser parser = makeParser(input);
            ParseTree treeRoot = parser.root();




        }
        catch (Exception e){
            throw new Error("Error in Parser" + e);
        }

        // TODO return statement here


    }

    private static ExpressionParser makeParser(CharStream stream){
        // Make a lexer.  This converts the stream of characters into a
        // stream of tokens.  A token is a character group, like "<i>"
        // or "</i>".  Note that this doesn't start reading the character stream yet,
        // it just sets up the lexer to read it.

        ExpressionLexer lexer = new ExpressionLexer(stream);
        lexer.reportErrorsAsExceptions();

        TokenStream tokens = new CommonTokenStream(lexer);
        // Make a parser whose input comes from the token stream produced by the lexer.
        ExpressionParser parser = new ExpressionParser(tokens);
        parser.reportErrorsAsExceptions();

        return parser;
    }

    class CustomExpression implements ExpressionListener {

        private Stack<Expression> stack = new Stack<>();
        //Invariant: stack contains the Expression value of each parse subtree
        //that has been fully-walked so far, but whose parent has not yet been exited
        //by the walk. The stack is ordered by recency of visit, so that
        //the top of the stack is the Expression for the most recently walked
        //subtree.
        //
        //At the start of the walk, the stack is empty as no subtrees have
        //been fully walked.
        //
        //Whenever a node is exited by the walk, the Expression values of its
        //children are  on top of the stack, with the order that the last
        //child is on top.
        //
        //To preserve the invariant, we pop the Expression values from the stack,
        //combine them with the appropriate Expression producer, and
        //push back an Expression value representing the entire subtree
        //under the node
        //
        //At the end of the walk, after all subtrees have been walked and the root
        //has been exited, only the entire tree satisfied the invariant property
        //so the top fof the stack is the Expression representing the entire parse tree

        /**
         * Returns the expression constructed by the listener object
         * Requires that the listener has completely walked over an Expression
         * parse tree using ParseTreeWalker
         * @return IntegerExpression for the parse tree that was walked
         */
        public Expression getExpression(){
            return stack.get(0);
        }

        @Override public void enterRoot(ExpressionParser.RootContext context) {
        }

        @Override public void exitRoot(ExpressionParser.RootContext context) {
        }

        @Override public void enterSum(ExpressionParser.SumContext context) {
        }

        @Override public void exitSum(ExpressionParser.SumContext context) {
            //matched the product ('+' product)* rule
            List<ExpressionParser.ProductContext> productTerms = context.product();
            assert stack.size() >= productTerms.size();

            //the pattern must have at least 1 child
            //pop the last child
            assert productTerms.size() > 0;
            Expression sum = stack.pop();

            //pop the older children, one by one, and add them on
            for(int i = 1; i < productTerms.size(); ++i){
                sum = Expression.sum(stack.pop(), sum);
            }

            // the result is this subtree's Expression
            stack.push(sum);
        }

        // TODO more void methods

    }

}
