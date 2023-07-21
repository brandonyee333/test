/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.web.internal.translator;

import com.liferay.osb.customer.zendesk.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.web.service.ZendeskCategoryWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.Date;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskCategoryTranslator.class)
public class ZendeskCategoryTranslator extends BaseTranslator<ZendeskCategory> {

	protected boolean continueTranslating(
		ZendeskCategory zendeskCategory, Date stopDate) {

		if (stopDate.after(zendeskCategory.getCreateDate())) {
			return false;
		}

		return true;
	}

	protected String getSortBy() {
		return "created_at";
	}

	protected SearchHits<ZendeskCategory> search(Query query)
		throws PortalException {

		return _zendeskCategoryWebService.getZendeskCategories(query);
	}

	@Reference
	private ZendeskCategoryWebService _zendeskCategoryWebService;

}