/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
public class LoopUserNotificationEventSoap implements Serializable {

	public static LoopUserNotificationEventSoap toSoapModel(
		LoopUserNotificationEvent model) {

		LoopUserNotificationEventSoap soapModel =
			new LoopUserNotificationEventSoap();

		soapModel.setLoopUserNotificationEventId(
			model.getLoopUserNotificationEventId());
		soapModel.setCreateTime(model.getCreateTime());
		soapModel.setRecipientUserId(model.getRecipientUserId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setGroupClassNameId(model.getGroupClassNameId());
		soapModel.setGroupClassPK(model.getGroupClassPK());
		soapModel.setGroupKey(model.getGroupKey());
		soapModel.setType(model.getType());
		soapModel.setReceived(model.isReceived());
		soapModel.setOpened(model.isOpened());

		return soapModel;
	}

	public static LoopUserNotificationEventSoap[] toSoapModels(
		LoopUserNotificationEvent[] models) {

		LoopUserNotificationEventSoap[] soapModels =
			new LoopUserNotificationEventSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopUserNotificationEventSoap[][] toSoapModels(
		LoopUserNotificationEvent[][] models) {

		LoopUserNotificationEventSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LoopUserNotificationEventSoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new LoopUserNotificationEventSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopUserNotificationEventSoap[] toSoapModels(
		List<LoopUserNotificationEvent> models) {

		List<LoopUserNotificationEventSoap> soapModels =
			new ArrayList<LoopUserNotificationEventSoap>(models.size());

		for (LoopUserNotificationEvent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new LoopUserNotificationEventSoap[soapModels.size()]);
	}

	public LoopUserNotificationEventSoap() {
	}

	public long getPrimaryKey() {
		return _loopUserNotificationEventId;
	}

	public void setPrimaryKey(long pk) {
		setLoopUserNotificationEventId(pk);
	}

	public long getLoopUserNotificationEventId() {
		return _loopUserNotificationEventId;
	}

	public void setLoopUserNotificationEventId(
		long loopUserNotificationEventId) {

		_loopUserNotificationEventId = loopUserNotificationEventId;
	}

	public long getCreateTime() {
		return _createTime;
	}

	public void setCreateTime(long createTime) {
		_createTime = createTime;
	}

	public long getRecipientUserId() {
		return _recipientUserId;
	}

	public void setRecipientUserId(long recipientUserId) {
		_recipientUserId = recipientUserId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public long getGroupClassNameId() {
		return _groupClassNameId;
	}

	public void setGroupClassNameId(long groupClassNameId) {
		_groupClassNameId = groupClassNameId;
	}

	public long getGroupClassPK() {
		return _groupClassPK;
	}

	public void setGroupClassPK(long groupClassPK) {
		_groupClassPK = groupClassPK;
	}

	public long getGroupKey() {
		return _groupKey;
	}

	public void setGroupKey(long groupKey) {
		_groupKey = groupKey;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public boolean getReceived() {
		return _received;
	}

	public boolean isReceived() {
		return _received;
	}

	public void setReceived(boolean received) {
		_received = received;
	}

	public boolean getOpened() {
		return _opened;
	}

	public boolean isOpened() {
		return _opened;
	}

	public void setOpened(boolean opened) {
		_opened = opened;
	}

	private long _loopUserNotificationEventId;
	private long _createTime;
	private long _recipientUserId;
	private long _classNameId;
	private long _classPK;
	private long _groupClassNameId;
	private long _groupClassPK;
	private long _groupKey;
	private int _type;
	private boolean _received;
	private boolean _opened;

}