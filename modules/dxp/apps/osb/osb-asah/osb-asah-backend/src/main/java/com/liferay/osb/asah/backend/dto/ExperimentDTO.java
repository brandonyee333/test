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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.Experiment;
import com.liferay.osb.asah.common.graphql.GraphQLProperty;
import com.liferay.osb.asah.common.graphql.GraphQLType;
import com.liferay.osb.asah.common.model.Goal;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

/**
 * @author Marcos Martins
 */
@GraphQLType("Experiment")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("experiments")
public class ExperimentDTO {

	public ExperimentDTO() {
	}

	public ExperimentDTO(Experiment experiment) {
		_channelId = experiment.getChannelId();
		_confidenceLevel = experiment.getConfidenceLevel();
		_createDate = experiment.getCreateDate();
		_dataSourceId = StringUtil.get(experiment.getDataSourceId(), null);
		_description = experiment.getDescription();
		_dxpExperienceId = experiment.getDXPExperienceId();
		_dxpExperienceName = experiment.getDXPExperienceName();
		_dxpGroupId = experiment.getDXPGroupId();
		_dxpLayoutId = experiment.getDXPLayoutId();
		_dxpSegmentId = experiment.getDXPSegmentId();
		_dxpSegmentName = experiment.getDXPSegmentName();

		Set<ExperimentVariantDTO> experimentVariantDTOs = SetUtil.map(
			experiment.getExperimentVariants(), ExperimentVariantDTO::new);

		if (!experimentVariantDTOs.isEmpty()) {
			_experimentVariantDTOs = experimentVariantDTOs;
		}

		Set<ExperimentMetricDTO> experimentMetricsDTOs = SetUtil.map(
			experiment.getExperimentMetrics(), ExperimentMetricDTO::new);

		if (!experimentMetricsDTOs.isEmpty()) {
			_experimentMetricDTOs = experimentMetricsDTOs;
		}

		_experimentStatus = StringUtil.get(
			experiment.getExperimentStatus(), null);
		_experimentType = StringUtil.get(experiment.getExperimentType(), null);
		_finishedDate = experiment.getFinishedDate();

		Goal goal = experiment.getGoal();

		if (goal != null) {
			_goalDTO = new GoalDTO(goal);
		}

		_id = StringUtil.get(experiment.getId(), null);
		_modifiedDate = experiment.getModifiedDate();
		_name = experiment.getName();
		_pageRelativePath = experiment.getPageRelativePath();
		_pageTitle = experiment.getPageTitle();
		_pageURL = experiment.getPageURL();
		_processedDate = experiment.getProcessedDate();
		_publishedDXPVariantId = experiment.getPublishedDXPVariantId();
		_startedDate = experiment.getStartedDate();
		_winnerDXPVariantId = experiment.getWinnerDXPVariantId();
	}

	public ExperimentDTO(List<Experiment> experiment) {
		_experimentDTOs = SetUtil.map(experiment, ExperimentDTO::new);
	}

	public Long getChannelId() {
		return _channelId;
	}

	public Double getConfidenceLevel() {
		return _confidenceLevel;
	}

	@JsonAlias("dateCreated")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("createDate")
	@NotNull
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@GraphQLProperty("createDate")
	@JsonIgnore
	public String getCreateDateISO() {
		if (_createDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_createDate);
	}

	public String getDataSourceId() {
		return _dataSourceId;
	}

	public String getDescription() {
		return _description;
	}

	@JsonProperty("dxpExperienceId")
	@NotBlank
	public String getDXPExperienceId() {
		return _dxpExperienceId;
	}

	@GraphQLProperty("dxpExperienceName")
	@JsonProperty("dxpExperienceName")
	@NotBlank
	public String getDXPExperienceName() {
		return _dxpExperienceName;
	}

	@JsonProperty("dxpGroupId")
	public String getDXPGroupId() {
		return _dxpGroupId;
	}

	@JsonProperty("dxpLayoutId")
	@NotBlank
	public String getDXPLayoutId() {
		return _dxpLayoutId;
	}

	@JsonProperty("dxpSegmentId")
	@NotBlank
	public String getDXPSegmentId() {
		return _dxpSegmentId;
	}

	@GraphQLProperty("dxpSegmentName")
	@JsonProperty("dxpSegmentName")
	public String getDXPSegmentName() {
		return _dxpSegmentName;
	}

	@JsonProperty("experiments")
	public Set<ExperimentDTO> getExperimentDTOs() {
		return _experimentDTOs;
	}

	@JsonProperty("metrics")
	public Set<ExperimentMetricDTO> getExperimentMetrics() {
		return _experimentMetricDTOs;
	}

	@GraphQLProperty("status")
	@JsonProperty("status")
	public String getExperimentStatus() {
		return _experimentStatus;
	}

	@GraphQLProperty("type")
	@JsonProperty("type")
	public String getExperimentType() {
		return _experimentType;
	}

	@GraphQLProperty("dxpVariants")
	@JsonProperty("dxpVariants")
	@Valid
	public Set<ExperimentVariantDTO> getExperimentVariants() {
		return _experimentVariantDTOs;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getFinishedDate() {
		if (_finishedDate == null) {
			return null;
		}

		return new Date(_finishedDate.getTime());
	}

	@GraphQLProperty("finishedDate")
	@JsonIgnore
	public String getFinishedDateISO() {
		if (_finishedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_finishedDate);
	}

	@Valid
	public GoalDTO getGoal() {
		return _goalDTO;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@GraphQLProperty("modifiedDate")
	@JsonIgnore
	public String getModifiedDateISO() {
		if (_modifiedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_modifiedDate);
	}

	@NotBlank
	public String getName() {
		return _name;
	}

	@NotBlank
	public String getPageRelativePath() {
		return _pageRelativePath;
	}

	public String getPageTitle() {
		return _pageTitle;
	}

	@NotBlank
	@URL
	public String getPageURL() {
		return _pageURL;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getProcessedDate() {
		if (_processedDate == null) {
			return null;
		}

		return new Date(_processedDate.getTime());
	}

	public String getPublishedDXPVariantId() {
		return _publishedDXPVariantId;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getStartedDate() {
		if (_startedDate == null) {
			return null;
		}

		return new Date(_startedDate.getTime());
	}

	@GraphQLProperty("startedDate")
	@JsonIgnore
	public String getStartedDateISO() {
		if (_startedDate == null) {
			return null;
		}

		return DateUtil.toUTCString(_startedDate);
	}

	@JsonIgnore
	public LocalDateTime getStartedDateLocalDateTime() {
		if (_startedDate == null) {
			return null;
		}

		return DateUtil.toLocalDateTime(_startedDate, ZoneOffset.UTC);
	}

	public String getWinnerDXPVariantId() {
		return _winnerDXPVariantId;
	}

	private Long _channelId;
	private Double _confidenceLevel;
	private Date _createDate;
	private String _dataSourceId;
	private String _description;
	private String _dxpExperienceId;
	private String _dxpExperienceName;
	private String _dxpGroupId;
	private String _dxpLayoutId;
	private String _dxpSegmentId;
	private String _dxpSegmentName;
	private Set<ExperimentDTO> _experimentDTOs = new HashSet<>();
	private Set<ExperimentMetricDTO> _experimentMetricDTOs = new HashSet<>();
	private String _experimentStatus;
	private String _experimentType;
	private Set<ExperimentVariantDTO> _experimentVariantDTOs = new HashSet<>();
	private Date _finishedDate;
	private GoalDTO _goalDTO;
	private String _id;
	private Date _modifiedDate;
	private String _name;
	private String _pageRelativePath;
	private String _pageTitle;
	private String _pageURL;
	private Date _processedDate;
	private String _publishedDXPVariantId;
	private Date _startedDate;
	private String _winnerDXPVariantId;

}