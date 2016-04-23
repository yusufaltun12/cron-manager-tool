<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html lang="en">
<head>
<meta charset="UTF-8">
<title>Example of Bootstrap 3 List Group Contextual Classes</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
    .bs-example{
    	margin: 20px;
    }
</style>
<script type="text/javascript">
	$(document).ready(function(){ 
		$.urlParam = function(name){
		    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
		    if (results==null){
		       return null;
		    }
		    else{
		       return results[1] || 0;
		    }
		}
		
		var jobId=$.urlParam('id');
		$.ajax({
			type : "GET",
			url : "/job/all",
			crossDomain : true,
			processData : true,
			success : function(jobs) 
			{
				var trHTML = '';
		        $.each(jobs, function (i, item) {
		            trHTML += '<tr class="row"><td>' + jobs[i].jobName + '</td><td>' + jobs[i].period + '</td><td>' + jobs[i].executableFilePath + '</td><td> </td><td><a href="/job-detail/?id='+jobs[i].id+'" >Go</a></td></tr>';
		        });
		        $('#jobs-table').append(trHTML);
			},
			error : function(ex, ajaxOptions, thrownError) 
			{
				 
			}
		});
	});
</script>
</head>
<body>
<div class="row" style="height: 100px; border: 1px silver solid; margin: 20px">
</div>
<div class="col-sm-3">
	<ul class="nav nav-pills nav-stacked bs-example" id="results-list">
        <li class="list-group-item list-group-item-success"><a href="#">Job Olustur</a></li>
        <li class="list-group-item list-group-item-success"><a href="#">Script Yaz</a></li>
        <li class="list-group-item list-group-item-success"><a href="#">Ayarlar</a></li>
	</ul>
</div>
<div class="col-sm-9">
	<div id="description">
		<table style="width: 100%" id="jobs-table" class="table table-bordered">
			<thead>
				<tr class="row">
					<th>Job Adi</th>
					<th>Çalisma Periyodu</th>
					<th>Calistirilabilir Dosya</th>
					<th>Son Calismma Zamani</th>
					<th>Git</th>
				</tr>	
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>
</div>

</body>
</html>