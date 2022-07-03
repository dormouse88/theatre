-- finding information on all current shows ---
SELECT 
    Title,
    Duration AS RunTimeMinutes,
    Lang AS Language,
    Info AS Description,
    ShowType.Genre AS Genre
FROM
    Showing
        JOIN
    ShowType ON Showing.ShowTypeID = ShowType.ShowTypeID;


-- finding information on specific show--
SELECT 
    Title,
    Duration AS RunTimeMinutes,
    Lang AS Language,
    Info AS Description
FROM
    Showing
WHERE
    Title = 'Mamma Mia';
-- change title to user input --


SELECT 
    Showing.Title, Performance.Price AS PricePence
FROM
    Performance
        JOIN
    Showing ON Showing.ShowID = Performance.ShowingID
WHERE
    Showing.Title = 'Mamma Mia'
        AND Performance.pdate = '22-07-13';
-- change title and performance dates with userinput requests


SELECT 
    Showing.Title AS Title,
    NumberOfSeatsCircle AS CircleSeats,
    NumberOfSeatsStalls AS StallSeats,
    (NumberOfSeatsStalls + NumberOfSeatsCircle) AS TotalSeats
FROM
    Performance
        JOIN
    Showing ON Showing.ShowID = Performance.ShowingID
WHERE
    Showing.Title = 'Mamma Mia';
-- title can be changed to accept user input/search


SELECT 
    Title
FROM
    Showing
        JOIN
    Performance ON Showing.ShowID = Performance.ShowingID
WHERE
    pdate = '2022-07-13';


-- finding todays shows
SELECT 
    Title
FROM
    Showing
        JOIN
    Performance ON Showing.ShowID = Performance.ShowingID
WHERE
    pdate = CURDATE();


-- finding all dates for a certain show
SELECT 
    pdate
FROM
    Performance
        JOIN
    Showing ON Showing.ShowID = Performance.ShowingID
WHERE
    Title = 'Mamma Mia'; 


-- get receipt -- i.e. return ticket information, ticketID, Number of tickets. cost, shows -- IF PAYMENT SUCCEEDS can use an adaption 
-- of this script to get specific tickets and get necessary data related to it - possibility to make multiple purchases by creating more tickets and
-- adding them all into the checkout basket.  
SELECT 
    TicketID,
    NumberOfTickets,
    Cost,
    Showing.Title AS Title,
    Performance.pdate AS Date,
    Performance.ptime AS Time
FROM
    Ticket
        LEFT JOIN
    Showing ON Ticket.ShowingID = Showing.ShowID
        LEFT JOIN
    Performance ON Showing.ShowID = Performance.ShowingID;