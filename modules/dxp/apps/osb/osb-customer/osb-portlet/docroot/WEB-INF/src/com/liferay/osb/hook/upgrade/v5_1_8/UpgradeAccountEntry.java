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

package com.liferay.osb.hook.upgrade.v5_1_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Jenny Chen
 */
public class UpgradeAccountEntry extends BaseUpgradeProcess {

	protected void cleanUpAccountEntryLanguages() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryLanguageId from OSB_AccountEntryLanguage " +
					"where accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryLanguageId = rs.getLong(
					"accountEntryLanguageId");

				AccountEntryLanguageLocalServiceUtil.deleteAccountEntryLanguage(
					accountEntryLanguageId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void cleanUpAccountEnvironments() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEnvironmentId from OSB_AccountEnvironment " +
					"where accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEnvironmentId = rs.getLong("accountEnvironmentId");

				AccountEnvironmentLocalServiceUtil.deleteAccountEnvironment(
					accountEnvironmentId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void cleanUpAddresses(long classNameId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select addressId from Address where classNameId = ? and " +
					"classPK not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			ps.setLong(1, classNameId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long addressId = rs.getLong("addressId");

				AddressLocalServiceUtil.deleteAddress(addressId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void cleanUpExternalIdMappers(long classNameId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select externalIdMapperId from OSB_ExternalIdMapper where " +
					"classNameId = ? and classPK not in (select " +
						"accountEntryId from OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			ps.setLong(1, classNameId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long externalIdMapperId = rs.getLong("externalIdMapperId");

				ExternalIdMapperLocalServiceUtil.deleteExternalIdMapper(
					externalIdMapperId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void cleanUpLicenseKeys() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select licenseKeyId OSB_LicenseKey where accountEntryId not " +
					"in (select accountEntryId from OSB_AccountEntry) or " +
						"licenseKeySetId not in (select licenseKeySetId from " +
							"OSB_LicenseKeySet)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long licenseKeyId = rs.getLong("licenseKeyId");

				LicenseKeyLocalServiceUtil.deleteLicenseKey(licenseKeyId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void cleanUpLicenseKeySets() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select licenseKeySetId from OSB_LicenseKeySet where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long licenseKeySetId = rs.getLong("licenseKeySetId");

				LicenseKeySetLocalServiceUtil.deleteLicenseKeySet(
					licenseKeySetId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void cleanUpOfferingEntries() throws Exception {
		runSQL(
			"delete from OSB_OfferingEntry where accountEntryId not in " +
				"(select accountEntryId from OSB_AccountEntry)");
	}

	protected void cleanUpOrderEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select orderEntryId from OSB_OrderEntry where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long orderEntryId = rs.getLong("orderEntryId");

				OrderEntryLocalServiceUtil.deleteOrderEntry(orderEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			AccountEntry.class.getName());

		cleanUpAccountEntryLanguages();
		cleanUpAccountEnvironments();
		cleanUpAddresses(classNameId);
		cleanUpExternalIdMappers(classNameId);
		cleanUpLicenseKeySets();
		cleanUpOfferingEntries();
		cleanUpOrderEntries();

		cleanUpLicenseKeys();
	}

}