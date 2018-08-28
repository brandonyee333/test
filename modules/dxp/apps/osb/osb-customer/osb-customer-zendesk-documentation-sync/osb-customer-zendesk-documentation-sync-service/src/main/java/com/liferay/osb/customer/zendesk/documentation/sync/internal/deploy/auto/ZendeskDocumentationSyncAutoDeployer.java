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

package com.liferay.osb.customer.zendesk.documentation.sync.internal.deploy.auto;

import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporterFactory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskCategoryLocalServiceUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author Amos Fong
 */
public class ZendeskDocumentationSyncAutoDeployer implements AutoDeployer {

	public ZendeskDocumentationSyncAutoDeployer(
		DocumentationImporterFactory documentationImporterFactory) {

		_documentationImporterFactory = documentationImporterFactory;
	}

	@Override
	public int autoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException {

		try {
			return doAutoDeploy(autoDeploymentContext);
		}
		catch (Exception e) {
			throw new AutoDeployException(e);
		}
	}

	@Override
	public AutoDeployer cloneAutoDeployer() {
		return new ZendeskDocumentationSyncAutoDeployer(
			_documentationImporterFactory);
	}

	protected int doAutoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		File file = autoDeploymentContext.getFile();

		ZendeskCategory zendeskCategory =
			ZendeskCategoryLocalServiceUtil.fetchZendeskCategory(
				file.getName());

		if (zendeskCategory == null) {
			_log.error(
				"Unable to find matching Zendesk category for " +
					file.getName());

			return AutoDeployer.CODE_NOT_APPLICABLE;
		}

		InputStream inputStream = null;
		ZipReader zipReader = null;

		try {
			inputStream = new FileInputStream(file);

			zipReader = ZipReaderFactoryUtil.getZipReader(inputStream);

			DocumentationImporter documentationImporter =
				_documentationImporterFactory.createDocumentationImporter(
					zipReader, zendeskCategory);

			documentationImporter.importArticles();
		}
		finally {
			if (zipReader == null) {
				zipReader.close();
			}

			StreamUtil.cleanUp(inputStream);
		}

		return AutoDeployer.CODE_DEFAULT;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskDocumentationSyncAutoDeployer.class);

	private final DocumentationImporterFactory _documentationImporterFactory;

}