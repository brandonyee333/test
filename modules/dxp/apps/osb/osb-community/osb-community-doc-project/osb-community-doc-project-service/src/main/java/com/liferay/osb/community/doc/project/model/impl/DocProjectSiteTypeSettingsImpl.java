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

package com.liferay.osb.community.doc.project.model.impl;

import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectSiteTypeSettings;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Ryan Park
 */
public class DocProjectSiteTypeSettingsImpl
	implements DocProjectSiteTypeSettings {

	public DocProjectSiteTypeSettingsImpl() {
		_properties = new UnicodeProperties(true);
	}

	public DocProjectSiteTypeSettingsImpl(DocProject docProject) {
		_properties = new UnicodeProperties(true);

		_properties.fastLoad(docProject.getTypeSettings());
	}

	@Override
	public String getGitHubRepositoryName() {
		return GetterUtil.getString(
			_properties.getProperty("gitHubRepositoryName"));
	}

	@Override
	public String getGitHubRepositoryOwner() {
		return GetterUtil.getString(
			_properties.getProperty("gitHubRepositoryOwner"));
	}

	@Override
	public String getHeaderGradientEndColor() {
		return GetterUtil.getString(
			_properties.getProperty("headerGradientEndColor"));
	}

	@Override
	public String getHeaderGradientStartColor() {
		return GetterUtil.getString(
			_properties.getProperty("headerGradientStartColor"));
	}

	@Override
	public void setGitHubRepositoryName(String gitHubRepositoryName) {
		_properties.setProperty("gitHubRepositoryName", gitHubRepositoryName);
	}

	@Override
	public void setGitHubRepositoryOwner(String gitHubRepositoryOwner) {
		_properties.setProperty("gitHubRepositoryOwner", gitHubRepositoryOwner);
	}

	@Override
	public void setHeaderGradientEndColor(String headerGradientEndColor) {
		_properties.setProperty(
			"headerGradientEndColor", headerGradientEndColor);
	}

	@Override
	public void setHeaderGradientStartColor(String headerGradientStartColor) {
		_properties.setProperty(
			"headerGradientStartColor", headerGradientStartColor);
	}

	@Override
	public String toString() {
		return _properties.toString();
	}

	private final UnicodeProperties _properties;

}