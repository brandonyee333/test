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

package com.liferay.osb.customer.release.tool.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ModuleVersionSoap implements Serializable {

	public static ModuleVersionSoap toSoapModel(ModuleVersion model) {
		ModuleVersionSoap soapModel = new ModuleVersionSoap();

		soapModel.setModuleVersionId(model.getModuleVersionId());
		soapModel.setReleaseAssetCategoryId(model.getReleaseAssetCategoryId());
		soapModel.setGroup(model.getGroup());
		soapModel.setName(model.getName());
		soapModel.setVersion(model.getVersion());

		return soapModel;
	}

	public static ModuleVersionSoap[] toSoapModels(ModuleVersion[] models) {
		ModuleVersionSoap[] soapModels = new ModuleVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ModuleVersionSoap[][] toSoapModels(ModuleVersion[][] models) {
		ModuleVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ModuleVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ModuleVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ModuleVersionSoap[] toSoapModels(List<ModuleVersion> models) {
		List<ModuleVersionSoap> soapModels = new ArrayList<ModuleVersionSoap>(
			models.size());

		for (ModuleVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ModuleVersionSoap[soapModels.size()]);
	}

	public ModuleVersionSoap() {
	}

	public long getPrimaryKey() {
		return _moduleVersionId;
	}

	public void setPrimaryKey(long pk) {
		setModuleVersionId(pk);
	}

	public long getModuleVersionId() {
		return _moduleVersionId;
	}

	public void setModuleVersionId(long moduleVersionId) {
		_moduleVersionId = moduleVersionId;
	}

	public long getReleaseAssetCategoryId() {
		return _releaseAssetCategoryId;
	}

	public void setReleaseAssetCategoryId(long releaseAssetCategoryId) {
		_releaseAssetCategoryId = releaseAssetCategoryId;
	}

	public String getGroup() {
		return _group;
	}

	public void setGroup(String group) {
		_group = group;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	private long _moduleVersionId;
	private long _releaseAssetCategoryId;
	private String _group;
	private String _name;
	private String _version;

}