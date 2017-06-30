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

package com.liferay.osb.admin.search;

import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Alan Zhang
 */
public class OfferingDefinitionSearchTerms
	extends OfferingDefinitionDisplayTerms {

	public OfferingDefinitionSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		productEntryIds = ParamUtil.getLongValues(
			portletRequest, PRODUCT_ENTRY_IDS);
		supportResponseIds = ParamUtil.getLongValues(
			portletRequest, SUPPORT_RESPONSE_IDS);
	}

}