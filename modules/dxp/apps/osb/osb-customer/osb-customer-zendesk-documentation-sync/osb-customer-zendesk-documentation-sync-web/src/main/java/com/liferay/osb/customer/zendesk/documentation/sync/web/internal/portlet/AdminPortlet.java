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

package com.liferay.osb.customer.zendesk.documentation.sync.web.internal.portlet;

import com.liferay.osb.customer.zendesk.documentation.sync.exception.DocumentationImportException;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporterFactory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskCategoryLocalService;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskSectionLocalService;
import com.liferay.osb.customer.zendesk.documentation.sync.web.internal.constants.ZendeskDocumentationSyncPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactoryUtil;

import java.io.InputStream;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-documentation-sync-admin-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"javax.portlet.display-name=OSB Documentation Sync Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/admin/",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name=" + ZendeskDocumentationSyncPortletKeys.ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class AdminPortlet extends MVCPortlet {

	public void deleteZendeskCategory(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long zendeskCategoryId = ParamUtil.getLong(
			actionRequest, "zendeskCategoryId");

		_zendeskCategoryLocalService.deleteZendeskCategory(zendeskCategoryId);
	}

	public void importDocumentationArchive(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		InputStream inputStream = null;
		ZipReader zipReader = null;

		try {
			UploadPortletRequest uploadPortletRequest =
				_portal.getUploadPortletRequest(actionRequest);

			long zendeskCategoryId = ParamUtil.getLong(
				uploadPortletRequest, "zendeskCategoryId");

			String fileName = uploadPortletRequest.getFileName("file");

			inputStream = uploadPortletRequest.getFileAsStream("file");

			ZendeskCategory zendeskCategory =
				_zendeskCategoryLocalService.getZendeskCategory(
					zendeskCategoryId);

			if (Validator.isNull(fileName) || (inputStream == null) ||
				!fileName.equals(zendeskCategory.getDocumentationKey())) {

				throw new DocumentationImportException();
			}

			zipReader = ZipReaderFactoryUtil.getZipReader(inputStream);

			if (_log.isInfoEnabled()) {
				_log.info("Importing articles for " + fileName);
			}

			DocumentationImporter documentationImporter =
				_documentationImporterFactory.createDocumentationImporter(
					zipReader, zendeskCategory);

			documentationImporter.importArticles();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw e;
		}
		finally {
			if (zipReader == null) {
				zipReader.close();
			}

			StreamUtil.cleanUp(inputStream);
		}
	}

	public void updateZendeskCategory(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long zendeskCategoryId = ParamUtil.getLong(
			actionRequest, "zendeskCategoryId");

		String documentationKey = ParamUtil.getString(
			actionRequest, "documentationKey");
		String documentationOriginalURL = ParamUtil.getString(
			actionRequest, "documentationOriginalURL");
		String articleLabels = ParamUtil.getString(
			actionRequest, "articleLabels");
		long remoteId = ParamUtil.getLong(actionRequest, "remoteId");

		if (zendeskCategoryId > 0) {
			_zendeskCategoryLocalService.updateZendeskCategory(
				zendeskCategoryId, documentationOriginalURL, articleLabels);
		}
		else {
			_zendeskCategoryLocalService.addZendeskCategory(
				documentationKey, documentationOriginalURL, articleLabels,
				remoteId);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(AdminPortlet.class);

	@Reference
	private DocumentationImporterFactory _documentationImporterFactory;

	@Reference
	private Portal _portal;

	@Reference
	private ZendeskCategoryLocalService _zendeskCategoryLocalService;

	@Reference
	private ZendeskSectionLocalService _zendeskSectionLocalService;

}