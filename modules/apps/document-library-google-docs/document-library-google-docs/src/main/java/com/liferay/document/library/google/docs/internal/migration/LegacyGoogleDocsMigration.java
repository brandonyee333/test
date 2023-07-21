/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.google.docs.internal.migration;

import com.liferay.document.library.google.docs.internal.util.GoogleDocsConstants;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsDLFileEntryTypeHelper;
import com.liferay.document.library.google.docs.internal.util.GoogleDocsMetadataHelper;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.dynamic.data.mapping.kernel.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.StorageEngine;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesToFieldsConverter;
import com.liferay.dynamic.data.mapping.util.FieldsToDDMFormValuesConverter;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.util.SetUtil;

/**
 * @author Iván Zaera
 */
public class LegacyGoogleDocsMigration {

	public LegacyGoogleDocsMigration(
		Company company,
		DDMFormValuesToFieldsConverter ddmFormValuesToFieldsConverter,
		DDMStructureLocalService ddmStructureLocalService,
		DLFileEntryTypeLocalService dlFileEntryTypeLocalService,
		DLFileEntryLocalService dlFileEntryLocalService,
		DLFileEntryMetadataLocalService dlFileEntryMetadataLocalService,
		FieldsToDDMFormValuesConverter fieldsToDDMFormValuesConverter,
		GoogleDocsDLFileEntryTypeHelper googleDocsDLFileEntryTypeHelper,
		StorageEngine storageEngine) {

		_company = company;
		_ddmFormValuesToFieldsConverter = ddmFormValuesToFieldsConverter;
		_ddmStructureLocalService = ddmStructureLocalService;
		_dlFileEntryTypeLocalService = dlFileEntryTypeLocalService;
		_dlFileEntryLocalService = dlFileEntryLocalService;
		_dlFileEntryMetadataLocalService = dlFileEntryMetadataLocalService;
		_fieldsToDDMFormValuesConverter = fieldsToDDMFormValuesConverter;
		_googleDocsDLFileEntryTypeHelper = googleDocsDLFileEntryTypeHelper;
		_storageEngine = storageEngine;

		try {
			_dlFileEntryType = _dlFileEntryTypeLocalService.fetchFileEntryType(
				_company.getGroupId(),
				LegacyGoogleDocsConstants.DL_FILE_ENTRY_TYPE_KEY);
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	public boolean isMigrationNeeded() {
		if (_dlFileEntryType == null) {
			return false;
		}

		return true;
	}

	public void migrate() throws PortalException {
		com.liferay.dynamic.data.mapping.model.DDMStructure ddmStructure =
			_googleDocsDLFileEntryTypeHelper.addGoogleDocsDDMStructure();

		_dlFileEntryType.setFileEntryTypeKey(
			GoogleDocsConstants.DL_FILE_ENTRY_TYPE_KEY);

		_dlFileEntryType = _dlFileEntryTypeLocalService.updateDLFileEntryType(
			_dlFileEntryType);

		_dlFileEntryTypeLocalService.addDDMStructureLinks(
			_dlFileEntryType.getFileEntryTypeId(),
			SetUtil.fromArray(new long[] {ddmStructure.getStructureId()}));

		upgradeDLFileEntries();

		deleteLegacyGoogleDocsDDMStructureFields();
	}

	protected void deleteLegacyGoogleDocsDDMStructureFields()
		throws PortalException {

		DDMStructure legacyDDMStructure =
			LegacyGoogleDocsMetadataHelper.getGoogleDocsDDMStructure(
				_dlFileEntryType);

		String definition = legacyDDMStructure.getDefinition();

		definition = definition.replaceAll(
			"(?s)<dynamic-element[^>]*>.*?</dynamic-element>", "");

		com.liferay.dynamic.data.mapping.model.DDMStructure ddmStructure =
			_ddmStructureLocalService.getDDMStructure(
				legacyDDMStructure.getStructureId());

		ddmStructure.setDefinition(definition);

		_ddmStructureLocalService.updateDDMStructure(ddmStructure);
	}

	protected void upgradeDLFileEntries() throws PortalException {
		ActionableDynamicQuery actionableDynamicQuery =
			_dlFileEntryLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<DLFileEntry>() {

				@Override
				public void performAction(DLFileEntry dlFileEntry) {
					GoogleDocsMetadataHelper googleDocsMetadataHelper =
						new GoogleDocsMetadataHelper(
							_ddmFormValuesToFieldsConverter,
							_ddmStructureLocalService, dlFileEntry,
							_dlFileEntryMetadataLocalService,
							_fieldsToDDMFormValuesConverter, _storageEngine);

					LegacyGoogleDocsMetadataHelper
						legacyGoogleDocsMetadataHelper =
							new LegacyGoogleDocsMetadataHelper(
								_ddmFormValuesToFieldsConverter,
								_ddmStructureLocalService, dlFileEntry,
								_storageEngine);

					googleDocsMetadataHelper.setFieldValue(
						GoogleDocsConstants.DDM_FIELD_NAME_EMBEDDABLE_URL,
						legacyGoogleDocsMetadataHelper.getFieldValue(
							LegacyGoogleDocsConstants.DDM_FIELD_NAME_VIEW_URL));
					googleDocsMetadataHelper.setFieldValue(
						GoogleDocsConstants.DDM_FIELD_NAME_ICON_URL,
						legacyGoogleDocsMetadataHelper.getFieldValue(
							LegacyGoogleDocsConstants.DDM_FIELD_NAME_ICON_URL));
					googleDocsMetadataHelper.setFieldValue(
						GoogleDocsConstants.DDM_FIELD_NAME_ID,
						legacyGoogleDocsMetadataHelper.getFieldValue(
							LegacyGoogleDocsConstants.DDM_FIELD_NAME_ID));
					googleDocsMetadataHelper.setFieldValue(
						GoogleDocsConstants.DDM_FIELD_NAME_NAME,
						legacyGoogleDocsMetadataHelper.getFieldValue(
							LegacyGoogleDocsConstants.DDM_FIELD_NAME_NAME));
					googleDocsMetadataHelper.setFieldValue(
						GoogleDocsConstants.DDM_FIELD_NAME_URL,
						legacyGoogleDocsMetadataHelper.getFieldValue(
							LegacyGoogleDocsConstants.DDM_FIELD_NAME_EDIT_URL));

					googleDocsMetadataHelper.update();

					legacyGoogleDocsMetadataHelper.delete();
				}

			});

		actionableDynamicQuery.performActions();
	}

	private final Company _company;
	private final DDMFormValuesToFieldsConverter
		_ddmFormValuesToFieldsConverter;
	private final DDMStructureLocalService _ddmStructureLocalService;
	private final DLFileEntryLocalService _dlFileEntryLocalService;
	private final DLFileEntryMetadataLocalService
		_dlFileEntryMetadataLocalService;
	private DLFileEntryType _dlFileEntryType;
	private final DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;
	private final FieldsToDDMFormValuesConverter
		_fieldsToDDMFormValuesConverter;
	private final GoogleDocsDLFileEntryTypeHelper
		_googleDocsDLFileEntryTypeHelper;
	private final StorageEngine _storageEngine;

}