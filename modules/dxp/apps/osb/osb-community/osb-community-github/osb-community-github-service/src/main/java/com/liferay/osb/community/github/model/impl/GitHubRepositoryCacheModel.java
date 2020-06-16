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

package com.liferay.osb.community.github.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.github.model.GitHubRepository;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GitHubRepository in entity cache.
 *
 * @author Haote Chou
 * @see GitHubRepository
 * @generated
 */
@ProviderType
public class GitHubRepositoryCacheModel implements CacheModel<GitHubRepository>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GitHubRepositoryCacheModel)) {
			return false;
		}

		GitHubRepositoryCacheModel gitHubRepositoryCacheModel = (GitHubRepositoryCacheModel)obj;

		if (gitHubRepositoryId == gitHubRepositoryCacheModel.gitHubRepositoryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, gitHubRepositoryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{gitHubRepositoryId=");
		sb.append(gitHubRepositoryId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", owner=");
		sb.append(owner);
		sb.append(", name=");
		sb.append(name);
		sb.append(", commits=");
		sb.append(commits);
		sb.append(", openIssues=");
		sb.append(openIssues);
		sb.append(", stars=");
		sb.append(stars);
		sb.append(", url=");
		sb.append(url);
		sb.append(", repositoryCreateDate=");
		sb.append(repositoryCreateDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public GitHubRepository toEntityModel() {
		GitHubRepositoryImpl gitHubRepositoryImpl = new GitHubRepositoryImpl();

		gitHubRepositoryImpl.setGitHubRepositoryId(gitHubRepositoryId);
		gitHubRepositoryImpl.setCompanyId(companyId);
		gitHubRepositoryImpl.setUserId(userId);

		if (userName == null) {
			gitHubRepositoryImpl.setUserName(StringPool.BLANK);
		}
		else {
			gitHubRepositoryImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			gitHubRepositoryImpl.setCreateDate(null);
		}
		else {
			gitHubRepositoryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			gitHubRepositoryImpl.setModifiedDate(null);
		}
		else {
			gitHubRepositoryImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (owner == null) {
			gitHubRepositoryImpl.setOwner(StringPool.BLANK);
		}
		else {
			gitHubRepositoryImpl.setOwner(owner);
		}

		if (name == null) {
			gitHubRepositoryImpl.setName(StringPool.BLANK);
		}
		else {
			gitHubRepositoryImpl.setName(name);
		}

		gitHubRepositoryImpl.setCommits(commits);
		gitHubRepositoryImpl.setOpenIssues(openIssues);
		gitHubRepositoryImpl.setStars(stars);

		if (url == null) {
			gitHubRepositoryImpl.setUrl(StringPool.BLANK);
		}
		else {
			gitHubRepositoryImpl.setUrl(url);
		}

		if (repositoryCreateDate == Long.MIN_VALUE) {
			gitHubRepositoryImpl.setRepositoryCreateDate(null);
		}
		else {
			gitHubRepositoryImpl.setRepositoryCreateDate(new Date(
					repositoryCreateDate));
		}

		gitHubRepositoryImpl.resetOriginalValues();

		return gitHubRepositoryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		gitHubRepositoryId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		owner = objectInput.readUTF();
		name = objectInput.readUTF();

		commits = objectInput.readInt();

		openIssues = objectInput.readInt();

		stars = objectInput.readInt();
		url = objectInput.readUTF();
		repositoryCreateDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(gitHubRepositoryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (owner == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(owner);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(commits);

		objectOutput.writeInt(openIssues);

		objectOutput.writeInt(stars);

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeLong(repositoryCreateDate);
	}

	public long gitHubRepositoryId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String owner;
	public String name;
	public int commits;
	public int openIssues;
	public int stars;
	public String url;
	public long repositoryCreateDate;
}