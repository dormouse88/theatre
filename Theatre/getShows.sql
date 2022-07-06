-- finding information on all current shows ---
SELECT 
	Showing.ShowID,
    Title,
    Duration AS RunTimeMinutes,
    Lang AS Language,
    Info AS Description,
    ShowType.Genre AS Genre,
	Performer_PerformerID AS Performer
FROM
    Showing
        JOIN
    ShowType
		ON Showing.ShowTypeID = ShowType.ShowTypeID
		JOIN
	Performer
		ON Performer.PerformerID = Showing.Performer_PerformerID;
