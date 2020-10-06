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

package com.liferay.osb.asah.backend.dog;

import com.liferay.osb.asah.backend.model.ItemRecommendation;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.SearchHits;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class RecommendationDog {

	public boolean deleteItemRecommendationsByJobId(String jobId) {
		BulkByScrollResponse bulkByScrollResponse =
			_faroInfoElasticsearchInvoker.deleteByQuery(
				QueryBuilders.termQuery("jobId", jobId), true,
				"recommended-items");

		List<BulkItemResponse.Failure> bulkFailures =
			bulkByScrollResponse.getBulkFailures();

		return bulkFailures.isEmpty();
	}

	public ItemRecommendation getItemRecommendation(String id) {
		return DogUtil.convert(
			_faroInfoElasticsearchInvoker.get("recommended-items", id),
			ItemRecommendation.class);
	}

	public ResultBag<ItemRecommendation> getItemRecommendationResultBag(
		String jobId, int size, Sort sort, int start) {

		SearchHits searchHits = _dataDog.querySearchHits(
			"recommended-items", _faroInfoElasticsearchInvoker,
			DogUtil.buildSearchSourceBuilder(
				SortBuilderUtil.fieldSort(sort),
				QueryBuilders.termQuery("jobId", jobId), size, start));

		return DogUtil.createResultBag(ItemRecommendation.class, searchHits);
	}

	@Autowired
	private DataDog _dataDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}