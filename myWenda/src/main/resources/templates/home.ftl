<html>
<body>
<a href="https://www.cnblogs.com/JealousGirl/p/6914122.html"  target="_blank">
	基础语法博客(空白页打开链接)
</a>
<pre>
	<#-- 客户端看不到 -->
     <!-- 
   		字符串取值
     -->
    ${value1}
    ${value2!'我是默认值'}-----有值时显示正常值，无值时显示默认值
    ${value3}
	 <!-- 
   		list取值
     -->
    colors: ${colors[2]}
    ${colors?size}
    <#list colors as color >
		 This is Color ${color_index}: ${color}
	</#list>
	 <!-- 
   		map取值
     -->
     <#list map?keys as key>
		<font color="blue">${key}----${map[key]}</font>
	</#list>
	<!-- 
   		 对象的属性和方法
     -->
    User:  ${user.name}
    ${user.description}
    ${user.getDescription()}
    <!-- 
   		 list+对象
     -->
    <#list userList as user>
    	第${user_index+1}名用户是${user.name}
		<#if !user_has_next>
	  		 共有${userList?size}最后一个用户是:${user.name}
	  	</#if>
  	</#list>
	<!-- 
   		定义变量
     -->
    #set($title = "nowcoder_title")
    Title: $!{title}


    Parse: #parse("header.html")
    Include: #include("header.html")

    #macro (render_color, $index, $color)
        Color Render Macro $index, $color
    #end

    #foreach($color in $colors)
        #render_color($foreach.index, $color)
    #end

    #set($hello = "hello")
    #set($helloworld1 = "$!{hello} world")
    $!hello
    $helloworld1
    #set($helloworld2 = '$!{hello} world')
    $helloworld2





</pre>
</body>
</html>