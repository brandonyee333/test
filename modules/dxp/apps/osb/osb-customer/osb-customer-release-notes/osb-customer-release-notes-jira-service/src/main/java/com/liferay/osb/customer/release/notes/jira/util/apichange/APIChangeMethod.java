/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.release.notes.jira.util.apichange;

import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Samuel Kong
 */
public class APIChangeMethod extends APIChangeBase {

	public APIChangeMethod(String apiChangeMethodText, JIRAIssue jiraIssue) {
		setJIRAIssue(jiraIssue);

		int x = apiChangeMethodText.indexOf("Method:");
		int y = apiChangeMethodText.indexOf(StringPool.NEW_LINE);

		if ((x == -1) || (y == -1) || (x > y)) {
			StringBundler sb = new StringBundler(4);

			sb.append("API method not formatted correctly (");
			sb.append(jiraIssue.getKey());
			sb.append(") ");
			sb.append(apiChangeMethodText);

			_log .warn(sb.toString());

			_signature = apiChangeMethodText;

			return;
		}

		x += "Method:".length();

		_signature = apiChangeMethodText.substring(x, y);

		String detailsText = apiChangeMethodText.substring(y);

		setDetails(detailsText.trim());
	}

	public String getSignature() {
		return _signature;
	}

	private static Log _log = LogFactoryUtil.getLog(APIChangeMethod.class);

	private final String _signature;

}