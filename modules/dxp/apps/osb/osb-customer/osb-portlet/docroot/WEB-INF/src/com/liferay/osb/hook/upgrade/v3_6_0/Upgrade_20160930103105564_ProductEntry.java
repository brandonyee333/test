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

package com.liferay.osb.hook.upgrade.v3_6_0;

import com.liferay.osb.exception.NoSuchProductEntryException;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.LicenseEntryConstants;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.ProductEntry;
import com.liferay.osb.model.ProductEntryConstants;
import com.liferay.osb.service.LicenseEntryLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.osb.service.ProductEntryLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Amos Fong
 */
public class Upgrade_20160930103105564_ProductEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160930103105564L;
	}

	protected void addLicenseEntries() throws PortalException {
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deBackupProductEntryId,
			"Digital Enterprise Backup", LicenseEntryConstants.TYPE_BACKUP,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deDevelopmentProductEntryId,
			"Digital Enterprise Development",
			LicenseEntryConstants.TYPE_DEVELOPER,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deDevelopmentProductEntryId,
			"Digital Enterprise Development (Cluster)",
			LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deEnterpriseProductEntryId,
			"Digital Enterprise Unlimited Enterprise-Wide",
			LicenseEntryConstants.TYPE_ENTERPRISE,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deLimitedProductEntryId,
			"Digital Enterprise Limited", LicenseEntryConstants.TYPE_LIMITED,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deNonProductionProductEntryId,
			"Digital Enterprise Non-Production",
			LicenseEntryConstants.TYPE_NON_PRODUCTION,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deOEMProductEntryId,
			"Digital Enterprise OEM", LicenseEntryConstants.TYPE_OEM,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
		LicenseEntryLocalServiceUtil.addLicenseEntry(
			OSBConstants.USER_AMOS_FONG_USER_ID, _deProductionProductEntryId,
			"Digital Enterprise Production",
			LicenseEntryConstants.TYPE_PRODUCTION,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10, 0);
	}

	protected void addProductEntries() throws PortalException {
		_deBackupProductEntryId = addProductEntry(
			"Digital Enterprise Backup",
			ProductEntryConstants.ENVIRONMENT_BACKUP);
		_deDevelopmentProductEntryId = addProductEntry(
			"Digital Enterprise Development",
			ProductEntryConstants.ENVIRONMENT_DEVELOPMENT);
		_deEnterpriseProductEntryId = addProductEntry(
			"Digital Enterprise Unlimited Enterprise-Wide",
			ProductEntryConstants.ENVIRONMENT_ANY);
		_deLimitedProductEntryId = addProductEntry(
			"Digital Enterprise Limited",
			ProductEntryConstants.ENVIRONMENT_ANY);
		_deNonProductionProductEntryId = addProductEntry(
			"Digital Enterprise Non-Production",
			ProductEntryConstants.ENVIRONMENT_NON_PRODUCTION);
		_deOEMProductEntryId = addProductEntry(
			"Digital Enterprise OEM", ProductEntryConstants.ENVIRONMENT_ANY);
		_deProductionProductEntryId = addProductEntry(
			"Digital Enterprise Production",
			ProductEntryConstants.ENVIRONMENT_PRODUCTION);
	}

	protected long addProductEntry(String name, int environment)
		throws PortalException {

		ProductEntry productEntry = null;

		try {
			productEntry = ProductEntryLocalServiceUtil.getProductEntryByName(
				name);
		}
		catch (NoSuchProductEntryException nspee) {
			productEntry = ProductEntryLocalServiceUtil.addProductEntry(
				OSBConstants.USER_AMOS_FONG_USER_ID, name,
				ProductEntryConstants.TYPE_REGULAR, environment,
				"digitalEnterpriseMajorVersions", new String[0]);
		}

		return productEntry.getProductEntryId();
	}

	protected void convertPortalVersion7OfferingEntries() throws Exception {
		updateOfferingEntries(
			_BACKUP_PRODUCT_ENTRY_IDS, _deBackupProductEntryId, 22005,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		updateOfferingEntries(
			_DEVELOPMENT_PRODUCT_ENTRY_IDS, _deDevelopmentProductEntryId, 22005,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		updateOfferingEntries(
			_ENTERPRISE_PRODUCT_ENTRY_IDS, _deEnterpriseProductEntryId, 22005,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		updateOfferingEntries(
			_LIMITED_PRODUCT_ENTRY_IDS, _deLimitedProductEntryId, 22005,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		updateOfferingEntries(
			_NON_PRODUCTION_PRODUCT_ENTRY_IDS, _deNonProductionProductEntryId,
			22005, ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		updateOfferingEntries(
			_OEM_PRODUCT_ENTRY_IDS, _deOEMProductEntryId, 22005,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		updateOfferingEntries(
			_PRODUCTION_PRODUCT_ENTRY_IDS, _deProductionProductEntryId, 22005,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
	}

	@Override
	protected void doUpgrade() throws Exception {
		try {
			ProductEntryLocalServiceUtil.getProductEntryByName(
				"Digital Enterprise Production");

			return;
		}
		catch (NoSuchProductEntryException nspee) {
		}

		addProductEntries();

		addLicenseEntries();

		convertPortalVersion7OfferingEntries();
		splitPortalVersionAnyOfferingEntries();
		updateListTypes();
	}

	protected void splitPortalVersionAnyOfferingEntries() throws Exception {
		runSQL(
			"update OSB_OfferingEntry set version = 22004 where " +
				"productEntryId = 67719137 and version = 22998");
		runSQL(
			"update OSB_OfferingEntry set version = 22004 where " +
				"productEntryId = 56880174 and version = 22998");

		StringBundler sb = new StringBundler(5);

		sb.append("update OSB_OfferingEntry set version = ");
		sb.append(ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		sb.append(", productEntryId = ");
		sb.append(_deDevelopmentProductEntryId);
		sb.append(" where type_ = 3");

		runSQL(sb.toString());

		splitPortalVersionAnyOfferingEntries(
			_BACKUP_PRODUCT_ENTRY_IDS, _deBackupProductEntryId, 21001,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		splitPortalVersionAnyOfferingEntries(
			_DEVELOPMENT_PRODUCT_ENTRY_IDS, _deDevelopmentProductEntryId, 21001,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		splitPortalVersionAnyOfferingEntries(
			_ENTERPRISE_PRODUCT_ENTRY_IDS, _deEnterpriseProductEntryId, 21001,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		splitPortalVersionAnyOfferingEntries(
			_LIMITED_PRODUCT_ENTRY_IDS, _deLimitedProductEntryId, 21001,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		splitPortalVersionAnyOfferingEntries(
			_NON_PRODUCTION_PRODUCT_ENTRY_IDS, _deNonProductionProductEntryId,
			21001, ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		splitPortalVersionAnyOfferingEntries(
			_OEM_PRODUCT_ENTRY_IDS, _deOEMProductEntryId, 21001,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
		splitPortalVersionAnyOfferingEntries(
			_PRODUCTION_PRODUCT_ENTRY_IDS, _deProductionProductEntryId, 21001,
			ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7);
	}

	protected void splitPortalVersionAnyOfferingEntries(
			long[] oldProductEntryIds, long newProductEntryId, int version62,
			int version7)
		throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(
			4 + (oldProductEntryIds.length * 2));

		sb.append("select offeringEntryId, accountEntryId, orderEntryId, ");
		sb.append("supportResponseId, productDescription, type_, licenses, ");
		sb.append("licenseLifetime, supportTickets, supportLifetime, sizing, ");
		sb.append("quantity, status from OSB_OfferingEntry where version = ? ");
		sb.append("and (");

		for (int i = 0; i < oldProductEntryIds.length; i++) {
			sb.append("productEntryId = ?");

			if ((i + 1) < oldProductEntryIds.length) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		try {
			ps = connection.prepareStatement(sb.toString());

			ps.setInt(1, 22998);

			for (int i = 0; i < oldProductEntryIds.length; i++) {
				ps.setLong(i + 2, oldProductEntryIds[i]);
			}

			rs = ps.executeQuery();

			while (rs.next()) {
				long offeringEntryId = rs.getLong("offeringEntryId");
				long accountEntryId = rs.getLong("accountEntryId");
				long orderEntryId = rs.getLong("orderEntryId");
				long supportResponseId = rs.getLong("supportResponseId");
				String productDescription = rs.getString("productDescription");
				int type = rs.getInt("type_");
				boolean licenses = rs.getBoolean("licenses");
				long licenseLifetime = rs.getLong("licenseLifetime");
				boolean supportTickets = rs.getBoolean("supportTickets");
				long supportLifetime = rs.getLong("supportLifetime");
				int sizing = rs.getInt("sizing");
				int quantity = rs.getInt("quantity");
				int status = rs.getInt("status");

				OfferingEntry offeringEntry62 =
					OfferingEntryLocalServiceUtil.getOfferingEntry(
						offeringEntryId);

				offeringEntry62.setVersion(version62);

				OfferingEntryLocalServiceUtil.updateOfferingEntry(
					offeringEntry62);

				OfferingEntry offeringEntry70 =
					OfferingEntryLocalServiceUtil.addOfferingEntry(
						OSBConstants.USER_AMOS_FONG_USER_ID, accountEntryId,
						orderEntryId, newProductEntryId, supportResponseId,
						productDescription, type, version7, licenses,
						licenseLifetime, 0, 0, supportTickets, supportLifetime,
						sizing, quantity, OfferingEntryConstants.STATUS_ACTIVE);

				offeringEntry70.setStatus(status);

				OfferingEntryLocalServiceUtil.updateOfferingEntry(
					offeringEntry70);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateListTypes() throws Exception {
		runSQL("delete from ListType where listTypeId = 20100");
		runSQL("delete from ListType where listTypeId = 21002");
		runSQL("delete from ListType where listTypeId = 22005");

		insertListType(
			41000, "7.0 GA1",
			ProductEntry.class.getName() + ".digitalEnterpriseAllVersions");
		insertListType(
			42000, "7",
			ProductEntry.class.getName() + ".digitalEnterpriseMajorVersions");
		insertListType(
			43000, "7.0",
			ProductEntry.class.getName() + ".digitalEnterpriseMinorVersions");

		runSQL(
			"update OSB_AccountEnvironment set envLFR = '" +
				ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10 +
					"' where envLFR = 20100");

		runSQL(
			"update OSB_LicenseEntry set portalVersionMax = 0 where " +
				"portalVersionMax = " +
					ProductEntryConstants.PORTAL_VERSION_OTHER);

		runSQL(
			"update OSB_LicenseKey set productVersion = '" +
				ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10 +
					"' where productVersion = 20100");

		runSQL(
			"update OSB_OfferingEntry set version = 21001 where version >= " +
				"22002 and version <= 22004");
		runSQL(
			"update OSB_OfferingEntry set version = 21000 where version = " +
				"22000 or version = 22001");

		runSQL(
			"update OSB_ProductEntry set versionsListType = " +
				"'portalMajorVersions' where versionsListType = " +
					"'portalMinorVersions'");

		runSQL(
			"update OSB_TicketInformation set data_ = '" +
				ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10 +
					"' where data_ = '20100'");
	}

	protected void updateOfferingEntries(
			long[] oldProductEntryIds, long newProductEntryId, int oldVersion,
			int newVersion)
		throws Exception {

		StringBundler sb = new StringBundler(
			7 + (oldProductEntryIds.length * 3));

		sb.append("update OSB_OfferingEntry set version = ");
		sb.append(newVersion);
		sb.append(", productEntryId = ");
		sb.append(newProductEntryId);
		sb.append(" where version = ");
		sb.append(oldVersion);
		sb.append(" and (");

		for (int i = 0; i < oldProductEntryIds.length; i++) {
			sb.append("productEntryId = ");
			sb.append(oldProductEntryIds[i]);

			if ((i + 1) < oldProductEntryIds.length) {
				sb.append(" or ");
			}
		}

		sb.append(")");

		runSQL(sb.toString());
	}

	private static final long[] _BACKUP_PRODUCT_ENTRY_IDS =
		{5968272, 10034376, 27012869, 24577955, 63869128, 73043190};

	private static final long[] _DEVELOPMENT_PRODUCT_ENTRY_IDS = {6135009};

	private static final long[] _ENTERPRISE_PRODUCT_ENTRY_IDS = {7493766};

	private static final long[] _LIMITED_PRODUCT_ENTRY_IDS = {11342216};

	private static final long[] _NON_PRODUCTION_PRODUCT_ENTRY_IDS = {
		5968274, 10034375, 24577950, 27012848, 42029290, 44966189, 49073528,
		57397429, 73045515
	};

	private static final long[] _OEM_PRODUCT_ENTRY_IDS = {7491306};

	private static final long[] _PRODUCTION_PRODUCT_ENTRY_IDS =
		{5968276, 27012837, 24576026, 44966204, 10034374};

	private static final Log _log = LogFactoryUtil.getLog(
		Upgrade_20160930103105564_ProductEntry.class);

	private long _deBackupProductEntryId;
	private long _deDevelopmentProductEntryId;
	private long _deEnterpriseProductEntryId;
	private long _deLimitedProductEntryId;
	private long _deNonProductionProductEntryId;
	private long _deOEMProductEntryId;
	private long _deProductionProductEntryId;

}