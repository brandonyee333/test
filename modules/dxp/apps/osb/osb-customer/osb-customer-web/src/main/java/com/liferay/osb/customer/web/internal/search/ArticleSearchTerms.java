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

package com.liferay.osb.customer.web.internal.search;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.portlet.PortletRequest;

/**
 * @author Alan Zhang
 */
public class ArticleSearchTerms extends ArticleDisplayTerms {

	public ArticleSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public boolean hasSearchTerms() {
		if (Validator.isNull(getKeywords()) &&
			ArrayUtil.isEmpty(getAssetCategoryIds()) &&
			ArrayUtil.isEmpty(getLanguageIds()) &&
			ArrayUtil.isEmpty(getPermissionTypes())) {

			return false;
		}
		else {
			return true;
		}
	}

}