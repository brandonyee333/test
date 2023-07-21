/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.internal.deploy.auto;

import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporterFactory;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskCategoryLocalService;
import com.liferay.portal.deploy.auto.ThreadSafeAutoDeployer;
import com.liferay.portal.kernel.deploy.auto.AutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.BaseAutoDeployListener;

import java.io.File;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "service.ranking:Integer=100",
	service = AutoDeployListener.class
)
public class ZendeskDocumentationSyncAutoDeployListener
	extends BaseAutoDeployListener {

	@Override
	protected AutoDeployer buildAutoDeployer() {
		return new ThreadSafeAutoDeployer(
			new ZendeskDocumentationSyncAutoDeployer(
				_documentationImporterFactory, _zendeskCategoryLocalService));
	}

	@Override
	protected String getPluginPathInfoMessage(File file) {
		return "Importing articles for " + file.getPath();
	}

	@Override
	protected String getSuccessMessage(File file) {
		return "Successfully imported articles from " + file.getPath();
	}

	@Override
	protected boolean isDeployable(File file) {
		String fileName = file.getName();

		if (fileName.endsWith(".zip")) {
			return true;
		}

		return false;
	}

	@Reference
	private DocumentationImporterFactory _documentationImporterFactory;

	@Reference
	private ZendeskCategoryLocalService _zendeskCategoryLocalService;

}