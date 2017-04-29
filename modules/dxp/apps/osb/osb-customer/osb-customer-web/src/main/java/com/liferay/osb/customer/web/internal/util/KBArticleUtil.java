/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.web.internal.util;

import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.knowledge.base.constants.KBFolderConstants;
import com.liferay.knowledge.base.constants.KBPortletKeys;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.knowledge.base.model.KBFolder;
import com.liferay.knowledge.base.service.KBArticleLocalService;
import com.liferay.knowledge.base.service.KBFolderLocalService;
import com.liferay.knowledge.base.service.KBFolderService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.importer.KBArticleInfo;
import com.liferay.osb.customer.util.comparator.AssetVocabularyDescriptionComparator;
import com.liferay.osb.customer.web.internal.importer.KBArticleImporter;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.InputStream;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = KBArticleUtil.class)
public class KBArticleUtil {

	public static List<AssetVocabulary> getAssetVocabularies()
		throws PortalException {

		List<AssetVocabulary> assetVocabularies =
			_assetVocabularyLocalService.getGroupVocabularies(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID);

		assetVocabularies = ListUtil.copy(assetVocabularies);

		Collections.sort(
			assetVocabularies, new AssetVocabularyDescriptionComparator(true));

		return assetVocabularies;
	}

	public static KBArticle getKBArticle(HttpServletRequest request)
		throws PortalException {

		HttpServletRequest originalRequest =
			PortalUtil.getOriginalServletRequest(request);

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long kbArticleId = ParamUtil.getLong(request, "kbArticleId");

		if (kbArticleId > 0) {
			return _kbArticleLocalService.getKBArticle(kbArticleId);
		}

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getStrictPortletSetup(
				themeDisplay.getLayout(), KBPortletKeys.KNOWLEDGE_BASE_DISPLAY);

		long resourceClassNameId = GetterUtil.getLong(
			portletPreferences.getValue("resourceClassNameId", null));
		long resourcePrimKey = GetterUtil.getLong(
			portletPreferences.getValue("resourcePrimKey", null));

		if (resourceClassNameId == PortalUtil.getClassNameId(KBArticle.class)) {
			return _kbArticleLocalService.getKBArticle(resourcePrimKey);
		}

		String urlTitle = ParamUtil.getString(
			originalRequest, "_2_WAR_knowledgebaseportlet_urlTitle");

		KBArticle kbArticle = _findKBArticle(
			themeDisplay.getScopeGroupId(), resourcePrimKey, urlTitle);

		if (kbArticle == null) {
			kbArticle = _kbArticleLocalService.fetchFirstChildKBArticle(
				themeDisplay.getScopeGroupId(), resourcePrimKey);
		}

		return kbArticle;
	}

	public static String getKBArticleURL(
			HttpServletRequest request, long plid, KBArticle kbArticle,
			String redirect)
		throws PortalException {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			request, KBPortletKeys.KNOWLEDGE_BASE_DISPLAY, plid,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter("urlTitle", kbArticle.getUrlTitle());

		if (kbArticle.getKbFolderId() !=
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

			KBFolder kbFolder = _kbFolderService.getKBFolder(
				kbArticle.getKbFolderId());

			portletURL.setParameter("kbFolderUrlTitle", kbFolder.getUrlTitle());
		}

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		return portletURL.toString();
	}

	public static void importAssetCategories(
			long kbFolderId, String fileName, InputStream inputStream)
		throws Exception {

		KBArticleImporter importer = new KBArticleImporter(inputStream);

		List<KBArticle> kbArticles =
			_kbArticleLocalService.getKBFolderKBArticles(
				OSBCustomerConstants.GROUP_KNOWLEDGE_ID, kbFolderId);

		for (KBArticle kbArticle : kbArticles) {
			KBArticleInfo kbArticleInfo = importer.getKBArticleInfo(
				kbArticle.getUrlTitle());

			if (kbArticleInfo == null) {
				importer.addErrorMessage(
					"No categories imported for: " + kbArticle.getUrlTitle());

				continue;
			}

			_kbArticleLocalService.updateKBArticleAsset(
				kbArticle.getUserId(), kbArticle,
				kbArticleInfo.getAssetCategoryIds(),
				kbArticleInfo.getAssetTagNames(),
				kbArticleInfo.getAssetLinkEntryIds());

			if (_log.isInfoEnabled()) {
				_log.info("Successfully updated: " + kbArticle.getUrlTitle());
			}

			importer.removeKBArticleInfo(kbArticle.getUrlTitle());
		}

		if (_log.isInfoEnabled()) {
			Map<String, KBArticleInfo> kbArticleInfoMap =
				importer.getKBArticleInfo();

			for (String urlTitle : kbArticleInfoMap.keySet()) {
				_log.info("Article was not found: " + urlTitle);
			}

			for (String message : importer.getErrorMessages()) {
				_log.info(message);
			}
		}
	}

	@Reference(unbind = "-")
	protected void setAssetVocabularyLocalService(
		AssetVocabularyLocalService assetVocabularyLocalService) {

		_assetVocabularyLocalService = assetVocabularyLocalService;
	}

	@Reference(unbind = "-")
	protected void setKBArticleLocalService(
		KBArticleLocalService kbArticleLocalService) {

		_kbArticleLocalService = kbArticleLocalService;
	}

	@Reference(unbind = "-")
	protected void setKBFolderLocalService(
		KBFolderLocalService kbFolderLocalService) {

		_kbFolderLocalService = kbFolderLocalService;
	}

	@Reference(unbind = "-")
	protected void setKBFolderService(KBFolderService kbFolderService) {
		_kbFolderService = kbFolderService;
	}

	private static KBArticle _findKBArticle(
			long groupId, long kbFolderId, String urlTitle)
		throws PortalException {

		KBArticle kbArticle =

			_kbArticleLocalService.fetchKBArticleByUrlTitle(

				groupId, kbFolderId, urlTitle);

		if (kbArticle != null) {
			return kbArticle;
		}

		List<KBFolder> kbFolders = _kbFolderLocalService.getKBFolders(
			groupId, kbFolderId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (KBFolder kbFolder : kbFolders) {
			return _findKBArticle(groupId, kbFolder.getKbFolderId(), urlTitle);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(KBArticleUtil.class);

	private static AssetVocabularyLocalService _assetVocabularyLocalService;
	private static KBArticleLocalService _kbArticleLocalService;
	private static KBFolderLocalService _kbFolderLocalService;
	private static KBFolderService _kbFolderService;

}