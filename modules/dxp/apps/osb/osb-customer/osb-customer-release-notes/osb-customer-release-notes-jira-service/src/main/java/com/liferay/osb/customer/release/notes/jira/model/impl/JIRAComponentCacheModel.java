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

package com.liferay.osb.customer.release.notes.jira.model.impl;

import com.liferay.osb.customer.release.notes.jira.model.JIRAComponent;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JIRAComponent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAComponentCacheModel
	implements CacheModel<JIRAComponent>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAComponentCacheModel)) {
			return false;
		}

		JIRAComponentCacheModel jiraComponentCacheModel =
			(JIRAComponentCacheModel)obj;

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
			jiraComponentImpl.setName("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(jiraComponentId);

		objectOutput.writeLong(jiraProjectId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long jiraComponentId;
	public long jiraProjectId;
	public String name;

}