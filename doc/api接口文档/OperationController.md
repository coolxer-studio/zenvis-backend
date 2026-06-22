# OperationController 运营看板接口文档

**基础信息**
- **模块名称**: 运营看板管理
- **基础路径**: `/api/v1/operation`
- **作者**: coolxer
- **协议**: HTTP/HTTPS
- **数据格式**: JSON

---

## 📋 数据模型定义

### 1. OperationBoardDto (看板传输对象)

```json
{
  "name": "访问量统计",        // String - 看板名称
  "type": "LINE_CHART",       // String - 图表类型
  "description": "描述信息",   // String - 描述信息
  "config": {}                // Object - 配置信息
}
```

### 2. ResponseWrap (统一响应格式)

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
| 1 | GET | `/api/v1/operation/dashboard` | 获取看板列表 | 获取所有看板面板列表 |
| 2 | GET | `/api/v1/operation/dashboard/{id}/chart` | 获取看板图表 | 根据ID获取看板图表数据 |
| 3 | POST | `/api/v1/operation/dashboard/add` | 添加看板 | 添加新的看板面板 |
| 4 | DELETE | `/api/v1/operation/{id}` | 删除看板 | 根据ID删除看板面板 |

---

## 🔌 接口详情

### 1️⃣ 获取看板列表

**接口地址**: `GET /api/v1/operation/dashboard`

**功能描述**: 获取所有看板面板列表

**请求参数**: 无

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/operation/dashboard
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "panel_list": [
      {
        "id": 1,
        "name": "访问量统计",
        "type": "LINE_CHART"
      }
    ]
  }
}
```

---

### 2️⃣ 获取看板图表

**接口地址**: `GET /api/v1/operation/dashboard/{id}/chart`

**功能描述**: 根据ID获取看板图表数据

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | Long | 是 | 看板ID |

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/v1/operation/dashboard/1/chart
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "chart_data": {}
  }
}
```

**失败响应**:
```json
{
  "status": -1,
  "msg": "error message",
  "data": null
}
```

---

### 3️⃣ 添加看板

**接口地址**: `POST /api/v1/operation/dashboard/add`

**功能描述**: 添加新的看板面板

**请求参数**:
- Content-Type: `application/json`
- Body: OperationBoardDto

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/v1/operation/dashboard/add \
  -H "Content-Type: application/json" \
  -d '{
    "name": "访问量统计",
    "type": "LINE_CHART"
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

**失败响应**:
```json
{
  "status": -1,
  "msg": "UNKNOWN_ERROR",
  "data": null
}
```

---

### 4️⃣ 删除看板

**接口地址**: `DELETE /api/v1/operation/{id}`

**功能描述**: 根据ID删除看板面板

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| id | Long | 是 | 看板ID |

**请求示例**:
```bash
curl -X DELETE http://localhost:8080/api/v1/operation/1
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "删除成功",
  "data": "删除成功"
}
```

**失败响应**:
```json
{
  "status": -1,
  "msg": "error message",
  "data": null
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
2. **看板ID**: 使用Long类型，通过路径参数传递