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
public class LoopExternalReferenceRelSoap implements Serializable {
	public static LoopExternalReferenceRelSoap toSoapModel(
		LoopExternalReferenceRel model) {
		LoopExternalReferenceRelSoap soapModel = new LoopExternalReferenceRelSoap();

		soapModel.setLoopExternalReferenceRelId(model.getLoopExternalReferenceRelId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setExternalReferenceName(model.getExternalReferenceName());
		soapModel.setExternalReferencePK(model.getExternalReferencePK());

		return soapModel;
	}

	public static LoopExternalReferenceRelSoap[] toSoapModels(
		LoopExternalReferenceRel[] models) {
		LoopExternalReferenceRelSoap[] soapModels = new LoopExternalReferenceRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopExternalReferenceRelSoap[][] toSoapModels(
		LoopExternalReferenceRel[][] models) {
		LoopExternalReferenceRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopExternalReferenceRelSoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopExternalReferenceRelSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopExternalReferenceRelSoap[] toSoapModels(
		List<LoopExternalReferenceRel> models) {
		List<LoopExternalReferenceRelSoap> soapModels = new ArrayList<LoopExternalReferenceRelSoap>(models.size());

		for (LoopExternalReferenceRel model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopExternalReferenceRelSoap[soapModels.size()]);
	}

	public LoopExternalReferenceRelSoap() {
	}

	public long getPrimaryKey() {
		return _loopExternalReferenceRelId;
	}

	public void setPrimaryKey(long pk) {
		setLoopExternalReferenceRelId(pk);
	}

	public long getLoopExternalReferenceRelId() {
		return _loopExternalReferenceRelId;
	}

	public void setLoopExternalReferenceRelId(long loopExternalReferenceRelId) {
		_loopExternalReferenceRelId = loopExternalReferenceRelId;
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

	public String getExternalReferenceName() {
		return _externalReferenceName;
	}

	public void setExternalReferenceName(String externalReferenceName) {
		_externalReferenceName = externalReferenceName;
	}

	public String getExternalReferencePK() {
		return _externalReferencePK;
	}

	public void setExternalReferencePK(String externalReferencePK) {
		_externalReferencePK = externalReferencePK;
	}

	private long _loopExternalReferenceRelId;
	private long _classNameId;
	private long _classPK;
	private String _externalReferenceName;
	private String _externalReferencePK;
}