/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model;

import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.ModelListenerRegistrationUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.model.bundle.modellistenerregistrationutil.TestModelListener;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SyntheticBundleRule;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Peter Fellwock
 */
public class ModelListenerRegistrationUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new SyntheticBundleRule("bundle.modellistenerregistrationutil"));

	@Test
	public void testGetModelListeners() {
		ModelListener<Contact>[] modelListeners =
			ModelListenerRegistrationUtil.getModelListeners(Contact.class);

		for (ModelListener<Contact> modelListener : modelListeners) {
			Class<?> clazz = modelListener.getClass();

			String className = clazz.getName();

			if (className.equals(TestModelListener.class.getName())) {
				return;
			}
		}

		Assert.fail();
	}

}