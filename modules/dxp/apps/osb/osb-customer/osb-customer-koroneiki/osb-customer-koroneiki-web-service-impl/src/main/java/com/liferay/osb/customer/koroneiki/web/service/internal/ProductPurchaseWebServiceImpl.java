/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service.internal;

import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductPurchaseResource;
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
	immediate = true, service = ProductPurchaseWebService.class
)
public class ProductPurchaseWebServiceImpl
	implements ProductPurchaseWebService {

	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception {

		return _productPurchaseResource.getProductPurchase(productPurchaseKey);
	}

	public List<ProductPurchase> search(
			String filterString, int page, int pageSize)
		throws Exception {

		Page<ProductPurchase> productPurchasesPage =
			_productPurchaseResource.getProductPurchasesPage(
				StringPool.BLANK, filterString, Pagination.of(page, pageSize),
				StringPool.BLANK);

		if ((productPurchasesPage != null) &&
			(productPurchasesPage.getItems() != null)) {

			return new ArrayList<>(productPurchasesPage.getItems());
		}

		return Collections.emptyList();
	}

	public long searchCount(String filterString) throws Exception {
		Page<ProductPurchase> productPurchasesPage =
			_productPurchaseResource.getProductPurchasesPage(
				StringPool.BLANK, filterString, Pagination.of(1, 1),
				StringPool.BLANK);

		if (productPurchasesPage != null) {
			return productPurchasesPage.getTotalCount();
		}

		return 0;
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductPurchaseResource.Builder builder =
			ProductPurchaseResource.builder();

		_productPurchaseResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private ProductPurchaseResource _productPurchaseResource;

}