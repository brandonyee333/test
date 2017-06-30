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

import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Val Nagy
 */
public class TrainingEventSearchTerms extends TrainingEventDisplayTerms {

	public TrainingEventSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		countryId = ParamUtil.getLong(portletRequest, COUNTRY_ID);
		portalMinorVersion = ParamUtil.getInteger(
			portletRequest, PORTAL_MINOR_VERSION);
		regionId = ParamUtil.getLong(portletRequest, REGION_ID);
		scope = ParamUtil.getInteger(portletRequest, SCOPE);
		type = ParamUtil.getInteger(portletRequest, TYPE);
	}

	public Long getCountryIdObj() {
		if (countryId == 0) {
			return null;
		}
		else {
			return new Long(countryId);
		}
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (!isAdvancedSearch()) {
			return params;
		}

		if (scope > 0) {
			params.put("ddlRecordSet", scope);
		}

		return params;
	}

	public Integer getPortalMinorVersionObj() {
		if (portalMinorVersion == 0) {
			return null;
		}
		else {
			return new Integer(portalMinorVersion);
		}
	}

	public Long getRegionIdObj() {
		if (regionId == 0) {
			return null;
		}
		else {
			return new Long(regionId);
		}
	}

	public Integer getTypeObj() {
		if (type == -1) {
			return null;
		}
		else {
			return new Integer(type);
		}
	}

}