/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.assets.properties.provider.internal;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLFileEntryMetadata;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryMetadataLocalService;
import com.liferay.document.library.kernel.service.DLFileVersionLocalService;
import com.liferay.dynamic.data.mapping.kernel.DDMFormValues;
import com.liferay.dynamic.data.mapping.kernel.StorageEngineManagerUtil;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.storage.Field;
import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.dynamic.data.mapping.util.DDMBeanTranslator;
import com.liferay.dynamic.data.mapping.util.DDMFormValuesToFieldsConverter;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.pulpo.connector.de.assets.properties.provider.AssetConnectorPropertiesProvider;
import com.liferay.pulpo.connector.de.assets.properties.provider.BaseAssetConnectorPropertiesProvider;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = AssetConnectorPropertiesProvider.class)
public class DLFileEntryAssetConnectorPropertiesProvider
	extends BaseAssetConnectorPropertiesProvider
	implements AssetConnectorPropertiesProvider {

	@Override
	public String getClassName() {
		return DLFileEntryConstants.getClassName();
	}

	@Override
	public JSONArray getProperties(AssetEntry assetEntry) {
		JSONArray propertiesJSONArray = JSONFactoryUtil.createJSONArray();

		try {
			AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

			if (assetRenderer == null) {
				return propertiesJSONArray;
			}

			FileEntry fileEntry = (FileEntry)assetRenderer.getAssetObject();

			FileVersion fileVersion = fileEntry.getFileVersion();

			addDLFileEntryMetadata(propertiesJSONArray, fileVersion);

			addExpandoValues(propertiesJSONArray, assetEntry);
		}
		catch (Exception e) {
			_log.error("Unable to get asset entry properties", e);
		}

		return propertiesJSONArray;
	}

	protected void addDLFileEntryMetadata(
			JSONArray array, FileVersion fileVersion)
		throws Exception {

		List<DLFileEntryMetadata> dlFileEntryMetadatas =
			_dlFileEntryMetadataLocalService.getFileVersionFileEntryMetadatas(
				fileVersion.getFileVersionId());

		for (DLFileEntryMetadata dlFileEntryMetadata : dlFileEntryMetadatas) {
			DDMFormValues ddmFormValues =
				StorageEngineManagerUtil.getDDMFormValues(
					dlFileEntryMetadata.getDDMStorageId());

			DDMStructure ddmStructure =
				_ddmStructureLocalService.getDDMStructure(
					dlFileEntryMetadata.getDDMStructureId());

			Fields fields = _ddmFormValuesToFieldsConverter.convert(
				ddmStructure, _ddmBeanTranslator.translate(ddmFormValues));

			for (Field field : fields) {
				JSONObject property = JSONFactoryUtil.createJSONObject();

				property.put("name", field.getName());
				property.put("value", field.getValue());

				array.put(property);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileEntryAssetConnectorPropertiesProvider.class);

	@Reference
	private DDMBeanTranslator _ddmBeanTranslator;

	@Reference
	private DDMFormValuesToFieldsConverter _ddmFormValuesToFieldsConverter;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryMetadataLocalService _dlFileEntryMetadataLocalService;

	@Reference
	private DLFileVersionLocalService _dlFileVersionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}