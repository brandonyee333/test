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

package com.liferay.pulpo.connector.de.contacts.internal.model;

import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoValue;
import com.liferay.expando.kernel.service.ExpandoValueLocalService;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.pulpo.connector.de.contacts.ContactsConnector;
import com.liferay.pulpo.connector.de.contacts.model.ContactsModelListener;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = ModelListener.class)
public class ExpandoValueModelListener extends BaseModelListener<ExpandoValue> {

	@Override
	public void onAfterCreate(ExpandoValue expandoValue)
		throws ModelListenerException {

		try {
			processExpandoValue(expandoValue);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onAfterUpdate(ExpandoValue expandoValue)
		throws ModelListenerException {

		try {
			String data = _originalExpandoDataMap.remove(
				expandoValue.getPrimaryKey());

			if (!data.equals(expandoValue.getData())) {
				processExpandoValue(expandoValue);
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	@Override
	public void onBeforeUpdate(ExpandoValue expandoValue)
		throws ModelListenerException {

		ExpandoValue originalExpandoValue =
			_expandoValueLocalService.fetchExpandoValue(
				expandoValue.getValueId());

		_originalExpandoDataMap.put(
			originalExpandoValue.getPrimaryKey(),
			originalExpandoValue.getData());
	}

	protected void processExpandoValue(ExpandoValue expandoValue)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		JSONObject fieldJSONObject = JSONFactoryUtil.createJSONObject();

		fieldJSONObject.put("id", expandoValue.getClassPK());

		ExpandoColumn column = expandoValue.getColumn();

		fieldJSONObject.put("name", column.getName());

		fieldJSONObject.put("value", expandoValue.getData());

		jsonArray.put(fieldJSONObject);

		String className = expandoValue.getClassName();

		ContactsModelListener modelListener =
			_contactsConnector.getModelListener(
				className.substring(
					className.lastIndexOf(StringPool.PERIOD) + 1));

		PersistedModelLocalService persistedModelLocalService =
			PersistedModelLocalServiceRegistryUtil.
				getPersistedModelLocalService(className);

		PersistedModel persistedModel =
			persistedModelLocalService.getPersistedModel(
				expandoValue.getClassPK());

		modelListener.onAfterUpdate((BaseModel)persistedModel, jsonArray);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ExpandoValueModelListener.class);

	private static final Map<Long, String> _originalExpandoDataMap =
		new HashMap<>();

	@Reference
	private ContactsConnector _contactsConnector;

	@Reference
	private ExpandoValueLocalService _expandoValueLocalService;

}