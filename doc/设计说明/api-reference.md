# API 参考

ZenVis 提供完整的 RESTful API，支持第三方系统集成。

## 在线文档

启动服务后访问 Swagger UI：

```
http://localhost:11001/swagger-ui/index.html
```

## API 分组

### 认证接口

| 文档 | 说明 |
| :--- | :--- |
| [LoginController](../api接口文档/LoginController.md) | 用户登录认证 |
| [UserController](../api接口文档/UserController.md) | 用户管理 |
| [RoleController](../api接口文档/RoleController.md) | 角色管理 |
| [MenuController](../api接口文档/MenuController.md) | 菜单管理 |

### 资产管理

| 文档 | 说明 |
| :--- | :--- |
| [AssetHostController](../api接口文档/AssetHostController.md) | 主机资产 |
| [AssetAppController](../api接口文档/AssetAppController.md) | App资产 |
| [AssetApiController](../api接口文档/AssetApiController.md) | API资产 |
| [AssetFileController](../api接口文档/AssetFileController.md) | 文件资产 |
| [AssetIotController](../api接口文档/AssetIotController.md) | IoT资产 |
| [AssetMobileController](../api接口文档/AssetMobileController.md) | 移动端资产 |
| [AssetPcController](../api接口文档/AssetPcController.md) | PC端资产 |
| [AssetProbeController](../api接口文档/AssetProbeController.md) | 探针资产 |
| [AssetServiceController](../api接口文档/AssetServiceController.md) | 服务资产 |
| [AssetLogController](../api接口文档/AssetLogController.md) | 日志资产 |
| [AssetRuleController](../api接口文档/AssetRuleController.md) | 规则资产 |
| [AssetOverallController](../api接口文档/AssetOverallController.md) | 资产总览 |

### 运营事件

| 文档 | 说明 |
| :--- | :--- |
| [AnrEventController](../api接口文档/AnrEventController.md) | ANR事件 |
| [ApiCallEventController](../api接口文档/ApiCallEventController.md) | API调用事件 |
| [ClickEventController](../api接口文档/ClickEventController.md) | 点击事件 |
| [CrashEventController](../api接口文档/CrashEventController.md) | 崩溃事件 |
| [ExtendEventController](../api接口文档/ExtendEventController.md) | 扩展事件 |
| [LocationEventController](../api接口文档/LocationEventController.md) | 位置事件 |
| [NetworkEventController](../api接口文档/NetworkEventController.md) | 网络事件 |
| [PageEventController](../api接口文档/PageEventController.md) | 页面事件 |
| [PerformanceEventController](../api接口文档/PerformanceEventController.md) | 性能事件 |
| [StartEventController](../api接口文档/StartEventController.md) | 启动事件 |

### 风险管理

| 文档 | 说明 |
| :--- | :--- |
| [AttackRiskController](../api接口文档/AttackRiskController.md) | 攻击风险 |
| [BaselineRiskController](../api接口文档/BaselineRiskController.md) | 基线风险 |
| [DataRiskController](../api接口文档/DataRiskController.md) | 数据风险 |
| [RiskController](../api接口文档/RiskController.md) | 风险总览 |
| [RiskEventController](../api接口文档/RiskEventController.md) | 风险事件 |
| [VulnerabilityRiskController](../api接口文档/VulnerabilityRiskController.md) | 漏洞风险 |
| [WeakRiskController](../api接口文档/WeakRiskController.md) | 弱口令风险 |

### AI 智能分析

| 文档 | 说明 |
| :--- | :--- |
| [ChatController](../api接口文档/ChatController.md) | AI对话 |
| [ChatSessionController](../api接口文档/ChatSessionController.md) | 对话会话 |
| [DihController](../api接口文档/DihController.md) | NL2SQL查询 |
| [VectorStoreQueryController](../api接口文档/VectorStoreQueryController.md) | 向量存储查询 |

### 数据检索

| 文档 | 说明 |
| :--- | :--- |
| [AggregateController](../api接口文档/AggregateController.md) | 数据聚合 |
| [EntityCoreController](../api接口文档/EntityCoreController.md) | 实体查询 |
| [EntityCountController](../api接口文档/EntityCountController.md) | 实体计数 |
| [RetrievalController](../api接口文档/RetrievalController.md) | 数据检索 |

### 仪表盘

| 文档 | 说明 |
| :--- | :--- |
| [DashboardController](../api接口文档/DashboardController.md) | 仪表盘管理 |
| [HomeBoardController](../api接口文档/HomeBoardController.md) | 首页看板 |

### 系统配置

| 文档 | 说明 |
| :--- | :--- |
| [ConfigController](../api接口文档/ConfigController.md) | 系统配置 |
| [AboutController](../api接口文档/AboutController.md) | 关于信息 |
| [OperationController](../api接口文档/OperationController.md) | 操作记录 |
| [PluginController](../api接口文档/PluginController.md) | 插件管理 |
| [PushTaskController](../api接口文档/PushTaskController.md) | 推送任务 |

## 通用说明

### 请求格式

```json
Content-Type: application/json
```

### 响应格式

```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

### 分页格式

```json
{
  "code": 200,
  "message": "success",
  "data": {
    "records": [],
    "total": 100,
    "page": 1,
    "pageSize": 20
  }
}
```

### 认证方式

除登录接口外，所有接口需要在 Header 中携带 Token：

```
Authorization: Bearer <token>
```

获取 Token：

```bash
POST /api/v1/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin"
}
```

## 常见问题

### 1. 请求超时

调整接口超时时间或减少查询数据量。

### 2. 数据量过大

使用分页参数：

```json
{
  "page": 1,
  "pageSize": 20
}
```

### 3. CORS 跨域

配置允许的源，详见 [部署配置](deployment.md)。

## 下一步

- [快速入门](getting-started.md)
- [AI智能分析](ai-analysis.md)
