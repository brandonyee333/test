/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.item.selector.web.internal.display.context;

import com.liferay.item.selector.ItemSelectorReturnType;
import com.liferay.layout.item.selector.criterion.LayoutItemSelectorCriterion;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Roberto Díaz
 */
public class LayoutItemSelectorViewDisplayContext {

	public LayoutItemSelectorViewDisplayContext(
		HttpServletRequest request,
		LayoutItemSelectorCriterion layoutItemSelectorCriterion,
		String itemSelectedEventName, boolean privateLayout) {

		_request = request;
		_layoutItemSelectorCriterion = layoutItemSelectorCriterion;
		_itemSelectedEventName = itemSelectedEventName;
		_privateLayout = privateLayout;
	}

	public String getCkEditorFuncNum() {
		return ParamUtil.getString(_request, "CKEditorFuncNum");
	}

	public PortletURL getEditLayoutURL() throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		PortletURL editLayoutURL = PortletProviderUtil.getPortletURL(
			_request, Layout.class.getName(), PortletProvider.Action.EDIT);

		editLayoutURL.setParameter("redirect", themeDisplay.getURLCurrent());
		editLayoutURL.setParameter(
			"groupId", String.valueOf(themeDisplay.getScopeGroupId()));

		return editLayoutURL;
	}

	public String getItemSelectedEventName() {
		return _itemSelectedEventName;
	}

	public String getItemSelectorReturnTypeName() {
		List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
			_layoutItemSelectorCriterion.getDesiredItemSelectorReturnTypes();

		ItemSelectorReturnType desiredItemSelectorReturnType =
			desiredItemSelectorReturnTypes.get(0);

		String itemSelectorReturnTypeName = ClassUtil.getClassName(
			desiredItemSelectorReturnType);

		if (Validator.isNull(itemSelectorReturnTypeName)) {
			throw new IllegalArgumentException(
				"Invalid item selector return type " +
					itemSelectorReturnTypeName);
		}

		return itemSelectorReturnTypeName;
	}

	public LayoutItemSelectorCriterion getLayoutItemSelectorCriterion() {
		return _layoutItemSelectorCriterion;
	}

	public String getRootNodeName() {
		ThemeDisplay themeDisplay = (ThemeDisplay)_request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Group group = themeDisplay.getScopeGroup();

		return group.getLayoutRootNodeName(
			isPrivateLayout(), themeDisplay.getLocale());
	}

	public String getSelectedLayoutIds() {
		return ParamUtil.getString(_request, "selectedLayoutIds");
	}

	public long getSelPlid() {
		return ParamUtil.getLong(
			_request, "selPlid", LayoutConstants.DEFAULT_PLID);
	}

	public boolean isPrivateLayout() {
		return _privateLayout;
	}

	private final String _itemSelectedEventName;
	private final LayoutItemSelectorCriterion _layoutItemSelectorCriterion;
	private final boolean _privateLayout;
	private final HttpServletRequest _request;

}