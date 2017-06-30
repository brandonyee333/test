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
public class AssetRecommendationSetSoap implements Serializable {
	public static AssetRecommendationSetSoap toSoapModel(
		AssetRecommendationSet model) {
		AssetRecommendationSetSoap soapModel = new AssetRecommendationSetSoap();

		soapModel.setAssetRecommendationSetId(model.getAssetRecommendationSetId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static AssetRecommendationSetSoap[] toSoapModels(
		AssetRecommendationSet[] models) {
		AssetRecommendationSetSoap[] soapModels = new AssetRecommendationSetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetRecommendationSetSoap[][] toSoapModels(
		AssetRecommendationSet[][] models) {
		AssetRecommendationSetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetRecommendationSetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetRecommendationSetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetRecommendationSetSoap[] toSoapModels(
		List<AssetRecommendationSet> models) {
		List<AssetRecommendationSetSoap> soapModels = new ArrayList<AssetRecommendationSetSoap>(models.size());

		for (AssetRecommendationSet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetRecommendationSetSoap[soapModels.size()]);
	}

	public AssetRecommendationSetSoap() {
	}

	public long getPrimaryKey() {
		return _assetRecommendationSetId;
	}

	public void setPrimaryKey(long pk) {
		setAssetRecommendationSetId(pk);
	}

	public long getAssetRecommendationSetId() {
		return _assetRecommendationSetId;
	}

	public void setAssetRecommendationSetId(long assetRecommendationSetId) {
		_assetRecommendationSetId = assetRecommendationSetId;
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

	private long _assetRecommendationSetId;
	private long _classNameId;
	private long _classPK;
}