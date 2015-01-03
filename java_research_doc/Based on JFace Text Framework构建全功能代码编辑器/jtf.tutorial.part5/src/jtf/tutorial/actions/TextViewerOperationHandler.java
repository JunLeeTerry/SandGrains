package jtf.tutorial.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextViewer;

public class TextViewerOperationHandler extends AbstractHandler {
	private int operationCode;
	private ITextViewer viewer;

	public TextViewerOperationHandler(ITextViewer viewer, int operationCode) {
		this.viewer = viewer;
		this.operationCode = operationCode;
	}

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		if (viewer != null && operationCode != -1)
			viewer.getTextOperationTarget().doOperation(operationCode);
		return null;
	}
}
