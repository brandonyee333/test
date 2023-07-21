/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * @author Ivica Cardic
 */
public class ExecuteScriptResponseMessage extends ResponseMessage {

	public String getResult() {
		return _result;
	}

	public void setResult(String result) {
		_result = result;
	}

	private String _result;

}