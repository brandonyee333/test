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

package com.liferay.osb.model;

import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.RemoteUserLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;

/**
 * @author Amos Fong
 * @author Joan Kim
 */
public class UserListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (associationClassName.equals(Role.class.getName())) {
				long userId = GetterUtil.getLong(classPK);
				long roleId = GetterUtil.getLong(associationClassPK);

				if (roleId == OSBConstants.ROLE_VERIFIED_USER_ID) {
					assignOrganizations(userId);
				}
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemove(User user) {
		try {
			AccountCustomerLocalServiceUtil.deleteAccountCustomers(
				user.getUserId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		try {
			AccountWorkerLocalServiceUtil.deleteAccountWorkers(
				user.getUserId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		try {
			PartnerWorkerLocalServiceUtil.deletePartnerWorkers(
				user.getUserId());
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	protected void assignOrganizations(long userId) throws PortalException {
		List<AccountCustomer> accountCustomers =
			AccountCustomerLocalServiceUtil.getUserAccountCustomers(userId);

		if (!accountCustomers.isEmpty()) {
			boolean customerAccount = false;

			for (AccountCustomer accountCustomer : accountCustomers) {
				AccountEntry accountEntry = accountCustomer.getAccountEntry();

				if (accountEntry.getType() !=
						AccountEntryConstants.TYPE_TRIAL) {

					customerAccount = true;

					break;
				}
			}

			if (customerAccount &&
				!OrganizationLocalServiceUtil.hasUserOrganization(
					userId, OSBConstants.ORGANIZATION_CUSTOMER_ID)) {

				RemoteUserLocalServiceUtil.addOrganizationUsers(
					OSBConstants.ORGANIZATION_CUSTOMER_ID, new long[] {userId});
			}
		}

		List<PartnerWorker> partnerWorkers =
			PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(userId);

		if (!partnerWorkers.isEmpty() &&
			!OrganizationLocalServiceUtil.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID)) {

			RemoteUserLocalServiceUtil.addOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(UserListener.class);

}