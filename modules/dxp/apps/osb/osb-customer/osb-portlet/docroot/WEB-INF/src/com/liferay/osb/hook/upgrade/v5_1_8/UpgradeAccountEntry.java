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
import com.liferay.osb.model.AccountEntryLanguage;
import com.liferay.osb.model.AccountEnvironment;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.AccountEnvironmentLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.LicenseKeyLocalServiceUtil;
import com.liferay.osb.service.LicenseKeySetLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Jenny Chen
 */
public class UpgradeAccountEntry extends BaseUpgradeProcess {

	protected void deleteAccountEntryLanguages() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId from OSB_AccountEntryLanguage where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");

				List<AccountEntryLanguage> accountEntryLanguages =
					AccountEntryLanguageLocalServiceUtil.
						getAccountEntryLanguages(accountEntryId);

				for (AccountEntryLanguage accountEntryLanguage :
						accountEntryLanguages) {

					AccountEntryLanguageLocalServiceUtil.
						deleteAccountEntryLanguage(accountEntryLanguage);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteAccountEntryLicenseKeys() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId from OSB_LicenseKey where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");

				List<LicenseKey> licenseKeys =
					LicenseKeyLocalServiceUtil.getAccountEntryLicenseKeys(
						accountEntryId);

				for (LicenseKey licenseKey : licenseKeys) {
					LicenseKeyLocalServiceUtil.deleteLicenseKey(licenseKey);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteAccountEnvironments() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId from OSB_AccountEnvironment where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");

				List<AccountEnvironment> accountEnvironments =
					AccountEnvironmentLocalServiceUtil.getAccountEnvironments(
						accountEntryId);

				for (AccountEnvironment accountEnvironment :
						accountEnvironments) {

					AccountEnvironmentLocalServiceUtil.deleteAccountEnvironment(
						accountEnvironment.getAccountEnvironmentId());
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteAddresses(long classNameId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select classPK from Address where classNameId = ? and " +
					"classPK not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			ps.setLong(1, classNameId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("classPK");

				AddressLocalServiceUtil.deleteAddresses(
					OSBConstants.COMPANY_ID, AccountEntry.class.getName(),
					accountEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteExternalIdMappers(long classNameId) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select classPK from OSB_ExternalIdMapper where classNameId " +
					"= ? and classPK not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			ps.setLong(1, classNameId);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("classPK");

				ExternalIdMapperLocalServiceUtil.deleteExternalIdMappers(
					classNameId, accountEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteLicenseKeySetLicenseKeys() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select licenseKeySetId from OSB_LicenseKey where " +
					"licenseKeySetId not in (select licenseKeySetId from " +
						"OSB_LicenseKeySet)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long licenseKeySetId = rs.getLong("licenseKeySetId");

				List<LicenseKey> licenseKeys =
					LicenseKeyLocalServiceUtil.getLicenseKeySetLicenseKeys(
						licenseKeySetId);

				for (LicenseKey licenseKey : licenseKeys) {
					LicenseKeyLocalServiceUtil.deleteLicenseKey(licenseKey);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteLicenseKeySets() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId from OSB_LicenseKeySet where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");

				List<LicenseKeySet> licenseKeySets =
					LicenseKeySetLocalServiceUtil.getAccountEntryLicenseKeySets(
						accountEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				for (LicenseKeySet licenseKeySet : licenseKeySets) {
					LicenseKeySetLocalServiceUtil.deleteLicenseKeySet(
						licenseKeySet);
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteOfferingEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId from OSB_OfferingEntry where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");

				List<OfferingEntry> offeringEntries =
					OfferingEntryLocalServiceUtil.
						getAccountEntryOfferingEntries(accountEntryId);

				for (OfferingEntry offeringEntry : offeringEntries) {
					runSQL(
						"delete from OSB_OfferingEntry where offeringEntryId " +
							"= " + offeringEntry.getOfferingEntryId());
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void deleteOrderEntries() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId from OSB_OrderEntry where " +
					"accountEntryId not in (select accountEntryId from " +
						"OSB_AccountEntry)";

			ps = connection.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");

				List<OrderEntry> orderEntries =
					OrderEntryLocalServiceUtil.getAccountEntryOrderEntries(
						accountEntryId);

				for (OrderEntry orderEntry : orderEntries) {
					OrderEntryLocalServiceUtil.deleteOrderEntry(orderEntry);
				}
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

		deleteAccountEntryLanguages();
		deleteAccountEnvironments();
		deleteAddresses(classNameId);
		deleteExternalIdMappers(classNameId);
		deleteAccountEntryLicenseKeys();
		deleteLicenseKeySetLicenseKeys();
		deleteLicenseKeySets();
		deleteOfferingEntries();
		deleteOrderEntries();
	}

}