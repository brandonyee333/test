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

package com.liferay.watson.servlet;

import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ResourceActionLocalServiceUtil;
import com.liferay.portal.kernel.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.watson.constants.WatsonPortletKeys;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Brent Krone-Schmidt
 */
public class WatsonServletContextListener
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

	protected void addRole(Element roleElement) throws Exception {
		long companyId = PortalUtil.getDefaultCompanyId();

		String roleName = roleElement.elementText("name");

		String roleTypeLabel = roleElement.elementText("role-type");

		int roleType = getRoleLabelType(roleTypeLabel);

		Role role = RoleLocalServiceUtil.fetchRole(companyId, roleName);

		if (role == null) {
			role = RoleLocalServiceUtil.addRole(
				UserLocalServiceUtil.getDefaultUserId(companyId), null, 0,
				roleName, null, null, roleType, null, null);
		}
		else if (role.getType() != roleType) {
			RoleLocalServiceUtil.deleteRole(role);

			role = RoleLocalServiceUtil.addRole(
				UserLocalServiceUtil.getDefaultUserId(companyId), null, 0,
				roleName, null, null, roleType, null, null);
		}

		boolean resetPermissions = GetterUtil.getBoolean(
			roleElement.elementText("reset-permissions"), true);

		if (resetPermissions) {
			List<ResourcePermission> resourcePermissions =
				ResourcePermissionLocalServiceUtil.getRoleResourcePermissions(
					role.getRoleId());

			for (ResourcePermission resourcePermission : resourcePermissions) {
				ResourcePermissionLocalServiceUtil.deleteResourcePermission(
					resourcePermission);
			}
		}

		int scope = ResourceConstants.SCOPE_COMPANY;

		String primKey = String.valueOf(companyId);

		if (roleType != RoleConstants.TYPE_REGULAR) {
			scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;

			primKey = String.valueOf(0);
		}

		List<Element> defaultActionKeysElements = roleElement.elements(
			"default-action-keys");

		for (Element defaultActionKeysElement : defaultActionKeysElements) {
			String name = defaultActionKeysElement.attributeValue(
				"resourceName");

			List<Element> actionKeyElements = defaultActionKeysElement.elements(
				"action-key");

			if (resetPermissions) {
				List<String> actionIds = new ArrayList<>();

				for (Element actionKeyElement : actionKeyElements) {
					actionIds.add(actionKeyElement.getText());
				}

				ResourcePermissionLocalServiceUtil.setResourcePermissions(
					companyId, name, scope, primKey, role.getRoleId(),
					actionIds.toArray(new String[actionIds.size()]));
			}
			else {
				for (Element actionKeyElement : actionKeyElements) {
					ResourcePermissionLocalServiceUtil.addResourcePermission(
						companyId, name, scope, primKey, role.getRoleId(),
						actionKeyElement.getText());
				}
			}
		}
	}

	@Override
	protected void doPortalDestroy() throws Exception {
	}

	@Override
	protected void doPortalInit() throws Exception {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		importResourceActions(classLoader);

		InputStream inputStream = classLoader.getResourceAsStream(
			"/com/liferay/watson/dependencies/roles.xml");

		String xml = new String(FileUtil.getBytes(inputStream));

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> roleElements = rootElement.elements("role");

		for (Element roleElement : roleElements) {
			addRole(roleElement);
		}
	}

	protected int getRoleLabelType(String roleLabel) {
		if (Objects.equals(roleLabel, RoleConstants.TYPE_ORGANIZATION_LABEL)) {
			return RoleConstants.TYPE_ORGANIZATION;
		}
		else if (Objects.equals(roleLabel, RoleConstants.TYPE_SITE_LABEL)) {
			return RoleConstants.TYPE_SITE;
		}
		else {
			return RoleConstants.TYPE_REGULAR;
		}
	}

	protected void importResourceActions(ClassLoader classLoader)
		throws Exception {

		String servletContextName = _servletContext.getServletContextName();

		ResourceActionsUtil.read(
			servletContextName, classLoader, "resource-actions/default.xml");

		for (String portletId : _PORTLET_IDS) {
			List<String> portletActions =
				ResourceActionsUtil.getPortletResourceActions(portletId);

			ResourceActionLocalServiceUtil.checkResourceActions(
				portletId, portletActions);

			List<String> modelNames =
				ResourceActionsUtil.getPortletModelResources(portletId);

			for (String modelName : modelNames) {
				List<String> modelActions =
					ResourceActionsUtil.getModelResourceActions(modelName);

				ResourceActionLocalServiceUtil.checkResourceActions(
					modelName, modelActions);
			}
		}
	}

	private static final String[] _PORTLET_IDS = {WatsonPortletKeys.WATSON};

	private ServletContext _servletContext;

}