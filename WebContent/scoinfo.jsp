<%@page import="entity.Score.Scores"%>
<%@page import="entity.Score"%>
<%@page import="entity.Course"%>
<%@page import="entity.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Students"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>管理系统</title>
	</head>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
			list-style: none;
			text-decoration: none;
		}
		
		.sidebar{
			position: fixed;
			left: -250px;
			width: 250px;
			height: 100%;
			background: #042331;
			transition: left .5s;
		}
		
		.sidebar header{
			font-size: 22px;
			color: white;
			text-align: center;
			line-height: 70px;
			background: #063146;
			user-select: none;
		}
		
		.sidebar ul a{
			display: block;
			height: 100%;
			width: 100%;
			line-height: 65px;
			font-size: 20px;
			color: white;
			padding-left: 40px;
			box-sizing: border-box;
			border-top: 1px solid rgba(255, 255, 255, .1);
			border-bottom: 1px solid black;
			transition: .4s
		}
		
		ul li:hover a{
			padding-left: 50px;
		}
		
		#check{
			position: absolute;
			background: #3498db;
			left: 0px;
			transition: left .5s;
		}
		
		#check:checked ~ .sidebar{
			left: 0;
		}
		
		#check:checked ~ section{
			margin-left: 250px;
		}
		
		#check:checked{
			z-index: 1;
			left: 240px;
			transition: left .5s;
		}
		
		section{
			background: url(resource/img.jpg) no-repeat;
			background-size: cover;
			background-position: center;
			height: 100vh;
			transition: all .5s;
			
		}
		
		.infoBox{
			width: 75%;
			height: 75%;
			padding: 40px;
			position: relative;
			background-color: rgba(0,0,0,0.7);
			top: 50%;
			left:50%;
			color: white;
			transform: translate(-50%, -50%);
			border: 5px solid #ccc;
			border-radius: 20px;
			text-align: center;
		}
	</style>
	<body>
		<input type="checkbox" id="check" checked="checked">
		<div class="sidebar">
			<header>
				<%
					String user = (String)session.getAttribute("username");
					if(user == null){
						out.print("<a href=\"index.jsp\">请登录</a>");
					}else{
						out.print("Hello " + user);
					}
				%>
			</header>
			<ul>
				<li><a href="main.jsp" id="chagne1">个人信息</a></li>
				<li><a href="peoinfo.jsp" id="chagne2">人员管理</a></li>
				<li><a href="couinfo.jsp" id="chagne3">课程管理</a></li>
				<li><a href="scoinfo.jsp" id="chagne4">成绩管理</a></li>
				<li><a href="PageChange?page=5" id="chagne5">课程申报</a></li>
				<li><a href="QuitCheck">退出</a></li>
			</ul>
		</div>
		
		<section>
			<div class="infoBox">
				<h1>课程管理</h1>
				<table class="tablediv" width="100%" height="80%" border="0" frame="hsides" cellpadding="50%">
					<%
						StringBuffer stb = new StringBuffer();
						String users = (String)session.getAttribute("username");
						if(user == null){
							out.print("<a href=\"index.jsp\">请登录</a>");
						}else{
							stb.append("<tr>" +
									"<th>学号</th>" +
									"<th>课程名称</th>"+
									"<th>分数</th></tr>");
							Score score = new Score((String)session.getAttribute("username"));
							ArrayList listC = score.getCourse_score();
							for(int i=0;i<8;i++){
								if(i>=listC.size()) {
									stb.append("<tr><th> </th><th> </th>"+
											"<th> </th></tr>");
								}else {
									Score.Scores sro= (Score.Scores)listC.get(i);
									stb.append("<tr><th>"+score.getStudent().getStudentid()+"</th><th>"+sro.getC().getClassname()+"</th>"+
											"<th>"+sro.getValue()+"</th></tr>");
								}
								
							}
							out.print(stb.toString());
						}
					%>
				</table>
			</div>
		</section>
	</body>
</html>