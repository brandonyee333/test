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

package com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.test;

import com.liferay.osb.asah.common.converter.helper.FilterStringConverterHelper;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoActivitiesFilterStringConverterHelper;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Vishal Reddy
 */
@ContextConfiguration(classes = OSBAsahSpringBootApplication.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class FaroInfoActivitiesFilterStringConverterHelperTest
	extends BaseFaroInfoFilterStringConverterHelperTestCase {

	@Override
	public FilterStringConverterHelper getFilterStringConverterHelper() {
		return _faroInfoActivitiesFilterStringConverterHelper;
	}

	@Test
	public void testAccountIdEq() throws Exception {
		testFilterString(
			"activities", "accountId eq '346306769708391202'",
			"348853926240043268", "348853926240043280", "348853926240065234",
			"348853932273096308", "357731168283574900", "357731168283574901",
			"357731168283574902", "357731168283574903", "357731168283574904",
			"349395508504086369");
	}

	@Test
	public void testAccountIdNe() throws Exception {
		testFilterString("activities", "accountId ne '346306769708391202'");
	}

	@Test
	public void testActivityTypeEq() throws Exception {
		testFilterString(
			"activities", "activityType eq 'BROWSE'", "348853926240043268",
			"348853926240043280", "348853926240065234", "348853932273096308",
			"357731168283574900", "357731168283574901", "357731168283574902",
			"357731168283574903", "357731168283574904", "349395508504086369");
	}

	@Test
	public void testActivityTypeNe() throws Exception {
		testFilterString("activities", "activityType ne 'BROWSE'");
	}

	@Test
	public void testApplicationIdNe() throws Exception {
		testFilterString(
			"activities", "applicationId ne 'Page'", "349395508504086369");
	}

	@Test
	public void testObjectNameEq() throws Exception {
		testFilterString(
			"activities", "object.name eq 'extend clicks-and-mortar e-tailers'",
			"348853926240043268", "348853926240043280", "348853926240065234",
			"357731168283574900", "357731168283574901", "357731168283574902",
			"357731168283574903", "357731168283574904");
	}

	@Autowired
	private FaroInfoActivitiesFilterStringConverterHelper
		_faroInfoActivitiesFilterStringConverterHelper;

}