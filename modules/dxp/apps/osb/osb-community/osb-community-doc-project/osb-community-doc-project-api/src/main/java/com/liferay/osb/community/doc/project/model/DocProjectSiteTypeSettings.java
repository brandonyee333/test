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

package com.liferay.osb.community.doc.project.model;

/**
 * @author Ryan Park
 */
public interface DocProjectSiteTypeSettings extends DocProjectTypeSettings {

	public String getGitHubRepositoryName();

	public String getGitHubRepositoryOwner();

	public String getHeaderGradientEndColor();

	public String getHeaderGradientStartColor();

	public void setGitHubRepositoryName(String gitHubRepositoryName);

	public void setGitHubRepositoryOwner(String gitHubRepositoryOwner);

	public void setHeaderGradientEndColor(String headerGradientEndColor);

	public void setHeaderGradientStartColor(String headerGradientStartColor);

}