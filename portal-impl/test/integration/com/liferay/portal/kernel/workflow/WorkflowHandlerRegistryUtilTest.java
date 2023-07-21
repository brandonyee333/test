/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.workflow.bundle.workflowhandlerregistryutil.TestWorkflowHandler;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.SyntheticBundleRule;
import com.liferay.portal.util.test.AtomicState;

import java.io.Serializable;

import java.util.HashMap;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Peter Fellwock
 */
public class WorkflowHandlerRegistryUtilTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			new SyntheticBundleRule("bundle.workflowhandlerregistryutil"));

	@BeforeClass
	public static void setUpClass() {
		_atomicState = new AtomicState();
	}

	@AfterClass
	public static void tearDownClass() {
		_atomicState.close();
	}

	@Test
	public void testGetWorkflowHandler() {
		WorkflowHandler<Object> workflowHandler =
			WorkflowHandlerRegistryUtil.getWorkflowHandler(
				TestWorkflowHandler.class.getName());

		Assert.assertEquals(
			TestWorkflowHandler.class.getName(),
			workflowHandler.getClassName());
	}

	@Test
	public void testGetWorkflowHandlers() {
		List<WorkflowHandler<?>> workflowHandlers =
			WorkflowHandlerRegistryUtil.getWorkflowHandlers();

		for (WorkflowHandler<?> workflowHandler : workflowHandlers) {
			String className = workflowHandler.getClassName();

			if (className.equals(TestWorkflowHandler.class.getName())) {
				return;
			}
		}

		Assert.fail();
	}

	@Test
	public void testStartWorkflowInstance1() {
		_atomicState.reset();

		try {
			ServiceContext serviceContext = new ServiceContext();

			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				1, 1, 1, TestWorkflowHandler.class.getName(), 1, null,
				serviceContext, new HashMap<String, Serializable>());
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	@Test
	public void testStartWorkflowInstance2() {
		_atomicState.reset();

		try {
			ServiceContext serviceContext = new ServiceContext();

			WorkflowHandlerRegistryUtil.startWorkflowInstance(
				1, 1, 1, TestWorkflowHandler.class.getName(), 1, null,
				serviceContext);
		}
		catch (Exception e) {
			Assert.fail();
		}

		Assert.assertTrue(_atomicState.isSet());
	}

	private static AtomicState _atomicState;

}