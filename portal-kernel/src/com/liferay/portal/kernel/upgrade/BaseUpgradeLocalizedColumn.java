/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upgrade;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.upgrade.util.UpgradeProcessUtil;
import com.liferay.portal.kernel.util.AggregateResourceBundleLoader;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;

import java.sql.SQLException;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * @author Leon Chi
 */
public abstract class BaseUpgradeLocalizedColumn extends UpgradeProcess {

	protected void upgradeLocalizedColumn(
			ResourceBundleLoader resourceBundleLoader, Class<?> tableClass,
			String columnName, String originalContent,
			String localizationMapKey, String localizationXMLKey,
			long[] companyIds)
		throws SQLException {

		Class<?> clazz = getClass();

		resourceBundleLoader = new AggregateResourceBundleLoader(
			ResourceBundleUtil.getResourceBundleLoader(
				"content.Language", clazz.getClassLoader()),
			resourceBundleLoader);

		try {
			String tableName = getTableName(tableClass);

			if (!hasColumnType(tableClass, columnName, "CLOB null") &&
				!_alteredTableNameColumnNames.contains(
					tableName + StringPool.POUND + columnName)) {

				alter(tableClass, new AlterColumnType(columnName, "TEXT null"));

				_alteredTableNameColumnNames.add(
					tableName + StringPool.POUND + columnName);
			}

			for (long companyId : companyIds) {
				_upgrade(
					resourceBundleLoader, tableClass, columnName,
					originalContent, localizationMapKey, localizationXMLKey,
					companyId);
			}
		}
		catch (Exception e) {
			throw new SQLException(e);
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x), use {@link
	 *             BaseUpgradeLocalizedColumn#upgradeLocalizedColumn(
	 *             ResourceBundleLoader, Class, String, String, String, String,
	 *             long[])}
	 */
	@Deprecated
	protected void upgradeLocalizedColumn(
			ResourceBundleLoader resourceBundleLoader, String tableName,
			String columnName, String originalContent,
			String localizationMapKey, String localizationXMLKey,
			long[] companyIds)
		throws SQLException {

		Class<?> clazz = getClass();

		resourceBundleLoader = new AggregateResourceBundleLoader(
			ResourceBundleUtil.getResourceBundleLoader(
				"content.Language", clazz.getClassLoader()),
			resourceBundleLoader);

		for (long companyId : companyIds) {
			_upgrade(
				resourceBundleLoader, tableName, columnName, originalContent,
				localizationMapKey, localizationXMLKey, companyId);
		}
	}

	private String _escape(String string) {
		return string.replace(
			StringPool.APOSTROPHE, StringPool.DOUBLE_APOSTROPHE);
	}

	private String _getLocalizationXML(
			String localizationMapKey, String localizationXMLKey,
			long companyId, ResourceBundleLoader resourceBundleLoader)
		throws SQLException {

		Long originalCompanyId = CompanyThreadLocal.getCompanyId();

		CompanyThreadLocal.setCompanyId(companyId);

		try {
			Map<Locale, String> localizationMap =
				ResourceBundleUtil.getLocalizationMap(
					resourceBundleLoader, localizationMapKey);

			String defaultLanguageId = UpgradeProcessUtil.getDefaultLanguageId(
				companyId);

			return LocalizationUtil.updateLocalization(
				localizationMap, "", localizationXMLKey, defaultLanguageId);
		}
		finally {
			CompanyThreadLocal.setCompanyId(originalCompanyId);
		}
	}

	private void _upgrade(
			ResourceBundleLoader resourceBundleLoader, Class<?> tableClass,
			String columnName, String originalContent,
			String localizationMapKey, String localizationXMLKey,
			long companyId)
		throws Exception {

		_upgrade(
			resourceBundleLoader, getTableName(tableClass), columnName,
			originalContent, localizationMapKey, localizationXMLKey, companyId);
	}

	private void _upgrade(
			ResourceBundleLoader resourceBundleLoader, String tableName,
			String columnName, String originalContent,
			String localizationMapKey, String localizationXMLKey,
			long companyId)
		throws SQLException {

		String localizationXML = _getLocalizationXML(
			localizationMapKey, localizationXMLKey, companyId,
			resourceBundleLoader);

		String sql = StringBundler.concat(
			"update ", tableName, " set ", columnName, " = '",
			_escape(localizationXML), "' where CAST_CLOB_TEXT(", columnName,
			") = '", _escape(originalContent), "' and companyId = ",
			String.valueOf(companyId));

		try {
			runSQL(sql);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	private static final Set<String> _alteredTableNameColumnNames =
		new HashSet<>();

}