/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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