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

package com.liferay.osb.customer.release.notes.remote.util.apichange;

import com.liferay.osb.customer.release.notes.remote.model.JIRAIssue;
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