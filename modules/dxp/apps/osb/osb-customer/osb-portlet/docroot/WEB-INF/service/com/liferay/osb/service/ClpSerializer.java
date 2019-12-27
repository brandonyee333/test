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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.LCSSubscriptionEntryClp;
import com.liferay.osb.model.LicenseEntryClp;
import com.liferay.osb.model.LicenseKeyClp;
import com.liferay.osb.model.LicenseKeySetClp;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.ClassLoaderObjectInputStream;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @generated
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
						"osb-portlet-deployment-context");

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
							"osb-portlet-deployment-context");

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
				_servletContextName = "osb-portlet";
			}

			return _servletContextName;
		}
	}

	public static Object translateInput(BaseModel<?> oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(LCSSubscriptionEntryClp.class.getName())) {
			return translateInputLCSSubscriptionEntry(oldModel);
		}

		if (oldModelClassName.equals(LicenseEntryClp.class.getName())) {
			return translateInputLicenseEntry(oldModel);
		}

		if (oldModelClassName.equals(LicenseKeyClp.class.getName())) {
			return translateInputLicenseKey(oldModel);
		}

		if (oldModelClassName.equals(LicenseKeySetClp.class.getName())) {
			return translateInputLicenseKeySet(oldModel);
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

	public static Object translateInputLCSSubscriptionEntry(
		BaseModel<?> oldModel) {
		LCSSubscriptionEntryClp oldClpModel = (LCSSubscriptionEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLCSSubscriptionEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputLicenseEntry(BaseModel<?> oldModel) {
		LicenseEntryClp oldClpModel = (LicenseEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLicenseEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputLicenseKey(BaseModel<?> oldModel) {
		LicenseKeyClp oldClpModel = (LicenseKeyClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLicenseKeyRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputLicenseKeySet(BaseModel<?> oldModel) {
		LicenseKeySetClp oldClpModel = (LicenseKeySetClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getLicenseKeySetRemoteModel();

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
					"com.liferay.osb.model.impl.LCSSubscriptionEntryImpl")) {
			return translateOutputLCSSubscriptionEntry(oldModel);
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
					"com.liferay.osb.model.impl.LicenseEntryImpl")) {
			return translateOutputLicenseEntry(oldModel);
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
					"com.liferay.osb.model.impl.LicenseKeyImpl")) {
			return translateOutputLicenseKey(oldModel);
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
					"com.liferay.osb.model.impl.LicenseKeySetImpl")) {
			return translateOutputLicenseKeySet(oldModel);
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
					"com.liferay.osb.exception.DuplicateHostNameException")) {
			return new com.liferay.osb.exception.DuplicateHostNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateIPAddressException")) {
			return new com.liferay.osb.exception.DuplicateIPAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateMACAddressException")) {
			return new com.liferay.osb.exception.DuplicateMACAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseEntryNameException")) {
			return new com.liferay.osb.exception.LicenseEntryNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseEntryVersionException")) {
			return new com.liferay.osb.exception.LicenseEntryVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyActiveException")) {
			return new com.liferay.osb.exception.LicenseKeyActiveException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyDescriptionException")) {
			return new com.liferay.osb.exception.LicenseKeyDescriptionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyHostNameException")) {
			return new com.liferay.osb.exception.LicenseKeyHostNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyIPAddressException")) {
			return new com.liferay.osb.exception.LicenseKeyIPAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyMACAddressException")) {
			return new com.liferay.osb.exception.LicenseKeyMACAddressException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyMaxServersException")) {
			return new com.liferay.osb.exception.LicenseKeyMaxServersException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyOwnerException")) {
			return new com.liferay.osb.exception.LicenseKeyOwnerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyProductEntryException")) {
			return new com.liferay.osb.exception.LicenseKeyProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyProductVersionException")) {
			return new com.liferay.osb.exception.LicenseKeyProductVersionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyRegistrationException")) {
			return new com.liferay.osb.exception.LicenseKeyRegistrationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyRenewException")) {
			return new com.liferay.osb.exception.LicenseKeyRenewException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyServerIdException")) {
			return new com.liferay.osb.exception.LicenseKeyServerIdException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeyServerInfoException")) {
			return new com.liferay.osb.exception.LicenseKeyServerInfoException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.LicenseKeySetNameException")) {
			return new com.liferay.osb.exception.LicenseKeySetNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.MaximumLicenseKeyException")) {
			return new com.liferay.osb.exception.MaximumLicenseKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals("com.liferay.osb.exception.RemoteServiceException")) {
			return new com.liferay.osb.exception.RemoteServiceException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredOfferingDefinitionException")) {
			return new com.liferay.osb.exception.RequiredOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredOfferingEntryException")) {
			return new com.liferay.osb.exception.RequiredOfferingEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredPartnerEntryException")) {
			return new com.liferay.osb.exception.RequiredPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredProductEntryException")) {
			return new com.liferay.osb.exception.RequiredProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.RequiredSupportResponseException")) {
			return new com.liferay.osb.exception.RequiredSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException")) {
			return new com.liferay.osb.exception.NoSuchLCSSubscriptionEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLicenseEntryException")) {
			return new com.liferay.osb.exception.NoSuchLicenseEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLicenseKeyException")) {
			return new com.liferay.osb.exception.NoSuchLicenseKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchLicenseKeySetException")) {
			return new com.liferay.osb.exception.NoSuchLicenseKeySetException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputLCSSubscriptionEntry(
		BaseModel<?> oldModel) {
		LCSSubscriptionEntryClp newModel = new LCSSubscriptionEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLCSSubscriptionEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputLicenseEntry(BaseModel<?> oldModel) {
		LicenseEntryClp newModel = new LicenseEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLicenseEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputLicenseKey(BaseModel<?> oldModel) {
		LicenseKeyClp newModel = new LicenseKeyClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLicenseKeyRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputLicenseKeySet(BaseModel<?> oldModel) {
		LicenseKeySetClp newModel = new LicenseKeySetClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setLicenseKeySetRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}