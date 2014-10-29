<?php
//$buscar=$_POST["buscar"];
/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['Rest_nombre']) && isset($_POST['Rest_tipo']) && isset($_POST['Rest_email'])) {
    
    $iddd = $_POST['Administrador_restaurant_idAdministrador_restaurant'];
    $nomRest = $_POST['Rest_nombre'];
    $tipoRest = $_POST['Rest_tipo'];
    $descripRest = $_POST['Rest_descripcion'];
    $caractRest = $_POST['Rest_caracteristicas'];
    $emailRest = $_POST['Rest_email'];
    $direccionRest= $_POST['Rest_direccion'];


    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    
    $result = mysql_query("INSERT INTO restaurant(Administrador_restaurant_idAdministrador_restaurant, Rest_nombre, Rest_tipo, Rest_descripcion, Rest_caracteristicas, Rest_email, Rest_direccion) VALUES('$iddd', '$nomRest' ,'$tipoRest' ,'$descripRest', '$caractRest', '$emailRest', '$direccionRest')");
     
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "restaurant successfully created.";
         
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>