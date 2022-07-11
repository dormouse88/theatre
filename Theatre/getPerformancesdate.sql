SELECT 
	Performance.PerformanceID as PerformanceID,
	Performance.ShowingID as ShowingID,
	pdate,
	ptime,
    A.NumberOfSeats AS StallSeats,
    A.Price AS StallPrice,
    B.NumberOfSeats AS CircleSeats,
    B.Price AS CirclePrice
FROM
    Performance
        LEFT JOIN
    Seat AS A ON A.PerformanceID = Performance.PerformanceID
        LEFT JOIN
    Seat AS B ON B.PerformanceID = Performance.PerformanceID
WHERE
	A.SeatZone = "stalls"
	AND
	B.SeatZone = "circle"
	AND
	Performance.pdate = ?;
	