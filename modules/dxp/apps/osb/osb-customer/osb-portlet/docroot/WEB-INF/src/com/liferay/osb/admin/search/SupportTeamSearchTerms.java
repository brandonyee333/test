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

import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brent Krone-Schmidt
 */
public class SupportTeamSearchTerms extends SupportTeamDisplayTerms {

	public SupportTeamSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = DAOParamUtil.getString(portletRequest, NAME);

		String typeString = ParamUtil.getString(portletRequest, TYPE);

		if (Validator.isNotNull(typeString)) {
			type = GetterUtil.getInteger(typeString);
		}
	}

}