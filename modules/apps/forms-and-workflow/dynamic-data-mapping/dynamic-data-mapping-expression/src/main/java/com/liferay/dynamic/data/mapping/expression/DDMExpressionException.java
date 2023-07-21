/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.expression;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Marcellus Tavares
 */
public class DDMExpressionException extends PortalException {

	public DDMExpressionException() {
	}

	public DDMExpressionException(String msg) {
		super(msg);
	}

	public DDMExpressionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DDMExpressionException(Throwable cause) {
		super(cause);
	}

	public static class FunctionNotAllowed extends DDMExpressionException {

		public FunctionNotAllowed(String functionName) {
			super(
				String.format(
					"The function name \"%s\" is not allowed", functionName));

			_functionName = functionName;
		}

		public String getFunctionName() {
			return _functionName;
		}

		private final String _functionName;

	}

	public static class NumberExceedsSupportedRange
		extends DDMExpressionException {

		public NumberExceedsSupportedRange() {
			super("The number entered exceeds the supported range");
		}

	}

}