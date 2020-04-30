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

package com.liferay.osb.customer.github.configuration;

import com.liferay.portal.kernel.util.GetterUtil;

/**
 * @author Jenny Chen
 */
public class GitHubConfigurationValues {

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