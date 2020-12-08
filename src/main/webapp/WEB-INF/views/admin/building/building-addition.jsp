<%@page import="com.laptrinhjavaweb.dto.input.BuildingInput"%>
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
						<h4 class="widget-title">Thêm Tòa Nhà</h4>

							<div class="widget-toolbar">
								<a href="#" data-action="collapse">
									<i class="ace-icon fa fa-chevron-up"></i>
								</a>													
							</div>
					</div>
					<div class="widget-body">
						<div class="widget-main">	
						
					<!-- PAGE CONTENT BEGINS -->
					<form:form method="POST" action="" id="buildingInfo" modelAttribute="buildingModelAttribute">
						      <div class="row">
						        <div class="col-xs-12 col-sm-6">															
						            <label for="name">Tên Tòa Nhà</label>
						            <form:input path="name" type="text" class="form-control" name="name"/>
						        </div>
						        <div class="col-xs-12 col-sm-6">
						            <label for="floorArea">Diện Tích Sàn</label>
						            <form:input path="floorArea" type="number" class="form-control" name="floorArea"/>
						        </div>
    
    						</div>
   							 <p />
   							<div class="row">
						        <div class="col-xs-12 col-sm-4">	
						            <label for="name">Quận</label>
						            <form:select path="district" name="district" style="width:45%" class="chosen-select form-control" id="form-field-select-3" data-placeholder="Choose a State...">
						                <form:option value="" label="Vui Lòng chọn quận" />											
						               	<form:options items="${districts }" />
						            </form:select>
						    
						        </div>
						        <div class="col-xs-12 col-sm-4">
						            <label for="buildingArea">Phường</label>
						            <form:input path="ward" name="ward" type="number" class="form-control" />
						        </div>
						        <div class="col-xs-12 col-sm-4">
						            <label for="buildingArea">Đường</label>
						            <form:input path="street" name="street" type="text" class="form-control" />
						        </div>
					   		</div>
					    	<p />
					    	<div class="row">
						        <div class="col-xs-12 col-sm-4">
					                <label for="buildingArea">Số tầng hầm</label>
					                <form:input path="numberOfBasement" name="numberOfBasement" type="number" class="form-control" />
						        </div>
						        <div class="col-xs-12 col-sm-4">
						                <label for="buildingArea">Hướng</label>
						                <form:input path="direction" name="direction" type="text" class="form-control" />
						        </div>
						        <div class="col-xs-12 col-sm-4">
						                <label for="buildingArea">Hạng</label>
						                <form:input path="level" name="level" type="number" class="form-control" />
						        </div>    
    						</div>
    						<p />
    						<div class="row">
	    						<div class="col-xs-12 col-sm-3">
	    							 <label for="buildingArea">Diện Tích</label>
	    							 <form:input path="rentArea" name="rentArea" type="text" class="form-control" placeholder="100,300,500" />
	    						</div> 
	    						<div class="col-xs-12 col-sm-3">
	    							 <label for="rentPrice">Giá Thuê : </label>
	    							 <form:input path="rentPrice" name="rentPrice" type="text" class="form-control" placeholder="" />
	    						</div>   
   							 </div>
   							 <p />
  							 <div class="row">
							        <div class="col-xs-12 col-sm-4">
							                <label for="buildingArea">Tên quản lý</label>
							                <form:input path="managerName" class="form-control" />
							        </div>
							        <div class="col-xs-12 col-sm-4">
							                <label for="buildingArea">Điện thoại quản lý</label>
							                <form:input path="managerPhone" type="number" class="form-control" />
							        </div>
													
       
   							</div>
   							<p />
   							<div class="row">														
						       	<div class="col-xs-12 col-sm-12" style="display: inline;">
				                     <c:forEach var="buildingType" items="${buildingTypes.entrySet()}">
				                      <input type="checkbox"  style="display: inline;" class="ace"  name="buildingType" value="${buildingType.getKey() }"/>
				                          <span style="display: inline;" class="lbl">${buildingType.getValue() }</span>
				                     </c:forEach>					
                   				</div>		        											
   							</div>
						    <p />
						    <div class="row">
						        <div class="col-xs-12 col-sm-12" style="display: inline;">
						            <form:button id="addBuildingInfo" class="btn btn-sm btn-success">
						                Thêm Tòa Nhà
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
		</div>	
	</div>
	
	

	<script src="<c:url value='/admin/assets/js/jquery.2.1.1.min.js' />"></script>
	<script>
	$('#addBuildingInfo').click(				
			function(event){
				event.preventDefault();
				
			/* cách lấy checkbox từ table (id table="#stafflist")
			  $('#stafflist').find('tbody input[type=checkbox]:checked')
			*/
			// cách lấy giá trị của một div $("#buildingInfo").val()
			// cách truyền giá trị vào một div $("#buildingInfo").val("string càn truyền")
			var buildingType=[];//buildingType.push("TANG_TRET");
			var data = {}; //object
			var formData = $("#buildingInfo").serializeArray(); // return array [{},{}]
			console.log(formData);
			//console.log(formData);
			$.each(formData,function(index,value){
				if(value.name == "buildingType"){
				 buildingType.push(value.value);
				}else{
				data[value.name]=value.value;
				}
			});
			 data['buildingTypes']=buildingType;
			//console.log(data);
			//console.log(JSON.stringify(data));
			<c:url var="API_ADMIN_BUILDING_SAVE" value="/api/admin/building/save" />
			$.ajax(
			{
				type:"POST",
				url:"${API_ADMIN_BUILDING_SAVE }",
				data: JSON.stringify(data),
				dataType: "json", //kiểu dữ liệu lấy về
				contentType: "application/json", //kiêu dữ liệu gửi đi
				success: function(response){
					console.log(response);
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