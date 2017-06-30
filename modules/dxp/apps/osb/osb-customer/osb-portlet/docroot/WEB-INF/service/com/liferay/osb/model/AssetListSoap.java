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
public class AssetListSoap implements Serializable {
	public static AssetListSoap toSoapModel(AssetList model) {
		AssetListSoap soapModel = new AssetListSoap();

		soapModel.setAssetListId(model.getAssetListId());
		soapModel.setAssetCategoryId(model.getAssetCategoryId());
		soapModel.setType(model.getType());

		return soapModel;
	}

	public static AssetListSoap[] toSoapModels(AssetList[] models) {
		AssetListSoap[] soapModels = new AssetListSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetListSoap[][] toSoapModels(AssetList[][] models) {
		AssetListSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetListSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetListSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetListSoap[] toSoapModels(List<AssetList> models) {
		List<AssetListSoap> soapModels = new ArrayList<AssetListSoap>(models.size());

		for (AssetList model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetListSoap[soapModels.size()]);
	}

	public AssetListSoap() {
	}

	public long getPrimaryKey() {
		return _assetListId;
	}

	public void setPrimaryKey(long pk) {
		setAssetListId(pk);
	}

	public long getAssetListId() {
		return _assetListId;
	}

	public void setAssetListId(long assetListId) {
		_assetListId = assetListId;
	}

	public long getAssetCategoryId() {
		return _assetCategoryId;
	}

	public void setAssetCategoryId(long assetCategoryId) {
		_assetCategoryId = assetCategoryId;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	private long _assetListId;
	private long _assetCategoryId;
	private int _type;
}