<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["Adm_rut"])) {
    //$cedula = $_GET['Adm_rut'];
  //$cedula = "123";
    // get a product from products table
    $result = mysql_query("SELECT * FROM administrador_restaurant WHERE Adm_rut = 123");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $administrador_restaurant = array();
  //          $administrador_restaurant["id"] = $result["idAdministrador_restaurant"];
//$administrador_restaurant["rut"] = $result["Adm_rut"];
            $administrador_restaurant["nom"] = $result["Adm_nombre"];
            $administrador_restaurant["apep"] = $result["Adm_apellidoPaterno"];
            $administrador_restaurant["apem"] = $result["Adm_apellidoMaterno"];
            $administrador_restaurant["dir"] = $result["Adm_direccion"];
            $administrador_restaurant["email"] = $result["Adm_email"];
           // $product["created_at"] = $result["created_at"];
           // $product["updated_at"] = $result["updated_at"];
            // success
            $response["success"] = 1;

            // user node
            $response["administrador_restaurant"] = array();

            array_push($response["administrador_restaurant"], $administrador_restaurant);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No empleado found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No empleado found";

        // echo no users JSON
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