package edu.suda.ide.yw.asmplugin.editor;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.IntStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import test.antlr4.RFLexer;
import test.antlr4.RFParser;
import edu.suda.ide.yw.asmplugin.Constants;

public class AntlrScanner implements ITokenScanner {

	private int offset;
	private CommonToken lastToken;
	private CommonTokenStream tokenStream;

	private final Color REGCOLOR = new Color(Display.getCurrent(), new RGB(0,
			255, 255));
	private final Color NUMCOLOR = new Color(Display.getCurrent(), new RGB(255,
			0, 255));
	// private AdaptedToken keyword = new AdaptedToken(
	// AdaptedToken.T_UNDEFINED,
	// TextAttributeConverter
	// .preferenceDataToTextAttribute(Constants.PREF_TEXTATTR_INSTRUCTION_DATA));
	// private AdaptedToken comment = new AdaptedToken(
	// AdaptedToken.T_UNDEFINED,
	// TextAttributeConverter
	// .preferenceDataToTextAttribute(Constants.PREF_TEXTATTR_COMMENT_DATA));
	// private AdaptedToken register = new
	// AdaptedToken(AdaptedToken.T_UNDEFINED,
	// new TextAttribute(REGCOLOR));
	// private AdaptedToken number = new AdaptedToken(AdaptedToken.T_UNDEFINED,
	// new TextAttribute(NUMCOLOR));

	private IToken keyword = new Token(
			TextAttributeConverter
					.preferenceDataToTextAttribute(Constants.PREF_TEXTATTR_INSTRUCTION_DATA));
	private IToken comment = new Token(
			TextAttributeConverter
					.preferenceDataToTextAttribute(Constants.PREF_TEXTATTR_COMMENT_DATA));
	private Token register = new Token(new TextAttribute(REGCOLOR));
	private Token number = new Token(new TextAttribute(NUMCOLOR));

	public AntlrScanner() {
	}

	@Override
	public IToken nextToken() {
		if (tokenStream.size() == 0) {
			System.err.println("no token can be found!!");
			return null;
		}
		if (lastToken == null) {
			lastToken = (CommonToken) tokenStream.get(0);
		} else {
			lastToken = (CommonToken) tokenStream
					.get(lastToken.getTokenIndex() + 1);
		}

		if (lastToken == null)
			return Token.UNDEFINED;

		switch (lastToken.getType()) {
		case RFParser.KEYWORD:
			return keyword;
		case RFParser.COMMENT:
			return comment;
		case RFParser.ID:
			return register;
		case RFParser.INT:
		case RFParser.HEX:
			return number;
		case IntStream.EOF:
			return Token.EOF;
		default:
			return Token.UNDEFINED;
		}
	}

	@Override
	public int getTokenOffset() {
		return lastToken.getStartIndex();
	}

	@Override
	public int getTokenLength() {
		return lastToken.getStopIndex() - lastToken.getStartIndex() + 1;
	}

	@Override
	public void setRange(IDocument document, int offset, int length) {
		lastToken = null;
		// this.offset = offset;
		antlrParse(document, offset, length);
	}

	private void antlrParse(IDocument document, int offset, int length) {
		if (document == null) {
			return;
		}
		//non-incremental scanner: swallow the whole document
		ANTLRInputStream input = new ANTLRInputStream(document.get());
		RFLexer lexer = new RFLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		RFParser parser = new RFParser(tokens);
		ParseTree tree = parser.prog();
		tokenStream = (CommonTokenStream) parser.getTokenStream();
		for (int i = 0; i < tokenStream.size(); i++) {
			System.out.println(i + " " + tokenStream.get(i).getText() + " "
					+ tokenStream.get(i).getType());
		}
	}
}
