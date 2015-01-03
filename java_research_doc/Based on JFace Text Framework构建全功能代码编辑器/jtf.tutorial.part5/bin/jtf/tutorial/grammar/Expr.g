grammar Expr;

options {
	output = AST;
	ASTLabelType = CommonTree;
}

@header {
	import java.util.Map;
	import java.util.HashMap;
}

@members {
	Map<Token, String> errors = new HashMap<Token, String>();
	
	public Map<Token, String> getErrors() {
		return errors;
	}
	
	public void displayRecognitionError(String[] tokenNames, RecognitionException e)
	{
		String hdr = getErrorHeader(e);
		String msg = getErrorMessage(e, tokenNames);
		String err = hdr+" "+msg;
		errors.put(e.token, err);
		emitErrorMessage(err);
	}
}

INT	:	'0'..'9'+;
ID	:	('a'..'z' | 'A'..'Z')+;
WS	:	(' ' | '\t' | '\r' | '\n')+ { $channel = HIDDEN; };

prog	:	stat+
	;

stat	:	expr ';'		-> expr
	|	ID '=' expr ';'		-> ^('=' ID expr)
	|	';'			->
	;
	
expr
	:	multExpr
		( '+'^ multExpr
		| '-'^ multExpr
		)*
	;
	
multExpr
	:	atom
		( '*'^ atom
		| '/'^ atom
		)*
	;
	
atom
	:	INT
	|	ID
	|	'('! expr ')'!
	;
