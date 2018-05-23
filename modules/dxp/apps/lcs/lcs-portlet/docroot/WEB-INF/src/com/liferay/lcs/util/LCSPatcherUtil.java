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

package com.liferay.lcs.util;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.patcher.PatcherUtil;
import com.liferay.portal.kernel.plugin.Version;
import com.liferay.portal.kernel.util.ClassResolverUtil;

import java.io.File;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.net.URL;
import java.net.URLDecoder;

import java.security.CodeSource;
import java.security.ProtectionDomain;

import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @author Matija Petanjek
 */
public class LCSPatcherUtil {

	public static String[] getFixedIssues() {
		_resetProperties();

		return PatcherUtil.getFixedIssues();
	}

	public static String[] getInstalledPatches() {
		_resetProperties();

		return PatcherUtil.getInstalledPatches();
	}

	public static File getPatchDirectory() {
		_resetProperties();

		return PatcherUtil.getPatchDirectory();
	}

	public static int getPatchingToolVersion() {
		_resetProperties();

		return PatcherUtil.getPatchingToolVersion();
	}

	public static boolean isConfigured() {
		_resetProperties();

		return PatcherUtil.isConfigured();
	}

	private static String _getCurrentPatcherImplBundleVersion() {
		if (_currentPatcherImplBundleVersion != null) {
			return _currentPatcherImplBundleVersion;
		}

		try {
			Class<?> clazz = ClassResolverUtil.resolveByPortalClassLoader(
				"com.liferay.portal.patcher.PatcherImpl");

			ProtectionDomain protectionDomain = clazz.getProtectionDomain();

			CodeSource codeSource = protectionDomain.getCodeSource();

			URL url = codeSource.getLocation();

			JarFile jarFile = new JarFile(
				URLDecoder.decode(url.getPath(), "UTF-8"));

			Manifest manifest = jarFile.getManifest();

			Attributes attributes = manifest.getMainAttributes();

			_currentPatcherImplBundleVersion = attributes.getValue(
				"Bundle-Version");
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}

		return _currentPatcherImplBundleVersion;
	}

	private static void _resetProperties() {
		if (_PATCHER_IMPL_FIX_BUNDLE_VERSION.isPreviousVersionThan(
				_getCurrentPatcherImplBundleVersion())) {

			return;
		}

		try {
			Object patcherUtil = PortalBeanLocatorUtil.locate(
				"com.liferay.portal.kernel.patcher.PatcherUtil");

			Class<?> clazz = patcherUtil.getClass();

			Field patcherField = clazz.getDeclaredField("_patcher");

			patcherField.setAccessible(true);

			Object patcherImpl = patcherField.get(patcherUtil);

			clazz = patcherImpl.getClass();

			Field propertiesField = clazz.getDeclaredField("_properties");

			propertiesField.setAccessible(true);

			propertiesField.set(patcherImpl, null);

			if (_PATCHER_IMPL_FIX_BUNDLE_VERSION.isSameVersionAs(
					_getCurrentPatcherImplBundleVersion())) {

				Method getPropertiesMethod = clazz.getDeclaredMethod(
					"getProperties");

				getPropertiesMethod.invoke(patcherImpl);
			}
		}
		catch (Exception e) {
			_log.error(e.getMessage(), e);
		}
	}

	private static final Version _PATCHER_IMPL_FIX_BUNDLE_VERSION =
		Version.getInstance("2.9.1");

	private static final Log _log = LogFactoryUtil.getLog(LCSPatcherUtil.class);

	private static String _currentPatcherImplBundleVersion;

}