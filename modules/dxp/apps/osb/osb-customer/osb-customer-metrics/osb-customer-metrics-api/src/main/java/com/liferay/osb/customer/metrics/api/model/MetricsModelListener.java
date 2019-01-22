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

package com.liferay.osb.customer.metrics.api.model;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;

/**
 * @author Jenny Chen
 */
public interface MetricsModelListener<T> extends ModelListener<T> {

	public boolean ignoreAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public boolean ignoreUpdate(T model) throws ModelListenerException;

}