/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextClassLoaderPool;

/**
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class ClassLoaderUtil {

	public static ClassLoader getAggregatePluginsClassLoader(
		String[] servletContextNames, boolean addContextClassLoader) {

		return _pacl.getAggregatePluginsClassLoader(
			servletContextNames, addContextClassLoader);
	}

	public static ClassLoader getClassLoader(Class<?> clazz) {
		return _pacl.getClassLoader(clazz);
	}

	public static ClassLoader getContextClassLoader() {
		return _pacl.getContextClassLoader();
	}

	public static ClassLoader getPluginClassLoader(String servletContextName) {
		return _pacl.getPluginClassLoader(servletContextName);
	}

	public static ClassLoader getPortalClassLoader() {
		return _pacl.getPortalClassLoader();
	}

	public static void setContextClassLoader(ClassLoader classLoader) {
		_pacl.setContextClassLoader(classLoader);
	}

	public static class NoPACL implements PACL {

		@Override
		public ClassLoader getAggregatePluginsClassLoader(
			String[] servletContextNames, boolean addContextClassLoader) {

			ClassLoader[] classLoaders = null;

			int offset = 0;

			if (addContextClassLoader) {
				classLoaders = new ClassLoader[servletContextNames.length + 1];

				Thread currentThread = Thread.currentThread();

				classLoaders[0] = currentThread.getContextClassLoader();

				offset = 1;
			}
			else {
				classLoaders = new ClassLoader[servletContextNames.length];
			}

			for (int i = 0; i < servletContextNames.length; i++) {
				ClassLoader classLoader =
					ServletContextClassLoaderPool.getClassLoader(
						servletContextNames[i]);

				if (classLoader == null) {
					_log.error(
						"Unable to find class loader for servlet context " +
							servletContextNames[i]);
				}
				else {
					classLoaders[offset + i] = classLoader;
				}
			}

			return AggregateClassLoader.getAggregateClassLoader(classLoaders);
		}

		@Override
		public ClassLoader getClassLoader(Class<?> clazz) {
			return clazz.getClassLoader();
		}

		@Override
		public ClassLoader getContextClassLoader() {
			Thread currentThread = Thread.currentThread();

			return currentThread.getContextClassLoader();
		}

		@Override
		public ClassLoader getPluginClassLoader(String servletContextName) {
			ClassLoader classLoader =
				ServletContextClassLoaderPool.getClassLoader(
					servletContextName);

			if (classLoader == null) {
				throw new IllegalStateException(
					"Unable to find the class loader for servlet context " +
						servletContextName);
			}

			return classLoader;
		}

		@Override
		public ClassLoader getPortalClassLoader() {
			return PortalClassLoaderUtil.getClassLoader();
		}

		@Override
		public void setContextClassLoader(ClassLoader classLoader) {
			Thread thread = Thread.currentThread();

			thread.setContextClassLoader(classLoader);
		}

	}

	public interface PACL {

		public ClassLoader getAggregatePluginsClassLoader(
			String[] servletContextNames, boolean addContextClassLoader);

		public ClassLoader getClassLoader(Class<?> clazz);

		public ClassLoader getContextClassLoader();

		public ClassLoader getPluginClassLoader(String servletContextName);

		public ClassLoader getPortalClassLoader();

		public void setContextClassLoader(ClassLoader classLoader);

	}

	private static final Log _log = LogFactoryUtil.getLog(
		ClassLoaderUtil.class);

	private static final PACL _pacl = new NoPACL();

}