package jtf.tutorial.tripleclick;

import org.eclipse.jface.text.ITextViewer;

/** 
 * Text viewer triple click strategy interface, the method will be invoked when triple clicking occurs
 */
public interface ITextTripleClickStrategy {
	/**
	 * Invoked when triple clicking detected
	 */
	public void tripleClicked(ITextViewer viewer);
}
