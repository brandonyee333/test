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

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

/**
 * @author Marcellus Tavares
 */
public interface AssetRepository extends CrudRepository<Asset, Long> {

	public long countByAssetType(String assetType);

	public long countByAssetTypeAndFilterStringAndKeyword(
		String assetType, @Nullable String filterString,
		@Nullable String keyword);

	public long countByFilterString(@Nullable String filterString);

	public List<Asset> findByAssetType(String assetType, Pageable pageable);

	public List<Asset> findByAssetTypeAndFilterStringAndKeyword(
		String assetType, @Nullable String filterString,
		@Nullable String keyword, Pageable pageable);

	public Optional<Asset> findByDataSourceAssetPKAndDataSourceId(
		String dataSourceAssetPK, Long dataSourceId);

	public List<Asset> findByFilterString(
		@Nullable String filterString, Pageable pageable);

	public List<Transformation> getAssetTransformations(
		String apply, @Nullable String filterString, Pageable pageable);

}