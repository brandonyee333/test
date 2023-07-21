/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.repository.cmis.internal;

import com.liferay.portal.kernel.util.ClassLoaderUtil;

import java.io.Closeable;

/**
 * @author Adolfo Pérez
 */
public class ContextClassLoaderSetter implements Closeable {

	public ContextClassLoaderSetter(ClassLoader classLoader) {
		_originalClassLoader = ClassLoaderUtil.getContextClassLoader();

		ClassLoaderUtil.setContextClassLoader(classLoader);
	}

	@Override
	public void close() {
		ClassLoaderUtil.setContextClassLoader(_originalClassLoader);
	}

	private final ClassLoader _originalClassLoader;

}