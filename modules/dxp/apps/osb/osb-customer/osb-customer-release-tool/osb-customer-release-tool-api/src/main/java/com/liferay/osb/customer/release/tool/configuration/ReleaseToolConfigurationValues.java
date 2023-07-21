/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.release.tool.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Amos Fong
 */
public class ReleaseToolConfigurationValues {

	public static final int FIX_PACK_JIRA_MAX_RESULTS = GetterUtil.getInteger(
		ReleaseToolConfigurationUtil.getString("fix.pack.jira.max.results"));

	public static final String[] FIX_PACK_JIRA_PROJECTS =
		ReleaseToolConfigurationUtil.getArray("fix.pack.jira.projects");

	public static final String RELEASE_METADATA_URL =
		ReleaseToolConfigurationUtil.getString("release.metadata.url");

}