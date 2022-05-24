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

package com.liferay.osb.asah.dataflow.ingestion.dxp.transform;

import com.google.api.services.bigquery.model.ErrorProto;
import com.google.api.services.bigquery.model.TableDataInsertAllResponse;
import com.google.api.services.bigquery.model.TableReference;
import com.google.api.services.bigquery.model.TableRow;

import com.liferay.osb.asah.dataflow.common.ObjectMapperUtil;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntityPubsubMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryInsertError;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.PCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Riccardo Ferrari
 */
public class BigQueryInsertErrorWriterPTransform
	extends PTransform
		<PCollection<BigQueryInsertError>,
		 PCollection<DXPEntityPubsubMessage>> {

	@Override
	public PCollection<DXPEntityPubsubMessage> expand(
		PCollection<BigQueryInsertError> input) {

		return input.apply(
			MapElements.via(
				new SimpleFunction
					<BigQueryInsertError, DXPEntityPubsubMessage>() {

					@Override
					public DXPEntityPubsubMessage apply(
						BigQueryInsertError input) {

						TableRow tableRow = input.getRow();

						TableDataInsertAllResponse.InsertErrors insertErrors =
							input.getError();

						List<ErrorProto> errorProtos = insertErrors.getErrors();

						Stream<ErrorProto> errorProtosStream =
							errorProtos.stream();

						List<Map<String, String>> errors =
							errorProtosStream.map(
								errorProto -> new HashMap<String, String>() {
									{
										put(
											"id",
											String.valueOf(
												tableRow.getOrDefault(
													"id", "-1")));
										put(
											"location",
											errorProto.getLocation());
										put("message", errorProto.getMessage());
										put("reason", errorProto.getReason());
									}
								}
							).collect(
								Collectors.toList()
							);

						if (_logger.isErrorEnabled()) {
							_logger.error(
								ObjectMapperUtil.writeValueAsString(errors));
						}

						TableReference tableReference = input.getTable();

						Map<String, String> attributes =
							new HashMap<String, String>() {
								{
									put(
										"dataSourceId",
										(String)tableRow.getOrDefault(
											"dataSourceId", "dataSourceId"));
									put(
										"projectId",
										(String)tableRow.getOrDefault(
											"projectId", "projectId"));
									put(
										"resourceName",
										tableReference.getTableId());
									put(
										"uploadTime",
										(String)tableRow.getOrDefault(
											"uploadDate", "uploadDate"));
									put(
										"uploadType",
										(String)tableRow.getOrDefault(
											"uploadType", "uploadType"));
								}
							};

						return new DXPEntityPubsubMessage(
							attributes,
							ObjectMapperUtil.writeValueAsString(errors));
					}

				}));
	}

	private static final Logger _logger = LoggerFactory.getLogger(
		BigQueryInsertErrorWriterPTransform.class);

}