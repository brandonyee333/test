/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
	public void onBeforeRemove(User user) {
		long userId = user.getUserId();

		try {
			AccountCustomerLocalServiceUtil.deleteAccountCustomers(userId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		try {
			AccountWorkerLocalServiceUtil.deleteAccountWorkers(userId);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		try {
			PartnerWorkerLocalServiceUtil.deletePartnerWorkers(userId);
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

		if (!partnerWorkers.isEmpty()) {
			if (!OrganizationLocalServiceUtil.hasUserOrganization(
					userId, OSBConstants.ORGANIZATION_PARTNER_ID)) {

				RemoteUserLocalServiceUtil.addOrganizationUsers(
					OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(UserListener.class);

}