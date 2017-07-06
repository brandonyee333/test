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
import com.liferay.osb.service.TicketAttachmentLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@ProviderType
public class TicketAttachmentClp extends BaseModelImpl<TicketAttachment>
	implements TicketAttachment {
	public TicketAttachmentClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return TicketAttachment.class;
	}

	@Override
	public String getModelClassName() {
		return TicketAttachment.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _ticketAttachmentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setTicketAttachmentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _ticketAttachmentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("ticketAttachmentId", getTicketAttachmentId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("ticketEntryId", getTicketEntryId());
		attributes.put("ticketSolutionId", getTicketSolutionId());
		attributes.put("releaseNotesId", getReleaseNotesId());
		attributes.put("fileName", getFileName());
		attributes.put("fileSize", getFileSize());
		attributes.put("type", getType());
		attributes.put("visibility", getVisibility());
		attributes.put("extractedText", getExtractedText());
		attributes.put("availableFileRepositoryIds",
			getAvailableFileRepositoryIds());
		attributes.put("replicate", getReplicate());
		attributes.put("deleteDate", getDeleteDate());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long ticketAttachmentId = (Long)attributes.get("ticketAttachmentId");

		if (ticketAttachmentId != null) {
			setTicketAttachmentId(ticketAttachmentId);
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

		Long ticketEntryId = (Long)attributes.get("ticketEntryId");

		if (ticketEntryId != null) {
			setTicketEntryId(ticketEntryId);
		}

		Long ticketSolutionId = (Long)attributes.get("ticketSolutionId");

		if (ticketSolutionId != null) {
			setTicketSolutionId(ticketSolutionId);
		}

		Long releaseNotesId = (Long)attributes.get("releaseNotesId");

		if (releaseNotesId != null) {
			setReleaseNotesId(releaseNotesId);
		}

		String fileName = (String)attributes.get("fileName");

		if (fileName != null) {
			setFileName(fileName);
		}

		Long fileSize = (Long)attributes.get("fileSize");

		if (fileSize != null) {
			setFileSize(fileSize);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer visibility = (Integer)attributes.get("visibility");

		if (visibility != null) {
			setVisibility(visibility);
		}

		String extractedText = (String)attributes.get("extractedText");

		if (extractedText != null) {
			setExtractedText(extractedText);
		}

		String availableFileRepositoryIds = (String)attributes.get(
				"availableFileRepositoryIds");

		if (availableFileRepositoryIds != null) {
			setAvailableFileRepositoryIds(availableFileRepositoryIds);
		}

		Boolean replicate = (Boolean)attributes.get("replicate");

		if (replicate != null) {
			setReplicate(replicate);
		}

		Date deleteDate = (Date)attributes.get("deleteDate");

		if (deleteDate != null) {
			setDeleteDate(deleteDate);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getTicketAttachmentId() {
		return _ticketAttachmentId;
	}

	@Override
	public void setTicketAttachmentId(long ticketAttachmentId) {
		_ticketAttachmentId = ticketAttachmentId;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketAttachmentId",
						long.class);

				method.invoke(_ticketAttachmentRemoteModel, ticketAttachmentId);
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

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_ticketAttachmentRemoteModel, userId);
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

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_ticketAttachmentRemoteModel, userName);
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

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_ticketAttachmentRemoteModel, createDate);
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

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketEntryId", long.class);

				method.invoke(_ticketAttachmentRemoteModel, ticketEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTicketSolutionId() {
		return _ticketSolutionId;
	}

	@Override
	public void setTicketSolutionId(long ticketSolutionId) {
		_ticketSolutionId = ticketSolutionId;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setTicketSolutionId",
						long.class);

				method.invoke(_ticketAttachmentRemoteModel, ticketSolutionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getReleaseNotesId() {
		return _releaseNotesId;
	}

	@Override
	public void setReleaseNotesId(long releaseNotesId) {
		_releaseNotesId = releaseNotesId;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setReleaseNotesId", long.class);

				method.invoke(_ticketAttachmentRemoteModel, releaseNotesId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFileName() {
		return _fileName;
	}

	@Override
	public void setFileName(String fileName) {
		_fileName = fileName;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setFileName", String.class);

				method.invoke(_ticketAttachmentRemoteModel, fileName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getFileSize() {
		return _fileSize;
	}

	@Override
	public void setFileSize(long fileSize) {
		_fileSize = fileSize;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setFileSize", long.class);

				method.invoke(_ticketAttachmentRemoteModel, fileSize);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_type = type;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_ticketAttachmentRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getVisibility() {
		return _visibility;
	}

	@Override
	public void setVisibility(int visibility) {
		_visibility = visibility;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setVisibility", int.class);

				method.invoke(_ticketAttachmentRemoteModel, visibility);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExtractedText() {
		return _extractedText;
	}

	@Override
	public void setExtractedText(String extractedText) {
		_extractedText = extractedText;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setExtractedText", String.class);

				method.invoke(_ticketAttachmentRemoteModel, extractedText);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAvailableFileRepositoryIds() {
		return _availableFileRepositoryIds;
	}

	@Override
	public void setAvailableFileRepositoryIds(String availableFileRepositoryIds) {
		_availableFileRepositoryIds = availableFileRepositoryIds;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setAvailableFileRepositoryIds",
						String.class);

				method.invoke(_ticketAttachmentRemoteModel,
					availableFileRepositoryIds);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getReplicate() {
		return _replicate;
	}

	@Override
	public boolean isReplicate() {
		return _replicate;
	}

	@Override
	public void setReplicate(boolean replicate) {
		_replicate = replicate;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setReplicate", boolean.class);

				method.invoke(_ticketAttachmentRemoteModel, replicate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getDeleteDate() {
		return _deleteDate;
	}

	@Override
	public void setDeleteDate(Date deleteDate) {
		_deleteDate = deleteDate;

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setDeleteDate", Date.class);

				method.invoke(_ticketAttachmentRemoteModel, deleteDate);
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

		if (_ticketAttachmentRemoteModel != null) {
			try {
				Class<?> clazz = _ticketAttachmentRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", int.class);

				method.invoke(_ticketAttachmentRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean fileExists() {
		try {
			String methodName = "fileExists";

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

	@Override
	public Set<java.lang.String> getAvailableFileRepositoryIdsSet() {
		try {
			String methodName = "getAvailableFileRepositoryIdsSet";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Set<java.lang.String> returnObj = (Set<java.lang.String>)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public int getContentLength() {
		try {
			String methodName = "getContentLength";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			Integer returnObj = (Integer)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.io.File getFile() {
		try {
			String methodName = "getFile";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.io.File returnObj = (java.io.File)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getFilePath() {
		try {
			String methodName = "getFilePath";

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
	public java.lang.String getKey() {
		try {
			String methodName = "getKey";

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

	@Override
	public java.lang.String getVisibilityLabel() {
		try {
			String methodName = "getVisibilityLabel";

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
	public void setAvailableFileRepositoryIdsSet(
		Set<java.lang.String> availableFileRepositoryIds) {
		try {
			String methodName = "setAvailableFileRepositoryIdsSet";

			Class<?>[] parameterTypes = new Class<?>[] { Set.class };

			Object[] parameterValues = new Object[] { availableFileRepositoryIds };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setFile(java.io.File file) {
		try {
			String methodName = "setFile";

			Class<?>[] parameterTypes = new Class<?>[] { java.io.File.class };

			Object[] parameterValues = new Object[] { file };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getTicketAttachmentRemoteModel() {
		return _ticketAttachmentRemoteModel;
	}

	public void setTicketAttachmentRemoteModel(
		BaseModel<?> ticketAttachmentRemoteModel) {
		_ticketAttachmentRemoteModel = ticketAttachmentRemoteModel;
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

		Class<?> remoteModelClass = _ticketAttachmentRemoteModel.getClass();

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

		Object returnValue = method.invoke(_ticketAttachmentRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			TicketAttachmentLocalServiceUtil.addTicketAttachment(this);
		}
		else {
			TicketAttachmentLocalServiceUtil.updateTicketAttachment(this);
		}
	}

	@Override
	public TicketAttachment toEscapedModel() {
		return (TicketAttachment)ProxyUtil.newProxyInstance(TicketAttachment.class.getClassLoader(),
			new Class[] { TicketAttachment.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		TicketAttachmentClp clone = new TicketAttachmentClp();

		clone.setTicketAttachmentId(getTicketAttachmentId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setTicketEntryId(getTicketEntryId());
		clone.setTicketSolutionId(getTicketSolutionId());
		clone.setReleaseNotesId(getReleaseNotesId());
		clone.setFileName(getFileName());
		clone.setFileSize(getFileSize());
		clone.setType(getType());
		clone.setVisibility(getVisibility());
		clone.setExtractedText(getExtractedText());
		clone.setAvailableFileRepositoryIds(getAvailableFileRepositoryIds());
		clone.setReplicate(getReplicate());
		clone.setDeleteDate(getDeleteDate());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
	public int compareTo(TicketAttachment ticketAttachment) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				ticketAttachment.getCreateDate());

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

		if (!(obj instanceof TicketAttachmentClp)) {
			return false;
		}

		TicketAttachmentClp ticketAttachment = (TicketAttachmentClp)obj;

		long primaryKey = ticketAttachment.getPrimaryKey();

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
		StringBundler sb = new StringBundler(33);

		sb.append("{ticketAttachmentId=");
		sb.append(getTicketAttachmentId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", ticketEntryId=");
		sb.append(getTicketEntryId());
		sb.append(", ticketSolutionId=");
		sb.append(getTicketSolutionId());
		sb.append(", releaseNotesId=");
		sb.append(getReleaseNotesId());
		sb.append(", fileName=");
		sb.append(getFileName());
		sb.append(", fileSize=");
		sb.append(getFileSize());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", visibility=");
		sb.append(getVisibility());
		sb.append(", extractedText=");
		sb.append(getExtractedText());
		sb.append(", availableFileRepositoryIds=");
		sb.append(getAvailableFileRepositoryIds());
		sb.append(", replicate=");
		sb.append(getReplicate());
		sb.append(", deleteDate=");
		sb.append(getDeleteDate());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.osb.model.TicketAttachment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>ticketAttachmentId</column-name><column-value><![CDATA[");
		sb.append(getTicketAttachmentId());
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
			"<column><column-name>ticketEntryId</column-name><column-value><![CDATA[");
		sb.append(getTicketEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>ticketSolutionId</column-name><column-value><![CDATA[");
		sb.append(getTicketSolutionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>releaseNotesId</column-name><column-value><![CDATA[");
		sb.append(getReleaseNotesId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileName</column-name><column-value><![CDATA[");
		sb.append(getFileName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fileSize</column-name><column-value><![CDATA[");
		sb.append(getFileSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>visibility</column-name><column-value><![CDATA[");
		sb.append(getVisibility());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>extractedText</column-name><column-value><![CDATA[");
		sb.append(getExtractedText());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>availableFileRepositoryIds</column-name><column-value><![CDATA[");
		sb.append(getAvailableFileRepositoryIds());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>replicate</column-name><column-value><![CDATA[");
		sb.append(getReplicate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deleteDate</column-name><column-value><![CDATA[");
		sb.append(getDeleteDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _ticketAttachmentId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private long _ticketEntryId;
	private long _ticketSolutionId;
	private long _releaseNotesId;
	private String _fileName;
	private long _fileSize;
	private int _type;
	private int _visibility;
	private String _extractedText;
	private String _availableFileRepositoryIds;
	private boolean _replicate;
	private Date _deleteDate;
	private int _status;
	private BaseModel<?> _ticketAttachmentRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}