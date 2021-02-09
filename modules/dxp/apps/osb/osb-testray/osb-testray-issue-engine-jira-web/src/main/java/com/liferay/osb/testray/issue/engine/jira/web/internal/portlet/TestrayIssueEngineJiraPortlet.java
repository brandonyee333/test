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