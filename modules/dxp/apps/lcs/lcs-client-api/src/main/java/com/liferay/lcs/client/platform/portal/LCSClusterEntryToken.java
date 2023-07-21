/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.platform.portal;

import java.util.Date;

/**
 * @author Igor Beslic
 */
public class LCSClusterEntryToken {

	public String getContent() {
		return _content;
	}

	public int getContentStructureVersion() {
		return _contentStructureVersion;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public long getLcsClusterEntryId() {
		return _lcsClusterEntryId;
	}

	public long getLcsClusterEntryTokenId() {
		return _lcsClusterEntryTokenId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setContentStructureVersion(int contentStructureVersion) {
		_contentStructureVersion = contentStructureVersion;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setLcsClusterEntryId(long lcsClusterEntryId) {
		_lcsClusterEntryId = lcsClusterEntryId;
	}

	public void setLcsClusterEntryTokenId(long lcsClusterEntryTokenId) {
		_lcsClusterEntryTokenId = lcsClusterEntryTokenId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private String _content;
	private int _contentStructureVersion;
	private Date _createDate;
	private long _lcsClusterEntryId;
	private long _lcsClusterEntryTokenId;
	private long _userId;

}