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
import com.liferay.osb.customer.web.internal.constants.PassportAccessPortletKeys;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ArrayUtil;
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
			if (hasPermission(themeDisplay.getUserId())) {
				return "/passport/view.jsp";
			}
		}
		catch (Exception e) {
			SessionErrors.add(renderRequest, e.getClass());
		}

		return "/passport/contact_support.jsp";
	}

	protected boolean hasPermission(long userId) throws PortalException {
		if (isPartner(userId)) {
			return true;
		}

		if (isCustomer(userId)) {
			List<AccountEntry> accountEntries =
				AccountEntryLocalServiceUtil.getUserActiveAccountEntries(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (AccountEntry accountEntry : accountEntries) {
				for (long supportRegionId :
						accountEntry.getSupportRegionIds()) {

					if (!ArrayUtil.contains(
							_APAC_SUPPORT_REGION_IDS, supportRegionId)) {

						return true;
					}
				}
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

	protected boolean isPartner(long userId) throws PortalException {
		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_PARTNER_ID)) {

			return true;
		}

		return false;
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final long[] _APAC_SUPPORT_REGION_IDS = {
		42356498, 42356502, 45637701
	};

	@Reference
	private OrganizationLocalService _organizationLocalService;

}