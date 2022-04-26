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

package com.liferay.osb.asah.backend.dto;

import com.liferay.osb.asah.common.entity.BQUser;
import com.liferay.osb.asah.common.graphql.GraphQLType;

/**
 * @author Marcos Martins
 */
@GraphQLType
public class DXPUserDTO extends DXPEntityDTO {

	public DXPUserDTO(BQUser bqUser) {
		super(bqUser);

		_screenName = bqUser.getScreenName();
	}

	public String getScreenName() {
		return _screenName;
	}

	private final String _screenName;

}