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

package com.liferay.pulpo.connector.de.contacts.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.bean.BeanPropertiesUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pulpo.connector.de.contacts.ContactsConnector;
import com.liferay.pulpo.connector.de.contacts.constants.ContactsEntryProviderDestinationNames;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public abstract class ContactsModelListener<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		if (!associationClassName.equals(User.class.getName())) {
			return;
		}

		Class<?> clazz = _contactsConnector.getModelClass(this);

		PersistedModelLocalService persistedModelLocalService =
			PersistedModelLocalServiceRegistryUtil.
				getPersistedModelLocalService(clazz.getName());

		try {
			PersistedModel persistedModel =
				persistedModelLocalService.getPersistedModel(
					(Serializable)classPK);

			User user = UserLocalServiceUtil.fetchUser(
				GetterUtil.getLong(associationClassPK));

			JSONArray fieldValuesJSONArray = getFieldValuesJSONArray(
				user, Collections.singletonList((T)persistedModel));

			updateUser(user, fieldValuesJSONArray);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	public void onAfterUpdate(T model) throws ModelListenerException {
		Map<String, Object> modelAttributes = model.getModelAttributes();

		JSONArray fieldValuesJSONArray = JSONFactoryUtil.createJSONArray();

		PersistedModel persistedModel = _persistedModels.get(
			model.getPrimaryKeyObj());

		Class<?> clazz = model.getModelClass();

		String fieldPrefix = clazz.getSimpleName() + StringPool.POUND;

		for (Map.Entry<String, Object> entry : modelAttributes.entrySet()) {
			String key = entry.getKey();

			String value = BeanPropertiesUtil.getString(model, entry.getKey());

			if (value.equals(
					BeanPropertiesUtil.getString(
						persistedModel, entry.getKey()))) {

				continue;
			}

			fieldValuesJSONArray.put(
				createJSONObject(
					model.getPrimaryKeyObj(), fieldPrefix.concat(key), value));
		}

		onAfterUpdate(model, fieldValuesJSONArray);
	}

	public void onAfterUpdate(T model, JSONArray jsonArray) {
		if (model instanceof User) {
			updateUser((User)model, jsonArray);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("contactsProviderId", _CONTACTS_PROVIDER_ID);
			jsonObject.put("projectId", _PROJECT_ID);
			jsonObject.put("properties", jsonArray);

			_contactsConnector.sendMessage(
				ContactsEntryProviderDestinationNames.UPDATE_PROPERTIES,
				jsonObject.toString());
		}
	}

	@Override
	public void onBeforeUpdate(T model) throws ModelListenerException {
		try {
			PersistedModelLocalService persistedModelLocalService =
				PersistedModelLocalServiceRegistryUtil.
					getPersistedModelLocalService(model.getModelClassName());

			PersistedModel persistedModel =
				persistedModelLocalService.getPersistedModel(
					model.getPrimaryKeyObj());

			_persistedModels.put(model.getPrimaryKeyObj(), persistedModel);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	public void setContactsConnector(ContactsConnector contactsConnector) {
		_contactsConnector = contactsConnector;
	}

	protected JSONObject createJSONObject(
		Serializable id, String name, String value) {

		JSONObject fieldJSONObject = JSONFactoryUtil.createJSONObject();

		fieldJSONObject.put("id", id);
		fieldJSONObject.put("name", name);
		fieldJSONObject.put("value", value);

		return fieldJSONObject;
	}

	protected JSONArray getFieldValuesJSONArray(User user, List<T> models) {
		Class<?> clazz = _contactsConnector.getModelClass(this);

		JSONArray fieldValuesJSONArray = JSONFactoryUtil.createJSONArray();

		if (ListUtil.isEmpty(models)) {
			try {
				models = getModels(user);
			}
			catch (Exception e) {
				_log.error(e, e);

				return fieldValuesJSONArray;
			}
		}

		for (BaseModel model : models) {
			ExpandoBridge expandoBridge = model.getExpandoBridge();

			String fieldPrefix = clazz.getSimpleName() + StringPool.POUND;

			Map<String, Object> attributes = model.getModelAttributes();

			for (Map.Entry<String, Object> attribute : attributes.entrySet()) {
				String value = null;

				String attributeName = attribute.getKey();

				if (attributeName.startsWith(_EXPANDO_FIELD)) {
					value = String.valueOf(
						expandoBridge.getAttribute(
							attributeName.replace(
								_EXPANDO_FIELD, StringPool.BLANK)));
				}
				else {
					value = String.valueOf(attribute.getValue());
				}

				if (Validator.isBlank(value)) {
					continue;
				}

				fieldValuesJSONArray.put(
					createJSONObject(
						model.getPrimaryKeyObj(),
						fieldPrefix.concat(attributeName), value));
			}
		}

		return fieldValuesJSONArray;
	}

	protected List<T> getModels(User user) throws Exception {
		return Collections.emptyList();
	}

	protected void updateUser(User user, JSONArray userJSONArray) {
		if (userJSONArray.length() == 0) {
			return;
		}

		JSONObject rootJSONObject = JSONFactoryUtil.createJSONObject();

		rootJSONObject.put("contactsProviderId", _CONTACTS_PROVIDER_ID);
		rootJSONObject.put("idPropertyNames", new String[] {"userId"});
		rootJSONObject.put("projectId", _PROJECT_ID);

		JSONArray contactsJSONArray = JSONFactoryUtil.createJSONArray();

		JSONObject contactJSONObject = createJSONObject(
			user.getUserId(), "userId", String.valueOf(user.getUserId()));

		userJSONArray.put(contactJSONObject);

		contactsJSONArray.put(userJSONArray);

		rootJSONObject.put("userProperties", contactsJSONArray);

		_contactsConnector.sendMessage(
			ContactsEntryProviderDestinationNames.UPDATE,
			rootJSONObject.toString());
	}

	private static final String _CONTACTS_PROVIDER_ID = System.getenv(
		"PULPO_CONTACTS_PROVIDER_ID");

	private static final String _EXPANDO_FIELD = "expando";

	private static final String _PROJECT_ID = System.getenv("PULPO_PROJECT_ID");

	private static final Log _log = LogFactoryUtil.getLog(
		ContactsModelListener.class);

	private static final Map<Serializable, PersistedModel> _persistedModels =
		new HashMap<>();

	private ContactsConnector _contactsConnector;

}