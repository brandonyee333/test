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

package com.liferay.osb.asah.backend.model;

/**
 * @author Rachael Koestartyo
 */
public class DataSource {

	public DataSource(String id, String name, String url) {
		_id = id;
		_name = name;
		_url = url;
	}

	public String getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	public String getUrl() {
		return _url;
	}

	private final String _id;
	private final String _name;
	private final String _url;

}