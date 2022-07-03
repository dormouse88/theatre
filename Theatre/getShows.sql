-- finding information on all current shows ---
SELECT 
	ShowID,
    Title,
    Duration AS RunTimeMinutes,
    Lang AS Language,
    Info AS Description,
    ShowType.Genre AS Genre,
	Performer.pname AS Performer
FROM
    Showing
        JOIN
    ShowType
		ON Showing.ShowTypeID = ShowType.ShowTypeID
		JOIN
	Performer
		ON Performer.ShowingID = Showing.ShowID;
