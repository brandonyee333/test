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
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pulpo.connector.de.contacts.ContactsConnector;
import com.liferay.pulpo.connector.de.contacts.constants.ContactsEntryProviderDestinationNames;
import com.liferay.pulpo.connector.de.contacts.constants.ContactsExpandoColumnConstants;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import java.io.Serializable;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shinn Lok
 */
public abstract class ContactsModelListener<T extends BaseModel<T>>
	extends BaseModelListener<T> {

	public JSONObject getFieldNamesJSONObject() {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			Class<?> clazz = _contactsConnector.getModelClass(this);

			addFieldNamesJSONObject(jsonObject, clazz);

			Class<?>[] interfaces = clazz.getInterfaces();

			addFieldNamesJSONObject(jsonObject, interfaces[0]);

			ExpandoBridge expandoBridge =
				ExpandoBridgeFactoryUtil.getExpandoBridge(
					CompanyThreadLocal.getCompanyId(), clazz.getName());

			Enumeration<String> attributeNames =
				expandoBridge.getAttributeNames();

			while (attributeNames.hasMoreElements()) {
				String attributeName = attributeNames.nextElement();

				int attributeType = expandoBridge.getAttributeType(
					attributeName);

				jsonObject.put(
					_EXPANDO_FIELD.concat(attributeName),
					ContactsExpandoColumnConstants.getContactsTypeLabel(
						attributeType));
			}

			return jsonObject;
		}
		catch (Exception e) {
			_log.error(e, e);

			return jsonObject;
		}
	}

	public JSONArray getFieldValuesJSONArray(
		User user, List<String> fields, List<T> models) {

		Class<?> clazz = _contactsConnector.getModelClass(this);

		JSONArray fieldValuesJSONArray = JSONFactoryUtil.createJSONArray();

		if (fields.isEmpty()) {
			return fieldValuesJSONArray;
		}

		if (ListUtil.isEmpty(models)) {
			try {
				models = getModels(user);
			}
			catch (Exception e) {
				_log.error(e, e);

				return fieldValuesJSONArray;
			}
		}

		fields.addAll(getRequiredFields());

		List<String> availableFields = getAvailableFields();

		for (BaseModel model : models) {
			ExpandoBridge expandoBridge = model.getExpandoBridge();

			String fieldPrefix = clazz.getSimpleName() + StringPool.POUND;

			Map<String, Object> attributes = model.getModelAttributes();

			for (String field : fields) {
				if (!availableFields.contains(field)) {
					continue;
				}

				String value = null;

				if (field.startsWith(_EXPANDO_FIELD)) {
					value = String.valueOf(
						expandoBridge.getAttribute(
							field.replace(_EXPANDO_FIELD, StringPool.BLANK)));
				}
				else {
					value = String.valueOf(attributes.get(field));
				}

				if (Validator.isBlank(value)) {
					continue;
				}

				JSONObject fieldJSONObject = JSONFactoryUtil.createJSONObject();

				fieldJSONObject.put("id", model.getPrimaryKeyObj());
				fieldJSONObject.put("name", fieldPrefix.concat(field));
				fieldJSONObject.put("value", value);

				fieldValuesJSONArray.put(fieldJSONObject);
			}
		}

		return fieldValuesJSONArray;
	}

	public boolean isPrimary() {
		return false;
	}

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
				user, getAvailableFields(),
				Collections.singletonList((T)persistedModel));

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

		List<String> availableFields = getAvailableFields();

		PersistedModel persistedModel = _persistedModels.get(
			model.getPrimaryKeyObj());

		Class<?> clazz = model.getModelClass();

		String fieldPrefix = clazz.getSimpleName() + StringPool.POUND;

		for (Map.Entry<String, Object> entry : modelAttributes.entrySet()) {
			String key = entry.getKey();

			if (!availableFields.contains(key)) {
				continue;
			}

			String value = BeanPropertiesUtil.getString(model, entry.getKey());

			if (value.equals(
					BeanPropertiesUtil.getString(
						persistedModel, entry.getKey()))) {

				continue;
			}

			JSONObject fieldJSONObject = JSONFactoryUtil.createJSONObject();

			fieldJSONObject.put("id", model.getPrimaryKeyObj());
			fieldJSONObject.put("name", fieldPrefix.concat(key));
			fieldJSONObject.put("value", value);

			fieldValuesJSONArray.put(fieldJSONObject);
		}

		onAfterUpdate(model, fieldValuesJSONArray);
	}

	public void onAfterUpdate(T model, JSONArray jsonArray) {
		if (model instanceof User) {
			updateUser((User)model, jsonArray);
		}
		else {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("contactsProviderId", "test");
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

	protected void addFieldNamesJSONObject(
			JSONObject jsonObject, Class<?> clazz)
		throws Exception {

		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

		PropertyDescriptor[] propertyDescriptors =
			beanInfo.getPropertyDescriptors();

		List<String> availableFields = getAvailableFields();

		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String name = propertyDescriptor.getName();

			if (!availableFields.contains(name)) {
				continue;
			}

			Class<?> propertyTypeClass = propertyDescriptor.getPropertyType();

			String simpleName = propertyTypeClass.getSimpleName();

			if (StringUtil.equalsIgnoreCase(name, simpleName)) {
				simpleName = ContactsExpandoColumnConstants.STRING_LABEL;
			}

			jsonObject.put(name, simpleName);
		}
	}

	protected List<String> getAvailableFields() {
		return Collections.emptyList();
	}

	protected List<T> getModels(User user) throws Exception {
		return Collections.emptyList();
	}

	protected List<String> getRequiredFields() {
		return Collections.emptyList();
	}

	protected void updateUser(User user, JSONArray userJSONArray) {
		if (userJSONArray.length() == 0) {
			return;
		}

		JSONObject rootJSONObject = JSONFactoryUtil.createJSONObject();

		rootJSONObject.put("contactsProviderId", "test");
		rootJSONObject.put("idPropertyNames", new String[] {"userId"});

		JSONArray contactsJSONArray = JSONFactoryUtil.createJSONArray();

		JSONObject contactJSONObject = JSONFactoryUtil.createJSONObject();

		contactJSONObject.put("id", user.getUserId());
		contactJSONObject.put("name", "userId");
		contactJSONObject.put("value", user.getUserId());

		userJSONArray.put(contactJSONObject);

		contactsJSONArray.put(userJSONArray);

		rootJSONObject.put("userProperties", contactsJSONArray);

		_contactsConnector.sendMessage(
			ContactsEntryProviderDestinationNames.UPDATE,
			rootJSONObject.toString());
	}

	private static final String _EXPANDO_FIELD = "expando";

	private static final Log _log = LogFactoryUtil.getLog(
		ContactsModelListener.class);

	private static final Map<Serializable, PersistedModel> _persistedModels =
		new HashMap<>();

	private ContactsConnector _contactsConnector;

}