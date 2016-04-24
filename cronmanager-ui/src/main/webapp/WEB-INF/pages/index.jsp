<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Example of Bootstrap 3 List Group Contextual Classes</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style type="text/css">
.bs-example {
	margin: 20px;
}
</style>
<script type="text/javascript">
	$(document).ready(function(){ 
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

		$.fn.enterKey = function (fnc) {
			return this.each(function () {
			    $(this).keypress(function (ev) {
			        var keycode = (ev.keyCode ? ev.keyCode : ev.which);
			        if (keycode == '13') {
			            fnc.call(this, ev);
			        }
			    })
			})
			}
	});
</script>
</head>
<body>
	<div class="row"
		style="height: 100px; border: 1px silver solid; margin: 20px"></div>
	<div class="col-sm-3">
		<ul class="nav nav-pills nav-stacked bs-example" id="results-list">
			<li class="list-group-item list-group-item-success"><a href="#"
				data-toggle="modal" data-target="#create-job">Job Olustur</a></li>
			<li class="list-group-item list-group-item-success"><a href="#">Script
					Yaz</a></li>
			<li class="list-group-item list-group-item-success"><a href="#">Ayarlar</a></li>
		</ul>
	</div>
	<div class="col-sm-9">
		<div id="description">
			<table style="width: 100%" id="jobs-table"
				class="table table-bordered">
				<thead>
					<tr class="row">
						<th>Job Adi</th>
						<th>Calisma Periyodu</th>
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
	<div class="modal fade" id="create-job" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Job Olustur</h4>
				</div>
				<div class="modal-body">
					<form enctype="multipart/form-data" action="/job/create-job"
						method="POST">
						<div class="form-group">
							<label for="job-name" class="form-control-label">Job Adi</label>
							<input type="text" class="form-control" id="job-name"
								name="job-name">
						</div>
						<div class="form-group">
							<label for="period" class="form-control-label">Period</label>
							<textarea class="form-control" id="period" name="period"></textarea>
						</div>
						<div class="form-group">
							<label for="period" class="form-control-label">Calistirilabilir Dosya</label> 
								<input id="executable-file" name="executable-file"
								type="file" class="file-loading">
						</div>
						<div class="form-group">
							<label for="period" class="form-control-label">Harici Dosyalar</label> 
							<input id="external-files" name="external-files"
								type="file" multiple class="file-loading">
						</div>
						<div class="form-group">
							<label for="arg" class="form-control-label">Args</label><br>
							<label for="arg" class="form-control-label"
								id="args-input-error"></label><br>
							<input type="text" id="arg" name="arg" /> <a href="#" id="add-arg">Ekle</a>
							<input type="hidden" id="args" name="args" />
							<div id="args-displayer" class="form-control-label"></div>
						</div>
						<button class="btn btn-default" type="submit">Kaydet</button>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
  	var inputIndex = 0;
  	$('#add-arg').click(function (e) {
		var argCount = $('#arg').val().trim().split(' ');
		if(argCount.length > 1)
		{
			$('#args-input-error').html('Lutfen bosluk birakmayiniz.');
		}
		else
		{
			var divTmp = '<span style="border: 1px silver solid; margin-right:10px">'+$('#arg').val()+'</span>';
			$('#args-displayer').append(divTmp);
			$('#args').val($('#args').val() +" "+ $('#arg').val());
		}
	});
  	$('#add-external-files').click(function() {
				$('#files-div')
						.append('<input type="file" name="files['+ inputIndex +']" />');
	});


  	
  </script>
</body>
</html>