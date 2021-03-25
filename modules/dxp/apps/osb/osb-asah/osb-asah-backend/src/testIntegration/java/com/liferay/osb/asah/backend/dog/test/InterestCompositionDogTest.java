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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.InterestCompositionDog;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@ElasticsearchIndex(
	name = "individuals", resourcePath = "individuals_info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@ElasticsearchIndex(
	name = "interests", resourcePath = "interests_info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@ElasticsearchIndex(
	name = "OSBAsahMarkers", resourcePath = "osbasahmarkers_info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class InterestCompositionDogTest extends BaseCompositionDogTestCase {

	@Test
	public void testGetActiveIndividualSegmentCompositionResultBag() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				true, "1", null, 366637689379787789L, 10, Sort.desc("count"),
				0),
			new LinkedHashMap<String, Long>() {
				{
					put("clicks-and-mortar e-tailers", 2L);
					put("compelling metrics", 2L);
					put("javascript", 1L);
				}
			},
			2, 3, 2);
	}

	@Test
	public void testGetInactiveIndividualSegmentCompositionResultBag() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				false, "1", null, 366637689379787789L, 10, Sort.desc("count"),
				0),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling metrics", 3L);
					put("clicks-and-mortar e-tailers", 2L);
					put("javascript", 1L);
				}
			},
			3, 3, 3);
	}

	@Test
	public void testGetIndividualCompositionResultBag() {
		checkResults(
			_interestCompositionDog.getIndividualCompositionResultBag(
				"1", null, 10, Sort.desc("count"), 0),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling metrics", 4L);
					put("clicks-and-mortar e-tailers", 2L);
					put("javascript", 1L);
				}
			},
			4, 3, 4);
	}

	@Test
	public void testGetIndividualSegmentCompositionResultBagWithKeyword() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				false, "1", "compel", 366637689379787789L, 10,
				Sort.desc("count"), 0),
			new LinkedHashMap<String, Long>() {
				{
					put("compelling metrics", 3L);
				}
			},
			3, 1, 3);
	}

	@Test
	public void testGetIndividualSegmentCompositionResultBagWithSortAsc() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				false, "1", null, 366637689379787789L, 10, Sort.asc("count"),
				0),
			new LinkedHashMap<String, Long>() {
				{
					put("javascript", 1L);
					put("clicks-and-mortar e-tailers", 2L);
					put("compelling metrics", 3L);
				}
			},
			3, 3, 3);
	}

	@Test
	public void testGetIndividualSegmentCompositionResultBagWithSortName() {
		checkResults(
			_interestCompositionDog.getIndividualSegmentCompositionResultBag(
				false, "1", null, 366637689379787789L, 10, Sort.asc("name"), 0),
			new LinkedHashMap<String, Long>() {
				{
					put("clicks-and-mortar e-tailers", 2L);
					put("compelling metrics", 3L);
					put("javascript", 1L);
				}
			},
			3, 3, 3);
	}

	@Autowired
	private InterestCompositionDog _interestCompositionDog;

}