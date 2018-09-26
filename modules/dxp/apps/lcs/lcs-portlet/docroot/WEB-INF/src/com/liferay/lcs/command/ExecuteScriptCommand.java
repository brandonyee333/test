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

package com.liferay.lcs.command;

import com.liferay.lcs.exception.CompressionException;
import com.liferay.lcs.messaging.ExecuteScriptCommandMessage;
import com.liferay.lcs.messaging.ExecuteScriptResponseMessage;
import com.liferay.lcs.platform.gateway.LCSGatewayService;
import com.liferay.petra.json.web.service.client.JSONWebServiceException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncPrintWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.scripting.ScriptingException;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.util.UnsyncPrintWriterPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Ivica Cardic
 */
public class ExecuteScriptCommand
	implements Command<ExecuteScriptCommandMessage> {

	public ExecuteScriptCommand(LCSGatewayService lcsGatewayService) {
		_lcsGatewayService = lcsGatewayService;
	}

	@Override
	public void execute(
		ExecuteScriptCommandMessage executeScriptCommandMessage) {

		if (_log.isTraceEnabled()) {
			_log.trace("Executing execute script command");
		}

		try {
			executeScript(executeScriptCommandMessage);
		}
		catch (Exception e) {
			StringBuilder sb = new StringBuilder(4);

			sb.append("Failed to execute script");

			if (e instanceof CompressionException ||
				e instanceof JSONWebServiceException) {

				sb.append(". Unable to send execution status feedback to LCS ");
				sb.append("gateway. Please note that even script executed, ");
				sb.append("execution result won't be registered at LCS ");
				sb.append("dashboard");
			}

			_log.error(sb.toString(), e);
		}
	}

	public void executeScript(
			ExecuteScriptCommandMessage executeScriptCommandMessage)

		throws CompressionException, JSONWebServiceException {

		Map<String, Object> inputObjects = new HashMap<>();

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		UnsyncPrintWriter unsyncPrintWriter = UnsyncPrintWriterPool.borrow(
			unsyncByteArrayOutputStream);

		inputObjects.put("out", unsyncPrintWriter);

		String script = executeScriptCommandMessage.getScript();

		if (_log.isDebugEnabled()) {
			_log.debug("Executing script " + script);
		}

		String result = null;

		String errorMessage = null;

		try {
			ScriptingUtil.exec(
				(Set<String>)null, inputObjects, "groovy", script,
				new String[0]);

			unsyncPrintWriter.flush();

			result = unsyncByteArrayOutputStream.toString();
		}
		catch (ScriptingException se) {
			errorMessage = se.getMessage();
		}

		_lcsGatewayService.sendMessage(
			_getExecuteScriptResponseMessage(
				executeScriptCommandMessage, result, errorMessage));
	}

	private ExecuteScriptResponseMessage _getExecuteScriptResponseMessage(
		ExecuteScriptCommandMessage executeScriptCommandMessage, String result,
		String errorMessage) {

		ExecuteScriptResponseMessage executeScriptResponseMessage =
			new ExecuteScriptResponseMessage();

		executeScriptResponseMessage.setCorrelationId(
			executeScriptCommandMessage.getCorrelationId());
		executeScriptResponseMessage.setCreateTime(System.currentTimeMillis());
		executeScriptResponseMessage.setErrorMessage(errorMessage);
		executeScriptResponseMessage.setKey(
			executeScriptCommandMessage.getKey());
		executeScriptResponseMessage.setResult(result);

		return executeScriptResponseMessage;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExecuteScriptCommand.class);

	private final LCSGatewayService _lcsGatewayService;

}