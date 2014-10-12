<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>${readResult.title}</title>
      <link href="/htdocs/img/logo.ico" rel="Shortcut Icon">
      <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
      <link rel="stylesheet" href="/htdocs/css/biaoxin.css">
      <script type="text/javascript">
         var browser={    
         versions:function(){            
         var u = navigator.userAgent, app = navigator.appVersion;            
         return {                
         trident: u.indexOf('Trident') > -1, //IE内核                
         presto: u.indexOf('Presto') > -1, //opera内核                
         webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核                
         gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核                
         mobile: !!u.match(/AppleWebKit.*Mobile.*/)||!!u.match(/AppleWebKit/), //是否为移动终端                
         ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端                
         android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器                
         iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器                
         iPad: u.indexOf('iPad') > -1, //是否iPad                
         webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部            
         };
         }()
         } 
         function checkBrowser(){
         var android = browser.versions.android;
         var ios = browser.versions.ios;
         if (!ios) {
         var iosBtn = document.getElementById("ios");
         iosBtn.style.display = "none";
         }
         if (!android) {
         var androidBtn = document.getElementById("android");
         androidBtn.style.display = "none";
         }
         }
      </script>
   </head>
   <body id="activity-detail">
      <div class="page-headerinfo">
         <div class="page-logo"></div>
      </div>
      <div class="page-bizinfo">
         <div class="header">
            <h1 id="activity-name">${readResult.title}</h1>
         </div>
      </div>
      <div id="page-content" class="page-content">
         <div class="text">
            ${readResult.content}
         </div>
         <div class="page-toolbar">
         		<p>想要了解更多标讯信息,欢迎使用标信</p>
            <div id="ios" class="ios-download" style="text-align:center">
               <a class="pure-button" href="https://itunes.apple.com/us/app/biao-xin/id871157802?ls=1&mt=8"></a>
            </div>
            <div id="android" class="android-download" style="text-align:center">
               <a class="pure-button pure-button-active" href="http://www.wandoujia.com/apps/com.biaoxin.app.ui"></a>
            </div>
            <p style="text-align:center">&copy; 2014 -  Zhongbiao Inc. All Rights Reserved</p>
         </div>
      </div>
   </body>
   <script>
      checkBrowser();
   </script>
</html>
