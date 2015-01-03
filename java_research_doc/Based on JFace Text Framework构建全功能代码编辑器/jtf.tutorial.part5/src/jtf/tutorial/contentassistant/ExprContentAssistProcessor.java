package jtf.tutorial.contentassistant;

import java.util.ArrayList;
import java.util.List;

import jtf.tutorial.grammar.TreeHelper;
import jtf.tutorial.grammar.TreeManager;

import org.antlr.runtime.tree.Tree;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.swt.graphics.Point;

public class ExprContentAssistProcessor implements IContentAssistProcessor {
	public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer,
	        int offset) {
		// get document
		IDocument doc = viewer.getDocument();
		
		// get tree
		Tree tree = TreeManager.getTree(doc);
		if(tree == null)
			return null;
		
		// get current selected range
		Point range = viewer.getSelectedRange();
		
		// get all declared variables
		List<String> variables = TreeHelper.getVariables(tree);
		
		// create proposals
		List<ICompletionProposal> proposals = new ArrayList<ICompletionProposal>();
		for(String var : variables) {
			proposals.add(new CompletionProposal(var, range.x, range.y, var.length(), null, var, null, "Add your info here"));
		}
		return proposals.toArray(new ICompletionProposal[proposals.size()]);
	}

	public IContextInformation[] computeContextInformation(ITextViewer viewer,
	        int offset) {
		return null;
	}

	public char[] getCompletionProposalAutoActivationCharacters() {
		return null;
	}

	public char[] getContextInformationAutoActivationCharacters() {
		return null;
	}

	public IContextInformationValidator getContextInformationValidator() {
		return null;
	}

	public String getErrorMessage() {
		return null;
	}
}
