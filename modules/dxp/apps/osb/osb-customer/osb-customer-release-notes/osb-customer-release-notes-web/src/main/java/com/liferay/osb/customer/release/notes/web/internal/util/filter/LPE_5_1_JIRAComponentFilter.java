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

package com.liferay.osb.customer.release.notes.web.internal.util.filter;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.web.internal.util.JIRAConstants;
import com.liferay.portal.kernel.util.ArrayUtil;

/**
 * @author Samuel Kong
 */
public class LPE_5_1_JIRAComponentFilter extends JIRAComponentFilter {

	protected boolean isEnabled(JIRAProjectVersion jiraProjectVersion) {
		String jiraProjectVersionName = jiraProjectVersion.getName();

		return jiraProjectVersionName.startsWith(_PROJECT_VERSION_NAME_PREFIX);
	}

	protected boolean isIgnore(JIRAComponent jiraComponent) {
		return ArrayUtil.contains(
			_IGNORED_COMPONENTS, jiraComponent.getJiraComponentId());
	}

	protected void rename(JIRAComponent jiraComponent) {
		long jiraComponentId = jiraComponent.getJiraComponentId();

		if (jiraComponentId == JIRAConstants.COMPONENT_JAVASCRIPT) {
			jiraComponent.setName("JavaScript");
		}
		else if (jiraComponentId ==
					JIRAConstants.COMPONENT_PORTLET_COMMUNITIES) {

			jiraComponent.setName("Portlet - Communities");
		}
		else if (jiraComponentId == JIRAConstants.COMPONENT_PORTLET_JOURNAL) {
			jiraComponent.setName("Portlet - Journal");
		}
	}

	private static final long[] _IGNORED_COMPONENTS = {
		JIRAConstants.COMPONENT_CONTROL_PANEL,
		JIRAConstants.COMPONENT_PLUGIN_EXT,
		JIRAConstants.COMPONENT_PLUGIN_HOOK_DEFAULT_WEB_CONTENT,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_KNOWLEDGE_BASE,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_OPEN_SOCIAL,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_REPORTS,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_SOCIAL_CODING,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_SOCIAL_NETWORKING,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_WEB_FORM,
		JIRAConstants.COMPONENT_PORTLET_CATEGORIES_NAVIGATION,
		JIRAConstants.COMPONENT_PORTLET_PAGE_FLAGS,
		JIRAConstants.COMPONENT_PORTLET_TAGS_NAVIGATION,
		JIRAConstants.COMPONENT_WORKFLOW
	};

	private static final String _PROJECT_VERSION_NAME_PREFIX = "5.1 EE";

}