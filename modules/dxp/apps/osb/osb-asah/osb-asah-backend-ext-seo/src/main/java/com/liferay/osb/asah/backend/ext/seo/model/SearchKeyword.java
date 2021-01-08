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

package com.liferay.osb.asah.backend.ext.seo.model;

import com.univocity.parsers.annotations.Parsed;

/**
 * @author David Arques
 */
public class SearchKeyword {

	public SearchKeyword() {
	}

	public String getKeyword() {
		return _keyword;
	}

	public int getPosition() {
		return _position;
	}

	public int getSearchVolume() {
		return _searchVolume;
	}

	public int getTraffic() {
		return _traffic;
	}

	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	public void setPosition(int position) {
		_position = position;
	}

	public void setSearchVolume(int searchVolume) {
		_searchVolume = searchVolume;
	}

	public void setTraffic(int traffic) {
		_traffic = traffic;
	}

	@Parsed(index = 0)
	private String _keyword;

	@Parsed(index = 1)
	private int _position;

	@Parsed(index = 2)
	private int _searchVolume;

	@Parsed(index = 3)
	private int _traffic;

}