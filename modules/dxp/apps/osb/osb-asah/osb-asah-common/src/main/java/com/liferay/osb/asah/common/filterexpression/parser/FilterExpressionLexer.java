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

package com.liferay.osb.asah.common.filterexpression.parser;

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
		T__0=1, T__1=2, T__2=3, COMMA=4, AND=5, OR=6, EQ=7, NEQ=8, GE=9, GT=10, 
		LE=11, LPAREN=12, RPAREN=13, LT=14, NOT=15, IDENTIFIER=16, FLOATING_POINT_LITERAL=17, 
		INTEGER_LITERAL=18, DIGITS=19, MINUS=20, STRING=21, WS=22;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "COMMA", "AND", "OR", "EQ", "NEQ", "GE", "GT", 
		"LE", "LPAREN", "RPAREN", "LT", "NOT", "IDENTIFIER", "NameChar", "NameStartChar", 
		"FLOATING_POINT_LITERAL", "INTEGER_LITERAL", "DIGITS", "MINUS", "STRING", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'true'", "'false'", "'null'", "','", null, null, null, "'ne'", 
		"'ge'", "'gt'", "'le'", "'('", "')'", "'lt'", null, null, null, null, 
		null, "'-'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "COMMA", "AND", "OR", "EQ", "NEQ", "GE", "GT", 
		"LE", "LPAREN", "RPAREN", "LT", "NOT", "IDENTIFIER", "FLOATING_POINT_LITERAL", 
		"INTEGER_LITERAL", "DIGITS", "MINUS", "STRING", "WS"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\30\u00be\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\5\7X\n\7\3\b\3\b\3\b\5\b]\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\5\20x\n\20\3\21\3\21\7\21|\n\21\f\21\16\21\177\13\21\3"+
		"\22\3\22\5\22\u0083\n\22\3\23\3\23\3\24\5\24\u0088\n\24\3\24\3\24\3\24"+
		"\5\24\u008d\n\24\3\24\5\24\u0090\n\24\3\24\3\24\5\24\u0094\n\24\3\25\5"+
		"\25\u0097\n\25\3\25\3\25\3\26\6\26\u009c\n\26\r\26\16\26\u009d\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\7\30\u00a6\n\30\f\30\16\30\u00a9\13\30\3\30\3"+
		"\30\3\30\3\30\3\30\7\30\u00b0\n\30\f\30\16\30\u00b3\13\30\3\30\5\30\u00b6"+
		"\n\30\3\31\6\31\u00b9\n\31\r\31\16\31\u00ba\3\31\3\31\2\2\32\3\3\5\4\7"+
		"\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\2%\2\'\23)\24+\25-\26/\27\61\30\3\2\7\5\2C\\aac|\3\2\62;\3\2$$\3\2)"+
		")\5\2\13\f\16\17\"\"\u00d1\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2"+
		"\2\2\2\61\3\2\2\2\3\63\3\2\2\2\58\3\2\2\2\7>\3\2\2\2\tC\3\2\2\2\13N\3"+
		"\2\2\2\rW\3\2\2\2\17\\\3\2\2\2\21^\3\2\2\2\23a\3\2\2\2\25d\3\2\2\2\27"+
		"g\3\2\2\2\31j\3\2\2\2\33l\3\2\2\2\35n\3\2\2\2\37w\3\2\2\2!y\3\2\2\2#\u0082"+
		"\3\2\2\2%\u0084\3\2\2\2\'\u0093\3\2\2\2)\u0096\3\2\2\2+\u009b\3\2\2\2"+
		"-\u009f\3\2\2\2/\u00b5\3\2\2\2\61\u00b8\3\2\2\2\63\64\7v\2\2\64\65\7t"+
		"\2\2\65\66\7w\2\2\66\67\7g\2\2\67\4\3\2\2\289\7h\2\29:\7c\2\2:;\7n\2\2"+
		";<\7u\2\2<=\7g\2\2=\6\3\2\2\2>?\7p\2\2?@\7w\2\2@A\7n\2\2AB\7n\2\2B\b\3"+
		"\2\2\2CD\7.\2\2D\n\3\2\2\2EF\7(\2\2FO\7(\2\2GO\7(\2\2HI\7c\2\2IJ\7p\2"+
		"\2JO\7f\2\2KL\7C\2\2LM\7P\2\2MO\7F\2\2NE\3\2\2\2NG\3\2\2\2NH\3\2\2\2N"+
		"K\3\2\2\2O\f\3\2\2\2PQ\7~\2\2QX\7~\2\2RX\7~\2\2ST\7q\2\2TX\7t\2\2UV\7"+
		"Q\2\2VX\7T\2\2WP\3\2\2\2WR\3\2\2\2WS\3\2\2\2WU\3\2\2\2X\16\3\2\2\2YZ\7"+
		"g\2\2Z]\7s\2\2[]\7?\2\2\\Y\3\2\2\2\\[\3\2\2\2]\20\3\2\2\2^_\7p\2\2_`\7"+
		"g\2\2`\22\3\2\2\2ab\7i\2\2bc\7g\2\2c\24\3\2\2\2de\7i\2\2ef\7v\2\2f\26"+
		"\3\2\2\2gh\7n\2\2hi\7g\2\2i\30\3\2\2\2jk\7*\2\2k\32\3\2\2\2lm\7+\2\2m"+
		"\34\3\2\2\2no\7n\2\2op\7v\2\2p\36\3\2\2\2qr\7p\2\2rs\7q\2\2sx\7v\2\2t"+
		"u\7P\2\2uv\7Q\2\2vx\7V\2\2wq\3\2\2\2wt\3\2\2\2x \3\2\2\2y}\5%\23\2z|\5"+
		"#\22\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\"\3\2\2\2\177}\3\2"+
		"\2\2\u0080\u0083\5%\23\2\u0081\u0083\4\62;\2\u0082\u0080\3\2\2\2\u0082"+
		"\u0081\3\2\2\2\u0083$\3\2\2\2\u0084\u0085\t\2\2\2\u0085&\3\2\2\2\u0086"+
		"\u0088\5-\27\2\u0087\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u008a\5+\26\2\u008a\u008c\7\60\2\2\u008b\u008d\5+\26\2\u008c"+
		"\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u0094\3\2\2\2\u008e\u0090\5-"+
		"\27\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\7\60\2\2\u0092\u0094\5+\26\2\u0093\u0087\3\2\2\2\u0093\u008f\3"+
		"\2\2\2\u0094(\3\2\2\2\u0095\u0097\5-\27\2\u0096\u0095\3\2\2\2\u0096\u0097"+
		"\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\5+\26\2\u0099*\3\2\2\2\u009a"+
		"\u009c\t\3\2\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009d\u009e\3\2\2\2\u009e,\3\2\2\2\u009f\u00a0\7/\2\2\u00a0.\3\2"+
		"\2\2\u00a1\u00a7\7$\2\2\u00a2\u00a3\7$\2\2\u00a3\u00a6\7$\2\2\u00a4\u00a6"+
		"\n\4\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7"+
		"\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a7\3\2"+
		"\2\2\u00aa\u00b6\7$\2\2\u00ab\u00b1\7)\2\2\u00ac\u00ad\7)\2\2\u00ad\u00b0"+
		"\7)\2\2\u00ae\u00b0\n\5\2\2\u00af\u00ac\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0"+
		"\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b4\3\2"+
		"\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b6\7)\2\2\u00b5\u00a1\3\2\2\2\u00b5"+
		"\u00ab\3\2\2\2\u00b6\60\3\2\2\2\u00b7\u00b9\t\6\2\2\u00b8\u00b7\3\2\2"+
		"\2\u00b9\u00ba\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc"+
		"\3\2\2\2\u00bc\u00bd\b\31\2\2\u00bd\62\3\2\2\2\25\2NW\\w}\u0082\u0087"+
		"\u008c\u008f\u0093\u0096\u009d\u00a5\u00a7\u00af\u00b1\u00b5\u00ba\3\b"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
