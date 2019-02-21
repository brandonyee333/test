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
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.model.listener.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserIdentityWebService;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

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

		sync(user, null, tags);
	}

	public void addPhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

		_asyncZendeskUserIdentityWebService.createZendeskUserIdentity(
			zendeskUserId, "phone_number", phone.getNumber());
	}

	public void deletePhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);
		long zendeskUserIdentityId = getExternalId(
			Phone.class, phone.getPhoneId());

		_asyncZendeskUserIdentityWebService.deleteZendeskUserIdentity(
			zendeskUserId, zendeskUserIdentityId, "phone_number");
	}

	public void removeObsoleteTags(long userId) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

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

			if (accountEntry.getActiveTicketSupport()) {
				customer = true;

				break;
			}
		}

		if (!customer) {
			tags.add(ZendeskTagConstants.OSB_CUSTOMER);
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

		if (!customer && partnerWorkers.isEmpty() &&
			!_organizationLocalService.hasUserOrganization(
				userId, OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID) &&
			!AccountEntryLocalServiceUtil.hasValidSupportAccountEntry(
				userId, false)) {

			tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);
		}

		if (!tags.isEmpty()) {
			_asyncZendeskUserWebService.deleteZendeskUserTags(
				zendeskUserId, tags);
		}
	}

	public long sync(User user, String organizationName, Set<String> tags)
		throws PortalException {

		String locale = ZendeskModelListenerUtil.convertToZendeskLocale(
			user.getLanguageId());

		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
			user.getUserId());

		if (zendeskUserId != 0) {
			_asyncZendeskUserWebService.createOrUpdateZendeskUser(
				user.getUuid(), user.getEmailAddress(), locale,
				user.getFullName(), organizationName, null);

			if ((tags != null) && !tags.isEmpty()) {
				_asyncZendeskUserWebService.addZendeskUserTags(
					zendeskUserId, tags);
			}
		}
		else {
			ZendeskUser zendeskUser =
				_zendeskUserWebService.createOrUpdateZendeskUser(
					user.getUuid(), user.getEmailAddress(), locale,
					user.getFullName(), organizationName, tags);

			zendeskUserId = zendeskUser.getZendeskUserId();
		}

		return zendeskUserId;
	}

	public void updatePhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);
		long zendeskUserIdentityId = getExternalId(
			Phone.class, phone.getPhoneId());

		_asyncZendeskUserIdentityWebService.updateZendeskUserIdentity(
			zendeskUserId, zendeskUserIdentityId, phone.getNumber());
	}

	protected long getExternalId(Class<?> clazz, long classPK) {
		long classNameId = _classNameLocalService.getClassNameId(clazz);

		List<ExternalIdMapper> externalIdMappers =
			ExternalIdMapperLocalServiceUtil.getExternalIdMappers(
				classNameId, classPK, ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers.isEmpty()) {
			return 0;
		}

		ExternalIdMapper externalIdMapper = externalIdMappers.get(0);

		return Long.valueOf(externalIdMapper.getExternalId());
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
	private UserLocalService _userLocalService;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference
	private ZendeskUserWebService _zendeskUserWebService;

}