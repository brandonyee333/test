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

package com.liferay.osb.asah.common.repository.impl;

import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.repository.ProjectRepository;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.DeleteUsingStep;
import org.jooq.Field;
import org.jooq.InsertValuesStep1;
import org.jooq.Record;
import org.jooq.SelectSelectStep;
import org.jooq.Table;
import org.jooq.impl.DSL;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
@ConditionalOnProperty(
	havingValue = "true", value = "osb.asah.postgresql.enabled"
)
public class ProjectRepositoryImpl implements ProjectRepository {

	public ProjectRepositoryImpl(DSLContext dslContext) {
		_dslContext = dslContext;
	}

	@Override
	public boolean deleteById(String projectId) {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			DeleteUsingStep<Record> deleteUsingStep = _dslContext.delete(
				DSL.table("Project"));

			Field<String> field = DSL.field("id", String.class);

			int result = deleteUsingStep.where(
				field.eq(projectId)
			).execute();

			if (result > 0) {
				return true;
			}

			return false;
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	@Override
	public List<Project> findAll() {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			SelectSelectStep<Record> selectSelectStep = _dslContext.select();

			return selectSelectStep.from(
				"Project"
			).fetch(
			).map(
				record -> new Project(record.get("id", String.class))
			);
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	@Override
	public Project save(Project project) {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			Table<Record> table = DSL.table("Project");

			InsertValuesStep1<Record, String> insertSetStep =
				_dslContext.insertInto(table, table.field("id", String.class));

			insertSetStep.values(project.getId());

			insertSetStep.execute();

			return new Project(project.getId());
		}
		finally {
			ProjectIdThreadLocal.setGlobalContext(false);
		}
	}

	private final DSLContext _dslContext;

}