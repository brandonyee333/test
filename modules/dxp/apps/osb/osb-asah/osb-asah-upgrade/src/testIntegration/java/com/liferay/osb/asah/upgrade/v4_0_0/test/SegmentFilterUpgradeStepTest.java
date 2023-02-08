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

package com.liferay.osb.asah.upgrade.v4_0_0.test;

import com.liferay.osb.asah.common.entity.BQEvent;
import com.liferay.osb.asah.common.entity.BQEventProperty;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.BQEventPropertyRepository;
import com.liferay.osb.asah.common.repository.BQEventRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentFilterUpgradeStep;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Ivica Cardic
 */
public class SegmentFilterUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		BQEvent bqEvent = new BQEvent();

		bqEvent.setApplicationId("Blog");
		bqEvent.setDataSourceId(370994124094927024L);
		bqEvent.setEventId("blogViewed");
		bqEvent.setId("1");

		_bqEventRepository.save(bqEvent);

		bqEvent.setApplicationId("Blog");
		bqEvent.setDataSourceId(370994124094927024L);
		bqEvent.setEventId("blogViewed");
		bqEvent.setId("2");

		_bqEventRepository.save(bqEvent);

		BQEventProperty bqEventProperty = new BQEventProperty();

		bqEventProperty.setId("1");
		bqEventProperty.setName("classPK");
		bqEventProperty.setValue("id");

		_bqEventPropertyRepository.save(bqEventProperty);

		bqEventProperty.setId("1");
		bqEventProperty.setName("title");
		bqEventProperty.setValue("title");

		_bqEventPropertyRepository.save(bqEventProperty);

		bqEventProperty = new BQEventProperty();

		bqEventProperty.setId("2");
		bqEventProperty.setName("classPK");
		bqEventProperty.setValue("id");

		_bqEventPropertyRepository.save(bqEventProperty);

		bqEventProperty.setId("2");
		bqEventProperty.setName("title");
		bqEventProperty.setValue("title");

		_bqEventPropertyRepository.save(bqEventProperty);

		Segment segment = new Segment();

		segment.setFilter(
			"(activities.filterByCount(filter='(activityKey eq " +
				"''Blog#blogViewed#370994124094927024'' and day gt " +
					"''last24Hours'')',operator='ge',value=1))");
		segment.setId(1L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 1");
		segment.setType(Segment.Type.DYNAMIC);

		_segmentRepository.save(segment);
	}

	@AfterEach
	public void tearDown() throws Exception {
		_bqEventRepository.deleteAll();
		_bqEventPropertyRepository.deleteAll();
		_segmentRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		_segmentFilterUpgradeStep.upgrade("");

		Optional<Segment> segmentOptional = _segmentRepository.findById(1L);

		Segment segment = segmentOptional.get();

		Assertions.assertEquals(
			"(activities.filterByCount(filter='(activityKey eq " +
				"''1829cd6956423819a16105ccbbdc9b351da4bef547ecd46c1c" +
					"183fb293732aaa'' and day gt ''last24Hours'')'," +
						"operator='ge',value=1))",
			segment.getFilter());
	}

	@Autowired
	private BQEventPropertyRepository _bqEventPropertyRepository;

	@Autowired
	private BQEventRepository _bqEventRepository;

	@Autowired
	private SegmentFilterUpgradeStep _segmentFilterUpgradeStep;

	@Autowired
	private SegmentRepository _segmentRepository;

}