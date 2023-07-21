/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.upload.web.internal;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.upload.UniqueFileNameProvider;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alejandro Tardín
 */
@Component
public class DefaultUniqueFileNameProvider implements UniqueFileNameProvider {

	@Override
	public String provide(String fileName, Predicate<String> predicate)
		throws PortalException {

		fileName = _removeParentheticalSuffix(fileName);

		String uniqueFileName = fileName;

		int tries = 0;

		while (predicate.test(uniqueFileName)) {
			if (tries >= _UNIQUE_FILE_NAME_TRIES) {
				throw new PortalException(
					"Unable to get a unique file name for " + fileName);
			}

			tries++;

			uniqueFileName = FileUtil.appendParentheticalSuffix(
				fileName, String.valueOf(tries));
		}

		return uniqueFileName;
	}

	private String _removeParentheticalSuffix(String fileName) {
		Matcher matcher = _pattern.matcher(fileName);

		if (matcher.matches()) {
			String name = matcher.group("name");
			String extension = matcher.group("extension");

			fileName = name;

			if (extension != null) {
				fileName += StringPool.PERIOD + extension;
			}
		}

		return fileName;
	}

	private static final int _UNIQUE_FILE_NAME_TRIES = 50;

	private static final Pattern _pattern = Pattern.compile(
		"(?<name>.+) \\(\\d+\\)(\\.(?<extension>[^.]+))?");

}