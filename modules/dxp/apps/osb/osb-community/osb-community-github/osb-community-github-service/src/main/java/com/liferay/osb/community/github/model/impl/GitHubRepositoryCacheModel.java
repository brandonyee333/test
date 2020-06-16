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

package com.liferay.osb.community.github.model.impl;

import com.liferay.osb.community.github.model.GitHubRepository;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing GitHubRepository in entity cache.
 *
 * @author Haote Chou
 * @generated
 */
public class GitHubRepositoryCacheModel
	implements CacheModel<GitHubRepository>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GitHubRepositoryCacheModel)) {
			return false;
		}

		GitHubRepositoryCacheModel gitHubRepositoryCacheModel =
			(GitHubRepositoryCacheModel)obj;

		if (gitHubRepositoryId ==
				gitHubRepositoryCacheModel.gitHubRepositoryId) {

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
			gitHubRepositoryImpl.setUserName("");
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
			gitHubRepositoryImpl.setOwner("");
		}
		else {
			gitHubRepositoryImpl.setOwner(owner);
		}

		if (name == null) {
			gitHubRepositoryImpl.setName("");
		}
		else {
			gitHubRepositoryImpl.setName(name);
		}

		gitHubRepositoryImpl.setCommits(commits);
		gitHubRepositoryImpl.setOpenIssues(openIssues);
		gitHubRepositoryImpl.setStars(stars);

		if (url == null) {
			gitHubRepositoryImpl.setUrl("");
		}
		else {
			gitHubRepositoryImpl.setUrl(url);
		}

		if (repositoryCreateDate == Long.MIN_VALUE) {
			gitHubRepositoryImpl.setRepositoryCreateDate(null);
		}
		else {
			gitHubRepositoryImpl.setRepositoryCreateDate(
				new Date(repositoryCreateDate));
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(gitHubRepositoryId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (owner == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(owner);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(commits);

		objectOutput.writeInt(openIssues);

		objectOutput.writeInt(stars);

		if (url == null) {
			objectOutput.writeUTF("");
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