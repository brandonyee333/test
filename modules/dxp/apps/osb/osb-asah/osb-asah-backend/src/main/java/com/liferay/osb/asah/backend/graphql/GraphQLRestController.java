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

package com.liferay.osb.asah.backend.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import com.liferay.osb.asah.backend.model.BlogMetric;
import com.liferay.osb.asah.backend.model.DocumentLibraryMetric;
import com.liferay.osb.asah.backend.model.FormMetric;
import com.liferay.osb.asah.backend.model.JournalMetric;
import com.liferay.osb.asah.backend.model.Organization;
import com.liferay.osb.asah.backend.model.User;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLError;
import graphql.GraphqlErrorHelper;

import graphql.introspection.IntrospectionQuery;

import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.PropertyDataFetcher;
import graphql.schema.TypeResolver;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.Charset;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@CrossOrigin
@RequestMapping("/graphql")
@RestController
public class GraphQLRestController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/schema")
	public String get() throws Exception {
		ExecutionResult executionResult = _graphQL.execute(
			IntrospectionQuery.INTROSPECTION_QUERY);

		return _toString(executionResult);
	}

	@PostConstruct
	public void init() {
		_graphQL = _buildGraphQL();

		_objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		_objectMapper.registerModule(new Jdk8Module());
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "")
	public String post(HttpServletRequest httpServletRequest) throws Exception {
		String body = IOUtils.toString(
			httpServletRequest.getInputStream(), Charset.defaultCharset());

		if (_log.isDebugEnabled()) {
			_log.debug("Post body: " + body);
		}

		return _getGraphQLExecutionResult(
			_objectMapper.readValue(body, GraphQLRequest.class));
	}

	private TypeResolver _buildAssetTypeRevolver() {
		return typeResolutionEnvironment -> {
			Object object = typeResolutionEnvironment.getObject();

			GraphQLSchema graphQLSchema = typeResolutionEnvironment.getSchema();

			if (object instanceof BlogMetric) {
				return (GraphQLObjectType)graphQLSchema.getType("BlogMetric");
			}
			else if (object instanceof DocumentLibraryMetric) {
				return (GraphQLObjectType)graphQLSchema.getType(
					"DocumentMetric");
			}
			else if (object instanceof FormMetric) {
				return (GraphQLObjectType)graphQLSchema.getType("FormMetric");
			}
			else if (object instanceof JournalMetric) {
				return (GraphQLObjectType)graphQLSchema.getType(
					"JournalMetric");
			}
			else {
				return (GraphQLObjectType)graphQLSchema.getType("PageMetric");
			}
		};
	}

	private TypeResolver _buildDXPEntityTypeResolver() {
		return typeResolutionEnvironment -> {
			GraphQLSchema graphQLSchema = typeResolutionEnvironment.getSchema();

			if (typeResolutionEnvironment.getObject() instanceof Organization) {
				return (GraphQLObjectType)graphQLSchema.getType("Organization");
			}
			else if (typeResolutionEnvironment.getObject() instanceof User) {
				return (GraphQLObjectType)graphQLSchema.getType("User");
			}

			return (GraphQLObjectType)graphQLSchema.getType("BaseDXPEntity");
		};
	}

	private GraphQL _buildGraphQL() {
		SchemaGenerator schemaGenerator = new SchemaGenerator();

		GraphQL.Builder builder = GraphQL.newGraphQL(
			schemaGenerator.makeExecutableSchema(
				_buildTypeDefinitionRegistry(), _buildRuntimeWiring()));

		return builder.build();
	}

	private RuntimeWiring _buildRuntimeWiring() {
		RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring();

		builder.type(
			"AssetMetric",
			typeWiring -> typeWiring.typeResolver(_buildAssetTypeRevolver()));
		builder.type(
			"DXPEntity",
			typeWiring -> typeWiring.typeResolver(
				_buildDXPEntityTypeResolver()));

		_defineCustomPropertyName(
			builder, "assetMetrics", "results", "AssetMetricBag");
		_defineCustomPropertyName(
			builder, "compositions", "results", "CompositionBag");
		_defineCustomPropertyName(
			builder, "confidenceInterval", "confidenceIntervalArray",
			"VariantMetrics");
		_defineCustomPropertyName(
			builder, "createDate", "createDateISO", "Experiment");
		_defineCustomPropertyName(
			builder, "dashboards", "results", "DashboardBag");
		_defineCustomPropertyName(
			builder, "dataControlTasks", "results", "DataControlTaskBag");
		_defineCustomPropertyName(
			builder, "dxpEntities", "results", "DXPEntityBag");
		_defineCustomPropertyName(
			builder, "dxpExperienceName", "DXPExperienceName", "Experiment");
		_defineCustomPropertyName(
			builder, "dxpSegmentName", "DXPSegmentName", "Experiment");
		_defineCustomPropertyName(
			builder, "dxpVariantId", "DXPVariantId", "DXPVariant");
		_defineCustomPropertyName(
			builder, "dxpVariantId", "DXPVariantId", "VariantMetrics");
		_defineCustomPropertyName(
			builder, "dxpVariantName", "DXPVariantName", "DXPVariant");
		_defineCustomPropertyName(
			builder, "dxpVariants", "DXPVariants", "Experiment");
		_defineCustomPropertyName(
			builder, "experiments", "results", "ExperimentBag");
		_defineCustomPropertyName(
			builder, "finishedDate", "finishedDateISO", "Experiment");
		_defineCustomPropertyName(
			builder, "individuals", "results", "IndividualBag");
		_defineCustomPropertyName(builder, "metric", "goalMetric", "Goal");
		_defineCustomPropertyName(builder, "metrics", "results", "MetricBag");
		_defineCustomPropertyName(
			builder, "modifiedDate", "modifiedDateISO", "Experiment");
		_defineCustomPropertyName(
			builder, "parameters", "jobParameters", "Job");
		_defineCustomPropertyName(
			builder, "startedDate", "startedDateISO", "Experiment");
		_defineCustomPropertyName(
			builder, "status", "dataExportTaskStatus", "DataExportTask");
		_defineCustomPropertyName(
			builder, "status", "experimentStatus", "Experiment");
		_defineCustomPropertyName(
			builder, "suppressions", "results", "SuppressionBag");
		_defineCustomPropertyName(
			builder, "type", "dataExportTaskType", "DataExportTask");
		_defineCustomPropertyName(
			builder, "type", "experimentType", "Experiment");
		_defineCustomPropertyName(builder, "type", "jobType", "Job");
		_defineCustomPropertyName(
			builder, "variantMetrics", "variantMetricsList",
			"ExperimentMetrics");

		for (DataFetcher dataFetcher : _dataFetchers) {
			Class<?> clazz = dataFetcher.getClass();

			Set<GraphQLTypeWiring> graphQLTypeWirings =
				AnnotatedElementUtils.findMergedRepeatableAnnotations(
					clazz, GraphQLTypeWiring.class);

			for (GraphQLTypeWiring graphQLTypeWiring : graphQLTypeWirings) {
				builder.type(
					graphQLTypeWiring.typeName(),
					typeWiring -> typeWiring.dataFetcher(
						graphQLTypeWiring.fieldName(), dataFetcher));

				if (_log.isDebugEnabled()) {
					_log.debug(
						String.format(
							"GraphQL type wiring: %s#%s -> %s",
							graphQLTypeWiring.typeName(),
							graphQLTypeWiring.fieldName(),
							clazz.getSimpleName()));
				}
			}
		}

		return builder.build();
	}

	private TypeDefinitionRegistry _buildTypeDefinitionRegistry() {
		SchemaParser schemaParser = new SchemaParser();

		Class<?> clazz = getClass();

		return schemaParser.parse(
			new InputStreamReader(
				clazz.getResourceAsStream("asah.graphqls"),
				Charset.defaultCharset()));
	}

	private void _defineCustomPropertyName(
		RuntimeWiring.Builder builder, String fieldName, String propertyName,
		String typeName) {

		builder.type(
			typeName,
			typeWiring -> typeWiring.dataFetcher(
				fieldName, new PropertyDataFetcher(propertyName)));
	}

	private String _getGraphQLExecutionResult(GraphQLRequest graphQLRequest)
		throws Exception {

		String query = graphQLRequest.getQuery();
		Map<String, Object> variables = graphQLRequest.getVariables();

		String cacheKey = query + "#" + variables;

		boolean skipCache = _skipCache(query, variables);

		if ((_cacheManager != null) && !skipCache) {
			if (_cache == null) {
				_cache = _cacheManager.getCache("getGraphQLExecutionResult");
			}

			if (_cache != null) {
				Cache.ValueWrapper cacheValueWrapper = _cache.get(cacheKey);

				if (cacheValueWrapper != null) {
					return String.valueOf(cacheValueWrapper.get());
				}
			}
		}

		SimpleTimer simpleTimer = new SimpleTimer();

		String operationName = null;

		try {
			ExecutionInput.Builder builder = ExecutionInput.newExecutionInput();

			operationName = graphQLRequest.getOperationName();

			ExecutionInput executionInput = builder.context(
				new HashMap<String, Object>()
			).operationName(
				operationName
			).query(
				graphQLRequest.getQuery()
			).variables(
				graphQLRequest.getVariables()
			).build();

			ExecutionResult executionResult = _graphQL.execute(executionInput);

			String executionResultString = _toString(executionResult);

			if ((_cache != null) && !skipCache) {
				_cache.put(cacheKey, executionResultString);
			}

			return executionResultString;
		}
		finally {
			if (operationName == null) {
				operationName = "unknown";
			}

			Histogram.Child child = _graphQLRequestsHistogram.labels(
				operationName);

			child.observe(simpleTimer.elapsedSeconds());
		}
	}

	private boolean _skipCache(String query, Map<String, Object> variables) {
		JSONObject variablesJSONObject = new JSONObject(variables);

		if (!query.startsWith("{pagesCount") &&
			!query.startsWith("query IndividualMetrics")) {

			if (variablesJSONObject.has("rangeEnd")) {
				String rangeEnd = variablesJSONObject.getString("rangeEnd");

				LocalDate currentLocalDate = LocalDate.now();

				if (currentLocalDate.equals(LocalDate.parse(rangeEnd))) {
					return true;
				}
			}
			else if (variablesJSONObject.optInt("rangeKey") > 0) {
				return false;
			}
		}

		return true;
	}

	private String _toString(ExecutionResult executionResult)
		throws IOException {

		Map<String, Object> map = new HashMap<>();

		map.put("data", executionResult.getData());

		List<GraphQLError> graphQLErrors = executionResult.getErrors();

		if ((graphQLErrors != null) && !graphQLErrors.isEmpty()) {
			Stream<GraphQLError> stream = graphQLErrors.stream();

			map.put(
				"errors",
				stream.map(
					GraphqlErrorHelper::toSpecification
				).collect(
					Collectors.toList()
				));
		}

		return _objectMapper.writeValueAsString(map);
	}

	private static final Log _log = LogFactory.getLog(
		GraphQLRestController.class);

	private static final Histogram _graphQLRequestsHistogram =
		PrometheusUtil.histogram(
			"backend_graphql_request_seconds",
			"The number of seconds taken to process the GraphQL requests",
			"operation");

	private Cache _cache;

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired
	private List<DataFetcher> _dataFetchers;

	private GraphQL _graphQL;

	private final ObjectMapper _objectMapper = new ObjectMapper() {
		{
			disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

			registerModule(new Jdk8Module());
		}
	};

}