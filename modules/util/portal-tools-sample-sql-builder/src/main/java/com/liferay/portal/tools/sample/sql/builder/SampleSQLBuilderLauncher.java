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

package com.liferay.portal.tools.sample.sql.builder;

import com.liferay.petra.process.ClassPathUtil;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;

import java.lang.reflect.Method;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author Lily Chi
 */
public class SampleSQLBuilderLauncher {

	public static void main(String[] args) throws Exception {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		currentThread.setContextClassLoader(
			new URLClassLoader(_getDependencies(contextClassLoader), null));

		try {
			Method method = ReflectionUtil.getDeclaredMethod(
				Class.forName(
					"com.liferay.portal.tools.sample.sql.builder." +
						"SampleSQLBuilder"),
				"main", String[].class);

			method.invoke(null, new Object[] {args});
		}
		finally {
			currentThread.setContextClassLoader(contextClassLoader);
		}
	}

	private static URL[] _getDependencies(ClassLoader classLoader)
		throws Exception {

		Set<URL> urls = SetUtil.fromArray(
			ClassPathUtil.getClassPathURLs(
				ClassPathUtil.getJVMClassPath(true)));

		URL url = classLoader.getResource("lib");

		try (FileSystem fileSystem = FileSystems.newFileSystem(
				url.toURI(), Collections.emptyMap())) {

			Stream<Path> pathStream = Files.list(fileSystem.getPath("/lib"));

			pathStream.forEach(
				path -> {
					URI uri = path.toUri();

					try {
						urls.add(uri.toURL());
					}
					catch (MalformedURLException malformedURLException) {
					}
				});
		}

		return urls.toArray(new URL[0]);
	}

}