package jtf.tutorial.ui;

import jtf.tutorial.syntaxhighlight.ExprTokenScanner;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

public class ExprConfiguation extends SourceViewerConfiguration {
	private ITokenScanner tokenScanner;
	
	@Override
	public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) 
	{
		PresentationReconciler reconciler = new PresentationReconciler();
		
		DefaultDamagerRepairer dr = new DefaultDamagerRepairer(getTokenScanner());
		reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
		
		return reconciler;
	}

	/**
	 * Get token scanner
	 */
	protected ITokenScanner getTokenScanner() 
	{
		if(tokenScanner == null)
			tokenScanner = new ExprTokenScanner();
		return tokenScanner;
	}
}
