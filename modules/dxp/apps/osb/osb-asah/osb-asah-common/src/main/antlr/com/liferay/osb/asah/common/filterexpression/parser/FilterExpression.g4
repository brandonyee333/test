grammar FilterExpression;

options {
	language = Java;
}

@header {
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
}

expression
	: logicalOrExpression EOF
	;

logicalOrExpression
	: logicalOrExpression OR logicalAndExpression # OrExpression
	| logicalAndExpression # ToLogicalAndExpression
	;

logicalAndExpression
	: logicalAndExpression AND equalityExpression # AndExpression
	| equalityExpression # ToEqualityExpression
	;

equalityExpression
	: equalityExpression EQ comparisonExpression # EqualsExpression
	| equalityExpression NEQ comparisonExpression # NotEqualsExpression
	| comparisonExpression #ToComparisonExpression
	;

comparisonExpression
	: comparisonExpression GT booleanOperandExpression # GreaterThanExpression
	| comparisonExpression GE booleanOperandExpression # GreaterThanOrEqualsExpression
	| comparisonExpression LT booleanOperandExpression # LessThanExpression
	| comparisonExpression LE booleanOperandExpression # LessThanOrEqualsExpression
	| booleanUnaryExpression #ToBooleanUnaryExpression
	;

booleanUnaryExpression
	: NOT booleanUnaryExpression # NotExpression
	| booleanOperandExpression # ToBooleanOperandExpression
	;

booleanOperandExpression
	: logicalTerm # ToLogicalTerm
	| operandExpression # ToOperandEpression
	| LPAREN logicalOrExpression RPAREN # BooleanParenthesis
	;

logicalTerm
	: (TRUE | FALSE) # LogicalConstant
	| IDENTIFIER # LogicalVariable
	| QUALIFIED_IDENTIFIER # LogicalQualifiedVariable
	;

operandExpression
	: literal # ToLiteral
	| functionCallExpression # ToFunctionCallExpression
	| filterCountExpression # ToFilterCountExpression
	| filterExpression # ToFilterExpression
	;

filterCountExpression
	: namespace=IDENTIFIER DOT FILTER_BY_COUNT LPAREN filterParameter COMMA operatorParameter COMMA valueParameter RPAREN
	;

filterExpression
	: namespace=IDENTIFIER DOT FILTER LPAREN filterParameter RPAREN
	;

functionCallExpression
	: functionName=IDENTIFIER LPAREN functionParameters? RPAREN
	;

functionParameters
	: functionParameter (COMMA functionParameter)*
	;

functionParameter
	: logicalOrExpression
	;

filterParameter
	: 'filter' EQ STRING
	;

operatorParameter
	: 'operator' EQ STRING
	;

valueParameter
	: 'value' EQ literal
	;

literal
	: FloatingPointLiteral # FloatingPointLiteral
	| IntegerLiteral # IntegerLiteral
	| STRING # StringLiteral
	;

IntegerLiteral
    : Digits
    ;

FloatingPointLiteral
    : DecimalFloatingPointLiteral
    ;

DecimalFloatingPointLiteral
	: Digits '.' Digits? ExponentPart?
	| '.' Digits ExponentPart?
	| Digits ExponentPart
	;

AND
	: '&&'
	| '&'
	| 'and'
	| 'AND'
	;

FILTER_BY_COUNT
	: 'filterByCount'
	;

FILTER
	: 'filter'
	;

COMMA
	: ','
	;

DIV	: '/'
	;

DOT
	: '.'
	;

EQ
	: 'eq'
	| '='
	;

FALSE
	: 'false'
	| 'FALSE'
	;

GE
	: 'ge'
	;

GT
	: 'gt'
	;

LBRACKET
	: '['
	;

LE
	: 'le'
	;

LPAREN
	: '('
	;

LT
	: 'lt'
	;

MINUS
	: '-'
	;

MULT
	: '*'
	;

NEQ
	: 'ne'
	;

NOT
	: 'not'
	| 'NOT'
	;

NULL
	: 'null'
	| 'NULL'
	;

OR
	: '||'
	| '|'
	| 'or'
	| 'OR'
	;

PLUS
	: '+'
	;

RBRACKET
	: ']'
	;

RPAREN
	: ')'
	;

STRING
	: '"' (~["])* '"'
	| '\'' (~['])* '\''
	;

TRUE
	: 'true'
	| 'TRUE'
	;

IDENTIFIER
	: NameStartChar NameChar*
	;

QUALIFIED_IDENTIFIER
	: IDENTIFIER ( '/' IDENTIFIER )+
	;
WS
	: [ \r\t\u000C\n]+ -> skip
	;

fragment
Digits
    : Digit+
    ;

fragment
Digit
	: [0-9]
	;

fragment
ExponentIndicator
    : [eE]
    ;

fragment
ExponentPart
    :   ExponentIndicator SignedInteger
    ;

fragment
NameChar
   : NameStartChar
   | '0'..'9'
   | '\u0300'..'\u036F'
   | '\u203F'..'\u2040'
   ;

fragment
NameStartChar
   : '_'
   | 'A'..'Z' | 'a'..'z'
   | '\u00C0'..'\u00D6'
   | '\u00D8'..'\u00F6'
   | '\u00F8'..'\u02FF'
   | '\u0370'..'\u037D'
   | '\u037F'..'\u1FFF'
   | '\u200C'..'\u200D'
   | '\u2070'..'\u218F'
   | '\u2C00'..'\u2FEF'
   | '\u3001'..'\uD7FF'
   | '\uF900'..'\uFDCF'
   | '\uFDF0'..'\uFFFD'
   ;

fragment
SignedInteger
    :   Sign? Digits
    ;

fragment
Sign
    :   [+-]
    ;