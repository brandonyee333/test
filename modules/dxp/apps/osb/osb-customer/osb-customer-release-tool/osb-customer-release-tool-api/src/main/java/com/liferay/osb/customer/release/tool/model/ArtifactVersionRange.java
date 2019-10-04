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

package com.liferay.osb.customer.release.tool.model;

/**
 * @author Amos Fong
 */
public class ArtifactVersionRange {

	public ArtifactVersionRange() {
	}

	public String getFromVersion() {
		return _fromVersion;
	}

	public String getGroup() {
		return _group;
	}

	public String getName() {
		return _name;
	}

	public String getPackaging() {
		return _packaging;
	}

	public int getRepository() {
		return _repository;
	}

	public String getToVersion() {
		return _toVersion;
	}

	public void setFromVersion(String fromVersion) {
		_fromVersion = fromVersion;
	}

	public void setGroup(String group) {
		_group = group;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setPackaging(String packaging) {
		_packaging = packaging;
	}

	public void setRepository(int repository) {
		_repository = repository;
	}

	public void setToVersion(String toVersion) {
		_toVersion = toVersion;
	}

	private String _fromVersion;
	private String _group;
	private String _name;
	private String _packaging;
	private int _repository;
	private String _toVersion;

}