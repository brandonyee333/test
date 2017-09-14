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

package com.liferay.osb.customer.release.notes.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.File;
import java.io.IOException;

/**
 * @author Samuel Kong
 */
public class DataURIUtil {

	public static String encode(File file) {
		StringBundler sb = new StringBundler(4);

		if (!file.exists()) {
			return sb.toString();
		}

		try {
			sb.append("data:");
			sb.append(MimeTypesUtil.getContentType(file));
			sb.append(";base64,");

			byte[] bytes = FileUtil.getBytes(file);

			sb.append(Base64.encode(bytes));
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(ioe.getMessage());
			}
		}

		return sb.toString();
	}

	public static String encode(String fileName) {
		return encode(new File(fileName));
	}

	private static Log _log = LogFactoryUtil.getLog(DataURIUtil.class);

}