package jtf.tutorial.grammar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.CommonTokenStream;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;

public class TokenManager {
	private static Map<IDocument, TokenList> TOKEN_MAP = new HashMap<IDocument, TokenList>();
	
	/**
	 * Get token list of a document
	 */
	public static TokenList getTokenList(IDocument doc) {
		TokenList tokenList = TOKEN_MAP.get(doc);
		if(tokenList == null) {
			CommonTokenStream tokenStream = SharedParser.getTokenStream(doc.get());
			List<CommonToken> list = (List<CommonToken>)tokenStream.getTokens();
			tokenList = new TokenList();
			tokenList.setTokenList(list);
			TOKEN_MAP.put(doc, tokenList);
		}
		return tokenList;
	}
	
	/**
	 * Clear token list of a document from cache
	 */
	public static void clearTokenList(IDocument doc) {
		TOKEN_MAP.remove(doc);
	}
	
	/**
	 * Register a document, then TokenManager will track the document change event and refresh
	 * token list when necessary
	 */
	public static void registerDocument(IDocument doc)
	{
		doc.addPrenotifiedDocumentListener(new IDocumentListener() {
			public void documentAboutToBeChanged(DocumentEvent event)
			{
			}
			
			public void documentChanged(DocumentEvent event)
			{
				TokenManager.clearTokenList(event.fDocument);
			}
		});
	}
}
