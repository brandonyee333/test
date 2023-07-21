/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;

import java.util.List;

/**
 * @author Kyle Bischof
 */
public interface ProductWebService {

	public Product fetchProduct(String productKey) throws Exception;

	public Product fetchProductByName(String name) throws Exception;

	public List<Product> getProducts(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception;

}