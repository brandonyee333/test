/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.search.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.dynamic.data.mapping.configuration.DDMIndexerConfiguration;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.util.SearchContextTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Jorge Díaz
 */
@RunWith(Arquillian.class)
public class DLFileEntrySearchLegacyDDMIndexFieldsTest
	extends DLFileEntrySearchTest {

	@BeforeClass
	public static void setUpClass() throws Exception {
		ConfigurationTestUtil.saveConfiguration(
			DDMIndexerConfiguration.class.getName(),
			new HashMapDictionary() {
				{
					put("enableLegacyDDMIndexFields", true);
				}
			});
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		ConfigurationTestUtil.deleteConfiguration(
			DDMIndexerConfiguration.class.getName());
	}

	@Override
	@Test
	public void testSearchStatus() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext(group.getGroupId());

		SearchContext searchContext = SearchContextTestUtil.getSearchContext(
			group.getGroupId());

		int initialBaseModelsCount = 0;

		assertBaseModelsCount(initialBaseModelsCount, "1.0", searchContext);

		BaseModel<?> parentBaseModel = getParentBaseModel(
			group, serviceContext);

		baseModel = addBaseModel(
			parentBaseModel, false, "Version 1.0", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount, searchContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		baseModel = updateBaseModel(baseModel, "Version 1.1", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount + 1, "1.1", searchContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_SAVE_DRAFT);

		baseModel = updateBaseModel(baseModel, "Version 1.2", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount + 1, "1.1", searchContext);

		serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

		baseModel = updateBaseModel(baseModel, "Version 1.3", serviceContext);

		assertBaseModelsCount(initialBaseModelsCount + 1, "1.3", searchContext);
	}

}