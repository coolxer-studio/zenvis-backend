# EntityCoreController 实体通用接口文档

**基础信息**
- **模块名称**: 实体通用管理
- **基础路径**: `/api/v1/entity/{entity}`
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

### 2. PageRowsVo (分页数据结构)

```json
{
  "page": 1,                  // Integer - 当前页码
  "size": 10,                 // Integer - 每页条数
  "total": 100,               // Long - 总记录数
  "rows": []                  // List - 数据列表
}
```

---

## 📊 接口总览

| 序号 | HTTP方法 | 接口路径 | 接口名称 | 功能描述 |
|:---:|:-------:|---------|---------|---------|
| 1 | POST | `/api/v1/entity/{entity}/add` | 添加实体 | 添加新的实体记录 |
| 2 | DELETE | `/api/v1/entity/{entity}/{id}` | 删除实体 | 根据ID删除单个实体 |
| 3 | DELETE | `/api/v1/entity/{entity}/bulk/{ids}` | 批量删除实体 | 批量删除多个实体 |
| 4 | POST | `/api/v1/entity/{entity}/{id}/update` | 更新实体 | 根据ID更新实体信息 |
| 5 | POST | `/api/v1/entity/{entity}/{ids}/bulk_update` | 批量更新实体 | 批量更新多个实体 |
| 6 | GET | `/api/v1/entity/{entity}/list` | 查询实体列表 | 分页查询实体列表 |
| 7 | GET | `/api/v1/entity/{entity}/{id}/view` | 查询实体详情 | 根据ID查询实体详细信息 |
| 8 | GET | `/api/v1/entity/{entity}/{attribute}/mapping` | 获取属性映射 | 获取指定属性的映射信息 |
| 9 | GET | `/api/v1/entity/{entity}/{attribute}/list` | 获取属性值列表 | 获取指定属性的所有值 |
| 10 | GET | `/api/v1/entity/{entity}/{attribute}/auto-complete` | 属性自动补全 | 根据关键词获取相似属性值 |

---

## 🔌 接口详情

### 1️⃣ 添加实体

**接口地址**: `POST /api/v1/entity/{entity}/add`

**功能描述**: 添加新的实体记录

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |

**请求参数**:
- Content-Type: `application/json`
- Body: Map\<String, Object\>（实体数据）

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/entity/asset_host/add \
  -H "Content-Type: application/json" \
  -d '{
    "hostName": "server-01",
    "ip": "192.168.1.1"
  }'
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "添加成功",
  "data": "添加成功"
}
```

---

### 2️⃣ 删除实体

**接口地址**: `DELETE /api/v1/entity/{entity}/{id}`

**功能描述**: 根据ID删除单个实体

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| id | String | 是 | 实体ID |

**请求示例**:
```bash
curl -X DELETE http://localhost:8080/api/v1/entity/asset_host/host-001
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "删除成功",
  "data": "删除成功"
}
```

---

### 3️⃣ 批量删除实体

**接口地址**: `DELETE /api/v1/entity/{entity}/bulk/{ids}`

**功能描述**: 批量删除多个实体

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| ids | List\<String\> | 是 | 实体ID列表（逗号分隔） |

**请求示例**:
```bash
curl -X DELETE http://localhost:8080/api/v1/entity/asset_host/bulk/host-001,host-002
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "删除成功",
  "data": "删除成功"
}
```

---

### 4️⃣ 更新实体

**接口地址**: `POST /api/v1/entity/{entity}/{id}/update`

**功能描述**: 根据ID更新实体信息

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| id | String | 是 | 实体ID |

**请求参数**:
- Content-Type: `application/json`
- Body: Map\<String, Object\>（更新数据）

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/entity/asset_host/host-001/update \
  -H "Content-Type: application/json" \
  -d '{
    "hostName": "server-01-updated"
  }'
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "修改成功",
  "data": "修改成功"
}
```

---

### 5️⃣ 批量更新实体

**接口地址**: `POST /api/v1/entity/{entity}/{ids}/bulk_update`

**功能描述**: 批量更新多个实体

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| ids | String[] | 是 | 实体ID数组（逗号分隔） |

**请求参数**:
- Content-Type: `application/json`
- Body: Map\<String, Object\>（更新数据）

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/entity/asset_host/host-001,host-002/bulk_update \
  -H "Content-Type: application/json" \
  -d '{
    "status": "active"
  }'
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "修改成功",
  "data": "修改成功"
}
```

---

### 6️⃣ 查询实体列表

**接口地址**: `GET /api/v1/entity/{entity}/list`

**功能描述**: 分页查询实体列表

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| page | Integer | 否 | 页码（默认1） |
| size | Integer | 否 | 每页条数（默认10） |

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/entity/asset_host/list?page=1&size=10"
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
    "rows": []
  }
}
```

---

### 7️⃣ 查询实体详情

**接口地址**: `GET /api/v1/entity/{entity}/{id}/view`

**功能描述**: 根据ID查询实体详细信息

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| id | String | 是 | 实体ID |

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/entity/asset_host/host-001/view
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "id": "host-001",
    "hostName": "server-01",
    "ip": "192.168.1.1"
  }
}
```

**失败响应**:
```json
{
  "status": -1,
  "msg": "fail",
  "data": null
}
```

---

### 8️⃣ 获取属性映射

**接口地址**: `GET /api/v1/entity/{entity}/{attribute}/mapping`

**功能描述**: 获取指定属性的映射信息

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| attribute | String | 是 | 属性名称 |

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/entity/asset_host/status/mapping
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "options": [
      {"label": "启用", "value": "active"},
      {"label": "禁用", "value": "inactive"}
    ]
  }
}
```

---

### 9️⃣ 获取属性值列表

**接口地址**: `GET /api/v1/entity/{entity}/{attribute}/list`

**功能描述**: 获取指定属性的所有值

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| attribute | String | 是 | 属性名称 |

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/entity/asset_host/manufacturer/list
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "options": [
      {"label": "Dell", "value": "Dell"},
      {"label": "HP", "value": "HP"}
    ]
  }
}
```

---

### 🔟 属性自动补全

**接口地址**: `GET /api/v1/entity/{entity}/{attribute}/auto-complete?term=xxx`

**功能描述**: 根据关键词获取相似属性值

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| entity | String | 是 | 实体名称 |
| attribute | String | 是 | 属性名称 |

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| term | String | 否 | 搜索关键词 |

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/entity/asset_host/host_name/auto-complete?term=server"
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "options": [
      {"label": "server-01", "value": "server-01"},
      {"label": "server-02", "value": "server-02"}
    ]
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
2. **动态实体**: `{entity}`为动态参数，表示具体的实体名称
3. **批量操作**: 支持批量删除和批量更新