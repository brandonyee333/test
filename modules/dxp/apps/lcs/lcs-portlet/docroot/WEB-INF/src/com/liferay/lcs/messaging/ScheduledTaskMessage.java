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
 * @author Riccardo Ferrari
 */
public class ScheduledTaskMessage extends Message {

	public String getModel() {
		return _model;
	}

	public long getPageEnd() {
		return _pageEnd;
	}

	public long getPageStart() {
		return _pageStart;
	}

	public long getQueryStartTime() {
		return _queryStartTime;
	}

	public long getResultCount() {
		return _resultCount;
	}

	public void setModel(String model) {
		_model = model;
	}

	public void setPageEnd(long pageEnd) {
		_pageEnd = pageEnd;
	}

	public void setPageStart(long pageStart) {
		_pageStart = pageStart;
	}

	public void setQueryStartTime(long queryStartTime) {
		_queryStartTime = queryStartTime;
	}

	public void setResultCount(long resultCount) {
		_resultCount = resultCount;
	}

	private String _model;
	private long _pageEnd;
	private long _pageStart;
	private long _queryStartTime;
	private long _resultCount;

}