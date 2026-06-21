# HomeBoardController API 接口文档

## 基础信息

| 属性 | 值 |
| :--- | :--- |
| **模块** | dashboard（仪表盘） |
| **Controller** | HomeBoardController |
| **基础路径** | `/api/v1/dashboard/home` |
| **描述** | 首页仪表盘数据接口，提供速率、汇总、状态、效率、实时信息及图表数据 |

---

## 数据模型定义

### 3.1 请求模型

#### BaseQueryDto

| 字段名 | 类型 | 必填 | 含义 | 示例 |
| :--- | :--- | :--- | :--- | :--- |
| `startTime` | Long | 是 | 查询开始时间（时间戳） | 1704067200000 |
| `endTime` | Long | 是 | 查询结束时间（时间戳） | 1704153600000 |

### 3.2 响应模型

#### SpeedVo（速率对象）

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `receiveTimeAverage` | double | 平均接收时间 | 12.5 |
| `receiveTimeCurrent` | double | 当前接收时间 | 10.2 |
| `processTimeAverage` | double | 平均处理时间 | 8.3 |
| `processTimeCurrent` | double | 当前处理时间 | 6.1 |
| `countOfSecondAverage` | double | 平均每秒处理数 | 100.5 |
| `countOfSecondCurrent` | double | 当前每秒处理数 | 120.0 |

#### SummaryVo（汇总对象）

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `msgTotal` | long | 消息总数 | 1000000 |
| `deviceTotal` | long | 设备总数 | 10000 |
| `startTotal` | long | 启动总数 | 5000 |

#### StatusVo（状态对象）

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `status` | String | 系统状态 | "running" |
| `serviceStatus` | Notice | 服务状态 | {"count": 0, "info": "正常"} |
| `msgStatus` | Notice | 消息状态 | {"count": 100, "info": "待处理"} |
| `toDo` | Notice | 待办事项 | {"count": 5, "info": "待审核"} |

**Notice（通知内部类）**

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `count` | int | 数量 | 10 |
| `info` | String | 描述信息 | "正常" |

#### EfficiencyVo（效率对象）

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `ratio` | int | 效率比率 | 95 |
| `receiveDelay` | String | 当前接收延迟 | "10ms" |
| `receiveDelayAverage` | String | 平均接收延迟 | "12ms" |
| `processDelay` | String | 当前处理延迟 | "8ms" |
| `processDelayAverage` | String | 平均处理延迟 | "10ms" |
| `msgCountForMinute` | String | 当前每分钟消息数 | "12000" |
| `msgCountForMinuteAverage` | String | 平均每分钟消息数 | "10000" |

#### RealInfoVo（实时信息对象）

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `infoList` | List\<String\> | 实时信息列表 | ["设备上线", "告警触发"] |

#### StackedBarChartVo（堆叠柱状图对象）

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `xAxis` | List\<String\> | X轴数据 | ["北京", "上海", "广州"] |
| `yAxisName` | Set\<String\> | Y轴名称集合 | {"Android", "iOS"} |
| `yAxisData` | List\<List\<Long\>\> | Y轴数据 | [[1000, 2000], [1500, 2500]] |

#### StackedLineChartVo（堆叠折线图对象）

| 字段名 | 类型 | 含义 | 示例 |
| :--- | :--- | :--- | :--- |
| `xAxis` | List\<String\> | X轴数据 | ["00:00", "01:00", "02:00"] |
| `yAxisName` | Set\<String\> | Y轴名称集合 | {"消息数", "设备数"} |
| `yAxisData` | List\<List\<Long\>\> | Y轴数据 | [[10000, 5000], [12000, 6000]] |

---

## 接口列表

| API 路径 | HTTP 方法 | 所属文件 | 功能描述 |
| :--- | :--- | :--- | :--- |
| `/api/v1/dashboard/home/speed-stat` | POST | HomeBoardController.java | 获取速率表数据 |
| `/api/v1/dashboard/home/summary` | POST | HomeBoardController.java | 获取数据总计 |
| `/api/v1/dashboard/home/status` | POST | HomeBoardController.java | 获取状态信息 |
| `/api/v1/dashboard/home/efficiency` | POST | HomeBoardController.java | 获取效率数据 |
| `/api/v1/dashboard/home/real-info` | POST | HomeBoardController.java | 获取实时信息 |
| `/api/v1/dashboard/home/province-city-stat` | POST | HomeBoardController.java | 获取省-市地域分布 |
| `/api/v1/dashboard/home/manufacture-system-stat` | POST | HomeBoardController.java | 获取厂商-操作系统分布 |
| `/api/v1/dashboard/home/msg-trend` | POST | HomeBoardController.java | 获取数据分布趋势 |

---

## 接口详细描述

### 5.1 获取速率表数据

- **路径**: `POST /api/v1/dashboard/home/speed-stat`
- **功能**: 获取当前和平均的接收时间、处理时间及每秒处理数

**请求体**

无请求参数

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "receiveTimeAverage": 12.5,
        "receiveTimeCurrent": 10.2,
        "processTimeAverage": 8.3,
        "processTimeCurrent": 6.1,
        "countOfSecondAverage": 100.5,
        "countOfSecondCurrent": 120.0
    }
}
```

---

### 5.2 获取数据总计

- **路径**: `POST /api/v1/dashboard/home/summary`
- **功能**: 获取指定时间范围内的消息总数、设备总数和启动总数

**请求体**

```json
{
    "startTime": 1704067200000,
    "endTime": 1704153600000
}
```

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "msgTotal": 1000000,
        "deviceTotal": 10000,
        "startTotal": 5000
    }
}
```

**失败响应**

```json
{
    "code": 400,
    "message": "开始时间不能为空",
    "data": null
}
```

---

### 5.3 获取状态信息

- **路径**: `POST /api/v1/dashboard/home/status`
- **功能**: 获取系统状态、服务状态、消息状态和待办事项

**请求体**

无请求参数

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "status": "running",
        "serviceStatus": {
            "count": 0,
            "info": "正常"
        },
        "msgStatus": {
            "count": 100,
            "info": "待处理"
        },
        "toDo": {
            "count": 5,
            "info": "待审核"
        }
    }
}
```

---

### 5.4 获取效率数据

- **路径**: `POST /api/v1/dashboard/home/efficiency`
- **功能**: 获取系统效率相关数据

**请求体**

无请求参数

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "ratio": 95,
        "receiveDelay": "10ms",
        "receiveDelayAverage": "12ms",
        "processDelay": "8ms",
        "processDelayAverage": "10ms",
        "msgCountForMinute": "12000",
        "msgCountForMinuteAverage": "10000"
    }
}
```

---

### 5.5 获取实时信息

- **路径**: `POST /api/v1/dashboard/home/real-info`
- **功能**: 获取系统实时信息列表

**请求体**

无请求参数

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "infoList": [
            "设备[192.168.1.100]上线",
            "告警[高风险]触发",
            "策略[policy_v1]已应用"
        ]
    }
}
```

---

### 5.6 获取省-市地域分布

- **路径**: `POST /api/v1/dashboard/home/province-city-stat`
- **功能**: 获取指定时间范围内按省-市维度的地域分布数据

**请求体**

```json
{
    "startTime": 1704067200000,
    "endTime": 1704153600000
}
```

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "xAxis": ["北京", "上海", "广州"],
        "yAxisName": {"Android", "iOS"},
        "yAxisData": [
            [1000, 800],
            [1200, 900],
            [800, 700]
        ]
    }
}
```

---

### 5.7 获取厂商-操作系统分布

- **路径**: `POST /api/v1/dashboard/home/manufacture-system-stat`
- **功能**: 获取指定时间范围内按厂商-操作系统维度的分布数据

**请求体**

```json
{
    "startTime": 1704067200000,
    "endTime": 1704153600000
}
```

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "xAxis": ["华为", "小米", "苹果"],
        "yAxisName": {"Android", "iOS"},
        "yAxisData": [
            [5000, 0],
            [4000, 0],
            [0, 3000]
        ]
    }
}
```

---

### 5.8 获取数据分布趋势

- **路径**: `POST /api/v1/dashboard/home/msg-trend`
- **功能**: 获取指定时间范围内的数据分布趋势

**请求体**

```json
{
    "startTime": 1704067200000,
    "endTime": 1704153600000
}
```

**成功响应**

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {
        "xAxis": ["00:00", "01:00", "02:00", "03:00"],
        "yAxisName": {"消息数", "设备数"},
        "yAxisData": [
            [10000, 5000],
            [12000, 6000],
            [8000, 4500],
            [15000, 7000]
        ]
    }
}
```

---

## 通用响应格式

所有接口返回统一响应结构：

```json
{
    "code": 200,
    "message": "操作成功",
    "data": {}
}
```

| 字段 | 类型 | 含义 |
| :--- | :--- | :--- |
| `code` | Integer | 状态码，200表示成功，其他表示失败 |
| `message` | String | 提示信息 |
| `data` | Object | 返回的数据，可为任意类型或null |