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
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.customer.koroneiki.constants.ProductConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.service.AuditFormLocalService;
import com.liferay.osb.customer.service.TrainingBaseWebService;
import com.liferay.osb.customer.web.internal.constants.PassportAccessPortletKeys;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ProductPurchase;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RequiredFieldException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Date;
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

			if (!hasPermission(themeDisplay.getUser(), endUserEmailAddress)) {
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

	protected boolean hasPermission(User user, String endUserEmailAddress)
		throws Exception {

		if (isLiferayEmployee(user.getUserId())) {
			return true;
		}

		String domain = endUserEmailAddress.substring(
			endUserEmailAddress.indexOf(StringPool.AT) + 1);

		if (_badDomains.contains(domain)) {
			return false;
		}

		StringBundler sb = new StringBundler(5);

		sb.append("customerContactEmailAddresses/any(s:contains(s, '@");
		sb.append(domain);
		sb.append("')) and status eq '");
		sb.append(Account.Status.ACTIVE);
		sb.append("'");

		List<Account> accounts = _accountWebService.search(
			StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

		for (Account account : accounts) {
			PostalAddress[] postalAddresses = account.getPostalAddresses();

			boolean hasCustomerAccessCountry = false;
			boolean hasPartnerAccessCountry = false;

			if (!ArrayUtil.isEmpty(postalAddresses)) {
				for (PostalAddress postalAddress : postalAddresses) {
					if (!ArrayUtil.contains(
							_CUSTOMER_NO_ACCESS_COUNTRIES,
							postalAddress.getAddressCountry())) {

						hasCustomerAccessCountry = true;
					}

					if (!ArrayUtil.contains(
							_PARTNER_NO_ACCESS_COUNTRIES,
							postalAddress.getAddressCountry())) {

						hasPartnerAccessCountry = true;
					}
				}
			}

			if (hasPartnerAccessCountry) {
				Entitlement[] entitlements = account.getEntitlements();

				if (entitlements != null) {
					for (Entitlement entitlement : entitlements) {
						String entitlementName = entitlement.getName();

						if (entitlementName.equals(
								EntitlementConstants.NAME_PARTNER)) {

							return true;
						}
					}
				}
			}

			if (!hasCustomerAccessCountry) {
				continue;
			}

			Date now = new Date();

			ProductPurchase[] productPurchases = account.getProductPurchases();

			if (productPurchases != null) {
				for (ProductPurchase productPurchase : productPurchases) {
					if (productPurchase.getStatus() ==
							ProductPurchase.Status.CANCELLED) {

						continue;
					}

					if ((productPurchase.getEndDate() != null) &&
						now.after(productPurchase.getEndDate())) {

						continue;
					}

					if ((productPurchase.getStartDate() != null) &&
						now.before(productPurchase.getStartDate())) {

						continue;
					}

					Product product = productPurchase.getProduct();

					Map<String, String> properties = product.getProperties();

					String type = properties.get(
						ProductConstants.PROPERTY_TYPE);

					if (Validator.isNotNull(type) &&
						type.equals(ProductConstants.TYPE_PRIMARY)) {

						String productName = product.getName();

						if (productName.startsWith(
								ProductConstants.NAME_PREFIX_DXP) ||
							productName.startsWith(
								ProductConstants.NAME_PREFIX_PORTAL)) {

							return true;
						}
					}
				}
			}
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

	private static final String[] _CUSTOMER_NO_ACCESS_COUNTRIES = {
		"India", "Japan"
	};

	private static final String[] _PARTNER_NO_ACCESS_COUNTRIES = {"India"};

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
	private AccountWebService _accountWebService;

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