/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.metrics.rabbitmq.processor;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.osb.customer.metrics.rabbitmq.configuration.MetricsProcessorConfigurationValues;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;

import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Kyle Bischof
 */
public abstract class BaseMessageProcessor implements MessageProcessor {

	@Override
	public void process(
		String routingKey, String message, Map<String, Object> properties) {

		int errorCount = 0;

		while (true) {
			try {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
					message.trim());

				doProcess(jsonObject);

				break;
			}
			catch (Exception e) {
				_log.error(e, e);

				errorCount++;

				if (errorCount ==
						MetricsProcessorConfigurationValues.
							ERROR_NOTIFY_ATTEMPT_THRESHOLD) {

					sendEmail(StackTraceUtil.getStackTrace(e));

					errorCount = 0;
				}

				try {
					Thread.sleep(
						MetricsProcessorConfigurationValues.ERROR_SLEEP_MILLIS);
				}
				catch (InterruptedException ie) {
					_log.error(ie, ie);
				}
			}
		}
	}

	protected abstract void doProcess(JSONObject jsonObject) throws Exception;

	protected Map<String, String> getColumnMap(JSONObject jsonObject)
		throws Exception {

		Map<String, String> columnMap = new HashMap<>();

		Iterator<String> keysIterator = jsonObject.keys();

		while (keysIterator.hasNext()) {
			String columnName = keysIterator.next();

			String columnValue = jsonObject.getString(columnName);

			if (_badColumnNames.contains(columnName)) {
				columnName += StringPool.UNDERLINE;
			}

			columnMap.put(columnName, columnValue);
		}

		return columnMap;
	}

	protected String getColumnValue(String sql)
		throws IOException, SQLException {

		Connection connection = null;
		Statement statement = null;

		try {
			Context initialContext = new InitialContext();

			DataSource dataSource = (DataSource)initialContext.lookup(
				_DATA_SOURCE_CONTEXT);

			connection = dataSource.getConnection();

			statement = connection.createStatement();

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				String column = StringUtils.substringBetween(
					sql, "select ", " from");

				return rs.getString(column);
			}
		}
		catch (Exception e) {
			_log.error("Unable to connect to data source", e);
		}
		finally {
			DataAccess.cleanUp(connection, statement);
		}

		return null;
	}

	protected String getMappingTableName(String modelName, String mappingName) {
		StringBundler sb = new StringBundler(4);

		sb.append("OSB_Metrics");
		sb.append(TextFormatter.formatPlural(modelName));
		sb.append("_Metrics");
		sb.append(TextFormatter.formatPlural(mappingName));

		return sb.toString();
	}

	protected String getTableName(String modelName) {
		return "OSB_Metrics" + modelName;
	}

	protected void runSQL(String sql) throws IOException, SQLException {
		Connection connection = null;
		Statement statement = null;

		try {
			Context initialContext = new InitialContext();

			DataSource dataSource = (DataSource)initialContext.lookup(
				_DATA_SOURCE_CONTEXT);

			connection = dataSource.getConnection();

			statement = connection.createStatement();

			statement.execute(sql);
		}
		catch (Exception e) {
			_log.error("Unable to connect to data source", e);
		}
		finally {
			DataAccess.cleanUp(connection, statement);
		}
	}

	protected void sendEmail(String mailBody) {
		mailBody = StringUtil.replace(mailBody, CharPool.NEW_LINE, "<br />");

		try {
			InternetAddress from = new InternetAddress("noreply@liferay.com");
			InternetAddress to = new InternetAddress(
				MetricsProcessorConfigurationValues.ERROR_EMAIL);

			String mailSubject = "Auto Generated DXP Metrics Error Message";

			MailMessage mailMessage = new MailMessage(
				from, to, mailSubject, mailBody, true);

			MailServiceUtil.sendEmail(mailMessage);
		}
		catch (AddressException ae) {
			_log.error(ae, ae);
		}
	}

	private static final String _BAD_COLUMN_FILE =
		"/com/liferay/portal/tools/service/builder/dependencies" +
			"/bad_column_names.txt";

	private static final String _DATA_SOURCE_CONTEXT =
		"java:comp/env/jdbc/" +
			MetricsProcessorConfigurationValues.DATABASE_SCHEMA_NAME;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageProcessor.class);

	private static final Set<String> _badColumnNames = new HashSet<>();

	static {
		try {
			StringUtil.readLines(
				BaseMessageProcessor.class.getResourceAsStream(
					_BAD_COLUMN_FILE),
				_badColumnNames);

			System.out.println(StringUtil.merge(_badColumnNames));
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

}