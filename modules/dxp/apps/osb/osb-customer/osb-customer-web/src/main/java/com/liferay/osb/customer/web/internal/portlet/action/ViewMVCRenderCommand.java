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

package com.liferay.osb.customer.web.internal.portlet.action;

import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.web.internal.constants.PassportAccessPortletKeys;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + PassportAccessPortletKeys.PASSPORT_ACCESS,
		"mvc.command.name=/", "mvc.command.name=/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			if (hasPermission(themeDisplay.getUser())) {
				return "/passport/view.jsp";
			}
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass());
		}

		return "/passport/contact_support.jsp";
	}

	protected boolean hasPermission(User user) throws Exception {
		if (isLiferayEmployee(user.getUserId()) ||
			isPartner(user.getUserId())) {

			return true;
		}

		if (isCustomer(user.getUserId())) {
			List<Account> accounts = _accountWebService.search(
				StringPool.BLANK,
				"customerContactEmailAddresses/any(s:s eq '" +
					user.getEmailAddress() + "')",
				1, 1000, StringPool.BLANK);

			for (Account account : accounts) {
				PostalAddress[] postalAddresses = account.getPostalAddresses();

				if (postalAddresses == null) {
					continue;
				}

				for (PostalAddress postalAddress : postalAddresses) {
					if (ArrayUtil.contains(
							_NO_ACCESS_COUNTRIES,
							postalAddress.getAddressCountry())) {

						return false;
					}
				}
			}

			return true;
		}

		return false;
	}

	protected boolean isCustomer(long userId) throws PortalException {
		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_CUSTOMER_DXP_ID) ||
			_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_CUSTOMER_PORTAL_ID)) {

			return true;
		}

		return false;
	}

	protected boolean isLiferayEmployee(long userId) throws PortalException {
		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		return false;
	}

	protected boolean isPartner(long userId) throws PortalException {
		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_PARTNER_ID)) {

			return true;
		}

		return false;
	}

	private static final String[] _NO_ACCESS_COUNTRIES = {
		"China", "India", "Japan"
	};

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

}