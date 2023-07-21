/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.util;

import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public interface AccountReader {

	public long getCorpProjectId(ExternalLink[] externalLinks);

	public String getCorpProjectUuid(ExternalLink[] externalLinks);

	public String getDossieraAccountKey(ExternalLink[] externalLinks);

	public Team getFirstLineSupportTeam(Account account) throws Exception;

	public List<ProductPurchase> getProductPurchases(String accountKey)
		throws Exception;

	public ProductPurchase getSLAProductPurchase(
		List<ProductPurchase> productPurchases);

	public String getState(String accountKey) throws Exception;

	public int getStatus(Account account);

	public String getSubscriptionState(List<ProductPurchase> productPurchases);

	public Date getSupportEndDate(List<ProductPurchase> productPurchases);

	public Date getTicketSupportEndDate(List<ProductPurchase> productPurchases);

	public boolean isActiveSupport(Entitlement[] entitlements);

	public boolean isActiveTicketSupport(Entitlement[] entitlements);

	public boolean isSyncAccount(List<ProductPurchase> productPurchases)
		throws Exception;

}