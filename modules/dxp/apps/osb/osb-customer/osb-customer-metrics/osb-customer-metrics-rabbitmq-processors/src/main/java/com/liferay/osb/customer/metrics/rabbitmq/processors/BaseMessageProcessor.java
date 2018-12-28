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

package com.liferay.osb.customer.metrics.rabbitmq.processors;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.osb.customer.metrics.rabbitmq.configuration.MetricsProcessorConfigurationValues;
import com.liferay.osb.customer.rabbitmq.connector.processor.MessageProcessor;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StackTraceUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Map;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.sql.DataSource;

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

				errorCount += 1;

				if (errorCount ==
						MetricsProcessorConfigurationValues.
							ERROR_NOTIFY_ATTEMPT_THRESHOLD) {

					sendEmail(
						StringUtil.replace(
							StackTraceUtil.getStackTrace(e), CharPool.NEW_LINE,
							"<br />"));

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

	public void runSQL(String sql) throws IOException, SQLException {
		String DATASOURCE_CONTEXT =
			"java:comp/env/jdbc/" +
				MetricsProcessorConfigurationValues.DATABASE_SCHEMA_NAME;

		Connection connection = null;

		try {
			Context initialContext = new InitialContext();

			DataSource datasource = (DataSource)initialContext.lookup(
				DATASOURCE_CONTEXT);

			if (datasource != null) {
				connection = datasource.getConnection();

				Statement statement = connection.createStatement();

				statement.execute(sql);

				statement.close();

				connection.close();
			}
			else {
				_log.error("Failed to lookup datasource");
			}
		}
		catch (Exception e) {
			_log.error("Cannot get connection: " + e);
		}
	}

	protected abstract void doProcess(JSONObject jsonObject) throws Exception;

	protected void sendEmail(String mailBody) {
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

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageProcessor.class);

}