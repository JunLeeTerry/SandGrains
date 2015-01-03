package jtf.tutorial.grammar;

import java.util.HashMap;
import java.util.Map;

import org.antlr.runtime.tree.Tree;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;

public class TreeManager {
	private static Map<IDocument, Tree> TREE_MAP = new HashMap<IDocument, Tree>();
	
	/**
	 * Get syntax tree of a document
	 */
	public static Tree getTree(IDocument doc) {
		Tree tree = TREE_MAP.get(doc);
		if(tree == null) {
			tree = SharedParser.getTree(doc.get());
			if(tree != null)
				TREE_MAP.put(doc, tree);
		}
		return tree;
	}
	
	/**
	 * Clear syntax tree of a document from cache
	 */
	public static void clearTree(IDocument doc) {
		TREE_MAP.remove(doc);
	}
	
	/**
	 * Register a document, then TreeManager will track the document change event and refresh
	 * syntax tree when necessary
	 */
	public static void registerDocument(IDocument doc)
	{
		doc.addPrenotifiedDocumentListener(new IDocumentListener() {
			public void documentAboutToBeChanged(DocumentEvent event)
			{
			}
			
			public void documentChanged(DocumentEvent event)
			{
				TreeManager.clearTree(event.fDocument);
			}
		});
	}
}
