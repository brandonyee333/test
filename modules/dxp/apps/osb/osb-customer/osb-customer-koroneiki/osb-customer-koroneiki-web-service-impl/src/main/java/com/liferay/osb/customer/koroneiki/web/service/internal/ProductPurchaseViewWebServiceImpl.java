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

import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Page;
import com.liferay.osb.koroneiki.phloem.rest.client.pagination.Pagination;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductPurchaseViewResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductPurchaseViewSerDes;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Kyle Bischof
 */
@Component(
	configurationPid = "com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ProductPurchaseViewWebService.class
)
public class ProductPurchaseViewWebServiceImpl
	implements ProductPurchaseViewWebService {

	public ProductPurchaseView fetchProductPurchaseView(
			String accountKey, String productKey)
		throws Exception {

		HttpInvoker.HttpResponse httpResponse =
			_productPurchaseViewResource.
				getAccountAccountKeyProductProductKeyProductPurchaseViewHttpResponse(
					accountKey, productKey);

		if ((httpResponse.getStatusCode() ==
				HttpServletResponse.SC_NO_CONTENT) ||
			(httpResponse.getStatusCode() ==
				HttpServletResponse.SC_NOT_FOUND)) {

			return null;
		}

		return ProductPurchaseViewSerDes.toDTO(httpResponse.getContent());
	}

	public List<ProductPurchaseView> getProductPurchaseViews(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception {

		try {
			Page<ProductPurchaseView> productPurchaseViewsPage =
				_productPurchaseViewResource.getProductPurchaseViewsPage(
					search, filterString, Pagination.of(page, pageSize),
					sortString);

			if ((productPurchaseViewsPage != null) &&
				(productPurchaseViewsPage.getItems() != null)) {

				return new ArrayList<>(productPurchaseViewsPage.getItems());
			}

			return Collections.emptyList();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return Collections.emptyList();
		}
	}

	public long getProductPurchaseViewsCount(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception {

		try {
			Page<ProductPurchaseView> productPurchaseViewsPage =
				_productPurchaseViewResource.getProductPurchaseViewsPage(
					search, filterString, Pagination.of(page, pageSize),
					sortString);

			if (productPurchaseViewsPage != null) {
				return productPurchaseViewsPage.getTotalCount();
			}

			return 0;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return 0;
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductPurchaseViewResource.Builder builder =
			ProductPurchaseViewResource.builder();

		_productPurchaseViewResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductPurchaseViewWebServiceImpl.class);

	private ProductPurchaseViewResource _productPurchaseViewResource;

}