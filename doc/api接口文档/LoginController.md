# Login 登录接口文档

**基础信息**
- **模块名称**: 登录管理
- **基础路径**: `/api/v1/system/login`
- **协议**: HTTP/HTTPS
- **数据格式**: JSON

---

## 📋 数据模型定义

### 1. LoginDto (登录入参)

```json
{
  "userName": "user@example.com",// String - 登录邮箱（必填）
  "password": "******",           // String - 密码（必填，RSA加密）
  "authCode": "abcd"              // String - 验证码（必填）
}
```

**字段说明**:
| 字段 | 类型 | 必填 | 说明 |
|-----|------|-----|------|
| userName | String | 是 | 登录邮箱 |
| password | String | 是 | 密码，需使用公钥加密 |
| authCode | String | 是 | 验证码 |

### 2. LoginVo (登录响应对象)

```json
{
  "permission": [],             // List<Tree<String>> - 权限列表（树形结构）
  "user": {                    // UserVo - 用户信息
    "id": 1,
    "email": "user@example.com",
    "name": "用户名",
    "roleId": 1,
    "roleName": "管理员",
    "updateTime": "2024-01-01 12:00:00"
  }
}
```

### 3. PubKeyVo (公钥响应对象)

```json
{
  "key": "MIIBIjANBgkqhkiG9w0BAQEFAAO..."  // String - RSA公钥
}
```

### 4. ResponseWrap (统一响应格式)

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
| 1 | POST | `/api/v1/system/login/sign-in` | 用户登录 | 用户登录系统 |
| 2 | GET | `/api/v1/system/login/kaptcha` | 获取验证码 | 生成并返回验证码图片 |
| 3 | GET | `/api/v1/system/login/encrypt/key` | 获取公钥 | 返回RSA公钥用于密码加密 |
| 4 | POST | `/api/v1/system/login/sign-out` | 退出登录 | 用户退出系统 |

---

## 🔌 接口详情

### 1️⃣ 用户登录

**接口地址**: `POST /api/v1/system/login/sign-in`

**功能描述**: 用户登录系统，验证验证码和密码，返回用户信息和权限列表

**请求参数**:
- Content-Type: `application/json`
- Body: LoginDto

**请求示例**:
```bash
curl -X POST http://localhost:11002/api/v1/system/login/sign-in \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "admin@example.com",
    "password": "encryptedPassword",
    "authCode": "abcd"
  }'
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "permission": [
      {
        "id": "1",
        "name": "系统管理",
        "children": []
      }
    ],
    "user": {
      "id": 1,
      "email": "admin@example.com",
      "name": "管理员",
      "roleId": 1,
      "roleName": "管理员",
      "updateTime": "2024-01-01 12:00:00"
    }
  }
}
```

---

### 2️⃣ 获取验证码

**接口地址**: `GET /api/v1/system/login/kaptcha`

**功能描述**: 生成验证码图片，同时将验证码保存到session中，有效期120秒

**请求参数**: 无

**请求示例**:
```bash
curl -X GET http://localhost:11002/api/v1/system/login/kaptcha
```

**成功响应**: 返回PNG格式的验证码图片

---

### 3️⃣ 获取公钥

**接口地址**: `GET /api/v1/system/login/encrypt/key`

**功能描述**: 返回RSA公钥，用于客户端对密码进行加密传输

**请求参数**: 无

**请求示例**:
```bash
curl -X GET http://localhost:11002/api/v1/system/login/encrypt/key
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
  "data": {
    "key": "MIIBIjANBgkqhkiG9w0BAQEFAAO..."
  }
}
```

---

### 4️⃣ 退出登录

**接口地址**: `POST /api/v1/system/login/sign-out`

**功能描述**: 用户退出系统，清除session和cookie

**请求参数**: 无

**请求示例**:
```bash
curl -X POST http://localhost:11002/api/v1/system/login/sign-out
```

**成功响应**:
```json
{
  "status": 0,
  "msg": "success",
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

1. **登录流程**: 
   - 先调用获取公钥接口获取RSA公钥
   - 调用获取验证码接口获取验证码
   - 使用公钥加密密码后调用登录接口
2. **验证码有效期**: 验证码有效期为120秒
3. **Session安全**: 登录成功后会重置SessionID，提高安全性
4. **退出登录**: 退出时会清除Redis中的Session和浏览器Cookie

---