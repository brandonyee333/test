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

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
@Primary
public interface AssetRepository extends Repository<Asset, Long> {

	@Cacheable
	public long countByAssetTypeAndFilterStringAndKeywords(
		String assetType, @Nullable String filterString,
		@Nullable String keyword);

	@Cacheable
	public long countByFilterString(@Nullable String filterString);

	@Cacheable
	public List<Asset> findByAssetTypeAndAssetKeywordNotNull(
		Long assetId, String assetType, int size);

	@Cacheable
	public List<Asset> findByAssetTypeAndFilterStringAndKeywords(
		String assetType, @Nullable String filterString,
		@Nullable String keyword, @Nullable Pageable pageable);

	@Cacheable
	public Optional<Asset> findByDataSourceAssetPKAndDataSourceId(
		String dataSourceAssetPK, Long dataSourceId);

	@Cacheable
	public List<Asset> findByFilterString(
		@Nullable String filterString, Pageable pageable);

	@Cacheable
	public List<String> findDataSourceAssetPKByKeyword(String keyword);

	@Cacheable
	public List<String> findKeywordByAssetType(String assetType);

	@Cacheable
	public List<Transformation> getAssetTransformations(
		String apply, @Nullable String filterString, Pageable pageable);

}