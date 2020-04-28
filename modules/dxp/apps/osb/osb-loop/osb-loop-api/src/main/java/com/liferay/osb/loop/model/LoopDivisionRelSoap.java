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
		LoopDivisionRelSoap[] soapModels =
			new LoopDivisionRelSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopDivisionRelSoap[][] toSoapModels(
		LoopDivisionRel[][] models) {

		LoopDivisionRelSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new LoopDivisionRelSoap[models.length][models[0].length];
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

		List<LoopDivisionRelSoap> soapModels =
			new ArrayList<LoopDivisionRelSoap>(models.size());

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