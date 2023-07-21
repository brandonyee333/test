/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.test.rule.PACLTestRule;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Raymond Augé
 */
public class AWTPermissionTest {

	@ClassRule
	@Rule
	public static final PACLTestRule paclTestRule = new PACLTestRule();

	@Test
	public void test1() throws Exception {
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();

			toolkit.addAWTEventListener(
				new AWTEventListener() {

					@Override
					public void eventDispatched(AWTEvent event) {
					}

				},
				AWTEvent.ACTION_EVENT_MASK);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test2() throws Exception {
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		toolkit.getSystemEventQueue();
	}

}