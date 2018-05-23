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

package com.liferay.lcs.rest.client;

import java.util.Date;

/**
 * @author Igor Beslic
 */
public class LCSClusterEntryToken {

	public String getContent() {
		return _content;
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
	private Date _createDate;
	private long _lcsClusterEntryId;
	private long _lcsClusterEntryTokenId;
	private long _userId;

}