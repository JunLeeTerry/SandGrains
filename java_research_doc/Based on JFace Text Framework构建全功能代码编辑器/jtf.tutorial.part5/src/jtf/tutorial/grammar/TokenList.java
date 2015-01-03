package jtf.tutorial.grammar;

import java.util.List;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;

public class TokenList {
	private List<CommonToken> tokenList;
	private boolean dirty;
	
	/**
	 * Get token by character offset
	 */
	public CommonToken getToken(int offset) {
		if(tokenList == null || tokenList.isEmpty())
			return (CommonToken)Token.EOF_TOKEN;
		else if(offset < 0)
			return tokenList.get(0);
		else if(offset >= getLength())
			return (CommonToken)Token.EOF_TOKEN;
		else {
			// it is better to use binary search here
			for(CommonToken token : tokenList) {
				if(offset >= token.getStartIndex() && offset <= token.getStopIndex())
					return token;
			}
			return (CommonToken)Token.EOF_TOKEN;
		} 
	}
	
	/**
	 * Get next token of given token, or null if this is last token
	 */
	public CommonToken getNextToken(CommonToken refToken) {
		if(tokenList == null || refToken == null) 
			return null;
		else {
			int index = tokenList.indexOf(refToken);
			if(index == tokenList.size() - 1) {
				if(refToken.getType() == Token.EOF)
					return null;
				else
					return (CommonToken)Token.EOF_TOKEN;
			} else
				return tokenList.get(index + 1);
		}
	}
	
	/**
	 * Get source code length
	 */
	public int getLength() {
		if(tokenList == null || tokenList.isEmpty())
			return 0;
		else
			return tokenList.get(tokenList.size() - 1).getStopIndex() + 1;
	}

	/**
	 * Get list of all tokens
	 */
	public List<CommonToken> getTokenList() {
		return tokenList;
	}

	/**
	 * Set token list
	 */
	public void setTokenList(List<CommonToken> tokenList) {
		this.tokenList = tokenList;
	}

	/**
	 * Get dirty flag
	 */
	public boolean isDirty() {
    	return dirty;
    }

	/**
	 * Set dirty flag
	 */
	public void setDirty(boolean dirty) {
    	this.dirty = dirty;
    }
}
