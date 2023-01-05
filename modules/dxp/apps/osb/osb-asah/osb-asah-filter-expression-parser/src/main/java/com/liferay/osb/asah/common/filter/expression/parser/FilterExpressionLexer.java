// Generated from FilterExpression.g4 by ANTLR 4.5.3

/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.asah.common.filter.expression.parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FilterExpressionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, AND=5, COMMA=6, EQ=7, FLOATING_POINT_LITERAL=8, 
		NEQ=9, GE=10, GT=11, INTEGER_LITERAL=12, LE=13, LPAREN=14, RPAREN=15, 
		LT=16, NOT=17, OR=18, STRING_LITERAL=19, VARIABLE_IDENTIFIER=20, WS=21;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "AND", "COMMA", "EQ", "FLOATING_POINT_LITERAL", 
		"NEQ", "GE", "GT", "INTEGER_LITERAL", "LE", "LPAREN", "RPAREN", "LT", 
		"NOT", "OR", "STRING_LITERAL", "VARIABLE_IDENTIFIER", "DIGITS", "MINUS", 
		"NAME_CHAR", "NAME_START_CHAR", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'.filter(filter='", "'true'", "'false'", "'null'", null, "','", 
		null, null, "'ne'", "'ge'", "'gt'", null, "'le'", "'('", "')'", "'lt'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "AND", "COMMA", "EQ", "FLOATING_POINT_LITERAL", 
		"NEQ", "GE", "GT", "INTEGER_LITERAL", "LE", "LPAREN", "RPAREN", "LT", 
		"NOT", "OR", "STRING_LITERAL", "VARIABLE_IDENTIFIER", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FilterExpressionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FilterExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\27\u00f7\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6_\n\6\3\7\3\7\3\b\3\b\3\b"+
		"\5\bf\n\b\3\t\5\ti\n\t\3\t\3\t\3\t\5\tn\n\t\3\t\5\tq\n\t\3\t\3\t\5\tu"+
		"\n\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\5\r\u0081\n\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u0095\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u009e"+
		"\n\23\3\24\3\24\3\24\3\24\7\24\u00a4\n\24\f\24\16\24\u00a7\13\24\3\24"+
		"\3\24\3\24\3\24\3\24\7\24\u00ae\n\24\f\24\16\24\u00b1\13\24\3\24\5\24"+
		"\u00b4\n\24\3\25\3\25\7\25\u00b8\n\25\f\25\16\25\u00bb\13\25\3\25\3\25"+
		"\7\25\u00bf\n\25\f\25\16\25\u00c2\13\25\3\25\3\25\3\25\7\25\u00c7\n\25"+
		"\f\25\16\25\u00ca\13\25\3\25\3\25\7\25\u00ce\n\25\f\25\16\25\u00d1\13"+
		"\25\3\25\3\25\3\25\7\25\u00d6\n\25\f\25\16\25\u00d9\13\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\5\25\u00e2\n\25\3\26\6\26\u00e5\n\26\r\26\16\26"+
		"\u00e6\3\27\3\27\3\30\3\30\5\30\u00ed\n\30\3\31\3\31\3\32\6\32\u00f2\n"+
		"\32\r\32\16\32\u00f3\3\32\3\32\2\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\2-\2"+
		"/\2\61\2\63\27\3\2\7\3\2$$\3\2))\3\2\62;\5\2C\\aac|\5\2\13\f\16\17\"\""+
		"\u010e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2"+
		"\2\5E\3\2\2\2\7J\3\2\2\2\tP\3\2\2\2\13^\3\2\2\2\r`\3\2\2\2\17e\3\2\2\2"+
		"\21t\3\2\2\2\23v\3\2\2\2\25y\3\2\2\2\27|\3\2\2\2\31\u0080\3\2\2\2\33\u0084"+
		"\3\2\2\2\35\u0087\3\2\2\2\37\u0089\3\2\2\2!\u008b\3\2\2\2#\u0094\3\2\2"+
		"\2%\u009d\3\2\2\2\'\u00b3\3\2\2\2)\u00e1\3\2\2\2+\u00e4\3\2\2\2-\u00e8"+
		"\3\2\2\2/\u00ec\3\2\2\2\61\u00ee\3\2\2\2\63\u00f1\3\2\2\2\65\66\7\60\2"+
		"\2\66\67\7h\2\2\678\7k\2\289\7n\2\29:\7v\2\2:;\7g\2\2;<\7t\2\2<=\7*\2"+
		"\2=>\7h\2\2>?\7k\2\2?@\7n\2\2@A\7v\2\2AB\7g\2\2BC\7t\2\2CD\7?\2\2D\4\3"+
		"\2\2\2EF\7v\2\2FG\7t\2\2GH\7w\2\2HI\7g\2\2I\6\3\2\2\2JK\7h\2\2KL\7c\2"+
		"\2LM\7n\2\2MN\7u\2\2NO\7g\2\2O\b\3\2\2\2PQ\7p\2\2QR\7w\2\2RS\7n\2\2ST"+
		"\7n\2\2T\n\3\2\2\2UV\7(\2\2V_\7(\2\2W_\7(\2\2XY\7c\2\2YZ\7p\2\2Z_\7f\2"+
		"\2[\\\7C\2\2\\]\7P\2\2]_\7F\2\2^U\3\2\2\2^W\3\2\2\2^X\3\2\2\2^[\3\2\2"+
		"\2_\f\3\2\2\2`a\7.\2\2a\16\3\2\2\2bc\7g\2\2cf\7s\2\2df\7?\2\2eb\3\2\2"+
		"\2ed\3\2\2\2f\20\3\2\2\2gi\5-\27\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\5+"+
		"\26\2km\7\60\2\2ln\5+\26\2ml\3\2\2\2mn\3\2\2\2nu\3\2\2\2oq\5-\27\2po\3"+
		"\2\2\2pq\3\2\2\2qr\3\2\2\2rs\7\60\2\2su\5+\26\2th\3\2\2\2tp\3\2\2\2u\22"+
		"\3\2\2\2vw\7p\2\2wx\7g\2\2x\24\3\2\2\2yz\7i\2\2z{\7g\2\2{\26\3\2\2\2|"+
		"}\7i\2\2}~\7v\2\2~\30\3\2\2\2\177\u0081\5-\27\2\u0080\177\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\5+\26\2\u0083\32\3\2\2"+
		"\2\u0084\u0085\7n\2\2\u0085\u0086\7g\2\2\u0086\34\3\2\2\2\u0087\u0088"+
		"\7*\2\2\u0088\36\3\2\2\2\u0089\u008a\7+\2\2\u008a \3\2\2\2\u008b\u008c"+
		"\7n\2\2\u008c\u008d\7v\2\2\u008d\"\3\2\2\2\u008e\u008f\7p\2\2\u008f\u0090"+
		"\7q\2\2\u0090\u0095\7v\2\2\u0091\u0092\7P\2\2\u0092\u0093\7Q\2\2\u0093"+
		"\u0095\7V\2\2\u0094\u008e\3\2\2\2\u0094\u0091\3\2\2\2\u0095$\3\2\2\2\u0096"+
		"\u0097\7~\2\2\u0097\u009e\7~\2\2\u0098\u009e\7~\2\2\u0099\u009a\7q\2\2"+
		"\u009a\u009e\7t\2\2\u009b\u009c\7Q\2\2\u009c\u009e\7T\2\2\u009d\u0096"+
		"\3\2\2\2\u009d\u0098\3\2\2\2\u009d\u0099\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"&\3\2\2\2\u009f\u00a5\7$\2\2\u00a0\u00a1\7$\2\2\u00a1\u00a4\7$\2\2\u00a2"+
		"\u00a4\n\2\2\2\u00a3\u00a0\3\2\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2"+
		"\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a8\u00b4\7$\2\2\u00a9\u00af\7)\2\2\u00aa\u00ab\7)\2"+
		"\2\u00ab\u00ae\7)\2\2\u00ac\u00ae\n\3\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ac"+
		"\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0"+
		"\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b4\7)\2\2\u00b3\u009f\3\2"+
		"\2\2\u00b3\u00a9\3\2\2\2\u00b4(\3\2\2\2\u00b5\u00b9\5\61\31\2\u00b6\u00b8"+
		"\5/\30\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00ba\u00e2\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc\u00c0\5\61"+
		"\31\2\u00bd\u00bf\5/\30\2\u00be\u00bd\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0"+
		"\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c3\3\2\2\2\u00c2\u00c0\3\2"+
		"\2\2\u00c3\u00c4\7\61\2\2\u00c4\u00c8\5\61\31\2\u00c5\u00c7\5/\30\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\u00e2\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cf\5\61\31\2\u00cc"+
		"\u00ce\5/\30\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd\3\2"+
		"\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d2\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2"+
		"\u00d3\7\61\2\2\u00d3\u00d7\5\61\31\2\u00d4\u00d6\5/\30\2\u00d5\u00d4"+
		"\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8"+
		"\u00da\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\7\61\2\2\u00db\u00dc\7"+
		"x\2\2\u00dc\u00dd\7c\2\2\u00dd\u00de\7n\2\2\u00de\u00df\7w\2\2\u00df\u00e0"+
		"\7g\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00b5\3\2\2\2\u00e1\u00bc\3\2\2\2\u00e1"+
		"\u00cb\3\2\2\2\u00e2*\3\2\2\2\u00e3\u00e5\t\4\2\2\u00e4\u00e3\3\2\2\2"+
		"\u00e5\u00e6\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7,\3"+
		"\2\2\2\u00e8\u00e9\7/\2\2\u00e9.\3\2\2\2\u00ea\u00ed\5\61\31\2\u00eb\u00ed"+
		"\4\62;\2\u00ec\u00ea\3\2\2\2\u00ec\u00eb\3\2\2\2\u00ed\60\3\2\2\2\u00ee"+
		"\u00ef\t\5\2\2\u00ef\62\3\2\2\2\u00f0\u00f2\t\6\2\2\u00f1\u00f0\3\2\2"+
		"\2\u00f2\u00f3\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5"+
		"\3\2\2\2\u00f5\u00f6\b\32\2\2\u00f6\64\3\2\2\2\32\2^ehmpt\u0080\u0094"+
		"\u009d\u00a3\u00a5\u00ad\u00af\u00b3\u00b9\u00c0\u00c8\u00cf\u00d7\u00e1"+
		"\u00e6\u00ec\u00f3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
