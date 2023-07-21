/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.sync.internal.deploy.auto;

import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporterFactory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskCategoryLocalService;
import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import java.util.Locale;

/**
 * @author Amos Fong
 */
public class ZendeskDocumentationSyncAutoDeployer implements AutoDeployer {

	public ZendeskDocumentationSyncAutoDeployer(
		DocumentationImporterFactory documentationImporterFactory,
		ZendeskCategoryLocalService zendeskCategoryLocalService) {

		_documentationImporterFactory = documentationImporterFactory;
		_zendeskCategoryLocalService = zendeskCategoryLocalService;
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
			_documentationImporterFactory, _zendeskCategoryLocalService);
	}

	protected int doAutoDeploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception {

		File file = autoDeploymentContext.getFile();

		String documentationKey = file.getName();

		Locale locale = LocaleUtil.US;

		if (documentationKey.endsWith("-en.zip")) {
			documentationKey = documentationKey.replace("-en.zip", ".zip");
		}
		else if (documentationKey.endsWith("-ja.zip")) {
			documentationKey = documentationKey.replace("-ja.zip", ".zip");

			locale = LocaleUtil.JAPAN;
		}

		ZendeskCategory zendeskCategory =
			_zendeskCategoryLocalService.fetchZendeskCategory(documentationKey);

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
					zipReader, zendeskCategory, locale);

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
	private final ZendeskCategoryLocalService _zendeskCategoryLocalService;

}