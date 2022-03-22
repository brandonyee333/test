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

import com.google.api.services.bigquery.model.TableRow;

import com.liferay.osb.asah.dataflow.ingestion.dxp.util.TableRowConverter;

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.InsertRetryPolicy;
import org.apache.beam.sdk.io.gcp.bigquery.WriteResult;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.PCollection;

/**
 * @author Riccardo Ferrari
 */
public class BigQueryWriterPTransform<T>
	extends PTransform<PCollection<T>, WriteResult> {

	public BigQueryWriterPTransform(String tableName) {
		_tableName = tableName;
	}

	@Override
	public WriteResult expand(PCollection<T> input) {
		return input.apply(
			MapElements.via(
				new SimpleFunction<T, TableRow>() {

					@Override
					public TableRow apply(T input) {
						return TableRowConverter.asRow(input);
					}

				})
		).apply(
			String.format("Write to BigQuery table: %s", _tableName),
			BigQueryIO.writeTableRows(
			).to(
				_tableName
			).withCreateDisposition(
				BigQueryIO.Write.CreateDisposition.CREATE_NEVER
			).withMethod(
				BigQueryIO.Write.Method.STREAMING_INSERTS
			).withWriteDisposition(
				BigQueryIO.Write.WriteDisposition.WRITE_APPEND
			).withoutValidation(
			).withExtendedErrorInfo(
			).withFailedInsertRetryPolicy(
				InsertRetryPolicy.retryTransientErrors()
			)
		);
	}

	private final String _tableName;

}