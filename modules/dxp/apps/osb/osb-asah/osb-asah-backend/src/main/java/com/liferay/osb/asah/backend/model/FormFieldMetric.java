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

package com.liferay.osb.asah.backend.model;

import java.util.Objects;

/**
 * @author Marcellus Tavares
 */
public class FormFieldMetric {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FormFieldMetric)) {
			return false;
		}

		FormFieldMetric formFieldMetric = (FormFieldMetric)obj;

		if (Objects.equals(
				_fieldAbandonmentsMetric,
				formFieldMetric._fieldAbandonmentsMetric) &&
			Objects.equals(
				_fieldInteractionDurationMetric,
				formFieldMetric._fieldInteractionDurationMetric) &&
			Objects.equals(
				_fieldInteractionsMetric,
				formFieldMetric._fieldInteractionsMetric) &&
			Objects.equals(_fieldName, formFieldMetric._fieldName) &&
			Objects.equals(
				_fieldRefilledMetric, formFieldMetric._fieldRefilledMetric)) {

			return true;
		}

		return false;
	}

	public Metric getFieldAbandonmentsMetric() {
		return _fieldAbandonmentsMetric;
	}

	public Metric getFieldInteractionDurationMetric() {
		return _fieldInteractionDurationMetric;
	}

	public Metric getFieldInteractionsMetric() {
		return _fieldInteractionsMetric;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public Metric getFieldRefilledMetric() {
		return _fieldRefilledMetric;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_fieldAbandonmentsMetric, _fieldInteractionDurationMetric,
			_fieldInteractionsMetric, _fieldName, _fieldRefilledMetric);
	}

	public void setFieldAbandonmentsMetric(Metric fieldAbandonmentsMetric) {
		_fieldAbandonmentsMetric = fieldAbandonmentsMetric;
	}

	public void setFieldInteractionDurationMetric(
		Metric fieldInteractionDurationMetric) {

		_fieldInteractionDurationMetric = fieldInteractionDurationMetric;
	}

	public void setFieldInteractionsMetric(Metric fieldInteractionsMetric) {
		_fieldInteractionsMetric = fieldInteractionsMetric;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public void setFieldRefilledMetric(Metric fieldRefilledMetric) {
		_fieldRefilledMetric = fieldRefilledMetric;
	}

	private Metric _fieldAbandonmentsMetric;
	private Metric _fieldInteractionDurationMetric;
	private Metric _fieldInteractionsMetric;
	private String _fieldName;
	private Metric _fieldRefilledMetric;

}