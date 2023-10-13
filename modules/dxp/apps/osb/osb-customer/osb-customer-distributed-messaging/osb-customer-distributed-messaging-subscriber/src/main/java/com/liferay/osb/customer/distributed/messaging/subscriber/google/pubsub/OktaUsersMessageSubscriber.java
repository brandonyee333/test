/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.distributed.messaging.subscriber.google.pubsub;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.zendesk.synchronizer.CustomerSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.UserSynchronizer;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ListTypeLocalService;
import com.liferay.portal.kernel.service.PhoneLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true, property = "topic.pattern=okta-users",
	service = OktaUsersMessageSubscriber.class
)
public class OktaUsersMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	protected void doReceive(JSONObject jsonObject) throws Exception {
		String eventType = jsonObject.getString("eventType");

		if (eventType.equals(_EVENT_TYPE_CREATE)) {
			_createUser(jsonObject.getJSONObject("user"));
		}
		else if (eventType.equals(_EVENT_TYPE_DEACTIVATE)) {
			_downgradeZendeskAgent(jsonObject.getJSONObject("user"));
		}
		else if (eventType.equals(_EVENT_TYPE_DELETE)) {
			_deleteUser(jsonObject.getJSONObject("user"));
		}
		else if (eventType.equals(_EVENT_TYPE_GROUP_REMOVE)) {
			if (_isGroupEmployee(jsonObject)) {
				_downgradeZendeskAgent(jsonObject.getJSONObject("user"));
			}
		}
		else if (eventType.equals(_EVENT_TYPE_UPDATE)) {
			_updateUser(jsonObject.getJSONObject("user"));
		}
	}

	private void _createUser(JSONObject jsonObject) throws Exception {
		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact contact =
			_contactWebService.fetchContactByUuid(
				profileJSONObject.getString("uuid"));

		if (contact == null) {
			return;
		}

		User user = _userIdentityProvider.fetchUserByEmailAddress(
			contact.getEmailAddress());

		List<Account> accounts = _accountWebService.getContactAccountsByUuid(
			contact.getUuid(), 1, 1000);

		for (Account account : accounts) {
			if (!_accountReader.isActiveTicketSupport(
					account.getEntitlements())) {

				continue;
			}

			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountContactRoles(
					account.getKey(), contact.getUuid(), 1, 1000);

			for (ContactRole contactRole : contactRoles) {
				if (ArrayUtil.contains(
						ContactRoleConstants.PARTNER_CONTACT_ROLES,
						contactRole.getName()) ||
					ArrayUtil.contains(
						ContactRoleConstants.SUPPORT_CONTACT_ROLES,
						contactRole.getName())) {

					AccountEntry accountEntry =
						_accountEntryLocalService.getKoroneikiAccountEntry(
							account.getKey());

					_customerSynchronizer.add(user, account, accountEntry);

					break;
				}
			}
		}
	}

	private void _deleteUser(JSONObject jsonObject) throws Exception {
		User user = _fetchUserByEmailAddress(
			jsonObject.getJSONObject("profile"));

		if (user == null) {
			return;
		}

		_userLocalService.deleteUser(user.getUserId());
	}

	private void _downgradeZendeskAgent(JSONObject jsonObject)
		throws Exception {

		User user = _fetchUser(jsonObject.getJSONObject("profile"));

		if (user != null) {
			_userSynchronizer.removeAgentRole(user);
		}
	}

	private User _fetchUser(JSONObject jsonObject) throws Exception {
		String uuid = jsonObject.getString("uuid");

		if (Validator.isNull(uuid)) {
			return null;
		}

		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			User user = _userLocalService.fetchUserByUuidAndCompanyId(
				uuid, company.getCompanyId());

			if (user != null) {
				return user;
			}
		}

		return null;
	}

	private User _fetchUserByEmailAddress(JSONObject jsonObject)
		throws Exception {

		String emailAddress = jsonObject.getString("email");

		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			User user = _userLocalService.fetchUserByEmailAddress(
				company.getCompanyId(), emailAddress);

			if (user != null) {
				return user;
			}
		}

		return null;
	}

	private boolean _isGroupEmployee(JSONObject jsonObject) {
		JSONObject groupJSONObject = jsonObject.getJSONObject("group");

		String name = groupJSONObject.getString("displayName");

		if (name.equals(_GROUP_NAME_EMPLOYEES)) {
			return true;
		}

		return false;
	}

	private void _syncPhone(
			User user, long typeId, String number,
			Map<Long, String> phoneNumbers, boolean primary)
		throws Exception {

		List<Phone> phones = user.getPhones();

		if (Validator.isNotNull(number)) {
			if (phoneNumbers.containsKey(typeId)) {
				for (Phone curPhone : phones) {
					if (typeId == curPhone.getTypeId()) {
						_phoneLocalService.updatePhone(
							curPhone.getPhoneId(), number,
							curPhone.getExtension(), typeId,
							curPhone.isPrimary());
					}
				}
			}
			else {
				ServiceContext serviceContext = new ServiceContext();

				Contact contact = user.getContact();

				serviceContext.setUuid(contact.getUserUuid());

				_phoneLocalService.addPhone(
					user.getUserId(), Contact.class.getName(),
					contact.getContactId(), number, StringPool.BLANK, typeId,
					primary, serviceContext);
			}
		}
		else {
			for (Phone curPhone : phones) {
				if (typeId == curPhone.getTypeId()) {
					_phoneLocalService.deletePhone(curPhone);
				}
			}
		}
	}

	private void _updateUser(JSONObject jsonObject) throws Exception {
		JSONObject profileJSONObject = jsonObject.getJSONObject("profile");

		User user = _fetchUser(profileJSONObject);

		if (user == null) {
			return;
		}

		Contact contact = user.getContact();

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(contact.getBirthday());

		user = _userLocalService.updateUser(
			user.getUserId(), null, null, null, false, null, null,
			user.getScreenName(), profileJSONObject.getString("email"),
			user.getFacebookId(), user.getOpenId(), true, null,
			user.getLanguageId(), user.getTimeZoneId(), user.getGreeting(),
			user.getComments(), profileJSONObject.getString("firstName"),
			user.getMiddleName(), profileJSONObject.getString("lastName"),
			contact.getPrefixId(), contact.getSuffixId(), contact.isMale(),
			calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE),
			calendar.get(Calendar.YEAR), contact.getSmsSn(),
			contact.getFacebookSn(), contact.getJabberSn(),
			contact.getSkypeSn(), contact.getTwitterSn(), user.getJobTitle(),
			null, null, null, null, null, null);

		long businessTypeId = 0;
		long mobileTypeId = 0;

		List<ListType> listTypes = _listTypeLocalService.getListTypes(
			"com.liferay.portal.kernel.model.Contact.phone");

		for (ListType listType : listTypes) {
			String name = listType.getName();

			if (name.equals("business")) {
				businessTypeId = listType.getListTypeId();
			}

			if (name.equals("mobile-phone")) {
				mobileTypeId = listType.getListTypeId();
			}
		}

		List<Phone> phones = user.getPhones();

		Map<Long, String> phoneNumbers = new HashMap<>();

		for (Phone curPhone : phones) {
			if (((businessTypeId == curPhone.getTypeId()) &&
				 curPhone.isPrimary()) ||
				(mobileTypeId == curPhone.getTypeId())) {

				phoneNumbers.put(curPhone.getTypeId(), curPhone.getNumber());
			}
			else {
				_phoneLocalService.deletePhone(curPhone);
			}
		}

		if (profileJSONObject.has("primaryPhone")) {
			_syncPhone(
				user, businessTypeId,
				profileJSONObject.getString("primaryPhone"), phoneNumbers,
				true);
		}

		if (profileJSONObject.has("mobilePhone")) {
			_syncPhone(
				user, mobileTypeId, profileJSONObject.getString("mobilePhone"),
				phoneNumbers, false);
		}
	}

	private static final String _EVENT_TYPE_CREATE = "user.lifecycle.create";

	private static final String _EVENT_TYPE_DEACTIVATE =
		"user.lifecycle.deactivate";

	private static final String _EVENT_TYPE_DELETE =
		"user.lifecycle.delete.initiated";

	private static final String _EVENT_TYPE_GROUP_REMOVE =
		"group.user_membership.remove";

	private static final String _EVENT_TYPE_UPDATE =
		"user.account.update_profile";

	private static final String _GROUP_NAME_EMPLOYEES = "Employees";

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private CustomerSynchronizer _customerSynchronizer;

	@Reference
	private ListTypeLocalService _listTypeLocalService;

	@Reference
	private PhoneLocalService _phoneLocalService;

	@Reference(target = "(provider=okta)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

}