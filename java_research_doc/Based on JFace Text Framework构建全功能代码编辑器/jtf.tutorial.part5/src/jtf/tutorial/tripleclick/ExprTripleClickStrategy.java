package jtf.tutorial.tripleclick;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextViewer;

public class ExprTripleClickStrategy implements ITextTripleClickStrategy {
	public void tripleClicked(ITextViewer viewer) {
		MessageDialog.openInformation(viewer.getTextWidget().getShell(), "Triple Click", "Triple Click Detected!!");
	}
}
