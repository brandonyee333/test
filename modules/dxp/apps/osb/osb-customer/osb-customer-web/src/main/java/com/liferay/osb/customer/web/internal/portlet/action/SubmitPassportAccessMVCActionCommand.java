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

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.exception.EmailAddressDomainException;
import com.liferay.osb.customer.exception.SubscriptionException;
import com.liferay.osb.customer.service.AuditFormLocalService;
import com.liferay.osb.customer.service.TrainingBaseWebService;
import com.liferay.osb.customer.web.internal.constants.PassportAccessPortletKeys;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	property = {
		"javax.portlet.name=" + PassportAccessPortletKeys.PASSPORT_ACCESS,
		"mvc.command.name=/submit_passport_access"
	},
	service = MVCActionCommand.class
)
public class SubmitPassportAccessMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			String endUserEmailAddress = ParamUtil.getString(
				actionRequest, "endUserWorkEmailAddress");

			if (!hasPermission(themeDisplay.getUserId(), endUserEmailAddress)) {
				throw new SubscriptionException();
			}

			String endUserFirstName = ParamUtil.getString(
				actionRequest, "endUserFirstName");
			String endUserLastName = ParamUtil.getString(
				actionRequest, "endUserLastName");
			String companyName = ParamUtil.getString(
				actionRequest, "companyName");
			boolean agreement = ParamUtil.getBoolean(
				actionRequest, "agreement");

			_auditFormLocalService.addAuditForm(
				themeDisplay.getUserId(),
				endUserFirstName + StringPool.SPACE + endUserLastName,
				endUserEmailAddress, companyName, agreement);

			Map<String, String> parameters = new HashMap<>();

			parameters.put("emailAddress", endUserEmailAddress);
			parameters.put("firstName", endUserFirstName);
			parameters.put("lastName", endUserLastName);

			_trainingBaseWebService.post(parameters);

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", "/success");
		}
		catch (Exception e) {
			if (e instanceof EmailAddressDomainException ||
				e instanceof RequiredFieldException ||
				e instanceof SubscriptionException) {

				SessionErrors.add(actionRequest, e.getClass());

				actionResponse.setRenderParameter(
					"mvcRenderCommandName", "/view");
			}
			else {
				_log.error(e, e);

				throw e;
			}
		}
	}

	protected boolean hasPermission(long userId, String endUserEmailAddress)
		throws PortalException {

		if (!isVerifiedUser(userId)) {
			return false;
		}

		if (isLiferayEmployee(userId) || isPartner(userId)) {
			return true;
		}

		if (isCustomer(userId)) {
			List<AccountEntry> accountEntries =
				AccountEntryLocalServiceUtil.getUserActiveAccountEntries(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (AccountEntry accountEntry : accountEntries) {
				Address address = accountEntry.getAddress();

				Country country = address.getCountry();

				String countryName = country.getName();

				if (!ArrayUtil.contains(_NO_ACCESS_COUNTRIES, countryName)) {
					return true;
				}
			}
		}
		else {
			String domain = endUserEmailAddress.substring(
				endUserEmailAddress.indexOf(StringPool.AT) + 1);

			if (_badDomains.contains(domain)) {
				return false;
			}

			if (AccountCustomerLocalServiceUtil.countPassportCustomersByDomain(
					domain) > 0) {

				return true;
			}

			if (PartnerWorkerLocalServiceUtil.countPassportPartnersByDomain(
					domain) > 0) {

				return true;
			}
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

	protected boolean isVerifiedUser(long userId) throws PortalException {
		if (_roleLocalService.hasUserRole(
				userId, OSBConstants.ROLE_VERIFIED_USER_ID)) {

			return true;
		}

		return false;
	}

	private static final String[] _NO_ACCESS_COUNTRIES = {
		"china", "india", "japan"
	};

	private static final Log _log = LogFactoryUtil.getLog(
		SubmitPassportAccessMVCActionCommand.class);

	private static final Set<String> _badDomains = new HashSet<>();

	static {
		try {
			StringUtil.readLines(
				SubmitPassportAccessMVCActionCommand.class.getResourceAsStream(
					"/dependencies/bad_domains.txt"),
				_badDomains);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Reference
	private AuditFormLocalService _auditFormLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private TrainingBaseWebService _trainingBaseWebService;

	@Reference
	private UserLocalService _userLocalService;

}