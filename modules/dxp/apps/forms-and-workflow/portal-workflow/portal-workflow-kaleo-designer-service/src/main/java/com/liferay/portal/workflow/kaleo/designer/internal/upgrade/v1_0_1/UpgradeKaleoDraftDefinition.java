/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.designer.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ResourceLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Leonardo Barros
 */
public class UpgradeKaleoDraftDefinition extends UpgradeProcess {

	public UpgradeKaleoDraftDefinition(
		CompanyLocalService companyLocalService,
		ResourceLocalService resourceLocalService) {

		_companyLocalService = companyLocalService;
		_resourceLocalService = resourceLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		ServiceContext serviceContext = new ServiceContext();

		for (Company company : _companyLocalService.getCompanies()) {
			long groupId = company.getGroupId();

			try (PreparedStatement ps = connection.prepareStatement(
					"select kaleoDraftDefinitionId, userId from " +
						"KaleoDraftDefinition where companyId = ? and " +
							"groupId = ?")) {

				ps.setLong(1, company.getCompanyId());
				ps.setLong(2, 0);

				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						long kaleoDraftDefinitionId = rs.getLong(
							"kaleoDraftDefinitionId");
						long userId = rs.getLong("userId");

						updateKaleoDraftDefinition(
							company.getCompanyId(), groupId, userId,
							kaleoDraftDefinitionId, serviceContext);
					}
				}
			}
		}
	}

	protected void updateKaleoDraftDefinition(
			long companyId, long groupId, long userId,
			long kaleoDraftDefinitionId, ServiceContext serviceContext)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				"update KaleoDraftDefinition set groupId = ? where " +
					"kaleoDraftDefinitionId = ?")) {

			ps.setLong(1, groupId);
			ps.setLong(2, kaleoDraftDefinitionId);

			ps.executeUpdate();

			_resourceLocalService.addModelResources(
				companyId, groupId, userId,
				KaleoDraftDefinition.class.getName(), kaleoDraftDefinitionId,
				serviceContext.getModelPermissions());
		}
	}

	private final CompanyLocalService _companyLocalService;
	private final ResourceLocalService _resourceLocalService;

}