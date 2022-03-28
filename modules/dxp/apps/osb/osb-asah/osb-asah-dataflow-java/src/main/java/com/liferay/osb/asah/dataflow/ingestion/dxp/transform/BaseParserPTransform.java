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

import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.BaseDXPEntity;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.PubsubMessageAttributes;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionTuple;
import org.apache.beam.sdk.values.TupleTag;
import org.apache.beam.sdk.values.TupleTagList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Riccardo Ferrari
 */
public abstract class BaseParserPTransform<T extends BaseDXPEntity>
	extends PTransform
		<PCollection<KV<PubsubMessageAttributes, String>>, PCollectionTuple> {

	@Override
	public PCollectionTuple expand(
		PCollection<KV<PubsubMessageAttributes, String>> input) {

		return input.apply(
			"Parse DXP Entity",
			ParDo.of(
				new DoFn
					<KV<PubsubMessageAttributes, String>,
					 KV<PubsubMessageAttributes, T>>() {

					@ProcessElement
					public void processElement(ProcessContext processContext) {
						KV<PubsubMessageAttributes, String> element =
							processContext.element();

						try {
							PubsubMessageAttributes pubsubMessageAttributes =
								element.getKey();

							T product = doParse(element);

							processContext.output(
								_successTag,
								KV.of(pubsubMessageAttributes, product));
						}
						catch (Exception exception) {
							_logger.error(
								"Unable to parse {}: {}", element,
								exception.getMessage());

							processContext.output(
								_failTag,
								KV.of(exception.getMessage(), element));
						}
					}

				}
			).withOutputTags(
				_successTag, TupleTagList.of(_failTag)
			));
	}

	public abstract Class<T> getEntity();

	public TupleTag<KV<String, KV<PubsubMessageAttributes, String>>>
		getFailTag() {

		return _failTag;
	}

	public TupleTag<KV<PubsubMessageAttributes, T>> getSuccessTag() {
		return _successTag;
	}

	protected abstract T doParse(KV<PubsubMessageAttributes, String> element)
		throws Exception;

	private static final Logger _logger = LoggerFactory.getLogger(
		BaseParserPTransform.class);

	private final TupleTag<KV<String, KV<PubsubMessageAttributes, String>>>
		_failTag =
			new TupleTag<KV<String, KV<PubsubMessageAttributes, String>>>() {
			};
	private final TupleTag<KV<PubsubMessageAttributes, T>> _successTag =
		new TupleTag<KV<PubsubMessageAttributes, T>>() {
		};

}