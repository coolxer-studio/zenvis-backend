# EntityCountController 实体统计接口文档

**基础信息**
- **模块名称**: 实体统计
- **基础路径**: `/api/v1/entity`
- **作者**: coolxer
- **协议**: HTTP/HTTPS
- **数据格式**: JSON

---

## 📋 数据模型定义

### 1. ResponseWrap (统一响应格式)

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
| 1 | GET | `/api/v1/entity/count` | 实体数量统计 | 获取多个实体的数量统计 |
| 2 | GET | `/api/v1/entity/trend` | 实体趋势统计 | 获取多个实体的趋势数据 |
| 3 | GET | `/api/v1/entity/statistics` | 实体字段统计 | 获取实体指定字段的统计信息 |

---

## 🔌 接口详情

### 1️⃣ 实体数量统计

**接口地址**: `GET /api/v1/entity/count`

**功能描述**: 获取多个实体的数量统计

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entities | List\<String\> | 是 | 实体名称列表（逗号分隔） |

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/entity/count?entities=asset_host,asset_pc,asset_mobile"
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "asset_host": 100,
    "asset_pc": 200,
    "asset_mobile": 150
  }
}
```

---

### 2️⃣ 实体趋势统计

**接口地址**: `GET /api/v1/entity/trend`

**功能描述**: 获取多个实体的趋势数据

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entities | List\<String\> | 是 | 实体名称列表（逗号分隔） |

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/entity/trend?entities=asset_host,asset_pc"
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "asset_host": {
      "dates": ["2024-01-01", "2024-01-02"],
      "counts": [100, 105]
    },
    "asset_pc": {
      "dates": ["2024-01-01", "2024-01-02"],
      "counts": [200, 210]
    }
  }
}
```

---

### 3️⃣ 实体字段统计

**接口地址**: `GET /api/v1/entity/statistics`

**功能描述**: 获取实体指定字段的统计信息

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entities | List\<String\> | 是 | 实体名称列表（逗号分隔） |
| field | String | 是 | 统计字段名称 |

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/entity/statistics?entities=asset_host&field=status"
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "asset_host": {
      "active": 80,
      "inactive": 20
    }
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
2. **多实体支持**: 支持同时统计多个实体的数据