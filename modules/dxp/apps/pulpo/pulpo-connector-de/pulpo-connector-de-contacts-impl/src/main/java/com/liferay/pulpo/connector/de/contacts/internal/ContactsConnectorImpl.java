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

package com.liferay.pulpo.connector.de.contacts.internal;

import com.liferay.lcs.messaging.MessageBusMessage;
import com.liferay.osb.lcs.messaging.LCSMessageBusService;
import com.liferay.osgi.util.ServiceTrackerFactory;
import com.liferay.portal.kernel.bean.ClassLoaderBeanHandler;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ModelListenerRegistrationUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pulpo.connector.de.contacts.ContactsConnector;
import com.liferay.pulpo.connector.de.contacts.model.ContactsModelListener;

import java.lang.reflect.InvocationHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * @author Shinn Lok
 */
@Component(
	property = {
		"json.web.service.context.name=pulpo",
		"json.web.service.context.path=pulpo"
	},
	service = ContactsConnector.class
)
@JSONWebService
public class ContactsConnectorImpl implements ContactsConnector {

	public static final String PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME =
		"PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME";

	public JSONObject getAvailableFields() throws Exception {
		JSONObject fieldsJSONObject = JSONFactoryUtil.createJSONObject();

		for (Map.Entry<String, ContactsModelListener> entry :
				_modelListeners.entrySet()) {

			ContactsModelListener modelListener = entry.getValue();

			JSONObject fieldNamesJSONObject =
				modelListener.getFieldNamesJSONObject();

			if (fieldNamesJSONObject.length() == 0) {
				continue;
			}

			fieldsJSONObject.put(entry.getKey(), fieldNamesJSONObject);
		}

		return fieldsJSONObject;
	}

	public JSONObject getContacts(final Map<String, List<String>> fields)
		throws Exception {

		JSONObject contactsJSONObject = JSONFactoryUtil.createJSONObject();

		ActionableDynamicQuery actionableDynamicQuery =
			_userLocalService.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<User>() {

				@Override
				public void performAction(User user) {
					JSONArray fieldsJSONArray =
						JSONFactoryUtil.createJSONArray();

					for (Map.Entry<String, ContactsModelListener> entry :
							_modelListeners.entrySet()) {

						ContactsModelListener modelListener = entry.getValue();

						List<String> modelFields = fields.get(entry.getKey());

						if ((modelFields == null) || modelFields.isEmpty()) {
							continue;
						}

						mergeJSONArrays(
							fieldsJSONArray,
							modelListener.getFieldValuesJSONArray(
								user, modelFields, null));
					}

					if (fieldsJSONArray.length() > 0) {
						contactsJSONObject.put(
							String.valueOf(user.getUserId()), fieldsJSONArray);
					}
				}

			});

		actionableDynamicQuery.performActions();

		return contactsJSONObject;
	}

	public Class<?> getModelClass(ContactsModelListener modelListener) {
		Class<?> clazz = modelListener.getClass();

		if (!ProxyUtil.isProxyClass(clazz)) {
			return ReflectionUtil.getGenericSuperType(clazz);
		}

		InvocationHandler invocationHandler = ProxyUtil.getInvocationHandler(
			modelListener);

		if (invocationHandler instanceof ClassLoaderBeanHandler) {
			ClassLoaderBeanHandler classLoaderBeanHandler =
				(ClassLoaderBeanHandler)invocationHandler;

			Object bean = classLoaderBeanHandler.getBean();

			clazz = bean.getClass();
		}

		return ReflectionUtil.getGenericSuperType(clazz);
	}

	@Override
	public ContactsModelListener getModelListener(String className) {
		return _modelListeners.get(className);
	}

	@Override
	public void sendMessage(String destinationName, String payload) {
		MessageBusMessage message = new MessageBusMessage();

		message.setPayload(payload);

		destinationName += "_" + _getEnvironmentUniqueName();

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Sending message %s to destination %s", payload,
					destinationName));
		}

		_lcsMessageBusService.sendMessage(destinationName, message);
	}

	@Activate
	protected void initServiceTracker(BundleContext bundleContext) {
		ServiceTrackerFactory.open(
			bundleContext, ContactsModelListener.class,
			new ServiceTrackerCustomizer
				<ContactsModelListener, ContactsModelListener>() {

				@Override
				public ContactsModelListener addingService(
					ServiceReference<ContactsModelListener> serviceReference) {

					ContactsModelListener modelListener =
						bundleContext.getService(serviceReference);

					modelListener.setContactsConnector(
						ContactsConnectorImpl.this);

					Class<?> clazz = getModelClass(modelListener);

					ContactsModelListener existingModelListener =
						_modelListeners.get(clazz.getSimpleName());

					if (existingModelListener != null) {
						ModelListenerRegistrationUtil.unregister(
							existingModelListener);
					}

					ModelListenerRegistrationUtil.unregister(modelListener);

					_modelListeners.put(clazz.getSimpleName(), modelListener);

					ModelListenerRegistrationUtil.register(modelListener);

					return modelListener;
				}

				@Override
				public void modifiedService(
					ServiceReference<ContactsModelListener> serviceReference,
					ContactsModelListener modelListener) {

					removedService(serviceReference, modelListener);

					addingService(serviceReference);
				}

				@Override
				public void removedService(
					ServiceReference<ContactsModelListener> serviceReference,
					ContactsModelListener modelListener) {

					Class<?> clazz = getModelClass(modelListener);

					String className = clazz.getSimpleName();

					_modelListeners.remove(className);

					ModelListenerRegistrationUtil.unregister(modelListener);

					bundleContext.ungetService(serviceReference);
				}

			});
	}

	protected void mergeJSONArrays(JSONArray jsonArray1, JSONArray jsonArray2) {
		Iterator<Object> iterator = jsonArray2.iterator();

		while (iterator.hasNext()) {
			Object object = iterator.next();

			jsonArray1.put(object);
		}
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private String _getEnvironmentUniqueName() {
		String environmentUniqueName = System.getenv(
			PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

		if (Validator.isNull(environmentUniqueName)) {
			throw new RuntimeException(
				String.format(
					"Environment variable %s must be set",
					PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME));
		}

		return environmentUniqueName;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContactsConnectorImpl.class);

	@Reference
	private LCSMessageBusService _lcsMessageBusService;

	private final Map<String, ContactsModelListener> _modelListeners =
		new HashMap<>();

	@Reference
	private UserLocalService _userLocalService;

}