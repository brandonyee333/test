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

import com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JIRAProjectVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAProjectVersionCacheModel
	implements CacheModel<JIRAProjectVersion>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAProjectVersionCacheModel)) {
			return false;
		}

		JIRAProjectVersionCacheModel jiraProjectVersionCacheModel =
			(JIRAProjectVersionCacheModel)obj;

		if (jiraProjectVersionId ==
				jiraProjectVersionCacheModel.jiraProjectVersionId) {

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
		JIRAProjectVersionImpl jiraProjectVersionImpl =
			new JIRAProjectVersionImpl();

		jiraProjectVersionImpl.setJiraProjectVersionId(jiraProjectVersionId);
		jiraProjectVersionImpl.setJiraProjectId(jiraProjectId);

		if (name == null) {
			jiraProjectVersionImpl.setName("");
		}
		else {
			jiraProjectVersionImpl.setName(name);
		}

		if (released == null) {
			jiraProjectVersionImpl.setReleased("");
		}
		else {
			jiraProjectVersionImpl.setReleased(released);
		}

		if (archived == null) {
			jiraProjectVersionImpl.setArchived("");
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
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(jiraProjectVersionId);

		objectOutput.writeLong(jiraProjectId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (released == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(released);
		}

		if (archived == null) {
			objectOutput.writeUTF("");
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