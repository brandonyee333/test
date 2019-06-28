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

package com.liferay.lcs.client.internal.command;

/**
 * @author Igor Beslic
 */
public class CommandValidator {

	public CommandValidator(CommandValidationResult commandValidationResult) {
		this(commandValidationResult, "Detailed message is not available");
	}

	public CommandValidator(
		CommandValidationResult commandValidationResult,
		String validationMessage) {

		_commandValidationResult = commandValidationResult;
		_validationMessage = validationMessage;
	}

	public String getErrorMessage() {
		return String.format(
			"Validation failed with cause {%s} and message {%s}",
			_commandValidationResult, _validationMessage);
	}

	public boolean isValid() {
		if (_commandValidationResult == CommandValidationResult.VALID) {
			return true;
		}

		return false;
	}

	private final CommandValidationResult _commandValidationResult;
	private final String _validationMessage;

}