/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.web.internal.util;

import com.liferay.document.library.display.context.DLEditFileEntryDisplayContext;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.util.Locale;
import java.util.Set;

/**
 * @author Adolfo Pérez
 */
public class DLFileEntryTypeUtil {

	public static Locale[] getDLFileEntryTypeAvailableLocales(
			FileVersion fileVersion, DLFileEntryType dlFileEntryType,
			DLEditFileEntryDisplayContext dlEditFileEntryDisplayContext,
			String defaultLanguageId)
		throws PortalException {

		Locale defaultLocale = LocaleUtil.fromLanguageId(defaultLanguageId);

		if ((fileVersion == null) || (dlFileEntryType == null)) {
			return new Locale[] {defaultLocale};
		}

		for (DDMStructure ddmStructure : dlFileEntryType.getDDMStructures()) {
			DLFileEntryMetadata dlFileEntryMetadata =
				DLFileEntryMetadataLocalServiceUtil.fetchFileEntryMetadata(
					ddmStructure.getStructureId(),
					fileVersion.getFileVersionId());

			if (dlFileEntryMetadata == null) {
				continue;
			}

			DDMFormValues ddmFormValues =
				dlEditFileEntryDisplayContext.getDDMFormValues(
					dlFileEntryMetadata.getDDMStorageId());

			if (ddmFormValues != null) {
				Set<Locale> availableLocalesSet =
					ddmFormValues.getAvailableLocales();

				return availableLocalesSet.toArray(new Locale[0]);
			}
		}

		return new Locale[] {defaultLocale};
	}

}