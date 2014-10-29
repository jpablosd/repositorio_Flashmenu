<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['Adm_nombre']) && isset($_POST['Adm_apellidoPaterno'])) {
    
   
   
    $nombre = $_POST['Adm_nombre'];
    $apellidop = $_POST['Adm_apellidoPaterno'];
    $apellidom = $_POST['Adm_apellidoMaterno'];
    $direccion = $_POST['Adm_direccion'];
    $email = $_POST['Adm_email'];



    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("UPDATE administrador_restaurant SET Adm_nombre = '$nombre', Adm_apellidoPaterno = '$apellidop', Adm_apellidoMaterno = '$apellidom', Adm_direccion = '$direccion', Adm_email = '$email' WHERE idAdministrador_restaurant = 1");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Empleado successfully updated.";
        
        // echoing JSON response
        echo json_encode($response);
    } else {
        
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>
