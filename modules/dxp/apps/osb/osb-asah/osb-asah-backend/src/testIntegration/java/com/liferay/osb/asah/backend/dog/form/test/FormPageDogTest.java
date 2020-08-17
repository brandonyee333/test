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

package com.liferay.osb.asah.backend.dog.form.test;

import com.liferay.osb.asah.backend.dog.form.FormPageDog;
import com.liferay.osb.asah.backend.dog.helper.SearchQueryContext;
import com.liferay.osb.asah.backend.dog.test.DogTestUtil;
import com.liferay.osb.asah.backend.model.AssetType;
import com.liferay.osb.asah.backend.model.FormFieldMetric;
import com.liferay.osb.asah.backend.model.FormPageMetric;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class FormPageDogTest {

	@ElasticsearchIndex(
		name = "forms", resourcePath = "forms_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testFormPageMetrics() {
		List<FormPageMetric> formPageMetrics = _formPageDog.getFormPageMetrics(
			new SearchQueryContext("37615", AssetType.FORM));

		Assert.assertEquals(
			formPageMetrics.toString(), 1, formPageMetrics.size());

		FormPageMetric formPageMetric = formPageMetrics.get(0);

		DogTestUtil.assertMetric(2, formPageMetric.getPageViewsMetric());

		List<FormFieldMetric> formFieldMetrics =
			formPageMetric.getFormFieldMetrics();

		Assert.assertEquals(
			formFieldMetrics.toString(), 1, formPageMetrics.size());

		FormFieldMetric formFieldMetric = formFieldMetrics.get(0);

		Assert.assertEquals("TextField", formFieldMetric.getFieldName());

		DogTestUtil.assertMetric(
			0, formFieldMetric.getFieldAbandonmentsMetric());
		DogTestUtil.assertMetric(
			2, formFieldMetric.getFieldInteractionsMetric());
		DogTestUtil.assertMetric(1, formFieldMetric.getFieldRefilledMetric());
	}

	@Autowired
	private FormPageDog _formPageDog;

}