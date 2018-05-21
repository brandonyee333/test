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

package com.liferay.osb.hook.upgrade;

import com.liferay.osb.util.UpgradeUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeLocalServiceUtil;
import com.liferay.portal.kernel.upgrade.UpgradeException;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.List;

/**
 * @author Amos Fong
 * @author Ryan Park
 */
public class BaseUpgradeProcess extends UpgradeProcess {

	public int getFrequency() {
		return 0;
	}

	public long getTimestamp() {
		return 0;
	}

	public boolean hasIndex(String tableName, String indexName)
		throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			DatabaseMetaData metadata = connection.getMetaData();

			rs = metadata.getIndexInfo(null, null, tableName, false, false);

			while (rs.next()) {
				String curIndexName = rs.getString("index_name");

				if (indexName.equals(curIndexName)) {
					return true;
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}

		return false;
	}

	@Override
	public void upgrade() throws UpgradeException {
		super.upgrade();

		long timestamp = getTimestamp();

		if (timestamp > 0) {
			UpgradeUtil.setRunTime(timestamp, System.currentTimeMillis());
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<Class<?>> classes = UpgradeUtil.getClasses(getThreshold());

		for (Class<?> clazz : classes) {
			upgrade(clazz);
		}
	}

	protected void insertListType(int listTypeId, String name, String type)
		throws Exception {

		ListType listType = ListTypeLocalServiceUtil.fetchListType(listTypeId);

		if (listType != null) {
			return;
		}

		StringBundler sb = new StringBundler(7);

		sb.append("insert into ListType (listTypeId, name, type_) values (");
		sb.append(String.valueOf(listTypeId));
		sb.append(", '");
		sb.append(name);
		sb.append("', '");
		sb.append(type);
		sb.append("')");

		runSQL(sb.toString());
	}

	protected boolean isColumnType(
		String tableName, String columnName, String columnTypeName) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement("select * from " + tableName);

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				String curColumnName = rsmd.getColumnName(i + 1);
				String curColumnTypeName = rsmd.getColumnTypeName(i + 1);

				if (curColumnName.equals(columnName) &&
					curColumnTypeName.equals(columnTypeName)) {

					return true;
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}

		return false;
	}

	protected void runSQL(String template, boolean failOnError)
		throws IOException, SQLException {

		if (failOnError) {
			runSQL(template);
		}
		else {
			try {
				runSQL(template);
			}
			catch (Exception e) {
				_log.error(e.getMessage() + ": " + template);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseUpgradeProcess.class);

}