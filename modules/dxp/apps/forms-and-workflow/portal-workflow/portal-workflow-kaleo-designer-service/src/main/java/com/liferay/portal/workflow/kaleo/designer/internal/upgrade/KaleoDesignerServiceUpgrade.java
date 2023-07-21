/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.internal.upgrade;

import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.workflow.kaleo.designer.internal.upgrade.v1_0_0.UpgradeResourceActions;
import com.liferay.portal.workflow.kaleo.designer.internal.upgrade.v1_0_1.UpgradeKaleoDraftDefinition;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class KaleoDesignerServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.portal.workflow.kaleo.designer.service", "0.0.1",
			"1.0.0",
			new UpgradeResourceActions(
				_resourceActionLocalService, _resourceActions));

		registry.register(
			"com.liferay.portal.workflow.kaleo.designer.service", "1.0.0",
			"1.0.1",
			new UpgradeKaleoDraftDefinition(
				_companyLocalService, _resourceLocalService));
	}

	@Reference(unbind = "-")
	public void setResourceActions(ResourceActions resourceActions) {
		_resourceActions = resourceActions;
	}

	@Reference(unbind = "-")
	protected void setCompanyLocalService(
		CompanyLocalService companyLocalService) {

		_companyLocalService = companyLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourceActionLocalService(
		ResourceActionLocalService resourceActionLocalService) {

		_resourceActionLocalService = resourceActionLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourceLocalService(
		ResourceLocalService resourceLocalService) {

		_resourceLocalService = resourceLocalService;
	}

	private CompanyLocalService _companyLocalService;
	private ResourceActionLocalService _resourceActionLocalService;
	private ResourceActions _resourceActions;
	private ResourceLocalService _resourceLocalService;

}