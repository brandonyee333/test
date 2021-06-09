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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.MembershipChange;
import com.liferay.osb.asah.common.repository.MembershipChangeRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Collections;
import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;

import org.json.JSONObject;

import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@Repository
public class ElasticsearchMembershipChangeRepositoryImpl
	extends BaseElasticsearchRepository<MembershipChange, Long>
	implements MembershipChangeRepository {

	@Override
	public void deleteByIndividualSegmentId(Long individualSegmentId) {
		_faroInfoElasticsearchInvoker.delete(
			"membership-changes",
			QueryBuilders.termQuery(
				"individualSegmentId", String.valueOf(individualSegmentId)));
	}

	@Override
	public Optional<MembershipChange> findByIndividualId(Long individualId) {
		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			getCollectionName(),
			QueryBuilders.termQuery(
				"individualId", String.valueOf(individualId)));

		return Optional.ofNullable(
			jsonObject
		).map(
			this::toEntity
		);
	}

	@Override
	public void updateIndividualDeletedByIndividualId(
		Boolean individualDeleted, Long individualId) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("individualId", individualId), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualDeleted = params.individualDeleted",
				Collections.singletonMap(
					"individualDeleted", individualDeleted)),
			getCollectionName());
	}

	@Override
	public void updateIndividualNameByIndividualId(
		Long individualId, String individualName) {

		_faroInfoElasticsearchInvoker.updateByQueryWithRetry(
			QueryBuilders.termQuery("individualId", individualId), true,
			new Script(
				Script.DEFAULT_SCRIPT_TYPE, Script.DEFAULT_SCRIPT_LANG,
				"ctx._source.individualName = params.individualName",
				Collections.singletonMap("individualName", individualName)),
			getCollectionName());
	}

	@Override
	protected String getCollectionName() {
		return "membership-changes";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _faroInfoElasticsearchInvoker;
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}