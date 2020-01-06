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

package com.liferay.osb.customer.koroneiki.web.service.internal;

import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ContactResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ContactWebService.class
)
public class ContactWebServiceImpl implements ContactWebService {

	public Contact fetchContactByUuid(String uuid) throws Exception {
		HttpInvoker.HttpResponse httpResponse =
			_contactResource.getContactByUuidContactUuidHttpResponse(uuid);

		if (httpResponse.getStatusCode() == 404) {
			return null;
		}

		return ContactSerDes.toDTO(httpResponse.getContent());
	}

	public List<Contact> search(String filterString, int page, int pageSize)
		throws Exception {

		Page<Contact> contactsPage = _contactResource.getContactsPage(
			StringPool.BLANK, filterString, Pagination.of(page, pageSize),
			StringPool.BLANK);

		if ((contactsPage != null) && (contactsPage.getItems() != null)) {
			return new ArrayList<>(contactsPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(String filterString) throws Exception {
		Page<Contact> contactsPage = _contactResource.getContactsPage(
			StringPool.BLANK, filterString, Pagination.of(1, 1),
			StringPool.BLANK);

		if (contactsPage != null) {
			return contactsPage.getTotalCount();
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ContactResource.Builder builder = ContactResource.builder();

		_contactResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private ContactResource _contactResource;

}