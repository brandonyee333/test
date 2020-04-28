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
		List<LoopStreamSoap> soapModels = new ArrayList<LoopStreamSoap>(
			models.size());

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