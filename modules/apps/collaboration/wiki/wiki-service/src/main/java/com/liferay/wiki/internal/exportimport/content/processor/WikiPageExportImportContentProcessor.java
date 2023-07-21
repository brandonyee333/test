/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.internal.exportimport.content.processor;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.content.processor.base.BaseTextExportImportContentProcessor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gergely Mathe
 */
@Component(
	property = "model.class.name=com.liferay.wiki.model.WikiPage",
	service = {
		ExportImportContentProcessor.class,
		WikiPageExportImportContentProcessor.class
	}
)
public class WikiPageExportImportContentProcessor
	extends BaseTextExportImportContentProcessor {
}