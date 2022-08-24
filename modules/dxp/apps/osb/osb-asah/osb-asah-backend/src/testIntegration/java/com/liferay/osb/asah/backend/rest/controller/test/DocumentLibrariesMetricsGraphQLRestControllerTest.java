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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.repository.CrudBQDocumentLibraryRepository;

import org.junit.jupiter.api.Disabled;

/**
 * @author André Miranda
 */
@Disabled
@RepositoryResource(
	repositoryClass = CrudBQDocumentLibraryRepository.class,
	resourcePath = "osbasahcerebroinfo/document_library_info.json"
)
public class DocumentLibrariesMetricsGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "document_library_metrics_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "document_library_metrics_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "document_library_metrics_query.graphql";
	}

}