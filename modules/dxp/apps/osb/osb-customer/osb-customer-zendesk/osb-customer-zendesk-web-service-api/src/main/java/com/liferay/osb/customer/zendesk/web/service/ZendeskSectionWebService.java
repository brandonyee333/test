/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskSectionWebService {

	public SearchHits<ZendeskSection> getZendeskSections(Query query)
		throws PortalException;

}