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

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JIRAComponent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see JIRAComponent
 * @generated
 */
@ProviderType
public class JIRAComponentCacheModel implements CacheModel<JIRAComponent>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAComponentCacheModel)) {
			return false;
		}

		JIRAComponentCacheModel jiraComponentCacheModel = (JIRAComponentCacheModel)obj;

		if (jiraComponentId == jiraComponentCacheModel.jiraComponentId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jiraComponentId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{jiraComponentId=");
		sb.append(jiraComponentId);
		sb.append(", jiraProjectId=");
		sb.append(jiraProjectId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JIRAComponent toEntityModel() {
		JIRAComponentImpl jiraComponentImpl = new JIRAComponentImpl();

		jiraComponentImpl.setJiraComponentId(jiraComponentId);
		jiraComponentImpl.setJiraProjectId(jiraProjectId);

		if (name == null) {
			jiraComponentImpl.setName(StringPool.BLANK);
		}
		else {
			jiraComponentImpl.setName(name);
		}

		jiraComponentImpl.resetOriginalValues();

		return jiraComponentImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jiraComponentId = objectInput.readLong();

		jiraProjectId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(jiraComponentId);

		objectOutput.writeLong(jiraProjectId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long jiraComponentId;
	public long jiraProjectId;
	public String name;
}