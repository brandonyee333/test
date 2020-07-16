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

package com.liferay.osb.community.github.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Haote Chou
 * @generated
 */
public class GitHubContributorSoap implements Serializable {

	public static GitHubContributorSoap toSoapModel(GitHubContributor model) {
		GitHubContributorSoap soapModel = new GitHubContributorSoap();

		soapModel.setGitHubContributorId(model.getGitHubContributorId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGitHubRepositoryId(model.getGitHubRepositoryId());
		soapModel.setName(model.getName());
		soapModel.setAvatarURL(model.getAvatarURL());
		soapModel.setContributions(model.getContributions());
		soapModel.setProfileURL(model.getProfileURL());

		return soapModel;
	}

	public static GitHubContributorSoap[] toSoapModels(
		GitHubContributor[] models) {

		GitHubContributorSoap[] soapModels =
			new GitHubContributorSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GitHubContributorSoap[][] toSoapModels(
		GitHubContributor[][] models) {

		GitHubContributorSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new GitHubContributorSoap[models.length][models[0].length];
		}
		else {
			soapModels = new GitHubContributorSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GitHubContributorSoap[] toSoapModels(
		List<GitHubContributor> models) {

		List<GitHubContributorSoap> soapModels =
			new ArrayList<GitHubContributorSoap>(models.size());

		for (GitHubContributor model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GitHubContributorSoap[soapModels.size()]);
	}

	public GitHubContributorSoap() {
	}

	public long getPrimaryKey() {
		return _gitHubContributorId;
	}

	public void setPrimaryKey(long pk) {
		setGitHubContributorId(pk);
	}

	public long getGitHubContributorId() {
		return _gitHubContributorId;
	}

	public void setGitHubContributorId(long gitHubContributorId) {
		_gitHubContributorId = gitHubContributorId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getGitHubRepositoryId() {
		return _gitHubRepositoryId;
	}

	public void setGitHubRepositoryId(long gitHubRepositoryId) {
		_gitHubRepositoryId = gitHubRepositoryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getAvatarURL() {
		return _avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		_avatarURL = avatarURL;
	}

	public int getContributions() {
		return _contributions;
	}

	public void setContributions(int contributions) {
		_contributions = contributions;
	}

	public String getProfileURL() {
		return _profileURL;
	}

	public void setProfileURL(String profileURL) {
		_profileURL = profileURL;
	}

	private long _gitHubContributorId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _gitHubRepositoryId;
	private String _name;
	private String _avatarURL;
	private int _contributions;
	private String _profileURL;

}