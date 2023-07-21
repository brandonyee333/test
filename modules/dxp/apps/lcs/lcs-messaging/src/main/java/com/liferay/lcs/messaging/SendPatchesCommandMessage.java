/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * @author Ivica Cardic
 */
public class SendPatchesCommandMessage extends CommandMessage {

	public String getHashCode() {
		return _hashCode;
	}

	@Override
	public String getSignatureString() {
		String signatureString = super.getSignatureString();

		if (_hashCode != null) {
			signatureString = signatureString.concat(_hashCode);
		}

		return signatureString;
	}

	public void setHashCode(String hashCode) {
		_hashCode = hashCode;
	}

	private String _hashCode;

}