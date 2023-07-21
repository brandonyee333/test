/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.google.docs.internal.migration;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalServiceUtil;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesToFieldsConverter;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Iván Zaera
 */
public class LegacyGoogleDocsMetadataHelper {

	public static DDMStructure getGoogleDocsDDMStructure(
		DLFileEntryType dlFileEntryType) {

		List<DDMStructure> ddmStructures = dlFileEntryType.getDDMStructures();

		for (DDMStructure ddmStructure : ddmStructures) {
			if (ddmStructure.getType() == DDMStructureConstants.TYPE_AUTO) {
				return ddmStructure;
			}
		}

		return null;
	}

	public LegacyGoogleDocsMetadataHelper(
		DDMFormValuesToFieldsConverter ddmFormValuesToFieldsConverter,
		DDMStructureLocalService ddmStructureLocalService,
		DLFileEntry dlFileEntry, StorageEngine storageEngine) {

		try {
			_ddmFormValuesToFieldsConverter = ddmFormValuesToFieldsConverter;
			_ddmStructureLocalService = ddmStructureLocalService;
			_storageEngine = storageEngine;

			_dlFileVersion = dlFileEntry.getFileVersion();
			_ddmStructure = getGoogleDocsDDMStructure(
				dlFileEntry.getDLFileEntryType());
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	public void delete() {
		try {
			DLFileEntryMetadataLocalServiceUtil.deleteDLFileEntryMetadata(
				_dlFileEntryMetadata.getFileEntryMetadataId());

			_storageEngine.deleteByClass(
				_dlFileEntryMetadata.getDDMStorageId());
		}
		catch (PortalException pe) {
			throw new SystemException(
				"Unable to delete DDM fields for file version " +
					_dlFileVersion.getFileVersionId(),
				pe);
		}
	}

	public String getFieldValue(String fieldName) {
		initDLFileEntryMetadataAndFields();

		Field field = _fields.get(fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Unknown field " + fieldName);
		}

		Serializable value = field.getValue();

		if (value == null) {
			return null;
		}

		return value.toString();
	}

	protected void initDLFileEntryMetadataAndFields() {
		if (_fields != null) {
			return;
		}

		if (_dlFileVersion == null) {
			return;
		}

		_fields = new HashMap<>();

		try {
			_dlFileEntryMetadata =
				DLFileEntryMetadataLocalServiceUtil.getFileEntryMetadata(
					_ddmStructure.getStructureId(),
					_dlFileVersion.getFileVersionId());

			DDMFormValues ddmFormValues = _storageEngine.getDDMFormValues(
				_dlFileEntryMetadata.getDDMStorageId());

			Fields fields = _ddmFormValuesToFieldsConverter.convert(
				_ddmStructureLocalService.getDDMStructure(
					_ddmStructure.getStructureId()),
				ddmFormValues);

			for (Field field : fields) {
				_fields.put(field.getName(), field);
			}
		}
		catch (PortalException pe) {
			throw new SystemException(
				"Unable to load DDM fields for file version " +
					_dlFileVersion.getFileVersionId(),
				pe);
		}
	}

	private final DDMFormValuesToFieldsConverter
		_ddmFormValuesToFieldsConverter;
	private final DDMStructure _ddmStructure;
	private final DDMStructureLocalService _ddmStructureLocalService;
	private DLFileEntryMetadata _dlFileEntryMetadata;
	private DLFileVersion _dlFileVersion;
	private Map<String, Field> _fields;
	private final StorageEngine _storageEngine;

}