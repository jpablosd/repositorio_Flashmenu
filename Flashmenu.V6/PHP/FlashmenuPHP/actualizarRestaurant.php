<?php

/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['Rest_nombre'])) {
    
   
   
    //$nombre = $_POST['Rest_nombre'];
    $tipo = $_POST['Rest_tipo'];
    $descripcion = $_POST['Rest_descripcion'];
    $caracteristicas = $_POST['Rest_caracteristicas'];
    $email = $_POST['Rest_email'];
    $direccion = $_POST['Rest_direccion'];



    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched pid
    $result = mysql_query("UPDATE restaurant SET Rest_tipo = '$tipo', Rest_descripcion = '$descripcion', Rest_caracteristicas = '$caracteristicas', Rest_email = '$email', Rest_direccion = '$direccion' WHERE Administrador_restaurant_idAdministrador_restaurant = 1");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "restaurant successfully updated.";
        
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
