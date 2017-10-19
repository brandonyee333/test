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

package com.liferay.watson.web.internal.setup;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.watson.web.constants.RoleConstants;
import com.liferay.watson.web.constants.WatsonPortletKeys;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Steven Smith
 */
@Component(immediate = true, service = SetupWatsonRoles.class)
public class SetupWatsonRoles {

	@Activate
	public void activate(BundleContext bundleContext) throws Exception {
		_bundle = bundleContext.getBundle();

		initResourceActions();
		initRoles();
	}

	public void initResourceActions() throws Exception {
		importResourceActions();
	}

	public void initRoles() throws Exception {
		URL url = _bundle.getResource(
			"com/liferay/watson/web/internal/setup/roles.xml");

		String xml = new String(FileUtil.getBytes(url.openStream()));

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		List<Element> roleElements = rootElement.elements("role");

		for (Element roleElement : roleElements) {
			addRole(roleElement);
		}
	}

	protected void addRole(Element roleElement) throws Exception {
		String roleName = roleElement.elementText("name");

		String roleTypeLabel = roleElement.elementText("role-type");

		int roleType = getRoleLabelType(roleTypeLabel);

		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			Role role = _roleLocalService.fetchRole(
				company.getCompanyId(), roleName);

			if (role == null) {
				role = _roleLocalService.addRole(
					_userLocalService.getDefaultUserId(company.getCompanyId()),
					null, 0, roleName, null, null, roleType, null, null);
			}
			else if (role.getType() != roleType) {
				_roleLocalService.deleteRole(role);

				role = _roleLocalService.addRole(
					_userLocalService.getDefaultUserId(company.getCompanyId()),
					null, 0, roleName, null, null, roleType, null, null);
			}

			boolean resetPermissions = GetterUtil.getBoolean(
				roleElement.elementText("reset-permissions"), true);

			if (resetPermissions) {
				List<ResourcePermission> resourcePermissions =
					_resourcePermissionLocalService.getRoleResourcePermissions(
						role.getRoleId());

				for (ResourcePermission resourcePermission :
						resourcePermissions) {

					_resourcePermissionLocalService.deleteResourcePermission(
						resourcePermission);
				}
			}

			int scope = ResourceConstants.SCOPE_COMPANY;

			String primKey = String.valueOf(company.getCompanyId());

			if (roleType != RoleConstants.TYPE_REGULAR) {
				scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;

				primKey = String.valueOf(0);
			}

			List<Element> defaultActionKeysElements = roleElement.elements(
				"default-action-keys");

			for (Element defaultActionKeysElement : defaultActionKeysElements) {
				List<Element> actionKeyElements =
					defaultActionKeysElement.elements("action-key");

				String name = defaultActionKeysElement.attributeValue(
					"resourceName");

				if (resetPermissions) {
					List<String> actionIds = new ArrayList<>();

					for (Element actionKeyElement : actionKeyElements) {
						actionIds.add(actionKeyElement.getText());
					}

					_resourcePermissionLocalService.setResourcePermissions(
						company.getCompanyId(), name, scope, primKey,
						role.getRoleId(),
						actionIds.toArray(new String[actionIds.size()]));
				}
				else {
					for (Element actionKeyElement : actionKeyElements) {
						_resourcePermissionLocalService.addResourcePermission(
							company.getCompanyId(), name, scope, primKey,
							role.getRoleId(), actionKeyElement.getText());
					}
				}
			}
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

	protected void importResourceActions() throws Exception {
		URL url = _bundle.getResource("resource-actions/default.xml");

		_resourceActions.read(null, url.openStream());

		for (String portletId : _PORTLET_IDS) {
			List<String> portletActions =
				_resourceActions.getPortletResourceActions(portletId);

			_resourceActionLocalService.checkResourceActions(
				portletId, portletActions);

			List<String> modelNames = _resourceActions.getPortletModelResources(
				portletId);

			for (String modelName : modelNames) {
				List<String> modelActions =
					_resourceActions.getModelResourceActions(modelName);

				_resourceActionLocalService.checkResourceActions(
					modelName, modelActions);
			}
		}
	}

	private static final String[] _PORTLET_IDS = {WatsonPortletKeys.WATSON};

	private Bundle _bundle;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourceActions _resourceActions;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private UserLocalService _userLocalService;

}