<?php
  $dbhost = 'localhost';
   $dbuser = 'root';
   $dbpass = '';
   $db='arun';
   $con = @mysql_connect($dbhost, $dbuser, $dbpass);
   if(! $con )
   {
     die('Could not connect: ' . mysql_error());
   }
 //  echo 'Connected successfully';
   mysql_select_db( $db );
//$con= mysqli_connect($dbhost, $dbuser, $dbpass, $db);
$uid=$_POST['id'];
$pass=$_POST['pass'];
$name=$_POST['name'];
$sqls="Insert into login (uid,pass,name) Values('".$uid."','".$pass."','".$name."') ";
$results = mysql_query($sqls,$con) OR die("Error:".mysql_error());
echo "Registered";

?>