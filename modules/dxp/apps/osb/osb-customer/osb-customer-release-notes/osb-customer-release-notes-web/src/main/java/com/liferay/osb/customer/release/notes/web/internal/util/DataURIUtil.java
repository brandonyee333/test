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

package com.liferay.osb.customer.release.notes.web.internal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Samuel Kong
 */
public class DataURIUtil {

	public static String encode(InputStream inputStream, String fileName) {
		StringBundler sb = new StringBundler(4);

		if (inputStream == null) {
			return sb.toString();
		}

		try {
			sb.append("data:");
			sb.append(MimeTypesUtil.getContentType(fileName));
			sb.append(";base64,");

			byte[] bytes = FileUtil.getBytes(inputStream);

			sb.append(Base64.encode(bytes));
		}
		catch (IOException ioe) {
			if (_log.isWarnEnabled()) {
				_log.warn(ioe.getMessage());
			}
		}

		return sb.toString();
	}

	private static final Log _log = LogFactoryUtil.getLog(DataURIUtil.class);

}