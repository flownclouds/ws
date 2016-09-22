# wcs
REST api for lianyungang water conservation management

## 使用框架
Spring Boot + Hibernate JPA + Mysql + MongoDB<br>
使用联表查询,实体内设置外键关联<br>

## api 接口说明:
### 1.用户登录
#### description
用户输入用户名和密码进行登录,返回信息包含所有的工程种类信息,用户信息(包含当前县信息和登录token,以后每次请求需要带上作为参数)<br>

#### request:
* url:/user/login
* HTTP 1.1/POST
* 参数:application/x-www-form-urlencoded<br>
 参数名称	    取值	    说明	    required<br>
 userName	String	用户名	TRUE<br>
 password	String	密码	    TRUE<br>

#### response:
        {
	code: 200,						//业务码，200成功
	data: {
		projectCategories: [			//工程种类
					{
						id: 1,
						createTime: "2016-08-15 10:37:12",
						updateTime: "2016-08-15 10:37:11",
						isDelete: 0,
						name: "泵站"
					},…
					],
				user: {			//用户信息
						id: 1,
						createTime: "2016-08-15 10:37:11",
						updateTime: "2016-09-03 14:39:14",
						isDelete: 0,
						name: "weck",		//用户名
						password:””
						salt: "",
						token: “", //重要字段，登录后的每次请求需要
						county: {		//用户所在县的信息
								id: 1,
								createTime: "",
								updateTime: "",
								isDelete: 0,
								name: "nankang",	//县名称
								isActive: 1,
								location: null	//地图信息
								},
						roles: [			//用户的角色信息
							{
								id: 1,
								createTime: "",
								updateTime: "",
								isDelete: 0,
								name: "admin",	//角色名称
								isActive: 1
							},…
							],
						phone: null,
						isActive: 1,
						email: null,
						realName: "weck"		//用户真实姓名
					}
				},
	error: null				//错误信息
}

### 2.获取首页公告
#### description
打开网站首页需要获取首页的公告信息,此时用户未登录,使用分页查询

#### request:
* url:/notification/indexNotifications
* HTTP 1.1/GET
* 参数:application/x-www-form-urlencoded<br>
参数名称	取值	    说明	    required<br>
page	Integer	页码	    TRUE<br>
size	Integer	每页数量	TRUE<br>

#### response:
        {
        	code: 200,
        	data: {
        		notifications: {		//拉取的分页信息
        			content: [		//页面内容
        				{
        					id: 3,
        					createTime: "1970-01-18 01:07:14",	//创建时间
        					updateTime: "2016-09-02 17:01:15",	//更新时间
        					isDelete: 0,
        					title: "收工啦",		//标题
        					county: null,		//当前县信息,网站公告没有
        					content: "红红火火恍恍惚惚哈哈哈",		//内容
        					category: 1,	//公告种类，1位网站公告，2为县级公告
        					user: {		//作者信息
        						id: 1,
        						createTime: "2016-08-15 10:37:11",
        						updateTime: "2016-09-03 14:39:15",
        						isDelete: 0,
        						name: "weck",		//用户名
        						password: "",
        						salt: “",
        						token: "",
        						county: {
        								id: 1,
        								createTime: "",
        								updateTime: "",
        								isDelete: 0,
        								name: "nankang",
        								isActive: 1,
        								location: null
        							},
        						roles: [
        							{
        								id: 1,
        								createTime: "",
        								updateTime: "",
        								isDelete: 0,
        								name: "admin",
        								isActive: 1
        							},…
        							],
        						phone: null,
        						isActive: 1,
        						email: null,
        						realName: "weck"		//真实姓名
        					}
        				},…
        				],
        			last: true,		//是否最后一页
        			totalPages: 1,		//总页数
        			totalElements: 2,		//总记录数
        			size: 2,			//每页数量
        			number: 0,			//当前页码－1
        			sort: [				//排序情况
        				{
        				direction: "DESC",
        				property: "updateTime",
        				ignoreCase: false,
        				nullHandling: "NATIVE",
        				ascending: false
        				}
        				],
        			first: true,			//是否第一页
        			numberOfElements: 2		//当前页面条数
        		}
        	},
        	error: null
        }

### 3.获取登录后的公告
#### description
用户登录网站后跳转到管理界面,此时的公告是县内的公告,采用分页查询,查询请求要带上token

#### request:
* url:/notification/countyNotifications
* HTTP 1.1/GET
* 参数:application/x-www-form-urlencoded<br>
参数名称	取值	说明	required<br>
userId	Integer	用户id	TRUE<br>
token	String	登录后分配的token	TRUE<br>
page	Integer	页码	TRUE<br>
size	Integer	每页数量	TRUE<br>

#### response:
        {
        	code: 200,
        	data: {
        		notifications: {		//拉取的分页信息
        			content: [		//页面内容
        				{
        					id: 3,
        					createTime: "1970-01-18 01:07:14",	//创建时间
        					updateTime: "2016-09-02 17:01:15",	//更新时间
        					isDelete: 0,
        					title: "",		//标题
        					county: {	//当前县信息
        						id: 1,
        						createTime: "2016-08-15 10:37:11",
        						updateTime: "2016-08-15 10:37:11",
        						isDelete: 0,
        						name: "nankang",
        						isActive: 1,
        						location: null
        						},
        					content: "",		//内容
        					category: 2,	//公告种类，1位网站公告，2为县级公告
        					user: {		//作者信息
        						id: 1,
        						createTime: "2016-08-15 10:37:11",
        						updateTime: "2016-09-03 14:39:15",
        						isDelete: 0,
        						name: "weck",		//用户名
        						password: "",
        						salt: “",
        						token: "",
        						county: {
        								id: 1,
        								createTime: "",
        								updateTime: "",
        								isDelete: 0,
        								name: "nankang",
        								isActive: 1,
        								location: null
        							},
        						roles: [
        							{
        								id: 1,
        								createTime: "",
        								updateTime: "",
        								isDelete: 0,
        								name: "admin",
        								isActive: 1
        							},…
        							],
        						phone: null,
        						isActive: 1,
        						email: null,
        						realName: "weck"		//真实姓名
        					}
        				},…
        				],
        			last: true,		//是否最后一页
        			totalPages: 1,		//总页数
        			totalElements: 2,		//总记录数
        			size: 2,			//每页数量
        			number: 0,			//当前页码－1
        			sort: [				//排序情况
        				{
        				direction: "DESC",
        				property: "updateTime",
        				ignoreCase: false,
        				nullHandling: "NATIVE",
        				ascending: false
        				}
        				],
        			first: true,			//是否第一页
        			numberOfElements: 2		//当前页面条数
        		}
        	},
        	error: null
        }

### 4.新增公告
#### description
管理员以上角色可以管理县内公告,超级管理员角色可以管理县内公告和网站公告。新增公告需要输入标题及内容,请求还要带上用户信息

#### request:
* url:/notification/addNotification
* HTTP 1.1/POST
* 参数: application/x-www-form-urlencoded<br>
|参数名称|------|取值|-------|说明|-------------------|required|<br>
|userId|-------|Integer|---|用户id|-----------------|TRUE|<br>
|token|--------|String|----|登录后分配的token|-------|TRUE|<br>
|title|--------|String|----|标题|-------------------|TRUE|<br>
|content|------|String|----|内容|-------------------|TRUE|<br>
|category|-----|Integer|---|1 网站公告，2 县内公告|---|FALSE，DEFAULT 2|<br>
|timeStamp|----|Long|------|客户端时间戳|------------|TRUE|<br>

#### response:
        {
        	code: 200,
        	data: {
        		details: "成功"
        		},
        	error: null
        }

### 5.修改公告
#### description
修改公告与新增公告类似,新增参数notificationId,没有category

#### request:
* url:/notification/updateNotification
* HTTP 1.1/POST
* 参数: application/x-www-form-urlencoded<br>
|参数名称|------|取值|-------|说明|-------------------|required|<br>
|userId|-------|Integer|---|用户id|-----------------|TRUE|<br>
|token|--------|String|----|登录后分配的token|-------|TRUE|<br>
|title|--------|String|----|标题|-------------------|TRUE|<br>
|content|------|String|----|内容|-------------------|TRUE|<br>
|notificationId|-----|Integer|---|当前修改公告的id|---|TRUE|<br>
|timeStamp|----|Long|------|客户端时间戳|------------|TRUE|<br>

#### response:
        {
        	code: 200,
        	data: {
        		details: "成功"
        		},
        	error: null
        }

### 6.删除公告
#### description
删除公告,需要用户id和公告id

#### request:
* url:/notification/deleteNotification
* HTTP 1.1/POST
* 参数: application/x-www-form-urlencoded<br>
|参数名称|------|取值|-------|说明|-------------------|required|<br>
|userId|-------|Integer|---|用户id|-----------------|TRUE|<br>
|token|--------|String|----|登录后分配的token|-------|TRUE|<br>
|notificationId|-----|Integer|---|当前要删除的公告id|---|TRUE|<br>
|timeStamp|----|Long|------|客户端时间戳|------------|TRUE|<br>

#### response:
        {
        	code: 200,
        	data: {
        		details: "成功"
        		},
        	error: null
        }
