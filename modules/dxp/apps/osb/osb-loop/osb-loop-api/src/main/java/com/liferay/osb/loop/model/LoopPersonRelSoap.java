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

package com.liferay.osb.loop.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Ethan Bustad
 * @generated
 */
@ProviderType
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
		List<LoopPersonRelSoap> soapModels = new ArrayList<LoopPersonRelSoap>(models.size());

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