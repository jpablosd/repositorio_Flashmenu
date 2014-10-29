<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['Adm_nombre']) && isset($_POST['Adm_apellidoPaterno']) && isset($_POST['Adm_apellidoMaterno'])) {
    
    //$idAdm = $_POST['idAdministrador_restaurant'];
    $rutAdm = $_POST['Adm_rut'];
    $nombreAdm = $_POST['Adm_nombre'];
    $apellidoPaternoAdm = $_POST['Adm_apellidoPaterno'];
    $apellidoMaternoAdm = $_POST['Adm_apellidoMaterno'];
    $direccionAdm = $_POST['Adm_direccion'];
    $emailAdm = $_POST['Adm_email'];


    // include db connect class
    require_once __DIR__ . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
   // $result = mysql_query("INSERT INTO administrador_restaurant(idAdministrador_restaurant, Adm_rut, Adm_nombre, Adm_apellidoPaterno, Adm_apellidoMaterno, Adm_direccion, Adm_email) VALUES('$idAdm', '$rutAdm' ,'$nombreAdm', '$apellidoPaternoAdm', '$apellidoMaternoAdm', '$direccionAdm', '$emailAdm')");
 $result = mysql_query("INSERT INTO administrador_restaurant(Adm_rut, Adm_nombre, Adm_apellidoPaterno, Adm_apellidoMaterno, Adm_direccion, Adm_email) VALUES('$rutAdm' ,'$nombreAdm', '$apellidoPaternoAdm', '$apellidoMaternoAdm', '$direccionAdm', '$emailAdm')");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Empleado successfully created.";

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