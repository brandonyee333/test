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

package com.liferay.osb.customer.web.internal.portlet;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.exception.KBArticleImportException;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.knowledge.base.service.KBFolderLocalService;
import com.liferay.knowledge.base.util.comparator.KBArticlePriorityComparator;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.constants.OSBCustomerPortletKeys;
import com.liferay.osb.customer.importer.KBArticleInfo;
import com.liferay.osb.customer.web.internal.util.KBArticleUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.PortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alan Zhang
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=osb-documentation-admin-portlet",
		"com.liferay.portlet.display-category=category.osb",
		"javax.portlet.display-name=OSB Documentation Admin",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/admin/",
		"javax.portlet.init-param.view-template=/admin/view.jsp",
		"javax.portlet.name=" + OSBCustomerPortletKeys.ADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=administrator,guest,power-user,user"
	},
	service = Portlet.class
)
public class AdminPortlet extends MVCPortlet {

	public void importFile(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		InputStream inputStream = null;

		try {
			UploadPortletRequest uploadPortletRequest =
				_portal.getUploadPortletRequest(actionRequest);

			long kbFolderId = ParamUtil.getLong(
				uploadPortletRequest, "kbFolderId",
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

			String fileName = uploadPortletRequest.getFileName("file");

			if (Validator.isNull(fileName)) {
				throw new KBArticleImportException("File name is null.");
			}

			if (!fileName.endsWith(".csv")) {
				throw new KBArticleImportException("Please upload a csv file.");
			}

			inputStream = uploadPortletRequest.getFileAsStream("file");

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				AdminPortlet.class.getName(), actionRequest);

			serviceContext.setGuestPermissions(new String[] {ActionKeys.VIEW});

			KBArticleUtil.importAssetCategories(
				kbFolderId, fileName, inputStream);
		}
		catch (KBArticleImportException kbaie) {
			SessionErrors.add(actionRequest, kbaie.getClass(), kbaie);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException, PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("exportCSV")) {
				serveKBFolderCSV(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("exportXML")) {
				serveKBFolderXML(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected List<KBArticleInfo> getChildKBArticleInfoList(
			Map<Long, List<KBArticle>> childKBArticlesMap, KBArticle kbArticle,
			String section, String subsection)
		throws PortalException {

		List<KBArticleInfo> kbArticleInfoList = new ArrayList<>();

		KBArticleInfo kbArticleInfo = new KBArticleInfo(
			kbArticle, section, subsection);

		kbArticleInfoList.add(kbArticleInfo);

		List<KBArticle> childKBArticles = childKBArticlesMap.get(
			kbArticle.getResourcePrimKey());

		if ((childKBArticles == null) || childKBArticles.isEmpty()) {
			return kbArticleInfoList;
		}

		kbArticleInfo.setLeafNode(false);

		Collections.sort(
			childKBArticles, new KBArticlePriorityComparator(true));

		for (KBArticle childKBArticle : childKBArticles) {
			List<KBArticleInfo> childKBArticleInfoList =
				getChildKBArticleInfoList(
					childKBArticlesMap, childKBArticle, section,
					childKBArticle.getTitle());

			kbArticleInfoList.addAll(childKBArticleInfoList);
		}

		return kbArticleInfoList;
	}

	protected List<KBArticleInfo> getKBArticleInfoList(long kbFolderId)
		throws PortalException {

		List<KBArticleInfo> kbArticleInfoList = new ArrayList<>();

		List<KBArticle> kbArticles =
			_kbArticleLocalService.getKBFolderKBArticles(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID, kbFolderId);

		Map<Long, List<KBArticle>> childKBArticlesMap = new HashMap<>();

		for (KBArticle kbArticle : kbArticles) {
			List<KBArticle> childKBArticles = childKBArticlesMap.get(
				kbArticle.getParentResourcePrimKey());

			if (childKBArticles == null) {
				childKBArticles = new ArrayList<>();

				childKBArticlesMap.put(
					kbArticle.getParentResourcePrimKey(), childKBArticles);
			}

			childKBArticles.add(kbArticle);
		}

		List<KBArticle> childKBArticles = childKBArticlesMap.get(kbFolderId);

		Collections.sort(
			childKBArticles, new KBArticlePriorityComparator(true));

		for (KBArticle kbArticle : childKBArticles) {
			List<KBArticleInfo> childKBArticleInfoList =
				getChildKBArticleInfoList(
					childKBArticlesMap, kbArticle, kbArticle.getTitle(),
					StringPool.BLANK);

			kbArticleInfoList.addAll(childKBArticleInfoList);
		}

		return kbArticleInfoList;
	}

	protected void serveKBFolderCSV(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long kbFolderId = ParamUtil.getLong(resourceRequest, "kbFolderId");

		KBFolder kbFolder = _kbFolderLocalService.getKBFolder(kbFolderId);

		List<KBArticleInfo> kbArticleInfoList = getKBArticleInfoList(
			kbFolderId);

		List<AssetVocabulary> assetVocabularies =
			KBArticleUtil.getAssetVocabularies();

		StringBundler sb = new StringBundler(
			(kbArticleInfoList.size() * 2) + 2);

		sb.append(KBArticleInfo.exportToCVSHeader(assetVocabularies));
		sb.append(StringPool.NEW_LINE);

		for (KBArticleInfo kbArticleInfo : kbArticleInfoList) {
			sb.append(kbArticleInfo.exportToCVS(assetVocabularies));
			sb.append(StringPool.NEW_LINE);
		}

		String content = sb.toString();

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, kbFolder.getName() + ".csv",
			content.getBytes(), ContentTypes.TEXT_CSV_UTF8);
	}

	protected void serveKBFolderXML(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		long kbFolderId = ParamUtil.getLong(resourceRequest, "kbFolderId");

		KBFolder kbFolder = _kbFolderLocalService.getKBFolder(kbFolderId);

		List<KBArticleInfo> kbArticleInfoList = getKBArticleInfoList(
			kbFolderId);

		List<AssetVocabulary> assetVocabularies =
			KBArticleUtil.getAssetVocabularies();

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("articles");

		for (KBArticleInfo kbArticleInfo : kbArticleInfoList) {
			kbArticleInfo.exportToXML(rootElement, assetVocabularies);
		}

		String content = document.formattedString();

		PortletResponseUtil.sendFile(
			resourceRequest, resourceResponse, kbFolder.getName() + ".xml",
			content.getBytes(), ContentTypes.TEXT_XML_UTF8);
	}

	@Reference
	private KBArticleLocalService _kbArticleLocalService;

	@Reference
	private KBFolderLocalService _kbFolderLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private RoleLocalService _roleLocalService;

}