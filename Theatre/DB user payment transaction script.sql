
-- transactions --
-- fulfilling a ticket -- add payment details then customer details, decrement the available seats

START transaction;
INSERT INTO Payment (CardDetails, PaymentDate, PaymentAmount) VALUES("1111222233334444", curdate(), 4000); 
-- inserts customer payment details ** TAKES USER PAYMENT DETAILS

INSERT INTO Customer (fname, lname, Email, Address,PaymentID) VALUES ("Jonathan", "Doenathon", "JoDo@email.com", "30 Oliver Dr", 3); 
-- inserts customer details ** TAKES USER DETAILS AND FK IS UPDATED MANUALLY (?)


-- selects the performance ID for seats to be removed from  ** needs user input to get this data [title, date and time]
--  -- changes amount of tickets available for specific show by changing seat numbers - had to use alias for subquery but the check query below confirms works

UPDATE Performance 
SET 
    NumberOfSeatsCircle = (NumberOFSeatsCircle - 1)
WHERE
    Performance.PerformanceID =
     (SELECT * FROM (SELECT 
            PerformanceID as SeatChange
        FROM
            Performance
                JOIN
            Showing ON Showing.ShowID = Performance.ShowingID
        WHERE
            Showing.Title = 'Mamma Mia'
                AND Performance.pdate = '2022-07-13'
                AND Performance.ptime = 'Matinee') AS P);  
-- TICKET DETAILS ** TAKES USERS INPUT FOR HOW MANY TICKETS REQUIRED. Cost (SHOULD BE RELABELLED TOTAL COST) can be calculated as price of ticket (script above) multiplied by number of tickets.

ROLLBACK; -- change to commit


-- select specific show at specific date and time (used in query to decrease value of seats) NumberOfSeatsCircle added to check transaction query success.
SELECT 
    PerformanceID, NumberOfSeatsCircle
FROM
    Performance
        JOIN
    Showing ON Showing.ShowID = Performance.ShowingID
WHERE
    Showing.Title = 'Mamma Mia'
        AND Performance.pdate = '2022-07-13'
        AND Performance.ptime = 'Matinee'; 


