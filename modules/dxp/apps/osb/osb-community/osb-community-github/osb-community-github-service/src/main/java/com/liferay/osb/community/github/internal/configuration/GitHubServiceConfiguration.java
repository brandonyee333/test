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

package com.liferay.osb.community.github.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Haote Chou
 */
@ExtendedObjectClassDefinition(category = "other")
@Meta.OCD(
	id = "com.liferay.osb.community.github.internal.configuration.GitHubServiceConfiguration",
	localization = "content/Language",
	name = "osb-community-github-configuration-name"
)
public interface GitHubServiceConfiguration {

	@Meta.AD(deflt = "", description = "api-key")
	public String apiKey();

	@Meta.AD(deflt = "10", name = "github-contributor-max-count")
	public int gitHubContributorMaxCount();

}