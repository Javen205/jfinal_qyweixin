package com.jfinal.qy.weixin.sdk.menu;

/**
 * @author Javen
 * @Email javenlife@126.com
 * 菜单管理器类 
 */
public class MenuManager  {
	 	/** 
	     * 组装菜单数据 
	     *  
	     * @return 
	     */  
	    public static Menu getMenu() {  
	    	OtherButton btn11 = new OtherButton();  
	        btn11.setName("扫码带提示");  
	        btn11.setType(MenuType.scancode_waitmsg.name());  
	        btn11.setKey("rselfmenu_0_0"); 
	       
	        
	        OtherButton btn12 = new OtherButton();  
	        btn12.setName("扫码推事件");  
	        btn12.setType(MenuType.scancode_push.name());  
	        btn12.setKey("rselfmenu_0_0"); 
	  
	        OtherButton btn21 = new OtherButton();  
	        btn21.setName("系统拍照发图");  
	        btn21.setType(MenuType.pic_sysphoto.name());  
	        btn21.setKey("rselfmenu_1_0");  
	        
	        OtherButton btn22 = new OtherButton();  
	        btn22.setName("拍照或者相册发图");  
	        btn22.setType(MenuType.pic_photo_or_album.name());  
	        btn22.setKey("rselfmenu_1_1");  
	        
	        OtherButton btn23 = new OtherButton();  
	        btn23.setName("微信相册发图");  
	        btn23.setType(MenuType.pic_weixin.name());  
	        btn23.setKey("rselfmenu_1_2");  
	  
	  
	  
	        OtherButton btn31 = new OtherButton();  
	        btn31.setName("发送位置");  
	        btn31.setType(MenuType.location_select.name());  
	        btn31.setKey("rselfmenu_3_0");  
	        
	        ClickButton btn32 = new ClickButton();  
	        btn32.setName("今日歌曲");  
	        btn32.setType(MenuType.click.name());  
	        btn32.setKey("V1001_TODAY_MUSIC"); 
	  
	        ViewButton btn33 = new ViewButton();  
	        btn33.setName("访问百度");  
	        btn33.setType("view");  
	        btn33.setUrl("http://www.baidu.com");  
	  
	        
	        ComButton mainBtn1 = new ComButton();  
	        mainBtn1.setName("扫码");  
	        mainBtn1.setSub_button(new Button[] { btn11, btn12});  
	  
	        ComButton mainBtn2 = new ComButton();  
	        mainBtn2.setName("发图");  
	        mainBtn2.setSub_button(new Button[] { btn23, btn22, btn23 });  
	  
	        ComButton mainBtn3 = new ComButton();  
	        mainBtn3.setName("普通");  
	        mainBtn3.setSub_button(new Button[] { btn31, btn32, btn33 });  
	  
	        Menu menu = new Menu();  
	        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });  
	  
	        return menu;  
	    }
	    
}
