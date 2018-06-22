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

package com.liferay.osb.tools;

import com.liferay.portal.kernel.exception.PortalException;
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
 * @author Igor Beslic
 */
public class BaseUpgradeImpl implements Upgrade {

	@Override
	public List<Class<?>> getClasses(int buildNumber) throws Exception {
		String packageName = getPackageName(buildNumber);

		String packageDirName = StringUtil.replace(
			packageName, CharPool.PERIOD, CharPool.SLASH);

		ClassLoader classLoader = BaseUpgradeImpl.class.getClassLoader();

		URL packageURL = classLoader.getResource(packageDirName);

		if (packageURL == null) {
			return Collections.emptyList();
		}

		List<Class<?>> classes = new ArrayList<>();

		File packageFile = new File(packageURL.getFile());

		String[] fileNames = packageFile.list();

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

	@Override
	public int[] getDeveloperBuildNumbers(String servletContextName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPackageName(int buildNumber) {
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

	@Override
	public boolean hasRun(long classPK) throws PortalException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isDeveloperUpgrade() {
		return true;
	}

	@Override
	public void setRunTime(long classPK, long runTime) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setUp() throws PortalException {
		throw new UnsupportedOperationException();
	}

}