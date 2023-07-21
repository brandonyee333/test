/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.util;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.xml.Document;

import java.util.Locale;

/**
 * @author Marcellus Tavares
 * @author Bruno Basto
 */
public interface JournalConverter {

	public String getContent(DDMStructure ddmStructure, Fields ddmFields)
		throws Exception;

	public Fields getDDMFields(DDMStructure ddmStructure, Document document)
		throws PortalException;

	public Fields getDDMFields(DDMStructure ddmStructure, String content)
		throws PortalException;

	public DDMFormValues getDDMFormValues(
			DDMStructure ddmStructure, Fields fields)
		throws PortalException;

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public String getDDMXSD(String journalXSD) throws Exception;

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public String getDDMXSD(String journalXSD, Locale defaultLocale)
		throws Exception;

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public String getJournalXSD(String ddmXSD) throws Exception;

}