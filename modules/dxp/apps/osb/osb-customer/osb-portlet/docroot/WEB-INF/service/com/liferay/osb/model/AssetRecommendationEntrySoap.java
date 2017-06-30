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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AssetRecommendationEntryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AssetRecommendationEntryServiceSoap
 * @generated
 */
public class AssetRecommendationEntrySoap implements Serializable {
	public static AssetRecommendationEntrySoap toSoapModel(
		AssetRecommendationEntry model) {
		AssetRecommendationEntrySoap soapModel = new AssetRecommendationEntrySoap();

		soapModel.setAssetRecommendationEntryId(model.getAssetRecommendationEntryId());
		soapModel.setAssetRecommendationSetId(model.getAssetRecommendationSetId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setViewedAlsoPurchasedCount(model.getViewedAlsoPurchasedCount());
		soapModel.setPurchasedAlsoPurchasedCount(model.getPurchasedAlsoPurchasedCount());

		return soapModel;
	}

	public static AssetRecommendationEntrySoap[] toSoapModels(
		AssetRecommendationEntry[] models) {
		AssetRecommendationEntrySoap[] soapModels = new AssetRecommendationEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetRecommendationEntrySoap[][] toSoapModels(
		AssetRecommendationEntry[][] models) {
		AssetRecommendationEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetRecommendationEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetRecommendationEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetRecommendationEntrySoap[] toSoapModels(
		List<AssetRecommendationEntry> models) {
		List<AssetRecommendationEntrySoap> soapModels = new ArrayList<AssetRecommendationEntrySoap>(models.size());

		for (AssetRecommendationEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetRecommendationEntrySoap[soapModels.size()]);
	}

	public AssetRecommendationEntrySoap() {
	}

	public long getPrimaryKey() {
		return _assetRecommendationEntryId;
	}

	public void setPrimaryKey(long pk) {
		setAssetRecommendationEntryId(pk);
	}

	public long getAssetRecommendationEntryId() {
		return _assetRecommendationEntryId;
	}

	public void setAssetRecommendationEntryId(long assetRecommendationEntryId) {
		_assetRecommendationEntryId = assetRecommendationEntryId;
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

	public int getViewedAlsoPurchasedCount() {
		return _viewedAlsoPurchasedCount;
	}

	public void setViewedAlsoPurchasedCount(int viewedAlsoPurchasedCount) {
		_viewedAlsoPurchasedCount = viewedAlsoPurchasedCount;
	}

	public int getPurchasedAlsoPurchasedCount() {
		return _purchasedAlsoPurchasedCount;
	}

	public void setPurchasedAlsoPurchasedCount(int purchasedAlsoPurchasedCount) {
		_purchasedAlsoPurchasedCount = purchasedAlsoPurchasedCount;
	}

	private long _assetRecommendationEntryId;
	private long _assetRecommendationSetId;
	private long _classNameId;
	private long _classPK;
	private int _viewedAlsoPurchasedCount;
	private int _purchasedAlsoPurchasedCount;
}