package jtf.tutorial.doubleclick;

import jtf.tutorial.grammar.TokenList;
import jtf.tutorial.grammar.TokenManager;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewer;

public class ExprDoubleClickStrategy implements ITextDoubleClickStrategy {
	public void doubleClicked(ITextViewer viewer) {
		// get doc
		IDocument doc = viewer.getDocument();
		
		// get token list
		TokenList tokenList = TokenManager.getTokenList(doc);
		
		// get double click position
		int offset = viewer.getSelectedRange().x;
		
		// get token in that offset
		CommonToken token = tokenList.getToken(offset);
		
		// select whole token if token is not null
		if(token != null && token.getType() != Token.EOF)
		{
			// select double clicked token
			viewer.setSelectedRange(token.getStartIndex(), token.getStopIndex() - token.getStartIndex() + 1);
		}
	}
}
