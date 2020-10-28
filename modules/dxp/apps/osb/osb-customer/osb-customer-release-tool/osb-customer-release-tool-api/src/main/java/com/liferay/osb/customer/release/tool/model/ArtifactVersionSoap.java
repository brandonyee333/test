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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ArtifactVersionSoap implements Serializable {

	public static ArtifactVersionSoap toSoapModel(ArtifactVersion model) {
		ArtifactVersionSoap soapModel = new ArtifactVersionSoap();

		soapModel.setArtifactVersionId(model.getArtifactVersionId());
		soapModel.setReleaseAssetCategoryId(model.getReleaseAssetCategoryId());
		soapModel.setOwner(model.getOwner());
		soapModel.setRepository(model.getRepository());
		soapModel.setGroup(model.getGroup());
		soapModel.setName(model.getName());
		soapModel.setVersion(model.getVersion());
		soapModel.setPackaging(model.getPackaging());

		return soapModel;
	}

	public static ArtifactVersionSoap[] toSoapModels(ArtifactVersion[] models) {
		ArtifactVersionSoap[] soapModels =
			new ArtifactVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ArtifactVersionSoap[][] toSoapModels(
		ArtifactVersion[][] models) {

		ArtifactVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ArtifactVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ArtifactVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ArtifactVersionSoap[] toSoapModels(
		List<ArtifactVersion> models) {

		List<ArtifactVersionSoap> soapModels =
			new ArrayList<ArtifactVersionSoap>(models.size());

		for (ArtifactVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ArtifactVersionSoap[soapModels.size()]);
	}

	public ArtifactVersionSoap() {
	}

	public long getPrimaryKey() {
		return _artifactVersionId;
	}

	public void setPrimaryKey(long pk) {
		setArtifactVersionId(pk);
	}

	public long getArtifactVersionId() {
		return _artifactVersionId;
	}

	public void setArtifactVersionId(long artifactVersionId) {
		_artifactVersionId = artifactVersionId;
	}

	public long getReleaseAssetCategoryId() {
		return _releaseAssetCategoryId;
	}

	public void setReleaseAssetCategoryId(long releaseAssetCategoryId) {
		_releaseAssetCategoryId = releaseAssetCategoryId;
	}

	public int getOwner() {
		return _owner;
	}

	public void setOwner(int owner) {
		_owner = owner;
	}

	public int getRepository() {
		return _repository;
	}

	public void setRepository(int repository) {
		_repository = repository;
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

	public String getPackaging() {
		return _packaging;
	}

	public void setPackaging(String packaging) {
		_packaging = packaging;
	}

	private long _artifactVersionId;
	private long _releaseAssetCategoryId;
	private int _owner;
	private int _repository;
	private String _group;
	private String _name;
	private String _version;
	private String _packaging;

}