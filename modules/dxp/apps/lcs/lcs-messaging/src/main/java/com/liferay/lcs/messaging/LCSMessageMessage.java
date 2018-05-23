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
public class LCSMessageMessage extends Message {

	public String getContent() {
		return _content;
	}

	public long getCorpProjectId() {
		return _corpProjectId;
	}

	public long getSourceMessageId() {
		return _sourceMessageId;
	}

	public int getType() {
		return _type;
	}

	public boolean isDelete() {
		return _delete;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;
	}

	public void setDelete(boolean delete) {
		_delete = delete;
	}

	public void setSourceMessageId(long sourceMessageId) {
		_sourceMessageId = sourceMessageId;
	}

	public void setType(int type) {
		_type = type;
	}

	private String _content;
	private long _corpProjectId;
	private boolean _delete;
	private long _sourceMessageId;
	private int _type;

}