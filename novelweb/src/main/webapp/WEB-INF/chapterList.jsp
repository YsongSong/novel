<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://www.ifengxue.com/ifengxue/favicon.ico">

    <title>章节列表-小说搜搜-免费且无广告的小说阅读网</title>

    <!-- Bootstrap core CSS -->
	 <link href="bootstrap3/css/bootstrap.css" rel="stylesheet">
    <style>
    	.jumbotron {
    		padding-top:10px;
    		padding-bottom:10px;
    	}
    </style>
  </head>
<body>
	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<a href="./">回到搜索页</a>
				</div>
			</div>
		</div>
	</div>
	<div class="container no-table-responsive">
		<table class="table table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr>
					<th colspan="4" style="text-align:center;">
						章节列表
					</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${chapters }" var="chapter" varStatus="status">
					<c:if test="${status.index % 4 == 0}" var="testResult"><tr></c:if>
						<td><a href="./chapterDetail.do?url=${chapter.url }&baseUrl=${baseUrl }">${chapter.title }</a></td>
					<c:if test="${(status.index + 1) % 4 == 0 }"></tr></c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script src="bootstrap3/js/bootstrapjs"></script>
	<script>
	</script>
</body>
</html>