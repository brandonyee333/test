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
public class LoopStatsEntrySoap implements Serializable {
	public static LoopStatsEntrySoap toSoapModel(LoopStatsEntry model) {
		LoopStatsEntrySoap soapModel = new LoopStatsEntrySoap();

		soapModel.setLoopStatsEntryId(model.getLoopStatsEntryId());
		soapModel.setModifiedTime(model.getModifiedTime());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setScore(model.getScore());

		return soapModel;
	}

	public static LoopStatsEntrySoap[] toSoapModels(LoopStatsEntry[] models) {
		LoopStatsEntrySoap[] soapModels = new LoopStatsEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static LoopStatsEntrySoap[][] toSoapModels(LoopStatsEntry[][] models) {
		LoopStatsEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new LoopStatsEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new LoopStatsEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static LoopStatsEntrySoap[] toSoapModels(List<LoopStatsEntry> models) {
		List<LoopStatsEntrySoap> soapModels = new ArrayList<LoopStatsEntrySoap>(models.size());

		for (LoopStatsEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new LoopStatsEntrySoap[soapModels.size()]);
	}

	public LoopStatsEntrySoap() {
	}

	public long getPrimaryKey() {
		return _loopStatsEntryId;
	}

	public void setPrimaryKey(long pk) {
		setLoopStatsEntryId(pk);
	}

	public long getLoopStatsEntryId() {
		return _loopStatsEntryId;
	}

	public void setLoopStatsEntryId(long loopStatsEntryId) {
		_loopStatsEntryId = loopStatsEntryId;
	}

	public long getModifiedTime() {
		return _modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		_modifiedTime = modifiedTime;
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

	public double getScore() {
		return _score;
	}

	public void setScore(double score) {
		_score = score;
	}

	private long _loopStatsEntryId;
	private long _modifiedTime;
	private long _classNameId;
	private long _classPK;
	private double _score;
}