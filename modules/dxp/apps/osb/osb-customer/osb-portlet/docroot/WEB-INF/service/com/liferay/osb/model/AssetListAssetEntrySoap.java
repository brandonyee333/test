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

package com.liferay.osb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class AssetListAssetEntrySoap implements Serializable {
	public static AssetListAssetEntrySoap toSoapModel(AssetListAssetEntry model) {
		AssetListAssetEntrySoap soapModel = new AssetListAssetEntrySoap();

		soapModel.setAssetListAssetEntryId(model.getAssetListAssetEntryId());
		soapModel.setAssetListId(model.getAssetListId());
		soapModel.setAssetEntryId(model.getAssetEntryId());

		return soapModel;
	}

	public static AssetListAssetEntrySoap[] toSoapModels(
		AssetListAssetEntry[] models) {
		AssetListAssetEntrySoap[] soapModels = new AssetListAssetEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetListAssetEntrySoap[][] toSoapModels(
		AssetListAssetEntry[][] models) {
		AssetListAssetEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetListAssetEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetListAssetEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetListAssetEntrySoap[] toSoapModels(
		List<AssetListAssetEntry> models) {
		List<AssetListAssetEntrySoap> soapModels = new ArrayList<AssetListAssetEntrySoap>(models.size());

		for (AssetListAssetEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetListAssetEntrySoap[soapModels.size()]);
	}

	public AssetListAssetEntrySoap() {
	}

	public long getPrimaryKey() {
		return _assetListAssetEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAssetListAssetEntryId(pk);
	}

	public long getAssetListAssetEntryId() {
		return _assetListAssetEntryId;
	}

	public void setAssetListAssetEntryId(long assetListAssetEntryId) {
		_assetListAssetEntryId = assetListAssetEntryId;
	}

	public long getAssetListId() {
		return _assetListId;
	}

	public void setAssetListId(long assetListId) {
		_assetListId = assetListId;
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	private long _assetListAssetEntryId;
	private long _assetListId;
	private long _assetEntryId;
}