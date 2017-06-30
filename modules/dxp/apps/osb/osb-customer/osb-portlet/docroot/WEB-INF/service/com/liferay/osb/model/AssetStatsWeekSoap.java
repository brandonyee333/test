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
public class AssetStatsWeekSoap implements Serializable {
	public static AssetStatsWeekSoap toSoapModel(AssetStatsWeek model) {
		AssetStatsWeekSoap soapModel = new AssetStatsWeekSoap();

		soapModel.setAssetStatsWeekId(model.getAssetStatsWeekId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setWeek(model.getWeek());
		soapModel.setYear(model.getYear());
		soapModel.setViewCount(model.getViewCount());
		soapModel.setDownloadCount(model.getDownloadCount());
		soapModel.setPurchaseCount(model.getPurchaseCount());
		soapModel.setCurrencyCodeRevenues(model.getCurrencyCodeRevenues());

		return soapModel;
	}

	public static AssetStatsWeekSoap[] toSoapModels(AssetStatsWeek[] models) {
		AssetStatsWeekSoap[] soapModels = new AssetStatsWeekSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetStatsWeekSoap[][] toSoapModels(AssetStatsWeek[][] models) {
		AssetStatsWeekSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetStatsWeekSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetStatsWeekSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetStatsWeekSoap[] toSoapModels(List<AssetStatsWeek> models) {
		List<AssetStatsWeekSoap> soapModels = new ArrayList<AssetStatsWeekSoap>(models.size());

		for (AssetStatsWeek model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetStatsWeekSoap[soapModels.size()]);
	}

	public AssetStatsWeekSoap() {
	}

	public long getPrimaryKey() {
		return _assetStatsWeekId;
	}

	public void setPrimaryKey(long pk) {
		setAssetStatsWeekId(pk);
	}

	public long getAssetStatsWeekId() {
		return _assetStatsWeekId;
	}

	public void setAssetStatsWeekId(long assetStatsWeekId) {
		_assetStatsWeekId = assetStatsWeekId;
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

	public int getWeek() {
		return _week;
	}

	public void setWeek(int week) {
		_week = week;
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

	private long _assetStatsWeekId;
	private long _classNameId;
	private long _classPK;
	private int _week;
	private int _year;
	private long _viewCount;
	private long _downloadCount;
	private long _purchaseCount;
	private String _currencyCodeRevenues;
}