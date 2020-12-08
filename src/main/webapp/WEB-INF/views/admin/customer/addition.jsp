<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>
							<li class="active">Dashboard</li>
						</ul><!-- /.breadcrumb -->
		
			</div>
		</div>
		<div class="page-content">	
			<div class="page-header">
							<h1>
								Dashboard
								<small>
									<i class="ace-icon fa fa-angle-double-right"></i>overview &amp; stats
								</small>
							</h1>
			</div>
			<!--  
				FORM SEARCH
			 -->
			<div class="row">						
				<div class="widget-box">
					<div class="widget-header">
						<h4 class="widget-title">Tìm Kiếm</h4>

							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>													
							</div>
					</div>
					<div class="widget-body">
						<div class="widget-main">	
						
					<!-- PAGE CONTENT BEGINS -->
					<form:form method="GET" action="" id="customerInfo" modelAttribute="customerModelAttribute">
						      <div class="row">
						      	<div class="col-sm-4">
						      	    <label for="name">Tên Khách Hàng</label>
						            <form:input path="fullName" type="text" class="form-control" name="fullName"/>
						      	</div>
						      	<div class="col-sm-4">
						      	    <label for="name">Phone</label>
						            <form:input path="phone" type="text" class="form-control" name="phone"/>
						      	</div>
						      		<div class="col-sm-4">
						      	    <label for="name">email</label>
						            <form:input path="email" type="text" class="form-control" name="email"/>
						      	</div>
						       
    
    						</div>
   							 <p />
						    <div class="row">
						        <div class="col-xs-12 col-sm-12" style="display: inline;">
						            <form:button id="btnAddCustomer" class="btn btn-sm btn-success">
						                Thêm Khách hàng
						                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
						            </form:button>
						        </div>
						    </div>
					</form:form>
					<!-- PAGE CONTENT ENDS -->
					
						</div>
					</div>		
				</div>
										
			</div>
			<p />
			
			<!-- 
				BUTTON ADD-DELETE
			 -->		
			<div class="row">
						<div class="pull-right">
							<button id="addBuildingInfo" class="btn btn-white btn-info btn-bold" data-placement="bottom" data-toggle="tooltip" title="Thêm tòa nhà">
								<i class="fa fa-plus-circle" aria-hidden="true"></i>		
							</button>
							<button id="deleteBuilding" class="btn btn-white btn-info btn-bold" data-placement="bottom" data-toggle="tooltip" title="Xóa tòa nhà">
								<i class="fa fa-trash" aria-hidden="true"></i>		
							</button>
						</div>
			</div>
			<p />
		</div>
		
	</div>

			

		
		
		<!-- my script-->
		<script src="<c:url value='/admin/assets/js/jquery.2.1.1.min.js' />"></script>	
		<script>
			$('#btnAddCustomer').click(				
				function(event){
					event.preventDefault();
				/* cách lấy checkbox từ table (id table="#stafflist")
				  $('#stafflist').find('tbody input[type=checkbox]:checked')
				*/
				// cách lấy giá trị của một div $("#buildingInfo").val()
				// cách truyền giá trị vào một div $("#buildingInfo").val("string càn truyền")
				//var buildingType=[];//buildingType.push("TANG_TRET");
				var data = {}; //object
				var formData = $("#customerInfo").serializeArray(); // return array [{},{}]
				//console.log(formData);
				$.each(formData,function(index,value){				
					data[value.name]=value.value;				
				});
				// data['buildingType']=buildingType;
				//console.log(data);
					console.log(JSON.stringify(data));
				<c:url var="API_ADMIN_CUSTOMER_SAVE" value="/api/admin/customer/save" />
				$.ajax(
				{
					type:"POST",
					url:"${API_ADMIN_CUSTOMER_SAVE} ",
					data: JSON.stringify(data),
					dataType: "json", //kiểu dữ liệu lấy về
					contentType: "application/json", //kiêu dữ liệu gửi đi
					success: function(response){
						console.log("successfully");
					},
					error: function(response){
						console.log("failed");
					}
				});
				}
			);
			

		</script> 
</body>
</html>