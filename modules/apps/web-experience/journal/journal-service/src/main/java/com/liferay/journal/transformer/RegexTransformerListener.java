/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.transformer;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.templateparser.TransformerListener;
import com.liferay.portal.kernel.xml.Document;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + JournalPortletKeys.JOURNAL,
	service = TransformerListener.class
)
public class RegexTransformerListener extends BaseTransformerListener {

	@Override
	public String onOutput(
		String output, String languageId, Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onOutput");
		}

		return replace(output);
	}

	@Override
	public String onScript(
		String script, Document document, String languageId,
		Map<String, String> tokens) {

		if (_log.isDebugEnabled()) {
			_log.debug("onScript");
		}

		return replace(script);
	}

	protected String replace(String s) {
		if (s == null) {
			return s;
		}

		List<Pattern> patterns = RegexTransformerUtil.getPatterns();
		List<String> replacements = RegexTransformerUtil.getReplacements();

		for (int i = 0; i < patterns.size(); i++) {
			Pattern pattern = patterns.get(i);
			String replacement = replacements.get(i);

			Matcher matcher = pattern.matcher(s);

			s = matcher.replaceAll(replacement);
		}

		return s;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RegexTransformerListener.class);

}