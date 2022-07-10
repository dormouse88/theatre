-- get a customer
SELECT 
	CustomerID,
    Username AS Username,
	fname,
    lname,
    Address
FROM
    Customer
WHERE 
Username = ?;
    
