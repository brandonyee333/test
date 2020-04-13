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
import com.liferay.osb.community.doc.project.model.DocProjectURLTypeSettings;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Ryan Park
 */
public class DocProjectURLTypeSettingsImpl
	implements DocProjectURLTypeSettings {

	public DocProjectURLTypeSettingsImpl() {
		_properties = new UnicodeProperties(true);
	}

	public DocProjectURLTypeSettingsImpl(DocProject docProject) {
		_properties = new UnicodeProperties(true);

		_properties.fastLoad(docProject.getTypeSettings());
	}

	@Override
	public String getURL() {
		return GetterUtil.getString(_properties.getProperty("url"));
	}

	@Override
	public void setURL(String url) {
		_properties.setProperty("url", url);
	}

	@Override
	public String toString() {
		return _properties.toString();
	}

	private final UnicodeProperties _properties;

}