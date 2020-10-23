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

package com.liferay.osb.customer.koroneiki.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public interface AccountReader {

	public Team getFirstLineSupportTeam(Account account) throws Exception;

	public List<ProductPurchase> getProductPurchases(String accountKey)
		throws Exception;

	public ProductPurchase getSLAProductPurchase(
		List<ProductPurchase> productPurchases);

	public int getStatus(Account account);

	public String getSubscriptionState(List<ProductPurchase> productPurchases);

	public Date getSupportEndDate(List<ProductPurchase> productPurchases);

	public Date getTicketSupportEndDate(List<ProductPurchase> productPurchases);

}