/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Jenny Chen
 */
public class GitHubConfigurationValues {

	public static final String[] EXCLUDE_GITHUB_TEAM_SLUGS =
		GetterUtil.getStringValues(
			GitHubConfigurationUtil.getArray("exclude.github.team.slugs"));

	public static final String[] EXCLUDE_GITHUB_USERNAMES =
		GetterUtil.getStringValues(
			GitHubConfigurationUtil.getArray("exclude.github.usernames"));

	public static final boolean GITHUB_FEATURE_ENABLED = GetterUtil.getBoolean(
		GitHubConfigurationUtil.get("github.feature.enabled"));

	public static final String REMOTE_REST_SERVICE_API_GITHUB_HOST =
		GetterUtil.getString(
			GitHubConfigurationUtil.get("remote.rest.service.api.github.host"));

	public static final int REMOTE_REST_SERVICE_API_GITHUB_PORT =
		GetterUtil.getInteger(
			GitHubConfigurationUtil.get("remote.rest.service.api.github.port"));

	public static final String REMOTE_REST_SERVICE_API_GITHUB_PROTOCOL =
		GetterUtil.getString(
			GitHubConfigurationUtil.get(
				"remote.rest.service.api.github.protocol"));

	public static final String REMOTE_REST_SERVICE_API_GITHUB_REPOSITORY_NAME =
		GetterUtil.getString(
			GitHubConfigurationUtil.get(
				"remote.rest.service.api.github.repository.name"));

	public static final String REMOTE_REST_SERVICE_API_GITHUB_REPOSITORY_OWNER =
		GetterUtil.getString(
			GitHubConfigurationUtil.get(
				"remote.rest.service.api.github.repository.owner"));

	public static final String REMOTE_REST_SERVICE_API_GITHUB_TOKEN =
		GetterUtil.getString(
			GitHubConfigurationUtil.get(
				"remote.rest.service.api.github.token"));

}