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
import com.liferay.osb.service.FeedbackEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
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
public class FeedbackEntryClp extends BaseModelImpl<FeedbackEntry>
	implements FeedbackEntry {
	public FeedbackEntryClp() {
	}

	public Class<?> getModelClass() {
		return FeedbackEntry.class;
	}

	public String getModelClassName() {
		return FeedbackEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _feedbackEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setFeedbackEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_feedbackEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("feedbackEntryId", getFeedbackEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("satisfied", getSatisfied());
		attributes.put("comments", getComments());
		attributes.put("pageURL", getPageURL());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long feedbackEntryId = (Long)attributes.get("feedbackEntryId");

		if (feedbackEntryId != null) {
			setFeedbackEntryId(feedbackEntryId);
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

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer satisfied = (Integer)attributes.get("satisfied");

		if (satisfied != null) {
			setSatisfied(satisfied);
		}

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}

		String pageURL = (String)attributes.get("pageURL");

		if (pageURL != null) {
			setPageURL(pageURL);
		}
	}

	public long getFeedbackEntryId() {
		return _feedbackEntryId;
	}

	public void setFeedbackEntryId(long feedbackEntryId) {
		_feedbackEntryId = feedbackEntryId;

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setFeedbackEntryId", long.class);

				method.invoke(_feedbackEntryRemoteModel, feedbackEntryId);
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

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_feedbackEntryRemoteModel, userId);
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

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_feedbackEntryRemoteModel, userName);
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

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_feedbackEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassNameId", long.class);

				method.invoke(_feedbackEntryRemoteModel, classNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setClassPK", long.class);

				method.invoke(_feedbackEntryRemoteModel, classPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public int getSatisfied() {
		return _satisfied;
	}

	public void setSatisfied(int satisfied) {
		_satisfied = satisfied;

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSatisfied", int.class);

				method.invoke(_feedbackEntryRemoteModel, satisfied);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setComments", String.class);

				method.invoke(_feedbackEntryRemoteModel, comments);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public String getPageURL() {
		return _pageURL;
	}

	public void setPageURL(String pageURL) {
		_pageURL = pageURL;

		if (_feedbackEntryRemoteModel != null) {
			try {
				Class<?> clazz = _feedbackEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setPageURL", String.class);

				method.invoke(_feedbackEntryRemoteModel, pageURL);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getFeedbackEntryRemoteModel() {
		return _feedbackEntryRemoteModel;
	}

	public void setFeedbackEntryRemoteModel(
		BaseModel<?> feedbackEntryRemoteModel) {
		_feedbackEntryRemoteModel = feedbackEntryRemoteModel;
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

		Class<?> remoteModelClass = _feedbackEntryRemoteModel.getClass();

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

		Object returnValue = method.invoke(_feedbackEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			FeedbackEntryLocalServiceUtil.addFeedbackEntry(this);
		}
		else {
			FeedbackEntryLocalServiceUtil.updateFeedbackEntry(this);
		}
	}

	@Override
	public FeedbackEntry toEscapedModel() {
		return (FeedbackEntry)ProxyUtil.newProxyInstance(FeedbackEntry.class.getClassLoader(),
			new Class[] { FeedbackEntry.class }, new AutoEscapeBeanHandler(this));
	}

	public FeedbackEntry toUnescapedModel() {
		return this;
	}

	@Override
	public Object clone() {
		FeedbackEntryClp clone = new FeedbackEntryClp();

		clone.setFeedbackEntryId(getFeedbackEntryId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setClassNameId(getClassNameId());
		clone.setClassPK(getClassPK());
		clone.setSatisfied(getSatisfied());
		clone.setComments(getComments());
		clone.setPageURL(getPageURL());

		return clone;
	}

	public int compareTo(FeedbackEntry feedbackEntry) {
		long primaryKey = feedbackEntry.getPrimaryKey();

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

		if (!(obj instanceof FeedbackEntryClp)) {
			return false;
		}

		FeedbackEntryClp feedbackEntry = (FeedbackEntryClp)obj;

		long primaryKey = feedbackEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(19);

		sb.append("{feedbackEntryId=");
		sb.append(getFeedbackEntryId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", satisfied=");
		sb.append(getSatisfied());
		sb.append(", comments=");
		sb.append(getComments());
		sb.append(", pageURL=");
		sb.append(getPageURL());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(31);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.FeedbackEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>feedbackEntryId</column-name><column-value><![CDATA[");
		sb.append(getFeedbackEntryId());
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
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>satisfied</column-name><column-value><![CDATA[");
		sb.append(getSatisfied());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comments</column-name><column-value><![CDATA[");
		sb.append(getComments());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>pageURL</column-name><column-value><![CDATA[");
		sb.append(getPageURL());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _feedbackEntryId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private int _satisfied;
	private String _comments;
	private String _pageURL;
	private BaseModel<?> _feedbackEntryRemoteModel;
}