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

package com.liferay.osb.community.doc.project.util;

import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectTypeSettings;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ryan Park
 */
@Component(immediate = true)
public class DocProjectTypeSettingsFactoryUtil {

	public static DocProjectTypeSettings create(DocProject docProject) {
		return _docProjectTypeSettingsFactory.create(docProject);
	}

	public static DocProjectTypeSettings create(String type) {
		return _docProjectTypeSettingsFactory.create(type);
	}

	@Reference(unbind = "-")
	protected void setDocProjectTypeSettingsFactory(
		DocProjectTypeSettingsFactory docProjectTypeSettingsFactory) {

		_docProjectTypeSettingsFactory = docProjectTypeSettingsFactory;
	}

	private static DocProjectTypeSettingsFactory _docProjectTypeSettingsFactory;

}