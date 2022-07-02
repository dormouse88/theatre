/*Find a particular customer's assigned banker*/
SELECT Employee.first_name, Employee.last_name FROM Employee JOIN Customer ON Employee.ID = Customer.banker WHERE Customer.first_name = "Ian" AND Customer.last_name = "Evans";
/*Show each Branch name with the number of customers who live in its city*/
SELECT Branch.name, COUNT(Customer.city) FROM Branch INNER JOIN Customer ON Customer.city = Branch.city GROUP BY Branch.name;
/*Show the number of customers that each employee is assigned to*/
SELECT Employee.first_name, Employee.last_name, COUNT(Customer.ID) FROM Employee LEFT JOIN Customer ON Employee.ID = Customer.banker GROUP BY Employee.first_name, Employee.last_name;
/*Show all customers who hold accounts and their balances*/
SELECT Customer.first_name, Customer.last_name, Account.balance, Account.account_type FROM Customer, Account, CustomerAccount WHERE CustomerAccount.customerID = Customer.ID AND Account.ID = CustomerAccount.accountID;
/*Show all managerial staff*/
SELECT DISTINCT A.first_name, A.last_name FROM Employee A, Employee B WHERE A.ID = B.Manager;
/*Show the total balance of savings accounts held by customers in Bradford*/
SELECT DISTINCT Account.balance FROM ((Account LEFT JOIN CustomerAccount ON Account.ID = CustomerAccount.accountID) LEFT JOIN Customer ON CustomerAccount.customerID = Customer.ID) WHERE Account.account_type = "savings" AND Customer.city = "Bradford";
