grammar Expr;

options {
	output = AST;
	ASTLabelType = CommonTree;
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
