/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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