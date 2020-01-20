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

package com.liferay.osb.customer.zendesk.synchronizer.listener.model;

import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;

import org.osgi.service.component.annotations.Component;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	/*@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (associationClassName.equals(Organization.class.getName())) {
				long userId = GetterUtil.getLong(classPK);
				long organizationId = GetterUtil.getLong(associationClassPK);

				if ((organizationId ==
						OSBCustomerConstants.
							ORGANIZATION_LIFERAY_CONTRACTOR_ID) ||
					(organizationId ==
						OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

					User user = _userLocalService.getUser(userId);

					_userSynchronizer.update(user, null);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (associationClassName.equals(Organization.class.getName())) {
				long userId = GetterUtil.getLong(classPK);
				long organizationId = GetterUtil.getLong(associationClassPK);

				if ((organizationId ==
						OSBCustomerConstants.
							ORGANIZATION_LIFERAY_CONTRACTOR_ID) ||
					(organizationId ==
						OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

					_userSynchronizer.updateTags(userId);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
		try {
			User oldUser = _oldUser.get();

			String oldEmailAddress = oldUser.getEmailAddress();

			if (!oldEmailAddress.equals(user.getEmailAddress())) {
				_userSynchronizer.updateEmailAddress(
					user.getUserId(), user.getEmailAddress());
			}

			String oldFirstName = oldUser.getFirstName();
			String oldLastName = oldUser.getLastName();

			if (!oldFirstName.equals(user.getFirstName()) ||
				!oldLastName.equals(user.getLastName())) {

				_userSynchronizer.update(user, null);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeRemove(User user) {
		try {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				user.getUserId());

			if (zendeskUserId <= 0) {
				return;
			}

			TODO
			List<AccountCustomer> accountCustomers =
				AccountCustomerLocalServiceUtil.getUserAccountCustomers(
					user.getUserId());

			for (AccountCustomer accountCustomer : accountCustomers) {
				try {
					_accountEntrySynchronizer.reassignTickets(accountCustomer);
				}
				catch (AccountCustomerRemovalException acre) {
					_accountEntrySynchronizer.closeZendeskTickets(
						accountCustomer.getAccountEntry());
				}
			}

			List<PartnerWorker> partnerWorkers =
				PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(
					user.getUserId());

			for (PartnerWorker partnerWorker : partnerWorkers) {
				PartnerEntry partnerEntry = partnerWorker.getPartnerEntry();

				List<AccountEntry> accountEntries =
					partnerEntry.getPartnerManagedAccountEntries();

				for (AccountEntry accountEntry : accountEntries) {
					try {
						long zendeskOrganizationId =
							_zendeskMapperUtil.fetchZendeskOrganizationId(
								accountEntry.getAccountEntryId());

						if (zendeskOrganizationId <= 0) {
							continue;
						}

						_accountEntrySynchronizer.reassignTickets(
							partnerWorker, accountEntry.getAccountEntryId());
					}
					catch (PartnerWorkerRemovalException pwre) {
						_accountEntrySynchronizer.closeZendeskTickets(
							user.getUserId(), accountEntry);
					}
				}
			}

			_zendeskUserWebService.deleteZendeskUser(zendeskUserId);

		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(User user) throws ModelListenerException {
		try {
			_oldUser.set(_userLocalService.getUser(user.getUserId()));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserModelListener.class);

	private static final ThreadLocal<User> _oldUser =
		new CentralizedThreadLocal<>(UserModelListener.class + "._oldUser");

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;*/

}