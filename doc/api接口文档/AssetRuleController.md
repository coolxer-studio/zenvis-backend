# AssetRule 资产规则接口文档

**基础信息**
- **模块名称**: 资产规则管理
- **基础路径**: `/api/v1/asset/rule`
- **作者**: coolxer
- **协议**: HTTP/HTTPS
- **数据格式**: JSON

---

## 📋 数据模型定义

### 1. AssetRuleDto (资产规则传输对象)

```json
{
  "id": 1,                    // Long - 规则ID（更新时使用，创建时不传）
  "name": "规则名称",          // String - 规则名称
  "action": "ALERT",          // String - 行动类型（枚举值）
  "status": "ACTIVE",         // String - 状态（枚举值）
  "description": "规则描述",   // String - 描述信息
  "config": "{}",             // String - 配置内容
  "source": "SYSTEM"          // String - 来源
}
```

### 2. AssetRuleVo (资产规则视图对象)

```json
{
  "id": 1,                    // Long - 规则ID
  "name": "规则名称",          // String - 规则名称
  "action": "ALERT",          // String - 行动类型
  "status": "ACTIVE",         // String - 状态
  "description": "规则描述",   // String - 描述信息
  "config": "{}",             // String - 配置内容
  "source": "SYSTEM",         // String - 来源
  "updateTime": "2024-01-01 12:00:00"  // String - 更新时间
}
```

### 3. ResponseWrap (统一响应格式)

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
| 1 | POST | `/api/v1/asset/rule/add` | 创建规则 | 创建新的资产规则 |
| 2 | DELETE | `/api/v1/asset/rule/{id}` | 删除规则 | 根据ID删除单个规则 |
| 3 | DELETE | `/api/v1/asset/rule/bulk/{ids}` | 批量删除规则 | 批量删除多个规则 |
| 4 | POST | `/api/v1/asset/rule/{id}/update` | 更新规则 | 根据ID更新规则信息 |
| 5 | POST | `/api/v1/asset/rule/{ids}/bulk_update` | 批量更新规则 | 批量更新多个规则 |
| 6 | GET | `/api/v1/asset/rule/list` | 查询规则列表 | 分页查询规则列表 |
| 7 | GET | `/api/v1/asset/rule/{id}/view` | 查询规则详情 | 根据ID查询规则详细信息 |
| 8 | POST | `/api/v1/asset/rule/{id}/activate` | 激活规则 | 激活规则 |
| 9 | POST | `/api/v1/asset/rule/{id}/deactivate` | 停用规则 | 停用规则 |
| 10 | GET | `/api/v1/asset/rule/action/list` | 获取行动列表 | 获取行动类型枚举列表 |
| 11 | GET | `/api/v1/asset/rule/status/list` | 获取状态列表 | 获取状态枚举列表 |

---

## 🔌 接口详情

### 1️⃣ 创建规则

**接口地址**: `POST /api/v1/asset/rule/add`

**功能描述**: 创建新的资产规则

**请求参数**:
- Content-Type: `application/json`
- Body: AssetRuleDto

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/asset/rule/add \
  -H "Content-Type: application/json" \
  -d '{
    "name": "测试规则",
    "action": "ALERT",
    "status": "ACTIVE",
    "description": "测试规则描述"
  }'
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "创建成功",
  "data": "创建成功"
}
```

---

### 2️⃣ 删除规则

**接口地址**: `DELETE /api/v1/asset/rule/{id}`

**功能描述**: 根据ID删除单个规则

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | Long | 是 | 规则ID |

**请求示例**:
```bash
curl -X DELETE http://localhost:8080/api/v1/asset/rule/1
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

### 3️⃣ 批量删除规则

**接口地址**: `DELETE /api/v1/asset/rule/bulk/{ids}`

**功能描述**: 批量删除多个规则

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| ids | List\<Long\> | 是 | 规则ID列表，逗号分隔 |

**请求示例**:
```bash
curl -X DELETE http://localhost:8080/api/v1/asset/rule/bulk/1,2,3
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

### 4️⃣ 更新规则

**接口地址**: `POST /api/v1/asset/rule/{id}/update`

**功能描述**: 根据ID更新规则信息

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | Long | 是 | 规则ID |

**请求参数**:
- Content-Type: `application/json`
- Body: AssetRuleDto

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/asset/rule/1/update \
  -H "Content-Type: application/json" \
  -d '{
    "name": "更新后的规则名称"
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

### 5️⃣ 批量更新规则

**接口地址**: `POST /api/v1/asset/rule/{ids}/bulk_update`

**功能描述**: 批量更新多个规则

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| ids | Long[] | 是 | 规则ID数组，逗号分隔 |

**请求参数**:
- Content-Type: `application/json`
- Body: AssetRuleDto

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/asset/rule/1,2,3/bulk_update \
  -H "Content-Type: application/json" \
  -d '{
    "status": "INACTIVE"
  }'
```

**成功响应**:
```json
{
  "status": 200,
  "msg": "OK",
  "data": null
}
```

---

### 6️⃣ 查询规则列表

**接口地址**: `GET /api/v1/asset/rule/list`

**功能描述**: 分页查询规则列表

**请求参数**:
- Query: AssetRuleSearchDto（分页及搜索条件）

**请求示例**:
```bash
curl -X GET "http://localhost:8080/api/v1/asset/rule/list?page=1&size=10"
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

### 7️⃣ 查询规则详情

**接口地址**: `GET /api/v1/asset/rule/{id}/view`

**功能描述**: 根据ID查询规则详细信息

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | Long | 是 | 规则ID |

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/asset/rule/1/view
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "id": 1,
    "name": "测试规则",
    "action": "ALERT",
    "status": "ACTIVE",
    "description": "测试规则描述",
    "config": "{}",
    "source": "SYSTEM",
    "updateTime": "2024-01-01 12:00:00"
  }
}
```

---

### 8️⃣ 激活规则

**接口地址**: `POST /api/v1/asset/rule/{id}/activate`

**功能描述**: 激活规则（当前版本操作无效）

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | Long | 是 | 规则ID |

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/asset/rule/1/activate
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "当前版本操作无效！",
  "data": null
}
```

---

### 9️⃣ 停用规则

**接口地址**: `POST /api/v1/asset/rule/{id}/deactivate`

**功能描述**: 停用规则（当前版本操作无效）

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | Long | 是 | 规则ID |

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/asset/rule/1/deactivate
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "当前版本操作无效！",
  "data": null
}
```

---

### 🔟 获取行动列表

**接口地址**: `GET /api/v1/asset/rule/action/list`

**功能描述**: 获取行动类型枚举列表

**请求参数**: 无

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/asset/rule/action/list
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "options": [
      {"label": "告警", "value": "ALERT"},
      {"label": "阻断", "value": "BLOCK"}
    ]
  }
}
```

---

### 1️⃣1️⃣ 获取状态列表

**接口地址**: `GET /api/v1/asset/rule/status/list`

**功能描述**: 获取状态枚举列表

**请求参数**: 无

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/asset/rule/status/list
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "options": [
      {"label": "激活", "value": "ACTIVE"},
      {"label": "停用", "value": "INACTIVE"}
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
2. **批量操作**: 批量删除/更新时，ID列表不能为空
3. **激活/停用**: 当前版本activate和deactivate操作返回"当前版本操作无效！"
4. **数据校验**: 创建/更新规则时需确保必填字段完整