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
public class LoopDivisionRelSoap implements Serializable {
	public static LoopDivisionRelSoap toSoapModel(LoopDivisionRel model) {
		LoopDivisionRelSoap soapModel = new LoopDivisionRelSoap();

		soapModel.setLoopDivisionRelId(model.getLoopDivisionRelId());
		soapModel.setChildLoopDivisionId(model.getChildLoopDivisionId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setParentLoopDivisionId(model.getParentLoopDivisionId());

		return soapModel;
	}

	public static LoopDivisionRelSoap[] toSoapModels(LoopDivisionRel[] models) {
		LoopDivisionRelSoap[] soapModels = new LoopDivisionRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopDivisionRelSoap[][] toSoapModels(
		LoopDivisionRel[][] models) {
		LoopDivisionRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopDivisionRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopDivisionRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopDivisionRelSoap[] toSoapModels(
		List<LoopDivisionRel> models) {
		List<LoopDivisionRelSoap> soapModels = new ArrayList<LoopDivisionRelSoap>(models.size());

		for (LoopDivisionRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopDivisionRelSoap[soapModels.size()]);
	}

	public LoopDivisionRelSoap() {
	}

	public long getPrimaryKey() {
		return _loopDivisionRelId;
	}

	public void setPrimaryKey(long pk) {
		setLoopDivisionRelId(pk);
	}

	public long getLoopDivisionRelId() {
		return _loopDivisionRelId;
	}

	public void setLoopDivisionRelId(long loopDivisionRelId) {
		_loopDivisionRelId = loopDivisionRelId;
	}

	public long getChildLoopDivisionId() {
		return _childLoopDivisionId;
	}

	public void setChildLoopDivisionId(long childLoopDivisionId) {
		_childLoopDivisionId = childLoopDivisionId;
	}

	public long getLoopPersonId() {
		return _loopPersonId;
	}

	public void setLoopPersonId(long loopPersonId) {
		_loopPersonId = loopPersonId;
	}

	public long getParentLoopDivisionId() {
		return _parentLoopDivisionId;
	}

	public void setParentLoopDivisionId(long parentLoopDivisionId) {
		_parentLoopDivisionId = parentLoopDivisionId;
	}

	private long _loopDivisionRelId;
	private long _childLoopDivisionId;
	private long _loopPersonId;
	private long _parentLoopDivisionId;
}