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
$uid=$_POST['name'];
$pass=$_POST['password'];
$sqls="select * from login where uid='".$uid."' and pass='".$pass."' ";
$results = mysql_query($sqls,$con) OR die("Error:".mysql_error());
$re=			mysql_num_rows($results);
if($re>0)
{
	echo "Valid User";
}
else
{
	echo "Invalid Userid or Password";
}

			while ($rows = mysql_fetch_array($results))
				{   }

?>