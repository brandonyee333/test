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

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface AssetRepository extends CrudRepository<Asset, Long> {

	public long countAssets(String filterString);

	public long countAssets(
		String assetType, String keyword, String filterString);

	public List<Asset> findByAssetType(String assetType);

	public Optional<Asset> findByDataSourceAssetPKAndDataSourceId(
		String dataSourceAssetPK, Long dataSourceId);

	public List<Asset> searchAssets(String filterString, Pageable pageable);

	public List<Asset> searchAssets(
		String assetType, String keyword, String filterString,
		Pageable pageable);

}
