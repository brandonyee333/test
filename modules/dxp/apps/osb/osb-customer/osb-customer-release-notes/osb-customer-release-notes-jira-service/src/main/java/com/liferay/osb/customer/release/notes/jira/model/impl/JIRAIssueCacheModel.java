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

import com.liferay.osb.customer.release.notes.jira.model.JIRAIssue;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing JIRAIssue in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JIRAIssueCacheModel
	implements CacheModel<JIRAIssue>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAIssueCacheModel)) {
			return false;
		}

		JIRAIssueCacheModel jiraIssueCacheModel = (JIRAIssueCacheModel)obj;

		if (jiraIssueId == jiraIssueCacheModel.jiraIssueId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, jiraIssueId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{jiraIssueId=");
		sb.append(jiraIssueId);
		sb.append(", jiraProjectId=");
		sb.append(jiraProjectId);
		sb.append(", issueNumber=");
		sb.append(issueNumber);
		sb.append(", type=");
		sb.append(type);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", description=");
		sb.append(description);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public JIRAIssue toEntityModel() {
		JIRAIssueImpl jiraIssueImpl = new JIRAIssueImpl();

		jiraIssueImpl.setJiraIssueId(jiraIssueId);
		jiraIssueImpl.setJiraProjectId(jiraProjectId);
		jiraIssueImpl.setIssueNumber(issueNumber);
		jiraIssueImpl.setType(type);

		if (summary == null) {
			jiraIssueImpl.setSummary("");
		}
		else {
			jiraIssueImpl.setSummary(summary);
		}

		if (description == null) {
			jiraIssueImpl.setDescription("");
		}
		else {
			jiraIssueImpl.setDescription(description);
		}

		jiraIssueImpl.setPriority(priority);

		jiraIssueImpl.resetOriginalValues();

		return jiraIssueImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		jiraIssueId = objectInput.readLong();

		jiraProjectId = objectInput.readLong();

		issueNumber = objectInput.readLong();

		type = objectInput.readInt();
		summary = objectInput.readUTF();
		description = objectInput.readUTF();

		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(jiraIssueId);

		objectOutput.writeLong(jiraProjectId);

		objectOutput.writeLong(issueNumber);

		objectOutput.writeInt(type);

		if (summary == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(summary);
		}

		if (description == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(description);
		}

		objectOutput.writeInt(priority);
	}

	public long jiraIssueId;
	public long jiraProjectId;
	public long issueNumber;
	public int type;
	public String summary;
	public String description;
	public int priority;

}