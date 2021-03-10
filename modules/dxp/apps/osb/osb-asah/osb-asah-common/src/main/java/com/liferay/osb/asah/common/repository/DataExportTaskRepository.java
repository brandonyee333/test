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

import static com.liferay.osb.asah.common.model.DataExportTask.Status;
import static com.liferay.osb.asah.common.model.DataExportTask.Type;

import com.liferay.osb.asah.common.model.DataExportTask;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Inácio Nery
 */
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
@Repository
public interface DataExportTaskRepository
	extends CrudRepository<DataExportTask, Long> {

	public List<DataExportTask> findByStatus(Status status);

	public DataExportTask findFirstByTypeOrderByIdDesc(Type type);

}