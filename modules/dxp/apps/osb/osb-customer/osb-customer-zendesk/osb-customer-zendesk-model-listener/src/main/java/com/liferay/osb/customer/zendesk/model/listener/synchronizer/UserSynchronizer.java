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

package com.liferay.osb.customer.zendesk.model.listener.synchronizer;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.constants.ZendeskUserIdentityConstants;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.ZendeskUserIdentity;
import com.liferay.osb.customer.zendesk.util.PhoneUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskLocaleUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserIdentityWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = UserSynchronizer.class)
public class UserSynchronizer {

	public void addLiferayEmployee(long userId) throws PortalException {
		User user = _userLocalService.getUser(userId);

		Set<String> tags = new HashSet<>();

		tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
		tags.add(ZendeskTagConstants.OSB_LIFERAY_EMPLOYEE);

		update(user, null, tags);
	}

	public void addPhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

		String number = _phoneUtil.convertToE164(phone);

		if ((zendeskUserId > 0) && Validator.isNotNull(number)) {
			_asyncZendeskUserIdentityWebService.createZendeskUserIdentity(
				zendeskUserId, "phone_number", number);
		}
	}

	public void deletePhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);
		long zendeskUserIdentityId =
			_zendeskMapperUtil.fetchZendeskUserIdentityId(phone.getPhoneId());

		_asyncZendeskUserIdentityWebService.deleteZendeskUserIdentity(
			zendeskUserId, zendeskUserIdentityId, "phone_number");
	}

	public void removeObsoleteTags(
			long userId, Set<String> currentTags,
			Set<String> additionalObsoleteTags)
		throws PortalException {

		Set<String> tags = getObsoleteTags(userId);

		if (additionalObsoleteTags != null) {
			tags.addAll(additionalObsoleteTags);
		}

		if (currentTags != null) {
			tags.retainAll(currentTags);
		}

		if (!tags.isEmpty()) {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

			_asyncZendeskUserWebService.deleteZendeskUserTags(
				zendeskUserId, tags);
		}
	}

	public void sync(ZendeskUser zendeskUser, User user, Set<String> tags)
		throws PortalException {

		String emailAddress = user.getEmailAddress();
		String fullName = user.getFullName();
		String zendeskLocale = _zendeskLocaleUtil.convertToZendeskLocale(
			user.getLanguageId());

		if (!StringUtil.equalsIgnoreCase(
				emailAddress, zendeskUser.getEmail()) ||
			!StringUtil.equalsIgnoreCase(fullName, zendeskUser.getName()) ||
			!StringUtil.equalsIgnoreCase(
				zendeskLocale, zendeskUser.getLocale())) {

			update(user, null, null);
		}

		tags.removeAll(zendeskUser.getTags());

		if (!tags.isEmpty()) {
			_zendeskUserWebService.addZendeskUserTags(
				zendeskUser.getZendeskUserId(), tags);
		}
	}

	public long update(User user, String organizationName, Set<String> tags)
		throws PortalException {

		String zendeskLocale = _zendeskLocaleUtil.convertToZendeskLocale(
			user.getLanguageId());

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if (zendeskUserId != 0) {
			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				user.getUuid(), StringPool.BLANK, zendeskLocale,
				user.getFullName(), organizationName, null);

			if ((tags != null) && !tags.isEmpty()) {
				_asyncZendeskUserWebService.addZendeskUserTags(
					zendeskUserId, tags);
			}
		}
		else {
			ZendeskUser zendeskUser =
				_zendeskUserWebService.createOrUpdateZendeskUser(
					user.getUuid(), user.getEmailAddress(), zendeskLocale,
					user.getFullName(), organizationName, tags);

			zendeskUserId = zendeskUser.getZendeskUserId();

			for (Phone phone : user.getPhones()) {
				addPhone(user.getUserId(), phone);
			}
		}

		return zendeskUserId;
	}

	public void updateEmailAddress(long userId, String emailAddress)
		throws PortalException {

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

	public void updatePhone(long userId, Phone phone) throws PortalException {
		long zendeskUserIdentityId =
			_zendeskMapperUtil.fetchZendeskUserIdentityId(phone.getPhoneId());

		if (zendeskUserIdentityId > 0) {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

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

	protected Set<String> getObsoleteTags(long userId) throws PortalException {
		Set<String> tags = new HashSet<>();

		// Customer

		boolean customer = false;

		List<AccountCustomer> accountCustomers =
			AccountCustomerLocalServiceUtil.getUserAccountCustomers(userId);

		for (AccountCustomer accountCustomer : accountCustomers) {
			if (accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_WATCHER) {

				continue;
			}

			AccountEntry accountEntry = accountCustomer.getAccountEntry();

			if (accountEntry.isActiveTicketSupport()) {
				customer = true;

				break;
			}
		}

		if (!customer) {
			tags.add(ZendeskTagConstants.OSB_CUSTOMER);
		}

		// Liferay Employee

		boolean liferayEmployee = false;

		if (_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			liferayEmployee = true;
		}

		if (!liferayEmployee) {
			tags.add(ZendeskTagConstants.OSB_LIFERAY_EMPLOYEE);
		}

		// Partner

		boolean partnerManagedSupportDeveloper = false;

		List<PartnerWorker> partnerWorkers =
			PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(userId);

		for (PartnerWorker partnerWorker : partnerWorkers) {
			PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

			List<AccountEntry> accountEntries =
				partnerEntry.getPartnerManagedAccountEntries();

			if (accountEntries.isEmpty()) {
				continue;
			}

			if (partnerWorker.getRole() !=
					PartnerWorkerConstants.ROLE_WATCHER) {

				partnerManagedSupportDeveloper = true;

				break;
			}
		}

		if (!partnerManagedSupportDeveloper) {
			tags.add(ZendeskTagConstants.OSB_PARTNER);
		}

		// Knowledge base

		if (!customer && partnerWorkers.isEmpty() && !liferayEmployee &&
			!AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
				userId, false) &&
			!_organizationLocalService.hasUserOrganization(
				userId,
				OSBCustomerConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID)) {

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
		}

		return tags;
	}

	@Reference(target = "(async=true)")
	private ZendeskUserIdentityWebService _asyncZendeskUserIdentityWebService;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _asyncZendeskUserWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private PhoneUtil _phoneUtil;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private ZendeskLocaleUtil _zendeskLocaleUtil;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskUserIdentityWebService _zendeskUserIdentityWebService;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}