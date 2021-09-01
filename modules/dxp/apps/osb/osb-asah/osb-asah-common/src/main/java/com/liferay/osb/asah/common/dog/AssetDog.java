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
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.collections4.IterableUtils;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author Marcellus Tavares
 */
@Component
public class AssetDog {

	public Asset addAsset(
		Set<AssetKeyword> assetKeywords, String assetType, String canonicalURL,
		Set<Long> channelIds, String dataSourceAssetPK, Long dataSourceId,
		String description, String title, String url) {

		Asset asset = new Asset();

		asset.setAssetKeywords(assetKeywords);
		asset.setAssetType(assetType);
		asset.setCanonicalURL(canonicalURL);
		asset.setChannelIds(channelIds);
		asset.setDataSourceAssetPK(dataSourceAssetPK);
		asset.setDataSourceId(dataSourceId);
		asset.setDescription(description);
		asset.setTitle(title);
		asset.setURL(url);

		return _assetRepository.save(asset);
	}

	public void deleteAsset(Asset asset, String deletionDayDateString) {
		Long assetId = asset.getId();

		if (assetId != null) {
			_assetRepository.deleteById(assetId);
		}

		Set<AssetKeyword> assetKeywords = asset.getAssetKeywords();

		if (assetKeywords != null) {
			for (AssetKeyword assetKeyword : assetKeywords) {
				_elasticsearchInvoker.delete(
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
							"name", assetKeyword.getKeyword())
					));

				_elasticsearchInvoker.delete(
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
							"interestName", assetKeyword.getKeyword())
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
		@Nullable String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		return PageableExecutionUtils.getPage(
			_assetRepository.findByFilterString(filterString, pageRequest),
			pageRequest,
			() -> _assetRepository.countByFilterString(filterString));
	}

	public Page<Asset> getAssetPage(
		String assetType, @Nullable String filterString,
		@Nullable String keyword, int page, int size, Sort sort) {

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_assetRepository.findByAssetTypeAndFilterStringAndKeyword(
				assetType, filterString, keyword, pageRequest),
			pageRequest,
			() -> _assetRepository.countByAssetTypeAndFilterStringAndKeyword(
				assetType, filterString, keyword));
	}

	public List<Asset> getAssets(Set<Long> assetIds) {
		if (CollectionUtils.isEmpty(assetIds)) {
			return Collections.emptyList();
		}

		return IterableUtils.toList(_assetRepository.findAllById(assetIds));
	}

	public List<Asset> getAssets(
		String assetType, int page, int size, Sort sort) {

		return _assetRepository.findByAssetTypeAndKeywordNotNull(
			assetType, PageRequest.of(page, size, sort));
	}

	public List<Asset> getAssets(String assetType, String keyword) {
		return _assetRepository.findByAssetTypeAndFilterStringAndKeyword(
			"Page", null, keyword, null);
	}

	public long getAssetsCount(String assetType) {
		return _assetRepository.countByAssetTypeAndKeywordNotNull(assetType);
	}

	public List<String> getKeywords(String assetType) {
		Set<String> keywords = new TreeSet<>();

		int size = 50;

		long total = _assetRepository.countByAssetType(assetType);

		int pages = (int)total / 50;

		for (int page = 0; page <= pages; page++) {
			List<Asset> assets = _assetRepository.findByAssetType(
				assetType, PageRequest.of(page, size));

			for (Asset asset : assets) {
				Set<AssetKeyword> assetKeywords = asset.getAssetKeywords();

				assetKeywords.forEach(
					assetKeyword -> keywords.add(assetKeyword.getKeyword()));
			}
		}

		return new ArrayList<>(keywords);
	}

	public Page<Transformation> getTransformationsPage(
		String apply, @Nullable String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_assetRepository.getAssetTransformations(
				apply, filterString, pageRequest);

		return PageableExecutionUtils.getPage(
			transformations, pageRequest, transformations::size);
	}

	public Asset updateAsset(Asset asset) {
		if (asset.getId() == null) {
			throw new IllegalArgumentException(
				"Unable to update asset with ID null");
		}

		return _assetRepository.save(asset);
	}

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AssetRepository _assetRepository;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private TimeZoneDog _timeZoneDog;

}