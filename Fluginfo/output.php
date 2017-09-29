<html>
<head>
	<title>Flugbuchung</title>
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

		$stmt -> execute();

		if($stmt -> rowCount() > 0){
	    $flight = $stmt -> fetch();
	    $start_apc = $flight[3];
	    $dest_apc = $flight[5];
		}


		








    }catch(Exception $e){

			$conn = null;
    echo "Connection failed: " . $e->getMessage();
    }
		//$abfrage = "SELECT airlines FROM flightdata WHERE country LIKE \"AT\"";










?>
</body>
</html>
