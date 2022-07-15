-- INSERT INTO Employee (fname, lname, email, address) VALUES ("John", "Doe", "JDoe@TR.com", "32 Oliver Dr")
-- INSERT INTO Payment (CardDetails, PaymentDate, PaymentAmount) VALUES ("4548493283237532", curdate(), 4000)

INSERT INTO ShowType (Genre) VALUES ("Theatre"), ("Musical"), ("Opera"), ("Concert"), ("Pantomime"), ("Eventhire"), ("Dance");
-- ShowTypeID,Genre
-- 1,Theatre
-- 2,Musical
-- 3,Opera
-- 4,Concert
-- 5,Pantomime
-- 6,EventHire
-- 7,Dance

-- INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES ("Mamma Mia", 195, "English", "Mamma Mia! is a jukebox musical written by British playwright Catherine Johnson, based on the songs of ABBA composed by Benny Andersson and Björn Ulvaeus, members of the band. The title of the musical is taken from the group's 1975 chart-topper 'Mamma Mia'.",2)
-- ShowingID,Title,Duration,Lang,Info,ShowTypeID
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("Hamlet",240,"English","What doesn't this tragedy have? In creating the tale of 'The Melancholy Dane.' Shakespeare created a monument in Western literature.",1);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("Death of a Salesman",180,"English","'Attention must be paid.' Indeed. Not just to Willy Loman and the sad realities of his life as a mediocre traveling salesman and the delusions that barely keep him afloat but also to Arthur Miller's exquisite modern tragedy about an average Joe.",1);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("Fantastically Great Women - (who changed the world)",180,"English","A brand new kickass-pirational pop musical bursts into life as the Fantastically Great Women take to the stage to tell their stories. Adapted from Kate Pankhurst's book.",2);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("The London Sinfonietta",120,"English","Music inspired by the sounds of our slumbering bodies. Music moves over the listener like a wave bathing us in warm sonorities and rich harmonies.",4);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("La Traviata",200,"Italian","Giuseppe Verdi’s riches-to-rags opera has it all: from the intoxicating joy of the ‘Brindisi’ party chorus to the hushed poignancy of the final act - where hope teeters on the edge of despair.",3);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("Private Booking",1440,"English","Private Hire - School" ,6);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("Summer Panto",120,"English","Back by popular demand. Follow the yellow brick road to B'ham's Theatre Royal for summer pantomime The Wizard of Oz",5);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("We Will Rock You",160,"English","The worldwide smash hit musical by Queen and Ben Elton returns to the UK. The multi-million-pound show dazzles all the senses in breath-taking style.",2);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("Peaky Blinders",120,"English","WORLD PREMIERE: A new dance theatre event - delving into the backstory of Tommy Shelby and the Peaky Blinders - written by the creator of the global hit television series.",7);
INSERT INTO Showing (Title, Duration, Lang, Info, ShowTypeID) VALUES 
("Samara",95,"English","A dance duet inspired by a classic Chinese novel.",7);

-- INSERT INTO Customer (fname, lname, email, address, username, password) VALUES ("Jane", "Doe", "JDoes@email.com", "33 Oliver Dr", "JaDo", "password")
-- ID,First Name,Last Name ,Email,Address,Username,Password
INSERT INTO Customer (fname, lname, email, address, username, password) VALUES
("Walter","Smity","Wsmity@gmail.com","11 Our Street","MakeUpStuff1","gobbledegook1");
INSERT INTO Customer (fname, lname, email, address, username, password) VALUES
("Jane","Smyth","jsmyth@gmail.com","12 Our Street","MakeUpStuff2","gobbledegook2");
INSERT INTO Customer (fname, lname, email, address, username, password) VALUES
("Ringo","Starr","ringo@gmail.com","15 Cavern Road","MakeUpStuff3","gobbledegook3");
INSERT INTO Customer (fname, lname, email, address, username, password) VALUES
("Paul","McCartney","McCartney@hotmail.com","12 Cavern Road","MakeUpStuff4","gobbledegoo4");
INSERT INTO Customer (fname, lname, email, address, username, password) VALUES
("Rosey-Maple","Moth","Moth@Microsoft.com","61 Red Leaf Road","MakeUPStuff5","gobbledegoo5");
-- Justin,Timer,jim@Timer.com,57 Lamda Rd,MakeUPStuff6,gobbledego6,,,B'ham,B6 F82
-- Howie,Hepialdae,howie@hep.com,24 Blue Rd,MakeUPStuff7,gooble7,,,B'ham,B8 G71
-- Hannah,Hepialdae,hannah@hep.com,24 Blue Rd,MakeUPStuff8,goobled8,,,B'jam,B8 G71
-- Carla,Ctenuchidae,CC@fly.com,35 Brasil St,MakeUpStuff9,Goo1,,,Coventry,CV1 DL
-- Charlie,Chaplin,Charlie@Chaplin.com,40 Slapstick Rd,MakeUPStuff10,Giggle1,,,,
-- Whitney,Houston,Houston@hotmail.com,15 Texas Drive,MakeUPStuff11,Gigger1,,,,
-- Nelson,Mandela,Nelson@SAHouse.com,1 Trafalgar Sqr,MakeUPStuff12,BigGig,,,,
-- Mother,Theresa,mail@TheMother.com,2 Albanian Lane,MakeUPStuff13,LilGig,,,,
-- Groucho,Marks,Mail@MeBrothers.com,14 Cigar St,MakeUPStuff14,FunGigs14,,,,
-- Aretha,Franklin,mail@Respect.com,11 Spelling Rd,MakeUPStuff15,SingGigs15,,,,
-- Martin,Luther,King@google.com,9 Dream Park,MakeUPStuff16,DreamGigs16,,,,
-- Boris,Clowning,on@yourbike.com,4 RightLeft Turn,MakeUPStuff17,DreamGigs17,,,,
-- Clint,Eastward,Get@yourHorse.co.uk,12 Spaghetti Junction,MakeUPStuff18,MovieGig18,,,,
-- Marie,Currie,MC@Seriously.co.uk,15 Daffodil Gdns,MakeUPStuff19,FlowGig19,,,,
-- Ada,Lovelace,AL@LovesDaBase.co,18 Data Rd,MakeUPStuff20,DataGig20,,,,

-- INSERT INTO Performer (pname) VALUES ("MammaMiaGroup")
-- Performer_ID,Performer Name
INSERT INTO Performer (pname) VALUES
("Pretty Yende");
INSERT INTO Performer (pname) VALUES
("Stephen Costello");
INSERT INTO Performer (pname) VALUES
("Aakash Odedra");
INSERT INTO Performer (pname) VALUES
("Hu Shenyuan");
INSERT INTO Performer (pname) VALUES
("Queen");
INSERT INTO Performer (pname) VALUES
("Elton John");
INSERT INTO Performer (pname) VALUES
("Steven Knight");
INSERT INTO Performer (pname) VALUES
("Bennoit Swan Poffer");

INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (1,1); 
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (2,2);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (3,3);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (4,4);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (5,5);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (7,5);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (8,6);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (9,7);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (10,8);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (1,4);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (2,5);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (3,4);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (3,5);
INSERT INTO ShowingPerformer(ShowingID, PerformerID) VALUES (3,6);

-- INSERT INTO Performance (pdate, ptime, ShowingID) VALUES ("2022-07-13", "Matinee", 1), ("2022-07-13", "Evening", 1)
-- PerformanceID,pdate,ptime,ShowingID
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-19","Matinee",1);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-19","Evening",1);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-21","Evening",1);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-22","Evening",2);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-23","Matinee",3);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-23","Evening",2);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-24","Evening",2);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-25","Evening",3);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-26","Evening",3);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-27","Evening",4);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-28","Evening",4);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-29","Evening",5);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-30","Matinee",5);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-31","Matinee",5);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-31","Evening",5);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-20","Matinee",6);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-07-20","Evening",6);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-08-01","Evening",7);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-08-01","Evening",7);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-08-02","Evening",8);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-08-02","Evening",9);
INSERT INTO Performance (pdate, ptime, ShowingID) VALUES
("2022-08-03","Evening",10);


-- INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES ("Circle", 80, 4000, 1), ("Stalls", 120, 4000, 1)
-- INSERT INTO Seat(SeatZone, Price, NumberOfSeats, PerformanceID) VALUES ("Stalls", 4000, 120, 2), ("Circle", 4000, 80, 2)

-- SeatID,SeatZone,NumberOfSeats,Price,PerformanceID
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,1);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,1);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,2);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,2);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,3);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,3);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,4);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,4);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,5);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,5);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,6);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,6);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,7);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,7);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,8);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,8);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,9);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,9);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,10);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,10);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,11);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,11);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,12);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,12);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,13);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,13);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,14);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,14);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,15);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,15);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,16);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,16);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,17);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,17);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,18);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,18);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,19);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,19);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,20);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,20);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,21);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,21);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Circle",80,2800,22);
INSERT INTO Seat (SeatZone, NumberOfSeats, Price, PerformanceID) VALUES 
("Stalls",120,2200,22);


-- INSERT INTO Booking (NumberOfAdults, NumberOfChildren, TotalCost, CustomerID, PaymentID, SeatID) VALUES (1,0,4000,1,1,1)
-- BookingID,NumberOfAdults,NumberOfChildren,CustomerID,SeatID
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(3,6,1,1);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(4,9,2,2);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(2,0,3,6);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(2,0,4,44);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(1,2,5,3);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(2,0,1,14);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(2,0,1,16);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(2,2,3,4);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(4,1,3,12);
INSERT INTO Booking (NumberOfAdults, NumberOfChildren, CustomerID, SeatID) VALUES
(2,2,3,7);
