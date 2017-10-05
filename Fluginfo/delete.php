<?php
$hostname = 'localhost';
$dbname = 'flightdata';
$username = 'ygoerguelue';
$password = '1234';
$pass_id = $_POST['pass_id'];
try {
    $conn = new PDO("mysql:host=$hostname;dbname=$dbname", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $result = $conn -> query("SELECT airline, flightnr FROM passengers WHERE id=$pass_id");
    $row = $result -> fetch();
    $flugnr = $row[0] . $row[1];
    $conn -> query("DELETE FROM passengers WHERE id=$pass_id");
    header("Location: output.php?flugnr=$flugnr");
} catch(Exception $e){
    echo "Error: " . $e -> getMessage();
}
?>
