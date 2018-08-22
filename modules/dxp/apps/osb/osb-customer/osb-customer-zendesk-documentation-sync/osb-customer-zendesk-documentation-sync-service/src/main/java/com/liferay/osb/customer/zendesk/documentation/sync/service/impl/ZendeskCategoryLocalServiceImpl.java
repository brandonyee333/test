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

package com.liferay.osb.customer.zendesk.documentation.sync.service.impl;

import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.osb.customer.zendesk.documentation.sync.exception.RequiredZendeskCategoryException;
import com.liferay.osb.customer.zendesk.documentation.sync.internal.importer.DocumentationArchiveImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.service.base.ZendeskCategoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;

import java.io.InputStream;

/**
 * @author Amos Fong
 */
public class ZendeskCategoryLocalServiceImpl
	extends ZendeskCategoryLocalServiceBaseImpl {

	public ZendeskCategory addZendeskCategory(
			String documentationKey, String articleLabels, long remoteId)
		throws PortalException {

		long zendeskCategoryId = counterLocalService.increment();

		ZendeskCategory zendeskCategory = zendeskCategoryPersistence.create(
			zendeskCategoryId);

		zendeskCategory.setDocumentationKey(documentationKey);
		zendeskCategory.setArticleLabels(articleLabels);
		zendeskCategory.setRemoteId(remoteId);

		return zendeskCategoryPersistence.update(zendeskCategory);
	}

	public ZendeskCategory deleteZendeskCategory(long zendeskCategoryId)
		throws PortalException {

		if (zendeskSectionLocalService.getZendeskSectionsCount(
				zendeskCategoryId) > 0) {

			throw new RequiredZendeskCategoryException();
		}

		return zendeskCategoryPersistence.remove(zendeskCategoryId);
	}

	public void importDocumentationArchive(
			long zendeskCategoryId, String fileName, InputStream inputStream)
		throws Exception {

		if (Validator.isNull(fileName) || (inputStream == null)) {
			throw new DocumentationImportException();
		}

		ZendeskCategory zendeskCategory =
			zendeskCategoryPersistence.findByPrimaryKey(zendeskCategoryId);

		ZipReader zipReader = null;

		try {
			zipReader = ZipReaderFactoryUtil.getZipReader(inputStream);

			DocumentationArchiveImporter documentationArchiveImporter =
				new DocumentationArchiveImporter(zipReader, zendeskCategory);

			documentationArchiveImporter.importArchive();
		}
		finally {
			if (zipReader == null) {
				zipReader.close();
			}
		}
	}

	public ZendeskCategory updateZendeskCategory(
			long zendeskCategoryId, String articleLabels)
		throws PortalException {

		ZendeskCategory zendeskCategory =
			zendeskCategoryPersistence.findByPrimaryKey(zendeskCategoryId);

		zendeskCategory.setArticleLabels(articleLabels);

		return zendeskCategoryPersistence.update(zendeskCategory);
	}

}