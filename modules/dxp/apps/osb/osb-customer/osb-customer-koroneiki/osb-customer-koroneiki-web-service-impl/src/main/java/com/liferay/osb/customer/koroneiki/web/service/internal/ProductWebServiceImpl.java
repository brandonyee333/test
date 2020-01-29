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

import com.liferay.osb.customer.koroneiki.web.service.ProductWebService;
import com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.http.HttpInvoker;
import com.liferay.osb.koroneiki.phloem.rest.client.resource.v1_0.ProductResource;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductSerDes;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.util.Http;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.koroneiki.web.service.internal.configuration.KoroneikiConfiguration",
	immediate = true, service = ProductWebService.class
)
public class ProductWebServiceImpl implements ProductWebService {

	public Product fetchProduct(String productKey) throws Exception {
		HttpInvoker.HttpResponse httpResponse =
			_productResource.getProductHttpResponse(productKey);

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			return null;
		}

		return ProductSerDes.toDTO(httpResponse.getContent());
	}

	public Product fetchProductByName(String name) throws Exception {
		HttpInvoker.HttpResponse httpResponse =
			_productResource.getProductByNameProductNameHttpResponse(
				_http.encodePath(name));

		if (httpResponse.getStatusCode() == HttpServletResponse.SC_NOT_FOUND) {
			return null;
		}

		return ProductSerDes.toDTO(httpResponse.getContent());
	}

	@Activate
	protected void activate(Map<String, Object> properties) throws Exception {
		KoroneikiConfiguration koroneikiConfiguration =
			ConfigurableUtil.createConfigurable(
				KoroneikiConfiguration.class, properties);

		ProductResource.Builder builder = ProductResource.builder();

		_productResource = builder.endpoint(
			koroneikiConfiguration.host(), koroneikiConfiguration.port(),
			koroneikiConfiguration.scheme()
		).header(
			"API_Token", koroneikiConfiguration.apiToken()
		).build();
	}

	@Reference
	private Http _http;

	private ProductResource _productResource;

}