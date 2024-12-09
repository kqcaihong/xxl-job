package com.xxl.job.executor.sample.frameless.jobhandler;

import com.xxl.job.core.handler.annotation.XxlJob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheRefreshJob2 {

  // local cache
  public static volatile Map<Integer, Object> CACHE = new HashMap<>();

  public void init() throws Exception {
    refreshCache();
  }

  @XxlJob(value = "cacheRefreshJob", init = "init", destroy = "destroy")
  public void refreshCache() throws Exception {
    List<Object> dataList = loadData();
    Map<Integer, Object> temporaryMap = new HashMap<>();
    for (Object data : dataList) {
      temporaryMap.put(data.hashCode(), data);
    }
    CACHE = temporaryMap;
  }

  private List<Object> loadData() {
    // 模拟查询数据源
    return new ArrayList<>();
  }

  public void destroy() throws Exception {
    CACHE.clear();
  }

  public Object query(Integer key) {
    return CACHE.get(key);
  }
}
