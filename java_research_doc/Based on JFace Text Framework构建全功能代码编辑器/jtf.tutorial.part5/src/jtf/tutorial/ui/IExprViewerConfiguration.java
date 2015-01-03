package jtf.tutorial.ui;

import org.eclipse.jface.text.source.ISourceViewer;

import jtf.tutorial.tripleclick.ITextTripleClickStrategy;

public interface IExprViewerConfiguration {
	/**
	 * Get triple click strategy
	 */
	public ITextTripleClickStrategy getTripleClickStrategy(ISourceViewer viewer, String contentType);
}
