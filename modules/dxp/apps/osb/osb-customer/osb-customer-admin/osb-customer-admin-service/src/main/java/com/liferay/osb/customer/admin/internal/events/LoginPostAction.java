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

package com.liferay.osb.customer.admin.internal.events;

import com.liferay.osb.customer.admin.constants.EntitlementConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true, property = "key=login.events.post",
	service = LifecycleAction.class
)
public class LoginPostAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			doRun(request, response);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void doRun(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		User user = _portal.getUser(request);

		Contact contact = _contactWebService.fetchContactByUuid(user.getUuid());

		if (contact == null) {
			return;
		}

		Map<String, Long> entitlementsMap = getEntitlements(user);

		Entitlement[] entitlements = contact.getEntitlements();

		if (entitlements != null) {
			for (Entitlement entitlement : entitlements) {
				String name = entitlement.getName();

				if (entitlementsMap.containsKey(name)) {
					entitlementsMap.remove(name);

					continue;
				}

				Organization organization =
					_organizationLocalService.fetchOrganization(
						user.getCompanyId(),
						EntitlementConstants.ORGANIZATION_NAME_PREFIX + name);

				if (organization == null) {
					return;
				}

				_userLocalService.addOrganizationUser(
					organization.getOrganizationId(), user.getUserId());
			}
		}

		for (Long organizationId : entitlementsMap.values()) {
			_userLocalService.unsetOrganizationUsers(
				organizationId, new long[] {user.getUserId()});
		}
	}

	protected Map<String, Long> getEntitlements(User user) throws Exception {
		Map<String, Long> entitlementsMap = new HashMap<>();

		List<Organization> organizations = user.getOrganizations();

		for (Organization organization : organizations) {
			String name = organization.getName();

			if (name.startsWith(
					EntitlementConstants.ORGANIZATION_NAME_PREFIX)) {

				entitlementsMap.put(
					name.replace(
						EntitlementConstants.ORGANIZATION_NAME_PREFIX,
						StringPool.BLANK),
					organization.getOrganizationId());
			}
		}

		return entitlementsMap;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoginPostAction.class);

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

}