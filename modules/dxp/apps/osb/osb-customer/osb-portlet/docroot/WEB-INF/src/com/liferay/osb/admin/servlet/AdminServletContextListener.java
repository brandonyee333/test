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

import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.service.permission.OSBCommonPermission;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.OSBCustomerQAInfrastructureUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.security.auth.AuthTokenUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.permission.CommonPermission;
import com.liferay.portal.kernel.service.permission.CommonPermissionUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
/* TODO update rabbitMQ integration
import com.liferay.osb.rabbitmq.RabbitMQConsumerRouter;
import com.liferay.rabbitmq.consumer.RabbitMQConsumer;
import com.liferay.rabbitmq.service.ConsumerManagerLocalServiceUtil;
*/
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceRegistration;

import com.liferay.registry.RegistryUtil;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

	protected void addAdministratorRole(long roleId) throws PortalException {
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

		DB db = DBManagerUtil.getDB();

		String template = StringUtil.read(
			_servletContext.getResourceAsStream("/WEB-INF/sql/osb.sql"));

		db.runSQLTemplateString(template, false, true);
	}

	@Override
	protected void doPortalDestroy() {
		_moduleServiceLifecycleServiceRegistration.unregister();

		/* TODO deploy error,
		need to fix when we figure out how to set up trial licenses
			_registerTrialLicenseDestination.unregister(
			_registerTrialLicenseMessageListener);

		MessageBusUtil.removeDestination(
			_registerTrialLicenseDestination.getName());*/

		// Auth token

		AuthTokenUtil authTokenUtil =
			(AuthTokenUtil)PortalBeanLocatorUtil.locate(
				AuthTokenUtil.class.getName());

		/* TODO fix authToken implementation
		OSBAuthToken osbAuthToken = (OSBAuthToken)AuthTokenUtil.getAuthToken();

		authTokenUtil.setAuthToken(osbAuthToken.getAuthToken());
		*/

		// Common permission

		// TODO fix cast from CommonPermission to OSBCommonPermission

		/*CommonPermissionUtil commonPermissionUtil =
			(CommonPermissionUtil)PortalBeanLocatorUtil.locate(
				CommonPermissionUtil.class.getName());

		OSBCommonPermission osbCommonPermission =
			(OSBCommonPermission)CommonPermissionUtil.getCommonPermission();

		commonPermissionUtil.setCommonPermission(
			osbCommonPermission.getCommonPermission());*/

		// RabbitMQ

		/* TODO update rabbitMQ integration

		if (Validator.isNotNull(_rabbitMQConsumerKey)) {
			ConsumerManagerLocalServiceUtil.unregisterConsumer(
				_rabbitMQConsumerKey);
		}

		*/
	}

	@Override
	@SuppressWarnings("unused")
	protected void doPortalInit() throws Exception {

		// OSGi

		Registry registry = RegistryUtil.getRegistry();

		Map<String, Object> properties = new HashMap<>();

		properties.put("module.service.lifecycle", "osb.portlet.initialized");

		_moduleServiceLifecycleServiceRegistration = registry.registerService(
			ModuleServiceLifecycle.class, new ModuleServiceLifecycle() {},
			properties);

		// Messaging

		/* TODO deploy error,
		need to fix when we figure out how to set up trial licenses

		SerialDestination serialDestination = new SerialDestination();

		serialDestination.setName("liferay/osb_portlet_license");

		serialDestination.open();

		_registerTrialLicenseDestination = serialDestination;

		MessageBusUtil.addDestination(_registerTrialLicenseDestination);

		_registerTrialLicenseMessageListener =
			new RegisterTrialLicenseMessageListener();

		_registerTrialLicenseDestination.register(
			_registerTrialLicenseMessageListener);*/

		// Developer mode

		if (PortletPropsValues.DEVELOPER_MODE_ENABLED) {
			setupDeveloperMode();
		}

		// QA Infrastructure

		if (PortletPropsValues.QA_INFRASTRUCTURE_ENABLED) {
			OSBCustomerQAInfrastructureUtil.setupQAInfrastructure();
		}

		// TODO need database for OSBConstants.*_USER_ID calls

		// Audit action

		AdminServletContextListenerAuditActionHelper.setup();

		// Expando

		// TODO need database for Role values called
		// AdminServletContextListenerExpandoHelper.setup();

		// Upgrade

		AdminServletContextListenerUpgradeHelper.setup(
			_servletContext.getServletContextName());

		// Workflow

		//TODO need database for userId values
		//AdminServletContextListenerWorkflowHelper.setup();

		// Auth token

		AuthTokenUtil authTokenUtil =
			(AuthTokenUtil)PortalBeanLocatorUtil.locate(
				AuthTokenUtil.class.getName());

		/* TODO fix authToken implementation
		AuthToken originalAuthToken = AuthTokenUtil.getAuthToken();

		AuthToken osbAuthToken = new OSBAuthToken(originalAuthToken);

		authTokenUtil.setAuthToken(osbAuthToken);
		*/

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

		/* TODO update rabbitMQ integration

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

		*/

		Message message = new Message();

		message.setDestinationName("liferay/osb_qa_infrastructure");

		MessageBusUtil.sendMessage(message.getDestinationName(), message);
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

		// Customer

		Group customerGroup = null;

		try {
			customerGroup = GroupLocalServiceUtil.getGroup(
				OSBConstants.COMPANY_ID, "Customer");
		}
		catch (Exception e) {
			customerGroup = GroupLocalServiceUtil.addGroup(
				OSBConstants.USER_SUPPORT_PM_USER_ID, 0, null, 0, 0, "Customer",
				StringPool.BLANK, GroupConstants.TYPE_SITE_PRIVATE, false, 0,
				"/customer", true, true, null);
		}

		OSBConstants.GROUP_CUSTOMER_ID = customerGroup.getGroupId();

		// Global group

		Group globalGroup = GroupLocalServiceUtil.getCompanyGroup(
			OSBConstants.COMPANY_ID);

		OSBConstants.GROUP_GLOBAL_ID = globalGroup.getGroupId();

		// Guest group

		Group guestGroup = GroupLocalServiceUtil.getGroup(
			OSBConstants.COMPANY_ID, GroupConstants.GUEST);

		OSBConstants.GROUP_GUEST_ID = guestGroup.getGroupId();

		// License

		Group licenseGroup = null;

		try {
			licenseGroup = GroupLocalServiceUtil.getGroup(
				OSBConstants.COMPANY_ID, "License");
		}
		catch (Exception e) {
			licenseGroup = GroupLocalServiceUtil.addGroup(
				OSBConstants.USER_SUPPORT_PM_USER_ID, 0, null, 0, 0, "License",
				StringPool.BLANK, GroupConstants.TYPE_SITE_PRIVATE, false, 0,
				"/license", true, true, null);
		}

		OSBConstants.GROUP_LICENSE_ID = licenseGroup.getGroupId();

		// Support

		Group supportGroup = null;

		try {
			supportGroup = GroupLocalServiceUtil.getGroup(
				OSBConstants.COMPANY_ID, "Support");
		}
		catch (Exception e) {
			supportGroup = GroupLocalServiceUtil.addGroup(
				OSBConstants.USER_SUPPORT_PM_USER_ID, 0, null, 0, 0, "Support",
				StringPool.BLANK, GroupConstants.TYPE_SITE_PRIVATE, false, 0,
				"/support", true, true, null);
		}

		OSBConstants.GROUP_SUPPORT_ID = supportGroup.getGroupId();

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
				"Customer", OrganizationConstants.TYPE_ORGANIZATION, 0L, 0L,
				(long)ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
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
					"Liferay, Inc.", OrganizationConstants.TYPE_ORGANIZATION,
					5L, 19L,
					(long)ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
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
				OrganizationConstants.TYPE_ORGANIZATION, 0L, 19L,
				(long)ListTypeConstants.ORGANIZATION_STATUS_DEFAULT,
				StringPool.BLANK, false, new ServiceContext());
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
				null, null, RoleConstants.TYPE_REGULAR, null, null);
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
				null, null, RoleConstants.TYPE_REGULAR, null, null);
		}

		OSBConstants.ROLE_OSB_ADMINISTRATOR_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_ADMINISTRATOR_ID);

		// OSB Support Admin role

		try {
			role = RoleLocalServiceUtil.getRole(
				OSBConstants.COMPANY_ID, "OSB Support Admin");
		}
		catch (Exception e) {
			role = RoleLocalServiceUtil.addRole(
				OSBConstants.USER_DEFAULT_USER_ID, null, 0, "OSB Support Admin",
				null, null, RoleConstants.TYPE_REGULAR, null, null);
		}

		OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID = role.getRoleId();

		addAdministratorRole(OSBConstants.ROLE_OSB_SUPPORT_ADMIN_ID);

		// List types

		addListTypes();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AdminServletContextListener.class);

	private ServiceRegistration<ModuleServiceLifecycle>
		_moduleServiceLifecycleServiceRegistration;
	private String _rabbitMQConsumerKey;
	private Destination _registerTrialLicenseDestination;
	private MessageListener _registerTrialLicenseMessageListener;
	private ServletContext _servletContext;

}