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

package com.liferay.osb.customer.web.internal.util;

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
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
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

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
			request, "_" + KBPortletKeys.KNOWLEDGE_BASE_DISPLAY + "_urlTitle");

		KBArticle kbArticle = _findKBArticle(
			themeDisplay.getScopeGroupId(), resourcePrimKey, urlTitle);

		if (kbArticle == null) {
			kbArticle = _kbArticleLocalService.fetchFirstChildKBArticle(
				themeDisplay.getScopeGroupId(), resourcePrimKey);
		}

		return kbArticle;
	}

	public static Map<String, List<Layout>> getKBArticleLayouts(long groupId) {
		Map<String, List<Layout>> kbArticleLayouts = new TreeMap<>();

		List<Layout> layouts = _layoutLocalService.getLayouts(
			groupId, false, LayoutConstants.DEFAULT_PARENT_LAYOUT_ID);

		for (Layout layout : layouts) {
			if (layout.isHidden()) {
				continue;
			}

			List<AssetCategory> assetCategories =
				_assetCategoryLocalService.getCategories(
					Layout.class.getName(), layout.getPlid());

			for (AssetCategory assetCategory : assetCategories) {
				if (assetCategory.getVocabularyId() !=
						OSBCustomerConstants.
							ASSET_VOCABULARY_LIFERAY_PRODUCT_ID) {

					continue;
				}

				List<Layout> curLayouts = new ArrayList<>();

				if (kbArticleLayouts.containsKey(assetCategory.getName())) {
					curLayouts = kbArticleLayouts.get(assetCategory.getName());
				}

				curLayouts.add(layout);

				kbArticleLayouts.put(assetCategory.getName(), curLayouts);
			}
		}

		return kbArticleLayouts;
	}

	public static String getKBArticleURL(
			HttpServletRequest request, KBArticle kbArticle)
		throws Exception {

		List<Layout> layouts = _layoutLocalService.getLayouts(
			kbArticle.getGroupId(), false, LayoutConstants.TYPE_PORTLET);

		for (Layout layout : layouts) {
			PortletPreferences portletPreferences =
				PortletPreferencesFactoryUtil.getPortletSetup(
					layout, KBPortletKeys.KNOWLEDGE_BASE_DISPLAY,
					StringPool.BLANK);

			long kbFolderClassNameId = _portal.getClassNameId(
				KBFolderConstants.getClassName());

			long resourceClassNameId = GetterUtil.getLong(
				portletPreferences.getValue("resourceClassNameId", null),
				kbFolderClassNameId);

			long resourcePrimKey = GetterUtil.getLong(
				portletPreferences.getValue("resourcePrimKey", null),
				KBFolderConstants.DEFAULT_PARENT_FOLDER_ID);

			if (resourceClassNameId != kbFolderClassNameId) {
				continue;
			}

			if (!isParentFolder(resourcePrimKey, kbArticle.getKbFolderId())) {
				continue;
			}

			PortletURL portletURL = PortletURLFactoryUtil.create(
				request, KBPortletKeys.KNOWLEDGE_BASE_DISPLAY, layout.getPlid(),
				PortletRequest.RENDER_PHASE);

			if (kbArticle.getKbFolderId() !=
					KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {

				KBFolder kbFolder = _kbFolderLocalService.getKBFolder(
					kbArticle.getKbFolderId());

				portletURL.setParameter(
					"kbFolderUrlTitle", String.valueOf(kbFolder.getUrlTitle()));
			}

			portletURL.setParameter("urlTitle", kbArticle.getUrlTitle());
			portletURL.setWindowState(LiferayWindowState.NORMAL);

			return portletURL.toString();
		}

		return StringPool.BLANK;
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

	public static String getKBArticleURL(KBArticle kbArticle) throws Exception {
		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		return getKBArticleURL(serviceContext.getRequest(), kbArticle);
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

	protected static boolean isParentFolder(
			long resourcePrimKey, long kbFolderId)
		throws PortalException {

		if (resourcePrimKey == kbFolderId) {
			return true;
		}

		while (kbFolderId != KBFolderConstants.DEFAULT_PARENT_FOLDER_ID) {
			if (resourcePrimKey == kbFolderId) {
				return true;
			}

			KBFolder kbFolder = _kbFolderLocalService.getKBFolder(kbFolderId);

			kbFolderId = kbFolder.getParentKBFolderId();
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setAssetCategoryLocalService(
		AssetCategoryLocalService assetCategoryLocalService) {

		_assetCategoryLocalService = assetCategoryLocalService;
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

	@Reference(unbind = "-")
	protected void setLayoutLocalService(
		LayoutLocalService layoutLocalService) {

		_layoutLocalService = layoutLocalService;
	}

	@Reference(unbind = "-")
	protected void setPortal(Portal portal) {
		_portal = portal;
	}

	private static KBArticle _findKBArticle(
			long groupId, long kbFolderId, String urlTitle)
		throws PortalException {

		KBArticle kbArticle = _kbArticleLocalService.fetchKBArticleByUrlTitle(
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

	private static AssetCategoryLocalService _assetCategoryLocalService;
	private static AssetVocabularyLocalService _assetVocabularyLocalService;
	private static KBArticleLocalService _kbArticleLocalService;
	private static KBFolderLocalService _kbFolderLocalService;
	private static KBFolderService _kbFolderService;
	private static LayoutLocalService _layoutLocalService;
	private static Portal _portal;

}