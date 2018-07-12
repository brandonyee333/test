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

package com.liferay.lcs.task;

import com.liferay.lcs.messaging.JVMMetricsMessage;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.yammer.metrics.core.VirtualMachineMetrics;
import com.yammer.metrics.core.VirtualMachineMetrics.BufferPoolStats;
import com.yammer.metrics.core.VirtualMachineMetrics.GarbageCollectorStats;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class JVMMetricsTask extends BaseScheduledTask {

	@Override
	public Type getType() {
		return Type.LOCAL;
	}

	@Override
	protected void doRun() throws Exception {
		if (_log.isTraceEnabled()) {
			_log.trace("Running JVM metrics task");
		}

		JVMMetricsMessage jvmMetricsMessage = new JVMMetricsMessage();

		jvmMetricsMessage.setBufferPoolMetrics(getBufferPoolMetrics());
		jvmMetricsMessage.setCreateTime(System.currentTimeMillis());
		jvmMetricsMessage.setDaemonThreadCount(
			_virtualMachineMetrics.daemonThreadCount());
		jvmMetricsMessage.setDeadlockedThreads(
			new HashSet<>(_virtualMachineMetrics.deadlockedThreads()));
		jvmMetricsMessage.setFileDescriptorUsage(getFileDescriptorUsage());
		jvmMetricsMessage.setGarbageCollectorMetrics(
			getGarbageCollectorMetrics());
		jvmMetricsMessage.setHeapCommitted(
			_virtualMachineMetrics.heapCommitted());
		jvmMetricsMessage.setHeapInit(_virtualMachineMetrics.heapInit());
		jvmMetricsMessage.setHeapMax(_virtualMachineMetrics.heapMax());
		jvmMetricsMessage.setHeapUsage(_virtualMachineMetrics.heapUsage());
		jvmMetricsMessage.setHeapUsed(_virtualMachineMetrics.heapUsed());
		jvmMetricsMessage.setKey(getKey());
		jvmMetricsMessage.setMemoryPoolUsage(
			new HashMap<>(_virtualMachineMetrics.memoryPoolUsage()));
		jvmMetricsMessage.setName(_virtualMachineMetrics.name());
		jvmMetricsMessage.setNonHeapUsage(
			_virtualMachineMetrics.nonHeapUsage());
		jvmMetricsMessage.setThreadCount(_virtualMachineMetrics.threadCount());
		jvmMetricsMessage.setThreadStatePercentages(
			getThreadStatePercentages());
		jvmMetricsMessage.setTotalCommitted(
			_virtualMachineMetrics.totalCommitted());
		jvmMetricsMessage.setTotalInit(_virtualMachineMetrics.totalInit());
		jvmMetricsMessage.setTotalMax(_virtualMachineMetrics.totalMax());
		jvmMetricsMessage.setTotalUsed(_virtualMachineMetrics.totalUsed());
		jvmMetricsMessage.setUptime((int)_virtualMachineMetrics.uptime());
		jvmMetricsMessage.setVersion(_virtualMachineMetrics.version());

		sendMessage(jvmMetricsMessage);
	}

	protected Map<String, Map<String, Long>> getBufferPoolMetrics() {
		Map<String, Map<String, Long>> bufferPoolMetrics = new HashMap<>();

		Map<String, BufferPoolStats> bufferPoolStatsMap =
			_virtualMachineMetrics.getBufferPoolStats();

		for (Map.Entry<String, BufferPoolStats> entry :
				bufferPoolStatsMap.entrySet()) {

			BufferPoolStats bufferPoolStats = entry.getValue();

			Map<String, Long> value = new HashMap<>();

			value.put("count", bufferPoolStats.getCount());
			value.put("memoryUsed", bufferPoolStats.getMemoryUsed());
			value.put("totalCapacity", bufferPoolStats.getTotalCapacity());

			bufferPoolMetrics.put(entry.getKey(), value);
		}

		return bufferPoolMetrics;
	}

	protected Double getFileDescriptorUsage() {
		double fileDescriptorUsage =
			_virtualMachineMetrics.fileDescriptorUsage();

		if (Double.isNaN(fileDescriptorUsage)) {
			return null;
		}

		return fileDescriptorUsage;
	}

	protected Map<String, Map<String, Long>> getGarbageCollectorMetrics() {
		Map<String, Map<String, Long>> garbageCollectorMetrics =
			new HashMap<>();

		Map<String, GarbageCollectorStats> garbageCollectorStatsMap =
			_virtualMachineMetrics.garbageCollectors();

		for (Map.Entry<String, GarbageCollectorStats> entry :
				garbageCollectorStatsMap.entrySet()) {

			GarbageCollectorStats garbageCollectorStats = entry.getValue();

			Map<String, Long> value = new HashMap<>();

			value.put("runs", garbageCollectorStats.getRuns());
			value.put(
				"time", garbageCollectorStats.getTime(TimeUnit.MILLISECONDS));

			garbageCollectorMetrics.put(entry.getKey(), value);
		}

		return garbageCollectorMetrics;
	}

	protected Map<String, Double> getThreadStatePercentages() {
		Map<String, Double> threadStatePercentages = new HashMap<>();

		Map<Thread.State, Double> currentThreadStatePercentages =
			_virtualMachineMetrics.threadStatePercentages();

		for (Map.Entry<Thread.State, Double> entry :
				currentThreadStatePercentages.entrySet()) {

			Thread.State state = entry.getKey();

			threadStatePercentages.put(state.toString(), entry.getValue());
		}

		return threadStatePercentages;
	}

	private static final Log _log = LogFactoryUtil.getLog(JVMMetricsTask.class);

	private static final VirtualMachineMetrics _virtualMachineMetrics =
		VirtualMachineMetrics.getInstance();

}