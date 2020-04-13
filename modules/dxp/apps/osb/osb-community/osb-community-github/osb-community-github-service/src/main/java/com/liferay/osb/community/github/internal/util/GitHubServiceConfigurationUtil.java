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

package com.liferay.osb.community.github.internal.util;

import com.liferay.osb.community.github.internal.configuration.GitHubServiceConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Haote Chou
 */
@Component(
	configurationPid = "com.liferay.osb.community.github.internal.configuration.GitHubServiceConfiguration",
	configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true
)
public class GitHubServiceConfigurationUtil {

	public static String getAPIKey() {
		return _gitHubServiceConfiguration.apiKey();
	}

	public static int getGitHubContributorMaxCount() {
		return _gitHubServiceConfiguration.gitHubContributorMaxCount();
	}

	public static GitHubServiceConfiguration getGitHubServiceConfiguration() {
		return _gitHubServiceConfiguration;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_gitHubServiceConfiguration = ConfigurableUtil.createConfigurable(
			GitHubServiceConfiguration.class, properties);
	}

	private static volatile GitHubServiceConfiguration
		_gitHubServiceConfiguration;

}