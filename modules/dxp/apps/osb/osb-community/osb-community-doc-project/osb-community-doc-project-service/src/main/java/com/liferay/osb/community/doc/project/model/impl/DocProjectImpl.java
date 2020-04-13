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

import com.liferay.osb.community.doc.project.internal.file.util.DocProjectFileUtil;
import com.liferay.osb.community.doc.project.model.DocProjectTypeSettings;
import com.liferay.osb.community.doc.project.util.DocProjectTypeSettingsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.InputStream;

/**
 * @author Ryan Park
 */
public class DocProjectImpl extends DocProjectBaseImpl {

	public DocProjectImpl() {
	}

	@Override
	public DocProjectTypeSettings getDocProjectTypeSettings() {
		if (_docProjectTypeSettings == null) {
			_docProjectTypeSettings = DocProjectTypeSettingsFactoryUtil.create(
				this);
		}

		return _docProjectTypeSettings;
	}

	@Override
	public InputStream getIconInputStream() throws PortalException {
		return DocProjectFileUtil.getDocumentProjectFileAsStream(
			getDocProjectId(), getIconFileName());
	}

	@Override
	public String getTypeSettings() {
		if (_docProjectTypeSettings == null) {
			return super.getTypeSettings();
		}

		return _docProjectTypeSettings.toString();
	}

	private DocProjectTypeSettings _docProjectTypeSettings;

}