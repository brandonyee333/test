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

package com.liferay.osb.community.github.model;

import aQute.bnd.annotation.ProviderType;

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
@ProviderType
public class GitHubRepositorySoap implements Serializable {
	public static GitHubRepositorySoap toSoapModel(GitHubRepository model) {
		GitHubRepositorySoap soapModel = new GitHubRepositorySoap();

		soapModel.setGitHubRepositoryId(model.getGitHubRepositoryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOwner(model.getOwner());
		soapModel.setName(model.getName());
		soapModel.setCommits(model.getCommits());
		soapModel.setOpenIssues(model.getOpenIssues());
		soapModel.setStars(model.getStars());
		soapModel.setUrl(model.getUrl());
		soapModel.setRepositoryCreateDate(model.getRepositoryCreateDate());

		return soapModel;
	}

	public static GitHubRepositorySoap[] toSoapModels(GitHubRepository[] models) {
		GitHubRepositorySoap[] soapModels = new GitHubRepositorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static GitHubRepositorySoap[][] toSoapModels(
		GitHubRepository[][] models) {
		GitHubRepositorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new GitHubRepositorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new GitHubRepositorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static GitHubRepositorySoap[] toSoapModels(
		List<GitHubRepository> models) {
		List<GitHubRepositorySoap> soapModels = new ArrayList<GitHubRepositorySoap>(models.size());

		for (GitHubRepository model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new GitHubRepositorySoap[soapModels.size()]);
	}

	public GitHubRepositorySoap() {
	}

	public long getPrimaryKey() {
		return _gitHubRepositoryId;
	}

	public void setPrimaryKey(long pk) {
		setGitHubRepositoryId(pk);
	}

	public long getGitHubRepositoryId() {
		return _gitHubRepositoryId;
	}

	public void setGitHubRepositoryId(long gitHubRepositoryId) {
		_gitHubRepositoryId = gitHubRepositoryId;
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

	public String getOwner() {
		return _owner;
	}

	public void setOwner(String owner) {
		_owner = owner;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public int getCommits() {
		return _commits;
	}

	public void setCommits(int commits) {
		_commits = commits;
	}

	public int getOpenIssues() {
		return _openIssues;
	}

	public void setOpenIssues(int openIssues) {
		_openIssues = openIssues;
	}

	public int getStars() {
		return _stars;
	}

	public void setStars(int stars) {
		_stars = stars;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public Date getRepositoryCreateDate() {
		return _repositoryCreateDate;
	}

	public void setRepositoryCreateDate(Date repositoryCreateDate) {
		_repositoryCreateDate = repositoryCreateDate;
	}

	private long _gitHubRepositoryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _owner;
	private String _name;
	private int _commits;
	private int _openIssues;
	private int _stars;
	private String _url;
	private Date _repositoryCreateDate;
}