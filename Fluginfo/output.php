<html>
<head>
	<title>Flugbuchung</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
<?php

include 'accessability.php';
// Da alles im Try ist, wird sofort ein ERROR ausgegeben sobald was schief läuft
try {
	// Stellt die Verbindung zur Datenbank her
    $conn = new PDO("$dbtype:host=$servername;dbname=$dbname", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
		// Um sich vor bösen Attacken zu schützen die die Datenbank bomberdieren wollen
		$stmt = $conn->prepare("SELECT * FROM flights WHERE airline LIKE :airline AND flightnr=:flightnr");
		$stmt->bindParam("airline",$airline);
		$stmt->bindParam("flightnr",$flightnr);
		//Teilt den input in 2 Variablen um
		$input = $_GET['flugnr'];
		$airline = substr($input,0,2);
		$flightnr = substr($input,2,5);
		//Macht aus den ersten beiden Buchstaben in der Flugnummer Großbuchstaben für eine schönere Ausgabe
		$airline = strtoupper($airline);
		$stmt -> execute();
		// Alles im if da bei einer Falscheingabe sofort eine Fehlermeldung geworfen wird
		// Da bei einer Falscheingabe keine row herauskommt, wurde es mittels diesem if gelöst
		if($stmt -> rowCount() > 0){
	    $flight = $stmt -> fetch();
	    $start_apc = $flight[3];
	    $dest_apc = $flight[5];
		?>
		<!--Zeigt an welche Flugnummer man soeben besichtigt-->
		<div class="jumbotron text-center">
			<h2>Flug-Informationen</h2>
			<?php
				echo '<p>';
				echo 'Flug ';
				echo $airline;
				echo $flightnr;
				echo '</p>';
			?>
		</div>


		<!--Zeigt Daten zum Abflug an-->
		<div class="container">
		<div class="row">
      <div class="col-xs-5">
        <div class="panel panel-primary">
          <div class="panel-heading">
            <h2 class="panel-title">Abflug</h2>
          </div>
          <div class="panel-body">
            <h4>
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										Land:
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
										Stadt:
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
										Flughafen:
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
										Abflugszeitpunkt:
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
			<!--Wieder zur Flugnummer Eingabe zurück-->
			<div class="col-xs-2">
				<center><a href="input.php"><button type="submit" class="btn btn-primary">Zurück</button></a></center>
			</div>
			<!--Zeigt Daten zur Ankunft an-->
      <div class="col-xs-5">
        <div class="panel panel-primary  text-right">
          <div class="panel-heading">
            <h4 class="panel-title">Ankunft</h4>
          </div>
          <div class="panel-body">
            <h4>
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										Land:
									</div>
									<div class="col-lg-2 col-offset-lg-2">
										<?php
										$result = $conn -> query("SELECT country FROM airports WHERE airportcode LIKE '$dest_apc'");
								    $row = $result -> fetch();
								    $dest_cc = $row[0];
								    $result = $conn -> query("SELECT name FROM countries WHERE code LIKE '$dest_cc'");
								    $row = $result -> fetch();
								    echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-2">
										Stadt:
									</div>
									<div class="col-lg-2">
										<?php
										$result = $conn -> query("SELECT city FROM airports WHERE airportcode LIKE '$dest_apc'");
										$row = $result -> fetch();
										echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-2">
										Flughafen:
									</div>
									<div class="col-lg-2">
										<?php
										$result = $conn -> query("SELECT name FROM airports WHERE airportcode LIKE '$dest_apc'");
										$row = $result -> fetch();
										echo $row[0];
										?><br \>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-2">
										Ankunftszeitpunkt:
									</div>
									<div class="col-lg-2 ">
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
		<!--Head der Tabelle-->
		<center><h2>Passagiere</h2></center>
		<div class="row">
			<div class="col-lg-12 col-lg-offset-2">
				<table align="center" class="table table-striped table-bordered ">

						<th>
							Vorname
						</th>
						<th>
							Nachname
						</th>
						<th>
							Sitz
						</th>
						<th>
							Entfernen
						</th>

				<?php
				//Gibt die Daten der Passagiere aus
				$result = $conn -> query("SELECT firstname,lastname,rownr,seatposition,id FROM passengers WHERE flightnr LIKE '$flightnr' ORDER BY 3 ASC, 4 ASC");
				while($row = $result -> fetch()){
					echo '<form method="POST" action="delete.php">';
					echo '<tr><td>'.$row[0].'</td>';
					echo '<td>'.$row[1].'</td>';
					echo '<td>'.$row[2].$row[3].'</td>';
					echo '<td><button type="submit" class="btn btn-danger">Entfernen</button></td>';
					echo '<td style="visibility:hidden;"><input type="text" name="pass_id" value="'.$row[4].'"></td>';
					echo '</tr>';
					echo '</form>';
				}
				?>
			</table>
		</div>
	</div>
	</div>
		<?php
		}else{
			// Wird oben eine ungültige Flugnummer eingegeben, kommt diese Fehlermeldung raus
			echo "<center>
		    <div class='page-header'>
		      <h1>FEHLER<br \><small>Bitte eine gültige Flugnummer eingeben!</small></h1>
		    </div>
		  </center>";
			echo "<center><a href='input.php'><button type='submit' class='btn btn-warning'>Zurück zur Flugnummereingabe</button></a></center>";

		}


    }catch(Exception $e){
			$conn = null;
			//Kann keine Connetion zur Datenbank hergestellt werden, wird diese Fehlermeldung angezeigt
    echo "Verbindung fehlgeschlagen: " . $e->getMessage();
    }
?>
</body>
</html>
