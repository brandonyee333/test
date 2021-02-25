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
import com.liferay.osb.customer.account.entry.details.web.internal.display.context.AccountEntryViewDisplayContext;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLanguageLocalService;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.permission.AccountEntryPermission;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.AuditEntryWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseViewWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.customer.ticket.service.TicketAttachmentLocalService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

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
		"mvc.command.name=/view_account_entry"
	},
	service = MVCRenderCommand.class
)
public class ViewAccountEntryMVCRenderCommand extends BaseMVCRenderCommand {

	@Override
	protected String doRender(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String koroneikiAccountKey = ParamUtil.getString(
			renderRequest, "koroneikiAccountKey");

		if (Validator.isNull(koroneikiAccountKey)) {
			Long accountEntryId = ParamUtil.getLong(
				renderRequest, "accountEntryId");

			AccountEntry accountEntry =
				_accountEntryLocalService.getAccountEntry(accountEntryId);

			koroneikiAccountKey = accountEntry.getKoroneikiAccountKey();
		}

		AccountEntryPermission.check(
			themeDisplay.getPermissionChecker(), koroneikiAccountKey,
			ActionKeys.VIEW);

		Account account = _accountWebService.getAccount(koroneikiAccountKey);

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

		if (accountEntryViewDisplayContext.isLiferayContractorOrg() ||
			accountEntryViewDisplayContext.isLiferayIncOrg()) {

			return "/account_entry_details/worker/view_account_entry.jsp";
		}

		return "/account_entry_details/customer/view_account_entry.jsp";
	}

	@Reference
	private AccountEntryLanguageLocalService _accountEntryLanguageLocalService;

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

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
	private TeamWebService _teamWebService;

	@Reference
	private TicketAttachmentLocalService _ticketAttachmentLocalService;

}