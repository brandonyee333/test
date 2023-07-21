/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface ProductPurchaseWebService {

	public ProductPurchase getProductPurchase(String productPurchaseKey)
		throws Exception;

	public List<ProductPurchase> search(
			String filterString, int page, int pageSize)
		throws Exception;

	public long searchCount(String filterString) throws Exception;

}