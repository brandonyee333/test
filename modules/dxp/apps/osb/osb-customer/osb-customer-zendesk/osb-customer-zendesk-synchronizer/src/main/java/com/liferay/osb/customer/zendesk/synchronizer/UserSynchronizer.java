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

package com.liferay.osb.customer.zendesk.synchronizer;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailService;
import com.liferay.osb.customer.admin.constants.EntitlementConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactAccountViewWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.constants.ZendeskUserIdentityConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.ZendeskUserIdentity;
import com.liferay.osb.customer.zendesk.model.ZendeskUserRelated;
import com.liferay.osb.customer.zendesk.util.PhoneUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskLocaleUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserIdentityWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserRelatedWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactAccountView;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = UserSynchronizer.class)
public class UserSynchronizer {

	public void addPhone(long userId, Phone phone) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

		String number = _phoneUtil.convertToE164(phone);

		if ((zendeskUserId > 0) && Validator.isNotNull(number)) {
			_asyncZendeskUserIdentityWebService.createZendeskUserIdentity(
				zendeskUserId, "phone_number", number);
		}
	}

	public void deletePhone(long userId, Phone phone) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);
		long zendeskUserIdentityId =
			_zendeskMapperUtil.fetchZendeskUserIdentityId(phone.getPhoneId());

		if ((zendeskUserId > 0) && (zendeskUserIdentityId > 0)) {
			_asyncZendeskUserIdentityWebService.deleteZendeskUserIdentity(
				zendeskUserId, zendeskUserIdentityId, "phone_number");
		}
	}

	public void removeAgentRole(User user) throws Exception {
		ZendeskUser zendeskUser = _zendeskUserWebService.getZendeskUserByEmail(
			user.getEmailAddress());

		if (zendeskUser.isAgent()) {
			ZendeskUserRelated zendeskUserRelated =
				_zendeskUserRelatedWebService.getZendeskUserRelated(
					zendeskUser.getZendeskUserId());

			if (zendeskUserRelated.getAssignedTickets() > 0) {
				sendEmail(zendeskUser.getName(), zendeskUser.getEmail());
			}
			else {
				zendeskUser.setRole("end-user");

				sync(user, zendeskUser);
			}
		}
	}

	public void sync(User user, ZendeskUser zendeskUser) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		String emailAddress = user.getEmailAddress();

		String fullName = user.getFullName();

		if (hasPartnerWorker(user)) {
			fullName = fullName + " [P]";
		}

		String zendeskLocale = _zendeskLocaleUtil.convertToZendeskLocale(
			user.getLanguageId());

		if (Validator.isNull(zendeskLocale)) {
			zendeskLocale = _zendeskLocaleUtil.convertToZendeskLocale(
				LocaleUtil.US);
		}

		Set<String> tags = getTags(user);

		if (!StringUtil.equalsIgnoreCase(
				emailAddress, zendeskUser.getEmail()) ||
			!StringUtil.equalsIgnoreCase(fullName, zendeskUser.getName()) ||
			!StringUtil.equalsIgnoreCase(
				zendeskLocale, zendeskUser.getLocale()) ||
			!tags.equals(zendeskUser.getTags())) {

			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				user.getUuid(), StringPool.BLANK, zendeskLocale, fullName, null,
				zendeskUser.getRole(), tags);
		}
	}

	public long update(User user, String organizationName) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return 0;
		}

		String zendeskLocale = _zendeskLocaleUtil.convertToZendeskLocale(
			user.getLanguageId());

		if (Validator.isNull(zendeskLocale)) {
			zendeskLocale = _zendeskLocaleUtil.convertToZendeskLocale(
				LocaleUtil.US);
		}

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		String fullName = user.getFullName();

		if (hasPartnerWorker(user)) {
			fullName = fullName + " [P]";
		}

		if (zendeskUserId != 0) {
			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				user.getUuid(), StringPool.BLANK, zendeskLocale, fullName,
				organizationName, null, getTags(user));
		}
		else {
			ZendeskUser zendeskUser =
				_zendeskUserWebService.createOrUpdateZendeskUser(
					user.getUuid(), user.getEmailAddress(), zendeskLocale,
					fullName, organizationName, null, getTags(user));

			zendeskUserId = zendeskUser.getZendeskUserId();

			for (Phone phone : user.getPhones()) {
				addPhone(user.getUserId(), phone);
			}
		}

		return zendeskUserId;
	}

	public void updateEmailAddress(long userId, String emailAddress)
		throws Exception {

		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

		if (zendeskUserId <= 0) {
			return;
		}

		List<ZendeskUserIdentity> zendeskUserIdentities =
			_zendeskUserIdentityWebService.getZendeskUserIdentities(
				zendeskUserId);

		for (ZendeskUserIdentity zendeskUserIdentity : zendeskUserIdentities) {
			String type = zendeskUserIdentity.getType();

			if (type.equals(ZendeskUserIdentityConstants.TYPE_EMAIL) &&
				zendeskUserIdentity.isPrimary()) {

				_asyncZendeskUserIdentityWebService.updateZendeskUserIdentity(
					zendeskUserId,
					zendeskUserIdentity.getZendeskUserIdentityId(),
					emailAddress);

				break;
			}
		}
	}

	public void updatePhone(long userId, Phone phone) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);
		long zendeskUserIdentityId =
			_zendeskMapperUtil.fetchZendeskUserIdentityId(phone.getPhoneId());

		if ((zendeskUserId > 0) && (zendeskUserIdentityId > 0)) {
			String number = _phoneUtil.convertToE164(phone);

			if (Validator.isNotNull(number)) {
				_asyncZendeskUserIdentityWebService.updateZendeskUserIdentity(
					zendeskUserId, zendeskUserIdentityId, number);
			}
		}
		else {
			addPhone(userId, phone);
		}
	}

	public void updateTags(User user) throws Exception {
		if (!ZendeskSynchronizerThreadLocal.isEnabled()) {
			return;
		}

		Set<String> tags = getTags(user);

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		_asyncZendeskUserWebService.updateZendeskUserTags(zendeskUserId, tags);
	}

	protected Set<String> getTags(User user) throws Exception {
		Set<String> tags = new HashSet<>();

		// Customer

		List<ContactAccountView> contactAccountViews =
			_contactAccountViewWebService.
				getContactByUuidContactUuidContactAccountViewsPage(
					user.getUuid(), 1, 1000);

		for (ContactAccountView contactAccountView : contactAccountViews) {
			Account account = contactAccountView.getAccount();

			AccountEntry accountEntry =
				_accountEntryLocalService.fetchKoroneikiAccountEntry(
					account.getKey());

			if (accountEntry == null) {
				continue;
			}

			ContactRole[] contactRoles =
				contactAccountView.getCustomerContactRoles();

			if (accountEntry.isActiveSupport()) {
				tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			}

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			boolean watcher = false;
			boolean closedWatcher = false;

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(ContactRoleConstants.NAME_SUPPORT_WATCHER)) {
					watcher = true;
				}
				else if (name.equals(
							ContactRoleConstants.NAME_SUPPORT_CLOSED_WATCHER)) {

					closedWatcher = true;
				}
			}

			if (accountEntry.isActiveTicketSupport()) {
				if (watcher && (zendeskOrganizationId > 0)) {
					tags.add(
						ZendeskTagConstants.getWatcherTag(
							zendeskOrganizationId));
				}
				else {
					tags.add(ZendeskTagConstants.OSB_CUSTOMER);
				}
			}

			if (closedWatcher && (zendeskOrganizationId > 0)) {
				tags.add(
					ZendeskTagConstants.getWatcherTag(zendeskOrganizationId));
			}
		}

		// Liferay Contractor

		if (_organizationLocalService.hasUserOrganization(
				user.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID)) {

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
		}

		// Liferay Employee

		if (_organizationLocalService.hasUserOrganization(
				user.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
			tags.add(ZendeskTagConstants.OSB_LIFERAY_EMPLOYEE);
		}

		// Partner

		if (hasPartnerWorker(user)) {
			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);

			TeamRole flsTeamRole = _teamRoleWebService.fetchTeamRole(
				TeamRole.Type.ACCOUNT.toString(),
				TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

			if (flsTeamRole != null) {
				StringBundler sb = new StringBundler(5);

				sb.append("accountKeyTeamRoleKeys/any(s:contains(s, '_");
				sb.append(flsTeamRole.getKey());
				sb.append("')) and contactUuids/any(s:s eq '");
				sb.append(user.getUuid());
				sb.append("')");

				long teamsCount = _teamWebService.searchCount(
					StringPool.BLANK, sb.toString());

				if (teamsCount > 0) {
					tags.add(ZendeskTagConstants.OSB_PARTNER);
				}
			}
		}

		return tags;
	}

	protected boolean hasPartnerWorker(User user) throws Exception {
		if (_organizationLocalService.hasUserOrganization(
				user.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return false;
		}

		for (String contactRoleName :
				ContactRoleConstants.PARTNER_CONTACT_ROLES) {

			ContactRole contactRole = _contactRoleWebService.fetchContactRole(
				ContactRole.Type.ACCOUNT_CUSTOMER.toString(), contactRoleName);

			if (contactRole == null) {
				continue;
			}

			StringBundler sb = new StringBundler(7);

			sb.append("contactUuidContactRoleKeys/any(s:s eq '");
			sb.append(user.getUuid());
			sb.append("_");
			sb.append(contactRole.getKey());
			sb.append("') and entitlements/any(s:s eq '");
			sb.append(EntitlementConstants.NAME_PARTNER);
			sb.append("')");

			long accountsCount = _accountWebService.searchCount(
				StringPool.BLANK, sb.toString());

			if (accountsCount > 0) {
				return true;
			}
		}

		return false;
	}

	protected void sendEmail(String fullName, String emailAddress)
		throws AddressException {

		StringBundler sb = new StringBundler(6);

		sb.append("Please downgrade ");
		sb.append(fullName);
		sb.append(StringPool.SPACE);
		sb.append(emailAddress);
		sb.append(" on Zendesk. They have non-closed tickets assigned to them");
		sb.append(StringPool.PERIOD);

		InternetAddress from = new InternetAddress("noreply@liferay.com");
		InternetAddress to = new InternetAddress("zendesk-admin@liferay.com");

		String mailSubject = "Reassign Tickets and Downgrade Full Agent";

		MailMessage mailMessage = new MailMessage(
			from, to, mailSubject, sb.toString(), true);

		_mailService.sendEmail(mailMessage);
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountReader _accountReader;

	@Reference
	private AccountWebService _accountWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserIdentityWebService _asyncZendeskUserIdentityWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactAccountViewWebService _contactAccountViewWebService;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private MailService _mailService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private PhoneUtil _phoneUtil;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private ZendeskLocaleUtil _zendeskLocaleUtil;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskUserIdentityWebService _zendeskUserIdentityWebService;

	@Reference
	private ZendeskUserRelatedWebService _zendeskUserRelatedWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}