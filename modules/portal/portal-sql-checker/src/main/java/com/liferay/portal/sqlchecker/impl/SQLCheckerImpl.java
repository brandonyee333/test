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

package com.liferay.portal.sqlchecker.impl;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DatabaseUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.SQLException;
import java.util.List;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.expression.JdbcParameter;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubSelect;

/**
 * @author Sampsa Sohlman
 */
public class SQLCheckerImpl implements SQLChecker {

	public void verifySelectSQL(String sql) throws SQLException {
		DB db = DBFactoryUtil.getDB();
		
		if (db.getType().equals(DB.TYPE_HYPERSONIC)) {
			// CCJSqlParserUtil not yet compatible with Hypersonic
			return;
		}
		
		if (StringUtil.toLowerCase(sql).startsWith("show")) {
			return;
		}
		
		try {			
			Statement statement = CCJSqlParserUtil.parse(sql);

			if (statement instanceof Select) {
				verifySelectBody(((Select)statement).getSelectBody());
			}
		}
		catch (NoTableNameAtColumnSQLException e) {
			StringBundler sb = new StringBundler();
			sb.append("\nERROR SQL:");
			sb.append(sql);
			sb.append(" is not valid");

			e.addMessage(sb.toString());

			throw e;
		}
		catch (Throwable t) {
			StringBundler sb = new StringBundler();
			sb.append("ERROR SQL:");
			sb.append(sql);
			sb.append(" is not valid");
			_log.warn(sb.toString(), t);
		}
	}

	protected void verifyExpression(Expression expression, boolean isMultiTable)
		throws NoTableNameAtColumnSQLException {

		if (expression == null) {
			return;
		}

		if (expression instanceof Parenthesis) {
			Parenthesis parenthesis = (Parenthesis)expression;

			verifyExpression(parenthesis.getExpression(), isMultiTable);
		}
		else if (expression instanceof BinaryExpression) {
			BinaryExpression binaryExpression = (BinaryExpression)expression;
			verifyExpression(
				binaryExpression.getLeftExpression(), isMultiTable);
			verifyExpression(
				binaryExpression.getRightExpression(), isMultiTable);
		}
		else if (expression instanceof Column) {
			Column column = (Column)expression;

			if (isMultiTable && column.getTable().getName()== null) {
				throw new NoTableNameAtColumnSQLException(
					"Column name does not have table name prefix " +
					column.getColumnName());
			}
		}
		else if (expression instanceof Function) {
			Function function = (Function)expression;
			ExpressionList expressionList = function.getParameters();

			if (expressionList!= null) {
				for (
					Expression functionExpression :
						expressionList.getExpressions()) {

					verifyExpression(functionExpression, isMultiTable);
				}
			}
		}
		else if (expression instanceof InExpression ) {
			InExpression inExpression = (InExpression)expression;
			verifyExpression(inExpression.getLeftExpression(), isMultiTable);
			ItemsList itemsList = inExpression.getRightItemsList();

			if ( itemsList instanceof SubSelect ) {
				SubSelect subSelect = (SubSelect)itemsList;
				verifySelectBody(subSelect.getSelectBody());
			}
			else if (itemsList instanceof ExpressionList) {
				ExpressionList expressionList = (ExpressionList)itemsList;

				for (
					Expression subExpression :
						expressionList.getExpressions()) {

					verifyExpression(subExpression, isMultiTable);
				}
			}
			else {
				if (_log.isDebugEnabled()) {
					_log.debug(" itemsList:"+ itemsList.getClass());
				}
			}
		}
		else if (expression instanceof JdbcParameter) {

			// Ignor

		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug(" expression:" + expression.getClass().getName());
			}
		}
	}

	protected void verifyFromItem(FromItem fromItem)
		throws NoTableNameAtColumnSQLException {

		if (fromItem instanceof SubSelect) {
			SubSelect subSelect = (SubSelect)fromItem;
			verifySelectBody(subSelect.getSelectBody());
		}
		else if (fromItem instanceof Table) {

			// Ignore

		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug(" fromItem:"+ fromItem.getClass());
			}
		}
	}

	protected void verifySelectBody(SelectBody selectBody)
		throws NoTableNameAtColumnSQLException {

		if (selectBody instanceof SetOperationList ) {
			SetOperationList setOperationList = (SetOperationList)selectBody;

			for (SelectBody subSelectBody : setOperationList.getSelects()) {
				verifySelectBody(subSelectBody);
			}
		}
		else if (selectBody instanceof PlainSelect) {
			PlainSelect plainSelect = (PlainSelect)selectBody;

			List<Join> joins = plainSelect.getJoins();

			boolean isMultiTable = joins!= null && !joins.isEmpty();

			FromItem fromItem = plainSelect.getFromItem();
			verifyFromItem(fromItem);

			if (joins!= null) {
				for (Join join : joins) {
					verifyExpression(join.getOnExpression(), isMultiTable);
					verifyFromItem(fromItem);
				}
			}

			List<SelectItem> list = plainSelect.getSelectItems();

			for ( SelectItem selectItem : list) {
				if (selectItem instanceof SelectExpressionItem) {
					SelectExpressionItem selectExpressionItem =
						(SelectExpressionItem)selectItem;
					verifyExpression(
						selectExpressionItem.getExpression(), isMultiTable);
				}
				else if (_log.isDebugEnabled()) {
					_log.debug("selectItem :" + selectItem.getClass());
				}
			}

			Expression whereExpression = plainSelect.getWhere();

			verifyExpression(whereExpression, isMultiTable);
			verifyExpression(plainSelect.getHaving(), isMultiTable);

			List<Expression> columnReferences =
				plainSelect.getGroupByColumnReferences();

			if (columnReferences!= null) {
				for (Expression expression : columnReferences ) {
					verifyExpression(expression, isMultiTable);
				}
			}

			List<OrderByElement> orderByElements =
				plainSelect.getOrderByElements();

			if (orderByElements!= null) {
				for (OrderByElement orderByElement : orderByElements ) {
					verifyExpression(
						orderByElement.getExpression(), isMultiTable);
				}
			}
		}
		else if (_log.isDebugEnabled()) {
			_log.debug("selectBody:" +selectBody);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(SQLCheckerImpl.class);

}