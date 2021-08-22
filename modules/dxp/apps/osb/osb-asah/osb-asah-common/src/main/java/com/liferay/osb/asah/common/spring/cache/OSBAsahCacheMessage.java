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

package com.liferay.osb.asah.common.spring.cache;

import java.io.Serializable;

/**
 * @author Inácio Nery
 */
public class OSBAsahCacheMessage implements Serializable {

	public OSBAsahCacheMessage() {
	}

	public OSBAsahCacheMessage(String hostAddress, Object key, String name) {
		_hostAddress = hostAddress;
		_key = key;
		_name = name;
	}

	public String getHostAddress() {
		return _hostAddress;
	}

	public Object getKey() {
		return _key;
	}

	public String getName() {
		return _name;
	}

	public void setHostAddress(String hostAddress) {
		_hostAddress = hostAddress;
	}

	public void setKey(Object key) {
		_key = key;
	}

	public void setName(String name) {
		_name = name;
	}

	private String _hostAddress;
	private Object _key;
	private String _name;

}