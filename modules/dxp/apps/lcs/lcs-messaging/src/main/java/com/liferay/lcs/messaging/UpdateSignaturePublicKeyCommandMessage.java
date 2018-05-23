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

		signatureString = signatureString.concat(_certificate);

		return signatureString;
	}

	public void setCertificate(String certificate) {
		_certificate = certificate;
	}

	private String _certificate;

}