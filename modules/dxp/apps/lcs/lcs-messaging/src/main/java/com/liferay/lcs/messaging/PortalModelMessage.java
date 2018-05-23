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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class PortalModelMessage extends Message {

	public List<Map<String, Object>> getModels() {
		return _models;
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

	public Type getType() {
		return _type;
	}

	public void setModels(List<Map<String, Object>> models) {
		_models = models;
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

	public void setType(Type type) {
		_type = type;
	}

	public enum Type {

		ORGANIZATION, SITE, USER_GROUP

	}

	private List<Map<String, Object>> _models =
		new ArrayList<Map<String, Object>>();
	private long _pageEnd;
	private long _pageStart;
	private long _queryStartTime;
	private long _resultCount;
	private Type _type;

}