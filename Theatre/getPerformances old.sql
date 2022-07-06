SELECT 
	PerformanceID,
	ShowID,
	pdate,
	ptime,
    NumberOfSeatsCircle AS CircleSeats,
    NumberOfSeatsStalls AS StallSeats,
	Price
FROM
    Performance
        JOIN
    Showing ON Showing.ShowID = Performance.ShowingID;
-- title can be changed to accept user input/search
