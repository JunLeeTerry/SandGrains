package jtf.tutorial.grammar;

import java.util.HashMap;
import java.util.Map;

import jtf.tutorial.grammar.ExprParser.prog_return;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.runtime.tree.Tree;

public class SharedParser {
	private static final ExprLexer lexer = new ExprLexer();
	private static final CommonTokenStream stream = new CommonTokenStream();
	private static final ExprParser parser = new ExprParser(stream);
	private static Map<Token, String> lastErrors = new HashMap<Token, String>();
	
	/**
	 * Get CommonTokenStream from source code
	 */
	public static CommonTokenStream getTokenStream(String source) {
		lexer.setCharStream(new ANTLRStringStream(source));
		return new CommonTokenStream(lexer);
	}
	
	/**
	 * Get syntax tree from source code
	 */
	public static Tree getTree(String source) {
		lexer.setCharStream(new ANTLRStringStream(source));
		stream.setTokenSource(lexer);
		parser.setTokenStream(stream);
		lastErrors.clear();
		try {
	        prog_return ret = parser.prog();
	        lastErrors = parser.getErrors();
	        return (Tree)ret.getTree();
        } catch (RecognitionException e) {
        	return null;
        }
	}

	/**
	 * Get error returned by last parsing 
	 */
	public static Map<Token, String> getLastErrors() {
		return lastErrors;
    }
}
