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

package com.liferay.osb.customer.release.notes.local.model.impl;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Alan Zhang
 * @author Amos Fong
 */
public class ReleaseNotesImpl extends ReleaseNotesBaseImpl {

	public ReleaseNotesImpl() {
	}

	public String[] getJiraIssueKeysArray() {
		if (_jiraIssueKeysArray == null) {
			_jiraIssueKeysArray = StringUtil.split(getJiraIssueKeys());
		}

		return _jiraIssueKeysArray;
	}

	private String[] _jiraIssueKeysArray;

}