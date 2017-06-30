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

package com.liferay.osb.model;

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.TrainingEventLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingEventClp extends BaseModelImpl<TrainingEvent>
	implements TrainingEvent {
	public TrainingEventClp() {
	}

	public Class<?> getModelClass() {
		return TrainingEvent.class;
	}

	public String getModelClassName() {
		return TrainingEvent.class.getName();
	}

	public long getPrimaryKey() {
		return _trainingEventId;
	}

	public void setPrimaryKey(long primaryKey) {
		setTrainingEventId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_trainingEventId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingEventId", getTrainingEventId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("DDLRecordSetId", getDDLRecordSetId());
		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("trainingCertificateTemplateId",
			getTrainingCertificateTemplateId());
		attributes.put("trainingCourseId", getTrainingCourseId());
		attributes.put("trainingLocationId", getTrainingLocationId());
		attributes.put("name", getName());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("portalMinorVersion", getPortalMinorVersion());
		attributes.put("type", getType());
		attributes.put("languageId", getLanguageId());
		attributes.put("localizedSlides", getLocalizedSlides());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("addressId", getAddressId());
		attributes.put("maxCustomers", getMaxCustomers());
		attributes.put("enrollmentURL", getEnrollmentURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingEventId = (Long)attributes.get("trainingEventId");

		if (trainingEventId != null) {
			setTrainingEventId(trainingEventId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long DDLRecordSetId = (Long)attributes.get("DDLRecordSetId");

		if (DDLRecordSetId != null) {
			setDDLRecordSetId(DDLRecordSetId);
		}

		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
		}

		Long trainingCertificateTemplateId = (Long)attributes.get(
				"trainingCertificateTemplateId");

		if (trainingCertificateTemplateId != null) {
			setTrainingCertificateTemplateId(trainingCertificateTemplateId);
		}

		Long trainingCourseId = (Long)attributes.get("trainingCourseId");

		if (trainingCourseId != null) {
			setTrainingCourseId(trainingCourseId);
		}

		Long trainingLocationId = (Long)attributes.get("trainingLocationId");

		if (trainingLocationId != null) {
			setTrainingLocationId(trainingLocationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Integer portalMinorVersion = (Integer)attributes.get(
				"portalMinorVersion");

		if (portalMinorVersion != null) {
			setPortalMinorVersion(portalMinorVersion);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Boolean localizedSlides = (Boolean)attributes.get("localizedSlides");

		if (localizedSlides != null) {
			setLocalizedSlides(localizedSlides);
		}

		String timeZoneId = (String)attributes.get("timeZoneId");

		if (timeZoneId != null) {
			setTimeZoneId(timeZoneId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		Integer maxCustomers = (Integer)attributes.get("maxCustomers");

		if (maxCustomers != null) {
			setMaxCustomers(maxCustomers);
		}

		String enrollmentURL = (String)attributes.get("enrollmentURL");

		if (enrollmentURL != null) {
			setEnrollmentURL(enrollmentURL);
		}
	}

	public long getTrainingEventId() {
		return _trainingEventId;
	}

	public void setTrainingEventId(long trainingEventId) {
		_trainingEventId = trainingEventId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingEventId", long.class);

				method.invoke(_trainingEventRemoteModel, trainingEventId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_trainingEventRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_trainingEventRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_trainingEventRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_trainingEventRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getDDLRecordSetId() {
		return _DDLRecordSetId;
	}

	public void setDDLRecordSetId(long DDLRecordSetId) {
		_DDLRecordSetId = DDLRecordSetId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setDDLRecordSetId", long.class);

				method.invoke(_trainingEventRemoteModel, DDLRecordSetId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setPartnerEntryId", long.class);

				method.invoke(_trainingEventRemoteModel, partnerEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingCertificateTemplateId() {
		return _trainingCertificateTemplateId;
	}

	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingCertificateTemplateId = trainingCertificateTemplateId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCertificateTemplateId",
						long.class);

				method.invoke(_trainingEventRemoteModel,
					trainingCertificateTemplateId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingCourseId() {
		return _trainingCourseId;
	}

	public void setTrainingCourseId(long trainingCourseId) {
		_trainingCourseId = trainingCourseId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingCourseId",
						long.class);

				method.invoke(_trainingEventRemoteModel, trainingCourseId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getTrainingLocationId() {
		return _trainingLocationId;
	}

	public void setTrainingLocationId(long trainingLocationId) {
		_trainingLocationId = trainingLocationId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setTrainingLocationId",
						long.class);

				method.invoke(_trainingEventRemoteModel, trainingLocationId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_trainingEventRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setEmailAddress", String.class);

				method.invoke(_trainingEventRemoteModel, emailAddress);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getPortalMinorVersion() {
		return _portalMinorVersion;
	}

	public void setPortalMinorVersion(int portalMinorVersion) {
		_portalMinorVersion = portalMinorVersion;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setPortalMinorVersion",
						int.class);

				method.invoke(_trainingEventRemoteModel, portalMinorVersion);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_trainingEventRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setLanguageId", String.class);

				method.invoke(_trainingEventRemoteModel, languageId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public boolean getLocalizedSlides() {
		return _localizedSlides;
	}

	public boolean isLocalizedSlides() {
		return _localizedSlides;
	}

	public void setLocalizedSlides(boolean localizedSlides) {
		_localizedSlides = localizedSlides;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setLocalizedSlides",
						boolean.class);

				method.invoke(_trainingEventRemoteModel, localizedSlides);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setTimeZoneId", String.class);

				method.invoke(_trainingEventRemoteModel, timeZoneId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_trainingEventRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setEndDate", Date.class);

				method.invoke(_trainingEventRemoteModel, endDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setAddressId", long.class);

				method.invoke(_trainingEventRemoteModel, addressId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getMaxCustomers() {
		return _maxCustomers;
	}

	public void setMaxCustomers(int maxCustomers) {
		_maxCustomers = maxCustomers;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setMaxCustomers", int.class);

				method.invoke(_trainingEventRemoteModel, maxCustomers);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getEnrollmentURL() {
		return _enrollmentURL;
	}

	public void setEnrollmentURL(String enrollmentURL) {
		_enrollmentURL = enrollmentURL;

		if (_trainingEventRemoteModel != null) {
			try {
				Class<?> clazz = _trainingEventRemoteModel.getClass();

				Method method = clazz.getMethod("setEnrollmentURL", String.class);

				method.invoke(_trainingEventRemoteModel, enrollmentURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public java.lang.String getTrainingWorkerDisplayHTML() {
		try {
			String methodName = "getTrainingWorkerDisplayHTML";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getCertificateCountDisplayHTML() {
		try {
			String methodName = "getCertificateCountDisplayHTML";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public boolean isDurationSingleDay() {
		try {
			String methodName = "isDurationSingleDay";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Boolean returnObj = (Boolean)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> getTrainingCustomers() {
		try {
			String methodName = "getTrainingCustomers";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.util.List<com.liferay.osb.model.TrainingCustomer> returnObj = (java.util.List<com.liferay.osb.model.TrainingCustomer>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getAddressDisplayHTML() {
		try {
			String methodName = "getAddressDisplayHTML";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getTrainingWorkersDisplayHTML() {
		try {
			String methodName = "getTrainingWorkersDisplayHTML";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.PartnerEntry getPartnerEntry() {
		try {
			String methodName = "getPartnerEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.PartnerEntry returnObj = (com.liferay.osb.model.PartnerEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public java.lang.String getTypeLabel() {
		try {
			String methodName = "getTypeLabel";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.TrainingLocation getTrainingLocation() {
		try {
			String methodName = "getTrainingLocation";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.TrainingLocation returnObj = (com.liferay.osb.model.TrainingLocation)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.osb.model.TrainingCourse getTrainingCourse() {
		try {
			String methodName = "getTrainingCourse";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.osb.model.TrainingCourse returnObj = (com.liferay.osb.model.TrainingCourse)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public com.liferay.portal.model.Address getAddress() {
		try {
			String methodName = "getAddress";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			com.liferay.portal.model.Address returnObj = (com.liferay.portal.model.Address)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getTrainingEventRemoteModel() {
		return _trainingEventRemoteModel;
	}

	public void setTrainingEventRemoteModel(
		BaseModel<?> trainingEventRemoteModel) {
		_trainingEventRemoteModel = trainingEventRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _trainingEventRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_trainingEventRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			TrainingEventLocalServiceUtil.addTrainingEvent(this);
		}
		else {
			TrainingEventLocalServiceUtil.updateTrainingEvent(this);
		}
	}

	@Override
	public TrainingEvent toEscapedModel() {
		return (TrainingEvent)ProxyUtil.newProxyInstance(TrainingEvent.class.getClassLoader(),
			new Class[] { TrainingEvent.class }, new AutoEscapeBeanHandler(this));
	}

	public TrainingEvent toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		TrainingEventClp clone = new TrainingEventClp();

		clone.setTrainingEventId(getTrainingEventId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setDDLRecordSetId(getDDLRecordSetId());
		clone.setPartnerEntryId(getPartnerEntryId());
		clone.setTrainingCertificateTemplateId(getTrainingCertificateTemplateId());
		clone.setTrainingCourseId(getTrainingCourseId());
		clone.setTrainingLocationId(getTrainingLocationId());
		clone.setName(getName());
		clone.setEmailAddress(getEmailAddress());
		clone.setPortalMinorVersion(getPortalMinorVersion());
		clone.setType(getType());
		clone.setLanguageId(getLanguageId());
		clone.setLocalizedSlides(getLocalizedSlides());
		clone.setTimeZoneId(getTimeZoneId());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setAddressId(getAddressId());
		clone.setMaxCustomers(getMaxCustomers());
		clone.setEnrollmentURL(getEnrollmentURL());

		return clone;
	}

	public int compareTo(TrainingEvent trainingEvent) {
		int value = 0;

		value = DateUtil.compareTo(getStartDate(), trainingEvent.getStartDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingEventClp)) {
			return false;
		}

		TrainingEventClp trainingEvent = (TrainingEventClp)obj;

		long primaryKey = trainingEvent.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(45);

		sb.append("{trainingEventId=");
		sb.append(getTrainingEventId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", DDLRecordSetId=");
		sb.append(getDDLRecordSetId());
		sb.append(", partnerEntryId=");
		sb.append(getPartnerEntryId());
		sb.append(", trainingCertificateTemplateId=");
		sb.append(getTrainingCertificateTemplateId());
		sb.append(", trainingCourseId=");
		sb.append(getTrainingCourseId());
		sb.append(", trainingLocationId=");
		sb.append(getTrainingLocationId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", emailAddress=");
		sb.append(getEmailAddress());
		sb.append(", portalMinorVersion=");
		sb.append(getPortalMinorVersion());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append(", localizedSlides=");
		sb.append(getLocalizedSlides());
		sb.append(", timeZoneId=");
		sb.append(getTimeZoneId());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", addressId=");
		sb.append(getAddressId());
		sb.append(", maxCustomers=");
		sb.append(getMaxCustomers());
		sb.append(", enrollmentURL=");
		sb.append(getEnrollmentURL());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(70);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TrainingEvent");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>trainingEventId</column-name><column-value><![CDATA[");
		sb.append(getTrainingEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>DDLRecordSetId</column-name><column-value><![CDATA[");
		sb.append(getDDLRecordSetId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>partnerEntryId</column-name><column-value><![CDATA[");
		sb.append(getPartnerEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingCertificateTemplateId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCertificateTemplateId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingCourseId</column-name><column-value><![CDATA[");
		sb.append(getTrainingCourseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trainingLocationId</column-name><column-value><![CDATA[");
		sb.append(getTrainingLocationId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>emailAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>portalMinorVersion</column-name><column-value><![CDATA[");
		sb.append(getPortalMinorVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>localizedSlides</column-name><column-value><![CDATA[");
		sb.append(getLocalizedSlides());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>timeZoneId</column-name><column-value><![CDATA[");
		sb.append(getTimeZoneId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>addressId</column-name><column-value><![CDATA[");
		sb.append(getAddressId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>maxCustomers</column-name><column-value><![CDATA[");
		sb.append(getMaxCustomers());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enrollmentURL</column-name><column-value><![CDATA[");
		sb.append(getEnrollmentURL());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _trainingEventId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _DDLRecordSetId;
	private long _partnerEntryId;
	private long _trainingCertificateTemplateId;
	private long _trainingCourseId;
	private long _trainingLocationId;
	private String _name;
	private String _emailAddress;
	private int _portalMinorVersion;
	private int _type;
	private String _languageId;
	private boolean _localizedSlides;
	private String _timeZoneId;
	private Date _startDate;
	private Date _endDate;
	private long _addressId;
	private int _maxCustomers;
	private String _enrollmentURL;
	private BaseModel<?> _trainingEventRemoteModel;
}