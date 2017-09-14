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

package com.liferay.osb.customer.release.notes.util.filter;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.osb.customer.release.notes.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.util.JIRAConstants;

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