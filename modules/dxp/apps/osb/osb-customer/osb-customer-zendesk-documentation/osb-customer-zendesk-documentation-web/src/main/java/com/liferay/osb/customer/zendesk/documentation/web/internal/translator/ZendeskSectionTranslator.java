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

package com.liferay.osb.customer.zendesk.documentation.web.internal.translator;

import com.liferay.osb.customer.zendesk.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.web.service.ZendeskSectionWebService;
import com.liferay.osb.customer.zendesk.web.service.search.Query;
import com.liferay.osb.customer.zendesk.web.service.search.SearchHits;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ZendeskSectionTranslator.class)
public class ZendeskSectionTranslator extends BaseTranslator<ZendeskSection> {

	protected SearchHits<ZendeskSection> search(Query query)
		throws PortalException {

		return _zendeskSectionWebService.getZendeskSections(query);
	}

	@Reference
	private ZendeskSectionWebService _zendeskSectionWebService;

}