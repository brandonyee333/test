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

package com.liferay.osb.customer.qa.infrastructure.internal.messaging;

import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.qa.infrastructure.internal.configuration.OSBCustomerQAConfigurationKeys;
import com.liferay.osb.customer.qa.infrastructure.internal.configuration.OSBCustomerQAConfigurationUtil;
import com.liferay.osb.customer.qa.infrastructure.internal.configuration.OSBCustomerQAConfigurationValues;
import com.liferay.portal.kernel.configuration.Filter;
import com.liferay.portal.kernel.exception.UserScreenNameException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserGroupRoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jeremy Fu
 */
@Component(
	immediate = true,
	property = "destination.name=liferay/osb_qa_infrastructure",
	service = MessageListener.class
)
public class OSBCustomerQAStartupMessageListener extends BaseMessageListener {

	protected User checkUser(
			String uuid, long companyId, long[] organizationIds, long[] roleIds,
			long siteId, long userGroupId, String emailAddress,
			String screenName, String firstName, String lastName,
			String password)
		throws Exception {

		User user = _userLocalService.fetchUserByEmailAddress(
			companyId, emailAddress);

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setUuid(uuid);

		if (user == null) {
			try {
				user = _userLocalService.addUser(
					0, companyId, false, password, password, false, screenName,
					emailAddress, 0, StringPool.BLANK, LocaleUtil.getDefault(),
					firstName, StringPool.BLANK, lastName, 0, 0, true, 0, 1,
					1970, StringPool.BLANK, null, null, null, null, false,
					serviceContext);
			}
			catch (UserScreenNameException.MustNotBeDuplicate usnemnbd) {
				String key = PwdGenerator.getPassword(PwdGenerator.KEY3, 4);

				screenName = screenName + StringPool.PERIOD + key;

				user = _userLocalService.addUser(
					0, companyId, false, password, password, false, screenName,
					emailAddress, 0, StringPool.BLANK, LocaleUtil.getDefault(),
					firstName, StringPool.BLANK, lastName, 0, 0, true, 0, 1,
					1970, StringPool.BLANK, null, null, null, null, false,
					serviceContext);
			}
		}

		for (long organizationId : organizationIds) {
			_userLocalService.addOrganizationUsers(
				organizationId, new long[] {user.getUserId()});
		}

		for (long roleId : roleIds) {
			Role role = _roleLocalService.getRole(roleId);

			if ((role.getType() == RoleConstants.TYPE_SITE) && (siteId > 0)) {
				_userLocalService.addGroupUsers(
					siteId, new long[] {user.getUserId()});

				_userGroupRoleLocalService.addUserGroupRoles(
					user.getUserId(), siteId, new long[] {roleId});
			}
			else {
				_userLocalService.addRoleUsers(
					roleId, new long[] {user.getUserId()});
			}
		}

		_userLocalService.addUserGroupUsers(
			userGroupId, new long[] {user.getUserId()});

		user = _userLocalService.updateAgreedToTermsOfUse(
			user.getUserId(), true);

		user = _userLocalService.updateEmailAddressVerified(
			user.getUserId(), true);

		user = _userLocalService.updatePasswordReset(user.getUserId(), false);

		_userLocalService.authenticateForBasic(
			companyId, CompanyConstants.AUTH_TYPE_EA, user.getEmailAddress(),
			password);

		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(User.class);

		indexer.reindex(user);

		if (_log.isInfoEnabled()) {
			_log.info("Checked user: " + user.getEmailAddress());
		}

		return user;
	}

	protected void checkUsers(long companyId) throws Exception {
		String[] emailAddresses = StringUtil.split(
			OSBCustomerQAConfigurationValues.OSB_QA_USER_EMAIL_ADDRESSES,
			StringPool.COMMA);

		for (String emailAddress : emailAddresses) {
			String firstName = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "first-name"));
			String lastName = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "last-name"));
			String password = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "password"));
			String[] organizationIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "organization-ids"));
			String[] roleIds = OSBCustomerQAConfigurationUtil.getArray(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "role-ids"));
			String screenName = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "screen-name"));
			String siteId = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "site-id"));
			String userGroupId = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "user-group"));
			String uuid = OSBCustomerQAConfigurationUtil.get(
				OSBCustomerQAConfigurationKeys.OSB_QA_USER,
				new Filter(emailAddress, "uuid"));

			checkUser(
				uuid, companyId, GetterUtil.getLongValues(organizationIds),
				GetterUtil.getLongValues(roleIds), GetterUtil.getLong(siteId),
				GetterUtil.getLong(userGroupId), emailAddress, screenName,
				firstName, lastName, password);
		}
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		checkUsers(OSBCustomerConstants.COMPANY_ID);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setRoleLocalService(RoleLocalService roleLocalService) {
		_roleLocalService = roleLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserGroupRoleLocalService(
		UserGroupRoleLocalService userGroupRoleLocalService) {

		_userGroupRoleLocalService = userGroupRoleLocalService;
	}

	@Reference(unbind = "-")
	protected void setUserLocalService(UserLocalService userLocalService) {
		_userLocalService = userLocalService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		OSBCustomerQAStartupMessageListener.class);

	private RoleLocalService _roleLocalService;
	private UserGroupRoleLocalService _userGroupRoleLocalService;
	private UserLocalService _userLocalService;

}