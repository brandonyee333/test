/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.transformer;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Brian Wing Shun Chan
 */
public class RegexTransformerUtil {

	public static List<Pattern> getPatterns() {
		return _instance._patterns;
	}

	public static List<String> getReplacements() {
		return _instance._replacements;
	}

	private RegexTransformerUtil() {
		_patterns = new ArrayList<>();
		_replacements = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			String regex = PropsUtil.get(
				"journal.transformer.regex.pattern." + i);
			String replacement = PropsUtil.get(
				"journal.transformer.regex.replacement." + i);

			if (Validator.isNull(regex) || Validator.isNull(replacement)) {
				break;
			}

			if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"Pattern ", regex, " will be replaced with ",
						replacement));
			}

			_patterns.add(Pattern.compile(regex));
			_replacements.add(replacement);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RegexTransformerUtil.class);

	private static final RegexTransformerUtil _instance =
		new RegexTransformerUtil();

	private final List<Pattern> _patterns;
	private final List<String> _replacements;

}