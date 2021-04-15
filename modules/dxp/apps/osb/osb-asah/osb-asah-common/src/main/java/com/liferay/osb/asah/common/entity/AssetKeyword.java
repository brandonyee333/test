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

package com.liferay.osb.asah.common.entity;

import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class AssetKeyword {

	public AssetKeyword() {
	}

	public AssetKeyword(String keyword, String type) {
		_keyword = keyword;
		_type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetKeyword)) {
			return false;
		}

		AssetKeyword assetKeyword = (AssetKeyword)obj;

		if (Objects.equals(_keyword, assetKeyword._keyword) &&
			Objects.equals(_type, assetKeyword._type)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getKeyword() {
		return _keyword;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_keyword, _type);
	}

	public void setKeyword(String keyword) {
		_keyword = keyword;
	}

	public void setType(String type) {
		_type = type;
	}

	@Transient
	private String _keyword;

	@Transient
	private String _type;

}