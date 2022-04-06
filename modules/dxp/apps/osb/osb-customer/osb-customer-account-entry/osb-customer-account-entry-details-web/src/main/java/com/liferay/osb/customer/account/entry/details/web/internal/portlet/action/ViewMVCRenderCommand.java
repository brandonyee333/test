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

package com.liferay.osb.customer.account.entry.details.web.internal.portlet.action;

import com.liferay.osb.customer.account.entry.details.web.internal.constants.AccountEntryDetailsPortletKeys;
import com.liferay.osb.customer.account.entry.details.web.internal.display.context.AccountEntrySearchDisplayContext;
import com.liferay.osb.customer.account.entry.details.web.internal.display.context.AccountEntryViewDisplayContext;
import com.liferay.osb.customer.admin.service.AccountEntryLanguageLocalService;
import com.liferay.osb.customer.admin.service.permission.AccountEntryPermission;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.customer.ticket.service.TicketAttachmentLocalService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + AccountEntryDetailsPortletKeys.ACCOUNT_ENTRY_DETAILS,
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand extends BaseMVCRenderCommand {

	@Override
	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		AccountEntrySearchDisplayContext accountEntrySearchDisplayContext =
			new AccountEntrySearchDisplayContext(
				renderRequest, renderResponse, themeDisplay, _accountReader,
				_accountWebService, _productWebService, _teamRoleWebService);

		renderRequest.setAttribute(
			AccountEntrySearchDisplayContext.class.getName(),
			accountEntrySearchDisplayContext);

		Account account = getAccount(accountEntrySearchDisplayContext);

		if (account != null) {
			AccountEntryPermission.check(
				themeDisplay.getPermissionChecker(), account.getKey(),
				ActionKeys.VIEW);

			AccountEntryViewDisplayContext accountEntryViewDisplayContext =
				new AccountEntryViewDisplayContext(
					renderRequest, renderResponse, account, _accountReader,
					_accountEntryLanguageLocalService, _auditEntryWebService,
					_contactRoleWebService, _contactWebService,
					_productPurchaseViewWebService, _productPurchaseWebService,
					_teamWebService, _ticketAttachmentLocalService);

			renderRequest.setAttribute(
				AccountEntryViewDisplayContext.class.getName(),
				accountEntryViewDisplayContext);

			return "/account_entry_details/customer/view_account_entry.jsp";
		}

		return "/account_entry_details/search.jsp";
	}

	protected Account getAccount(
			AccountEntrySearchDisplayContext accountEntrySearchDisplayContext)
		throws Exception {

		if (accountEntrySearchDisplayContext.isAdminOrSubscriptionServices()) {
			return null;
		}

		SearchContainer searchContainer =
			accountEntrySearchDisplayContext.getAccountsSearchContainer();

		if (searchContainer.getTotal() <= 0) {
			throw new PrincipalException();
		}

		List<Account> accounts = searchContainer.getResults();

		if (accounts.size() == 1) {
			return accounts.get(0);
		}

		return null;
	}

	@Reference
	private AccountEntryLanguageLocalService _accountEntryLanguageLocalService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private AuditEntryWebService _auditEntryWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private ProductPurchaseViewWebService _productPurchaseViewWebService;

	@Reference
	private ProductPurchaseWebService _productPurchaseWebService;

	@Reference
	private ProductWebService _productWebService;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private TicketAttachmentLocalService _ticketAttachmentLocalService;

}