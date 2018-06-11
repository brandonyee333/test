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

package com.liferay.osb.customer.release.notes.web.internal.util.apichange;

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
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(4);

				sb.append("API method not formatted correctly (");
				sb.append(jiraIssue.getKey());
				sb.append(") ");
				sb.append(apiChangeMethodText);

				_log.warn(sb.toString());
			}

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