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

package com.liferay.osb.customer.identity.management.internal.provider;

import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.okta.web.service.OktaWebService;
import com.liferay.osb.customer.okta.web.service.constants.OktaConstants;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	configurationPid = "com.liferay.osb.customer.identity.management.internal.configuration.OktaUserIdentityConfiguration",
	immediate = true, property = "provider=okta",
	service = UserIdentityProvider.class
)
public class OktaUserIdentityProvider implements UserIdentityProvider {

	public void addOrganizationMembership(long organizationId, long userId)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	public User fetchUserByEmailAddress(String emailAddress) throws Exception {
		User user = _userLocalService.fetchUserByEmailAddress(
			_companyId, emailAddress);

		if (user != null) {
			return user;
		}

		JSONObject userJSONObject = _oktaWebService.getUser(emailAddress);

		if (userJSONObject == null) {
			return null;
		}

		JSONObject profileJSONObject = userJSONObject.getJSONObject("profile");

		Locale locale = LocaleUtil.fromLanguageId(
			profileJSONObject.getString("locale"));

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(profileJSONObject.getString("uuid"));

		user = _userLocalService.addUser(
			_defaultUserId, _companyId, true, StringPool.BLANK,
			StringPool.BLANK, false, profileJSONObject.getString("uuid"),
			profileJSONObject.getString("email"), 0, StringPool.BLANK, locale,
			profileJSONObject.getString("firstName"),
			profileJSONObject.getString("middleName"),
			profileJSONObject.getString("lastName"), 0, 0, false, 0, 1, 1970,
			StringPool.BLANK, new long[0], new long[0], new long[0],
			new long[0], false, serviceContext);

		String oktaUserId = userJSONObject.getString("id");

		JSONArray groupsJSONArray = _oktaWebService.getUserGroups(oktaUserId);

		if (groupsJSONArray != null) {
			for (int i = 0; i < groupsJSONArray.length(); i++) {
				JSONObject groupJSONObject = groupsJSONArray.getJSONObject(i);

				JSONObject groupProfileJSONObject =
					groupJSONObject.getJSONObject("profile");

				String name = groupProfileJSONObject.getString("name");

				if (ArrayUtil.contains(OktaConstants.GROUPS, name)) {
					Organization organization =
						_organizationLocalService.fetchOrganization(
							_companyId, name);

					if (organization != null) {
						_organizationLocalService.addUserOrganization(
							user.getUserId(), organization);
					}
				}
			}
		}

		String primaryPhone = profileJSONObject.getString("primaryPhone");

		Contact contact = user.getContact();

		if (Validator.isNotNull(primaryPhone)) {
			long typeId = 0;

			List<ListType> listTypes = _listTypeLocalService.getListTypes(
				"com.liferay.portal.kernel.model.Contact.phone");

			for (ListType listType : listTypes) {
				String name = listType.getName();

				if (name.equals("business")) {
					typeId = listType.getListTypeId();

					break;
				}
			}

			_phoneLocalService.addPhone(
				user.getUserId(), Contact.class.getName(),
				contact.getContactId(), primaryPhone, StringPool.BLANK, typeId,
				true, serviceContext);
		}

		String mobilePhone = profileJSONObject.getString("mobilePhone");

		if (Validator.isNotNull(mobilePhone)) {
			long typeId = 0;

			List<ListType> listTypes = _listTypeLocalService.getListTypes(
				"com.liferay.portal.kernel.model.Contact.phone");

			for (ListType listType : listTypes) {
				String name = listType.getName();

				if (name.equals("mobile-phone")) {
					typeId = listType.getListTypeId();

					break;
				}
			}

			_phoneLocalService.addPhone(
				user.getUserId(), Contact.class.getName(),
				contact.getContactId(), mobilePhone, StringPool.BLANK, typeId,
				false, serviceContext);
		}

		return user;
	}

	public User fetchUserByProviderId(String providerId) throws Exception {
		throw new UnsupportedOperationException();
	}

	public User getUserByEmailAddress(String emailAddress) throws Exception {
		User user = fetchUserByEmailAddress(emailAddress);

		if (user == null) {
			throw new NoSuchUserException();
		}

		return user;
	}

	public User getUserByProviderId(String providerId) throws Exception {
		throw new UnsupportedOperationException();
	}

	public void removeOrganizationMembership(long organizationId, long userId)
		throws Exception {

		throw new UnsupportedOperationException();
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) throws Exception {
		_companyId = _portalInstancesLocalService.getDefaultCompanyId();

		User user = _userLocalService.getDefaultUser(_companyId);

		_defaultUserId = user.getUserId();
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	private long _companyId;
	private long _defaultUserId;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private ListTypeLocalService _listTypeLocalService;

	@Reference
	private OktaWebService _oktaWebService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private PhoneLocalService _phoneLocalService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

	@Reference
	private UserLocalService _userLocalService;

}