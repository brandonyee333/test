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

package com.liferay.lcs.client.internal.osgi.framework;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

/**
 * @author Igor Beslic
 */
public class MockServiceReference<S> implements ServiceReference {

	public MockServiceReference(String componentName, String reference) {
		_componentName = componentName;
		_reference = reference;
	}

	@Override
	public int compareTo(Object reference) {
		if (reference.equals(_reference)) {
			return 0;
		}

		return -1;
	}

	@Override
	public Bundle getBundle() {
		return null;
	}

	@Override
	public Object getProperty(String key) {
		if (key.equals("component.name")) {
			return _componentName;
		}

		return null;
	}

	@Override
	public String[] getPropertyKeys() {
		return new String[0];
	}

	@Override
	public Bundle[] getUsingBundles() {
		return new Bundle[0];
	}

	@Override
	public boolean isAssignableTo(Bundle bundle, String className) {
		return false;
	}

	private final String _componentName;
	private final String _reference;

}