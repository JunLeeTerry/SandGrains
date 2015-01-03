package jtf.tutorial.textdecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jtf.tutorial.grammar.SharedParser;
import jtf.tutorial.grammar.TreeManager;

import org.antlr.runtime.CommonToken;
import org.antlr.runtime.Token;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModelExtension;
import org.eclipse.jface.text.source.ISourceViewer;

public class SyntaxChecker implements IDocumentListener {
	private ISourceViewer viewer;
	
	public SyntaxChecker(ISourceViewer viewer) {
		this.viewer = viewer;
	}
	
	public void install() {
		viewer.getDocument().addDocumentListener(this);
	}
	
	public void uninstall() {
		viewer.getDocument().removeDocumentListener(this);
	}
	
	public void documentAboutToBeChanged(DocumentEvent event) {
	}

	public void documentChanged(DocumentEvent event) {
		// get model
		IAnnotationModel model = viewer.getAnnotationModel();
		if(model == null || !(model instanceof IAnnotationModelExtension))
			return;
		
		// create map contains annotations to be added
		Map<Annotation, Position> toBeAdded = new HashMap<Annotation, Position>();
		
		// get annotations to be removed
		Annotation[] toBeRemoved = getAnnotations(new String[] {
		        "org.eclipse.ui.workbench.texteditor.error"
		});
		
		// get document
		IDocument doc = event.getDocument();
		
		// parse it
		TreeManager.getTree(doc);
		
		// get errors
		Map<Token, String> errors = SharedParser.getLastErrors();
		
		// add annotation
		for(Token token : errors.keySet()) {
			CommonToken ct = (CommonToken)token;
			Annotation anno = new ExprAnnotation("org.eclipse.ui.workbench.texteditor.error", 
					errors.get(token));
			Position pos = new Position(ct.getStartIndex(), ct.getStopIndex() - ct.getStartIndex() + 1);
			toBeAdded.put(anno, pos);
		}
		
		// replace annotation one time, this provides a better performance than remove/add one by one
		((IAnnotationModelExtension)model).replaceAnnotations(toBeRemoved, toBeAdded);
	}
	
	/**
	 * Get annotation with given type
	 */
	public Annotation[] getAnnotations(String[] types)
	{
		List<Annotation> ret = new ArrayList<Annotation>();
		Iterator i = viewer.getAnnotationModel().getAnnotationIterator();
		while(i.hasNext())
		{
			Annotation anno = (Annotation)i.next();
			for(String type : types)
			{
				if(anno.getType().equals(type))
				{
					ret.add(anno);
					break;
				}
			}
		}
		
		return ret.toArray(new Annotation[ret.size()]);
	}
}
