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

package com.liferay.osb.customer.zendesk.model.listener;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.zendesk.model.listener.exception.AccountCustomerRemovalException;
import com.liferay.osb.customer.zendesk.model.listener.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountCustomerSynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountEntrySynchronizer;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (associationClassName.equals(Organization.class.getName())) {
				long userId = GetterUtil.getLong(classPK);
				long organizationId = GetterUtil.getLong(associationClassPK);

				if (organizationId ==
						OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID) {

					_userSynchronizer.addLiferayEmployee(userId);
				}
			}
		}
		catch (Exception e) {
			_log.error(e);

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

				if (organizationId ==
						OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID) {

					_userSynchronizer.removeObsoleteTags(userId);
				}
			}
		}
		catch (Exception e) {
			_log.error(e);

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

				_userSynchronizer.sync(user, null, null);
			}
		}
		catch (Exception e) {
			_log.error(e);

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

			List<AccountCustomer> accountCustomers =
				AccountCustomerLocalServiceUtil.getUserAccountCustomers(
					user.getUserId());

			for (AccountCustomer accountCustomer : accountCustomers) {
				try {
					_accountCustomerSynchronizer.reassignTickets(
						accountCustomer);
				}
				catch (AccountCustomerRemovalException acre) {
					_accountEntrySynchronizer.closeZendeskTickets(
						accountCustomer.getAccountEntry());
				}
			}

			_zendeskUserWebService.deleteZendeskUser(zendeskUserId);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(User user) throws ModelListenerException {
		try {
			_oldUser.set(_userLocalService.getUser(user.getUserId()));
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
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
	private ZendeskUserWebService _zendeskUserWebService;

}