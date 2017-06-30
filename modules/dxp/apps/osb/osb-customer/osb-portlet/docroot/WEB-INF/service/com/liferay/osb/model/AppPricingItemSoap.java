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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppPricingItemServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppPricingItemServiceSoap
 * @generated
 */
public class AppPricingItemSoap implements Serializable {
	public static AppPricingItemSoap toSoapModel(AppPricingItem model) {
		AppPricingItemSoap soapModel = new AppPricingItemSoap();

		soapModel.setAppPricingItemId(model.getAppPricingItemId());
		soapModel.setAppPricingId(model.getAppPricingId());
		soapModel.setAssetLicenseId(model.getAssetLicenseId());
		soapModel.setCurrencyEntryId(model.getCurrencyEntryId());
		soapModel.setPrice(model.getPrice());

		return soapModel;
	}

	public static AppPricingItemSoap[] toSoapModels(AppPricingItem[] models) {
		AppPricingItemSoap[] soapModels = new AppPricingItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppPricingItemSoap[][] toSoapModels(AppPricingItem[][] models) {
		AppPricingItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppPricingItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppPricingItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppPricingItemSoap[] toSoapModels(List<AppPricingItem> models) {
		List<AppPricingItemSoap> soapModels = new ArrayList<AppPricingItemSoap>(models.size());

		for (AppPricingItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppPricingItemSoap[soapModels.size()]);
	}

	public AppPricingItemSoap() {
	}

	public long getPrimaryKey() {
		return _appPricingItemId;
	}

	public void setPrimaryKey(long pk) {
		setAppPricingItemId(pk);
	}

	public long getAppPricingItemId() {
		return _appPricingItemId;
	}

	public void setAppPricingItemId(long appPricingItemId) {
		_appPricingItemId = appPricingItemId;
	}

	public long getAppPricingId() {
		return _appPricingId;
	}

	public void setAppPricingId(long appPricingId) {
		_appPricingId = appPricingId;
	}

	public long getAssetLicenseId() {
		return _assetLicenseId;
	}

	public void setAssetLicenseId(long assetLicenseId) {
		_assetLicenseId = assetLicenseId;
	}

	public long getCurrencyEntryId() {
		return _currencyEntryId;
	}

	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntryId = currencyEntryId;
	}

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	private long _appPricingItemId;
	private long _appPricingId;
	private long _assetLicenseId;
	private long _currencyEntryId;
	private double _price;
}