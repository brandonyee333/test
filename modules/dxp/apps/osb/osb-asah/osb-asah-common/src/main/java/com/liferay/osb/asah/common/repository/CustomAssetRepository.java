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

package com.liferay.osb.asah.common.repository;

import com.liferay.osb.asah.common.entity.Asset;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.helper.FilterHelper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Ivica Cardic
 */
public interface CustomAssetRepository {

	@Cacheable
	public long countAssetKeywords(String keyword);

	@Cacheable
	public long countByAssetTypeAndCanonicalURLIn(
		String assetType, Collection<String> canonicalUrls);

	@Cacheable
	public long countByAssetTypeAndFilterStringAndKeywords(
		String assetType, FilterHelper filterHelper, @Nullable String keywords);

	@Cacheable
	public long countByFilterString(FilterHelper filterHelper);

	@Cacheable
	public List<Asset> findByAssetTypeAndAssetKeywordNotNull(
		Long assetId, String assetType, int size);

	@Cacheable
	public List<Asset> findByAssetTypeAndCanonicalURLIn(
		String assetType, Collection<String> canonicalUrls, Pageable pageable);

	@Cacheable
	public List<Asset> findByAssetTypeAndFilterString(
		String assetType, FilterHelper filterHelper);

	@Cacheable
	public List<Asset> findByAssetTypeAndFilterStringAndKeywords(
		String assetType, FilterHelper filterHelper, String keywords,
		Pageable pageable);

	@Cacheable
	public List<Asset> findByChannelIds(
		List<Long> channelIds, Pageable pageable);

	@Cacheable
	public List<Asset> findByFilterString(
		FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public List<String> findDataSourceAssetPKByKeyword(String keyword);

	@Cacheable
	public List<String> findKeywordByAssetType(String assetType);

	@Cacheable
	public List<String> getAssetKeywords(String keyword, Pageable pageable);

	@Cacheable
	public List<Transformation> getAssetTransformations(
		String apply, FilterHelper filterHelper, Pageable pageable);

	@Cacheable
	public Map<String, Set<String>> getByAssetTypeAndChannelIdAndDatasourceId(
		String assetType, @Nullable Long channelId,
		@Nullable Long dataSourceId);

}