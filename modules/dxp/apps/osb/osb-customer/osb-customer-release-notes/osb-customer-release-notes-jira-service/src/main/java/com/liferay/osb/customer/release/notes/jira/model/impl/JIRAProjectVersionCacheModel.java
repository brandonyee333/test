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

package com.liferay.osb.customer.release.notes.jira.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JIRAProjectVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectVersion
 * @generated
 */
@ProviderType
public class JIRAProjectVersionCacheModel implements CacheModel<JIRAProjectVersion>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectVersionCacheModel)) {
			return false;
		}

		JIRAProjectVersionCacheModel jiraProjectVersionCacheModel = (JIRAProjectVersionCacheModel)obj;

		if (jiraProjectVersionId == jiraProjectVersionCacheModel.jiraProjectVersionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jiraProjectVersionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{jiraProjectVersionId=");
		sb.append(jiraProjectVersionId);
		sb.append(", jiraProjectId=");
		sb.append(jiraProjectId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", released=");
		sb.append(released);
		sb.append(", archived=");
		sb.append(archived);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JIRAProjectVersion toEntityModel() {
		JIRAProjectVersionImpl jiraProjectVersionImpl = new JIRAProjectVersionImpl();

		jiraProjectVersionImpl.setJiraProjectVersionId(jiraProjectVersionId);
		jiraProjectVersionImpl.setJiraProjectId(jiraProjectId);

		if (name == null) {
			jiraProjectVersionImpl.setName(StringPool.BLANK);
		}
		else {
			jiraProjectVersionImpl.setName(name);
		}

		if (released == null) {
			jiraProjectVersionImpl.setReleased(StringPool.BLANK);
		}
		else {
			jiraProjectVersionImpl.setReleased(released);
		}

		if (archived == null) {
			jiraProjectVersionImpl.setArchived(StringPool.BLANK);
		}
		else {
			jiraProjectVersionImpl.setArchived(archived);
		}

		jiraProjectVersionImpl.resetOriginalValues();

		return jiraProjectVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jiraProjectVersionId = objectInput.readLong();

		jiraProjectId = objectInput.readLong();
		name = objectInput.readUTF();
		released = objectInput.readUTF();
		archived = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(jiraProjectVersionId);

		objectOutput.writeLong(jiraProjectId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (released == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(released);
		}

		if (archived == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(archived);
		}
	}

	public long jiraProjectVersionId;
	public long jiraProjectId;
	public String name;
	public String released;
	public String archived;
}