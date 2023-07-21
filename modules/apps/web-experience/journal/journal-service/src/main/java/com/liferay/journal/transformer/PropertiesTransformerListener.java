/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.transformer;

import com.liferay.journal.constants.JournalPortletKeys;
import com.liferay.portal.kernel.templateparser.BaseTransformerListener;
import com.liferay.portal.kernel.templateparser.TransformerListener;
import com.liferay.portal.kernel.xml.Document;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.journal.properties.transformer.listener.JournalPropertiesTransformerListener}
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + JournalPortletKeys.JOURNAL,
	service = TransformerListener.class
)
@Deprecated
public class PropertiesTransformerListener extends BaseTransformerListener {

	@Override
	public String onOutput(
		String output, String languageId, Map<String, String> tokens) {

		return super.onOutput(output, languageId, tokens);
	}

	@Override
	public String onScript(
		String script, Document document, String languageId,
		Map<String, String> tokens) {

		return super.onScript(script, document, languageId, tokens);
	}

	/**
	 * Replace the properties in a given string with their values fetched from
	 * the template GLOBAL-PROPERTIES.
	 *
	 * @return the processed string
	 */
	protected String replace(
		String s, String languageId, Map<String, String> tokens) {

		return null;
	}

}