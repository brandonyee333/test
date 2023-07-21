/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * @author Ivica Cardic
 */
public class UpdateSignaturePublicKeyCommandMessage extends CommandMessage {

	public String getCertificate() {
		return _certificate;
	}

	@Override
	public String getSignatureString() {
		String signatureString = super.getSignatureString();

		return signatureString.concat(_certificate);
	}

	public void setCertificate(String certificate) {
		_certificate = certificate;
	}

	private String _certificate;

}