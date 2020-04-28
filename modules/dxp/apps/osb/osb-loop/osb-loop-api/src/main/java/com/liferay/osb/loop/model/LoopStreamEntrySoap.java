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
public class LoopStreamEntrySoap implements Serializable {

	public static LoopStreamEntrySoap toSoapModel(LoopStreamEntry model) {
		LoopStreamEntrySoap soapModel = new LoopStreamEntrySoap();

		soapModel.setLoopStreamEntryId(model.getLoopStreamEntryId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setLoopStreamId(model.getLoopStreamId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setFollowing(model.isFollowing());
		soapModel.setFollowingType(model.getFollowingType());

		return soapModel;
	}

	public static LoopStreamEntrySoap[] toSoapModels(LoopStreamEntry[] models) {
		LoopStreamEntrySoap[] soapModels =
			new LoopStreamEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopStreamEntrySoap[][] toSoapModels(
		LoopStreamEntry[][] models) {

		LoopStreamEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LoopStreamEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopStreamEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopStreamEntrySoap[] toSoapModels(
		List<LoopStreamEntry> models) {

		List<LoopStreamEntrySoap> soapModels =
			new ArrayList<LoopStreamEntrySoap>(models.size());

		for (LoopStreamEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopStreamEntrySoap[soapModels.size()]);
	}

	public LoopStreamEntrySoap() {
	}

	public long getPrimaryKey() {
		return _loopStreamEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLoopStreamEntryId(pk);
	}

	public long getLoopStreamEntryId() {
		return _loopStreamEntryId;
	}

	public void setLoopStreamEntryId(long loopStreamEntryId) {
		_loopStreamEntryId = loopStreamEntryId;
	}

	public long getLoopPersonId() {
		return _loopPersonId;
	}

	public void setLoopPersonId(long loopPersonId) {
		_loopPersonId = loopPersonId;
	}

	public long getLoopStreamId() {
		return _loopStreamId;
	}

	public void setLoopStreamId(long loopStreamId) {
		_loopStreamId = loopStreamId;
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

	public boolean getFollowing() {
		return _following;
	}

	public boolean isFollowing() {
		return _following;
	}

	public void setFollowing(boolean following) {
		_following = following;
	}

	public int getFollowingType() {
		return _followingType;
	}

	public void setFollowingType(int followingType) {
		_followingType = followingType;
	}

	private long _loopStreamEntryId;
	private long _loopPersonId;
	private long _loopStreamId;
	private long _classNameId;
	private long _classPK;
	private boolean _following;
	private int _followingType;

}