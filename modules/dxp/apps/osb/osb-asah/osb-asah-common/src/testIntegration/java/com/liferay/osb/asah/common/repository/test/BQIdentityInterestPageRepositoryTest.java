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

package com.liferay.osb.asah.common.repository.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.dog.util.SortUtil;
import com.liferay.osb.asah.common.model.Transformation;
import com.liferay.osb.asah.common.repository.BQIdentityInterestPageRepository;
import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 * @author Leslie Wong
 */
public class BQIdentityInterestPageRepositoryTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testCountActivePagesTransformationsIndividual() {
		Assertions.assertEquals(
			2,
			_bqIdentityInterestPageRepository.countActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual"));
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testCountActivePagesTransformationsIndividualSegment() {
		Assertions.assertEquals(
			5,
			_bqIdentityInterestPageRepository.countActivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment"));
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testCountInactivePagesTransformationsIndividual() {
		Assertions.assertEquals(
			5,
			_bqIdentityInterestPageRepository.countInactivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual"));
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testCountInactivePagesTransformationsIndividualSegment() {
		Assertions.assertEquals(
			2,
			_bqIdentityInterestPageRepository.countInactivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment"));
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testGetActivePagesTransformationsIndividual() {
		List<Transformation> expectedTransformations = new ArrayList<>();

		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Weight");
							put("uniqueVisitsCount", BigDecimal.valueOf(6));
							put(
								"url",
								"https://www.know-your-ratios.com/weight");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Home");
							put("uniqueVisitsCount", BigDecimal.valueOf(2));
							put("url", "https://www.know-your-ratios.com");
						}
					}),
				null));

		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual", PageRequest.of(0, 5)),
			expectedTransformations);
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testGetActivePagesTransformationsIndividualSegment() {
		List<Transformation> expectedTransformations = new ArrayList<>();

		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Distance");
							put("uniqueVisitsCount", BigDecimal.valueOf(13));
							put(
								"url",
								"https://www.know-your-ratios.com/distance");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Time");
							put("uniqueVisitsCount", BigDecimal.valueOf(7));
							put("url", "https://www.know-your-ratios.com/time");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Volume");
							put("uniqueVisitsCount", BigDecimal.valueOf(7));
							put(
								"url",
								"https://www.know-your-ratios.com/volume");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Weight");
							put("uniqueVisitsCount", BigDecimal.valueOf(6));
							put(
								"url",
								"https://www.know-your-ratios.com/weight");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Home");
							put("uniqueVisitsCount", BigDecimal.valueOf(2));
							put("url", "https://www.know-your-ratios.com");
						}
					}),
				null));

		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment", PageRequest.of(0, 5)),
			expectedTransformations);
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testGetActivePagesTransformationsIndividualWithSort() {
		Transformation transformation1 = new Transformation(
			new Transformation.Term(
				new HashMap<String, Object>() {
					{
						put("title", "Know Your Ratios - Weight");
						put("uniqueVisitsCount", BigDecimal.valueOf(6));
						put("url", "https://www.know-your-ratios.com/weight");
					}
				}),
			null);

		Transformation transformation2 = new Transformation(
			new Transformation.Term(
				new HashMap<String, Object>() {
					{
						put("title", "Know Your Ratios - Home");
						put("uniqueVisitsCount", BigDecimal.valueOf(2));
						put("url", "https://www.know-your-ratios.com");
					}
				}),
			null);

		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"url", "asc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation2);
					add(transformation1);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"url", "desc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation1);
					add(transformation2);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual",
				PageRequest.of(
					0, 5,
					SortUtil.getSort(
						new String[] {"uniqueVisitsCount", "asc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation2);
					add(transformation1);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual",
				PageRequest.of(
					0, 5,
					SortUtil.getSort(
						new String[] {"uniqueVisitsCount", "desc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation1);
					add(transformation2);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"title", "asc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation2);
					add(transformation1);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getActivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"title", "desc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation1);
					add(transformation2);
				}
			});
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testGetInactivePagesTransformationsIndividual() {
		List<Transformation> expectedTransformations = new ArrayList<>();

		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Basic Calculator");
							put("uniqueVisitsCount", BigDecimal.valueOf(0));
							put("url", "https://www.calculator.com/basic");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Distance");
							put("uniqueVisitsCount", BigDecimal.valueOf(0));
							put(
								"url",
								"https://www.know-your-ratios.com/distance");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Time");
							put("uniqueVisitsCount", BigDecimal.valueOf(0));
							put("url", "https://www.know-your-ratios.com/time");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Know Your Ratios - Volume");
							put("uniqueVisitsCount", BigDecimal.valueOf(0));
							put(
								"url",
								"https://www.know-your-ratios.com/volume");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Scientific Calculator");
							put("uniqueVisitsCount", BigDecimal.valueOf(0));
							put("url", "https://www.calculator.com/scientific");
						}
					}),
				null));

		_assertTransformations(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				"interestName eq 'ratio'",
				"ae9fbeefab123032b0ce91e946ec50930aeb2f55116ee887d142a6c32f4f" +
					"d9f4",
				"individual", PageRequest.of(0, 5)),
			expectedTransformations);
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testGetInactivePagesTransformationsIndividualSegment() {
		List<Transformation> expectedTransformations = new ArrayList<>();

		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Basic Calculator");
							put("uniqueVisitsCount", BigDecimal.valueOf(0));
							put("url", "https://www.calculator.com/basic");
						}
					}),
				null));
		expectedTransformations.add(
			new Transformation(
				new Transformation.Term(
					new HashMap<String, Object>() {
						{
							put("title", "Scientific Calculator");
							put("uniqueVisitsCount", BigDecimal.valueOf(0));
							put("url", "https://www.calculator.com/scientific");
						}
					}),
				null));

		_assertTransformations(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment", PageRequest.of(0, 5)),
			expectedTransformations);
	}

	@BQSQLResource(
		resourcePath = "test_bq_identity_interest_page_repository.sql"
	)
	@Test
	public void testGetInactivePagesTransformationsIndividualSegmentWithSort() {
		Transformation transformation1 = new Transformation(
			new Transformation.Term(
				new HashMap<String, Object>() {
					{
						put("title", "Basic Calculator");
						put("uniqueVisitsCount", BigDecimal.valueOf(0));
						put("url", "https://www.calculator.com/basic");
					}
				}),
			null);

		Transformation transformation2 = new Transformation(
			new Transformation.Term(
				new HashMap<String, Object>() {
					{
						put("title", "Scientific Calculator");
						put("uniqueVisitsCount", BigDecimal.valueOf(0));
						put("url", "https://www.calculator.com/scientific");
					}
				}),
			null);

		_assertTransformations(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"title", "asc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation1);
					add(transformation2);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"title", "desc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation2);
					add(transformation1);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"url", "asc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation1);
					add(transformation2);
				}
			});
		_assertTransformations(
			_bqIdentityInterestPageRepository.getInactivePagesTransformations(
				"interestName eq 'ratio'", "1234567891011",
				"individual-segment",
				PageRequest.of(
					0, 5, SortUtil.getSort(new String[] {"url", "desc"}))),
			new ArrayList<Transformation>() {
				{
					add(transformation2);
					add(transformation1);
				}
			});
	}

	private void _assertTransformations(
		List<Transformation> actualTransformations,
		List<Transformation> expectedTransformations) {

		Assertions.assertEquals(
			expectedTransformations.size(), actualTransformations.size());

		for (int i = 0; i < expectedTransformations.size(); i++) {
			Assertions.assertEquals(
				expectedTransformations.get(i), actualTransformations.get(i));
		}
	}

	@Autowired
	private BQIdentityInterestPageRepository _bqIdentityInterestPageRepository;

}