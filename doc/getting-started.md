# 快速入门

本指南将帮助你在 5 分钟内快速部署并运行 ZenVis 服务。

## 前置要求

| 要求 | 版本 |
| :--- | :--- |
| Docker | 20.10+ |
| Docker Compose | 2.0+ |
| 系统内存 | 4GB+ |

## 快速启动（推荐）

### 1. 克隆项目

```bash
git clone https://github.com/your-repo/zenvis-backend.git
cd zenvis-backend
```

### 2. 一键启动

```bash
cd deploy
docker-compose up -d
```

### 3. 验证服务

等待 1-2 分钟服务启动后，访问以下地址：

| 服务 | 地址 | 说明 |
| :--- | :--- | :--- |
| Web前端 | http://localhost:11000 | 用户界面 |
| API服务 | http://localhost:11001 | 后端接口 |
| Swagger文档 | http://localhost:11001/swagger-ui/index.html | API在线文档 |

## 默认账号

登录 Web 前端：

- **用户名**: admin
- **密码**: admin

## 快速体验 AI 智能分析

服务启动后，可通过 API 接口体验自然语言查询：

```bash
curl -X POST http://localhost:11001/api/v1/dih/chat \
  -H "Content-Type: application/json" \
  -d '{
    "query": "查询最近7天的API调用次数",
    "chatId": "session-001"
  }'
```

## 目录结构

```
zenvis-backend/
├── deploy/              # 部署配置
│   ├── docker-compose.yml
│   ├── config/          # 服务配置
│   └── data/           # 数据持久化目录
├── doc/                 # 项目文档
├── src/                 # 源代码
└── pom.xml
```

## 下一步

- [详细安装部署](installation.md)
- [系统架构设计](architecture.md)
- [API接口文档](api接口文档/)
