package jtf.tutorial.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

public class TreeHelper {
	/**
	 * Get all declared variables
	 */
	public static List<String> getVariables(Tree tree) {
		List<String> variables = new ArrayList<String>();
		internalGetVariables(tree, variables);
		return variables;
	}
	
	/**
	 * Get variables from syntax tree, internal used only
	 */
	private static void internalGetVariables(Tree tree, List<String> variables) {
		// visit first child
		int count = tree.getChildCount();
		if(count > 0)
			internalGetVariables(tree.getChild(0), variables);
		
		// visit itself
		if(tree.getType() == IExprTokens.EQ) {
			Token token = ((CommonTree)tree.getChild(0)).getToken();
			String var = token.getText();
			if(!variables.contains(var))
				variables.add(var);
		}
		
		// visit other children
		for(int i = 1; i < count; i++)
			internalGetVariables(tree.getChild(i), variables);
	}
}
