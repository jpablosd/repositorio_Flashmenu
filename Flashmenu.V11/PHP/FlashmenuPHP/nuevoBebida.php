<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['Bebidas_nombre']) && isset($_POST['Bebidas_precio'])) {
    
    $id = $_POST['Restaurant_idRestaurant'];
    $nom = $_POST['Bebidas_nombre'];
    $descrip = $_POST['Bebidas_descripcion'];
    $precio = $_POST['Bebidas_precio'];
    

    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO bebidas(Bebidas_nombre, Bebidas_descripcion, Bebidas_precio, Restaurant_idRestaurant) VALUES('$nom' ,'$descrip', '$precio', '$id')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Menu creado exitosamente.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! Ocurrio un erros.";
        
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