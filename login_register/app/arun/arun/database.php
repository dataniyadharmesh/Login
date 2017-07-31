<?php
   $dbhost = 'localhost';
   $dbuser = 'root';
   $dbpass = '';
   $db='arun';
   $con = @mysql_connect($dbhost, $dbuser, $dbpass);
   if(! $con )
   {
     die('Could not connect: ' . mysql_error());
   }else
   {
 // echo 'Connected successfully';
   }
   mysql_select_db( $db );
//$con= mysqli_connect($dbhost, $dbuser, $dbpass, $db);
?>