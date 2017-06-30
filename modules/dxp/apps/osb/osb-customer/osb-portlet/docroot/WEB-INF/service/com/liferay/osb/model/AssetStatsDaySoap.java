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
public class AssetStatsDaySoap implements Serializable {
	public static AssetStatsDaySoap toSoapModel(AssetStatsDay model) {
		AssetStatsDaySoap soapModel = new AssetStatsDaySoap();

		soapModel.setAssetStatsDayId(model.getAssetStatsDayId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setDay(model.getDay());
		soapModel.setYear(model.getYear());
		soapModel.setViewCount(model.getViewCount());
		soapModel.setDownloadCount(model.getDownloadCount());
		soapModel.setPurchaseCount(model.getPurchaseCount());
		soapModel.setCurrencyCodeRevenues(model.getCurrencyCodeRevenues());

		return soapModel;
	}

	public static AssetStatsDaySoap[] toSoapModels(AssetStatsDay[] models) {
		AssetStatsDaySoap[] soapModels = new AssetStatsDaySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetStatsDaySoap[][] toSoapModels(AssetStatsDay[][] models) {
		AssetStatsDaySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetStatsDaySoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetStatsDaySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetStatsDaySoap[] toSoapModels(List<AssetStatsDay> models) {
		List<AssetStatsDaySoap> soapModels = new ArrayList<AssetStatsDaySoap>(models.size());

		for (AssetStatsDay model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetStatsDaySoap[soapModels.size()]);
	}

	public AssetStatsDaySoap() {
	}

	public long getPrimaryKey() {
		return _assetStatsDayId;
	}

	public void setPrimaryKey(long pk) {
		setAssetStatsDayId(pk);
	}

	public long getAssetStatsDayId() {
		return _assetStatsDayId;
	}

	public void setAssetStatsDayId(long assetStatsDayId) {
		_assetStatsDayId = assetStatsDayId;
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

	public int getDay() {
		return _day;
	}

	public void setDay(int day) {
		_day = day;
	}

	public int getYear() {
		return _year;
	}

	public void setYear(int year) {
		_year = year;
	}

	public long getViewCount() {
		return _viewCount;
	}

	public void setViewCount(long viewCount) {
		_viewCount = viewCount;
	}

	public long getDownloadCount() {
		return _downloadCount;
	}

	public void setDownloadCount(long downloadCount) {
		_downloadCount = downloadCount;
	}

	public long getPurchaseCount() {
		return _purchaseCount;
	}

	public void setPurchaseCount(long purchaseCount) {
		_purchaseCount = purchaseCount;
	}

	public String getCurrencyCodeRevenues() {
		return _currencyCodeRevenues;
	}

	public void setCurrencyCodeRevenues(String currencyCodeRevenues) {
		_currencyCodeRevenues = currencyCodeRevenues;
	}

	private long _assetStatsDayId;
	private long _classNameId;
	private long _classPK;
	private int _day;
	private int _year;
	private long _viewCount;
	private long _downloadCount;
	private long _purchaseCount;
	private String _currencyCodeRevenues;
}