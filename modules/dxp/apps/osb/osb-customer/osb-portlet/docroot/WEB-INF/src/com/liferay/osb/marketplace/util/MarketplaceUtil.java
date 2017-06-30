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

package com.liferay.osb.marketplace.util;

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetList;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetListLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.asset.model.AssetCategory;
import com.liferay.portlet.asset.model.AssetCategoryConstants;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetCategoryLocalServiceUtil;
import com.liferay.portlet.asset.service.AssetCategoryServiceUtil;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.asset.service.persistence.AssetEntryQuery;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Douglas Wong
 * @author Joan Kim
 */
public class MarketplaceUtil {

	public static final String ASSET_COMPANY_KEYWORD = "company:";

	public static final String ASSET_DEVELOPER_KEYWORD = "developer:";

	public static final int ASSET_ENTRY_REVIEW_MAX_LENGTH = 25000;

	public static final String ASSET_TAGS_KEYWORD = "tags:";

	public static final long[] MARKETPLACE_ASSET_ENTRY_CLASS_NAME_IDS = {
		PortalUtil.getClassNameId(AppEntry.class.getName()),
		PortalUtil.getClassNameId(JournalArticle.class.getName())
	};

	public static void addAssetCategoryBreadcrumbEntries(
			long assetCategoryId, HttpServletRequest request,
			RenderResponse renderResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		addHomeBreadCrumbEntry(request, themeDisplay);

		if (assetCategoryId > 0) {
			AssetCategory assetCategory =
				AssetCategoryLocalServiceUtil.getCategory(assetCategoryId);

			List<AssetCategory> assetCategories =
				new ArrayList<AssetCategory>();

			assetCategories.add(assetCategory);
			assetCategories.addAll(assetCategory.getAncestors());

			Collections.reverse(assetCategories);

			_addAssetCategoryBreadcrumbEntries(
				assetCategories, request, renderResponse);
		}
	}

	public static void addHomeBreadCrumbEntry(
			HttpServletRequest request, ThemeDisplay themeDisplay)
		throws PortalException, SystemException {

		String homeURL = PortalUtil.getLayoutURL(themeDisplay);

		String portletId = PortalUtil.getPortletId(request);

		if (portletId.equals(OSBPortletKeys.OSB_MARKETPLACE_SERVER)) {
			PortletURL portletURL = PortletURLFactoryUtil.create(
				request, portletId, themeDisplay.getPlid(),
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter("remoteMVCPath", "/marketplace/view.jsp");

			homeURL = portletURL.toString();
		}

		PortalUtil.addPortletBreadcrumbEntry(
			request, themeDisplay.translate("home"), homeURL);
	}

	public static void addPortletBreadcrumbEntries(
			String className, long classPK, HttpServletRequest request,
			RenderResponse renderResponse)
		throws PortalException, SystemException {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		addHomeBreadCrumbEntry(request, themeDisplay);

		if (!className.equals(AppEntry.class.getName())) {
			return;
		}

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(classPK);

		_addAssetCategoryBreadcrumbEntries(
			appEntry.getAssetCategories(), request, renderResponse);

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			getMVCPathParam(request), "/marketplace/view_app_entry.jsp");
		portletURL.setParameter("appEntryId", String.valueOf(classPK));

		PortalUtil.addPortletBreadcrumbEntry(
			request, appEntry.getTitle(), portletURL.toString());
	}

	public static List<MBMessage> getAppEntryReviews(
			long mbThreadId, int status, int start, int end)
		throws SystemException {

		DynamicQuery dynamicQuery = _getAppEntryReviewsDynamicQuery(
			mbThreadId, status);

		dynamicQuery.addOrder(OrderFactoryUtil.desc("modifiedDate"));

		return (List<MBMessage>)MBMessageLocalServiceUtil.dynamicQuery(
			dynamicQuery, start, end);
	}

	public static int getAppEntryReviewsCount(long mbThreadId, int status)
		throws SystemException {

		DynamicQuery dynamicQuery = _getAppEntryReviewsDynamicQuery(
			mbThreadId, status);

		return (int)MBMessageLocalServiceUtil.dynamicQueryCount(dynamicQuery);
	}

	public static List<AssetCategory> getAssetCategories()
		throws PortalException, SystemException {

		return AssetCategoryServiceUtil.getVocabularyCategories(
			OSBConstants.ASSET_VOCABULARY_MARKETPLACE_ID, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	public static List<AssetEntry> getAssetEntries(
			long assetCategoryId, String className, Boolean visible, int start,
			int end, String orderByCol)
		throws SystemException {

		AssetEntryQuery assetEntryQuery = new AssetEntryQuery();

		_setAssetEntryQueryCategoryId(assetEntryQuery, assetCategoryId);

		if (Validator.isNotNull(className)) {
			assetEntryQuery.setClassName(className);
		}
		else {
			assetEntryQuery.setClassNameIds(
				MARKETPLACE_ASSET_ENTRY_CLASS_NAME_IDS);
		}

		assetEntryQuery.setEnd(end);
		assetEntryQuery.setOrderByCol1(orderByCol);
		assetEntryQuery.setOrderByType1("DESC");
		assetEntryQuery.setStart(start);

		if (Validator.isNotNull(visible) && visible) {
			assetEntryQuery.setVisible(visible);
		}

		return AssetEntryLocalServiceUtil.getEntries(assetEntryQuery);
	}

	public static String getAssetEntryAuthorName(AssetEntry assetEntry)
		throws PortalException, SystemException {

		String className = assetEntry.getClassName();

		if (className.equals(AppEntry.class.getName())) {
			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
				assetEntry.getClassPK());

			return appEntry.getDeveloperEntryName();
		}

		return StringPool.BLANK;
	}

	public static List<AssetEntry> getAssetListAssetEntries(
			long assetCategoryId, int assetListType, boolean visible)
		throws PortalException, SystemException {

		return getAssetListAssetEntries(
			assetCategoryId, assetListType, visible, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	}

	public static List<AssetEntry> getAssetListAssetEntries(
			long assetCategoryId, int assetListType, boolean visible, int start,
			int end)
		throws PortalException, SystemException {

		AssetList assetList = AssetListLocalServiceUtil.fetchAssetList(
			assetCategoryId, assetListType);

		if (assetList != null) {
			if ((start == QueryUtil.ALL_POS) || (end == QueryUtil.ALL_POS)) {
				return assetList.getAssetEntries(visible);
			}
			else {
				return ListUtil.subList(
					assetList.getAssetEntries(visible), start, end);
			}
		}
		else {
			return Collections.emptyList();
		}
	}

	public static String getDescriptionProperty(String description) {
		description = HtmlUtil.stripHtml(description);

		return description.replace(StringPool.NEW_LINE, "\\\n ");
	}

	public static String getExternalPurchaseURL(
			String hostName, long appEntryId)
		throws Exception {

		StringBundler sb = new StringBundler(6);

		sb.append("/web");

		Group group = GroupLocalServiceUtil.getGroup(
			OSBConstants.GROUP_GUEST_ID);

		sb.append(group.getFriendlyURL());

		long marketplacePlid = PortalUtil.getPlidFromPortletId(
			OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

		Layout marketplaceLayout = LayoutLocalServiceUtil.getLayout(
			marketplacePlid);

		sb.append(marketplaceLayout.getFriendlyURL());
		sb.append("/-/mp/application/");
		sb.append(appEntryId);
		sb.append("/purchase/");

		String portalURL = PortalUtil.getPortalURL(
			hostName, PortalUtil.getPortalPort(true), true);

		return HttpUtil.addParameter(
			portalURL + "/c/portal/login", "redirect", sb.toString());
	}

	public static List<AssetCategory> getLeafAssetCategories()
		throws PortalException, SystemException {

		List<AssetCategory> leafAssetCategories =
			new ArrayList<AssetCategory>();

		List<AssetCategory> assetCategories = getAssetCategories();

		for (AssetCategory assetCategory : assetCategories) {
			int count = AssetCategoryLocalServiceUtil.getChildCategoriesCount(
				assetCategory.getCategoryId());

			if (count == 0) {
				leafAssetCategories.add(assetCategory);
			}
		}

		return leafAssetCategories;
	}

	public static String getMVCPathParam(HttpServletRequest request) {
		if (isMarketplaceServer(request)) {
			return "remoteMVCPath";
		}

		return "mvcPath";
	}

	public static String getMVCPathParam(PortletRequest portletRequest) {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return getMVCPathParam(request);
	}

	public static PortletPreferences getPortletPreferences()
		throws SystemException {

		long ownerId = OSBConstants.COMPANY_ID;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = OSBPortletKeys.OSB_MARKETPLACE_ADMIN;
		String defaultPreferences = null;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			OSBConstants.COMPANY_ID, ownerId, ownerType, plid, portletId,
			defaultPreferences);
	}

	public static AssetCategory getSubcategory(AssetEntry assetEntry)
		throws SystemException {

		List<AssetCategory> assetCategories = assetEntry.getCategories();

		for (AssetCategory assetCategory : assetCategories) {
			if (!assetCategory.isRootCategory()) {
				return assetCategory;
			}
		}

		return null;
	}

	public static String getSubcategoryName(AssetEntry assetEntry)
		throws SystemException {

		AssetCategory assetCategory = getSubcategory(assetEntry);

		if (assetCategory == null) {
			return StringPool.BLANK;
		}
		else {
			return assetCategory.getName();
		}
	}

	public static MBMessage getUserDiscussionMessage(
			long userId, String className, long classPK)
		throws SystemException {

		List<MBMessage> messages =
			MBMessageLocalServiceUtil.getUserDiscussionMessages(
				userId, className, classPK, WorkflowConstants.STATUS_ANY,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (MBMessage message : messages) {
			if (!message.isRoot()) {
				return message;
			}
		}

		return null;
	}

	public static boolean isAppEntryDeveloper(long userId, AppEntry appEntry)
		throws PortalException, SystemException {

		DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

		if (developerEntry.isCompany()) {
			CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
				developerEntry.getDossieraAccountKey());

			Group group = corpEntry.getGroup();

			if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(
					userId, group.getGroupId(),
					OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID)) {

				return true;
			}
		}
		else if (developerEntry.isUser()) {
			if (userId == developerEntry.getUserId()) {
				return true;
			}
		}

		return false;
	}

	public static boolean isMarketplaceServer(HttpServletRequest request) {
		String portletId = PortalUtil.getPortletId(request);

		if (portletId.equals(OSBPortletKeys.OSB_MARKETPLACE_SERVER)) {
			return true;
		}

		return false;
	}

	public static boolean isMarketplaceServer(PortletRequest portletRequest) {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		return isMarketplaceServer(request);
	}

	public static boolean isUnselectableCategory(long assetCategoryId) {
		return ArrayUtil.contains(
			PortletPropsValues.MARKETPLACE_UNSELECTABLE_ASSET_CATEGORY_IDS,
			assetCategoryId);
	}

	public static String shortenString(String s, int length) {
		if (s.length() > length) {
			return s.substring(0, length - 3) + "...";
		}
		else {
			return s;
		}
	}

	private static void _addAssetCategoryBreadcrumbEntries(
		List<AssetCategory> assetCategories, HttpServletRequest request,
		RenderResponse renderResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL portletURL = renderResponse.createRenderURL();

		portletURL.setParameter(
			getMVCPathParam(request), "/marketplace/view_category.jsp");

		for (AssetCategory assetCategory : assetCategories) {
			if (assetCategory.getCategoryId() ==
					OSBConstants.ASSET_CATEGORY_EE_PLUGINS_ID) {

				continue;
			}

			portletURL.setParameter(
				"assetCategoryId",
				String.valueOf(assetCategory.getCategoryId()));

			PortalUtil.addPortletBreadcrumbEntry(
				request, assetCategory.getTitle(themeDisplay.getLanguageId()),
				portletURL.toString());
		}
	}

	private static DynamicQuery _getAppEntryReviewsDynamicQuery(
		long mbThreadId, int status) {

		DynamicQuery dynamicQuery = MBMessageLocalServiceUtil.dynamicQuery();

		Property threadIdProperty = PropertyFactoryUtil.forName("threadId");

		dynamicQuery.add(threadIdProperty.eq(mbThreadId));

		Property parentMessageIdProperty = PropertyFactoryUtil.forName(
			"parentMessageId");

		dynamicQuery.add(parentMessageIdProperty.ne(0L));

		if (status != WorkflowConstants.STATUS_ANY) {
			Property statusProperty = PropertyFactoryUtil.forName("status");

			dynamicQuery.add(statusProperty.eq(status));
		}

		return dynamicQuery;
	}

	private static void _setAssetEntryQueryCategoryId(
			AssetEntryQuery assetEntryQuery, long assetCategoryId)
		throws SystemException {

		long[] assetCategoryIds = new long[] {assetCategoryId};

		if (assetCategoryId ==
				AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID) {

			List<AssetCategory> assetCategories =
				AssetCategoryLocalServiceUtil.getVocabularyRootCategories(
					OSBConstants.ASSET_VOCABULARY_MARKETPLACE_ID,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			assetCategoryIds = new long[assetCategories.size()];

			for (int i = 0; i < assetCategoryIds.length; i++) {
				AssetCategory assetCategory = assetCategories.get(i);

				assetCategoryIds[i] = assetCategory.getCategoryId();
			}

			assetEntryQuery.setAnyCategoryIds(assetCategoryIds);
		}
		else {
			assetEntryQuery.setAllCategoryIds(assetCategoryIds);
		}
	}

	private static Pattern _urlPattern = Pattern.compile(
		"(https?://[A-Z0-9@#%_+=;:./-]*(\\?([A-Z0-9(&amp;)%_+=;./-])*)?" +
			"[A-Z0-9#%_+=;/-])",
		Pattern.CASE_INSENSITIVE);

}