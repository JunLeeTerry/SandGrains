package jtf.tutorial.syntaxhighlight;

import jtf.tutorial.ColorManager;
import jtf.tutorial.IColorConstants;
import jtf.tutorial.grammar.IExprTokens;
import jtf.tutorial.grammar.TokenList;
import jtf.tutorial.grammar.TokenManager;

import org.antlr.runtime.CommonToken;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;

public class ExprTokenScanner implements ITokenScanner {
	/*
	 * JFace token type definition
	 */
	
	private static IToken VARIABLE = new Token(new TextAttribute(ColorManager.getInstance().getColor(IColorConstants.VARIABLE)));
	private static IToken INTEGER = new Token(new TextAttribute(ColorManager.getInstance().getColor(IColorConstants.INTEGER)));
	private static IToken DEFAULT = new Token(new TextAttribute(ColorManager.getInstance().getColor(IColorConstants.DEFAULT)));

	// character offset
	private int offset;
	// last token returned by nextToken()
	private CommonToken lastToken;
	// token list
	private TokenList tokenList;
	
	public ExprTokenScanner() {
		offset = 0;
	}
	
	public int getTokenLength() {
		return lastToken.getStopIndex() - lastToken.getStartIndex() + 1;
	}

	public int getTokenOffset() {
		return lastToken.getStartIndex();
	}

	public IToken nextToken() {
		if(lastToken == null)
			lastToken = tokenList.getToken(offset);
		else
			lastToken = tokenList.getNextToken(lastToken);
		
		if(lastToken == null)
			return Token.UNDEFINED;
		
		switch(lastToken.getType()) {
			case IExprTokens.ID:
				return VARIABLE;
			case IExprTokens.INT:
				return INTEGER;
			case org.antlr.runtime.Token.EOF:
				return Token.EOF;
			default:
				return DEFAULT;
		}
	}

	public void setRange(IDocument document, int offset, int length) {
		lastToken = null;
		this.offset = offset;
		tokenList = TokenManager.getTokenList(document);
	}
}
