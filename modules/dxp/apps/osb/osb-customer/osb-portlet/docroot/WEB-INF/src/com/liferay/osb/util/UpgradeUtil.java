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

package com.liferay.osb.util;

import com.liferay.expando.kernel.exception.DuplicateColumnNameException;
import com.liferay.expando.kernel.exception.NoSuchTableException;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoValueLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.service.ReleaseLocalServiceUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.File;

import java.net.URL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Amos Fong
 */
public class UpgradeUtil {

	public static List<Class<?>> getClasses(int buildNumber) throws Exception {
		String packageName = getPackageName(buildNumber);

		String packageDirName = StringUtil.replace(
			packageName, CharPool.PERIOD, CharPool.SLASH);

		ClassLoader classLoader = UpgradeUtil.class.getClassLoader();

		URL packageURL = classLoader.getResource(packageDirName);

		if (packageURL == null) {
			return Collections.emptyList();
		}

		List<Class<?>> classes = new ArrayList<>();

		File packageFile = new File(packageURL.getFile());

		String[] fileNames = packageFile.list();

		if (ArrayUtil.isEmpty(fileNames)) {
			return Collections.emptyList();
		}

		Arrays.sort(fileNames);

		for (String fileName : fileNames) {
			if (!fileName.endsWith(".class")) {
				continue;
			}

			String className = fileName.substring(0, fileName.length() - 6);

			Class<?> clazz = classLoader.loadClass(
				packageName + StringPool.PERIOD + className);

			classes.add(clazz);
		}

		return classes;
	}

	public static int[] getDeveloperBuildNumbers(String servletContextName) {
		Release release = ReleaseLocalServiceUtil.fetchRelease(
			servletContextName);

		return new int[] {
			release.getBuildNumber() - 2, release.getBuildNumber() - 1,
			release.getBuildNumber()
		};
	}

	public static String getPackageName(int buildNumber) {
		String buildNumberString = String.valueOf(buildNumber);

		StringBundler sb = new StringBundler(buildNumberString.length() * 2);

		sb.append("com.liferay.osb.hook.upgrade.v");

		for (int i = 0; i < buildNumberString.length(); i++) {
			char c = buildNumberString.charAt(i);

			sb.append(c);

			if (i < (buildNumberString.length() - 1)) {
				sb.append(CharPool.UNDERLINE);
			}
		}

		return sb.toString();
	}

	public static boolean hasRun(long classPK) throws PortalException {
		long runTime = ExpandoValueLocalServiceUtil.getData(
			OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
			ExpandoTableConstants.DEFAULT_TABLE_NAME, _EXPANDO_COLUMN_NAME,
			classPK, 0L);

		if (runTime > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean isDeveloperUpgrade() {
		return PortletPropsValues.DEVELOPER_UPGRADE_ENABLED;
	}

	public static void setRunTime(long classPK, long runTime) {
		try {
			ExpandoValueLocalServiceUtil.addValue(
				OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME, _EXPANDO_COLUMN_NAME,
				classPK, runTime);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public static void setUp() throws PortalException {
		ExpandoTable table = null;

		try {
			table = ExpandoTableLocalServiceUtil.getTable(
				OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}
		catch (NoSuchTableException nste) {
			table = ExpandoTableLocalServiceUtil.addTable(
				OSBConstants.COMPANY_ID, BaseUpgradeProcess.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		try {
			ExpandoColumnLocalServiceUtil.addColumn(
				table.getTableId(), _EXPANDO_COLUMN_NAME,
				ExpandoColumnConstants.LONG);
		}
		catch (DuplicateColumnNameException dcne) {
		}
	}

	private static final String _EXPANDO_COLUMN_NAME = "runTime";

	private static final Log _log = LogFactoryUtil.getLog(UpgradeUtil.class);

}