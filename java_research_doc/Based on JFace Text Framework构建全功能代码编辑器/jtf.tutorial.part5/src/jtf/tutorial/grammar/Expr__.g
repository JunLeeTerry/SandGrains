lexer grammar Expr;

T7 : ';' ;
T8 : '=' ;
T9 : '+' ;
T10 : '-' ;
T11 : '*' ;
T12 : '/' ;
T13 : '(' ;
T14 : ')' ;

// $ANTLR src "Expr.g" 30
INT	:	'0'..'9'+;
// $ANTLR src "Expr.g" 31
ID	:	('a'..'z' | 'A'..'Z')+;
// $ANTLR src "Expr.g" 32
WS	:	(' ' | '\t' | '\r' | '\n')+ { $channel = HIDDEN; };

