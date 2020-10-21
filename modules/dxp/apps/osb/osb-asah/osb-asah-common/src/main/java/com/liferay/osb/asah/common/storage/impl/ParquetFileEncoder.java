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

package com.liferay.osb.asah.common.storage.impl;

import java.io.IOException;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 */
public class ParquetFileEncoder implements FileEncoder {

	public ParquetFileEncoder(String pathString, Schema schema) {
		_pathString = pathString;
		_schema = schema;
	}

	@Override
	public void close() throws IOException {
		_parquetWriter.close();
	}

	@Override
	public void encode(String data) throws IOException {
		_parquetWriter.write(
			_jsonAvroTransformer.transform(new JSONObject(data), _schema));
	}

	@Override
	public long getDataSize() {
		return _parquetWriter.getDataSize();
	}

	@Override
	public void open() throws IOException {
		AvroParquetWriter.Builder<GenericRecord> builder =
			AvroParquetWriter.builder(new Path(_pathString));

		builder.withCompressionCodec(CompressionCodecName.SNAPPY);
		builder.withSchema(_schema);
		builder.withWriteMode(ParquetFileWriter.Mode.OVERWRITE);

		_parquetWriter = builder.build();
	}

	private JSONAvroTransformer _jsonAvroTransformer =
		new JSONAvroTransformer();
	private ParquetWriter<GenericRecord> _parquetWriter;
	private final String _pathString;
	private final Schema _schema;

}