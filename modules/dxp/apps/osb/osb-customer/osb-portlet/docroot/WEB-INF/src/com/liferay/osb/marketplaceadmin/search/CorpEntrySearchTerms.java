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

package com.liferay.osb.marketplaceadmin.search;

import com.liferay.osb.util.WorkflowConstants;

import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Douglas Wong
 */
public class CorpEntrySearchTerms extends CorpEntryDisplayTerms {

	public CorpEntrySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (countryId > 0) {
			params.put("countryId", countryId);
		}

		if (_status.equals("disabled")) {
			params.put("corpEntryStatus", WorkflowConstants.STATUS_EXPIRED);
		}

		return params;
	}

	public void setStatus(String status) {
		_status = status;
	}

	private String _status;

}