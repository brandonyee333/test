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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Douglas Wong
 */
public class CorpEntryDisplayTerms extends DisplayTerms {

	public static final String COUNTRY_ID = "countryId";

	public static final String NAME = "name";

	public CorpEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		countryId = ParamUtil.getLong(portletRequest, COUNTRY_ID);
		name = ParamUtil.getString(portletRequest, NAME);
	}

	public long getCountryId() {
		return countryId;
	}

	public String getName() {
		return name;
	}

	protected long countryId;
	protected String name;

}