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

package com.liferay.watson.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import com.liferay.watson.model.WatsonActivityClp;
import com.liferay.watson.model.WatsonAddressClp;
import com.liferay.watson.model.WatsonHistoryClp;
import com.liferay.watson.model.WatsonIncidentClp;
import com.liferay.watson.model.WatsonIncidentRelClp;
import com.liferay.watson.model.WatsonListTypeClp;
import com.liferay.watson.model.WatsonListTypeRelClp;
import com.liferay.watson.model.WatsonPersonClp;
import com.liferay.watson.model.WatsonRelationshipClp;
import com.liferay.watson.model.WatsonResourceClp;
import com.liferay.watson.model.WatsonVehicleClp;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eddie Olson
 */
@ProviderType
public class ClpSerializer {
	public static String getServletContextName() {
		if (Validator.isNotNull(_servletContextName)) {
			return _servletContextName;
		}

		synchronized (ClpSerializer.class) {
			if (Validator.isNotNull(_servletContextName)) {
				return _servletContextName;
			}

			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Class<?> portletPropsClass = classLoader.loadClass(
						"com.liferay.util.portlet.PortletProps");

				Method getMethod = portletPropsClass.getMethod("get",
						new Class<?>[] { String.class });

				String portletPropsServletContextName = (String)getMethod.invoke(null,
						"watson-portlet-deployment-context");

				if (Validator.isNotNull(portletPropsServletContextName)) {
					_servletContextName = portletPropsServletContextName;
				}
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info(
						"Unable to locate deployment context from portlet properties");
				}
			}

			if (Validator.isNull(_servletContextName)) {
				try {
					String propsUtilServletContextName = PropsUtil.get(
							"watson-portlet-deployment-context");

					if (Validator.isNotNull(propsUtilServletContextName)) {
						_servletContextName = propsUtilServletContextName;
					}
				}
				catch (Throwable t) {
					if (_log.isInfoEnabled()) {
						_log.info(
							"Unable to locate deployment context from portal properties");
					}
				}
			}

			if (Validator.isNull(_servletContextName)) {
				_servletContextName = "watson-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(WatsonActivityClp.class.getName())) {
			return translateInputWatsonActivity(oldModel);
		}

		if (oldModelClassName.equals(WatsonAddressClp.class.getName())) {
			return translateInputWatsonAddress(oldModel);
		}

		if (oldModelClassName.equals(WatsonHistoryClp.class.getName())) {
			return translateInputWatsonHistory(oldModel);
		}

		if (oldModelClassName.equals(WatsonIncidentClp.class.getName())) {
			return translateInputWatsonIncident(oldModel);
		}

		if (oldModelClassName.equals(WatsonIncidentRelClp.class.getName())) {
			return translateInputWatsonIncidentRel(oldModel);
		}

		if (oldModelClassName.equals(WatsonListTypeClp.class.getName())) {
			return translateInputWatsonListType(oldModel);
		}

		if (oldModelClassName.equals(WatsonListTypeRelClp.class.getName())) {
			return translateInputWatsonListTypeRel(oldModel);
		}

		if (oldModelClassName.equals(WatsonPersonClp.class.getName())) {
			return translateInputWatsonPerson(oldModel);
		}

		if (oldModelClassName.equals(WatsonRelationshipClp.class.getName())) {
			return translateInputWatsonRelationship(oldModel);
		}

		if (oldModelClassName.equals(WatsonResourceClp.class.getName())) {
			return translateInputWatsonResource(oldModel);
		}

		if (oldModelClassName.equals(WatsonVehicleClp.class.getName())) {
			return translateInputWatsonVehicle(oldModel);
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInputWatsonActivity(BaseModel<?> oldModel) {
		WatsonActivityClp oldClpModel = (WatsonActivityClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonActivityRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonAddress(BaseModel<?> oldModel) {
		WatsonAddressClp oldClpModel = (WatsonAddressClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonAddressRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonHistory(BaseModel<?> oldModel) {
		WatsonHistoryClp oldClpModel = (WatsonHistoryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonHistoryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonIncident(BaseModel<?> oldModel) {
		WatsonIncidentClp oldClpModel = (WatsonIncidentClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonIncidentRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonIncidentRel(BaseModel<?> oldModel) {
		WatsonIncidentRelClp oldClpModel = (WatsonIncidentRelClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonIncidentRelRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonListType(BaseModel<?> oldModel) {
		WatsonListTypeClp oldClpModel = (WatsonListTypeClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonListTypeRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonListTypeRel(BaseModel<?> oldModel) {
		WatsonListTypeRelClp oldClpModel = (WatsonListTypeRelClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonListTypeRelRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonPerson(BaseModel<?> oldModel) {
		WatsonPersonClp oldClpModel = (WatsonPersonClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonPersonRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonRelationship(BaseModel<?> oldModel) {
		WatsonRelationshipClp oldClpModel = (WatsonRelationshipClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonRelationshipRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonResource(BaseModel<?> oldModel) {
		WatsonResourceClp oldClpModel = (WatsonResourceClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonResourceRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputWatsonVehicle(BaseModel<?> oldModel) {
		WatsonVehicleClp oldClpModel = (WatsonVehicleClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getWatsonVehicleRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateInput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonActivityImpl")) {
			return translateOutputWatsonActivity(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonAddressImpl")) {
			return translateOutputWatsonAddress(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonHistoryImpl")) {
			return translateOutputWatsonHistory(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonIncidentImpl")) {
			return translateOutputWatsonIncident(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonIncidentRelImpl")) {
			return translateOutputWatsonIncidentRel(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonListTypeImpl")) {
			return translateOutputWatsonListType(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonListTypeRelImpl")) {
			return translateOutputWatsonListTypeRel(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonPersonImpl")) {
			return translateOutputWatsonPerson(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonRelationshipImpl")) {
			return translateOutputWatsonRelationship(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonResourceImpl")) {
			return translateOutputWatsonResource(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		if (oldModelClassName.equals(
					"com.liferay.watson.model.impl.WatsonVehicleImpl")) {
			return translateOutputWatsonVehicle(oldModel);
		}
		else if (oldModelClassName.endsWith("Clp")) {
			try {
				ClassLoader classLoader = ClpSerializer.class.getClassLoader();

				Method getClpSerializerClassMethod = oldModelClass.getMethod(
						"getClpSerializerClass");

				Class<?> oldClpSerializerClass = (Class<?>)getClpSerializerClassMethod.invoke(oldModel);

				Class<?> newClpSerializerClass = classLoader.loadClass(oldClpSerializerClass.getName());

				Method translateOutputMethod = newClpSerializerClass.getMethod("translateOutput",
						BaseModel.class);

				Class<?> oldModelModelClass = oldModel.getModelClass();

				Method getRemoteModelMethod = oldModelClass.getMethod("get" +
						oldModelModelClass.getSimpleName() + "RemoteModel");

				Object oldRemoteModel = getRemoteModelMethod.invoke(oldModel);

				BaseModel<?> newModel = (BaseModel<?>)translateOutputMethod.invoke(null,
						oldRemoteModel);

				return newModel;
			}
			catch (Throwable t) {
				if (_log.isInfoEnabled()) {
					_log.info("Unable to translate " + oldModelClassName, t);
				}
			}
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel<?>) {
			return translateOutput((BaseModel<?>)obj);
		}
		else if (obj instanceof List<?>) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Throwable translateThrowable(Throwable throwable) {
		if (_useReflectionToTranslateThrowable) {
			ObjectInputStream objectInputStream = null;
			ObjectOutputStream objectOutputStream = null;

			try {
				UnsyncByteArrayOutputStream unsyncByteArrayOutputStream = new UnsyncByteArrayOutputStream();
				objectOutputStream = new ObjectOutputStream(unsyncByteArrayOutputStream);

				objectOutputStream.writeObject(throwable);

				objectOutputStream.flush();

				UnsyncByteArrayInputStream unsyncByteArrayInputStream = new UnsyncByteArrayInputStream(unsyncByteArrayOutputStream.unsafeGetByteArray(),
						0, unsyncByteArrayOutputStream.size());

				Thread currentThread = Thread.currentThread();

				ClassLoader contextClassLoader = currentThread.getContextClassLoader();

				objectInputStream = new ClassLoaderObjectInputStream(unsyncByteArrayInputStream,
						contextClassLoader);

				throwable = (Throwable)objectInputStream.readObject();

				return throwable;
			}
			catch (ClassNotFoundException cnfe) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (SecurityException se) {
				if (_log.isInfoEnabled()) {
					_log.info("Do not use reflection to translate throwable");
				}

				_useReflectionToTranslateThrowable = false;
			}
			catch (Throwable throwable2) {
				_log.error(throwable2, throwable2);

				return throwable2;
			}
			finally {
				if (objectOutputStream != null) {
					try {
						objectOutputStream.close();
					}
					catch (Throwable throwable2) {
						_log.error(throwable2, throwable2);

						return throwable2;
					}
				}

				if (objectInputStream != null) {
					try {
						objectInputStream.close();
					}
					catch (Throwable throwable2) {
						_log.error(throwable2, throwable2);

						return throwable2;
					}
				}
			}
		}

		Class<?> clazz = throwable.getClass();

		String className = clazz.getName();

		if (className.equals(
					"com.liferay.watson.exception.NoSuchActivityException")) {
			return new com.liferay.watson.exception.NoSuchActivityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchAddressException")) {
			return new com.liferay.watson.exception.NoSuchAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchHistoryException")) {
			return new com.liferay.watson.exception.NoSuchHistoryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchIncidentException")) {
			return new com.liferay.watson.exception.NoSuchIncidentException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchIncidentRelException")) {
			return new com.liferay.watson.exception.NoSuchIncidentRelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchListTypeException")) {
			return new com.liferay.watson.exception.NoSuchListTypeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchListTypeRelException")) {
			return new com.liferay.watson.exception.NoSuchListTypeRelException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchPersonException")) {
			return new com.liferay.watson.exception.NoSuchPersonException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchRelationshipException")) {
			return new com.liferay.watson.exception.NoSuchRelationshipException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchResourceException")) {
			return new com.liferay.watson.exception.NoSuchResourceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.watson.exception.NoSuchVehicleException")) {
			return new com.liferay.watson.exception.NoSuchVehicleException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputWatsonActivity(BaseModel<?> oldModel) {
		WatsonActivityClp newModel = new WatsonActivityClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonActivityRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonAddress(BaseModel<?> oldModel) {
		WatsonAddressClp newModel = new WatsonAddressClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonAddressRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonHistory(BaseModel<?> oldModel) {
		WatsonHistoryClp newModel = new WatsonHistoryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonHistoryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonIncident(BaseModel<?> oldModel) {
		WatsonIncidentClp newModel = new WatsonIncidentClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonIncidentRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonIncidentRel(BaseModel<?> oldModel) {
		WatsonIncidentRelClp newModel = new WatsonIncidentRelClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonIncidentRelRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonListType(BaseModel<?> oldModel) {
		WatsonListTypeClp newModel = new WatsonListTypeClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonListTypeRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonListTypeRel(BaseModel<?> oldModel) {
		WatsonListTypeRelClp newModel = new WatsonListTypeRelClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonListTypeRelRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonPerson(BaseModel<?> oldModel) {
		WatsonPersonClp newModel = new WatsonPersonClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonPersonRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonRelationship(
		BaseModel<?> oldModel) {
		WatsonRelationshipClp newModel = new WatsonRelationshipClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonRelationshipRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonResource(BaseModel<?> oldModel) {
		WatsonResourceClp newModel = new WatsonResourceClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonResourceRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputWatsonVehicle(BaseModel<?> oldModel) {
		WatsonVehicleClp newModel = new WatsonVehicleClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setWatsonVehicleRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}