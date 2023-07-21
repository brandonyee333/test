/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.issue.engine.jira.web.internal.portlet;

import com.liferay.osb.testray.issue.engine.jira.web.internal.constants.TestrayIssueEngineJiraPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Testray JIRA Issue Engine",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.info.keywords=Testray JIRA Issue Engine",
		"javax.portlet.info.short-title=Testray JIRA Issue Engine",
		"javax.portlet.info.title=Testray JIRA Issue Engine",
		"javax.portlet.name=" + TestrayIssueEngineJiraPortletKeys.TESTRAY_ISSUE_ENGINE_JIRA
	},
	service = Portlet.class
)
public class TestrayIssueEngineJiraPortlet extends MVCPortlet {
}