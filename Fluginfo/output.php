<html>
<head>
	<title>Flugbuchung</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<?php
$servername = "localhost";
$dbname = "flightdata";
$username = "ygoerguelue";
$password = "1234";

try {
    $conn = new PDO("mysql:host=$servername;dbname=$dbname", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);


		$stmt = $conn->prepare("SELECT * FROM flights WHERE airline LIKE :airline AND flightnr=:flightnr");
		$stmt->bindParam("airline",$airline);
		$stmt->bindParam("flightnr",$flightnr);

		$input = $_GET['flugnr'];
		$airline = substr($input,0,2);
		$flightnr = substr($input,2,5);

		$airline = strtoupper($airline);

		$stmt -> execute();

		if($stmt -> rowCount() > 0){
	    $flight = $stmt -> fetch();
	    $start_apc = $flight[3];
	    $dest_apc = $flight[5];
		}
		?>
		<div class="jumbotron text-center">
			<h2>Flight-Informations</h2>
			<?php
				echo '<p>';
				echo 'Flight ';
				echo $airline;
				echo $flightnr;
				echo '</p>';
			?>
		</div>




		<div class="row">
      <div class="col-xs-5">
        <div class="panel panel-default">
          <div class="panel-heading">
            <h2 class="panel-title">Depature</h2>
          </div>
          <div class="panel-body">
            <h4>
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										Country:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT country FROM airports WHERE airportcode LIKE '$start_apc'");
								    $row = $result -> fetch();
								    $start_cc = $row[0];
								    $result = $conn -> query("SELECT name FROM countries WHERE code LIKE '$start_cc'");
								    $row = $result -> fetch();
								    echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-2">
										City:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT city FROM airports WHERE airportcode LIKE '$start_apc'");
										$row = $result -> fetch();
										echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-2">
										Airport:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT name FROM airports WHERE airportcode LIKE '$start_apc'");
										$row = $result -> fetch();
										echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-2">
										Departure Time:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT departure_time FROM flights WHERE flightnr LIKE '$flightnr'");
										$row = $result -> fetch();
										echo $row[0];
										?>
									</div>
								</div>
							</div>
            </h4>
          </div>
        </div>
      </div>
      <div class="col-xs-5 col-xs-offset-2">
        <div class="panel panel-default  text-right">
          <div class="panel-heading">
            <h4 class="panel-title">Arrival</h4>
          </div>
          <div class="panel-body">
            <h4>
							<div class="container">
								<div class="row">
									<div class="col-lg-4">
										Country:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT country FROM airports WHERE airportcode LIKE '$dest_apc'");
								    $row = $result -> fetch();
								    $start_cc = $row[0];
								    $result = $conn -> query("SELECT name FROM countries WHERE code LIKE '$start_cc'");
								    $row = $result -> fetch();
								    echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4">
										City:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT city FROM airports WHERE airportcode LIKE '$dest_apc'");
										$row = $result -> fetch();
										echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4">
										Airport:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT name FROM airports WHERE airportcode LIKE '$dest_apc'");
										$row = $result -> fetch();
										echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-4">
										Departure Time:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT destination_time FROM flights WHERE flightnr LIKE '$flightnr'");
										$row = $result -> fetch();
										echo $row[0];
										?>
									</div>
								</div>
							</div>
						</h4>
          </div>
        </div>
      </div>
    </div>


		<table class="table table-striped">
			<caption>Passengers</caption>
				<th>
					Firstname
				</th>
				<th>
					Lastname
				</th>
				<th colspan="2">
					Seat
				</th>


		<?php
		$result = $conn -> query("SELECT firstname,lastname,rownr,seatposition FROM passengers WHERE flightnr LIKE '$flightnr' ORDER BY 3 ASC, 4 ASC");
		while($row = $result -> fetch()){
			echo '<tr><td>'.$row[0].'</td>';
			echo '<td>'.$row[1].'</td>';
			echo '<td>'.$row[2].$row[3].'</td>';
			echo '</tr>';
			echo '<br>';
		}
		?><br \>
		</table>






		<?php
    }catch(Exception $e){

			$conn = null;
    echo "Connection failed: " . $e->getMessage();
    }











?>
</body>
</html>
