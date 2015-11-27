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

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author Sampsa Sohlman
 */
public class SQLCheckerConnection implements Connection {

	public SQLCheckerConnection(Connection connection, SQLChecker sqlChecker) {
		_connection = connection;
		_sqlChecker = sqlChecker;
	}

	public void abort(Executor executor) throws SQLException {
		_connection.abort(executor);
	}

	public void clearWarnings() throws SQLException {
		_connection.clearWarnings();
	}

	public void close() throws SQLException {
		_connection.close();
	}

	public void commit() throws SQLException {
		_connection.commit();
	}

	public Array createArrayOf(String typeName, Object[] elements)
		throws SQLException {

		return _connection.createArrayOf(typeName, elements);
	}

	public Blob createBlob() throws SQLException {
		return _connection.createBlob();
	}

	public Clob createClob() throws SQLException {
		return _connection.createClob();
	}

	public NClob createNClob() throws SQLException {
		return _connection.createNClob();
	}

	public SQLXML createSQLXML() throws SQLException {
		return _connection.createSQLXML();
	}

	public Statement createStatement() throws SQLException {
		Statement statement = _connection.createStatement();
		return new SQLCheckerStatement(statement, _sqlChecker);
	}

	public Statement createStatement(
			int resultSetType, int resultSetConcurrency)
		throws SQLException {

		Statement statement = _connection.createStatement(
			resultSetType, resultSetConcurrency);

		return new SQLCheckerStatement(statement, _sqlChecker);
	}

	public Statement createStatement(
			int resultSetType, int resultSetConcurrency,
			int resultSetHoldability)
		throws SQLException {

		Statement statement =
			_connection.createStatement(resultSetType, resultSetConcurrency,
			resultSetHoldability);
		return new SQLCheckerStatement(statement, _sqlChecker);
	}

	public Struct createStruct(String typeName, Object[] attributes)
		throws SQLException {

		return _connection.createStruct(typeName, attributes);
	}

	public boolean getAutoCommit() throws SQLException {
		return _connection.getAutoCommit();
	}

	public String getCatalog() throws SQLException {
		return _connection.getCatalog();
	}

	public Properties getClientInfo() throws SQLException {
		return _connection.getClientInfo();
	}

	public String getClientInfo(String name) throws SQLException {
		return _connection.getClientInfo(name);
	}

	public int getHoldability() throws SQLException {
		return _connection.getHoldability();
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		return _connection.getMetaData();
	}

	public int getNetworkTimeout() throws SQLException {
		return _connection.getNetworkTimeout();
	}

	public String getSchema() throws SQLException {
		return _connection.getSchema();
	}

	public int getTransactionIsolation() throws SQLException {
		return _connection.getTransactionIsolation();
	}

	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return _connection.getTypeMap();
	}

	public SQLWarning getWarnings() throws SQLException {
		return _connection.getWarnings();
	}

	public boolean isClosed() throws SQLException {
		return _connection.isClosed();
	}

	public boolean isReadOnly() throws SQLException {
		return _connection.isReadOnly();
	}

	public boolean isValid(int timeout) throws SQLException {
		return _connection.isValid(timeout);
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return _connection.isWrapperFor(iface);
	}

	public String nativeSQL(String sql) throws SQLException {
		return _connection.nativeSQL(sql);
	}

	public CallableStatement prepareCall(String sql) throws SQLException {
		return _connection.prepareCall(sql);
	}

	public CallableStatement prepareCall(
			String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException {

		return _connection.prepareCall(
			sql, resultSetType, resultSetConcurrency);
	}

	public CallableStatement prepareCall(
			String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability)
		throws SQLException {

		return _connection.prepareCall(
			sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql) throws SQLException {
		_sqlChecker.verifySelectSQL(sql);
		return _connection.prepareStatement(sql);
	}

	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
		throws SQLException {

		return _connection.prepareStatement(sql, autoGeneratedKeys);
	}

	public PreparedStatement prepareStatement(
			String sql, int resultSetType, int resultSetConcurrency)
		throws SQLException {

		return _connection.prepareStatement(
			sql, resultSetType, resultSetConcurrency);
	}

	public PreparedStatement prepareStatement(
			String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability)
		throws SQLException {

		return _connection.prepareStatement(
			sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
		throws SQLException {

		return _connection.prepareStatement(sql, columnIndexes);
	}

	public PreparedStatement prepareStatement(String sql, String[] columnNames)
		throws SQLException {

		return _connection.prepareStatement(sql, columnNames);
	}

	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		_connection.releaseSavepoint(savepoint);
	}

	public void rollback() throws SQLException {
		_connection.rollback();
	}

	public void rollback(Savepoint savepoint) throws SQLException {
		_connection.rollback(savepoint);
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		_connection.setAutoCommit(autoCommit);
	}

	public void setCatalog(String catalog) throws SQLException {
		_connection.setCatalog(catalog);
	}

	public void setClientInfo(Properties properties)
		throws SQLClientInfoException {

		_connection.setClientInfo(properties);
	}

	public void setClientInfo(String name, String value)
		throws SQLClientInfoException {

		_connection.setClientInfo(name, value);
	}

	public void setHoldability(int holdability) throws SQLException {
		_connection.setHoldability(holdability);
	}

	public void setNetworkTimeout(Executor executor, int milliseconds)
		throws SQLException {

		_connection.setNetworkTimeout(executor, milliseconds);
	}

	public void setReadOnly(boolean readOnly) throws SQLException {
		_connection.setReadOnly(readOnly);
	}

	public Savepoint setSavepoint() throws SQLException {
		return _connection.setSavepoint();
	}

	public Savepoint setSavepoint(String name) throws SQLException {
		return _connection.setSavepoint(name);
	}

	public void setSchema(String schema) throws SQLException {
		_connection.setSchema(schema);
	}

	public void setTransactionIsolation(int level) throws SQLException {
		_connection.setTransactionIsolation(level);
	}

	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		_connection.setTypeMap(map);
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return _connection.unwrap(iface);
	}

	private final Connection _connection;
	private final SQLChecker _sqlChecker;

}