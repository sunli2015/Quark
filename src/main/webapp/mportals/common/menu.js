﻿function onNodeSelect(id){
	switch(id){
		case "logout":
			Ext.MessageBox.confirm('提示', '您确定退出系统么?', logout);
			break;
		case "index":
			Ext.get('application-info-iframe').dom.src=CONTEXT_PATH+'/index.jsp';
			break;
		case "admin_user":
			//Ext.get('application-info-iframe').dom.src=CONTEXT_PATH+'/admin/userIndex.action?cacheflag='+new Date().getTime();
			var frameUrl = CONTEXT_PATH+'/admin/userIndex.action?cacheflag='+new Date().getTime();
			updateTabFrame('人事权限管理', frameUrl, 'application-admin_user-iframe');
			ifactivetab = false;
			break;
		case "admin_res":
			//Ext.get('application-info-iframe').dom.src=CONTEXT_PATH+'/admin/resourceIndex.action?cacheflag='+new Date().getTime();
			var frameUrl = CONTEXT_PATH+'/admin/resourceIndex.action?cacheflag='+new Date().getTime();
			updateTabFrame('系统资源管理', frameUrl, 'application-admin_res-iframe');
			ifactivetab = false;
			break;
		case "admin_role":
			//Ext.get('application-info-iframe').dom.src=CONTEXT_PATH+'/admin/role!index.action?cacheflag='+new Date().getTime();
			var frameUrl = CONTEXT_PATH+'/admin/role!index.action?cacheflag='+new Date().getTime();
			updateTabFrame('角色管理', frameUrl, 'application-admin_role-iframe');
			ifactivetab = false;
			break;
		case "cms9":
			Ext.get('application-info-iframe').dom.src=CONTEXT_PATH+'/cms/adminportal.do?cacheflag='+new Date().getTime();
			break;				
		case "pwd":
			contentPanel.setActiveTab(0); 
			Ext.get('application-info-iframe').dom.src=CONTEXT_PATH+'/pwd/pwd.action'
			break;		
		case "cms":
			var frameUrl = CONTEXT_PATH+'/cms/contentIndex.action?cacheflag='+new Date().getTime();
			updateTabFrame('通知管理', frameUrl, 'application-cms-iframe');
			ifactivetab = false;
			break;
		case "order":
			var frameUrl = CONTEXT_PATH+'/order/order!index.action?cacheflag='+new Date().getTime();
			updateTabFrame('订单管理', frameUrl, 'application-order-iframe');
			ifactivetab = false;
			break;
		case "yikatong":
			var frameUrl = CONTEXT_PATH+'/yikatong/user!index.action?cacheflag='+new Date().getTime();
			updateTabFrame('一卡通管理', frameUrl, 'application-yikatong-iframe');
			ifactivetab = false;
			break;
		case "cms2":
			var frameUrl = CONTEXT_PATH+'/cms/search!partSearch.do';
			updateTabFrame('全文检索', frameUrl, 'application-partSearch-iframe');
			ifactivetab = false;
			break;
		default:
		;
	}
	
}