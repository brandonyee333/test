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

package com.liferay.osb.customer.zendesk.synchronizer.util;

import com.liferay.osb.customer.koroneiki.constants.ProductConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = AccountUtil.class)
public class AccountUtil {

	public Team getFirstLineSupportTeam(Account account) throws Exception {
		Team[] assignedTeams = account.getAssignedTeams();

		if (assignedTeams != null) {
			for (Team team : assignedTeams) {
				List<TeamRole> teamRoles = _teamRoleWebService.getTeamRoles(
					account.getKey(), team.getKey());

				for (TeamRole teamRole : teamRoles) {
					String teamRoleName = teamRole.getName();

					if (teamRoleName.equals(
							TeamRoleConstants.NAME_FIRST_LINE_SUPPORT)) {

						return team;
					}
				}
			}
		}

		return null;
	}

	public List<ProductPurchase> getProductPurchases(String accountKey)
		throws Exception {

		StringBundler sb = new StringBundler(3);

		sb.append("accountKey eq '");
		sb.append(accountKey);
		sb.append("' and state eq 'active'");

		return _productPurchaseWebService.search(sb.toString(), 1, 1000);
	}

	public boolean hasActiveSupport(Account account) throws Exception {
		Date now = new Date();

		List<ProductPurchase> productPurchases = getProductPurchases(
			account.getKey());

		for (ProductPurchase productPurchase : productPurchases) {
			Product product = productPurchase.getProduct();

			String name = product.getName();

			if (name.equals(ProductConstants.NAME_GOLD) ||
				name.equals(ProductConstants.NAME_LIMITED) ||
				name.equals(ProductConstants.NAME_PLATINUM) ||
				name.equals(ProductConstants.NAME_SILVER)) {

				if (productPurchase.getPerpetual()) {
					return true;
				}

				Date endDate = productPurchase.getEndDate();

				if (now.before(endDate)) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean hasActiveTicketSupport(Account account) throws Exception {
		List<ProductPurchase> productPurchases = getProductPurchases(
			account.getKey());

		for (ProductPurchase productPurchase : productPurchases) {
			Map<String, String> properties = productPurchase.getProperties();

			if (properties.get("Tickets") != null) {
				return true;
			}
		}

		return false;
	}

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

}