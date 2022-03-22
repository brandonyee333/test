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

package com.liferay.osb.asah.common.messaging.model;

import java.util.Map;

/**
 * @author Robson Pastor
 */
public class Message<T> {

	public Message(
		String ackId, Map<String, String> attributes, String id, T object) {

		_ackId = ackId;
		_attributes = attributes;
		_id = id;
		_object = object;
	}

	public String getAckId() {
		return _ackId;
	}

	public Map<String, String> getAttributes() {
		return _attributes;
	}

	public String getId() {
		return _id;
	}

	public T getObject() {
		return _object;
	}

	private final String _ackId;
	private final Map<String, String> _attributes;
	private final String _id;
	private final T _object;

}