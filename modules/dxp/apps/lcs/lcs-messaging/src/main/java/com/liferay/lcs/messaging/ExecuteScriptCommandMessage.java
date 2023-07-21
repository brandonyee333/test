/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * @author Ivica Cardic
 */
public class ExecuteScriptCommandMessage extends CommandMessage {

	public String getScript() {
		return _script;
	}

	@Override
	public String getSignatureString() {
		String signatureString = super.getSignatureString();

		return signatureString.concat(_script);
	}

	public void setScript(String script) {
		_script = script;
	}

	private String _script;

}