/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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