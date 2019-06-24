package com.hyssop.framework.util;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 分表的工具类
 * @author duomn.hu
 *
 */
public class ShardUtils {

	private static Logger logger = LoggerFactory.getLogger(ShardUtils.class);

	/** 第一张分表的开始时间 */
	private static Date startDate;
	/** 第一张 分表的时间间隔 */
	private static Integer firstPeriod;
	/** 分表间隔，3个月 */
	private static Integer period;
	/** 分表的间隔的单位, 0-年, 1-月, 2-日 */
	private static Integer unit;

	/** 当前分表的下标 */
	private static int currIdx = 0;

	/** 从订单表号中，快速定位分表<年月,分表Idx> */
	private static Map<String, Integer> month2Shard = new HashMap<String, Integer>();
	/** 根据时间定位范围定位到分表范围 <分表Idx, 时间点-秒>*/
	private static Map<Integer, Integer> shard2Sec = new HashMap<Integer, Integer>();

	/** 当前月发生变化时，更新缓存数据 */
	private volatile static int currMonth = 0;

	// 初始化参数
	static {
		String startTimeStr = PropertyUtil.getProperty("orders.starttime", "2014-11-01");
		String firstPeriodStr = PropertyUtil.getProperty("orders.firstperiod", "2");
		String periodStr = PropertyUtil.getProperty("orders.period", "3");
		String unitStr = PropertyUtil.getProperty("orders.unit", "1");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = sdf.parse(startTimeStr);
		} catch (Exception e) {
			logger.error("orders.starttime configure parse error. orders.starttime[" + startTimeStr + "].", e);
		}
		try {
			firstPeriod = Integer.parseInt(firstPeriodStr);
		} catch (Exception e) {
			logger.error("orders.starttime configure parse error. orders.firstperiod[" + firstPeriodStr + "].", e);
		}
		try {
			period = Integer.parseInt(periodStr);
		} catch (Exception e) {
			logger.error("orders.starttime configure parse error. orders.period[" + periodStr + "].", e);
		}
		try {
			unit = Integer.parseInt(unitStr);
		} catch (Exception e) {
			logger.error("orders.starttime configure parse error. orders.starttime[" + unitStr + "].", e);
		}
		logger.info("the shard config  startDate[" +  startDate + "], firstPeriod[" + firstPeriod + "], period[" + period + "], unit[" + unit + "].");

		int month = DateTimeUtils.getMonth();
		calShardCache(month);
	}

	/** 计算时间  */
	private static void addCalendar(Calendar cal, int period, int unit) {
		switch (unit) {
			case 0: // 年
				cal.add(Calendar.YEAR, period);
				break;
			case 1: // 月
				cal.add(Calendar.MONTH, period);
				break;
			case 2: // 日
				cal.add(Calendar.DATE, period);
				break;
			default:
				break;
		}
	}

	/**
	 * 计算需要的分表的映射，缓存一份
	 */
	public synchronized static void calShardCache(int month) {
		if (currMonth == month) { // 其他线程已经计算过
			return;
		}

		Calendar cal = Calendar.getInstance();
		String currentTime = DateTimeUtils.formatDate(cal.getTime(), DateTimeUtils.PATTEN_yyyyMMddHHmmss);

		// 多创建一个分表
		int currSec = (int) (cal.getTimeInMillis() / 1000); // 当前时间的秒数
		addCalendar(cal, period, unit);
		int nextShardSec = (int) (cal.getTimeInMillis() / 1000);  // 下一个分表日期的秒数

		// 根据时间定位范围定位到分表范围 <分表Idx, 时间点-秒>
		Map<Integer, Integer> shard2SecTmp = new HashMap<Integer, Integer>();

		int idx = 0; // 分表下标从1开始

		// 放入初始时间, 下标为0, 2013-07-01
		shard2SecTmp.put(idx++, 1372608000);

		// 放入第一个分表时间，下标为1, 2014-10-01
		shard2SecTmp.put(idx++, (int) (startDate.getTime() / 1000));

		// 计算第二个分表时间, 下标为2, 2015-01-01
		cal.setTime(startDate);
		addCalendar(cal, firstPeriod, unit);

		int sec =  0;
		do { // 依次放入第二个以后的分表时间
			sec = (int) (cal.getTimeInMillis() / 1000);
			shard2SecTmp.put(idx++, sec);
			addCalendar(cal, period, unit);
		} while (sec <= nextShardSec); // 如果当前时间是 2015-01-01，nextShardSec 为 2015-04-01，shard2SecTmp 里面会放入到2015-07-01的秒数，以前没有等于条件，2015-07-01放入不进去
		// 当前时间是 2015-01-02 时，nextShardSec 为 2015-04-02，里面会放入到 2015-04-01

		// 计算currIdx
		for (int i = idx - 1; i >= 0; i--) {
			int shardSec = shard2SecTmp.get(i);
			if (currSec >= shardSec) { // currSec可能为 2015-01-01 或者会大于这个值   shardSec一定是2015-01-01 或者 2015-04-01，保证在整点时计算出的时间满足要求，没有等于条件，在2015-04-01导致整点计算不正确
				currIdx = i;
				break;
			}
		}

		shard2Sec = shard2SecTmp;

		cachedMonth2Shard();

		// debug 日志
		if (logger.isInfoEnabled()) {
			StringBuilder debugBuf = new StringBuilder();
			debugBuf.append("currentTime[").append(currentTime);
			debugBuf.append("], currIdx[").append(currIdx);
			debugBuf.append("], shard2Sec[");
			SimpleDateFormat debugSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (Entry<Integer, Integer> item : shard2Sec.entrySet()) {
				int showSec = item.getValue();
				debugBuf.append(item.getKey()).append("->").append(debugSdf.format(new Date(showSec * 1000L))).append(";");
			}

			debugBuf.append("], month2Shard[");
			for (Entry<String, Integer> item : month2Shard.entrySet()) {
				debugBuf.append(item.getKey()).append("->").append(item.getValue()).append(";");
			}
			debugBuf.append("]");
			logger.info(debugBuf.toString());
		}

		currMonth = month;
	}

	/** 根据现在的分表，计算一个<月, 分表>  */
	private static void cachedMonth2Shard() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyMM");

		Map<String, Integer> month2ShardTmp = new HashMap<String, Integer>();

		// 获取13年7月1号 到现在的映射，下标均为0
		Calendar cal = Calendar.getInstance();
		cal.set(2013, 07, 01);
		while (cal.getTimeInMillis() < startDate.getTime()) {
			String month = sdf.format(cal.getTime());
			month2ShardTmp.put(month, 0);
			cal.add(Calendar.MONTH, 1);
		}

		// 取第一个
		for (int i = 1; i < currIdx + 2; i++) {
			int curr = shard2Sec.get(i);
			int next = shard2Sec.get(i + 1);
			cal.setTimeInMillis(curr * 1000L);
			while (curr < next) {
				String key = sdf.format(cal.getTime());
				month2ShardTmp.put(key, i);
				cal.add(Calendar.MONTH, 1);
				curr = (int) (cal.getTimeInMillis() / 1000);
			}
		}

		month2Shard = month2ShardTmp;
	}

	/**
	 * 根据订单号获取分表
	 * @param orderNo
	 * @return 截取订单号中的时间部分来计算分表
	 */
	public static int getIdxByOrderNo(String orderNo) {
		String orderKey = null;
		switch (unit) {
			case 0: // 年 14

				break;
			case 1: // 月  1409
				orderKey = orderNo.substring(5, 9);
				break;
			case 2: // 日 140929
				orderKey = orderNo.substring(5, 11);
				break;
			default:
				orderKey = orderNo.substring(5, 9); // 默认按月来
				break;
		}
		if (month2Shard.containsKey(orderKey)) {
			return month2Shard.get(orderKey);
		} else {
			int sec = 0;
			try {
				sec = getOrderNoSec(orderKey, unit);
			} catch (ParseException e) {
				logger.error("parse the orderno date error.", e);
			}
			int idx = currIdx;
			int timestamp = shard2Sec.get(idx);
			while (timestamp > sec && idx > 0) {
				timestamp = shard2Sec.get(--idx);
			}
			return idx;
		}
	}

	/**
	 * 根据订单号返回分表名称
	 * @param orderNo
	 * @return
	 */
	public static String getShardByOrderNo(String orderNo) {
		int idx = getIdxByOrderNo(orderNo);
		return idx == 0 ? "orders" : "orders_" + idx;
	}
	/**
	 * 根据订单号返回分表名称
	 * @param orderNo
	 * @return
	 */
	public static String getShardExtByOrderNo(String orderNo) {
		int idx = getIdxByOrderNo(orderNo);
		return idx == 0 ? "orders_ext" : "orders_ext_" + idx;
	}

	/**
	 * 根据订单号返回分表名称
	 * @param orderNo
	 * @return
	 */
	public static String getShardByErrOrderNo(String orderNo) {
		int idx = getIdxByOrderNo(orderNo);
		return idx == 0 ? "fail_orders" : "fail_orders_" + idx;
	}

	/**
	 * 根据根据订单分表获取扩展分表
	 * @return
	 */
	public static List<String> getShardExtTableByOrderTableList(List<String> orderTableList) {
		List<String> orderExtTableList = new ArrayList<String>();

		for(String orderTable : orderTableList) {
			String orderExtTable = null;
			if(orderTable.indexOf("_") > 0) {
				String sequene = orderTable.substring(orderTable.lastIndexOf("_") + 1, orderTable.length());
				if(StringUtils.isNotBlank(sequene)) {
					int idx = Integer.parseInt(sequene);
					orderExtTable = "orders_ext_" + idx;

				}
			} else {
				orderExtTable = "orders_ext";
			}
			orderExtTableList.add(orderExtTable);
		}
		return orderExtTableList;
	}

	/**
	 * 根据根据订单分表获取扩展分表
	 *
	 * @return
	 */
	public static String getShardExtTableByOrderTable(String orderTable) {
		String orderExtTable = null;
		if(orderTable.indexOf("_") > 0) {
			String sequene = orderTable.substring(orderTable.lastIndexOf("_") + 1, orderTable.length());
			if(StringUtils.isNotBlank(sequene)) {
				int idx = Integer.parseInt(sequene);
				orderExtTable = "orders_ext_" + idx;

			}
		} else {
			orderExtTable = "orders_ext";
		}
		return orderExtTable;
	}

	/**
	 * 获取当前订单号对应的分表的前一张分表
	 * @param orderNo
	 * @return
	 */
	public static String getPreShardByOrderNo(String orderNo) {
		int idx = getIdxByOrderNo(orderNo) - 1;
		idx = idx < 0 ? 0 : idx;
		return idx == 0 ? "orders" : "orders_" + idx;
	}

	/**
	 * 获取当前订单号对应的分表的后一张分表
	 * @param orderNo
	 * @return
	 */
	public static String getNextShardByOrderNo(String orderNo) {
		triggerCalc();
		int idx = getIdxByOrderNo(orderNo) + 1;
		idx = idx > currIdx ? currIdx : idx;
		return idx == 0 ? "orders" : "orders_" + idx;
	}

	/**
	 * 根据多个订单号获取适应的分表
	 * @param orderNos
	 * @return
	 */
	public static List<String> getShardByOrderNos(Collection<? extends String> orderNos) {
		triggerCalc();
		Set<Integer> idxSet = new HashSet<Integer>();
		for (String orderNo : orderNos) {
			idxSet.add(getIdxByOrderNo(orderNo));
		}

		List<Integer> sortedIdxs = sortIdx(idxSet);
		return convertIdx2TabName(sortedIdxs);
	}

	/** 获取订单表中的秒数 
	 * @throws ParseException */
	private static int getOrderNoSec(String dateStr, int unit) throws ParseException {
		switch (unit) {
			case 0: // 年
				SimpleDateFormat yearSdf = new SimpleDateFormat("yy");
				return (int) (yearSdf.parse(dateStr).getTime() / 1000);
			case 1: // 月
				SimpleDateFormat monthSdf = new SimpleDateFormat("yyMM");
				return (int) (monthSdf.parse(dateStr).getTime() / 1000);
			case 2: // 日
				SimpleDateFormat daySdf = new SimpleDateFormat("yyMMdd");
				return (int) (daySdf.parse(dateStr).getTime() / 1000);
			default:
				SimpleDateFormat monthSdfDef = new SimpleDateFormat("yyMM");
				return (int) (monthSdfDef.parse(dateStr).getTime() / 1000);
		}
	}

	/**
	 * 触发是否需要计算缓存中的分表
	 */
	private static void triggerCalc() {
		int month = DateTimeUtils.getMonth();
		if (currMonth != month) {
			try {
				calShardCache(month);
			} catch (Exception e) {
				logger.error("update the cache date error.", e);
			}
		}
	}

	/**
	 * 根据创建时间范围获取符合的多个分表
	 * @param minTs 时间范围的下限
	 * @param maxTs 时间范围的上限
	 * @return 返回的多个分表，顺序由前到后
	 */
	public static List<String> getShardByCreatedTsRange(Integer minTs, Integer maxTs) {
		return getShardTableByCreatedTsAndTableName("orders",minTs,maxTs);
	}

	/**
	 * 根据创建时间范围获取符合条件的多个分表
	 * @param tableName 表名
	 * @param minTs 时间范围的下限
	 * @param maxTs 时间范围的上限
	 * @return 返回的多个分表，顺序由前到后
	 */
	private static List<String> getShardTableByCreatedTsAndTableName(String tableName,Integer minTs,Integer maxTs){
		// 触发频率相对较低，此处触发更新缓存
		triggerCalc();

		int minIdx = 0, maxIdx = currIdx;
		if (minTs != null) { // 从0开始找下边界
			int timestamp = shard2Sec.get(minIdx + 1);
			while (timestamp < minTs && minIdx < currIdx) {
				timestamp = shard2Sec.get(++minIdx);
			}
		}

		// add by liupeng for bugfix:如果映射表存放的是20130701,20141001,20150101,20150401的时候，而搜索的下限为20141002的时候
		// 搜索停止的时候minIdx = 2，这个时候会出现搜索不到分表的情况，所以需要将minIdx - 1才能满足需求
		if (minIdx > 0) {
			minIdx--;
		}

		if (maxTs != null) { // 从当前往前找下边界
			int timestamp = shard2Sec.get(maxIdx);
			while (timestamp > maxTs && maxIdx > 0) {
				timestamp = shard2Sec.get(--maxIdx);
			}
		}

		List<String> tabs = new ArrayList<String>();
		for (int i = minIdx; i <= maxIdx; i++) {
			if (i == 0) {
				tabs.add(tableName);
			} else {
				tabs.add(tableName + "_" + i);
			}
		}
		return tabs;
	}
	/**
	 * 在分表区间内，对下标进行排序
	 * @param idxs
	 * @return
	 */
	private static List<Integer> sortIdx(Collection<? extends Integer> idxs) {
		boolean[] bitMap = new boolean[currIdx + 1];
		for (int i = 0; i <= currIdx; i++) {
			bitMap[i] = false;
		}
		for (int idx : idxs) {
			bitMap[idx] = true;
		}
		List<Integer> newIdxs = new ArrayList<Integer>(idxs.size());
		for (int i = 0; i <= currIdx; i++) {
			if (bitMap[i]) {
				newIdxs.add(i);
			}
		}
		return newIdxs;
	}

	/**
	 * 把查询到的下标转换成实际的分表
	 * @param idxs
	 * @return
	 */
	private static List<String> convertIdx2TabName(Collection<? extends Integer> idxs) {
		List<String> tabs = new ArrayList<String>();
		for (Integer idx : idxs) {
			tabs.add(idx == 0 ? "orders" : "orders_" + idx);
		}
		return tabs;
	}

	/**
	 * 根据创建时间范围获取符合的多个分表: 无下限
	 * @param maxTs 时间范围的上限
	 * @return
	 */
	public static List<String> getShardByCreatedTsNoMinTs(Integer maxTs) {
		triggerCalc();
		int maxIdx = currIdx;
		if (maxTs != null) { // 从当前往前找上边界
			int timestamp = shard2Sec.get(maxIdx);
			while (timestamp > maxTs && maxIdx > 0) {
				timestamp = shard2Sec.get(--maxIdx);
			}
		}

		List<String> tabs = new ArrayList<String>(maxIdx + 1);
		for (int i = 0; i <= maxIdx; i++) {
			tabs.add(i == 0 ? "orders" : "orders_" + i);
		}

		return tabs;
	}

	/**
	 * 获取订单表的所有分表
	 * @return 订单表的所有分表
	 */
	public static List<String> getAllShardTableName() {
		triggerCalc();
		List<String> tabs = new ArrayList<String>(currIdx + 1);
		for (int i = 0; i <= currIdx; i++) {
			tabs.add(i == 0 ? "orders" : "orders_" + i);
		}
		return tabs;
	}

	/**
	 * 根据时间范围获取符合的多个orders_ext分表: 无下限
	 * @param maxTs 时间范围的上限
	 * @return
	 */
	public static List<String> getOrderExtShardByCreatedTsNoMinTs(Integer maxTs) {
		triggerCalc();
		int maxIdx = currIdx;
		if (maxTs != null) { // 从当前往前找上边界
			int timestamp = shard2Sec.get(maxIdx);
			while (timestamp > maxTs && maxIdx > 0) {
				timestamp = shard2Sec.get(--maxIdx);
			}
		}

		List<String> tabs = new ArrayList<String>(maxIdx + 1);
		for (int i = 0; i <= maxIdx; i++) {
			tabs.add(i == 0 ? "orders_ext" : "orders_ext_" + i);
		}

		return tabs;
	}

	/**
	 * 根据时间范围获取符合的多个orders_ext分表:
	 * @param minTs 时间范围的下限
	 * @param maxTs 时间范围的上限
	 * @return 分表名称集合
	 */
	public static List<String> getOrderExtShardByCreatedTsRange(Integer minTs, Integer maxTs){
		return getShardTableByCreatedTsAndTableName("orders_ext",minTs,maxTs);
	}

	/**
	 * 得到当前分表的下标
	 * @return
	 */
	public static int getCurrIdx() {
		return currIdx;
	}

	public static void main(String[] args) {
		ShardUtils.getAllShardTableName();
	}
}
