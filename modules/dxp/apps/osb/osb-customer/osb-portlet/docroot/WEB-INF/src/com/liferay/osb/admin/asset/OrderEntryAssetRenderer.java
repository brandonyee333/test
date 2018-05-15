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

package com.liferay.osb.admin.asset;

import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.util.OSBPortletKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Amos Fong
 */
public class OrderEntryAssetRenderer extends AccountEntryAssetRenderer {

	public OrderEntryAssetRenderer(OrderEntry orderEntry)
		throws PortalException {

		super(orderEntry.getAccountEntry());

		_orderEntry = orderEntry;
	}

	@Override
	public long getClassPK() {
		return _orderEntry.getOrderEntryId();
	}

	@Override
	public String getJspPath(HttpServletRequest request, String template) {
		if (template.equals(TEMPLATE_ABSTRACT) ||
			template.equals(TEMPLATE_FULL_CONTENT)) {

			return "/admin/asset/order_entry/" + template + ".jsp";
		}
		else {
			return null;
		}
	}

	@Override
	public String getURLViewInContext(
			LiferayPortletRequest liferayPortletRequest,
			LiferayPortletResponse liferayPortletResponse,
			String noSuchEntryRedirect)
		throws Exception {

		PortletURL portletURL = liferayPortletResponse.createLiferayPortletURL(
			getControlPanelPlid(liferayPortletRequest),
			OSBPortletKeys.OSB_ADMIN, PortletRequest.RENDER_PHASE);

		portletURL.setParameter("mvcPath", "/admin/edit_order_entry.jsp");
		portletURL.setParameter(
			"orderEntryId", String.valueOf(_orderEntry.getOrderEntryId()));

		return portletURL.toString();
	}

	@Override
	public long getUserId() {
		return _orderEntry.getUserId();
	}

	@Override
	public String getUserName() {
		return _orderEntry.getUserName();
	}

	private final OrderEntry _orderEntry;

}