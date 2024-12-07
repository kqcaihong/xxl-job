package com.xxl.job.executor.sample.frameless.jobhandler;

import com.xxl.job.core.handler.IJobHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheRefreshJob extends IJobHandler {

  // local cache
  public static volatile Map<Integer, Object> CACHE;

  @Override
  public void init() throws Exception {
    CACHE = new HashMap<>();
  }

  @Override
  public void execute() throws Exception {
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

  public Object query(Integer key) {
    return CACHE.get(key);
  }

  @Override
  public void destroy() throws Exception {
    CACHE.clear();
  }
}
