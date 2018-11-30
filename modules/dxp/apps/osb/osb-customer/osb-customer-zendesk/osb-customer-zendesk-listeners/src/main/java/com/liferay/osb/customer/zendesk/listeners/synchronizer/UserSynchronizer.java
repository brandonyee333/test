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

package com.liferay.osb.customer.zendesk.listeners.synchronizer;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.zendesk.connector.constants.ZendeskTagConstants;
import com.liferay.osb.customer.zendesk.listeners.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.listeners.util.ZendeskModelListenerUtil;
import com.liferay.osb.customer.zendesk.model.ZendeskUser;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapper;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

		Set<String> tags = new HashSet<>();

		tags.add(ZendeskTagConstants.OSB_KNOWLEDGE_BASE);

		_asyncZendeskUserWebService.addZendeskUserTags(zendeskUserId, tags);
	}

	public void addPhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);

		_asyncZendeskUserWebService.createZendeskUserIdentity(
			zendeskUserId, "phone_number", phone.getNumber());
	}

	public void deletePhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);
		long zendeskUserIdentityId = getExternalId(
			Phone.class, phone.getPhoneId());

		_asyncZendeskUserWebService.deleteZendeskUserIdentity(
			zendeskUserId, zendeskUserIdentityId, "phone_number");
	}

	public void removeObsoleteTags(long userId) throws PortalException {
		try {
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

			boolean partner = false;

			List<PartnerWorker> partnerWorkers =
				PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(userId);

			for (PartnerWorker partnerWorker : partnerWorkers) {
				if (partnerWorker.getRole() !=
						PartnerWorkerConstants.ROLE_WATCHER) {

					partner = true;

					break;
				}
			}

			if (!partner) {
				tags.add(ZendeskTagConstants.OSB_PARTNER);
			}

			// Knowledge base

			if (!customer && !partner &&
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
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public long sync(
			User user, long accountEntryId, String organizationName,
			Set<String> tags)
		throws PortalException {

		try {
			String locale = ZendeskModelListenerUtil.convertToZendeskLocale(
				user.getLanguageId());

			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				user.getUserId());

			if (zendeskUserId != 0) {
				_asyncZendeskUserWebService.createOrUpdateZendeskUser(
					user.getUuid(), user.getEmailAddress(), locale,
					user.getFullName(), organizationName, null);

				_asyncZendeskUserWebService.addZendeskUserTags(
					zendeskUserId, tags);
			}
			else {
				ZendeskUser zendeskUser =
					_zendeskUserWebService.createOrUpdateZendeskUser(
						user.getUuid(), user.getEmailAddress(), locale,
						user.getFullName(), organizationName, tags);

				zendeskUserId = zendeskUser.getZendeskUserId();
			}

			if (accountEntryId > 0) {
				long zendeskOrganizationId =
					_zendeskMapperUtil.fetchZendeskOrganizationId(
						accountEntryId);

				_asyncZendeskUserWebService.
					createZendeskUserOrganizationSubscription(
						zendeskUserId, zendeskOrganizationId);
			}

			return zendeskUserId;
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	public void updatePhone(long userId, Phone phone) throws PortalException {
		long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(userId);
		long zendeskUserIdentityId = getExternalId(
			Phone.class, phone.getPhoneId());

		_asyncZendeskUserWebService.updateZendeskUserIdentity(
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

	private static final Log _log = LogFactoryUtil.getLog(
		UserSynchronizer.class);

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