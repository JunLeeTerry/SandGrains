package jtf.tutorial.ui;

import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.swt.widgets.Composite;

public class ExprViewer extends SourceViewer {

	public ExprViewer(Composite parent, IVerticalRuler ruler, int styles) {
		super(parent, ruler, styles);
	}

	public ExprViewer(Composite parent, IVerticalRuler verticalRuler,
			IOverviewRuler overviewRuler, boolean showAnnotationsOverview,
			int styles) {
		super(parent, verticalRuler, overviewRuler, showAnnotationsOverview,
				styles);
	}

}
