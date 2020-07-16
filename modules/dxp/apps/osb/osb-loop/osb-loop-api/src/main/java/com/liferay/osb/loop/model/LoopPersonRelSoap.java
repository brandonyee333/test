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
public class LoopPersonRelSoap implements Serializable {

	public static LoopPersonRelSoap toSoapModel(LoopPersonRel model) {
		LoopPersonRelSoap soapModel = new LoopPersonRelSoap();

		soapModel.setLoopPersonRelId(model.getLoopPersonRelId());
		soapModel.setChildLoopPersonId(model.getChildLoopPersonId());
		soapModel.setParentLoopPersonId(model.getParentLoopPersonId());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static LoopPersonRelSoap[] toSoapModels(LoopPersonRel[] models) {
		LoopPersonRelSoap[] soapModels = new LoopPersonRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopPersonRelSoap[][] toSoapModels(LoopPersonRel[][] models) {
		LoopPersonRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopPersonRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopPersonRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopPersonRelSoap[] toSoapModels(List<LoopPersonRel> models) {
		List<LoopPersonRelSoap> soapModels = new ArrayList<LoopPersonRelSoap>(
			models.size());

		for (LoopPersonRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopPersonRelSoap[soapModels.size()]);
	}

	public LoopPersonRelSoap() {
	}

	public long getPrimaryKey() {
		return _loopPersonRelId;
	}

	public void setPrimaryKey(long pk) {
		setLoopPersonRelId(pk);
	}

	public long getLoopPersonRelId() {
		return _loopPersonRelId;
	}

	public void setLoopPersonRelId(long loopPersonRelId) {
		_loopPersonRelId = loopPersonRelId;
	}

	public long getChildLoopPersonId() {
		return _childLoopPersonId;
	}

	public void setChildLoopPersonId(long childLoopPersonId) {
		_childLoopPersonId = childLoopPersonId;
	}

	public long getParentLoopPersonId() {
		return _parentLoopPersonId;
	}

	public void setParentLoopPersonId(long parentLoopPersonId) {
		_parentLoopPersonId = parentLoopPersonId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _loopPersonRelId;
	private long _childLoopPersonId;
	private long _parentLoopPersonId;
	private int _type;

}