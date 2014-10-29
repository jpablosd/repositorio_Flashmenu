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
if (isset($_GET["Rest_nombre"])) {
    //$cedula = $_GET['Adm_rut'];
  $n= "MAIA";
    // get a product from products table
    $result = mysql_query("SELECT * FROM restaurant WHERE Rest_nombre = '$n'");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $restaurant = array();
  //          $administrador_restaurant["id"] = $result["idAdministrador_restaurant"];
//$administrador_restaurant["rut"] = $result["Adm_rut"];
            $restaurant["nom"] = $result["Rest_nombre"];
            $restaurant["tipo"] = $result["Rest_tipo"];
            $restaurant["descrip"] = $result["Rest_descripcion"];
            $restaurant["caract"] = $result["Rest_caracteristicas"];
            $restaurant["email"] = $result["Rest_email"];
            $restaurant["direccion"] = $result["Rest_direccion"];
           // $product["created_at"] = $result["created_at"];
           // $product["updated_at"] = $result["updated_at"];
            // success
            $response["success"] = 1;

            // user node
            $response["restaurant"] = array();

            array_push($response["restaurant"], $restaurant);

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