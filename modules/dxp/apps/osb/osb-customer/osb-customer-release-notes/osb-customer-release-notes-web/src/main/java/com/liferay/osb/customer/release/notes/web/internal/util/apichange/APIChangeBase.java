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
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Kong
 */
public class APIChangeBase {

	public void addDetail(KeyValuePair detail) {
		if (_details == null) {
			_details = new ArrayList<>();
		}

		_details.add(detail);
	}

	public List<KeyValuePair> getDetails() {
		return _details;
	}

	public int getDetailsCount() {
		if (_details == null) {
			return 0;
		}

		return _details.size();
	}

	public JIRAIssue getJIRAIssue() {
		return _jiraIssue;
	}

	protected void addDetail(String detailText) {
		String[] tokens = detailText.split(StringPool.COLON);

		if (tokens.length != 2) {
			if (_log.isWarnEnabled()) {
				StringBundler sb = new StringBundler(4);

				sb.append("API change detail not formatted correctly (");
				sb.append(_jiraIssue.getKey());
				sb.append(") ");
				sb.append(detailText);

				_log.warn(sb.toString());
			}

			return;
		}

		addDetail(new KeyValuePair(tokens[0], tokens[1]));
	}

	protected void setDetails(String detailsText) {
		if (Validator.isNull(detailsText)) {
			return;
		}

		String[] tokens = StringUtil.split(detailsText, StringPool.NEW_LINE);

		for (String token : tokens) {
			addDetail(token.trim());
		}
	}

	protected void setJIRAIssue(JIRAIssue jiraIssue) {
		_jiraIssue = jiraIssue;
	}

	private static final Log _log = LogFactoryUtil.getLog(APIChangeBase.class);

	private List<KeyValuePair> _details;
	private JIRAIssue _jiraIssue;

}