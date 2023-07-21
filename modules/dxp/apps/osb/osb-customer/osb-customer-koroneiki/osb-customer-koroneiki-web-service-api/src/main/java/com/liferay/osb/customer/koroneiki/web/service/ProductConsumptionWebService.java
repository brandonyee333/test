/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.web.service;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductConsumption;

import java.util.List;

/**
 * @author Amos Fong
 */
public interface ProductConsumptionWebService {

	public ProductConsumption addProductConsumption(
			String agentName, String agentUID, String accountKey,
			ProductConsumption productConsumption)
		throws Exception;

	public void deleteProductConsumption(
			String agentName, String agentUID, String productConsumptionKey)
		throws Exception;

	public List<ProductConsumption> getProductConsumptions(
			String domain, String entityName, String entityId, int page,
			int pageSize)
		throws Exception;

	public List<ProductConsumption> search(
			String filterString, int page, int pageSize)
		throws Exception;

	public long searchCount(String filterString) throws Exception;

}