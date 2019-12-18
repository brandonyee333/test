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

import com.liferay.osb.model.AccountCustomerClp;
import com.liferay.osb.model.AccountInformationClp;
import com.liferay.osb.model.AccountProjectClp;
import com.liferay.osb.model.AccountWorkerClp;
import com.liferay.osb.model.CorpProjectClp;
import com.liferay.osb.model.CorpProjectMessageClp;
import com.liferay.osb.model.LCSSubscriptionEntryClp;
import com.liferay.osb.model.LicenseEntryClp;
import com.liferay.osb.model.LicenseKeyClp;
import com.liferay.osb.model.LicenseKeySetClp;
import com.liferay.osb.model.OfferingBundleClp;
import com.liferay.osb.model.OfferingDefinitionClp;
import com.liferay.osb.model.OfferingEntryClp;
import com.liferay.osb.model.OrderEntryClp;
import com.liferay.osb.model.PartnerEntryClp;
import com.liferay.osb.model.PartnerWorkerClp;
import com.liferay.osb.model.ProductEntryClp;
import com.liferay.osb.model.SupportResponseClp;

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

		if (oldModelClassName.equals(AccountCustomerClp.class.getName())) {
			return translateInputAccountCustomer(oldModel);
		}

		if (oldModelClassName.equals(AccountInformationClp.class.getName())) {
			return translateInputAccountInformation(oldModel);
		}

		if (oldModelClassName.equals(AccountProjectClp.class.getName())) {
			return translateInputAccountProject(oldModel);
		}

		if (oldModelClassName.equals(AccountWorkerClp.class.getName())) {
			return translateInputAccountWorker(oldModel);
		}

		if (oldModelClassName.equals(CorpProjectClp.class.getName())) {
			return translateInputCorpProject(oldModel);
		}

		if (oldModelClassName.equals(CorpProjectMessageClp.class.getName())) {
			return translateInputCorpProjectMessage(oldModel);
		}

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

		if (oldModelClassName.equals(OfferingBundleClp.class.getName())) {
			return translateInputOfferingBundle(oldModel);
		}

		if (oldModelClassName.equals(OfferingDefinitionClp.class.getName())) {
			return translateInputOfferingDefinition(oldModel);
		}

		if (oldModelClassName.equals(OfferingEntryClp.class.getName())) {
			return translateInputOfferingEntry(oldModel);
		}

		if (oldModelClassName.equals(OrderEntryClp.class.getName())) {
			return translateInputOrderEntry(oldModel);
		}

		if (oldModelClassName.equals(PartnerEntryClp.class.getName())) {
			return translateInputPartnerEntry(oldModel);
		}

		if (oldModelClassName.equals(PartnerWorkerClp.class.getName())) {
			return translateInputPartnerWorker(oldModel);
		}

		if (oldModelClassName.equals(ProductEntryClp.class.getName())) {
			return translateInputProductEntry(oldModel);
		}

		if (oldModelClassName.equals(SupportResponseClp.class.getName())) {
			return translateInputSupportResponse(oldModel);
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

	public static Object translateInputAccountCustomer(BaseModel<?> oldModel) {
		AccountCustomerClp oldClpModel = (AccountCustomerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountCustomerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountInformation(BaseModel<?> oldModel) {
		AccountInformationClp oldClpModel = (AccountInformationClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountInformationRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountProject(BaseModel<?> oldModel) {
		AccountProjectClp oldClpModel = (AccountProjectClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountProjectRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputAccountWorker(BaseModel<?> oldModel) {
		AccountWorkerClp oldClpModel = (AccountWorkerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getAccountWorkerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCorpProject(BaseModel<?> oldModel) {
		CorpProjectClp oldClpModel = (CorpProjectClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCorpProjectRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputCorpProjectMessage(BaseModel<?> oldModel) {
		CorpProjectMessageClp oldClpModel = (CorpProjectMessageClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getCorpProjectMessageRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
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

	public static Object translateInputOfferingBundle(BaseModel<?> oldModel) {
		OfferingBundleClp oldClpModel = (OfferingBundleClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOfferingBundleRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputOfferingDefinition(BaseModel<?> oldModel) {
		OfferingDefinitionClp oldClpModel = (OfferingDefinitionClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOfferingDefinitionRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputOfferingEntry(BaseModel<?> oldModel) {
		OfferingEntryClp oldClpModel = (OfferingEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOfferingEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputOrderEntry(BaseModel<?> oldModel) {
		OrderEntryClp oldClpModel = (OrderEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getOrderEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPartnerEntry(BaseModel<?> oldModel) {
		PartnerEntryClp oldClpModel = (PartnerEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPartnerEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputPartnerWorker(BaseModel<?> oldModel) {
		PartnerWorkerClp oldClpModel = (PartnerWorkerClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getPartnerWorkerRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputProductEntry(BaseModel<?> oldModel) {
		ProductEntryClp oldClpModel = (ProductEntryClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getProductEntryRemoteModel();

		newModel.setModelAttributes(oldClpModel.getModelAttributes());

		return newModel;
	}

	public static Object translateInputSupportResponse(BaseModel<?> oldModel) {
		SupportResponseClp oldClpModel = (SupportResponseClp)oldModel;

		BaseModel<?> newModel = oldClpModel.getSupportResponseRemoteModel();

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
					"com.liferay.osb.model.impl.AccountCustomerImpl")) {
			return translateOutputAccountCustomer(oldModel);
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
					"com.liferay.osb.model.impl.AccountInformationImpl")) {
			return translateOutputAccountInformation(oldModel);
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
					"com.liferay.osb.model.impl.AccountProjectImpl")) {
			return translateOutputAccountProject(oldModel);
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
					"com.liferay.osb.model.impl.AccountWorkerImpl")) {
			return translateOutputAccountWorker(oldModel);
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
					"com.liferay.osb.model.impl.CorpProjectImpl")) {
			return translateOutputCorpProject(oldModel);
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
					"com.liferay.osb.model.impl.CorpProjectMessageImpl")) {
			return translateOutputCorpProjectMessage(oldModel);
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

		if (oldModelClassName.equals(
					"com.liferay.osb.model.impl.OfferingBundleImpl")) {
			return translateOutputOfferingBundle(oldModel);
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
					"com.liferay.osb.model.impl.OfferingDefinitionImpl")) {
			return translateOutputOfferingDefinition(oldModel);
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
					"com.liferay.osb.model.impl.OfferingEntryImpl")) {
			return translateOutputOfferingEntry(oldModel);
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
					"com.liferay.osb.model.impl.OrderEntryImpl")) {
			return translateOutputOrderEntry(oldModel);
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
					"com.liferay.osb.model.impl.PartnerEntryImpl")) {
			return translateOutputPartnerEntry(oldModel);
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
					"com.liferay.osb.model.impl.PartnerWorkerImpl")) {
			return translateOutputPartnerWorker(oldModel);
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
					"com.liferay.osb.model.impl.ProductEntryImpl")) {
			return translateOutputProductEntry(oldModel);
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
					"com.liferay.osb.model.impl.SupportResponseImpl")) {
			return translateOutputSupportResponse(oldModel);
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
					"com.liferay.osb.exception.AccountProjectNameException")) {
			return new com.liferay.osb.exception.AccountProjectNameException(throwable.getMessage(),
				throwable.getCause());
		}

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
					"com.liferay.osb.exception.DuplicateOfferingBundleException")) {
			return new com.liferay.osb.exception.DuplicateOfferingBundleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateOfferingDefinitionException")) {
			return new com.liferay.osb.exception.DuplicateOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicatePartnerEntryCodeException")) {
			return new com.liferay.osb.exception.DuplicatePartnerEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicatePartnerEntryDossieraAccountKeyException")) {
			return new com.liferay.osb.exception.DuplicatePartnerEntryDossieraAccountKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.DuplicateSupportResponseException")) {
			return new com.liferay.osb.exception.DuplicateSupportResponseException(throwable.getMessage(),
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

		if (className.equals(
					"com.liferay.osb.exception.OfferingBundleNameException")) {
			return new com.liferay.osb.exception.OfferingBundleNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntryQuantityException")) {
			return new com.liferay.osb.exception.OfferingEntryQuantityException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntrySizingException")) {
			return new com.liferay.osb.exception.OfferingEntrySizingException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntryStatusException")) {
			return new com.liferay.osb.exception.OfferingEntryStatusException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OfferingEntrySupportExpiredException")) {
			return new com.liferay.osb.exception.OfferingEntrySupportExpiredException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OrderEntryActualStartDateException")) {
			return new com.liferay.osb.exception.OrderEntryActualStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.OrderEntryStartDateException")) {
			return new com.liferay.osb.exception.OrderEntryStartDateException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.PartnerEntryCodeException")) {
			return new com.liferay.osb.exception.PartnerEntryCodeException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.PartnerEntryDossieraAccountKeyException")) {
			return new com.liferay.osb.exception.PartnerEntryDossieraAccountKeyException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.PartnerEntryParentPartnerEntryException")) {
			return new com.liferay.osb.exception.PartnerEntryParentPartnerEntryException(throwable.getMessage(),
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
					"com.liferay.osb.exception.SupportResponseNameException")) {
			return new com.liferay.osb.exception.SupportResponseNameException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountCustomerException")) {
			return new com.liferay.osb.exception.NoSuchAccountCustomerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountInformationException")) {
			return new com.liferay.osb.exception.NoSuchAccountInformationException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountProjectException")) {
			return new com.liferay.osb.exception.NoSuchAccountProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchAccountWorkerException")) {
			return new com.liferay.osb.exception.NoSuchAccountWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchCorpProjectException")) {
			return new com.liferay.osb.exception.NoSuchCorpProjectException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchCorpProjectMessageException")) {
			return new com.liferay.osb.exception.NoSuchCorpProjectMessageException(throwable.getMessage(),
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

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOfferingBundleException")) {
			return new com.liferay.osb.exception.NoSuchOfferingBundleException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOfferingDefinitionException")) {
			return new com.liferay.osb.exception.NoSuchOfferingDefinitionException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOfferingEntryException")) {
			return new com.liferay.osb.exception.NoSuchOfferingEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchOrderEntryException")) {
			return new com.liferay.osb.exception.NoSuchOrderEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchPartnerEntryException")) {
			return new com.liferay.osb.exception.NoSuchPartnerEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchPartnerWorkerException")) {
			return new com.liferay.osb.exception.NoSuchPartnerWorkerException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchProductEntryException")) {
			return new com.liferay.osb.exception.NoSuchProductEntryException(throwable.getMessage(),
				throwable.getCause());
		}

		if (className.equals(
					"com.liferay.osb.exception.NoSuchSupportResponseException")) {
			return new com.liferay.osb.exception.NoSuchSupportResponseException(throwable.getMessage(),
				throwable.getCause());
		}

		return throwable;
	}

	public static Object translateOutputAccountCustomer(BaseModel<?> oldModel) {
		AccountCustomerClp newModel = new AccountCustomerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountCustomerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountInformation(
		BaseModel<?> oldModel) {
		AccountInformationClp newModel = new AccountInformationClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountInformationRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountProject(BaseModel<?> oldModel) {
		AccountProjectClp newModel = new AccountProjectClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountProjectRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputAccountWorker(BaseModel<?> oldModel) {
		AccountWorkerClp newModel = new AccountWorkerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setAccountWorkerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCorpProject(BaseModel<?> oldModel) {
		CorpProjectClp newModel = new CorpProjectClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCorpProjectRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputCorpProjectMessage(
		BaseModel<?> oldModel) {
		CorpProjectMessageClp newModel = new CorpProjectMessageClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setCorpProjectMessageRemoteModel(oldModel);

		return newModel;
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

	public static Object translateOutputOfferingBundle(BaseModel<?> oldModel) {
		OfferingBundleClp newModel = new OfferingBundleClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOfferingBundleRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputOfferingDefinition(
		BaseModel<?> oldModel) {
		OfferingDefinitionClp newModel = new OfferingDefinitionClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOfferingDefinitionRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputOfferingEntry(BaseModel<?> oldModel) {
		OfferingEntryClp newModel = new OfferingEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOfferingEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputOrderEntry(BaseModel<?> oldModel) {
		OrderEntryClp newModel = new OrderEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setOrderEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPartnerEntry(BaseModel<?> oldModel) {
		PartnerEntryClp newModel = new PartnerEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPartnerEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputPartnerWorker(BaseModel<?> oldModel) {
		PartnerWorkerClp newModel = new PartnerWorkerClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setPartnerWorkerRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputProductEntry(BaseModel<?> oldModel) {
		ProductEntryClp newModel = new ProductEntryClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setProductEntryRemoteModel(oldModel);

		return newModel;
	}

	public static Object translateOutputSupportResponse(BaseModel<?> oldModel) {
		SupportResponseClp newModel = new SupportResponseClp();

		newModel.setModelAttributes(oldModel.getModelAttributes());

		newModel.setSupportResponseRemoteModel(oldModel);

		return newModel;
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static String _servletContextName;
	private static boolean _useReflectionToTranslateThrowable = true;
}