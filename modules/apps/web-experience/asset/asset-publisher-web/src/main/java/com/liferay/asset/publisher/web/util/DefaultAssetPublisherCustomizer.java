/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.publisher.web.util;

import com.liferay.asset.kernel.service.persistence.AssetEntryQuery;
import com.liferay.asset.kernel.util.AssetEntryQueryProcessor;
import com.liferay.asset.publisher.web.constants.AssetPublisherPortletKeys;
import com.liferay.asset.publisher.web.internal.configuration.AssetPublisherWebConfigurationValues;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = AssetPublisherCustomizer.class)
public class DefaultAssetPublisherCustomizer
	implements AssetPublisherCustomizer {

	@Override
	public Integer getDelta(HttpServletRequest request) {
		PortletPreferences portletPreferences = getPortletPreferences(request);

		return GetterUtil.getInteger(
			portletPreferences.getValue("delta", null),
			SearchContainer.DEFAULT_DELTA);
	}

	@Override
	public String getPortletId() {
		return AssetPublisherPortletKeys.ASSET_PUBLISHER;
	}

	@Override
	public boolean isEnablePermissions(HttpServletRequest request) {
		if (AssetPublisherWebConfigurationValues.SEARCH_WITH_INDEX) {
			return true;
		}

		if (!AssetPublisherWebConfigurationValues.
				PERMISSION_CHECKING_CONFIGURABLE) {

			return true;
		}

		PortletPreferences portletPreferences = getPortletPreferences(request);

		return GetterUtil.getBoolean(
			portletPreferences.getValue("enablePermissions", null), true);
	}

	@Override
	public boolean isOrderingAndGroupingEnabled(HttpServletRequest request) {
		return true;
	}

	@Override
	public boolean isOrderingByTitleEnabled(HttpServletRequest request) {
		if (!AssetPublisherWebConfigurationValues.SEARCH_WITH_INDEX) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isSelectionStyleEnabled(HttpServletRequest request) {
		return true;
	}

	@Override
	public boolean isShowAssetEntryQueryProcessor(
		AssetEntryQueryProcessor assetEntryQueryProcessor) {

		return true;
	}

	@Override
	public boolean isShowEnableAddContentButton(HttpServletRequest request) {
		return true;
	}

	@Override
	public boolean isShowEnableRelatedAssets(HttpServletRequest request) {
		return true;
	}

	@Override
	public boolean isShowScopeSelector(HttpServletRequest request) {
		return true;
	}

	@Override
	public boolean isShowSubtypeFieldsFilter(HttpServletRequest request) {
		if (!AssetPublisherWebConfigurationValues.SEARCH_WITH_INDEX) {
			return false;
		}

		return true;
	}

	@Override
	public void setAssetEntryQueryOptions(
		AssetEntryQuery assetEntryQuery, HttpServletRequest request) {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletPreferences portletPreferences = getPortletPreferences(request);

		long[] groupIds = AssetPublisherUtil.getGroupIds(
			portletPreferences, themeDisplay.getScopeGroupId(),
			themeDisplay.getLayout());

		assetEntryQuery.setGroupIds(groupIds);
	}

	protected String getPortletName(HttpServletRequest request) {
		PortletConfig portletConfig = (PortletConfig)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		if (portletConfig == null) {
			return StringPool.BLANK;
		}

		return portletConfig.getPortletName();
	}

	protected PortletPreferences getPortletPreferences(
		HttpServletRequest request) {

		PortletRequest portletRequest = (PortletRequest)request.getAttribute(
			JavaConstants.JAVAX_PORTLET_REQUEST);

		if (portletRequest != null) {
			return portletRequest.getPreferences();
		}

		return null;
	}

}