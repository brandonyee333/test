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

package com.liferay.osb.testray.internal.upgrade.v1_8_0;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.List;

/**
 * @author Ethan Bustad
 */
public class UpgradeResourceAction extends UpgradeProcess {

	public UpgradeResourceAction(
		CompanyLocalService companyLocalService,
		ResourceActionLocalService resourceActionLocalService,
		ResourceActions resourceActions,
		ResourcePermissionLocalService resourcePermissionLocalService,
		RoleLocalService roleLocalService) {

		_companyLocalService = companyLocalService;
		_resourceActionLocalService = resourceActionLocalService;
		_resourceActions = resourceActions;
		_resourcePermissionLocalService = resourcePermissionLocalService;
		_roleLocalService = roleLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			for (String roleName : _RESETTABLE_ROLE_NAMES) {
				Role role = _roleLocalService.fetchRole(
					company.getCompanyId(), roleName);

				List<ResourcePermission> resourcePermissions =
					_resourcePermissionLocalService.getRoleResourcePermissions(
						role.getRoleId());

				for (ResourcePermission resourcePermission :
						resourcePermissions) {

					_resourcePermissionLocalService.deleteResourcePermission(
						resourcePermission);
				}
			}
		}

		List<String> portletActions =
			_resourceActions.getPortletResourceActions(_TESTRAY_PORTLET_KEY);

		for (String actionId : portletActions) {
			ResourceAction resourceAction =
				_resourceActionLocalService.getResourceAction(
					_TESTRAY_PORTLET_KEY, actionId);

			_resourceActionLocalService.deleteResourceAction(resourceAction);
		}

		List<String> modelNames = _resourceActions.getPortletModelResources(
			_TESTRAY_PORTLET_KEY);

		for (String modelName : modelNames) {
			List<String> modelActions =
				_resourceActions.getModelResourceActions(modelName);

			for (String actionId : modelActions) {
				ResourceAction resourceAction =
					_resourceActionLocalService.getResourceAction(
						modelName, actionId);

				_resourceActionLocalService.deleteResourceAction(
					resourceAction);
			}
		}
	}

	private static final String[] _RESETTABLE_ROLE_NAMES = {
		"Test Designer", "Test Lead", "Tester", "Testray Administrator",
		"Testray User"
	};

	private static final String _TESTRAY_PORTLET_KEY =
		"com_liferay_osb_testray_portlet_TestrayPortlet";

	private final CompanyLocalService _companyLocalService;
	private final ResourceActionLocalService _resourceActionLocalService;
	private final ResourceActions _resourceActions;
	private final ResourcePermissionLocalService
		_resourcePermissionLocalService;
	private final RoleLocalService _roleLocalService;

}