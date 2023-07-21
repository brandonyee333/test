/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchaseView;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface ProductPurchaseViewWebService {

	public ProductPurchaseView fetchProductPurchaseView(
			String accountKey, String productKey)
		throws Exception;

	public List<ProductPurchaseView> getProductPurchaseViews(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception;

	public long getProductPurchaseViewsCount(
			String search, String filterString, int page, int pageSize,
			String sortString)
		throws Exception;

}