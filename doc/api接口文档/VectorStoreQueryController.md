# VectorStoreQueryController 向量存储管理接口文档

**基础信息**
- **模块名称**: 向量存储管理（内部测试使用）
- **基础路径**: `/api/vectorstore`
- **作者**: coolxer
- **协议**: HTTP/HTTPS
- **数据格式**: JSON

---

## 📋 数据模型定义

### 1. Document (文档对象)

```json
{
  "id": "doc-001",            // String - 文档ID
  "content": "文档内容",       // String - 文档内容
  "metadata": {}              // Map - 文档元数据
}
```

### 2. SearchRequest (搜索请求对象)

```json
{
  "query": "搜索关键词",       // String - 搜索查询词
  "topK": 5,                  // Integer - 返回条数（默认5）
  "vectorType": "embedding"   // String - 向量类型
}
```

---

## 📊 接口总览

| 序号 | HTTP方法 | 接口路径 | 接口名称 | 功能描述 |
|:---:|:-------:|---------|---------|---------|
| 1 | GET | `/api/vectorstore/documents` | 获取所有文档 | 获取向量存储中的所有文档 |
| 2 | GET | `/api/vectorstore/document/{documentId}` | 获取单个文档 | 根据ID获取指定文档 |
| 3 | DELETE | `/api/vectorstore/document/{documentId}` | 删除单个文档 | 根据ID删除指定文档 |
| 4 | DELETE | `/api/vectorstore/documents` | 批量删除文档 | 根据ID列表批量删除文档 |
| 5 | POST | `/api/vectorstore/build-schema` | 构建Schema | 构建向量存储Schema |
| 6 | POST | `/api/vectorstore/search` | 相似度搜索 | 根据查询词进行相似度搜索 |
| 7 | DELETE | `/api/vectorstore/agent-documents` | 删除Agent文档 | 删除所有Agent使用的RAG数据 |

---

## 🔌 接口详情

### 1️⃣ 获取所有文档

**接口地址**: `GET /api/vectorstore/documents`

**功能描述**: 获取向量存储中的所有文档

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/vectorstore/documents
```

**成功响应**:
```json
[
  {
    "id": "doc-001",
    "content": "文档内容",
    "metadata": {}
  }
]
```

---

### 2️⃣ 获取单个文档

**接口地址**: `GET /api/vectorstore/document/{documentId}`

**功能描述**: 根据ID获取指定文档

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| documentId | String | 是 | 文档ID |

**请求示例**:
```bash
curl -X GET http://localhost:8080/api/vectorstore/document/doc-001
```

**成功响应**:
```json
{
  "id": "doc-001",
  "content": "文档内容",
  "metadata": {}
}
```

---

### 3️⃣ 删除单个文档

**接口地址**: `DELETE /api/vectorstore/document/{documentId}`

**功能描述**: 根据ID删除指定文档

**路径参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| documentId | String | 是 | 文档ID |

**请求示例**:
```bash
curl -X DELETE http://localhost:8080/api/vectorstore/document/doc-001
```

**成功响应**:
```bash
文档删除成功: doc-001
```

**失败响应**:
```bash
文档删除失败: doc-001
```

---

### 4️⃣ 批量删除文档

**接口地址**: `DELETE /api/vectorstore/documents`

**功能描述**: 根据ID列表批量删除文档

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| documentIds | List\<String\> | 是 | 文档ID列表 |

**请求示例**:
```bash
curl -X DELETE "http://localhost:8080/api/vectorstore/documents?documentIds=doc-001,doc-002"
```

**成功响应**:
```bash
文档删除成功，共删除 2 个文档
```

**失败响应**:
```bash
文档删除失败
```

---

### 5️⃣ 构建Schema

**接口地址**: `POST /api/vectorstore/build-schema`

**功能描述**: 构建向量存储Schema

**请求示例**:
```bash
curl -X POST http://localhost:8080/api/vectorstore/build-schema
```

**成功响应**:
```bash
success
```

---

### 6️⃣ 相似度搜索

**接口地址**: `POST /api/vectorstore/search`

**功能描述**: 根据查询词进行相似度搜索

**查询参数**:
| 参数名 | 类型 | 必填 | 说明 |
|-------|------|-----|------|
| query | String | 是 | 搜索查询词 |
| topK | Integer | 否 | 返回条数（默认5） |
| vectorType | String | 是 | 向量类型 |

**请求示例**:
```bash
curl -X POST "http://localhost:8080/api/vectorstore/search?query=测试&topK=5&vectorType=embedding"
```

**成功响应**:
```json
[
  {
    "id": "doc-001",
    "content": "相关文档内容",
    "metadata": {}
  }
]
```

---

### 7️⃣ 删除Agent文档

**接口地址**: `DELETE /api/vectorstore/agent-documents`

**功能描述**: 删除所有Agent使用的RAG数据（table/column/evidence）

**请求示例**:
```bash
curl -X DELETE http://localhost:8080/api/vectorstore/agent-documents
```

**成功响应**:
```bash
删除成功，共删除 10 个文档
```

**无文档响应**:
```bash
当前没有需要删除的文档
```

---

## 📊 响应码汇总

| 响应码 | 说明 | 触发场景 |
|--------|------|---------|
| 200 | 请求成功 | 操作成功完成 |

---

## 🔐 注意事项

1. **内部测试**: 此接口仅供内部测试使用，生产环境不建议暴露
2. **向量类型**: search接口必须指定vectorType参数
3. **批量删除**: documents接口通过query参数传递documentIds列表