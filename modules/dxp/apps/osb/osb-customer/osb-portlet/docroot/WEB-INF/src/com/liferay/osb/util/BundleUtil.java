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

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author Ryan Park
 */
public class BundleUtil {

	public static String getContextPath(File file) {
		Manifest manifest = getManifest(file);

		if (manifest == null) {
			return StringPool.BLANK;
		}

		Attributes attributes = manifest.getMainAttributes();

		return GetterUtil.getString(attributes.getValue("Web-ContextPath"));
	}

	public static Manifest getManifest(File file) {
		InputStream inputStream = null;
		ZipFile zipFile = null;

		try {
			zipFile = new ZipFile(file);

			ZipEntry zipEntry = zipFile.getEntry("META-INF/MANIFEST.MF");

			if (zipEntry == null) {
				return null;
			}

			inputStream = zipFile.getInputStream(zipEntry);

			return new Manifest(inputStream);
		}
		catch (Exception e) {
		}
		finally {
			StreamUtil.cleanUp(inputStream);

			if (zipFile != null) {
				try {
					zipFile.close();
				}
				catch (IOException ioe) {
				}
			}
		}

		return null;
	}

	public static String getSymbolicName(File file) {
		Manifest manifest = getManifest(file);

		if (manifest == null) {
			return StringPool.BLANK;
		}

		Attributes attributes = manifest.getMainAttributes();

		return GetterUtil.getString(attributes.getValue("Bundle-SymbolicName"));
	}

}