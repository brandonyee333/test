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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.ClpSerializer;
import com.liferay.osb.service.TicketFeedbackLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @generated
 */
@ProviderType
public class TicketFeedbackClp extends BaseModelImpl<TicketFeedback>
	implements TicketFeedback {
	public TicketFeedbackClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return TicketFeedback.class;
	}

	@Override
	public String getModelClassName() {
		return TicketFeedback.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _ticketFeedbackId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTicketFeedbackId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketFeedbackId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketFeedbackId", getTicketFeedbackId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("subject", getSubject());
		attributes.put("satisfied", getSatisfied());
		attributes.put("answer1", getAnswer1());
		attributes.put("answer2", getAnswer2());
		attributes.put("answer3", getAnswer3());
		attributes.put("rating1", getRating1());
		attributes.put("rating2", getRating2());
		attributes.put("rating3", getRating3());
		attributes.put("rating4", getRating4());
		attributes.put("comments", getComments());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketFeedbackId = (Long)attributes.get("ticketFeedbackId");

		if (ticketFeedbackId != null) {
			setTicketFeedbackId(ticketFeedbackId);
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

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Integer subject = (Integer)attributes.get("subject");

		if (subject != null) {
			setSubject(subject);
		}

		Integer satisfied = (Integer)attributes.get("satisfied");

		if (satisfied != null) {
			setSatisfied(satisfied);
		}

		Integer answer1 = (Integer)attributes.get("answer1");

		if (answer1 != null) {
			setAnswer1(answer1);
		}

		Integer answer2 = (Integer)attributes.get("answer2");

		if (answer2 != null) {
			setAnswer2(answer2);
		}

		Integer answer3 = (Integer)attributes.get("answer3");

		if (answer3 != null) {
			setAnswer3(answer3);
		}

		Integer rating1 = (Integer)attributes.get("rating1");

		if (rating1 != null) {
			setRating1(rating1);
		}

		Integer rating2 = (Integer)attributes.get("rating2");

		if (rating2 != null) {
			setRating2(rating2);
		}

		Integer rating3 = (Integer)attributes.get("rating3");

		if (rating3 != null) {
			setRating3(rating3);
		}

		Integer rating4 = (Integer)attributes.get("rating4");

		if (rating4 != null) {
			setRating4(rating4);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getTicketFeedbackId() {
		return _ticketFeedbackId;
	}

	@Override
	public void setTicketFeedbackId(long ticketFeedbackId) {
		_ticketFeedbackId = ticketFeedbackId;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketFeedbackId",
						long.class);

				method.invoke(_ticketFeedbackRemoteModel, ticketFeedbackId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketFeedbackRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_ticketFeedbackRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketFeedbackRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_ticketFeedbackRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getAccountEntryId() {
		return _accountEntryId;
	}

	@Override
	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountEntryId", long.class);

				method.invoke(_ticketFeedbackRemoteModel, accountEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTicketEntryId() {
		return _ticketEntryId;
	}

	@Override
	public void setTicketEntryId(long ticketEntryId) {
		_ticketEntryId = ticketEntryId;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketFeedbackRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSubject() {
		return _subject;
	}

	@Override
	public void setSubject(int subject) {
		_subject = subject;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setSubject", int.class);

				method.invoke(_ticketFeedbackRemoteModel, subject);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSatisfied() {
		return _satisfied;
	}

	@Override
	public void setSatisfied(int satisfied) {
		_satisfied = satisfied;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setSatisfied", int.class);

				method.invoke(_ticketFeedbackRemoteModel, satisfied);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAnswer1() {
		return _answer1;
	}

	@Override
	public void setAnswer1(int answer1) {
		_answer1 = answer1;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setAnswer1", int.class);

				method.invoke(_ticketFeedbackRemoteModel, answer1);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAnswer2() {
		return _answer2;
	}

	@Override
	public void setAnswer2(int answer2) {
		_answer2 = answer2;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setAnswer2", int.class);

				method.invoke(_ticketFeedbackRemoteModel, answer2);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getAnswer3() {
		return _answer3;
	}

	@Override
	public void setAnswer3(int answer3) {
		_answer3 = answer3;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setAnswer3", int.class);

				method.invoke(_ticketFeedbackRemoteModel, answer3);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRating1() {
		return _rating1;
	}

	@Override
	public void setRating1(int rating1) {
		_rating1 = rating1;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setRating1", int.class);

				method.invoke(_ticketFeedbackRemoteModel, rating1);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRating2() {
		return _rating2;
	}

	@Override
	public void setRating2(int rating2) {
		_rating2 = rating2;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setRating2", int.class);

				method.invoke(_ticketFeedbackRemoteModel, rating2);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRating3() {
		return _rating3;
	}

	@Override
	public void setRating3(int rating3) {
		_rating3 = rating3;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setRating3", int.class);

				method.invoke(_ticketFeedbackRemoteModel, rating3);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getRating4() {
		return _rating4;
	}

	@Override
	public void setRating4(int rating4) {
		_rating4 = rating4;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setRating4", int.class);

				method.invoke(_ticketFeedbackRemoteModel, rating4);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getComments() {
		return _comments;
	}

	@Override
	public void setComments(String comments) {
		_comments = comments;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setComments", String.class);

				method.invoke(_ticketFeedbackRemoteModel, comments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_status = status;

		if (_ticketFeedbackRemoteModel != null) {
			try {
				Class<?> clazz = _ticketFeedbackRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_ticketFeedbackRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String getAnswer1Label() {
		try {
			String methodName = "getAnswer1Label";

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

	@Override
	public java.lang.String getAnswer2Label() {
		try {
			String methodName = "getAnswer2Label";

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

	@Override
	public java.lang.String getAnswer3Label() {
		try {
			String methodName = "getAnswer3Label";

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

	@Override
	public double getAverageRating() {
		try {
			String methodName = "getAverageRating";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Double returnObj = (Double)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getAverageRatingDisplay() {
		try {
			String methodName = "getAverageRatingDisplay";

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

	@Override
	public java.lang.String getRating1Label() {
		try {
			String methodName = "getRating1Label";

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

	@Override
	public java.lang.String getRating2Label() {
		try {
			String methodName = "getRating2Label";

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

	@Override
	public java.lang.String getRating3Label() {
		try {
			String methodName = "getRating3Label";

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

	@Override
	public java.lang.String getRating4Label() {
		try {
			String methodName = "getRating4Label";

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

	@Override
	public java.lang.String getSatisfiedLabel() {
		try {
			String methodName = "getSatisfiedLabel";

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

	@Override
	public TicketEntry getTicketEntry() {
		try {
			String methodName = "getTicketEntry";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			TicketEntry returnObj = (TicketEntry)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public boolean isClosed() {
		try {
			String methodName = "isClosed";

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

	public BaseModel<?> getTicketFeedbackRemoteModel() {
		return _ticketFeedbackRemoteModel;
	}

	public void setTicketFeedbackRemoteModel(
		BaseModel<?> ticketFeedbackRemoteModel) {
		_ticketFeedbackRemoteModel = ticketFeedbackRemoteModel;
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

		Class<?> remoteModelClass = _ticketFeedbackRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketFeedbackRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			TicketFeedbackLocalServiceUtil.addTicketFeedback(this);
		}
		else {
			TicketFeedbackLocalServiceUtil.updateTicketFeedback(this);
		}
	}

	@Override
	public TicketFeedback toEscapedModel() {
		return (TicketFeedback)ProxyUtil.newProxyInstance(TicketFeedback.class.getClassLoader(),
			new Class[] { TicketFeedback.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TicketFeedbackClp clone = new TicketFeedbackClp();

		clone.setTicketFeedbackId(getTicketFeedbackId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAccountEntryId(getAccountEntryId());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setSubject(getSubject());
		clone.setSatisfied(getSatisfied());
		clone.setAnswer1(getAnswer1());
		clone.setAnswer2(getAnswer2());
		clone.setAnswer3(getAnswer3());
		clone.setRating1(getRating1());
		clone.setRating2(getRating2());
		clone.setRating3(getRating3());
		clone.setRating4(getRating4());
		clone.setComments(getComments());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(TicketFeedback ticketFeedback) {
		long primaryKey = ticketFeedback.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TicketFeedbackClp)) {
			return false;
		}

		TicketFeedbackClp ticketFeedback = (TicketFeedbackClp)obj;

		long primaryKey = ticketFeedback.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{ticketFeedbackId=");
		sb.append(getTicketFeedbackId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", accountEntryId=");
		sb.append(getAccountEntryId());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", subject=");
		sb.append(getSubject());
		sb.append(", satisfied=");
		sb.append(getSatisfied());
		sb.append(", answer1=");
		sb.append(getAnswer1());
		sb.append(", answer2=");
		sb.append(getAnswer2());
		sb.append(", answer3=");
		sb.append(getAnswer3());
		sb.append(", rating1=");
		sb.append(getRating1());
		sb.append(", rating2=");
		sb.append(getRating2());
		sb.append(", rating3=");
		sb.append(getRating3());
		sb.append(", rating4=");
		sb.append(getRating4());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(58);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketFeedback");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketFeedbackId</column-name><column-value><![CDATA[");
		sb.append(getTicketFeedbackId());
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
			"<column><column-name>accountEntryId</column-name><column-value><![CDATA[");
		sb.append(getAccountEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>subject</column-name><column-value><![CDATA[");
		sb.append(getSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>satisfied</column-name><column-value><![CDATA[");
		sb.append(getSatisfied());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>answer1</column-name><column-value><![CDATA[");
		sb.append(getAnswer1());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>answer2</column-name><column-value><![CDATA[");
		sb.append(getAnswer2());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>answer3</column-name><column-value><![CDATA[");
		sb.append(getAnswer3());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rating1</column-name><column-value><![CDATA[");
		sb.append(getRating1());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rating2</column-name><column-value><![CDATA[");
		sb.append(getRating2());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rating3</column-name><column-value><![CDATA[");
		sb.append(getRating3());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rating4</column-name><column-value><![CDATA[");
		sb.append(getRating4());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketFeedbackId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _accountEntryId;
	private long _ticketEntryId;
	private int _subject;
	private int _satisfied;
	private int _answer1;
	private int _answer2;
	private int _answer3;
	private int _rating1;
	private int _rating2;
	private int _rating3;
	private int _rating4;
	private String _comments;
	private int _status;
	private BaseModel<?> _ticketFeedbackRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}