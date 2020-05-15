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

package com.liferay.osb.asah.stream.curator.model.page;

import com.liferay.osb.asah.stream.curator.model.BaseModel;

/**
 * @author Marcellus Tavares
 */
public abstract class BasePageModel extends BaseModel {

	public String getURL() {
		return _url;
	}

	@Override
	public boolean isAsset() {
		return false;
	}

	public void setURL(String url) {
		_url = url;
	}

	private String _url;

}