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

package com.liferay.osb.hook.upgrade.v3_5_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AuditEntry;
import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketInformationConstants;
import com.liferay.osb.service.AuditEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Jenny Chen
 */
public class Upgrade_20160608141255945_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20160608141255945L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateListType();

		updateTicketEntryTable();

		updateTicketEntry();
	}

	protected void updateAuditEntry(
			long fieldClassPK, int envBrowser, int oldEnvBrowser)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			StringBundler sb = new StringBundler(3);

			sb.append("select auditEntryId from OSB_AuditEntry where (");
			sb.append("fieldClassPK = ?) and (field = ?) and (");
			sb.append("oldValue = ? or newValue = ?)");

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, fieldClassPK);
			ps.setInt(2, AuditEntryConstants.FIELD_ENV_BROWSER);
			ps.setString(3, String.valueOf(oldEnvBrowser));
			ps.setString(4, String.valueOf(oldEnvBrowser));

			rs = ps.executeQuery();

			while (rs.next()) {
				long auditEntryId = rs.getLong("auditEntryId");

				updateAuditEntryData(auditEntryId, envBrowser, oldEnvBrowser);
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateAuditEntryData(
			long auditEntryId, int envBrowser, int oldEnvBrowser)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			AuditEntry auditEntry = AuditEntryLocalServiceUtil.getAuditEntry(
				auditEntryId);

			String oldValue = auditEntry.getOldValue();

			if (!oldValue.equals(StringPool.BLANK)) {
				String sql =
					"update OSB_AuditEntry set oldValue = ? " +
						"where (auditEntryId = ?) and (oldValue = ?)";

				ps = con.prepareStatement(sql);

				ps.setString(1, String.valueOf(envBrowser));
				ps.setLong(2, auditEntryId);
				ps.setString(3, String.valueOf(oldEnvBrowser));

				ps.executeUpdate();
			}

			String newValue = auditEntry.getNewValue();

			if (Validator.isNotNull(newValue)) {
				String sql =
					"update OSB_AuditEntry set newValue = ? " +
						"where (auditEntryId = ?) and (newValue = ?)";

				ps = con.prepareStatement(sql);

				ps.setString(1, String.valueOf(envBrowser));
				ps.setLong(2, auditEntryId);
				ps.setString(3, String.valueOf(oldEnvBrowser));

				ps.executeUpdate();
			}
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void updateListType() throws Exception {
		insertListType(
			AuditEntryConstants.FIELD_ENV_CS, "cloud-services",
			AuditEntryConstants.LIST_TYPE_FIELD);
		insertListType(
			AuditEntryConstants.FIELD_ENV_SEARCH, "search",
			AuditEntryConstants.LIST_TYPE_FIELD);
		insertListType(
			TicketEntryConstants.ENV_AS_TCSERVER_3_1, "tcServer-3",
			TicketEntryConstants.LIST_TYPE_ENV_AS);
		insertListType(
			TicketEntryConstants.ENV_AS_TOMCAT_8_0, "tomcat-8.0",
			TicketEntryConstants.LIST_TYPE_ENV_AS);
		insertListType(
			TicketEntryConstants.ENV_AS_WEBLOGIC_12C_R2, "weblogic-12c-r2",
			TicketEntryConstants.LIST_TYPE_ENV_AS);
		insertListType(
			TicketEntryConstants.ENV_AS_WILDFILY_10_0, "wildfly-10.0",
			TicketEntryConstants.LIST_TYPE_ENV_AS);
		insertListType(
			TicketEntryConstants.ENV_BROWSER_EDGE, "edge",
			TicketEntryConstants.LIST_TYPE_ENV_BROWSER);
		insertListType(
			TicketEntryConstants.ENV_BROWSER_FIREFOX_ESR_45, "firefox-esr-45",
			TicketEntryConstants.LIST_TYPE_ENV_BROWSER);
		insertListType(
			TicketEntryConstants.ENV_BROWSER_MOBILE_CHROME, "mobile-chrome",
			TicketEntryConstants.LIST_TYPE_ENV_BROWSER);
		insertListType(
			TicketEntryConstants.ENV_BROWSER_SAFARI_8, "safari-8",
			TicketEntryConstants.LIST_TYPE_ENV_BROWSER);
		insertListType(
			TicketEntryConstants.ENV_BROWSER_SAFARI_9, "safari-9",
			TicketEntryConstants.LIST_TYPE_ENV_BROWSER);
		insertListType(
			TicketEntryConstants.ENV_CS_AWS_ELASTIC_COMPUTE_CLOUD,
			"aws-elastic-compute-cloud", TicketEntryConstants.LIST_TYPE_ENV_CS);
		insertListType(
			TicketEntryConstants.ENV_CS_AWS_RELATIONAL_DATABASE_SERVICE,
			"aws-relational-database-service",
			TicketEntryConstants.LIST_TYPE_ENV_CS);
		insertListType(
			TicketEntryConstants.ENV_CS_AWS_S3, "aws-s3",
			TicketEntryConstants.LIST_TYPE_ENV_CS);
		insertListType(
			TicketEntryConstants.ENV_CS_AZURE_FILES, "azure-files",
			TicketEntryConstants.LIST_TYPE_ENV_CS);
		insertListType(
			TicketEntryConstants.ENV_CS_AZURE_SQL_DATABASES,
			"azure-sql-databases", TicketEntryConstants.LIST_TYPE_ENV_CS);
		insertListType(
			TicketEntryConstants.ENV_CS_AZURE_VIRTUAL_MACHINES,
			"azure-virtual-machines", TicketEntryConstants.LIST_TYPE_ENV_CS);
		insertListType(
			TicketEntryConstants.ENV_DB_MYSQL_5_7, "mysql-5.7",
			TicketEntryConstants.LIST_TYPE_ENV_DB);
		insertListType(
			TicketEntryConstants.ENV_DB_POSTGRESQL_9_4, "postgresql-9.4",
			TicketEntryConstants.LIST_TYPE_ENV_DB);
		insertListType(
			TicketEntryConstants.ENV_DB_SYBASE_ASE_16, "sybase-ase-16",
			TicketEntryConstants.LIST_TYPE_ENV_DB);
		insertListType(
			TicketEntryConstants.ENV_JVM_IBM_JDK_8, "ibm-jdk-8",
			TicketEntryConstants.LIST_TYPE_ENV_JVM);
		insertListType(
			TicketEntryConstants.ENV_OS_CENTOS_7, "centos-7",
			TicketEntryConstants.LIST_TYPE_ENV_OS);
		insertListType(
			TicketEntryConstants.ENV_OS_DEBIAN_7, "debian-7",
			TicketEntryConstants.LIST_TYPE_ENV_OS);
		insertListType(
			TicketEntryConstants.ENV_OS_DEBIAN_8, "debian-8",
			TicketEntryConstants.LIST_TYPE_ENV_OS);
		insertListType(
			TicketEntryConstants.ENV_OS_ORACLE_LINUX_7, "oracle-linux-7",
			TicketEntryConstants.LIST_TYPE_ENV_OS);
		insertListType(
			TicketEntryConstants.ENV_OS_SUSE_ENTERPRISE_LINUX_12,
			"suse-enterprise-linux-12", TicketEntryConstants.LIST_TYPE_ENV_OS);
		insertListType(
			TicketEntryConstants.ENV_OS_UBUNTU_LTS_14_04, "ubuntu-lts-14.04",
			TicketEntryConstants.LIST_TYPE_ENV_OS);
		insertListType(
			TicketEntryConstants.ENV_SEARCH_ELASTICSEARCH, "elasticsearch",
			TicketEntryConstants.LIST_TYPE_ENV_SEARCH);
		insertListType(
			TicketEntryConstants.ENV_SEARCH_KIBANA_4_4, "kibana-4.4",
			TicketEntryConstants.LIST_TYPE_ENV_SEARCH);
		insertListType(
			TicketEntryConstants.ENV_SEARCH_MARVEL_2_2, "marvel-2.2",
			TicketEntryConstants.LIST_TYPE_ENV_SEARCH);
		insertListType(
			TicketEntryConstants.ENV_SEARCH_SHIELD_2_2, "shield-2.2",
			TicketEntryConstants.LIST_TYPE_ENV_SEARCH);
		insertListType(
			TicketEntryConstants.ENV_SEARCH_SOLR, "solr",
			TicketEntryConstants.LIST_TYPE_ENV_SEARCH);
		insertListType(
			TicketEntryConstants.ENV_SEARCH_SOLRCLOUD, "solrcloud",
			TicketEntryConstants.LIST_TYPE_ENV_SEARCH);

		runSQL(
			"update ListType set name = 'chrome' where listTypeId = " +
				TicketEntryConstants.ENV_BROWSER_CHROME);
		runSQL(
			"update ListType set name = 'firefox' where listTypeId = " +
				TicketEntryConstants.ENV_BROWSER_FIREFOX);
		runSQL(
			"update ListType set name = 'ios-safari' where listTypeId = " +
				TicketEntryConstants.ENV_BROWSER_IOS_SAFARI);
		runSQL(
			"update ListType set name = 'oracle-10g-release-2' where " +
				"listTypeId = " +
					TicketEntryConstants.ENV_DB_ORACLE_10G_RELEASE_2);
		runSQL(
			"update ListType set name = 'oracle-11g-release-1' where " +
				"listTypeId = " +
					TicketEntryConstants.ENV_DB_ORACLE_11G_RELEASE_1);
		runSQL(
			"update ListType set name = 'oracle-11g-release-2' where " +
				"listTypeId = " +
					TicketEntryConstants.ENV_DB_ORACLE_11G_RELEASE_2);
		runSQL(
			"update ListType set name = 'oracle-12c-release-1' where " +
				"listTypeId = " +
					TicketEntryConstants.ENV_DB_ORACLE_12C_RELEASE_1);
		runSQL(
			"update ListType set name = 'oracle-linux-6' where listTypeId = " +
				TicketEntryConstants.ENV_OS_ORACLE_LINUX_6);
		runSQL(
			"update ListType set name = 'weblogic-12c-r1' where listTypeId = " +
				TicketEntryConstants.ENV_AS_WEBLOGIC_12C_R1);
	}

	protected void updateTicketEntry() throws Exception {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"select ticketEntryId, envBrowser from OSB_TicketEntry " +
					"where (envBrowser = ?)";

			ps = con.prepareStatement(sql);

			ps.setInt(1, 37011);

			rs = ps.executeQuery();

			while (rs.next()) {
				long ticketEntryId = rs.getLong("ticketEntryId");
				int envBrowser = rs.getInt("envBrowser");

				updateAuditEntry(
					ticketEntryId, TicketEntryConstants.ENV_BROWSER_IOS_SAFARI,
					envBrowser);
				updateTicketEntryData(
					ticketEntryId, TicketEntryConstants.ENV_BROWSER_IOS_SAFARI);
				updateTicketInformation(
					ticketEntryId,
					String.valueOf(
						TicketEntryConstants.ENV_BROWSER_IOS_SAFARI));
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}

	protected void updateTicketEntryData(long ticketEntryId, int envBrowser)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"update OSB_TicketEntry set envBrowser = ? where (" +
					"ticketEntryId = ?)";

			ps = con.prepareStatement(sql);

			ps.setInt(1, envBrowser);
			ps.setLong(2, ticketEntryId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

	protected void updateTicketEntryTable() throws Exception {
		if (!tableHasColumn("OSB_TicketEntry", "envCS")) {
			runSQL("alter table OSB_TicketEntry add column envCS INTEGER");
		}

		if (!tableHasColumn("OSB_TicketEntry", "envSearch")) {
			runSQL(
				"alter table OSB_TicketEntry add column envSearch VARCHAR(75)");
		}
	}

	protected void updateTicketInformation(long ticketEntryId, String data)
		throws Exception {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataAccess.getUpgradeOptimizedConnection();

			String sql =
				"update OSB_TicketInformation set data_ = ? where (" +
					"fieldId = ?) and (ticketEntryId = ?)";

			ps = con.prepareStatement(sql);

			ps.setString(1, data);
			ps.setLong(2, TicketInformationConstants.FIELD_ENV_BROWSER);
			ps.setLong(3, ticketEntryId);

			ps.executeUpdate();
		}
		finally {
			DataAccess.cleanUp(con, ps);
		}
	}

}

*/

}