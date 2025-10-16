# FlyUnion VA系统API文档

## 1、前言

此文档为FlyUnion的VA系统涉及到的所有接口的接口文档，在第二章中注明了系统牵扯到的每个实体类的详解与示例数据。在第三章在每个接口的标题中标注了请求方法，必要的请求头与请求所需参数，以及各种可能出现的响应以供开发人员参考。如有遗漏或未在本文档中出现的响应或本文档的错误请联系1228。

### 本地部署前须知

本地部署需要jdk版本高于17，node版本高于18

在IDEA运行配置中添加SpringBoot启动项，选择子模块va或sqlite，启动类选择org.flyunion下的xxApplication的Java启动类后点击确定

在IDEA运行配置中添加npm启动项，选择vue项目对应的package.json文件后在脚本中添加dev后点击确定

运行时需要保证npm启动项与SpringBoot启动项均开始运行，启动先后顺序不做限制。

SpringBoot-va项目的端口号为9999，sqlite项目的端口号为9998，vue前端页面的端口号为5174

## 2、VA后端服务器实体类信息

### 2.1 航空公司（Company）

实体类数据详解

|  变量名称  |    变量描述    | 变量类型 |
| :--------: | :------------: | :------: |
|     id     | 航司唯一标识符 |  String  |
|    name    |    航司名称    |  String  |
| pilotCount | 航司飞行员人数 |   int    |
| planeCount | 航司航空器数量 |   int    |
|  balance   |    航司余额    |  String  |
|    base    |    航司基地    |  String  |
|    icao    |   航司三字码   |  String  |
|    iata    |   航司二字码   |  String  |

实体类数据示例

```json
{
    "id": "839218643384247",
    "name": "厦门航空",
    "pilotCount": 0,
    "planeCount": 0,
    "balance": "0",
    "base": "ZSAM",
    "icao": "CXA",
    "iata": "MF"
}
```

### 2.2 常量（Constants）

实体类数据详解

|  变量名称   | 变量描述 | 变量类型 |
| :---------: | :------: | :------: |
|     key     |  常量键  |  String  |
|    value    |  常量值  |  double  |
| description | 常量描述 |  String  |

实体类数据示例

```json
{
	"key": "ATC_fee",
    "value": 300,
    "description": "空管服务费"
}
```

### 2.3 机队（Fleet）

实体类数据详解

| 变量名称 |       变量描述        | 变量类型 |
| :------: | :-------------------: | :------: |
|   name   |       机队名称        |  String  |
|  model   |       飞机型号        |  String  |
| economy  |    经济舱座位数量     |   int    |
| business | 头等舱+商务舱座位数量 |   int    |
|  cargo   |      货舱装载量       |   int    |
|  number  |       飞机数量        |   int    |

实体类数据示例

```json
{
    "name": "B737-800",
    "model": "Boeing 737-800",
    "economy": 149,
    "business": 8,
    "cargo": 0,
    "number": 0
}
```

### 2.4 飞行记录（FlightLog）

实体类数据详解

| 变量名称  |                           变量描述                           |   变量类型    |
| :-------: | :----------------------------------------------------------: | :-----------: |
|    id     |                      飞行记录唯一标识符                      |    String     |
|   code    |                            航班号                            |    String     |
|   plane   |                        执飞飞机注册号                        |    String     |
|   pilot   |                        执飞飞行员cid                         |    String     |
| departure |                           起飞机场                           |    String     |
|  arrival  |                           落地机场                           |    String     |
|   route   |                           飞行航路                           |    String     |
|   date    |                       飞行记录提交时间                       | LocalDateTIme |
|   rate    |                            下降率                            |      int      |
|    oil    |                            耗油量                            |    double     |
|  status   | 报告状态（verify：待审批，accepted：已接受，rejected：已拒绝） |    String     |

实体类数据示例

```json
{
    "id": "71b0c2ff320840c591695c8fc0a57b22",
    "code": "MF8001",
    "plane": "B-1228",
    "pilot": "100013",
    "departure": "ZYTL",
    "arrival": "ZSHC",
    "route": "KARPI W5 TAO W174 XDX B221 ODULO V2 HSH G455 PK W116 JTN V73 SUPAR",
    "date": "2025-09-14 13:07:07",
    "rate": -118,
    "oil": 3600,
    "status": "verify"
}
```

### 2.5 可执行航线（FlightPlan）

实体类数据详解

|   变量名   |       变量描述       | 变量类型 |
| :--------: | :------------------: | :------: |
|   planID   | 可执行航线唯一标识符 |  String  |
| flightCode |        航班号        |  String  |
| departure  |       起飞机场       |  String  |
|  arrival   |       落地机场       |  String  |
|   route    |       飞行航路       |  String  |
|  company   |       所属航司       |  String  |

实体类数据示例

```json
{
    "id": "60fdf6ee6b1245aa9bdd87058eef4e21",
    "flightCode": "MF8002",
    "departure": "ZSPD",
    "arrival": "ZYTL",
    "route": "ODULO B221 XDX W174 FD W172 TEKAM V68 ORIXA",
    "company": "839218643384247"
}
```

### 2.6 飞机维护记录（MaintainingRecord）

实体类数据详解

| 变量名 |      变量描述      |   变量类型    |
| :----: | :----------------: | :-----------: |
|   id   | 维护记录唯一标识符 |    String     |
|  code  |  维护的飞机注册号  |    String     |
|  type  |      维护类型      |    String     |
|  date  |  维护报告提交时间  | LocalDateTime |

实体类数据示例

```json
{
    "id": "60fdf6ee6b1245aa9bdd87058eef4e21",
    "code": "B-1228",
    "type": "Heavy landing maintenance",//重着陆后检查
    "date": "2025-09-14 13:07:07"
}
```

### 2.7 航空器（Plane）

实体类数据详解

|   变量名   |                  变量描述                  | 变量类型 |
| :--------: | :----------------------------------------: | :------: |
|    code    |          飞机注册号（唯一标识符）          |  String  |
|   owner    |               飞机所有人cid                |  String  |
|   fleet    |                  所属机队                  |  String  |
|   model    |                    机模                    |  String  |
|   status   | 当前飞机状态（available，in use，crashed） |  String  |
|  position  |                当前飞机位置                |  String  |
|    time    |                    机龄                    |   int    |
| durability |              飞机当前维护情况              |  double  |
|  company   |                所属航空公司                |  String  |

实体类数据示例

```json
{
    "code": "B-1228",
    "owner": "100013",
    "fleet": "B737-800",
    "model": "Boeing 737-800",
    "status": "available",
    "position": "ZYTL",
    "time": 0,
    "durability": 100,
    "company": "839218643384247"
}
```

### 2.8 计划航班（PlannedFlight）

|   变量名    |      变量描述      |   变量类型    |
| :---------: | :----------------: | :-----------: |
|     id      | 计划航班唯一标识符 |    String     |
| flightCode  |       航班号       |    String     |
|  departure  |      起飞机场      |    String     |
|   arrival   |      落地机场      |    String     |
|    pilot    |   计划飞行员cid    |    String     |
| plannedTime |    计划飞行时间    | LocalDateTime |
|   status    |    当前计划状态    |    String     |

实体类数据示例

```json
{
    "id": "57d54cc56ef94ac3ac3210df3979a6d1",
    "code": "MF8001",
    "departure": "ZYTL",
    "arrival": "ZSHC",
    "pilot": "100013",
    "plannedTime": "2025-09-14 13:07:07",
    "status": "prepared"
}
```

### 2.9 用户（User）

实体类数据示例

|   变量名    |     变量描述     | 变量类型 |
| :---------: | :--------------: | :------: |
|     cid     |  用户唯一标识符  |  String  |
|  userName   |     用户昵称     |  String  |
|  callsign   |     四位呼号     |  String  |
|  password   |       密码       |  String  |
|     qq      |       qq号       |  String  |
|    email    |       邮箱       |  String  |
| permission  |     系统等级     |   int    |
|   status    |     用户状态     |  String  |
|   balance   |       余额       |   int    |
|   company   |   用户航空公司   |  String  |
|   airport   |   当前所在位置   |  String  |
|    time     |   用户飞行时长   |   int    |
| flightCount | 用户完成飞行次数 |   int    |
|     job     | 用户航空公司职位 |   int    |
| planeCount  | 用户拥有飞机数量 |   int    |

实体类数据示例

```json
{
    "cid": "100013",
    "userName": "FallenEra",
    "callsign": "1228",
    "password": "我不可能告诉你任何事情！！！！",
    "qq": "850824385",
    "email": "850824385@qq.com",
    "permission": 4,
    "status": "normal",
    "balance": 0,
    "company": "839218643384247",
    "airport": "ZYTL",
    "time": 0,
   	"flightCount": 0,
    "job": 8,
    "planeCount": 0
}
```

### 2.10 Request请求类

#### 2.10.1 验证码请求类（CaptchaRequest）

实体类数据详解

|    变量名    |     变量描述     | 变量类型 |
| :----------: | :--------------: | :------: |
| inputCaptcha |   输入的验证码   |  String  |
|    email     | 收到验证码的邮箱 |  String  |

实体类数据示例

```json
{
    "inputCaptha": "STEAM",
    "email": "850824385@qq.com"
}
```

#### 2.10.2 用户信息更改请求类（ChangeInfoRequest）

实体类数据详解

|       变量名       |              变量描述               | 变量类型 |
| :----------------: | :---------------------------------: | :------: |
|        cid         | 请求更改信息的用户cid，前端无法编辑 |  String  |
|      userName      |          更改后的用户昵称           |  String  |
|    oldPassword     |            输入的旧密码             |  String  |
|    newPassword     |            输入的新密码             |  String  |
| confirmNewPassword |          确认输入的新密码           |  String  |
|       email        |            输入的新邮箱             |  String  |

实体类数据示例

```json
{
    "cid": "100013",
    "userName": "FallenEra",
    "oldPassword": "我不可能告诉你任何事情！！！",
    "newPassword": "我不可能告诉你任何事情！！！",
    "confirmNewPassword": "我不可能告诉你任何事情！！！",
    "email": "8508234385@qq.com"
}
```

#### 2.10.3 重置密码请求类（PasswordResetRequest）

实体类数据详解

|  变量名  |     变量描述     | 变量类型 |
| :------: | :--------------: | :------: |
| password |   重置的新密码   |  String  |
|  email   | 收到验证码的邮箱 |  String  |

实体类数据示例

```json
{
    "password": "我不可能告诉你任何事情！！！",
    "email": "850824385@qq.com" 
}
```

#### 2.10.4 计划航班搜索请求类（PlanSearchRequest）

至少有一个不为空，实体类数据详解

|  变量名   | 变量描述 | 变量类型 |
| :-------: | :------: | :------: |
| departure | 起飞机场 |  String  |
|  arrival  | 落地机场 |  String  |

实体类数据示例

```json
{
    "departure": "ZYTL",
    "arrival": "ZSHC"
}
```

### 2.11 气象报文解析类（MetarData）

实体类数据详解

|      变量名       |      变量描述       |        变量类型        |
| :---------------: | :-----------------: | :--------------------: |
|      isCavok      | 报文中是否包含CAVOK |        boolean         |
|      rawText      |      原始报文       |         String         |
|     stationId     |    报文发布站点     |         String         |
|  observationTime  |    报文发布时间     |         String         |
|       wind        |    风向风速信息     |          Wind          |
|    visibility     |     能见度信息      |       Visibility       |
| weatherConditions |      天气现象       | List<WeatherCondition> |
|      clouds       |      云层信息       |    List<CloudLayer>    |
|    temperature    |      温度信息       |      Temperature       |
|     pressure      |      气压信息       |        Pressure        |
|       trend       |    气象趋势播报     |         String         |

子类数据详解

#### 2.11.1 风向风速信息（Wind）

实体类数据详解

|       变量名       |    变量描述    | 变量类型 |
| :----------------: | :------------: | :------: |
|       isVRB        | 风向是否为不定 | boolean  |
|   windDirection    |      风向      |  String  |
|       speed        |      风速      |   int    |
|     gustSpeed      |    阵风风速    |  String  |
|     speedUnit      |    风速单位    |  String  |
| directionVariation |  风向变化范围  |  String  |

#### 2.11.2 能见度信息（Visibility）

实体类数据详解

|   变量名    |      变量描述      | 变量类型 |
| :---------: | :----------------: | :------: |
|    value    |     能见度数值     |   int    |
| exceeds10km | 能见度是否超过10km | boolean  |
|  rawString  |      原始文本      |  String  |

#### 2.11.3 天气信息（WeatherCondition）

实体类数据详解

|    变量名     |  变量类型  | 变量值 |
| :-----------: | :--------: | :----: |
|   intensity   |  天气强度  | String |
|  descriptor   | 天气描述符 | String |
| precipitation |  降水类型  | String |
|  obscuration  |  遮蔽现象  | String |
|     other     |  其他现象  | String |

#### 2.11.4 云层信息（CloudLayer）

实体类数据详解

|  变量名  | 变量描述 | 变量类型 |
| :------: | :------: | :------: |
| coverage |   云量   |  String  |
| altitude |   云高   |   int    |
|   type   | 云层类型 |  String  |

#### 2.11.5 温度信息（Temperature）

实体类数据详解

|  变量名  | 变量描述 | 变量类型 |
| :------: | :------: | :------: |
| airTemp  |   气温   |   int    |
| dewPoint |   露点   |   int    |

#### 2.11.6 气压信息（Pressure）

实体类数据详解

| 变量名  |       变量描述        | 变量类型 |
| :-----: | :-------------------: | :------: |
|   qnh   |        气压值         |   int    |
| qnhUnit | 气压单位（默认为hpa） |  String  |

MetarData实体类数据类型

```json
{
  "isCavok": false,
  "rawText": "ZYTL 150630Z 09005MPS 6000 BKN023 27/23 Q1013 NOSIG=",
  "stationId": "ZYTL",
  "observationTime": "06:30 UTC",
  "wind": {
    "isVRB": false,
    "windDirection": 90,
    "speed": 5,
    "gustSpeed": null,
    "speedUnit": "MPS",
    "directionVariation": null
  },
  "visibility": {
    "value": 6000,
    "exceeds10km": false,
    "rawString": "6000"
  },
  "weatherConditions": [],
  "clouds": [
    {
      "coverage": "BKN",
      "altitude": 2300,
      "type": null
    }
  ],
  "temperature": {
    "airTemp": 27,
    "dewPoint": 23
  },
  "pressure": {
    "qnh": 1013,
    "qnhUnit": "hPa"
  },
  "trend": "NOSIG"
}
```



## 3、接口文档

`所有的数据解释详见章节1《实体类信息》`

当前所有接口文档的域名均为部署在自己电脑上的域名，即`localhost`。

`所有的请求与响应所展示的数据均为示例数据！`

### 3.1 VA后端服务器可匿名访问接口

此标题以下的接口均为可匿名访问，即为无需Token验证亦可访问的接口，在后端中标有`@SkipAuthentication`的注解。

#### 3.1.1 CID登录接口（请求方法：`POST`）

此方法用于使用CID与密码登录后获得Token，是获取Token的三种方式之第一种。

请求URL：`localhost:9999/user/login/cid`

必须的请求参数：

```json
{
    "cid": "100013",
    "password": "我不可能告诉你任何事情！！！"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "登录成功！",
	"data":"生成的Token"
}
```

请求失败的响应：

##### 3.1.1.1 用户被封禁（UserBannedException）

```json
{
    "code": 403,
    "message": "用户100013已经被封禁，如为误封请联系管理员！",
    "data": null
}
```

##### 3.1.1.2 登录密码错误（IncorrectPasswordException）

```json
{
    "code": 400,
    "message": "密码输入不正确，请重新输入",
    "data": null
}
```

##### 3.1.1.3 用户不存在（UserNotFountException）

```json
{
    "code": 404,
    "message": "用户100013不存在！",
    "data": null
}
```

#### 3.1.2 Email登录接口（请求方法：`POST`）

此方法用于使用Email与密码登录后获得Token，是获取Token的三种方式之第二种。

请求URL：`localhost:9999/user/login/email`

必须的请求参数：

```json
{
    "email": "850824385@qq.com",
    "password": "我不可能告诉你任何事情！！！"
}
```

`所有响应与3.1.1 CID登录接口的响应相同`

#### 3.1.3 注册接口（请求方法：`POST`）

此方法为系统注册新用户时使用。是获取Token的三种方式之第三种，在注册操作完成后会直接生成Token并跳转至主页。

请求URL：`localhost:9999/user/register`

必须的请求参数：

```json
{
    "cid": "100013",
    "userName": "FallenEra",
    "callsign": "1228",
    "password": "我不可能告诉你任何事情！！！",
    "confirm": "我不可能告诉你任何事情！！！",
    "email": "85082438@qq.com",
    "qq": "850824385",
    "company": "839218643384247"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "注册成功！",
	"data":"生成的Token"
}
```

请求失败的响应：

##### 3.1.3.1 用户存在（UserExistException）

```json
{
    "code": 409,
    "message": "用户100013已经存在，若忘记密码，请进入帮助中心！",
    "data": null
}
```

#### 3.1.4 更改密码（请求方法：`POST`）

此接口用于在用户忘记密码时在帮助页面进行修改密码的操作

请求URL：`localhost:9999/user/changePassword`

必须的请求参数：

```json
{
    "password": "我不会告诉你任何事情！！！",
    "confirm": "我不会告诉你任何事情！！！",
    "email": "850824385@qq.com"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "更改密码完毕，即将登录",
    "data": "生成的Token"
}
```

请求失败的响应：

##### 3.1.4.1 系统问题

```json
{
    "code": 500,
    "message": "出现未知问题，请联系管理员",
    "data": null
}
```

#### 3.1.5 选择所有航空公司信息（请求方法：`GET`）

此接口用于在注册时选择所有航空公司的基本信息，用于渲染前端单选框。

请求URL：`localhost:9999/company/all`

必须的请求参数：`无`

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下航空公司信息!",
    "data": ["找到的所有航空公司的基本数据"]
}
```

请求失败的响应：

```json
{
    "code": 404,
    "message": "未找到符合条件的数据",
    "data": null
}
```

#### 3.1.6 生成并发送验证码（请求方法：`POST`）

此接口适用于在更改密码前发送验证码验证邮箱

请求URL：`localhost:9999/mail/sendCaptcha/{email}`

必须的请求参数：

```json
{
    "email": "850824385@qq.com",
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "验证码发送成功",
    "data": null
}
```

请求失败时会在后端抛出应有的异常，请及时查阅后端日志进行分析

#### 3.1.7 比较验证码（请求方法：`POST`）

此接口用于验证通过接口3.1.6发送的验证码并比较验证码有效性的接口，比较成功后会跳转至重置密码界面

请求URL：`localhost:9999/mail/verifyCaptcha`

必须的请求头：

```json
{
    "inputCaptcha": "6DFUP",
    "email": "850824385@qq.com"
}
```

请求成功的响应： 

```json
{
    "code": 200,
    "message": "验证码验证成功",
    "data": null
}
```

请求失败的响应：

##### 3.1.7.1 验证码失效

```json
{
    "code": 404,
    "message": "未找到对应的验证码",
    "data": null
}
```

##### 3.1.7.2 验证码输入错误（IncorrectCaptchaException）

```json
{
    "code": 400,
    "message": "验证码输入错误",
    "data": null
}
```

### 3.2 VA后端服务器不可匿名访问接口

此标题以下的接口均无法匿名访问，后端处理请求前会先检测请求头中的Token，请求头名为`Authorization`，值应为登录获取的Token

#### 3.2.1 用户相关接口（UserController）

##### 3.2.1.1 加载已登录用户（请求方法：`GET`，权限要求：`无`）

此接口用于在Token中提取cid后加载已经登录的用户。此接口为全自动完成，不需要任何用户的操作

请求URL：`localhost:9999/user/loadLoginUser`

必须的`请求头`：

```json
{
    "Authorization": "登录时生成的token"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "登录用户信息查询成功",
    "data": {
        "cid": "100013",
        "userName": "FallenEra",
        "callsign": "1228",
        "password": "我不可能告诉你任何事情！！！！",
        "qq": "850824385",
        "email": "850824385@qq.com",
        "permission": 4,
        "status": "normal",
        "balance": 0,
        "company": "839218643384247",
        "airport": "ZYTL",
        "time": 0,
        "flightCount": 0,
        "job": 8,
        "planeCount": 0
    }
}
```

请求失败的响应： 

```json
{
    "code": 404,
    "message": "用户不存在",
    "data": null
}
```

##### 3.2.1.2查询系统飞行榜前十用户（请求方法：`GET`，权限要求：`无`）

此接口为主页显示系统执行航班数量前十的飞行员基本信息，此接口为全自动，无需任何操作

请求URL：`localhost:9999/user/topTen`

必须的请求参数：`无`

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下信息！",
    "data": ["查询到的航班数量前十的飞行员基本信息"]
}
```

##### 3.2.1.3 查询航空公司的所有飞行员（请求方法： `GET`，权限要求：`无`）

此接口为查询航空公司所有飞行员，并在前端以列表形式展示的接口

请求URL：`localhost:9999/user/getUserByCompany/{company}`

必须的请求参数：

```json
{
    "company": "839218643384247"
}
```

请求成功的响应： 

```json
{
    "code": 200,
    "message": "找到如下数据",
    "data": ["查询到的所有属于该航空公司的人员信息"]
}
```

##### 3.2.1.4 登出（请求方法：`PUT`，权限要求：`无`）

用于用户登出，销毁jwt的token与redis中存储的token，使得用户必须重新登录才可以访问网站

请求URL：`localhost:9999/user/logOut`

必须的`请求头`：

```json
{
    "Authorization": "登录时由后端生成的Token字符串"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "退出登录成功",
    "data": null
}
```

请求失败的响应：

###### 3.2.1.4.1 Token过期（TokenExpiredException）

```json
{
    "code": 401,
    "message": "Token已经过期，请重新登录",
    "data": null
}
```

##### 3.2.1.5 更改用户信息（请求方法：`PUT`，权限要求：`无`）

用于在登录后更改用户的昵称，密码，邮箱

请求URL：`localhost:999/user/changeInfo`

必须的请求参数：

```json
{
    "cid": "100013",
    "userName": "FallenEra",
    "oldPassword": "我不可能告诉你任何事情！！！",
    "newPassword": "我不可能告诉你任何事情！！！",
    "confirmNewPassword": "我不可能告诉你任何事情！！！",
    "email": "85082438@qq.com"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "更改完毕",
    "data": null
}
```

##### 3.2.1.6 查询航空公司飞行员数量（请求方法：`GET`，权限要求：`无`）

用于查询航空公司旗下所有的飞行员人数

请求URL：`localhost:9999/user/getUserCountByCompany/{company}`

必须的请求参数：

```json
{
    "company": "839218643384247"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下数据",
    "data": 5
}
```

##### 3.2.1.7 加载所有用户（请求方法：`GET`，权限要求：`无`）

用于查询所有用户

请求URL：`localhost:9999/user/getAllUser`

必须的请求参数：`无`

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下数据",
    "data": ["查询到的所有用户数据"]
}
```

##### 3.2.1.8 封禁用户（请求方法：`PUT`，权限要求：`4级`）

#### 3.2.2 已计划航班相关接口（PlannedFlightController）

##### 3.2.2.1 新计划航班（请求方法：`POST`，权限要求：`无`）

用于新增已计划航班，并且放入计划队列中，等待执行航班

请求URL：`localhost:9999/plannedFlight/`

必须的请求参数：

```json
{
    "id": "57d54cc56ef94ac3ac3210df3979a6d1",
    "code": "MF8001",
    "departure": "ZYTL",
    "arrival": "ZSHC",
    "pilot": "100013",
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "添加完毕",
    "data": {
        "id": "57d54cc56ef94ac3ac3210df3979a6d1",
        "code": "MF8001",
        "departure": "ZYTL",
        "arrival": "ZSHC",
        "pilot": "100013",
        "plannedTime": "2025-09-14 13:07:07",
        "status": "prepared"
    }
}
```

##### 3.2.2.2 更改已计划航班的状态（请求方法：`PUT`，权限要求：`无`）

用于更改航班状态，如已经起飞，正在爬升，正在巡航等状态

请求URL：`localhost:9999/plannedFlight/{status}/{id}/{cid}`

必须的请求参数：

```json
{
    "status": "taxing",
    "id": "57d54cc56ef94ac3ac3210df3979a6d1",
    "cid": "100013"
}
```

请求成功的相应：

```json
{
    "code": 200,
    "message": "更改完毕",
    "data": null//其实是返回的数据没什么大用处
}
```

##### 3.2.2.3 删除航班计划（请求方法：`DELETE`，权限要求：`无`）

用于将航班状态更改为已完成，删除为假删除

请求URL：`localhost:9999/plannedFlight/{id}/{cid}`

必须的请求参数：

```json
{
    "id": "57d54cc56ef94ac3ac3210df3979a6d1",
    "cid": 100013	
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "状态已更改为已完成",
    "data": null//别问，问就是返回的数据没大处
}
```

##### 3.2.2.4 根据CID查询所有航班计划（请求方法：`GET`，权限要求：`无`）

用于查询单个用户所有计划好的航班，并展现在主页上

请求URL：`localhost:9999/plannedFlight/{cid}`

必须的请求参数：

```json
{
    "cid": "100013"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下数据",
    "data": ["找到的计划航班"]
}
```

请求失败的响应：

```json
{
    "code": 404,
    "message": "未找到符合条件的数据",
    "data": null
}
```

#### 3.2.3 飞机相关接口（PlaneController）

##### 3.2.3.1 根据注册号查询飞机（请求方法：`GET`，权限要求：`无`）

用于查询单架飞机的信息

请求URL： `localhost:9999/plane/code/{code}`

必须的请求参数：

```json
{
    "code": "B-1228"
}
```

请求成功的响应： 

```json
{
    "code": 200,
    "message": "找到注册号为B-1228的飞机",
    "data": {
        "code": "B-1228",
        "owner": "100013",
        "fleet": "B737-800",
        "model": "Boeing 737-800",
        "status": "available",
        "position": "ZYTL",
        "time": 0,
        "durability": 100,
        "company": "839218643384247"
    }
}
```

请求失败的响应：

```json
{
    "code": 404,
    "message": "未找到符合条件的航空器！",
    "data": null
}
```

##### 3.2.3.2 根据CID查询飞机（请求方法：`GET`，权限要求：`无`）

用于查询cid对应的用户名下所有的飞机

请求URL：`localhost:9999/plane/{cid}`

必须的请求参数：

```json
{
    "cid": "100013"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下所属人为100013的航空器",
    "data": ["cid对应的航空器"]
}
```

请求失败的响应：

```json
{
    "code": 404,
    "message": "未找到符合条件的航空器！",
    "data": null
}
```

##### 3.2.3.3 新增航空器（请求方法：`POST`，权限要求：`4级`）

用于给飞行员名下新增航空器

请求URL：`localhost:9999/plane/newPlane`

必须的请求参数：

```json
{
    "code": "B-1228",//后端自动生成，无需前端填写
    "owner": "100013",
    "fleet": "B737-800",
    "model": "Boeing 737-800",
    "position": "ZSAM",
    "company": "839218643384247"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "添加成功",
    "data": null
}
```

##### 3.2.3.4 坠毁飞机（请求方法：`PUT`，权限要求：`无`）

用于在飞机重着陆时将飞机设置为坠毁

请求URL：`localhost:9999/plane/crash/{code}`

必须的请求参数：

```json
{
    "code": "B-1228"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "调整完毕",
    "data": null
}
```

##### 3.2.3.5 为飞机增加飞行时长（请求方法：`PUT`，权限要求：`无`）

用于为飞机增加飞行时长

请求URL：`localhost:9999/plane/addTime/{code}`

必须的请求参数：

```json
{
    "code": "B-1228"
}
```

请求成功的相应：

```json
{
    "code": 200,
    "message": "调整完毕",
    "data": null
}
```

##### 3.2.3.6 选择当前航空公司的飞机（请求方法：`GET`，权限要求：`无`）

按航空公司将飞机分类，并且在业务层按照机型将飞机分类

请求URL：`localhost:9999/plane/getPlaneByCompany/{company}`

必须的请求参数：

```json
{
    "company": "839218643384247"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下飞机",
    "data": ["根据后端分类逻辑分类的所有飞机"]
}
```

##### 3.2.3.7 查询当前航空公司的飞机数量（请求方法：`GET`，权限要求：`无`）

用于查询飞机航司ID为查询航司的飞机数量

请求URL：`localhost:9999/plane/getPlaneCountByCompany/{company}`

必须的请求参数：

```json
{
    "company": "839218643384247"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下飞机",
    "data": 3
}
```

##### 3.2.3.8 查询所有飞机（请求方法：`GET`，权限要求：`无`）

用于查询所有飞机

请求URL：`localhost:9999/plane/getAllPlane`

请求必须参数：`无`

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下数据",
    "data": ["找到的所有飞机数据，以js中array的形式"]
}
```

##### 3.2.3.9 还原飞机状态（请求方法：`PUT`，权限要求：`4级`）

若游戏闪退，飞机会卡在天上，此接口用于回调飞机至起飞前的阶段

请求URL：`localhost:9999/plane/restorePlane/{code}`

必须的请求参数：

```json
{
    "code": "B-1228"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "调整完毕",
    "data": null
}
```

##### 3.2.3.10 将飞机设为公用机（请求方法：`PUT`，权限要求：`4级`）

将当前飞机设置为公用机，通常用于因违规而被封禁的用户的飞机上

请求URL：`localhost:9999/plane/publicPlane/{code}`

请求必须的参数：

```json
{
    "code": "B-1228"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "调整完毕",
    "data": null
}
```

#### 3.2.4 气象报文相关接口（MetarController）

##### 3.2.4.1 获取气象报文（请求方法：`GET`，权限要求：`无`）

用于获取并解析气象报文，展现在主页天气大屏上，气象报文原始数据来源接口为XFlySim数据开放平台接口

请求URL：`localhost:9999/metar/{icao}`

必须的请求参数：

```json
{
    "icao": "ZYTL"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "获取到如下METAR",
    "data": {
        "isCavok": false,
        "rawText": "ZYTL 150630Z 09005MPS 6000 BKN023 27/23 Q1013 NOSIG=",
        "stationId": "ZYTL",
        "observationTime": "06:30 UTC",
        "wind": {
            "isVRB": false,
            "windDirection": 90,
            "speed": 5,
            "gustSpeed": null,
            "speedUnit": "MPS",
            "directionVariation": null
        },
        "visibility": {
            "value": 6000,
            "exceeds10km": false,
            "rawString": "6000"
        },
        "weatherConditions": [],
        "clouds": [
            {
                "coverage": "BKN",
                "altitude": 2300,
                "type": null
            }
        ],
        "temperature": {
            "airTemp": 27,
            "dewPoint": 23
        },
        "pressure": {
            "qnh": 1013,
            "qnhUnit": "hPa"
        },
        "trend": "NOSIG"
    }
}
```

#### 3.2.5 维护记录相关接口（MaintainingRecordController）

##### 3.2.5.1 获取所有维护记录（请求方法：`GET`，权限要求：`无`）

获取所有记录在数据库的维护记录

请求URL：`localhost:9999/maintainingRecord/`

必须的请求参数：`无`

请求成功的响应：

```json
{
    "code": 200,
    "meesage": "找到如下符合条件的数据",
    "data": ["所有的记录在数据库中的维护记录"]
}
```

##### 3.2.5.2 新增维护记录（请求方法：`POST`，权限要求：`3级`）

用于生成飞机维护后的报告

请求URL：`localhost:9999/maintainingRecord/`

必须的请求参数：

```json
{
    "code": "B-1228",
    "type": "Routine maintenance",
    "date": "2025-09-14 13:07:07"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "添加完成",
    "data": null
}
```

##### 3.2.5.3 根据注册号选择飞机的维护记录（请求方法：`GET`，权限要求：`无`）

用于根据飞机注册号调出该飞机的维护记录

请求URL：`localhost:9999/maintainingRecord/{plane}`

必须的请求参数：

```json
{
    "code": "B-1228"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下符合条件的数据",
    "data": ["所有符合条件的维护记录"]
}
```

#### 3.2.6 可执行航班相关接口（FlightPlanController）

##### 3.2.6.1 获取所有的可执行航班（请求方法：`GET`，权限要求：`无`）

用于获取系统中所有可用的可执行航班

请求URL：`localhost:9999/flightPlan/`

必须的请求参数：`无`

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下可用航班信息",
    "data": ["所有的可执行航班"]
}
```

##### 3.2.6.2 根据起降机场搜索可用航班（请求方法：`GET`，权限要求：`无`）

用于获取当前登录用户的航空公司的特定起降机场可用航班

请求URL：`localhost:9999/flightPlan/airport`

必须的请求参数：

`两者至少有一个不为空`

```json
{
    "departure": "ZYTL",
    "arrival": "ZSHC"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下可用航班信息",
    "data": ["所有的符合搜索条件的可执行航班"]
}
```

##### 3.2.6.3 新增可执行航班（请求方法：`POST`，权限要求：`2级`）

用于向服务器中添加新的可执行航班

请求URL：`localhost:9999/flightPlan/`

必须的请求参数：

```json
{
    "id": "60fdf6ee6b1245aa9bdd87058eef4e21",
    "flightCode": "MF8002",
    "departure": "ZSPD",
    "arrival": "ZYTL",
    "route": "ODULO B221 XDX W174 FD W172 TEKAM V68 ORIXA",
    "company": "839218643384247"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "添加完毕",
    "data": null
}
```

###### 3.2.6.4 更新可执行航班（请求方法：`PUT`，权限要求：`2级`）

用于更新可执行航班的信息

请求URL：`localhost:9999/flightPlan/`

必须的请求参数：

```json
{
    "id": "60fdf6ee6b1245aa9bdd87058eef4e21",
    "departure": "ZSPD",
    "arrival": "ZYTL",
    "route": "ODULO B221 XDX W174 FD W172 TEKAM V68 ORIXA",
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "修改完毕",
    "data": null
}
```

##### 3.2.6.5 获取特定航空公司的所有可执行航班（请求方法：`GET`，权限要求：`无`）

用于查找特定航空公司的所有可执行航班

请求URL：`localhost:9999/flightPlan/queryByCompany/{company}`

必须的请求参数：

```json
{
    "company": "839218643384247"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下数据",
    "data": ["找到的所有符合条件的可执行航班信息"]
}
```



#### 3.2.7 飞行记录相关接口（FlightLogController）

##### 3.2.7.1 选择所有的飞行报告（请求方法：`GET`，权限要求：`无`）

用于查询所有的飞行报告

请求URL：`localhost:9999/flightLog/`

必须的请求参数：`无`

请求成功的响应：

``` json
{
    "code": 200,
    "message": "找到如下航班报告信息",
    "data": ["找到的所有的航班信息"]
}
```

##### 3.2.7.2 根据飞行员cid查询飞行报告（请求方法：`GET`，权限要求：`无`）

用于根据CID查询飞行员执行过的飞行报告

请求URL：`localhost:9999/flightLog/pilot/{pilot}`

必须的请求参数： 

```json
{
    "pilot": "100013"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下航班报告信息",
    "data": ["找到的所有的属于当前飞行员的航班信息"]
}
```

##### 3.2.7.3 根据ID查询单个飞行报告（请求方法：`GET`，权限要求：`无`）

用于查询单个飞行报告

请求URL：`localhost:9999/flightLog/id/{id}`

必须的请求参数：

```json
{
    "id": "57d54cc56ef94ac3ac3210df3979a6d1"
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "找到如下航班报告信息",
    "data": {
        "id": "71b0c2ff320840c591695c8fc0a57b22",
        "code": "MF8001",
        "plane": "B-1228",
        "pilot": "100013",
        "departure": "ZYTL",
        "arrival": "ZSHC",
        "route": "KARPI W5 TAO W174 XDX B221 ODULO V2 HSH G455 PK W116 JTN V73 SUPAR",
        "date": "2025-09-14 13:07:07",
        "rate": -118,
        "oil": 3600,
        "status": "verify"
    }
}
```

##### 3.2.7.4 提交飞行报告（请求方法：`GET`，权限要求：`无`）

用于从飞行模拟器提交飞行报告至服务器中

请求URL：`localhost:9999/flightLog/fly`

必须的请求参数：

```json
{
    "id": "71b0c2ff320840c591695c8fc0a57b22",
    "code": "MF8001",
    "plane": "B-1228",
    "pilot": "100013",
    "departure": "ZYTL",
    "arrival": "ZSHC",
    "route": "KARPI W5 TAO W174 XDX B221 ODULO V2 HSH G455 PK W116 JTN V73 SUPAR",,
    "rate": -118,
    "oil": 3600,
}
```

请求成功的响应：

```json
{
    "code": 200,
    "message": "航班报告提交完成，请通知管理员审批",
    "data": "71b0c2ff320840c591695c8fc0a57b22"
}
```

