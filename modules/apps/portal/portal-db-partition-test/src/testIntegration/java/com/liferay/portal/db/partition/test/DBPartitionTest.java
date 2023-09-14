/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.db.partition.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.petra.function.UnsafeBiConsumer;
import com.liferay.petra.lang.SafeCloseable;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.db.partition.DBPartitionUtil;
import com.liferay.portal.db.partition.test.util.BaseDBPartitionTestCase;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.model.impl.ClassNameImpl;
import com.liferay.portal.service.impl.ClassNameLocalServiceImpl;
import com.liferay.portal.service.impl.CompanyLocalServiceImpl;
import com.liferay.portal.spring.aop.AopInvocationHandler;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.util.PortalInstances;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Alberto Chaparro
 */
@RunWith(Arquillian.class)
public class DBPartitionTest extends BaseDBPartitionTestCase {

	@BeforeClass
	public static void setUpClass() throws Exception {
		enableDBPartition();

		entityCache.removeCache(ClassNameImpl.class.getName());
		finderCache.removeCache(ClassNameImpl.class.getName());

		createControlTable(TEST_CONTROL_TABLE_NAME);

		addDBPartitions();

		insertPartitionRequiredData();
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		deletePartitionRequiredData();

		removeDBPartitions(false);

		dropTable(TEST_CONTROL_TABLE_NAME);

		disableDBPartition();

		entityCache.removeCache(ClassNameImpl.class.getName());
		finderCache.removeCache(ClassNameImpl.class.getName());
	}

	@After
	public void tearDown() throws Exception {
		if (dbInspector.hasIndex(TEST_CONTROL_TABLE_NAME, TEST_INDEX_NAME)) {
			dropIndex(TEST_CONTROL_TABLE_NAME);
		}

		dropTable(TEST_TABLE_NAME);
	}

	@Test
	public void testAddIndexControlTable() throws Exception {
		DBPartitionUtil.forEachCompanyId(
			companyId -> createIndex(TEST_CONTROL_TABLE_NAME));

		Assert.assertTrue(
			dbInspector.hasIndex(TEST_CONTROL_TABLE_NAME, TEST_INDEX_NAME));
	}

	@Test
	public void testAddUniqueIndexControlTable() throws Exception {
		DBPartitionUtil.forEachCompanyId(
			companyId -> createUniqueIndex(TEST_CONTROL_TABLE_NAME));

		Assert.assertTrue(
			dbInspector.hasIndex(TEST_CONTROL_TABLE_NAME, TEST_INDEX_NAME));
	}

	@Test
	public void testAlterControlTable() throws Exception {
		try {
			DBPartitionUtil.forEachCompanyId(
				companyId -> db.runSQL(
					StringBundler.concat(
						"alter table ", TEST_CONTROL_TABLE_NAME, " add column ",
						TEST_CONTROL_TABLE_NEW_COLUMN, " bigint")));

			Assert.assertTrue(
				dbInspector.hasColumn(
					TEST_CONTROL_TABLE_NAME, TEST_CONTROL_TABLE_NEW_COLUMN));
		}
		finally {
			DBPartitionUtil.forEachCompanyId(
				companyId -> {
					if (dbInspector.hasColumn(
							TEST_CONTROL_TABLE_NAME,
							TEST_CONTROL_TABLE_NEW_COLUMN)) {

						db.runSQL(
							StringBundler.concat(
								"alter table ", TEST_CONTROL_TABLE_NAME,
								" drop column ",
								TEST_CONTROL_TABLE_NEW_COLUMN));
					}
				});
		}
	}

	@Test
	public void testCopyClassName() throws Exception {
		String classNameValue = "";
		long classNameId = 0;

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				"select value, classNameId from ClassName_ order by " +
					"classNameId asc limit 1; ");
			ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				classNameValue = resultSet.getString(1);
				classNameId = resultSet.getLong(2);
			}
		}

		ClassName nullClassName = ReflectionTestUtil.getFieldValue(
			ClassNameLocalServiceImpl.class, "_nullClassName");

		long finalClassNameId = classNameId;
		String finalClassNameValue = classNameValue;

		DBPartitionUtil.forEachCompanyId(
			companyId -> {
				ClassName className = _classNameLocalService.fetchClassName(
					finalClassNameValue);

				Assert.assertNotEquals(nullClassName, className);
				Assert.assertEquals(
					finalClassNameId, className.getClassNameId());
				Assert.assertEquals(finalClassNameValue, className.getValue());
			});
	}

	@Test
	public void testCounterGetNames() throws Exception {
		_runOnCompanyAndCleanup(
			(runCompanyId, className) -> {
				_counterLocalService.increment(className);

				DBPartitionUtil.forEachCompanyId(
					companyId -> {
						List<String> counterNames =
							_counterLocalService.getNames();

						if (companyId.equals(runCompanyId)) {
							Assert.assertTrue(counterNames.contains(className));
						}
						else {
							Assert.assertFalse(
								counterNames.contains(className));
						}
					});
			});
	}

	@Test
	public void testCounterIncrement() throws Exception {
		CopyOnWriteArrayList<Long> maxCounters = new CopyOnWriteArrayList<>();

		DBPartitionUtil.forEachCompanyId(
			companyId -> maxCounters.add(_counterLocalService.increment()));

		long maxCounter = 0;

		for (long counter : maxCounters) {
			if (counter > maxCounter) {
				maxCounter = counter;
			}
		}

		long finalMaxCounter = maxCounter + 1000;

		DBPartitionUtil.forEachCompanyId(
			companyId -> _counterLocalService.reset(
				Counter.class.getName(), finalMaxCounter));

		DBPartitionUtil.forEachCompanyId(
			companyId -> Assert.assertEquals(
				finalMaxCounter + 1, _counterLocalService.increment()));
	}

	@Test
	public void testCounterIncrementWithName() throws Exception {
		_runOnCompanyAndCleanup(
			(runCompanyId, className) -> {
				for (int i = 1; i <= 10; i++) {
					Assert.assertEquals(
						i, _counterLocalService.increment(className));
				}

				DBPartitionUtil.forEachCompanyId(
					companyId -> {
						if (runCompanyId.equals(companyId)) {
							Assert.assertEquals(
								11, _counterLocalService.increment(className));
						}
						else {
							Assert.assertEquals(
								1, _counterLocalService.increment(className));
						}
					});
			});
	}

	@Test
	public void testCounterIncrementWithNameAndSize() throws Exception {
		_runOnCompanyAndCleanup(
			(runCompanyId, className) -> {
				for (int i = 1; i <= 10; i++) {
					Assert.assertEquals(
						i * 10, _counterLocalService.increment(className, 10));
				}

				DBPartitionUtil.forEachCompanyId(
					companyId -> {
						if (runCompanyId.equals(companyId)) {
							Assert.assertEquals(
								110,
								_counterLocalService.increment(className, 10));
						}
						else {
							Assert.assertEquals(
								10,
								_counterLocalService.increment(className, 10));
						}
					});
			});
	}

	@Test
	public void testCounterRename() throws Exception {
		_runOnCompanyAndCleanup(
			(runCompanyId, className) -> {
				try {
					DBPartitionUtil.forEachCompanyId(
						companyId -> _counterLocalService.increment(className));

					_counterLocalService.rename(className, className + ".test");

					DBPartitionUtil.forEachCompanyId(
						companyId -> {
							List<String> counterNames =
								_counterLocalService.getNames();

							if (runCompanyId.equals(companyId)) {
								Assert.assertFalse(
									counterNames.contains(className));
								Assert.assertTrue(
									counterNames.contains(className + ".test"));
							}
							else {
								Assert.assertFalse(
									counterNames.contains(className + ".test"));
								Assert.assertTrue(
									counterNames.contains(className));
							}
						});
				}
				finally {
					DBPartitionUtil.forEachCompanyId(
						companyId -> _counterLocalService.reset(
							className + ".test"));
				}
			});
	}

	@Test
	public void testCounterReset() throws Exception {
		_runOnCompanyAndCleanup(
			(runCompanyId, className) -> {
				for (int i = 0; i < 10; i++) {
					_counterLocalService.increment(className, 100);
				}

				Assert.assertEquals(
					1001, _counterLocalService.increment(className));

				_counterLocalService.reset(getClass().getName());

				Assert.assertEquals(
					1, _counterLocalService.increment(className));

				DBPartitionUtil.forEachCompanyId(
					companyId -> {
						List<String> counterNames =
							_counterLocalService.getNames();

						if (runCompanyId.equals(companyId)) {
							Assert.assertTrue(counterNames.contains(className));
						}
						else {
							Assert.assertFalse(
								counterNames.contains(className));
						}
					});
			});
	}

	@Test
	public void testCounterResetWithIncrement() throws Exception {
		_runOnCompanyAndCleanup(
			(runCompanyId, className) -> {
				for (int i = 0; i < 10; i++) {
					_counterLocalService.increment(className, 100);
				}

				Assert.assertEquals(
					1001, _counterLocalService.increment(className));

				_counterLocalService.reset(getClass().getName(), 2000);

				Assert.assertEquals(
					2001, _counterLocalService.increment(className));

				DBPartitionUtil.forEachCompanyId(
					companyId -> {
						List<String> counterNames =
							_counterLocalService.getNames();

						if (runCompanyId.equals(companyId)) {
							Assert.assertTrue(counterNames.contains(className));
						}
						else {
							Assert.assertFalse(
								counterNames.contains(className));
						}
					});
			});
	}

	@Test
	public void testDropIndexControlTable() throws Exception {
		createIndex(TEST_CONTROL_TABLE_NAME);

		DBPartitionUtil.forEachCompanyId(
			companyId -> dropIndex(TEST_CONTROL_TABLE_NAME));

		Assert.assertTrue(
			!dbInspector.hasIndex(TEST_CONTROL_TABLE_NAME, TEST_INDEX_NAME));
	}

	@Test
	public void testGetClassName() throws Exception {
		Map<ClassName, String> classNames = Collections.synchronizedMap(
			new IdentityHashMap<>());

		try {
			DBPartitionUtil.forEachCompanyId(
				companyId -> Assert.assertNull(
					classNames.put(
						_classNameLocalService.getClassName("class.name.test"),
						"")));

			Assert.assertEquals(
				classNames.toString(), _companyLocalService.getCompaniesCount(),
				classNames.size());
		}
		finally {
			DBPartitionUtil.forEachCompanyId(
				companyId -> _classNameLocalService.deleteClassName(
					_classNameLocalService.fetchClassName("class.name.test")));
		}
	}

	@Test
	public void testRegenerateViews() throws Exception {
		try {
			DBPartitionUtil.forEachCompanyId(
				companyId -> db.runSQL(
					StringBundler.concat(
						"alter table ", TEST_CONTROL_TABLE_NAME, " add column ",
						TEST_CONTROL_TABLE_NEW_COLUMN, " bigint")));

			DBPartitionUtil.forEachCompanyId(
				companyId -> Assert.assertTrue(
					dbInspector.hasColumn(
						TEST_CONTROL_TABLE_NAME,
						TEST_CONTROL_TABLE_NEW_COLUMN)));
		}
		finally {
			DBPartitionUtil.forEachCompanyId(
				companyId -> {
					if (dbInspector.hasColumn(
							TEST_CONTROL_TABLE_NAME,
							TEST_CONTROL_TABLE_NEW_COLUMN)) {

						db.runSQL(
							StringBundler.concat(
								"alter table ", TEST_CONTROL_TABLE_NAME,
								" drop column ",
								TEST_CONTROL_TABLE_NEW_COLUMN));
					}
				});
		}
	}

	@Test
	public void testRemoveDBPartitionWhenCompanyCreationFails()
		throws Exception {

		AopInvocationHandler aopInvocationHandler =
			ProxyUtil.fetchInvocationHandler(
				_companyLocalService, AopInvocationHandler.class);

		CompanyLocalServiceImpl companyLocalServiceImpl =
			(CompanyLocalServiceImpl)aopInvocationHandler.getTarget();

		ReflectionTestUtil.setFieldValue(
			companyLocalServiceImpl, "_dlFileEntryTypeLocalService", null);

		long companyId = RandomTestUtil.randomLong();
		boolean orphanedDBPartition = false;
		String webId = "test.com";

		try {
			_companyLocalService.addCompany(
				companyId, webId, webId, webId, 0, true, null, null, null, null,
				null, null);
		}
		catch (Exception exception) {
			try (Connection connection = DataAccess.getConnection();
				PreparedStatement preparedStatement =
					connection.prepareStatement(
						StringBundler.concat(
							"select schema_name from ",
							"information_schema.schemata where schema_name = '",
							_DB_PARTITION_SCHEMA_NAME_PREFIX + companyId, "'"));
				ResultSet resultSet = preparedStatement.executeQuery()) {

				orphanedDBPartition = resultSet.next();

				Assert.assertFalse(
					"The database partition was not removed",
					orphanedDBPartition);
			}
		}
		finally {
			if (orphanedDBPartition) {
				removeDBPartitions(new long[] {companyId}, false);
			}
		}
	}

	@Test
	public void testUpdateIndexes() throws Exception {
		try {
			DBPartitionUtil.forEachCompanyId(
				companyId -> {
					createAndPopulateTable(TEST_TABLE_NAME);

					Assert.assertFalse(
						dbInspector.hasIndex(TEST_TABLE_NAME, TEST_INDEX_NAME));

					db.updateIndexes(
						connection, getCreateTableSQL(TEST_TABLE_NAME),
						getCreateIndexSQL(TEST_TABLE_NAME), true);

					Assert.assertTrue(
						dbInspector.hasIndex(TEST_TABLE_NAME, TEST_INDEX_NAME));
				});
		}
		finally {
			DBPartitionUtil.forEachCompanyId(
				companyId -> dropTable(TEST_TABLE_NAME));
		}
	}

	@Test
	public void testUpgrade() throws Exception {
		DBPartitionUpgradeProcess dbPartitionUpgradeProcess =
			new DBPartitionUpgradeProcess();

		dbPartitionUpgradeProcess.upgrade();

		long[] expectedCompanyIds = PortalInstances.getCompanyIdsBySQL();

		Arrays.sort(expectedCompanyIds);

		long[] actualCompanyIds = dbPartitionUpgradeProcess.getCompanyIds();

		Arrays.sort(actualCompanyIds);

		Assert.assertArrayEquals(expectedCompanyIds, actualCompanyIds);
	}

	public class DBPartitionUpgradeProcess extends UpgradeProcess {

		public long[] getCompanyIds() {
			return ArrayUtil.toArray(_companyIds.toArray(new Long[0]));
		}

		@Override
		protected void doUpgrade() throws Exception {
			_companyIds.add(CompanyThreadLocal.getCompanyId());
		}

		private volatile List<Long> _companyIds = new CopyOnWriteArrayList<>();

	}

	@Inject
	protected static EntityCache entityCache;

	@Inject
	protected static FinderCache finderCache;

	private void _runOnCompanyAndCleanup(
			UnsafeBiConsumer<Long, String, Exception> unsafeBiConsumer)
		throws Exception {

		try (SafeCloseable safeCloseable =
				CompanyThreadLocal.setWithSafeCloseable(COMPANY_IDS[0])) {

			unsafeBiConsumer.accept(COMPANY_IDS[0], getClass().getName());
		}
		finally {
			DBPartitionUtil.forEachCompanyId(
				companyId -> _counterLocalService.reset(getClass().getName()));
		}
	}

	private static final String _DB_PARTITION_SCHEMA_NAME_PREFIX =
		"lpartitiontest_";

	@Inject
	private ClassNameLocalService _classNameLocalService;

	@Inject
	private CompanyLocalService _companyLocalService;

	@Inject
	private CounterLocalService _counterLocalService;

}