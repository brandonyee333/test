/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Jamie Sammons
 * @generated
 */
public class BounceEntrySoap implements Serializable {

	public static BounceEntrySoap toSoapModel(BounceEntry model) {
		BounceEntrySoap soapModel = new BounceEntrySoap();

		soapModel.setBounceEntryId(model.getBounceEntryId());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setBounceDate(model.getBounceDate());
		soapModel.setBounceType(model.getBounceType());
		soapModel.setBounceSubtype(model.getBounceSubtype());

		return soapModel;
	}

	public static BounceEntrySoap[] toSoapModels(BounceEntry[] models) {
		BounceEntrySoap[] soapModels = new BounceEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BounceEntrySoap[][] toSoapModels(BounceEntry[][] models) {
		BounceEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BounceEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new BounceEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BounceEntrySoap[] toSoapModels(List<BounceEntry> models) {
		List<BounceEntrySoap> soapModels = new ArrayList<BounceEntrySoap>(
			models.size());

		for (BounceEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BounceEntrySoap[soapModels.size()]);
	}

	public BounceEntrySoap() {
	}

	public long getPrimaryKey() {
		return _bounceEntryId;
	}

	public void setPrimaryKey(long pk) {
		setBounceEntryId(pk);
	}

	public long getBounceEntryId() {
		return _bounceEntryId;
	}

	public void setBounceEntryId(long bounceEntryId) {
		_bounceEntryId = bounceEntryId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public Date getBounceDate() {
		return _bounceDate;
	}

	public void setBounceDate(Date bounceDate) {
		_bounceDate = bounceDate;
	}

	public String getBounceType() {
		return _bounceType;
	}

	public void setBounceType(String bounceType) {
		_bounceType = bounceType;
	}

	public String getBounceSubtype() {
		return _bounceSubtype;
	}

	public void setBounceSubtype(String bounceSubtype) {
		_bounceSubtype = bounceSubtype;
	}

	private long _bounceEntryId;
	private String _emailAddress;
	private Date _bounceDate;
	private String _bounceType;
	private String _bounceSubtype;

}