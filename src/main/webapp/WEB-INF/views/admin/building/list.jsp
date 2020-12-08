<%@page import="com.laptrinhjavaweb.dto.BuildingDTO"%>
<%@page import="com.laptrinhjavaweb.dto.output.BuildingOutput"%>
<%@page import="enums.DistrictEnum"%>
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
					<form:form method="GET" action="" id="buildingInfo" modelAttribute="buildingModelAttribute">
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
						                <label for="buildingArea">Diện tích từ</label>
						                <form:input path="rentAreaFrom" name="rentAreaFrom" type="number" class="form-control" />
						        </div>
						        <div class="col-xs-12 col-sm-3">
						                <label for="buildingArea">Diện tích đến</label>
						                <form:input path="rentAreaTo" name="rentAreaTo" type="number" class="form-control" />
						        </div>
						        <div class="col-xs-12 col-sm-3">
						                <label for="buildingArea">Giá thuê từ</label>
						                <form:input path="rentPriceFrom" name="rentPriceFrom" type="number" class="form-control" />
						        </div>
						        <div class="col-xs-12 col-sm-3">
						                <label for="buildingArea">Giá thuê đến</label>
						                <form:input path="rentPriceTo" name="rentPriceTo" type="number" class="form-control" />
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
							        <div class="col-xs-12 col-sm-2">
							            <label for="buildingArea">Chọn nhân viên phụ trách</label>
							            <form:select path="staffId" class="chosen-select form-control"  name="staffId" id="form-field-select-3" data-placeholder="Choose a State...">
							                <form:option value="" label="Chọn nhân viên phụ trách" />
							                <form:options items="${staffs }" /> 
							            </form:select>
							        </div>															
       
   							</div>
   							<p />
   							<div class="row">														
						       	<div class="col-xs-12 col-sm-12" style="display: inline;">
				                     <c:forEach var="buildingType" items="${buildingTypes.entrySet()}">
				                      <input type="checkbox"  style="display: inline;" class="ace"  name="buildingTypes" value="${buildingType.getKey() }"/>
				                          <span style="display: inline;" class="lbl">${buildingType.getValue() }</span>
				                     </c:forEach>					
                   				</div>		        											
   							</div>
						    <p />
						    <div class="row">
						        <div class="col-xs-12 col-sm-12" style="display: inline;">
						            <form:button type="submit" class="btn btn-sm btn-success">
						                Tìm Kiếm
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
			
			<!-- 
				SEARCH RESULT
			 -->
			<div class="row">
						<table id="buildingList" class="table table-striped table-bordered table-hover">
							<thead>
									<tr>
										<th>
											<label class="pos-rel">
												<input type="checkbox" class="ace" value="buildingAll" />
												<span class="lbl"></span>
											</label>
										
										</th>
										<th>Tên Tòa Nhà</th>
										<th>Địa chỉ</th>
										<th>Tên quản lý </th>
										<th>Số điện thoại</th>
										<th>Diện tích sàn</th>
										<th>Giá thuê</th>
										<th>Phí dịch vụ</th>
										<th>Thao tác</th>
									</tr>
							</thead>
							<tbody>
								<c:forEach items="${buildingDTOList }" var="buildingDTO">
									<tr>				
									<td>
										<label class="pos-rel">
											<input type="checkbox" class="ace" value="${buildingDTO.getId()}" />
											<span class="lbl"></span>
										</label>

									</td>
									<td> ${buildingDTO.getName() }</td>
									<td>${buildingDTO.street }&nbsp;${buildingDTO.ward }&nbsp;${buildingDTO.district }</td>
									<td>${buildingDTO.managerName }</td>
									<td>${buildingDTO.managerName }</td>
									<td>${buildingDTO.floorArea }</td>
									<td>${buildingDTO.rentPrice }</td>
									<td>${buildingDTO.serviceFee }</td>
									<td>	
										<button class="btn btn-xs btn-info" data-placement="bottom" data-toggle="tooltip" title="Giao tòa nhà" onclick="openAssignmentBuildingModal(${buildingDTO.id})">
											<i class="fa fa-share-square-o" aria-hidden="true"></i>
										</button>
										<button class="btn btn-success btn-xs" data-placement="bottom" data-toggle="tooltip" title="Sửa tòa nhà" onclick="openEditBuildingModal(${buildingDTO.id});">
											<i class="ace-icon fa fa-pencil-square-o bigger-110 icon-only"></i>
										</button>
									</td>
									</tr>		
								</c:forEach>
							</tbody>
						</table>
			</div>	
		</div>
		
	</div>


		<!-- thiết lập pop up-->
		<!-- Modal -->
		<div class="modal fade" id="assignmentBuildingModal" role="dialog">
			<div class="modal-dialog">
			
			  <!-- Modal content-->
			  <div class="modal-content">
				<div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal">&times;</button>
				  <h4 class="modal-title">Danh sách nhân viên</h4>
				</div>
				<div class="modal-body">
				  <!-- -content -->
				  
				  <table id="employeeTable" class="table table-striped table-bordered table-hover">
					<thead>
						<th>Chọn nhân viên</th>
						<th>Tên nhân viên</th>
					</thead>
					<tbody>
						<tr>
							<td>
								<label class="pos-rel">
									<input type="checkbox" class="ace"  value="thanhlam"/>
									<span class="lbl"></span>
								</label>
							</td>
							<td>
								Thanh Lam
							</td>
						</tr>
					</tbody>
				  </table>
				  
				  
				</div>
				<div class="modal-footer">
				<button id="assignmentBuilding" type="button" class="btn btn-default" data-dismiss="modal">Giao tòa nhà</button>
				  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			  </div>
			  
			</div>
		 </div>
		 
		  <div class="modal fade" id="editBuildingModal" role="dialog">
			<div class="modal-dialog">
			
			  <!-- Modal content-->
			  <div class="modal-content">
				<div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal">&times;</button>
				  <h4 class="modal-title">Cập nhật thông tin tòa nhà</h4>
				</div>
				<div class="modal-body">
				  <!-- -content -->
				  
				  <table id="employeeTable" class="table table-striped table-bordered table-hover">
					<thead>
						<th>Chọn nhân viên</th>
						<th>Tên nhân viên</th>
					</thead>
					<tbody>
						<tr>
							<td>
								<label class="pos-rel">
									<input type="checkbox" class="ace"  value="thanhlam"/>
									<span class="lbl"></span>
								</label>
							</td>
							<td>
								Thanh Lam
							</td>
						</tr>
					</tbody>
				  </table>
				  
				  
				</div>
				<div class="modal-footer">
				<button id="assignmentBuilding" type="button" class="btn btn-default" data-dismiss="modal">Cập Nhật</button>
				  <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			  </div>
			  
			</div>
		 </div>

		
		
		<!-- my script-->
		<script src="<c:url value='/admin/assets/js/jquery.2.1.1.min.js' />"></script>
		<script type="text/javascript">
			<% 
			String stringBuildingType = "";
				if(((BuildingOutput)request.getAttribute("buildingModelAttribute")).getBuildingTypes() != null){
				for(String buildingType : ((BuildingOutput)request.getAttribute("buildingModelAttribute")).getBuildingTypes()){
					if(stringBuildingType.equals("")){stringBuildingType += "'"+buildingType+"'";}
					else {stringBuildingType += ",'"+buildingType+"'";}
					}
				stringBuildingType = "["+stringBuildingType+"];";
			}else{
				stringBuildingType ="[]";
			}
			
			%>
			var buildingTypesChecked = <%out.print(stringBuildingType);%>
			if(buildingTypesChecked.length > 0){
			for(var i=0;i<buildingTypesChecked.length;i++){
				document.querySelector("input[value="+buildingTypesChecked[i]+"]").checked=true;
			}
			}
		</script>
		<script>
			$('#deleteBuilding').click(
				function(){
				//console.log($('#buildingList').find('tbody input[type=checkbox]:checked').map(function(){return $(this).val()}).get());
				var rawData = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function(){return $(this).val()}).get();
				var data = [];
				for(var i = 0;i<rawData.length;i++){
					data.push(parseInt(rawData[i]));
				}
				$.ajax(
						{
							type:"DELETE",
							<c:url var="API_ADMIN_BUILDING_DELETE" value="/api/admin/building-delete"/>
							url:"${API_ADMIN_BUILDING_DELETE}",
							data: JSON.stringify(data),
							dataType: "json", //kiểu dữ liệu lấy về
							contentType: "application/json", //kiêu dữ liệu gửi đi
							success: function(response){
								location.reload();
								console.log("successfully");
							},
							error: function(response){
								console.log("failed");
							}
						});
				}
				);
			$('#addBuildingInfo').click(				
				function(){
				/* cách lấy checkbox từ table (id table="#stafflist")
				  $('#stafflist').find('tbody input[type=checkbox]:checked')
				*/
				// cách lấy giá trị của một div $("#buildingInfo").val()
				// cách truyền giá trị vào một div $("#buildingInfo").val("string càn truyền")
				var buildingType=[];//buildingType.push("TANG_TRET");
				var data = {}; //object
				var formData = $("#buildingInfo").serializeArray(); // return array [{},{}]
				//console.log(formData);
				$.each(formData,function(index,value){
					if(value.name == "buildingType"){
					 buildingType.push(value.value);
					}else{
					data[value.name]=value.value;
					}
				});
				 data['buildingType']=buildingType;
				//console.log(data);
					console.log(JSON.stringify(data));
				/*$.ajax(
				{
					type:"POST",
					url:"http://localhost",
					data: JSON.stringify(data),
					dataType: "json", kiểu dữ liệu lấy về
					contentType: "application/json", kiêu dữ liệu gửi đi
					success: function(response){
						console.log("successfully");
					},
					error: function(response){
						console.log("failed");
					}
				});*/
				}
			);
			$('#assignmentBuilding').click(
			function(){
			var strListStaffChecked = $('#employeeTable').find('tbody input[type=checkbox]:checked').map(function(){return $(this).val()}).get();
			var listStaffChecked =[];
			for(var i=0;i<strListStaffChecked.length;i++){
				listStaffChecked.push(parseInt(strListStaffChecked[i]));
			}
			var assignmentBuildingDTO = {
					'buildingId':parseInt(document.getElementById("assignmentBuildingModal").getAttribute("value")),
					'staffId':listStaffChecked
			};
			console.log(assignmentBuildingDTO);
			$.ajax(
					{
						type:"PUT",
						<c:url var="API_ADMIN_BUILDING_ASSIGNMENT" value="/api/admin/building/assignment-staff" />
						url:"${API_ADMIN_BUILDING_ASSIGNMENT }",
						data: JSON.stringify(assignmentBuildingDTO),
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
			
	
			function openAssignmentBuildingModal(buildingId){
				openModelAssignmentBuilding(buildingId);
				
			}
			function openModelAssignmentBuilding(buildingId){
				//console.log(buildingId);
				<c:url var="API_ADMIN_GETSTAFFS" value="/api/admin/staffs" />
				$.ajax(
						{
							type:"GET",
							url:"${API_ADMIN_GETSTAFFS }/"+buildingId,
							//data: JSON.stringify(data),
							dataType: "json", //kiểu dữ liệu lấy về
							//contentType: "application/json", //kiêu dữ liệu gửi đi
							success: function(response){
								console.log(response);
								var content = '';
								for(var i=0;i<response.length;i++){
									if(response[i].checked == true){
										content += '<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  value="'+response[i].id+'" checked/><span class="lbl"></span></label></td>';
										content += '<td>'+response[i].name+'</td></tr>';
									}else{
										content += '<tr><td><label class="pos-rel"><input type="checkbox" class="ace"  value="'+response[i].id+'"/><span class="lbl"></span></label></td>';
										content += '<td>'+response[i].name+'</td></tr>';
									}
								}
								
								document.querySelector("#employeeTable tbody").innerHTML=content;
								assignmentBuildingModal
								document.getElementById("assignmentBuildingModal").setAttribute("value",buildingId);
							},
							error: function(response){
								console.log("failed");
							}
						});
				$('#assignmentBuildingModal').modal();
			}

			function openEditBuildingModal(buildingId){
				openModelEditBuilding(buildingId);
			}
			function openModelEditBuilding(buildingId){
				//$('#editBuildingModal').modal();
				<c:url var="URL_ADMIN_EDITBUILDING" value="/admin/building-edit" />
				location.replace("${URL_ADMIN_EDITBUILDING }/"+buildingId)
			}


		</script> 
</body>
</html>