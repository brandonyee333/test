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

package com.liferay.osb.customer.release.notes.remote.util.filter;

import com.liferay.osb.customer.release.notes.remote.model.JIRAComponent;
import com.liferay.osb.customer.release.notes.remote.model.JIRAProjectVersion;
import com.liferay.osb.customer.release.notes.remote.util.JIRAConstants;
import com.liferay.portal.kernel.util.ArrayUtil;

/**
 * @author Samuel Kong
 */
public class LPE_6_0_JIRAComponentFilter extends JIRAComponentFilter {

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

		if (jiraComponentId == JIRAConstants.COMPONENT_PORTLET_JOURNAL) {
			jiraComponent.setName("Portlet - Web Content");
		}
	}

	private static final long[] _IGNORED_COMPONENTS = {
		JIRAConstants.COMPONENT_EXTENSION_ENVIRONMENT,
		JIRAConstants.COMPONENT_PLUGIN_EXT,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_GOOGLE_SEARCH,
		JIRAConstants.COMPONENT_PLUGIN_PORTLET_WOL,
		JIRAConstants.COMPONENT_PORTLET_ADMIN,
		JIRAConstants.COMPONENT_PORTLET_ENTERPRISE_ADMIN,
		JIRAConstants.COMPONENT_PORTLET_PLUGIN_INSTALLER,
		JIRAConstants.COMPONENT_PORTLET_REVEREND_FUN,
		JIRAConstants.COMPONENT_PORTLET_SMS,
		JIRAConstants.COMPONENT_PORTLET_TAGS_ADMIN
	};

	private static final String _PROJECT_VERSION_NAME_PREFIX = "6.0 EE";

}