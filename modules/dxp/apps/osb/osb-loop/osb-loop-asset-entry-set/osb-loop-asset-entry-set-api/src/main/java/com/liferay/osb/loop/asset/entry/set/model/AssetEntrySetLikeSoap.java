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

package com.liferay.osb.loop.asset.entry.set.model;

import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikePK;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetEntrySetLikeSoap implements Serializable {

	public static AssetEntrySetLikeSoap toSoapModel(AssetEntrySetLike model) {
		AssetEntrySetLikeSoap soapModel = new AssetEntrySetLikeSoap();

		soapModel.setAssetEntrySetId(model.getAssetEntrySetId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static AssetEntrySetLikeSoap[] toSoapModels(
		AssetEntrySetLike[] models) {

		AssetEntrySetLikeSoap[] soapModels =
			new AssetEntrySetLikeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetEntrySetLikeSoap[][] toSoapModels(
		AssetEntrySetLike[][] models) {

		AssetEntrySetLikeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new AssetEntrySetLikeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetEntrySetLikeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetEntrySetLikeSoap[] toSoapModels(
		List<AssetEntrySetLike> models) {

		List<AssetEntrySetLikeSoap> soapModels =
			new ArrayList<AssetEntrySetLikeSoap>(models.size());

		for (AssetEntrySetLike model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetEntrySetLikeSoap[soapModels.size()]);
	}

	public AssetEntrySetLikeSoap() {
	}

	public AssetEntrySetLikePK getPrimaryKey() {
		return new AssetEntrySetLikePK(
			_assetEntrySetId, _classNameId, _classPK);
	}

	public void setPrimaryKey(AssetEntrySetLikePK pk) {
		setAssetEntrySetId(pk.assetEntrySetId);
		setClassNameId(pk.classNameId);
		setClassPK(pk.classPK);
	}

	public long getAssetEntrySetId() {
		return _assetEntrySetId;
	}

	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySetId = assetEntrySetId;
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

	private long _assetEntrySetId;
	private long _classNameId;
	private long _classPK;

}