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
public class LoopStreamSoap implements Serializable {
	public static LoopStreamSoap toSoapModel(LoopStream model) {
		LoopStreamSoap soapModel = new LoopStreamSoap();

		soapModel.setLoopStreamId(model.getLoopStreamId());
		soapModel.setLoopPersonId(model.getLoopPersonId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static LoopStreamSoap[] toSoapModels(LoopStream[] models) {
		LoopStreamSoap[] soapModels = new LoopStreamSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopStreamSoap[][] toSoapModels(LoopStream[][] models) {
		LoopStreamSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopStreamSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopStreamSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopStreamSoap[] toSoapModels(List<LoopStream> models) {
		List<LoopStreamSoap> soapModels = new ArrayList<LoopStreamSoap>(models.size());

		for (LoopStream model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopStreamSoap[soapModels.size()]);
	}

	public LoopStreamSoap() {
	}

	public long getPrimaryKey() {
		return _loopStreamId;
	}

	public void setPrimaryKey(long pk) {
		setLoopStreamId(pk);
	}

	public long getLoopStreamId() {
		return _loopStreamId;
	}

	public void setLoopStreamId(long loopStreamId) {
		_loopStreamId = loopStreamId;
	}

	public long getLoopPersonId() {
		return _loopPersonId;
	}

	public void setLoopPersonId(long loopPersonId) {
		_loopPersonId = loopPersonId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _loopStreamId;
	private long _loopPersonId;
	private String _name;
	private String _description;
}