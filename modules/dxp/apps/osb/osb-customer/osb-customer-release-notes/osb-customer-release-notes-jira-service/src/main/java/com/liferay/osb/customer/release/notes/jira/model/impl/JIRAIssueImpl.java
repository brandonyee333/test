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

package com.liferay.osb.customer.release.notes.jira.model.impl;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProject;
import com.liferay.osb.customer.release.notes.jira.service.JIRAComponentLocalServiceUtil;
import com.liferay.osb.customer.release.notes.jira.service.JIRAIssueLocalServiceUtil;
import com.liferay.osb.customer.release.notes.jira.service.JIRAProjectLocalServiceUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Samuel Kong
 */
public class JIRAIssueImpl extends JIRAIssueBaseImpl {

	public JIRAIssueImpl() {
	}

	public String getAPIChange() {
		if (!_parsedDescription) {
			parseDescription();
		}

		return _apiChange;
	}

	public List<JIRAIssue> getIsRelatedToJiraIssues() {
		return JIRAIssueLocalServiceUtil.getIsRelatedToJIRAIssues(
			getJiraIssueId());
	}

	public List<JIRAComponent> getJIRAComponents() {
		return JIRAComponentLocalServiceUtil.getJIRAIssueJIRAComponents(
			getJiraIssueId());
	}

	public String getKey() {
		if (Validator.isNotNull(_key)) {
			return _key;
		}

		try {
			JIRAProject jiraProject =
				JIRAProjectLocalServiceUtil.getJIRAProject(getJiraProjectId());

			_key = jiraProject.getKey() + StringPool.DASH + getIssueNumber();
		}
		catch (Exception e) {
		}

		return _key;
	}

	public String getUpgradeNote() {
		if (!_parsedDescription) {
			parseDescription();
		}

		return _upgradeNote;
	}

	protected void parseDescription() {
		_upgradeNote = _getUpgradeNote(getDescription());
		_apiChange = _getAPIChange(getDescription());

		_parsedDescription = true;
	}

	private String _getAPIChange(String description) {
		if (Validator.isNull(description)) {
			return null;
		}

		int x = description.indexOf(_API_CHANGE_IDENTIFIER);

		if (x < 0) {
			return null;
		}

		x = description.indexOf(StringPool.NEW_LINE, x);

		int y = description.indexOf(_UPGRADE_NOTE_IDENTIFIER, x);

		String apiChangeText = null;

		if (y < 0) {
			apiChangeText = description.substring(x);
		}
		else {
			apiChangeText = description.substring(x, y);
		}

		return _normalizeLineBreaks(apiChangeText);
	}

	private String _getUpgradeNote(String description) {
		if (Validator.isNull(description)) {
			return null;
		}

		int x = description.indexOf(_UPGRADE_NOTE_IDENTIFIER);

		if (x < 0) {
			return null;
		}

		x = description.indexOf(StringPool.NEW_LINE, x);

		int y = description.indexOf(_API_CHANGE_IDENTIFIER, x);

		String upgradeNote = null;

		if (y < 0) {
			upgradeNote = description.substring(x);
		}
		else {
			upgradeNote = description.substring(x, y);
		}

		return upgradeNote.trim();
	}

	private String _normalizeLineBreaks(String s) {
		return StringUtil.replace(
			s, new String[] {StringPool.RETURN_NEW_LINE, StringPool.RETURN},
			new String[] {StringPool.NEW_LINE, StringPool.NEW_LINE});
	}

	private static final String _API_CHANGE_IDENTIFIER = "API CHANGE";

	private static final String _UPGRADE_NOTE_IDENTIFIER = "UPGRADE NOTE";

	private String _apiChange;
	private String _key;
	private boolean _parsedDescription;
	private String _upgradeNote;

}