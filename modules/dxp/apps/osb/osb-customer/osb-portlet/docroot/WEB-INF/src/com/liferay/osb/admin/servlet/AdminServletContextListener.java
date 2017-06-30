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

package com.liferay.osb.admin.servlet;

import com.liferay.compat.portal.kernel.util.LocaleUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.hook.auth.OSBAuthToken;
import com.liferay.osb.license.messaging.RegisterTrialLicenseMessageListener;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.CorpMembershipRequest;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TrainingEvent;
import com.liferay.osb.rabbitmq.RabbitMQConsumerRouter;
import com.liferay.osb.service.permission.OSBCommonPermission;
import com.liferay.osb.servlet.AdminServletContextListenerUpgradeHelper;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.NoSuchOrganizationException;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.messaging.SerialDestination;
import com.liferay.portal.kernel.search.SearchEngineUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ListType;
import com.liferay.portal.model.ListTypeConstants;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.OrganizationConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthToken;
import com.liferay.portal.security.auth.AuthTokenUtil;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.permission.CommonPermission;
import com.liferay.portal.service.permission.CommonPermissionUtil;
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;
import com.liferay.rabbitmq.service.ConsumerManagerLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brian Wing Shun Chan
 * @author Amos Fong
 */
public class AdminServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_servletContext = servletContextEvent.getServletContext();

		registerPortalLifecycle();
	}

	protected void addAdministratorRole(long roleId)
		throws PortalException, SystemException {

		Role role = RoleLocalServiceUtil.getRole(
			OSBConstants.COMPANY_ID, RoleConstants.ADMINISTRATOR);

		long[] userIds = UserLocalServiceUtil.getRoleUserIds(role.getRoleId());

		UserLocalServiceUtil.addRoleUsers(roleId, userIds);
	}

	protected void addListTypes() throws Exception {
		List<ListType> statusTypes = ListTypeServiceUtil.getListTypes(
			TicketEntryConstants.LIST_TYPE_STATUS);

		if (!statusTypes.isEmpty()) {
			return;
		}

		DB db = DBFactoryUtil.getDB();

		String template = StringUtil.read(
			_servletContext.getResourceAsStream("/WEB-INF/sql/osb.sql"));

		db.runSQLTemplateString(template, false, true);
	}

	@Override
	protected void doPortalDestroy() {
		_registerTrialLicenseDestination.unregister(
			_registerTrialLicenseMessageListener);

		MessageBusUtil.removeDestination(
			_registerTrialLicenseDestination.getName());

		// Auth token

		AuthTokenUtil authTokenUtil =
			(AuthTokenUtil)PortalBeanLocatorUtil.locate(
				AuthTokenUtil.class.getName());

		OSBAuthToken osbAuthToken = (OSBAuthToken)AuthTokenUtil.getAuthToken();

		authTokenUtil.setAuthToken(osbAuthToken.getAuthToken());

		// Common permission

		CommonPermissionUtil commonPermissionUtil =
			(CommonPermissionUtil)PortalBeanLocatorUtil.locate(
				CommonPermissionUtil.class.getName());

		OSBCommonPermission osbCommonPermission =
			(OSBCommonPermission)CommonPermissionUtil.getCommonPermission();

		commonPermissionUtil.setCommonPermission(
			osbCommonPermission.getCommonPermission());

		// RabbitMQ

		if (Validator.isNotNull(_rabbitMQConsumerKey)) {
			ConsumerManagerLocalServiceUtil.unregisterConsumer(
				_rabbitMQConsumerKey);
		}
	}

	@Override
	@SuppressWarnings("unused")
	protected void doPortalInit() throws Exception {

		// Messaging

		SerialDestination serialDestination = new SerialDestination();

		serialDestination.setName("liferay/osb_portlet_license");

		serialDestination.open();

		_registerTrialLicenseDestination = serialDestination;

		MessageBusUtil.addDestination(_registerTrialLicenseDestination);

		_registerTrialLicenseMessageListener =
			new RegisterTrialLicenseMessageListener();

		_registerTrialLicenseDestination.register(
			_registerTrialLicenseMessageListener);

		// Developer mode

		if (PortletPropsValues.DEVELOPER_MODE_ENABLED) {
			setupDeveloperMode();
		}

		// Asset

		AdminServletContextListenerAssetHelper.setup();

		// Audit action

		AdminServletContextListenerAuditActionHelper.setup();

		// E-commerce

		AdminServletContextListenerECommerceHelper.setup();

		// Expando

		AdminServletContextListenerExpandoHelper.setup();

		// Journal

		AdminServletContextListenerJournalHelper.setup();

		// Upgrade

		AdminServletContextListenerUpgradeHelper
			adminServletContextListenerUpgradeHelper =
				new AdminServletContextListenerUpgradeHelper();

		adminServletContextListenerUpgradeHelper.setup(
			_servletContext.getServletContextName());

		// Workflow

		AdminServletContextListenerWorkflowHelper.setup();

		// Auth token

		AuthTokenUtil authTokenUtil =
			(AuthTokenUtil)PortalBeanLocatorUtil.locate(
				AuthTokenUtil.class.getName());

		AuthToken originalAuthToken = AuthTokenUtil.getAuthToken();

		AuthToken osbAuthToken = new OSBAuthToken(originalAuthToken);

		authTokenUtil.setAuthToken(osbAuthToken);

		// Common permission

		CommonPermissionUtil commonPermissionUtil =
			(CommonPermissionUtil)PortalBeanLocatorUtil.locate(
				CommonPermissionUtil.class.getName());

		CommonPermission originalCommonPermission =
			CommonPermissionUtil.getCommonPermission();

		CommonPermission osbCommonPermission = new OSBCommonPermission(
			originalCommonPermission);

		commonPermissionUtil.setCommonPermission(osbCommonPermission);

		// RabbitMQ

		if (Validator.isNotNull(
				PortletPropsValues.RABBITMQ_MESSAGE_QUEUE_NAME)) {

			try {
				RabbitMQConsumer rabbitMQConsumer =
					new RabbitMQConsumerRouter();

				_rabbitMQConsumerKey =
					ConsumerManagerLocalServiceUtil.registerConsumer(
						PortletPropsValues.RABBITMQ_MESSAGE_QUEUE_NAME, 1,
						rabbitMQConsumer);
			}
			catch (Exception e) {
				_log.error("Unable to register consumer", e);
			}
		}

		// Search

		SearchEngineUtil searchEngineUtil =
			(SearchEngineUtil)PortalBeanLocatorUtil.locate(
				SearchEngineUtil.class.getName());

		List<String> excludedEntryClassNames = new ArrayList<String>(8);

		excludedEntryClassNames.add(AppEntry.class.getName());
		excludedEntryClassNames.add(CorpEntry.class.getName());
		excludedEntryClassNames.add(CorpMembershipRequest.class.getName());
		excludedEntryClassNames.add(Organization.class.getName());
		excludedEntryClassNames.add(TicketEntry.class.getName());
		excludedEntryClassNames.add(TrainingEvent.class.getName());
		excludedEntryClassNames.add(User.class.getName());

		searchEngineUtil.setExcludedEntryClassNames(excludedEntryClassNames);
	}

	protected void setupDeveloperMode() throws Exception {

		// Company

		try {
			Company company = CompanyLocalServiceUtil.getCompanyByWebId(
				"liferay.com");

			OSBConstants.COMPANY_ID = company.getCompanyId();
		}
		catch (Exception e) {
			OSBConstants.COMPANY_ID = PortalUtil.getDefaultCompanyId();
		}

		// Default user

		OSBConstants.USER_DEFAULT_USER_ID =
			UserLocalServiceUtil.getDefaultUserId(OSBConstants.COMPANY_ID);

		// Support PM user

		User user = null;

		try {
			user = UserLocalServiceUtil.getUserByEmailAddress(
				OSBConstants.COMPANY_ID, "support-pm@liferay.com");
		}
		catch (Exception e) {
			user = UserLocalServiceUtil.addUser(
				0, OSBConstants.COMPANY_ID, true, "test", "test", true,
				"support-pm", "support-pm@liferay.com", 0, StringPool.BLANK,
				LocaleUtil.getDefault(), "Liferay", StringPool.BLANK,
				"Support Project Management", 0, 0, true, 0, 1, 1970,
				StringPool.BLANK, null, null, null, null, false,
				new ServiceContext());
		}

		OSBConstants.USER_SUPPORT_PM_USER_ID = user.getUserId();

		// Customer portal

		Group group = null;

		try {
			group = GroupLocalServiceUtil.getGroup(
				OSBConstants.COMPANY_ID, "Customer Portal");
		}
		catch (Exception e) {
			group = GroupLocalServiceUtil.addGroup(
				OSBConstants.USER_SUPPORT_PM_USER_ID, null, 0,
				"Customer Portal", StringPool.BLANK,
				GroupConstants.TYPE_SITE_PRIVATE, "/customer", true, true,
				null);
		}

		OSBConstants.GROUP_CUSTOMER_ID = group.getGroupId();

		// Global group

		Group globalGroup = GroupLocalServiceUtil.getCompanyGroup(
			OSBConstants.COMPANY_ID);

		OSBConstants.GROUP_GLOBAL_ID = globalGroup.getGroupId();

		// Guest group

		Group guestGroup = GroupLocalServiceUtil.getGroup(
			OSBConstants.COMPANY_ID, GroupConstants.GUEST);

		OSBConstants.GROUP_GUEST_ID = guestGroup.getGroupId();

		// Corp organization

		Organization organization = null;

		try {
			organization = OrganizationLocalServiceUtil.getOrganization(
				OSBConstants.COMPANY_ID, "Corporation Parent");
		}
		catch (NoSuchOrganizationException nsoe) {
			organization = OrganizationLocalServiceUtil.addOrganization(
				OSBConstants.USER_SUPPORT_PM_USER_ID, 0L, "Corporation Parent",
				OrganizationConstants.TYPE_REGULAR_ORGANIZATION, true, 0, 0,
				OSBConstants.ORGANIZATION_FULL_MEMBER_STATUS_ID,
				"The parent organization of all corporations", false,
				new ServiceContext());
		}

		if (organization != null) {
			OSBConstants.ORGANIZATION_CORPORATION_PARENT_ID =
				organization.getOrganizationId();
		}

		// Corp group organization

		Organization corpGroupOrganization = null;

		try {
			corpGroupOrganization =
				OrganizationLocalServiceUtil.getOrganization(
					OSBConstants.COMPANY_ID, "Corporation Group Parent");
		}
		catch (NoSuchOrganizationException nsoe) {
			corpGroupOrganization =
				OrganizationLocalServiceUtil.addOrganization(
					OSBConstants.USER_SUPPORT_PM_USER_ID, 0L,
					"Corporation Group Parent",
					OrganizationConstants.TYPE_REGULAR_ORGANIZATION, true, 0, 0,
					OSBConstants.ORGANIZATION_FULL_MEMBER_STATUS_ID,
					"The parent organization of all corporation groups", false,
					new ServiceContext());
		}

		if (corpGroupOrganization != null) {
			OSBConstants.ORGANIZATION_CORPORATION_GROUP_PARENT_ID =
				corpGroupOrganization.getOrganizationId();
		}

		// Corp project organization

		Organization corpProjectOrganization = null;

		try {
			corpProjectOrganization =
				OrganizationLocalServiceUtil.getOrganization(
					OSBConstants.COMPANY_ID, "Corporation Project Parent");
		}
		catch (NoSuchOrganizationException nsoe) {
			corpProjectOrganization =
				OrganizationLocalServiceUtil.addOrganization(
					OSBConstants.USER_SUPPORT_PM_USER_ID, 0L,
					"Corporation Project Parent",
					OrganizationConstants.TYPE_REGULAR_ORGANIZATION, true, 0, 0,
					OSBConstants.ORGANIZATION_FULL_MEMBER_STATUS_ID,
					"The parent organization of all corporation projects",
					false, new ServiceContext());
		}

		if (corpProjectOrganization != null) {
			OSBConstants.ORGANIZATION_CORPORATION_PROJECT_PARENT_ID =
				corpProjectOrganization.getOrganizationId();
		}

		// Customer organization

		Organization customerOrganization = null;

		try {
			customerOrganization = OrganizationLocalServiceUtil.getOrganization(
				OSBConstants.COMPANY_ID, "Customer");
		}
		catch (Exception e) {
			customerOrganization = OrganizationLocalServiceUtil.addOrganization(
				OSBConstants.USER_DEFAULT_USER_ID,
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
				"Customer", OrganizationConstants.TYPE_REGULAR_ORGANIZATION,
				true, 0, 0, ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
				StringPool.BLANK, false, new ServiceContext());
		}

		OSBConstants.ORGANIZATION_CUSTOMER_ID =
			customerOrganization.getOrganizationId();

		// Liferay, Inc. organization

		Organization liferayIncOrganization = null;

		try {
			liferayIncOrganization =
				OrganizationLocalServiceUtil.getOrganization(
					OSBConstants.COMPANY_ID, "Liferay, Inc.");
		}
		catch (Exception e) {
			liferayIncOrganization =
				OrganizationLocalServiceUtil.addOrganization(
					OSBConstants.USER_DEFAULT_USER_ID,
					OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID,
					"Liferay, Inc.",
					OrganizationConstants.TYPE_REGULAR_ORGANIZATION, true, 5,
					19, ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
					StringPool.BLANK, false, new ServiceContext());
		}

		OSBConstants.ORGANIZATION_LIFERAY_INC_ID =
			liferayIncOrganization.getOrganizationId();

		// Partner organization

		Organization partnerOrganization = null;

		try {
			partnerOrganization = OrganizationLocalServiceUtil.getOrganization(
				OSBConstants.COMPANY_ID, "Partner");
		}
		catch (Exception e) {
			partnerOrganization = OrganizationLocalServiceUtil.addOrganization(
				OSBConstants.USER_DEFAULT_USER_ID,
				OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, "Partner",
				OrganizationConstants.TYPE_REGULAR_ORGANIZATION, true, 0, 19,
				ListTypeConstants.ORGANIZATION_STATUS_DEFAULT, StringPool.BLANK,
				false, new ServiceContext());
		}

		OSBConstants.ORGANIZATION_PARTNER_ID =
			partnerOrganization.getOrganizationId();

		// OSB Account Admin role

		Role role = null;

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Account Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0, "OSB Account Admin",
				null, null, RoleConstants.TYPE_REGULAR, null);
		}

		OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID);

		// OSB Administrator role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Administrator");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0, "OSB Administrator",
				null, null, RoleConstants.TYPE_REGULAR, null);
		}

		OSBConstants.ROLE_OSB_ADMINISTRATOR_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_ADMINISTRATOR_ID);

		// OSB Corp Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Corp Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0, "OSB Corp Admin",
				null, null, RoleConstants.TYPE_ORGANIZATION, null);
		}

		OSBConstants.ROLE_OSB_CORP_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_CORP_ADMIN_ID);

		// OSB Corp Buyer role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Corp Buyer");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0, "OSB Corp Buyer",
				null, null, RoleConstants.TYPE_ORGANIZATION, null);
		}

		OSBConstants.ROLE_OSB_CORP_BUYER_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_CORP_BUYER_ID);

		// OSB Corp Developer role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Corp Developer");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0,
				"OSB Corp Developer", null, null,
				RoleConstants.TYPE_ORGANIZATION, null);
		}

		OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID);

		// OSB Corp LCS User role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Corp LCS User");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0, "OSB Corp LCS User",
				null, null, RoleConstants.TYPE_ORGANIZATION, null);
		}

		OSBConstants.ROLE_OSB_CORP_LCS_USER_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_CORP_LCS_USER_ID);

		// OSB Corp Project Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Corp Project Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0,
				"OSB Corp Project Admin", null, null,
				RoleConstants.TYPE_REGULAR, null);
		}

		OSBConstants.ROLE_OSB_CORP_PROJECT_ADMIN_ID = role.getRoleId();

		// OSB Marketing Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Marketing Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0,
				"OSB Marketing Admin", null, null, RoleConstants.TYPE_REGULAR,
				null);
		}

		OSBConstants.ROLE_OSB_MARKETING_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_MARKETING_ADMIN_ID);

		// OSB Marketplace Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Marketplace Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0,
				"OSB Marketplace Admin", null, null, RoleConstants.TYPE_REGULAR,
				null);
		}

		OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID);

		// OSB Support Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Support Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0, "OSB Support Admin",
				null, null, RoleConstants.TYPE_REGULAR, null);
		}

		OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID);

		// OSB Training Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Training Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0,
				"OSB Training Admin", null, null, RoleConstants.TYPE_REGULAR,
				null);
		}

		OSBConstants.ROLE_OSB_TRAINING_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_TRAINING_ADMIN_ID);

		// OSB Training Trainer role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Training Trainer");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0,
				"OSB Training Trainer", null, null, RoleConstants.TYPE_REGULAR,
				null);
		}

		OSBConstants.ROLE_OSB_TRAINING_TRAINER_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_TRAINING_TRAINER_ID);

		// OSB Trial License Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Trial License Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0,
				"OSB Trial License Admin", null, null,
				RoleConstants.TYPE_REGULAR, null);
		}

		OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID);

		// List types

		addListTypes();
	}

	private static Log _log = LogFactoryUtil.getLog(
		AdminServletContextListener.class);

	private String _rabbitMQConsumerKey;
	private Destination _registerTrialLicenseDestination;
	private MessageListener _registerTrialLicenseMessageListener;
	private ServletContext _servletContext;

}