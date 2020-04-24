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

package com.liferay.osb.testray.issue.engine.jira.web.internal.util;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;

/**
 * @author Ethan Bustad
 */
public class TestrayIssueEngineJiraWebUtil {

	public static String get(String key) {
		return _configuration.get(key);
	}

	private static final Configuration _configuration =
		ConfigurationFactoryUtil.getConfiguration(
			TestrayIssueEngineJiraWebUtil.class.getClassLoader(), "portlet");

}