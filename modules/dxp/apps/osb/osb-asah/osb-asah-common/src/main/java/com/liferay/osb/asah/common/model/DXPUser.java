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

package com.liferay.osb.asah.common.model;

import com.liferay.osb.asah.common.entity.DXPEntity;

import java.util.Objects;

/**
 * @author Matthew Kong
 */
public class User extends DXPEntity {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!super.equals(obj)) {
			return false;
		}

		if (!(obj instanceof User)) {
			return false;
		}

		User user = (User)obj;

		if (Objects.equals(getId(), user.getId()) &&
			Objects.equals(getName(), user.getName()) &&
			Objects.equals(_firstName, user._firstName) &&
			Objects.equals(_lastName, user._lastName) &&
			Objects.equals(_screenName, user._screenName)) {

			return true;
		}

		return false;
	}

	@Override
	public String getName() {
		return _firstName + " " + _lastName;
	}

	public String getScreenName() {
		return _screenName;
	}

	@Override
	public Type getType() {
		return DXPEntity.Type.USER;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			getId(), getName(), _firstName, _lastName, _screenName);
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	private String _firstName;
	private String _lastName;
	private String _screenName;

}