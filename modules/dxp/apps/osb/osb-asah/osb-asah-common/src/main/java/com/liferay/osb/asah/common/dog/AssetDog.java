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

import com.liferay.osb.asah.common.converter.helper.DefaultFilterStringConverterHelper;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.date.dog.TimeZoneDog;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.elasticsearch.converter.helper.faro.info.FaroInfoAssetFilterStringConverterHelper;
import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.entity.AssetKeyword;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.AssetRepository;
import com.liferay.osb.asah.common.repository.BQIndividualInterestScoreRepository;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
				_bqIndividualInterestScoreRepository.
					deleteByKeywordAndRecordedDateGreaterThanEqual(
						assetKeyword.getKeyword(),
						DateUtil.toUTCDate(deletionDayDateString));
			}
		}
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

	public Map<String, Set<String>> getAssetIdsGroupedByKeywords(
		String assetType, Long channelId, Long dataSourceId) {

		return _assetRepository.getByAssetTypeAndChannelIdAndDatasourceId(
			assetType, channelId, dataSourceId);
	}

	public Page<Asset> getAssetPage(
		@Nullable String filterString, int page, int size, String[] sorts) {

		PageRequest pageRequest = PageRequest.of(
			page, size, SortUtil.getSort(sorts));

		FilterHelper filterHelper = new FilterHelper(
			_faroInfoAssetFilterStringConverterHelper, filterString,
			_defaultFilterStringConverterHelper);

		return PageableExecutionUtils.getPage(
			_assetRepository.findByFilterString(filterHelper, pageRequest),
			pageRequest,
			() -> _assetRepository.countByFilterString(filterHelper));
	}

	public Page<Asset> getAssetPage(
		String assetType, @Nullable String filterString,
		@Nullable String keyword, int page, int size, Sort sort) {

		FilterHelper filterHelper = new FilterHelper(
			_faroInfoAssetFilterStringConverterHelper, filterString,
			_defaultFilterStringConverterHelper);

		PageRequest pageRequest = PageRequest.of(page, size, sort);

		return PageableExecutionUtils.getPage(
			_assetRepository.findByAssetTypeAndFilterStringAndKeywords(
				assetType, filterHelper, keyword, pageRequest),
			pageRequest,
			() -> _assetRepository.countByAssetTypeAndFilterStringAndKeywords(
				assetType, filterHelper, keyword));
	}

	public List<Asset> getAssets(List<Long> channelIds, int page, int size) {
		return _assetRepository.findByChannelIds(
			channelIds, PageRequest.of(page, size));
	}

	public List<Asset> getAssets(Long assetId, String assetType, int size) {
		return _assetRepository.findByAssetTypeAndAssetKeywordNotNull(
			assetId, assetType, size);
	}

	public List<Asset> getAssets(Set<Long> assetIds) {
		if (CollectionUtils.isEmpty(assetIds)) {
			return Collections.emptyList();
		}

		return IterableUtils.toList(_assetRepository.findAllById(assetIds));
	}

	public Map<String, Asset> getAssets(
		String assetType, Collection<String> canonicalUrls) {

		List<Asset> assets = new ArrayList<>();

		int page = 0;

		while (true) {
			List<Asset> currentAssets =
				_assetRepository.findByAssetTypeAndCanonicalURLIn(
					assetType, canonicalUrls,
					PageRequest.of(
						page++, 10000, Sort.by(Sort.Direction.DESC, "id")));

			if (currentAssets.isEmpty()) {
				break;
			}

			assets.addAll(currentAssets);

			if (currentAssets.size() < 10000) {
				break;
			}
		}

		Stream<Asset> stream = assets.stream();

		return stream.collect(
			Collectors.toMap(
				Asset::getCanonicalURL, Function.identity(),
				(existing, replacement) -> replacement));
	}

	public List<Asset> getAssets(
		String assetType, Collection<String> canonicalUrls, int page, int size,
		Sort sort) {

		return _assetRepository.findByAssetTypeAndCanonicalURLIn(
			assetType, canonicalUrls, PageRequest.of(page, size, sort));
	}

	public List<Asset> getAssets(String assetType, String assetKeyword) {
		return _assetRepository.findByAssetTypeAndFilterString(
			assetType,
			new FilterHelper(
				_faroInfoAssetFilterStringConverterHelper,
				String.format("keywords/keyword eq '%s'", assetKeyword),
				_defaultFilterStringConverterHelper));
	}

	public long getAssetsCount(
		String assetType, Collection<String> canonicalUrls) {

		return _assetRepository.countByAssetTypeAndCanonicalURLIn(
			assetType, canonicalUrls);
	}

	public List<String> getDataSourceAssetPKs(String keyword) {
		return _assetRepository.findDataSourceAssetPKByKeyword(keyword);
	}

	public List<String> getKeywords(String assetType) {
		return _assetRepository.findKeywordByAssetType(assetType);
	}

	public List<String> getKeywords(
		String keywords, int page, int size, Sort sort) {

		return _assetRepository.getAssetKeywords(
			keywords, PageRequest.of(page, size, sort));
	}

	public long getKeywordsCount(String keywords) {
		return _assetRepository.countAssetKeywords(keywords);
	}

	public Page<Transformation> getTransformationPage(
		String apply, @Nullable String filterString, int page, int size) {

		PageRequest pageRequest = PageRequest.of(
			page, size,
			SortUtil.getSort(
				Sort.by(Sort.Order.desc("totalElements")),
				new String[] {"totalElements", "desc", "terms", "asc"}));

		List<Transformation> transformations =
			_assetRepository.getAssetTransformations(
				apply,
				new FilterHelper(
					_faroInfoAssetFilterStringConverterHelper, filterString,
					_defaultFilterStringConverterHelper),
				pageRequest);

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

	@Autowired
	private BQIndividualInterestScoreRepository
		_bqIndividualInterestScoreRepository;

	private final DefaultFilterStringConverterHelper
		_defaultFilterStringConverterHelper =
			new DefaultFilterStringConverterHelper();
	private final FaroInfoAssetFilterStringConverterHelper
		_faroInfoAssetFilterStringConverterHelper =
			new FaroInfoAssetFilterStringConverterHelper();

	@Autowired
	private TimeZoneDog _timeZoneDog;

}