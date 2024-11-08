<html>
<body>
<h1>WELCOME</h1><br>
<h3>All Contacts</h3><a href="allContacts">Click Here-></a><br><br><br><h3>Search contact</h3> <br>
<form method="post" action="searchContact">
First name : <input type="text" name="fname"><br><br>
Last name :  <input type="text" name="lname"><br><br>
<input type="submit" value="search">
</form>
<br>
<br>

<h3>Add contact</h3> <br>
<form method="post" action="addContact">
First name : <input type="text" name="fname"><br><br>
Last name :  <input type="text" name="lname"><br><br>
Phone Number :  <input type="tel" name="phoneNumber"><br><br>
Email :  <input type="email" name="email"><br><br>
Address :  <input type="text" name="address"><br><br>
Note :  <input type="text" name="note"><br><br>

<input type="submit" value="ADD">
</form>
<br><br>
<form action="login">
<input type="submit" value="Logout">
</form>
<br>
</body>
</html>