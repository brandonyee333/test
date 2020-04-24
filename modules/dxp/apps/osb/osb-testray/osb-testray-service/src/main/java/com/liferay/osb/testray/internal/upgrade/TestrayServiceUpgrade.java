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

package com.liferay.osb.testray.internal.upgrade;

import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ethan Bustad
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class TestrayServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.testray.service", "1.0.0", "1.1.0",
			new com.liferay.osb.testray.internal.upgrade.v1_1_0.
				UpgradeResourceAction(),
			new com.liferay.osb.testray.internal.upgrade.v1_1_0.
				UpgradeResourcePermission(),
			new com.liferay.osb.testray.internal.upgrade.v1_1_0.
				UpgradeTestrayArchive(_classNameLocalService),
			new com.liferay.osb.testray.internal.upgrade.v1_1_0.
				UpgradeTestrayAssignment(_classNameLocalService),
			new com.liferay.osb.testray.internal.upgrade.v1_1_0.
				UpgradeTestrayFactor(_classNameLocalService),
			new com.liferay.osb.testray.internal.upgrade.v1_1_0.
				UpgradeTestrayPortletId());

		registry.register(
			"com.liferay.osb.testray.service", "1.1.0", "1.2.0",
			new com.liferay.osb.testray.internal.upgrade.v1_2_0.
				UpgradeTestrayBuildMetric(),
			new com.liferay.osb.testray.internal.upgrade.v1_2_0.
				UpgradeTestrayCaseType());

		registry.register(
			"com.liferay.osb.testray.service", "1.2.0", "1.3.0",
			new com.liferay.osb.testray.internal.upgrade.v1_3_0.UpgradeRole());

		registry.register(
			"com.liferay.osb.testray.service", "1.3.0", "1.4.0",
			new com.liferay.osb.testray.internal.upgrade.v1_4_0.
				UpgradeTestrayBuild());

		registry.register(
			"com.liferay.osb.testray.service", "1.4.0", "1.5.0",
			new com.liferay.osb.testray.internal.upgrade.v1_5_0.
				UpgradeTestrayBuild(),
			new com.liferay.osb.testray.internal.upgrade.v1_5_0.
				UpgradeTestrayCaseResult(),
			new com.liferay.osb.testray.internal.upgrade.v1_5_0.
				UpgradeTestrayCaseType(),
			new com.liferay.osb.testray.internal.upgrade.v1_5_0.
				UpgradeTestrayComponent(),
			new com.liferay.osb.testray.internal.upgrade.v1_5_0.
				UpgradeTestrayProject(),
			new com.liferay.osb.testray.internal.upgrade.v1_5_0.
				UpgradeTestrayTeam());

		registry.register(
			"com.liferay.osb.testray.service", "1.5.0", "1.6.0",
			new com.liferay.osb.testray.internal.upgrade.v1_6_0.
				UpgradeTestrayFactor(),
			new com.liferay.osb.testray.internal.upgrade.v1_6_0.
				UpgradeTestrayFactorCategory(),
			new com.liferay.osb.testray.internal.upgrade.v1_6_0.
				UpgradeTestrayFactorOption(),
			new com.liferay.osb.testray.internal.upgrade.v1_6_0.
				UpgradeTestrayIssue(),
			new com.liferay.osb.testray.internal.upgrade.v1_6_0.
				UpgradeTestrayProductVersion(),
			new com.liferay.osb.testray.internal.upgrade.v1_6_0.
				UpgradeTestrayRun());

		registry.register(
			"com.liferay.osb.testray.service", "1.6.0", "1.7.0",
			new com.liferay.osb.testray.internal.upgrade.v1_7_0.
				UpgradeTestrayRequirement());

		registry.register(
			"com.liferay.osb.testray.service", "1.7.0", "1.8.0",
			new com.liferay.osb.testray.internal.upgrade.v1_8_0.
				UpgradeResourceAction(
					_companyLocalService, _resourceActionLocalService,
					_resourceActions, _resourcePermissionLocalService,
					_roleLocalService));

		registry.register(
			"com.liferay.osb.testray.service", "1.8.0", "1.10.0",
			new com.liferay.osb.testray.internal.upgrade.v1_10_0.
				UpgradeTestrayRequirement());

		registry.register(
			"com.liferay.osb.testray.service", "1.10.0", "1.11.0",
			new com.liferay.osb.testray.internal.upgrade.v1_11_0.
				UpgradeTestrayCase(),
			new com.liferay.osb.testray.internal.upgrade.v1_11_0.
				UpgradeTestrayComponent());

		registry.register(
			"com.liferay.osb.testray.service", "1.11.0", "1.12.0",
			new com.liferay.osb.testray.internal.upgrade.v1_12_0.
				UpgradeTestrayCase(),
			new com.liferay.osb.testray.internal.upgrade.v1_12_0.
				UpgradeTestrayRequirement());

		registry.register(
			"com.liferay.osb.testray.service", "1.12.0", "1.13.0",
			new com.liferay.osb.testray.internal.upgrade.v1_13_0.
				UpgradeTestrayBuild());

		registry.register(
			"com.liferay.osb.testray.service", "1.13.0", "1.14.0",
			new com.liferay.osb.testray.internal.upgrade.v1_14_0.
				UpgradeTestrayFactor(),
			new com.liferay.osb.testray.internal.upgrade.v1_14_0.
				UpgradeTestrayFactorOption());

		registry.register(
			"com.liferay.osb.testray.service", "1.14.0", "1.15.0",
			new com.liferay.osb.testray.internal.upgrade.v1_15_0.UpgradeRole());

		registry.register(
			"com.liferay.osb.testray.service", "1.15.0", "1.16.0",
			new com.liferay.osb.testray.internal.upgrade.v1_16_0.UpgradeRole());

		registry.register(
			"com.liferay.osb.testray.service", "1.16.0", "1.17.0",
			new com.liferay.osb.testray.internal.upgrade.v1_17_0.
				UpgradeTestrayRequirement());

		registry.register(
			"com.liferay.osb.testray.service", "1.17.0", "1.18.0",
			new com.liferay.osb.testray.internal.upgrade.v1_18_0.
				UpgradeTestrayBuild());

		registry.register(
			"com.liferay.osb.testray.service", "1.18.0", "1.19.0",
			new com.liferay.osb.testray.internal.upgrade.v1_19_0.
				UpgradeTestrayCaseResult());
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

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

}