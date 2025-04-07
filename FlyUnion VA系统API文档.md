# FlyUnion VA系统API文档

## 1、前言

此文档为FlyUnion的VA系统涉及到的所有接口的接口文档，在第二章中注明了系统牵扯到的每个实体类的详解与示例数据。在第三章在每个接口的标题中标注了请求方法，必要的请求头与请求体，以及各种可能出现的响应以供开发人员参考。如有遗漏或未在本文档中出现的响应或本文档的错误请联系1228

## 本地部署前须知

本地部署需要jdk版本高于17，node版本高于18

在IDEA运行配置中添加SpringBoot启动项，选择子模块va或sqlite，启动类选择org.flyunion下的xxApplication的Java启动类后点击确定

在IDEA运行配置中添加npm启动项，选择vue项目对应的package.json文件后在脚本中添加dev后点击确定

运行时需要保证nom启动项与SpringBoot启动项均开始运行，启动先后顺序不做限制。

SpringBoot-va项目的端口号为9999，sqlite项目的端口号为9998，vue前端页面的端口号为5173

## 2、实体类信息

### 2.1、VA后端服务器实体类信息

#### 2.1.1、User

实体类数据详解

|    变量名称    |                变量描述                |  变量类型  |
|:----------:|:----------------------------------:|:------:|
|    cid     |              用户唯一标识符               |  int   |
|  userName  |                用户昵称                | string |
|  callsign  |               用户四位呼号               |  int   |
|  password  |          用户密码，使用Bycrpt加密           | string |
| permission | 用户权限等级(1为飞行员、2为签派员、3为正副经理、4为系统管理员) |  int   |
|   email    |               用户电子邮箱               | string |

实体类数据示例

```json
{
    "cid": 100013,
    "userName": "FallenEra",
    "callsign": 1228,
    "password": "*********************",
    "permission": 4,
    "email": "va@flyunion.com"
}
```

#### 2.1.2、Plane

实体类数据详解

|     变量名称      |                       变量描述                       |  变量类型  |
|:-------------:|:------------------------------------------------:|:------:|
|     code      |                      飞机注册号                       | string |
|     owner     |                    飞机所有者：cid                     |  int   |
| aircraftFleet |                       所属机队                       | string |
| airplaneModel |                       飞机型号                       | string |
|    status     | 当前状态（'available可使用l', 'in use使用中', 'crashed已坠毁'） | string |
|     time      |                  当前飞行时长（单位：分钟）                   | string |
|  durability   |              当前耐久（耐久过低时需要对飞机进行维护保养）              | double |

实体类数据示例

```json
{
    "code": "B-1228",
    "owner": 100013,
    "aircraftFleet": "B738",
    "airplaneModel": "Boeing 737-800",
    "status": "available",
    "time": 130,
    "durability": 100
}
```

## 3、接口文档

`所有的数据解释详见章节1《实体类信息》`

### 3.1、VA后端服务器接口文档

当前所有接口文档的域名均为部署在自己电脑上的域名，即`localhost`

`所有的请求与响应所展示的数据均为示例数据！`

#### 3.1.1、利用CID登录（获取token的两种方法其一）（POST）（可匿名访问）

请求url：

```txt
localhost:9999/user/login/cid
```

必须的请求体

```json
{
    "cid": 100013,
    "password": "flyunion100013"
}
```

成功响应

```json
{
	"code": 200,
	"message": "登录成功",
	"data": {
		"token": "由后端Token生成器生成的Token"
	}
}
```

密码错误响应（抛出IncorrectPasswordException）

```json
{
	"code": 401,
	"message": "密码输入不正确！",
	"data": null
}
```

用户不存在响应（抛出UserNotFoundException ）

```json
{
	"code": 404,
	"message": "用户100013不存在！",
	"data": null
}
```

#### 3.1.2、使用邮箱登录（获取token的两种方法其二）（POST）（可匿名访问）

请求url

```txt
localhost:9999/user/login/email
```

必须的请求体

```json
{
    "email": "850824385@qq.com",
    "password": "flyunion100013"
}
```

成功响应同3.1.1

密码错误响应同3.1.1

用户不存在响应（抛出UserNotFoundException ）

```json
{
	"code": 404,
	"message": "用户850824385@qq.com不存在！",
	"data": null
}
```

#### 3.1.3、注册（POST）（可匿名访问）

请求url

```txt
localhost:9999/user/register
```

必须的请求体

 ```json
 {
     "cid": 100013,
     "userName": "FallenEra",
     "callsign": 1228,
     "password": "*********************",
     "email": "va@flyunion.com"
 }
 ```

成功响应

```json
{
    "code": 200,
    "message": "注册成功!",
    "data": {
        "token": "由后端Token生成器生成的Token"
    }
}
```

注册失败响应

```json
{
    "code": 500,
    "message": "注册出现未知错误!",
    "data": null
}
```

CID已存在响应（抛出UserExistException）

```json
{
    "code": 409,
    "message": "注册出现未知错误!",
    "data": "用户100013已经存在，若忘记密码，请进入帮助中心！"
}
```

#### 3.1.4、封禁用户（PUT）（仅系统管理员等级可用）

请求url

```
localhost:9999/user/ADM/banUser/{cid}
```

必须的请求头

```json
{
    "Authorization": "通过登录生成的Token"
}
```

必须的请求参数

```json
{
    "cid": 100013
}
```

成功响应

```json
{
	"code": 200,
	"message": "封禁完毕",
	"data": "100013"
}
```

封禁失败响应

```json
{
	"code": 404,
	"message": "用户100002不存在",
	"data": null
}
```

