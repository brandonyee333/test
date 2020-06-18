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

package com.liferay.osb.asah.backend.rest.controller.api.external;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.backend.dog.JobDog;
import com.liferay.osb.asah.backend.dog.RecommendationDog;
import com.liferay.osb.asah.backend.model.ItemRecommendation;
import com.liferay.osb.asah.backend.model.Job;
import com.liferay.osb.asah.backend.model.JobStatus;
import com.liferay.osb.asah.backend.model.ResultBag;
import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.util.ListUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.digest.DigestUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@RequestMapping(produces = "application/json", value = "/api/recommendations")
@RestController
public class RecommendationRestController extends BaseRestController {

	@GetMapping
	public ResourceSupport getLinksResourceSupport() {
		return new ResourceSupport() {
			{
				add(
					Arrays.asList(
						ControllerLinkBuilder.linkTo(
							_getModelResultBagResource(null, null)
						).withRel(
							"models"
						)));
			}
		};
	}

	@GetMapping("/models/{modelId}")
	public Resource<Model> getModelResource(@PathVariable String modelId) {
		return _toModelResource(_jobDog.getJob(modelId));
	}

	@GetMapping("/models")
	public ResultBagResource<Model> getModelResultBagResource(
		@RequestParam(defaultValue = "0") Integer page,
		@RequestParam(defaultValue = "") String keywords) {

		ResultBag<Job> jobResultBag = _jobDog.getJobResultBag(
			keywords, _PAGE_SIZE, Sort.desc("id"), page * _PAGE_SIZE);

		return _toResultBagResource(
			_getModelResultBagResource(page + 1, keywords), page,
			_getModelResultBagResource(page - 1, keywords), jobResultBag,
			this::_toModelResource);
	}

	@PostMapping("/recommended-pages")
	public Resource<PageRecommendation> getPageRecommendationResource(
		@RequestBody String json) {

		JSONObject jsonObject = new JSONObject(json);

		return getPageRecommendationResource(
			jsonObject.getString("modelId"),
			DigestUtils.sha1Hex(
				jsonObject.getString("modelId") +
					jsonObject.getString("item")));
	}

	@GetMapping("/models/{modelId}/recommended-pages/{recommendationId}")
	public Resource<PageRecommendation> getPageRecommendationResource(
		@PathVariable String modelId, @PathVariable String recommendationId) {

		return _toPageRecommendationResource(
			_recommendationDog.getItemRecommendation(recommendationId));
	}

	private ResultBagResource<Model> _getModelResultBagResource(
		Integer page, String keywords) {

		return ControllerLinkBuilder.methodOn(
			RecommendationRestController.class
		).getModelResultBagResource(
			page, keywords
		);
	}

	private <T, R> List<Resource<R>> _toListResource(
		List<T> results,
		Function<T, Resource<R>> resultResourceMapperFunction) {

		Stream<T> stream = results.stream();

		return stream.map(
			resultResourceMapperFunction
		).collect(
			Collectors.toList()
		);
	}

	private Resource<Model> _toModelResource(Job job) {
		return new Resource<>(
			new Model(job, _jobDog.getJobStatus(job.getId())),
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					RecommendationRestController.class
				).getModelResource(
					job.getId()
				)
			).withSelfRel());
	}

	private PageRecommendation _toPageRecommendation(
		ItemRecommendation itemRecommendation) {

		PageRecommendation pageRecommendation = new PageRecommendation();

		pageRecommendation.setId(itemRecommendation.getId());
		pageRecommendation.setJobId(itemRecommendation.getJobId());
		pageRecommendation.setURL(itemRecommendation.getItemId());
		pageRecommendation.setPageRecommendations(
			ListUtil.map(
				itemRecommendation.getRecommendedItemIds(),
				recommendedItemId -> _toPageRecommendation(
					itemRecommendation.getJobId(), recommendedItemId)));

		return pageRecommendation;
	}

	private PageRecommendation _toPageRecommendation(String jobId, String url) {
		PageRecommendation pageRecommendation = new PageRecommendation();

		pageRecommendation.setId(DigestUtils.sha1Hex(jobId + url));
		pageRecommendation.setJobId(jobId);
		pageRecommendation.setURL(url);

		return pageRecommendation;
	}

	private Resource<PageRecommendation> _toPageRecommendationResource(
		ItemRecommendation itemRecommendation) {

		return new Resource<>(
			_toPageRecommendation(itemRecommendation),
			ControllerLinkBuilder.linkTo(
				ControllerLinkBuilder.methodOn(
					RecommendationRestController.class
				).getPageRecommendationResource(
					itemRecommendation.getJobId(), itemRecommendation.getId()
				)
			).withSelfRel());
	}

	private <T, R> ResultBagResource<R> _toResultBagResource(
		Object nextPageMethodInvocation, int page,
		Object prevPageMethodInvocation, ResultBag<T> resultBag,
		Function<T, Resource<R>> resultResourceMapperFunction) {

		ResultBagResource<R> resultBagResource = new ResultBagResource<>(
			new ResultBag<>(
				_toListResource(
					resultBag.getResults(), resultResourceMapperFunction),
				resultBag.getTotal()));

		if (((page + 1L) * _PAGE_SIZE) < resultBag.getTotal()) {
			resultBagResource.add(
				ControllerLinkBuilder.linkTo(
					nextPageMethodInvocation
				).withRel(
					"next"
				));
		}

		if (page > 0) {
			resultBagResource.add(
				ControllerLinkBuilder.linkTo(
					prevPageMethodInvocation
				).withRel(
					"prev"
				));
		}

		return resultBagResource;
	}

	private static final int _PAGE_SIZE = 20;

	@Autowired
	private JobDog _jobDog;

	@Autowired
	private RecommendationDog _recommendationDog;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private static class Model extends Job {

		public Model(Job job, JobStatus jobStatus) {
			setId(job.getId());
			setJobParameters(job.getJobParameters());
			setJobStatus(jobStatus);
			setJobTrainingFrequency(job.getJobTrainingFrequency());
			setJobTrainingPeriod(job.getJobTrainingPeriod());
			setJobType(job.getJobType());
			setName(job.getName());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!super.equals(obj)) {
				return false;
			}

			if (!(obj instanceof Model)) {
				return false;
			}

			Model model = (Model)obj;

			if (super.equalsJob(model) &&
				Objects.equals(_jobStatus, model._jobStatus)) {

				return true;
			}

			return false;
		}

		@JsonProperty("status")
		public JobStatus getStatus() {
			return _jobStatus;
		}

		@Override
		public int hashCode() {
			return super.hashCode() ^ Objects.hash(_jobStatus);
		}

		public void setJobStatus(JobStatus jobStatus) {
			_jobStatus = jobStatus;
		}

		private JobStatus _jobStatus;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private static class PageRecommendation {

		@JsonIgnore
		public String getId() {
			return _id;
		}

		@JsonIgnore
		public String getJobId() {
			return _jobId;
		}

		@JsonProperty("recommendedPages")
		public List<PageRecommendation> getPageRecommendations() {
			return _pageRecommendations;
		}

		public String getURL() {
			return _url;
		}

		public void setId(String id) {
			_id = id;
		}

		public void setJobId(String jobId) {
			_jobId = jobId;
		}

		public void setPageRecommendations(
			List<PageRecommendation> pageRecommendations) {

			_pageRecommendations = pageRecommendations;
		}

		public void setURL(String url) {
			_url = url;
		}

		private String _id;
		private String _jobId;
		private List<PageRecommendation> _pageRecommendations;
		private String _url;

	}

	private static class ResultBagResource<T>
		extends Resource<ResultBag<Resource<T>>> {

		public ResultBagResource(
			ResultBag<Resource<T>> content, Link... links) {

			super(content, links);
		}

	}

}