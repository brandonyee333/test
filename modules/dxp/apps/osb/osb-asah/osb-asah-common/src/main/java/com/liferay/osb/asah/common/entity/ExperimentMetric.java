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

package com.liferay.osb.asah.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.time.LocalDateTime;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class ExperimentMetric implements Persistable<Long> {

	public ExperimentMetric() {
	}

	public ExperimentMetric(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public void addExperimentVariantMetric(
		ExperimentVariantMetric experimentVariantMetric) {

		_experimentVariantMetrics.add(experimentVariantMetric);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExperimentMetric)) {
			return false;
		}

		ExperimentMetric experimentMetric = (ExperimentMetric)obj;

		if (Objects.equals(_completion, experimentMetric._completion) &&
			Objects.equals(
				_confidenceLevel, experimentMetric._confidenceLevel) &&
			Objects.equals(_elapsedDays, experimentMetric._elapsedDays) &&
			Objects.equals(
				_estimatedDaysLeft, experimentMetric._estimatedDaysLeft) &&
			Objects.equals(
				_experimentVariantMetrics,
				experimentMetric._experimentVariantMetrics) &&
			Objects.equals(_id, experimentMetric._id) &&
			Objects.equals(_processedDate, experimentMetric._processedDate)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public double getCompletion() {
		return _completion;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public double getConfidenceLevel() {
		return _confidenceLevel;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public long getElapsedDays() {
		return _elapsedDays;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getEstimatedDaysLeft() {
		return _estimatedDaysLeft;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("variantMetrics")
	@MappedCollection(idColumn = "experimentmetricid")
	public Set<ExperimentVariantMetric> getExperimentVariantMetrics() {
		return _experimentVariantMetrics;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public LocalDateTime getProcessedDate() {
		return _processedDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_completion, _confidenceLevel, _elapsedDays, _estimatedDaysLeft,
			_experimentVariantMetrics, _id, _processedDate);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setCompletion(double completion) {
		_completion = completion;
	}

	public void setConfidenceLevel(double confidenceLevel) {
		_confidenceLevel = confidenceLevel;
	}

	public void setElapsedDays(long elapsedDays) {
		_elapsedDays = elapsedDays;
	}

	public void setEstimatedDaysLeft(Long estimatedDaysLeft) {
		_estimatedDaysLeft = estimatedDaysLeft;
	}

	@JsonDeserialize(as = LinkedHashSet.class)
	public void setExperimentVariantMetrics(
		Set<ExperimentVariantMetric> experimentVariantMetrics) {

		_experimentVariantMetrics = experimentVariantMetrics;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setProcessedDate(LocalDateTime processedDate) {
		_processedDate = processedDate;
	}

	@Transient
	private double _completion;

	@Transient
	private double _confidenceLevel;

	@Transient
	private long _elapsedDays;

	@Transient
	private Long _estimatedDaysLeft;

	@Transient
	private Set<ExperimentVariantMetric> _experimentVariantMetrics =
		new LinkedHashSet<>();

	@Transient
	private Long _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private LocalDateTime _processedDate;

}