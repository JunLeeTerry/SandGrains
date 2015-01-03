package jtf.tutorial.ui;

import jtf.tutorial.contentassistant.ExprContentAssistProcessor;
import jtf.tutorial.doubleclick.ExprDoubleClickStrategy;
import jtf.tutorial.syntaxhighlight.ExprTokenScanner;
import jtf.tutorial.tripleclick.ExprTripleClickStrategy;
import jtf.tutorial.tripleclick.ITextTripleClickStrategy;

import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.widgets.Shell;

public class ExprConfiguation extends SourceViewerConfiguration implements IExprViewerConfiguration {
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
	
	@Override
	public ITextDoubleClickStrategy getDoubleClickStrategy(
	        ISourceViewer sourceViewer, String contentType) {
	    return new ExprDoubleClickStrategy();
	}

	public ITextTripleClickStrategy getTripleClickStrategy(
            ISourceViewer viewer, String contentType) {
		return new ExprTripleClickStrategy();
    }
	
	@Override
	public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) 
	{
        ContentAssistant assistant = new ContentAssistant();
        assistant.setInformationControlCreator(new IInformationControlCreator() {
        	public IInformationControl createInformationControl(Shell parent)
        	{
        		DefaultInformationControl control = new DefaultInformationControl(parent);
        		return control;
        	}
        });

        // add assist processor
        IContentAssistProcessor processor = new ExprContentAssistProcessor();
        assistant.setContentAssistProcessor(processor, IDocument.DEFAULT_CONTENT_TYPE);
        
        return assistant;
	}
}
