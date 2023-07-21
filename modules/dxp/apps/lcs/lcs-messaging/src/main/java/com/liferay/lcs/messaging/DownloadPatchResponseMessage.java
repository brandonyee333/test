/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

/**
 * @author Ivica Cardic
 */
public class DownloadPatchResponseMessage extends ResponseMessage {

	public String getPatchFileName() {
		return _patchFileName;
	}

	public int getStatus() {
		return _status;
	}

	public void setPatchFileName(String patchFileName) {
		_patchFileName = patchFileName;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _patchFileName;
	private int _status;

}