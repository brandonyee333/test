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