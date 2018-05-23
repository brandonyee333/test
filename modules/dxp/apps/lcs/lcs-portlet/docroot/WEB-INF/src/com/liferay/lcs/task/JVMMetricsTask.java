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

import com.liferay.lcs.messaging.MetricsMessage;
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

		MetricsMessage metricsMessage = new MetricsMessage();

		metricsMessage.setCreateTime(System.currentTimeMillis());
		metricsMessage.setKey(getKey());
		metricsMessage.setMetricsType(MetricsMessage.METRICS_TYPE_JVM);
		metricsMessage.setPayload(getPayload());

		sendMessage(metricsMessage);
	}

	protected Map<String, Object> getBufferPoolMetrics() {
		Map<String, Object> bufferPoolMetrics = new HashMap<>();

		Map<String, BufferPoolStats> bufferPoolStatsMap =
			_virtualMachineMetrics.getBufferPoolStats();

		for (Map.Entry<String, BufferPoolStats> entry :
				bufferPoolStatsMap.entrySet()) {

			BufferPoolStats bufferPoolStats = entry.getValue();

			Map<String, Object> value = new HashMap<>();

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

	protected Map<String, Object> getGarbageCollectorMetrics() {
		Map<String, Object> garbageCollectorMetrics = new HashMap<>();

		Map<String, GarbageCollectorStats> garbageCollectorStatsMap =
			_virtualMachineMetrics.garbageCollectors();

		for (Map.Entry<String, GarbageCollectorStats> entry :
				garbageCollectorStatsMap.entrySet()) {

			GarbageCollectorStats garbageCollectorStats = entry.getValue();

			Map<String, Object> value = new HashMap<>();

			value.put("runs", garbageCollectorStats.getRuns());
			value.put(
				"time", garbageCollectorStats.getTime(TimeUnit.MILLISECONDS));

			garbageCollectorMetrics.put(entry.getKey(), value);
		}

		return garbageCollectorMetrics;
	}

	protected Map<String, Object> getPayload() {
		Map<String, Object> payload = new HashMap<>();

		payload.put("bufferPoolMetrics", getBufferPoolMetrics());
		payload.put(
			"daemonThreadCount", _virtualMachineMetrics.daemonThreadCount());
		payload.put(
			"deadlockedThreads",
			new HashSet<String>(_virtualMachineMetrics.deadlockedThreads()));
		payload.put("fileDescriptorUsage", getFileDescriptorUsage());
		payload.put("garbageCollectorMetrics", getGarbageCollectorMetrics());
		payload.put("heapCommitted", _virtualMachineMetrics.heapCommitted());
		payload.put("heapInit", _virtualMachineMetrics.heapInit());
		payload.put("heapMax", _virtualMachineMetrics.heapMax());
		payload.put("heapUsage", _virtualMachineMetrics.heapUsage());
		payload.put("heapUsed", _virtualMachineMetrics.heapUsed());
		payload.put(
			"memoryPoolUsage",
			new HashMap<String, Double>(
				_virtualMachineMetrics.memoryPoolUsage()));
		payload.put("name", _virtualMachineMetrics.name());
		payload.put("nonHeapUsage", _virtualMachineMetrics.nonHeapUsage());
		payload.put("threadCount", _virtualMachineMetrics.threadCount());
		payload.put(
			"threadStatePercentages",
			new HashMap<Thread.State, Double>(
				_virtualMachineMetrics.threadStatePercentages()));
		payload.put("totalCommitted", _virtualMachineMetrics.totalCommitted());
		payload.put("totalInit", _virtualMachineMetrics.totalInit());
		payload.put("totalMax", _virtualMachineMetrics.totalMax());
		payload.put("totalUsed", _virtualMachineMetrics.totalUsed());
		payload.put("uptime", _virtualMachineMetrics.uptime());
		payload.put("version", _virtualMachineMetrics.version());

		return payload;
	}

	private static final Log _log = LogFactoryUtil.getLog(JVMMetricsTask.class);

	private static final VirtualMachineMetrics _virtualMachineMetrics =
		VirtualMachineMetrics.getInstance();

}