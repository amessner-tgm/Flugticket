<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Flüge</title>
</head>
<body>
  <center>
    <div class="page-header">
      <h1>Flüge<br \><small>Geben Sie bitte Ihre Flugnummer ein.</small></h1>
    </div>
  </center>
  <center>
    <form class="form-inline" action="output.php" method="get">
      <div class="form-group">
        <div class="input-group">
          <div class="input-group-addon"><i class="glyphicon glyphicon-plane"></i></div>
          <input type="text" class="form-control" name="flugnr" placeholder="Flugnummer">
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Akzeptieren</button>
    </form>
  </center>
</html>
