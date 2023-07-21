/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.issue.engine.jira.web.internal.util;

import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Ethan Bustad
 */
public class TestrayIssueEngineJiraWebUtil {

	public static String get(String key) {
		return PropsUtil.get(key);
	}

}