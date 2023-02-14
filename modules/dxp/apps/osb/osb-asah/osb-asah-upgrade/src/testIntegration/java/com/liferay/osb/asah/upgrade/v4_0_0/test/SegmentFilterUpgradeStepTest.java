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

import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.repository.SegmentRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.SegmentFilterUpgradeStep;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;

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

		_createIndexTemplate("assets", WeDeployDataService.OSB_ASAH_FARO_INFO);

		Segment segment = new Segment();

		segment.setFilter(
			"(activities.filterByCount(filter='(activityKey eq " +
				"''WebContent#webContentViewed#562090627547503004'' and day " +
					"gt ''last24Hours'')', operator='ge', value=1)) and " +
						"(activities.filterByCount(filter='(activityKey eq " +
							"''Page#pageViewed#562088009867349328'' and day " +
								"gt ''last24Hours'')', operator='ge', value=" +
									"1))");
		segment.setId(1L);
		segment.setIncludeAnonymousUsers(Boolean.FALSE);
		segment.setIsNew(true);
		segment.setModifiedDate(new Date());
		segment.setName("Segment 1");
		segment.setType(Segment.Type.DYNAMIC);

		_segmentRepository.save(segment);
	}

	@AfterEach
	public void tearDown() {
		_faroInfoElasticsearchInvoker.delete(
			"assets", QueryBuilders.matchAllQuery());

		_segmentRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		_faroInfoElasticsearchInvoker.add(
			"assets",
			_getJSONArray(
				"assets", WeDeployDataService.OSB_ASAH_FARO_INFO.toString()));

		_segmentFilterUpgradeStep.upgrade("");

		Optional<Segment> segmentOptional = _segmentRepository.findById(1L);

		Segment segment = segmentOptional.get();

		Assertions.assertEquals(
			"(activities.filterByCount(filter='(applicationId eq " +
				"''WebContent'' and eventId eq ''webContentViewed'' and " +
					"assetId eq ''4bbc36e2819ac0d4b638e4eda149d2ff26e1cc9cfa" +
						"6f4f0e85bcad1ade10542c'' and day gt ''last24Hours''" +
							")', operator='ge', value=1)) and (activities." +
								"filterByCount(filter='(applicationId eq " +
									"''Page'' and eventId eq ''pageViewed'' " +
										"and assetId eq ''5c5db277986449c8e0a" +
											"80f8172a6b4cf7aecd8dd94374a5cdb2" +
												"47d0d70ca155e'' and day gt " +
													"''last24Hours'')', " +
														"operator='ge', value" +
															"=1))",
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

	private JSONArray _getJSONArray(
			String collectionName, String weDeployDataServiceName)
		throws Exception {

		return new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					String.format(
						"dependencies/%s/%s.json", weDeployDataServiceName,
						collectionName),
					this)));
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SegmentFilterUpgradeStep _segmentFilterUpgradeStep;

	@Autowired
	private SegmentRepository _segmentRepository;

}