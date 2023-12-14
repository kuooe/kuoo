package kr.kuooe.comm.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Display;
import oshi.hardware.Firmware;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.PowerSource;
import oshi.hardware.Sensors;
import oshi.hardware.UsbDevice;
import oshi.software.os.FileSystem;
import oshi.software.os.NetworkParams;
import oshi.software.os.OSFileStore;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSort;
import oshi.util.FormatUtil;
import oshi.util.Util;

@Slf4j
@Component
public class SystemUtil {

	private static SystemInfo si;
	private static HardwareAbstractionLayer hal;
	private static OperatingSystem os;
	
	
	public SystemUtil() {
		log.debug("documoa SystemUtil =====> Start");
		si	= new SystemInfo();
		hal	= si.getHardware();
		os	= si.getOperatingSystem();
		log.debug("documoa SystemUtil =====> End");
	}
	public static void getComputerSystem() {
		log.debug("SystemUtil - getComputerSystem() =====> Start");
		final ComputerSystem computerSystem = hal.getComputerSystem();
		log.debug("getComputerSystem() =====> manufacturer: " + computerSystem.getManufacturer());
		log.debug("getComputerSystem() =====> model: " + computerSystem.getModel());
		log.debug("getComputerSystem() =====> serialnumber: " + computerSystem.getSerialNumber());
		
		final Firmware firmware = computerSystem.getFirmware();
		log.debug("getComputerSystem() =====> firmware:");
		log.debug("getComputerSystem() =====>   manufacturer: " + firmware.getManufacturer());
		log.debug("getComputerSystem() =====>   name: " + firmware.getName());
		log.debug("getComputerSystem() =====>   description: " + firmware.getDescription());
		log.debug("getComputerSystem() =====>   version: " + firmware.getVersion());
		
		final Baseboard baseboard = computerSystem.getBaseboard();
		log.debug("getComputerSystem() =====> baseboard:");
		log.debug("getComputerSystem() =====>   manufacturer: " + baseboard.getManufacturer());
		log.debug("getComputerSystem() =====>   model: " + baseboard.getModel());
		log.debug("getComputerSystem() =====>   version: " + baseboard.getVersion());
		log.debug("getComputerSystem() =====>   serialnumber: " + baseboard.getSerialNumber());
	}
	public static void getProcessor() {
		CentralProcessor processor = hal.getProcessor();
		log.debug("getProcessor() =====> "+ processor);
		log.debug("getProcessor() =====>  " + processor.getPhysicalPackageCount() + " physical CPU package(s)");
		log.debug("getProcessor() =====>  " + processor.getPhysicalProcessorCount() + " physical CPU core(s)");
		log.debug("getProcessor() =====>  " + processor.getLogicalProcessorCount() + " logical CPU(s)");
	}
	public static void getProcesses() {
		GlobalMemory memory = hal.getMemory();
		log.debug("Processes: " + os.getProcessCount() + ", Threads: " + os.getThreadCount());
		
		// Sort by highest CPU
		List<OSProcess> procs = Arrays.asList(os.getProcesses(5, ProcessSort.CPU));

		log.debug("   PID  %CPU %MEM       VSZ       RSS Name");
		for (int i = 0; i < procs.size() && i < 5; i++) {
			OSProcess p = procs.get(i);
			System.out.format(" %5d %5.1f %4.1f %9s %9s %s%n", p.getProcessID(),
					100d * (p.getKernelTime() + p.getUserTime()) / p.getUpTime(),
					100d * p.getResidentSetSize() / memory.getTotal(), FormatUtil.formatBytes(p.getVirtualSize()),
					FormatUtil.formatBytes(p.getResidentSetSize()), p.getName());
		}
	}
	public static void getSensors() {
		Sensors sensors = hal.getSensors();
		
		log.debug("Sensors:");
		System.out.format(" CPU Temperature: %.1f��C%n", sensors.getCpuTemperature());
		log.debug(" CPU Temperature : "+ sensors.getCpuTemperature());
		log.debug(" CPU Voltage : "+ sensors.getCpuVoltage());
		//log.debug(" CPU Temperature : "+ String.format("%.1f��C%", sensors.getCpuTemperature()));
		//log.debug(" Fan Speeds: " + Arrays.toString(sensors.getFanSpeeds()));
		System.out.format(" CPU Voltage: %.1fV%n", sensors.getCpuVoltage());
	}
	public static void getPowerSources() {
		PowerSource[] powerSources = hal.getPowerSources();
		
		StringBuilder sb = new StringBuilder("Power: ");
		if (powerSources.length == 0) {
			sb.append("Unknown");
		} else {
			double timeRemaining = powerSources[0].getTimeRemaining();
			if (timeRemaining < -1d) {
				sb.append("Charging");
			} else if (timeRemaining < 0d) {
				sb.append("Calculating time remaining");
			} else {
				sb.append(String.format("%d:%02d remaining", (int) (timeRemaining / 3600),
						(int) (timeRemaining / 60) % 60));
			}
		}
		for (PowerSource pSource : powerSources) {
			sb.append(String.format("%n %s @ %.1f%%", pSource.getName(), pSource.getRemainingCapacity() * 100d));
		}
		log.debug(sb.toString());
	}
	public static void getDisks() {
		log.debug("getDisks() =====> Start");
		
		HWDiskStore[] diskStores = hal.getDiskStores();
		
		log.debug("Disks:");
		for (HWDiskStore disk : diskStores) {
			boolean readwrite = disk.getReads() > 0 || disk.getWrites() > 0;
			System.out.format(" %s: (model: %s - S/N: %s) size: %s, reads: %s (%s), writes: %s (%s), xfer: %s ms%n",
					disk.getName(), disk.getModel(), disk.getSerial(),
					disk.getSize() > 0 ? FormatUtil.formatBytesDecimal(disk.getSize()) : "?",
					readwrite ? disk.getReads() : "?", readwrite ? FormatUtil.formatBytes(disk.getReadBytes()) : "?",
					readwrite ? disk.getWrites() : "?", readwrite ? FormatUtil.formatBytes(disk.getWriteBytes()) : "?",
					readwrite ? disk.getTransferTime() : "?");
			HWPartition[] partitions = disk.getPartitions();
			if (partitions == null) {
				continue;
			}
			for (HWPartition part : partitions) {
				System.out.format(" |-- %s: %s (%s) Maj:Min=%d:%d, size: %s%s%n", part.getIdentification(),
						part.getName(), part.getType(), part.getMajor(), part.getMinor(),
						FormatUtil.formatBytesDecimal(part.getSize()),
						part.getMountPoint().isEmpty() ? "" : " @ " + part.getMountPoint());
			}
		}
	}
	public static void getNetworkInterfaces() {
		NetworkIF[] networkIFs = hal.getNetworkIFs();
		
		log.debug("Network interfaces:");
		for (NetworkIF net : networkIFs) {
			System.out.format(" Name: %s (%s)%n", net.getName(), net.getDisplayName());
			System.out.format("   MAC Address: %s %n", net.getMacaddr());
			System.out.format("   MTU: %s, Speed: %s %n", net.getMTU(), FormatUtil.formatValue(net.getSpeed(), "bps"));
			System.out.format("   IPv4: %s %n", Arrays.toString(net.getIPv4addr()));
			System.out.format("   IPv6: %s %n", Arrays.toString(net.getIPv6addr()));
			boolean hasData = net.getBytesRecv() > 0 || net.getBytesSent() > 0 || net.getPacketsRecv() > 0
					|| net.getPacketsSent() > 0;
			System.out.format("   Traffic: received %s/%s%s; transmitted %s/%s%s %n",
					hasData ? net.getPacketsRecv() + " packets" : "?",
					hasData ? FormatUtil.formatBytes(net.getBytesRecv()) : "?",
					hasData ? " (" + net.getInErrors() + " err)" : "",
					hasData ? net.getPacketsSent() + " packets" : "?",
					hasData ? FormatUtil.formatBytes(net.getBytesSent()) : "?",
					hasData ? " (" + net.getOutErrors() + " err)" : "");
		}
	}
	public static void getNetworkParameters() {
		NetworkParams networkParams = os.getNetworkParams();
		
		log.debug("Network parameters:");
		log.debug(" Host name : "+ networkParams.getHostName());
		log.debug(" Domain name : "+ networkParams.getDomainName());
		log.debug(" DNS servers : "+ Arrays.toString(networkParams.getDnsServers()));
		log.debug(" IPv4 Gateway : "+ networkParams.getIpv4DefaultGateway());
		log.debug(" IPv6 Gateway : "+ networkParams.getIpv6DefaultGateway());
	}
	public static void getDisplays() {
		Display[] displays = hal.getDisplays();
		
		log.debug("Displays:");
		int i = 0;
		for (Display display : displays) {
			log.debug(" Display " + i + ":");
			log.debug(display.toString());
			i++;
		}
	}
	public static void getUsbDevices() {
		UsbDevice[] usbDevices = hal.getUsbDevices(true);
		
		log.debug("USB Devices:");
		for (UsbDevice usbDevice : usbDevices) {
			log.debug(usbDevice.toString());
		}
	}
	
	
	public static Map<String, Object> getCpu() {
		log.debug("getCpu() =====> Start");
		
		JSONObject rtnJson			= new JSONObject();
		Map<String, Object> rtnMap	= new HashMap<String, Object>();
		
		CentralProcessor processor = hal.getProcessor();

		long[] prevTicks = processor.getSystemCpuLoadTicks();
		
		// Wait a second...
		Util.sleep(1000);
		long[] ticks = processor.getSystemCpuLoadTicks();
		
		long user		= ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];		// 사용자 수준(응용 프로그램)에서 실행하는 동안 발생한 CPU 사용률
		long nice		= ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];		// 좋은 우선 순위로 사용자 수준에서 실행하는 동안 발생한 CPU 사용률
		long sys		= ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];	// 시스템 수준(커널)에서 실행하는 동안 발생한 CPU 사용률
		long idle		= ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];		// CPU가 유휴 상태이고 시스템에 미결 디스크 I/O 요청이 없는 시간
		long iowait		= ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];	// 시스템에 미결 디스크 I/O 요청이 있는 동안 CPU가 유휴 상태였던 시간
		long irq		= ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];			// CPU가 하드웨어 IRQ를 서비스하는 데 사용한 시간
		long softirq	= ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];	// CPU가 소프트 IRQ를 서비스하는 데 사용한 시간
		long steal		= ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];		// 하이퍼바이저가 시스템의 다른 게스트 전용 시간
		long totalCpu	= user + nice + sys + idle + iowait + irq + softirq + steal;					// CPU사용량 합계

		//double[] loadAverage = processor.getSystemLoadAverage(3);
		// per core CPU
		StringBuilder procCpu = new StringBuilder("CPU load per processor:");
		double[] load = processor.getProcessorCpuLoadBetweenTicks();
		int num = 0;
		for (double avg : load) {
			procCpu.append(String.format(" %.1f%%", avg * 100));
			rtnJson.put("cpuProcess"+ num,	avg);
			num ++;
		}
		
		rtnJson.put("cpuUser",		user);
		rtnJson.put("cpuNice",		nice);
		rtnJson.put("cpuSys",		sys);
		rtnJson.put("cpuIdle",		idle);
		rtnJson.put("cpuIowait",	iowait);
		rtnJson.put("cpuIrq",		irq);
		rtnJson.put("cpuSoftirq",	softirq);
		rtnJson.put("cpuSteal",		steal);
		rtnJson.put("cpuTotal",		totalCpu);
		
		rtnMap.put("cpuUser",		user);
		rtnMap.put("cpuNice",		nice);
		rtnMap.put("cpuSys",		sys);
		rtnMap.put("cpuIdle",		idle);
		rtnMap.put("cpuIowait",		iowait);
		rtnMap.put("cpuIrq",		irq);
		rtnMap.put("cpuSoftirq",	softirq);
		rtnMap.put("cpuSteal",		steal);
		rtnMap.put("cpuTotal",		totalCpu);
		
		return rtnMap;
	}
	public static Map<String, Object> getMemory() {
		log.debug("getMemory() =====> Start");
		
		GlobalMemory memory = hal.getMemory();
		//Map<String, Object> rtnData = new HashMap<String, Object>();;
		//log.debug("getMemory() =====> Memory: " + memory.getAvailable() +"/"+ memory.getTotal());
		//log.debug("getMemory() =====> Memory: " + FormatUtil.formatBytes(memory.getAvailable()) +"/"+ FormatUtil.formatBytes(memory.getTotal()));
		//log.debug("getMemory() =====> Swap used: " + memory.getSwapUsed() +"/"+ memory.getSwapTotal());
		//log.debug("getMemory() =====> Swap used: " + FormatUtil.formatBytes(memory.getSwapUsed()) +"/"+ FormatUtil.formatBytes(memory.getSwapTotal()));
		
		//JSONObject rtnJson = new JSONObject();
		Map<String, Object> rtnMap	= new HashMap<String, Object>();
		
		long totalMemory	= memory.getTotal();		// 총 메모리
		long freeMemory		= memory.getAvailable();	// 사용가능한 메모리
		long usageMemory	= totalMemory - freeMemory; // 사용즁인 메모리\
		
		// 실제 물리적 메모리의 양(바이트)
		rtnMap.put("totalMemory",		totalMemory );
		rtnMap.put("totalMemotyFormat",	FormatUtil.formatBytes(totalMemory));
		// 현재 사용가능한 물리적 메모리의 양
		rtnMap.put("freeMemory",		freeMemory );
		rtnMap.put("freeMemoryFormat",	FormatUtil.formatBytes(freeMemory));
		// 현재 사용중인 물리적 메모리의 양
		rtnMap.put("usageMemory",		usageMemory );
		rtnMap.put("usageMemoryFormat",	FormatUtil.formatBytes( usageMemory ));
		
		long totalSwap	= memory.getSwapTotal();	// 총 Swap메모리
		long usageSwap	= memory.getSwapUsed();		// 사용중인 Swap메모리
		long freeSwap	= totalSwap - usageSwap;	// 사용가능한 Swap메모리
		
		// 페이징/스왑 파일의 현재 크기(바이트)
		rtnMap.put("totalSwap",			totalSwap );
		rtnMap.put("totalSwapFormat",	FormatUtil.formatBytes( totalSwap ));
		// 페이징/스왑 파일에 커밋된 현재 메모리(바이트)
		rtnMap.put("usageSwap",			usageSwap );
		rtnMap.put("usageSwapFormat",	FormatUtil.formatBytes( usageSwap ));
		// 페이징/스왑 파일에 사용가능한 현재 메모리(바이트)
		rtnMap.put("freeSwap",			freeSwap );
		rtnMap.put("freeSwapFormat",	FormatUtil.formatBytes( freeSwap ));
		
		return rtnMap;
	}
	public static Map<String, Object> getFileSystem() {
		log.debug("getFileSystem() =====> Start");
		
		Map<String, Object> rtnMap	= new HashMap<String, Object>();
		FileSystem fileSystem = os.getFileSystem();
		
		ArrayList<Object> ListObj = new ArrayList<Object>();
		
		OSFileStore[] fsArray = fileSystem.getFileStores();
		for (OSFileStore fs : fsArray) {
			long usable	= fs.getUsableSpace();
			long total	= fs.getTotalSpace();
			
			String mount = "";
			mount = String.format("%s", fs.getMount());
			log.debug("getFileSystem() ===== > mount : "+ mount);
			
			Map<String, Object> diskData	= new HashMap<String, Object>();
			diskData.put("diskName",	fs.getName());
			diskData.put("diskDesc",	fs.getDescription().isEmpty() ? "file system" : fs.getDescription());
			diskData.put("diskType",	fs.getType());
			diskData.put("diskFree",	usable);
			diskData.put("diskTotal",	total);
			diskData.put("diskMount",	mount);
			//log.debug("getFileSystem() ===== > diskData : "+ diskData.toString());
			
			ListObj.add(diskData);
		}
		rtnMap.put("diskData", ListObj);
		
		log.debug("getFileSystem() ===== > rtnData : "+ rtnMap.toString());
		return rtnMap;
	}
}
