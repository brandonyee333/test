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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Asset;
import com.liferay.osb.asah.common.model.PropertyFilter;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class AssetDog {

	public Asset addAsset(Asset asset) {
		if (!asset.isNew()) {
			throw new IllegalArgumentException("Unable to add asset not new");
		}

		return _assetRepository.save(asset);
	}

	public void deleteAsset(
		JSONObject assetJSONObject, String deletionDayDateString) {

		String assetId = assetJSONObject.getString("id");

		elasticsearchInvoker.delete("assets", assetId);

		JSONArray keywordsJSONArray = assetJSONObject.optJSONArray("keywords");

		if (keywordsJSONArray != null) {
			for (int i = 0; i < keywordsJSONArray.length(); i++) {
				JSONObject jsonObject = keywordsJSONArray.getJSONObject(i);

				elasticsearchInvoker.delete(
					"interests",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.rangeQuery(
							"dateRecorded"
						).gte(
							deletionDayDateString
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					).filter(
						QueryBuilders.termQuery(
							"name", jsonObject.getString("keyword"))
					));

				elasticsearchInvoker.delete(
					"visited-pages",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.rangeQuery(
							"day"
						).gte(
							deletionDayDateString
						).timeZone(
							_timeZoneDog.getTimeZoneId()
						)
					).filter(
						QueryBuilders.termQuery(
							"interestName", jsonObject.getString("keyword"))
					));
			}
		}

		_asahTaskDog.scheduleAsahTask(
			"IndividualInterestScoresNanite",
			JSONUtil.put("reprocessDay", deletionDayDateString));
	}

	public Asset fetchAsset(String dataSourceAssetPK, Long dataSourceId) {
		Optional<Asset> assetOptional =
			_assetRepository.findByDataSourceAssetPKAndDataSourceId(
				dataSourceAssetPK, dataSourceId);

		return assetOptional.orElse(null);
	}

	public Asset getAsset(Long assetId) {
		Optional<Asset> assetOptional = _assetRepository.findById(assetId);

		return assetOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no asset with ID " + assetId));
	}

	public Page<Asset> getAssetPage(
		String assetType, String keyword, List<PropertyFilter> propertyFilters,
		int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_assetRepository.searchAssets(
				assetType, keyword, propertyFilters, pageRequest),
			pageRequest,
			() -> _assetRepository.countAssets(
				assetType, keyword, propertyFilters));
	}

	public List<String> getKeywords() {
		Set<String> keywords = new TreeSet<>();

		JSONArray assetsJSONArray = elasticsearchInvoker.get(
			"assets",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("assetType", "Page")));

		for (int i = 0; i < assetsJSONArray.length(); i++) {
			JSONObject assetJSONObject = assetsJSONArray.getJSONObject(i);

			JSONArray keywordsJSONArray = assetJSONObject.getJSONArray(
				"keywords");

			for (int j = 0; j < keywordsJSONArray.length(); j++) {
				JSONObject keywordJSONObject = keywordsJSONArray.getJSONObject(
					j);

				keywords.add(keywordJSONObject.getString("keyword"));
			}
		}

		return new ArrayList<>(keywords);
	}

	public Page<Asset> searchAssetsPage(
		String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_assetRepository.searchAssets(filterString, pageRequest),
			pageRequest, () -> _assetRepository.countAssets(filterString));
	}

	public Asset updateAsset(Asset asset) {
		if (asset.getId() == null) {
			throw new IllegalArgumentException(
				"Unable to update asset with ID null");
		}

		return _assetRepository.save(asset);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	protected ElasticsearchInvoker elasticsearchInvoker;

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetRepository _assetRepository;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}