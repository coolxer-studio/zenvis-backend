# AggregateController 数据聚合接口文档

**基础信息**
- **模块名称**: 数据聚合
- **基础路径**: `/api/v1/retrieval/aggregate`
- **作者**: coolxer
- **协议**: HTTP/HTTPS
- **数据格式**: JSON

---

## 📋 数据模型定义

### 1. BaseQueryDto (基础查询传输对象)

```json
{
  "page": 1,                  // Integer - 当前页码
  "size": 10,                 // Integer - 每页条数
  "startTime": "2024-01-01 00:00:00", // String - 开始时间
  "endTime": "2024-01-31 23:59:59"    // String - 结束时间
}
```

### 2. AggregateMsgInfoVo (聚合消息信息视图对象)

```json
{
  "tags": [],                 // List - 标签列表
  "info": {}                  // Object - 消息信息
}
```

### 3. StackedLineChartVo (堆叠折线图视图对象)

```json
{
  "categories": [],           // List - 类别列表
  "series": [],               // List - 系列数据
  "values": {}                // Object - 数值数据
}
```

### 4. DataMsgVo (数据消息视图对象)

```json
{
  "startId": 1,               // BigDecimal - 起始ID
  "appName": "App",           // String - 应用名称
  "appVersion": "1.0",        // String - 应用版本
  "platform": "Android",      // String - 平台
  "lanIp": "192.168.1.1",     // String - 局域网IP
  "wanIp": "202.100.1.1",     // String - 广域网IP
  "factType": "type",         // String - 事实类型
  "agendaTags": "tag1,tag2",  // String - 议程标签
  "punishTypes": "type1",     // String - 处罚类型
  "location": "北京市",        // String - 位置信息
  "clientTime": "2024-01-01 12:00:00", // String - 客户端时间
  "serverTime": "2024-01-01 12:00:00", // String - 服务端时间
  "data": "{}"                // String - 数据内容
}
```

### 5. PageView\<T\> (分页视图对象)

```json
{
  "page": 1,                  // Integer - 当前页码
  "size": 10,                 // Integer - 每页条数
  "total": 100,               // Long - 总记录数
  "items": []                 // List\<T\> - 数据列表
}
```

### 6. ResponseWrap (统一响应格式)

```json
{
  "status": 0,                // Integer - 响应码(0:成功，其他:失败)
  "msg": "success",           // String - 响应消息
  "data": {}                  // Object - 响应数据
}
```

---

## 📊 接口总览

| 序号 | HTTP方法 | 接口路径 | 接口名称 | 功能描述 |
|:---:|:-------:|---------|---------|---------|
| 1 | GET | `/api/v1/retrieval/aggregate/msg/tag` | msg基础数据标签 | 获取msg基础数据标签信息 |
| 2 | GET | `/api/v1/retrieval/aggregate/msg/trend` | 数据分布趋势 | 获取数据分布趋势 |
| 3 | POST | `/api/v1/retrieval/aggregate/detail/{id}` | 数据链列表 | 获取数据链详情列表 |

---

## 🔌 接口详情

### 1️⃣ msg基础数据标签

**接口地址**: `GET /api/v1/retrieval/aggregate/msg/tag`

**功能描述**: 获取msg基础数据标签信息

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| params | Map\<String, String\> | 是 | 查询参数（至少包含一个参数） |

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/retrieval/aggregate/msg/tag?device_id=device-001"
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "tags": [],
    "info": {}
  }
}
```

**失败响应**:
```json
{
  "status": -1,
  "msg": "缺少必需参数",
  "data": null
}
```

---

### 2️⃣ 数据分布趋势

**接口地址**: `GET /api/v1/retrieval/aggregate/msg/trend`

**功能描述**: 获取数据分布趋势

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| params | Map\<String, String\> | 是 | 查询参数（至少包含一个参数） |

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/retrieval/aggregate/msg/trend?start_time=2024-01-01&end_time=2024-01-31"
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "categories": ["2024-01-01", "2024-01-02"],
    "series": ["series1"],
    "values": {}
  }
}
```

**失败响应**:
```json
{
  "status": -1,
  "msg": "缺少必需参数",
  "data": null
}
```

---

### 3️⃣ 数据链列表

**接口地址**: `POST /api/v1/retrieval/aggregate/detail/{id}`

**功能描述**: 获取数据链详情列表

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | String | 是 | 设备ID或数据链ID |

**请求参数**:
- Content-Type: `application/json`
- Body: BaseQueryDto

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/retrieval/aggregate/detail/device-001 \
  -H "Content-Type: application/json" \
  -d '{
    "page": 1,
    "size": 10,
    "startTime": "2024-01-01 00:00:00",
    "endTime": "2024-01-31 23:59:59"
  }'
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "page": 1,
    "size": 10,
    "total": 100,
    "items": []
  }
}
```

---

## 📊 响应码汇总

| 响应码 | 说明 | 触发场景 |
|--------|------|---------|
| 0 | 请求成功 | 操作成功完成 |
| -1 | 未知错误 | 遇到未定义的异常情况 |

---

## 🔐 注意事项

1. **认证授权**: 需要登录认证
2. **参数验证**: msg/tag和msg/trend接口必须提供至少一个查询参数
3. **时间格式**: startTime和endTime格式为`yyyy-MM-dd HH:mm:ss`