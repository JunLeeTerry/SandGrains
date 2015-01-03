package jtf.tutorial.ui;

import jtf.tutorial.grammar.TokenManager;
import jtf.tutorial.grammar.TreeManager;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.rules.RuleBasedPartitionScanner;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class JTFDialog extends Dialog {

	public JTFDialog(Shell parentShell) {
		super(parentShell);
	}

	public JTFDialog(IShellProvider parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite control = (Composite)super.createDialogArea(parent);
		
		// create viewer
		ExprViewer viewer = new ExprViewer(control, null, SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER | SWT.MULTI
				| SWT.FULL_SELECTION);
		viewer.getTextWidget().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.getTextWidget().setFont(JFaceResources.getFont(JFaceResources.TEXT_FONT));
		
		// set doc and partitioner
		IDocument doc = new Document();		
		IDocumentPartitioner partitioner = new FastPartitioner(new RuleBasedPartitionScanner(), new String[0]);
		partitioner.connect(doc);
		doc.setDocumentPartitioner(partitioner);
		viewer.setDocument(doc);
		
		// configure viewer
		viewer.configure(new ExprConfiguation());
		
		// initialize
		TokenManager.registerDocument(doc);
		TreeManager.registerDocument(doc);
		
		return control;
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(500, 400);
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("JFace Text Framework Tutorial Demo");
	}
}
