/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.calendar.internal.exportimport.content.processor;

import com.liferay.exportimport.content.processor.ExportImportContentProcessor;
import com.liferay.exportimport.content.processor.base.BaseTextExportImportContentProcessor;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gergely Mathe
 */
@Component(
	property = "model.class.name=com.liferay.calendar.model.CalendarNotificationTemplate",
	service = {
		CalendarNotificationTemplateExportImportContentProcessor.class,
		ExportImportContentProcessor.class
	}
)
public class CalendarNotificationTemplateExportImportContentProcessor
	extends BaseTextExportImportContentProcessor {
}