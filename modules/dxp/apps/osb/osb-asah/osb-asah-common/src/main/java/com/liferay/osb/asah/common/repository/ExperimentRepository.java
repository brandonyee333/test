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

import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.model.ExperimentStatus;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;

/**
 * @author Marcos Martins
 */
public interface ExperimentRepository extends CrudRepository<Experiment, Long> {

	public List<Experiment> findByExperimentStatus(
		ExperimentStatus experimentStatus);

	public List<Experiment> searchExperimentsByChannelIdAndKeywords(
		Long channelId, @Nullable String keywords, Pageable pageable);

}