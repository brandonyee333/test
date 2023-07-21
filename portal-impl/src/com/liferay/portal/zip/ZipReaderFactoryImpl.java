/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.zip;

import com.liferay.portal.kernel.util.ClassLoaderUtil;
import com.liferay.portal.kernel.zip.ZipReader;
import com.liferay.portal.kernel.zip.ZipReaderFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Raymond Augé
 */
public class ZipReaderFactoryImpl implements ZipReaderFactory {

	@Override
	public ZipReader getZipReader(File file) {
		ClassLoader portalClassLoader = ClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			ClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				ClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipReaderImpl(file);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				ClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

	@Override
	public ZipReader getZipReader(InputStream inputStream) throws IOException {
		ClassLoader portalClassLoader = ClassLoaderUtil.getPortalClassLoader();

		ClassLoader contextClassLoader =
			ClassLoaderUtil.getContextClassLoader();

		try {
			if (contextClassLoader != portalClassLoader) {
				ClassLoaderUtil.setContextClassLoader(portalClassLoader);
			}

			return new ZipReaderImpl(inputStream);
		}
		finally {
			if (contextClassLoader != portalClassLoader) {
				ClassLoaderUtil.setContextClassLoader(contextClassLoader);
			}
		}
	}

}