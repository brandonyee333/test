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

package com.liferay.osb.community.doc.project.internal.util;

import com.liferay.osb.community.doc.project.constants.DocProjectConstants;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.DocProjectTypeSettings;
import com.liferay.osb.community.doc.project.model.impl.DocProjectSiteTypeSettingsImpl;
import com.liferay.osb.community.doc.project.model.impl.DocProjectURLTypeSettingsImpl;
import com.liferay.osb.community.doc.project.util.DocProjectTypeSettingsFactory;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ryan Park
 */
@Component(service = DocProjectTypeSettingsFactory.class)
public class DocProjectTypeSettingsFactoryImpl
	implements DocProjectTypeSettingsFactory {

	@Override
	public DocProjectTypeSettings create(DocProject docProject) {
		String type = docProject.getType();

		if (type.equals(DocProjectConstants.TYPE_SITE)) {
			return new DocProjectSiteTypeSettingsImpl(docProject);
		}
		else if (type.equals(DocProjectConstants.TYPE_URL)) {
			return new DocProjectURLTypeSettingsImpl(docProject);
		}

		return null;
	}

	@Override
	public DocProjectTypeSettings create(String type) {
		if (type.equals(DocProjectConstants.TYPE_SITE)) {
			return new DocProjectSiteTypeSettingsImpl();
		}
		else if (type.equals(DocProjectConstants.TYPE_URL)) {
			return new DocProjectURLTypeSettingsImpl();
		}

		return null;
	}

}