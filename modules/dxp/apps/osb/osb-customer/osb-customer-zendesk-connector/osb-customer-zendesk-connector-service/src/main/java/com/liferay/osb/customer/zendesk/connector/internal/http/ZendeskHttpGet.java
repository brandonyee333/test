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

package com.liferay.osb.customer.zendesk.connector.internal.http;

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

/**
 * @author Kyle Bischof
 */
public class ZendeskHttpGet extends HttpEntityEnclosingRequestBase {

	public static final String METHOD_NAME = "GET";

	public ZendeskHttpGet() {
	}

	public ZendeskHttpGet(final String uri) {
		setURI(URI.create(uri));
	}

	public ZendeskHttpGet(final URI uri) {
		setURI(uri);
	}

	public String getMethod() {
		return METHOD_NAME;
	}

}