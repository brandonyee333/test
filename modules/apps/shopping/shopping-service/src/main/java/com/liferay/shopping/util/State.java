/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.shopping.util;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class State {

	public State(String id, String name) {
		_id = id;
		_name = name;
	}

	public int compareTo(Object obj) {
		State state = (State)obj;

		if ((getId() != null) && (state.getId() != null)) {
			String lowerCaseId = StringUtil.toLowerCase(getId());

			return lowerCaseId.compareTo(StringUtil.toLowerCase(state.getId()));
		}
		else if ((getName() != null) && (state.getName() != null)) {
			String lowerCaseName = StringUtil.toLowerCase(getName());

			return lowerCaseName.compareTo(
				StringUtil.toLowerCase(state.getName()));
		}

		return -1;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof State)) {
			return false;
		}

		State state = (State)obj;

		if ((getId() != null) && (state.getId() != null)) {
			return StringUtil.equalsIgnoreCase(getId(), state.getId());
		}
		else if ((getName() != null) && (state.getName() != null)) {
			return StringUtil.equalsIgnoreCase(getName(), state.getName());
		}

		return false;
	}

	public String getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, _id);

		return HashUtil.hash(hashCode, _name);
	}

	private final String _id;
	private final String _name;

}