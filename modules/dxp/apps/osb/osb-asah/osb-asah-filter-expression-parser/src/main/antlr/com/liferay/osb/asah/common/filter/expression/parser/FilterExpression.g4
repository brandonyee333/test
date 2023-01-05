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

package com.liferay.osb.asah.common.filter.expression.parser;
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
	| LPAREN logicalOrExpression RPAREN # BooleanParenthesis
	;

logicalTerm
	: literal # ToLiteral
	| functionCallExpression # ToFunctionCallExpression
	| filterExpression # ToFilterExpression
	| IDENTIFIER # LogicalVariable
	;

literal
	: FLOATING_POINT_LITERAL # FloatingPointLiteral
	| INTEGER_LITERAL # IntegerLiteral
	| ('true' | 'false') # BooleanLiteral
	| 'null' # NullLiteral
	| STRING_LITERAL # StringLiteral
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

filterExpression
	: domainName=IDENTIFIER '.filter(filter=' filter=STRING_LITERAL ')'
	;

AND
	: '&&'
	| '&'
	| 'and'
	| 'AND'
	;

COMMA
	: ','
	;

EQ
	: 'eq'
	| '='
	;

FLOATING_POINT_LITERAL
    : MINUS? DIGITS '.' DIGITS?
    | MINUS? '.' DIGITS
    ;

NEQ
	: 'ne'
	;

GE
	: 'ge'
	;

GT
	: 'gt'
	;

INTEGER_LITERAL
	: MINUS? DIGITS
	;

LE
	: 'le'
	;

LPAREN
	: '('
	;

RPAREN
	: ')'
	;

LT
	: 'lt'
	;

NOT
	: 'not'
	| 'NOT'
	;

OR
	: '||'
	| '|'
	| 'or'
	| 'OR'
	;

STRING_LITERAL
	: '"' ( '""' | ~["] )* '"'
	| '\'' ( '\'\'' | ~['] )* '\''
	;

IDENTIFIER
	: NAME_START_CHAR NAME_CHAR*
    | NAME_START_CHAR NAME_CHAR* '/' NAME_START_CHAR NAME_CHAR*
	| NAME_START_CHAR NAME_CHAR* '/' NAME_START_CHAR NAME_CHAR* '/value'
	;

fragment
DIGITS
    : [0-9]+
    ;

fragment
MINUS
	: '-'
	;

fragment
NAME_CHAR
   : NAME_START_CHAR
   | '0'..'9'
   ;

fragment
NAME_START_CHAR
   : '_'
   | 'A'..'Z' | 'a'..'z'
   ;

WS
	: [ \r\t\u000C\n]+ -> skip
	;
