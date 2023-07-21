/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service.internal;

import com.liferay.osb.customer.koroneiki.web.service.ExternalLinkWebService;
import com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ExternalLinkResource;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ExternalLinkWebService.class
)
public class ExternalLinkWebServiceImpl implements ExternalLinkWebService {

	public List<ExternalLink> getExternalLinks(
			String accountKey, int page, int pageSize)
		throws Exception {

		Page<ExternalLink> externalLinksPage =
			_externalLinkResource.getAccountAccountKeyExternalLinksPage(
				accountKey, Pagination.of(page, pageSize));

		if ((externalLinksPage != null) &&
			(externalLinksPage.getItems() != null)) {

			return new ArrayList<>(externalLinksPage.getItems());
		}

		return Collections.emptyList();
	}

	public void postExternalLink(String accountKey, ExternalLink externalLink)
		throws Exception {

		_externalLinkResource.postAccountAccountKeyExternalLink(
			StringPool.BLANK, StringPool.BLANK, accountKey, externalLink);
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ExternalLinkResource.Builder builder = ExternalLinkResource.builder();

		_externalLinkResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private ExternalLinkResource _externalLinkResource;

}