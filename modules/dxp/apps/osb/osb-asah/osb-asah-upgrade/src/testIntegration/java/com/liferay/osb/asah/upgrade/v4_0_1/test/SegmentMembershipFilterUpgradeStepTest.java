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

package com.liferay.osb.asah.upgrade.v4_0_1.test;

import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_1.SegmentMembershipFilterUpgradeStep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Rachael Koestartyo
 */
public class SegmentMembershipFilterUpgradeStepTest
	implements OSBAsahTestExecutionListenersContext,
			   OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_createIndexTemplate("groups", WeDeployDataService.OSB_ASAH_DXP_RAW);
		_createIndexTemplate(
			"organizations", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_createIndexTemplate("roles", WeDeployDataService.OSB_ASAH_DXP_RAW);
		_createIndexTemplate("teams", WeDeployDataService.OSB_ASAH_DXP_RAW);
		_createIndexTemplate(
			"user-groups", WeDeployDataService.OSB_ASAH_DXP_RAW);

		List<Segment> segments = new ArrayList<>();

		Segment segment = new Segment();

		segment.setFilter(
			"(groupIds eq '405057435148143169') and (groupIds ne " +
				"'405057436480425079')");

		segment.setId(2L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 2");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		segment.setFilter(
			"organizations.filter(filter='id eq ''402139267512234420''')");

		segment.setId(3L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 3");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		segment.setFilter(
			"(roleIds eq '405057439101255435') and (roleIds ne " +
				"'405057439859160765')");

		segment.setId(4L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 4");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		segment.setFilter(
			"(teamIds eq '405057437515771690') and (teamIds ne " +
				"'405057437515771691')");

		segment.setId(5L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 5");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		segment = new Segment();

		segment.setFilter(
			"(userGroupIds eq '405057436532876741') and (userGroupIds ne " +
				"'405057436532876741')");

		segment.setId(6L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 6");
		segment.setType(Segment.Type.DYNAMIC);

		segments.add(segment);

		_segmentRepository.saveAll(segments);
	}

	@AfterEach
	public void tearDown() {
		_dxpEntityRepository.deleteAll();

		_segmentRepository.deleteAll();
	}

	@SQLResource(resourcePath = "test_segment_filter_with_dxp_entities.sql")
	@Test
	public void testUpgrade() throws Exception {
		_segmentMembershipFilterUpgradeStep.upgrade("");

		Optional<Segment> segmentOptional = _segmentRepository.findById(2L);

		Segment segment = segmentOptional.get();

		String id1 = DigestUtils.sha256Hex("test#405057430327289648#20121");
		String id2 = DigestUtils.sha256Hex("test#405057430327289648#20125");

		Assertions.assertEquals(
			"(groupIds eq '" + id1 + "') and (groupIds ne '" + id2 + "')",
			segment.getFilter());

		segmentOptional = _segmentRepository.findById(3L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#33120");

		Assertions.assertEquals(
			"organizations.filter(filter='id eq ''" + id1 + "''')",
			segment.getFilter());

		segmentOptional = _segmentRepository.findById(4L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#20104");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#20108");

		Assertions.assertEquals(
			"(roleIds eq '" + id1 + "') and (roleIds ne '" + id2 + "')",
			segment.getFilter());

		segmentOptional = _segmentRepository.findById(5L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35472");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#35473");

		Assertions.assertEquals(
			"(teamIds eq '" + id1 + "') and (teamIds ne '" + id2 + "')",
			segment.getFilter());

		segmentOptional = _segmentRepository.findById(6L);

		segment = segmentOptional.get();

		id1 = DigestUtils.sha256Hex("test#405057430327289648#35452");
		id2 = DigestUtils.sha256Hex("test#405057430327289648#35452");

		Assertions.assertEquals(
			"(userGroupIds eq '" + id1 + "') and (userGroupIds ne '" + id2 +
				"')",
			segment.getFilter());
	}

	private void _createIndexTemplate(
			String collection, WeDeployDataService weDeployDataService)
		throws Exception {

		_elasticsearchIndexManager.delete(
			String.format("test_%s_%s", weDeployDataService, collection));

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				String.format(
					"dependencies/%s_index_configuration.json",
					StringUtils.replace(collection, "-", "_")),
				this),
			String.format("test_%s_%s", weDeployDataService, collection));

		_elasticsearchIndexManager.addAlias(
			String.format("test_%s_%s_alias", weDeployDataService, collection),
			String.format("test_%s_%s", weDeployDataService, collection));
	}

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SegmentMembershipFilterUpgradeStep
		_segmentMembershipFilterUpgradeStep;

	@Autowired
	private SegmentRepository _segmentRepository;

}