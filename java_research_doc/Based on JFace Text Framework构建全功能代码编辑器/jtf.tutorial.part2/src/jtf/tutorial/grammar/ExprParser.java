package jtf.tutorial.grammar;

// $ANTLR 3.0.1 Expr.g 2007-09-01 22:56:25

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class ExprParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "INT", "ID", "WS", "';'", "'='", "'+'", "'-'", "'*'", "'/'", "'('", "')'"
    };
    public static final int INT=4;
    public static final int WS=6;
    public static final int EOF=-1;
    public static final int ID=5;

        public ExprParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Expr.g"; }


    public static class prog_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start prog
    // Expr.g:12:1: prog : ( stat )+ ;
    public final prog_return prog() throws RecognitionException {
        prog_return retval = new prog_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        stat_return stat1 = null;



        try {
            // Expr.g:12:6: ( ( stat )+ )
            // Expr.g:12:8: ( stat )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // Expr.g:12:8: ( stat )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=INT && LA1_0<=ID)||LA1_0==7||LA1_0==13) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Expr.g:12:8: stat
            	    {
            	    pushFollow(FOLLOW_stat_in_prog84);
            	    stat1=stat();
            	    _fsp--;

            	    adaptor.addChild(root_0, stat1.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end prog

    public static class stat_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start stat
    // Expr.g:15:1: stat : ( expr ';' -> expr | ID '=' expr ';' -> ^( '=' ID expr ) | ';' ->);
    public final stat_return stat() throws RecognitionException {
        stat_return retval = new stat_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal3=null;
        Token ID4=null;
        Token char_literal5=null;
        Token char_literal7=null;
        Token char_literal8=null;
        expr_return expr2 = null;

        expr_return expr6 = null;


        CommonTree char_literal3_tree=null;
        CommonTree ID4_tree=null;
        CommonTree char_literal5_tree=null;
        CommonTree char_literal7_tree=null;
        CommonTree char_literal8_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_8=new RewriteRuleTokenStream(adaptor,"token 8");
        RewriteRuleTokenStream stream_7=new RewriteRuleTokenStream(adaptor,"token 7");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Expr.g:15:6: ( expr ';' -> expr | ID '=' expr ';' -> ^( '=' ID expr ) | ';' ->)
            int alt2=3;
            switch ( input.LA(1) ) {
            case INT:
            case 13:
                {
                alt2=1;
                }
                break;
            case ID:
                {
                int LA2_2 = input.LA(2);

                if ( (LA2_2==8) ) {
                    alt2=2;
                }
                else if ( (LA2_2==7||(LA2_2>=9 && LA2_2<=12)) ) {
                    alt2=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("15:1: stat : ( expr ';' -> expr | ID '=' expr ';' -> ^( '=' ID expr ) | ';' ->);", 2, 2, input);

                    throw nvae;
                }
                }
                break;
            case 7:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("15:1: stat : ( expr ';' -> expr | ID '=' expr ';' -> ^( '=' ID expr ) | ';' ->);", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Expr.g:15:8: expr ';'
                    {
                    pushFollow(FOLLOW_expr_in_stat95);
                    expr2=expr();
                    _fsp--;

                    stream_expr.add(expr2.getTree());
                    char_literal3=(Token)input.LT(1);
                    match(input,7,FOLLOW_7_in_stat97); 
                    stream_7.add(char_literal3);


                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 15:18: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.next());

                    }



                    }
                    break;
                case 2 :
                    // Expr.g:16:4: ID '=' expr ';'
                    {
                    ID4=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_stat107); 
                    stream_ID.add(ID4);

                    char_literal5=(Token)input.LT(1);
                    match(input,8,FOLLOW_8_in_stat109); 
                    stream_8.add(char_literal5);

                    pushFollow(FOLLOW_expr_in_stat111);
                    expr6=expr();
                    _fsp--;

                    stream_expr.add(expr6.getTree());
                    char_literal7=(Token)input.LT(1);
                    match(input,7,FOLLOW_7_in_stat113); 
                    stream_7.add(char_literal7);


                    // AST REWRITE
                    // elements: ID, 8, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 16:21: -> ^( '=' ID expr )
                    {
                        // Expr.g:16:24: ^( '=' ID expr )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_8.next(), root_1);

                        adaptor.addChild(root_1, stream_ID.next());
                        adaptor.addChild(root_1, stream_expr.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }



                    }
                    break;
                case 3 :
                    // Expr.g:17:4: ';'
                    {
                    char_literal8=(Token)input.LT(1);
                    match(input,7,FOLLOW_7_in_stat129); 
                    stream_7.add(char_literal8);


                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 17:10: ->
                    {
                        root_0 = null;
                    }



                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end stat

    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expr
    // Expr.g:20:1: expr : multExpr ( '+' multExpr | '-' multExpr )* ;
    public final expr_return expr() throws RecognitionException {
        expr_return retval = new expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal10=null;
        Token char_literal12=null;
        multExpr_return multExpr9 = null;

        multExpr_return multExpr11 = null;

        multExpr_return multExpr13 = null;


        CommonTree char_literal10_tree=null;
        CommonTree char_literal12_tree=null;

        try {
            // Expr.g:21:2: ( multExpr ( '+' multExpr | '-' multExpr )* )
            // Expr.g:21:4: multExpr ( '+' multExpr | '-' multExpr )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_multExpr_in_expr145);
            multExpr9=multExpr();
            _fsp--;

            adaptor.addChild(root_0, multExpr9.getTree());
            // Expr.g:22:3: ( '+' multExpr | '-' multExpr )*
            loop3:
            do {
                int alt3=3;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==9) ) {
                    alt3=1;
                }
                else if ( (LA3_0==10) ) {
                    alt3=2;
                }


                switch (alt3) {
            	case 1 :
            	    // Expr.g:22:5: '+' multExpr
            	    {
            	    char_literal10=(Token)input.LT(1);
            	    match(input,9,FOLLOW_9_in_expr151); 
            	    char_literal10_tree = (CommonTree)adaptor.create(char_literal10);
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal10_tree, root_0);

            	    pushFollow(FOLLOW_multExpr_in_expr154);
            	    multExpr11=multExpr();
            	    _fsp--;

            	    adaptor.addChild(root_0, multExpr11.getTree());

            	    }
            	    break;
            	case 2 :
            	    // Expr.g:23:5: '-' multExpr
            	    {
            	    char_literal12=(Token)input.LT(1);
            	    match(input,10,FOLLOW_10_in_expr160); 
            	    char_literal12_tree = (CommonTree)adaptor.create(char_literal12);
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal12_tree, root_0);

            	    pushFollow(FOLLOW_multExpr_in_expr163);
            	    multExpr13=multExpr();
            	    _fsp--;

            	    adaptor.addChild(root_0, multExpr13.getTree());

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expr

    public static class multExpr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start multExpr
    // Expr.g:27:1: multExpr : atom ( '*' atom | '/' atom )* ;
    public final multExpr_return multExpr() throws RecognitionException {
        multExpr_return retval = new multExpr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal15=null;
        Token char_literal17=null;
        atom_return atom14 = null;

        atom_return atom16 = null;

        atom_return atom18 = null;


        CommonTree char_literal15_tree=null;
        CommonTree char_literal17_tree=null;

        try {
            // Expr.g:28:2: ( atom ( '*' atom | '/' atom )* )
            // Expr.g:28:4: atom ( '*' atom | '/' atom )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_atom_in_multExpr180);
            atom14=atom();
            _fsp--;

            adaptor.addChild(root_0, atom14.getTree());
            // Expr.g:29:3: ( '*' atom | '/' atom )*
            loop4:
            do {
                int alt4=3;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==11) ) {
                    alt4=1;
                }
                else if ( (LA4_0==12) ) {
                    alt4=2;
                }


                switch (alt4) {
            	case 1 :
            	    // Expr.g:29:5: '*' atom
            	    {
            	    char_literal15=(Token)input.LT(1);
            	    match(input,11,FOLLOW_11_in_multExpr186); 
            	    char_literal15_tree = (CommonTree)adaptor.create(char_literal15);
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal15_tree, root_0);

            	    pushFollow(FOLLOW_atom_in_multExpr189);
            	    atom16=atom();
            	    _fsp--;

            	    adaptor.addChild(root_0, atom16.getTree());

            	    }
            	    break;
            	case 2 :
            	    // Expr.g:30:5: '/' atom
            	    {
            	    char_literal17=(Token)input.LT(1);
            	    match(input,12,FOLLOW_12_in_multExpr195); 
            	    char_literal17_tree = (CommonTree)adaptor.create(char_literal17);
            	    root_0 = (CommonTree)adaptor.becomeRoot(char_literal17_tree, root_0);

            	    pushFollow(FOLLOW_atom_in_multExpr198);
            	    atom18=atom();
            	    _fsp--;

            	    adaptor.addChild(root_0, atom18.getTree());

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end multExpr

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start atom
    // Expr.g:34:1: atom : ( INT | ID | '(' expr ')' );
    public final atom_return atom() throws RecognitionException {
        atom_return retval = new atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token INT19=null;
        Token ID20=null;
        Token char_literal21=null;
        Token char_literal23=null;
        expr_return expr22 = null;


        CommonTree INT19_tree=null;
        CommonTree ID20_tree=null;
        CommonTree char_literal21_tree=null;
        CommonTree char_literal23_tree=null;

        try {
            // Expr.g:35:2: ( INT | ID | '(' expr ')' )
            int alt5=3;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt5=1;
                }
                break;
            case ID:
                {
                alt5=2;
                }
                break;
            case 13:
                {
                alt5=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("34:1: atom : ( INT | ID | '(' expr ')' );", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // Expr.g:35:4: INT
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    INT19=(Token)input.LT(1);
                    match(input,INT,FOLLOW_INT_in_atom215); 
                    INT19_tree = (CommonTree)adaptor.create(INT19);
                    adaptor.addChild(root_0, INT19_tree);


                    }
                    break;
                case 2 :
                    // Expr.g:36:4: ID
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    ID20=(Token)input.LT(1);
                    match(input,ID,FOLLOW_ID_in_atom220); 
                    ID20_tree = (CommonTree)adaptor.create(ID20);
                    adaptor.addChild(root_0, ID20_tree);


                    }
                    break;
                case 3 :
                    // Expr.g:37:4: '(' expr ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    char_literal21=(Token)input.LT(1);
                    match(input,13,FOLLOW_13_in_atom225); 
                    pushFollow(FOLLOW_expr_in_atom228);
                    expr22=expr();
                    _fsp--;

                    adaptor.addChild(root_0, expr22.getTree());
                    char_literal23=(Token)input.LT(1);
                    match(input,14,FOLLOW_14_in_atom230); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end atom


 

    public static final BitSet FOLLOW_stat_in_prog84 = new BitSet(new long[]{0x00000000000020B2L});
    public static final BitSet FOLLOW_expr_in_stat95 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_stat97 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_stat107 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_8_in_stat109 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_expr_in_stat111 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_7_in_stat113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_stat129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multExpr_in_expr145 = new BitSet(new long[]{0x0000000000000602L});
    public static final BitSet FOLLOW_9_in_expr151 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_multExpr_in_expr154 = new BitSet(new long[]{0x0000000000000602L});
    public static final BitSet FOLLOW_10_in_expr160 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_multExpr_in_expr163 = new BitSet(new long[]{0x0000000000000602L});
    public static final BitSet FOLLOW_atom_in_multExpr180 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_11_in_multExpr186 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_atom_in_multExpr189 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_12_in_multExpr195 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_atom_in_multExpr198 = new BitSet(new long[]{0x0000000000001802L});
    public static final BitSet FOLLOW_INT_in_atom215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_atom220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_13_in_atom225 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_expr_in_atom228 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_atom230 = new BitSet(new long[]{0x0000000000000002L});

}