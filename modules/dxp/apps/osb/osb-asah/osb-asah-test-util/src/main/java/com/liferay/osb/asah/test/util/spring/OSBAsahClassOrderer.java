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

package com.liferay.osb.asah.test.util.spring;

import com.liferay.osb.asah.common.util.ArrayUtil;
import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.ClassDescriptor;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.ClassOrdererContext;

import org.springframework.context.annotation.Import;

/**
 * @author Inácio Nery
 */
public class OSBAsahClassOrderer implements ClassOrderer {

	@Override
	public void orderClasses(ClassOrdererContext classOrdererContext) {
		List<? extends ClassDescriptor> classDescriptors =
			classOrdererContext.getClassDescriptors();

		Comparator<ClassDescriptor> annotationComparator =
			Comparator.comparingInt(
				classDescriptor -> {
					Optional<SQLResource> sqlResourceOptional =
						classDescriptor.findAnnotation(SQLResource.class);

					if (sqlResourceOptional.isPresent() &&
						Objects.equals(
							sqlResourceOptional.map(
								SQLResource::dataSource
							).get(),
							"trinoDataSource")) {

						return 3;
					}

					Optional<Import> importOptional =
						classDescriptor.findAnnotation(Import.class);

					if (importOptional.isPresent() &&
						ArrayUtil.contains(
							importOptional.map(
								Import::value
							).get(),
							JDBCTestConfiguration.class)) {

						return 2;
					}

					return 1;
				});

		Comparator<ClassDescriptor> nameComparator = Comparator.comparing(
			classDescriptor -> {
				Class<?> testClass = classDescriptor.getTestClass();

				return testClass.getName();
			});

		Collections.sort(
			classDescriptors,
			annotationComparator.thenComparing(nameComparator));
	}

}