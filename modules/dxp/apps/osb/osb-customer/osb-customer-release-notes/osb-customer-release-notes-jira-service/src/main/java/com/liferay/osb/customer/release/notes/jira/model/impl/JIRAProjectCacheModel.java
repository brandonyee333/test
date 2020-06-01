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

import com.liferay.osb.customer.release.notes.jira.model.JIRAProject;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JIRAProject in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAProjectCacheModel
	implements CacheModel<JIRAProject>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectCacheModel)) {
			return false;
		}

		JIRAProjectCacheModel jiraProjectCacheModel =
			(JIRAProjectCacheModel)obj;

		if (jiraProjectId == jiraProjectCacheModel.jiraProjectId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jiraProjectId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{jiraProjectId=");
		sb.append(jiraProjectId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JIRAProject toEntityModel() {
		JIRAProjectImpl jiraProjectImpl = new JIRAProjectImpl();

		jiraProjectImpl.setJiraProjectId(jiraProjectId);

		if (key == null) {
			jiraProjectImpl.setKey("");
		}
		else {
			jiraProjectImpl.setKey(key);
		}

		if (name == null) {
			jiraProjectImpl.setName("");
		}
		else {
			jiraProjectImpl.setName(name);
		}

		jiraProjectImpl.resetOriginalValues();

		return jiraProjectImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jiraProjectId = objectInput.readLong();
		key = objectInput.readUTF();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(jiraProjectId);

		if (key == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long jiraProjectId;
	public String key;
	public String name;

}