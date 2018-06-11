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

package com.liferay.osb.customer.release.notes.web.internal.util.comparator;

import com.liferay.osb.customer.release.notes.web.internal.util.apichange.APIChangeMethod;

import java.util.Comparator;

/**
 * @author Samuel Kong
 */
public class APIChangeMethodComparator implements Comparator<APIChangeMethod> {

	@Override
	public int compare(
		APIChangeMethod apiChangeMethod1, APIChangeMethod apiChangeMethod2) {

		String signature1 = apiChangeMethod1.getSignature();
		String signature2 = apiChangeMethod2.getSignature();

		return signature1.compareTo(signature2);
	}

}