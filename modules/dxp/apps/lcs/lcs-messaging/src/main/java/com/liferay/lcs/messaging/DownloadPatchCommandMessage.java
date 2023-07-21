/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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
		StringBuilder sb = new StringBuilder();

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