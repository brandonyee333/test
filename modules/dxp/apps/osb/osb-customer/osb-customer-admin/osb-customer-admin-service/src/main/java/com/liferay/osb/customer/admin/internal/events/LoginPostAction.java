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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.osb.customer.admin.internal.configuration.LoginPostActionConfiguration;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.admin.internal.events.LoginPostAction",
	immediate = true, property = "key=login.events.post",
	service = LifecycleAction.class
)
public class LoginPostAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

		try {
			User user = _portal.getUser(request);

			if (_loginPostActionConfiguration.syncKoroneiki()) {
				synchronizeWithKoroneiki(user);
			}

			synchronizeWithOkta(user);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws Exception {
		_loginPostActionConfiguration = ConfigurableUtil.createConfigurable(
			LoginPostActionConfiguration.class, properties);
	}

	protected Map<String, Long> getEntitlements(User user) throws Exception {
		Map<String, Long> entitlementsMap = new HashMap<>();

		List<Organization> organizations = user.getOrganizations();

		for (Organization organization : organizations) {
			String name = organization.getName();

			if (name.startsWith(
					EntitlementConstants.ORGANIZATION_NAME_PREFIX)) {

				entitlementsMap.put(
					StringUtil.removeSubstring(
						name, EntitlementConstants.ORGANIZATION_NAME_PREFIX),
					organization.getOrganizationId());
			}
		}

		return entitlementsMap;
	}

	protected void synchronizeWithKoroneiki(User user) throws Exception {
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

	protected void synchronizeWithOkta(User user) throws Exception {

		// User

		User oktaUser = _userIdentityProvider.getUserByEmailAddress(
			user.getEmailAddress());

		if (_loginPostActionConfiguration.syncOktaUser()) {
			com.liferay.portal.kernel.model.Contact contact = user.getContact();

			Calendar calendar = Calendar.getInstance();

			calendar.setTime(contact.getBirthday());

			_userLocalService.updateUser(
				user.getUserId(), null, null, null, false, null, null,
				oktaUser.getScreenName(), oktaUser.getEmailAddress(),
				user.getFacebookId(), user.getOpenId(), true, null,
				oktaUser.getLanguageId(), oktaUser.getTimeZoneId(),
				user.getGreeting(), user.getComments(), oktaUser.getFirstName(),
				oktaUser.getMiddleName(), oktaUser.getLastName(),
				contact.getPrefixId(), contact.getSuffixId(), contact.isMale(),
				calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
				calendar.get(Calendar.YEAR), contact.getSmsSn(),
				contact.getFacebookSn(), contact.getJabberSn(),
				contact.getSkypeSn(), contact.getTwitterSn(),
				oktaUser.getJobTitle(), null, null, null, null, null, null);
		}

		// Organizations

		long[] oktaOrganizationIds = oktaUser.getOrganizationIds();

		long[] curOrganizationIds = user.getOrganizationIds();

		for (long curOrganizationId : curOrganizationIds) {
			if (ArrayUtil.contains(oktaOrganizationIds, curOrganizationId)) {
				continue;
			}

			Organization organization =
				_organizationLocalService.getOrganization(curOrganizationId);

			ExpandoBridge organizationExpandoBridge =
				organization.getExpandoBridge();

			boolean remote = (Boolean)organizationExpandoBridge.getAttribute(
				"remote", false);

			if (!remote) {
				continue;
			}

			String name = organization.getName();

			if (!name.startsWith(
					EntitlementConstants.ORGANIZATION_NAME_PREFIX)) {

				_userLocalService.unsetOrganizationUsers(
					curOrganizationId, new long[] {user.getUserId()});
			}
		}

		for (long oktaOrganizationId : oktaOrganizationIds) {
			if (ArrayUtil.contains(curOrganizationIds, oktaOrganizationId)) {
				continue;
			}

			Organization organization =
				_organizationLocalService.getOrganization(oktaOrganizationId);

			String name = organization.getName();

			if (!name.startsWith(
					EntitlementConstants.ORGANIZATION_NAME_PREFIX)) {

				_userLocalService.addOrganizationUsers(
					oktaOrganizationId, new long[] {user.getUserId()});
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LoginPostAction.class);

	@Reference
	private ContactWebService _contactWebService;

	private volatile LoginPostActionConfiguration _loginPostActionConfiguration;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private Portal _portal;

	@Reference(target = "(provider=okta)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserLocalService _userLocalService;

}