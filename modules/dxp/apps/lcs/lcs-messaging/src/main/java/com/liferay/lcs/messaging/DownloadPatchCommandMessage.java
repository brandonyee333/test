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
public class DownloadPatchCommandMessage extends CommandMessage {

	public String getMd5Sum() {
		return _md5Sum;
	}

	public String getPatchFileName() {
		return _patchFileName;
	}

	public String getPatchURL() {
		return _patchURL;
	}

	@Override
	public String getSignatureString() {
		StringBuilder sb = new StringBuilder(4);

		sb.append(super.getSignatureString());
		sb.append(_md5Sum);
		sb.append(_patchFileName);
		sb.append(_patchURL);

		return sb.toString();
	}

	public void setMd5Sum(String md5Sum) {
		_md5Sum = md5Sum;
	}

	public void setPatchFileName(String patchFileName) {
		_patchFileName = patchFileName;
	}

	public void setPatchURL(String patchURL) {
		_patchURL = patchURL;
	}

	private String _md5Sum;
	private String _patchFileName;
	private String _patchURL;

}