/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.io.File;
import java.io.InputStream;

/**
 * @author Bruno Farache
 */
public class DocumentConversionUtil {

	public static File convert(
			String id, InputStream inputStream, String sourceExtension,
			String targetExtension)
		throws Exception {

		Object returnObj = PortalClassInvoker.invoke(
			_convertMethodKey, id, inputStream, sourceExtension,
			targetExtension);

		if (returnObj != null) {
			return (File)returnObj;
		}

		return null;
	}

	public static String[] getConversions(String extension) throws Exception {
		Object returnObj = PortalClassInvoker.invoke(
			_getConversionsMethodKey, extension);

		if (returnObj != null) {
			return (String[])returnObj;
		}

		return null;
	}

	private static final String _CLASS_NAME =
		"com.liferay.portlet.documentlibrary.util.DocumentConversionUtil";

	private static final MethodKey _convertMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME), "convert",
		String.class, InputStream.class, String.class, String.class);
	private static final MethodKey _getConversionsMethodKey = new MethodKey(
		ClassResolverUtil.resolveByPortalClassLoader(_CLASS_NAME),
		"getConversions", String.class);

}