#机构上传的CSV文件上传的位置
#eg:   D:\Program Files\apache-tomcat-6.0.36\wtpwebapps\myspring\uploads\ta_organization20140224114450108.csv
orgfile.path = uploads

#设置工程所有list页面默认分页显示的记录数
#set project whole pageSize
project.pageSize = 10

#设置拉动ajmessage服务器的脚本的位置，可以完成启动消息邮局的时候同时拉动ajmessage服务
#ajmessage.script.start=/usr/myspring_soft/openfire/target/openfire/bin/openfire.sh

#添加消息邮局插件上传的位置，即获取插件的位置（这个功能要测试解析plugin文件的位置和上传文件的位置一致）
#plugin.dir=d:/myspring/plugin/

#工程是否开启权限管理功能   	false不开启。 true开启。不设置此项是开启，其它值也是开启
#project.privilegeManager = true

#setup.database是是否有数据库的标志，如果为false则在启动tomcate时，创建数据库，当为true时，不执行任何操作
#database.iP 数据库ip地址
#database.port 数据库端口
#database.userName 数据库用户名 
#@database.passWord 数据库密码

#当project.properties中不配置adtec.accorgsetxmlimpl属性的时候，默认的找本地数据库的帐号，当按照下面在project.properties中配置上时，则切换到外部数据库
#adtec.accorgsetxmlimpl=adtec.util.switchInterface.impl.ExternalInterfaceServiceImpl