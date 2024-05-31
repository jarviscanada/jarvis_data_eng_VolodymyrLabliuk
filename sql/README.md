# Introduction

In this project, I spearheaded the setup of a new PostgreSQL database, meticulously executing a variety of Data Definition Language (DDL) and Data Manipulation Language (DML) queries. From meticulously crafting tables and defining their structures to executing intricate data modifications, I ensured the database's robustness and efficiency. 
Through strategic planning and precise execution, I not only built a solid foundation for data management but also demonstrated my expertise in handling complex database operations.

# SQL Quries

###### Table Setup (DDL)

```sql
CREATE TABLE Members (
  memid integer NOT NULL, 
  surname character varying(200) NOT NULL, 
  firsname character varying(200) NOT NULL, 
  address character varying(300) NOT NULL, 
  zipcode integer NOT NULL, 
  telephone character varying(20) NOT NULL, 
  recommendedby integer NOT NULL, 
  joindate timestamp NOT NULL, 
  CONSTRAINT member_pk PRIMARY KEY (memid) CONSTRAINT members_fk FOREIGN KEY (recommendedby) REFERENCES Members(memid) ON DELETE 
  SET 
    NULL
);
CREATE TABLE Bookings (
  facid integer NOT NULL, 
  memid integer NOT NULL, 
  starttime timestamp NOT NULL, 
  slots integer NOT NULL, 
  CONSTRAINT bookings_pk PRIMARY KEY (facid), 
  CONSTRAINT bookings_member_fk FOREIGN KEY (memid) REFERENCES Members(memid), 
  CONSTRAINT bookings_facilities_fk FOREIGN KEY (facid) REFERENCES Facilities(facid)
  );
CREATE TABLE Facilities (
  facid integer NOT NULL, 
  name character varying(20) NOT NULL, 
  membercost numeric NOT NULL, 
  guestcost numeric NOT NULL, 
  initialoutlay numeric NOT NULL, 
  monthlymaintenance numeric NOT NULL, 
  CONSTRAINT facilities_pk PRIMARY KEY (facid)
);

```

###### Question 1: Insert some data into a table

```sql
INSERT INTO facilities
VALUES
(9, 'Spa', 20, 30, 100000, 800);
```

###### Question 2: Insert calculated data into a table

```sql
INSERT INTO facilities
VALUES
(
(
SELECT
max(facid)
FROM
cd.facilities
) + 1,
'Spa',
20,
30,
100000,
800
);
```

###### Question 3: Update some existing data

```sql
UPDATE
facilities
SET
initialoutlay = 10000
WHERE
name = 'Tennis Court 2';
```

###### Question 4: Update a row based on the contents of another row

```sql

UPDATE
facilities
SET
membercost = (
(
SELECT
membercost
FROM
cd.facilities
WHERE
name = 'Tennis Court 1'
) * 1.1
),
guestcost = (
(
SELECT
guestcost
FROM
cd.facilities
WHERE
name = 'Tennis Court 1'
) * 1.1
)
WHERE
name = 'Tennis Court 2';
```

###### Question 5: Delete all bookings

```sql
TRUNCATE TABLE facilities CASCADE;
```

###### Question 6: Delete a member from the cd.members table

```sql
DELETE FROM
cd.members
WHERE
memid = 37;
```

###### Question 7: Control which rows are retrieved - part 2


```sql
SELECT
f.facid,
f.name,
f.membercost,
f.monthlymaintenance
FROM
facilities f
WHERE
membercost > 0
AND membercost < (
(
SELECT
monthlymaintenance
FROM
facilities
WHERE
facid = f.facid
) * 0.02
)
```

###### Question 8: Basic string searches

```sql
SELECT
*
FROM
facilities
WHERE
name LIKE '%Tennis%';
```

###### Question 9: Matching against multiple possible values

```sql
SELECT
*
FROM
facilities
WHERE
facid = 1
UNION
SELECT
*
FROM
facilities
WHERE
facid = 5;
```

###### Question 10: Working with dates

```sql
SELECT
memid,
surname,
firstname,
joindate
FROM
members
WHERE
DATE(joindate) >= '2012-09-01';
```


###### Question 11: Combining results from multiple queries

```sql
SELECT
surname
FROM
members
UNION
SELECT
name
FROM
facilities;
```


###### Question 12: Retrieve the start times of members' bookings

```sql
SELECT
starttime
FROM
members m
JOIN bookings b ON m.memid = b.memid
WHERE
surname = 'Farrell'
AND firstname = 'David';
```

###### Question 13: Work out the start times of bookings for tennis courts

```sql
SELECT
b.starttime AS start,
f.name
FROM
facilities f
JOIN bookings b ON f.facid = b.facid
WHERE
DATE(b.starttime) = '2012-09-21'
AND name LIKE 'Tennis%'
ORDER BY
b.starttime;
```

###### Question 14: Produce a list of all members, along with their recommender

```sql
SELECT
m.firstname AS memfname,
m.surname AS memsname,
rm.firstname AS recfname,
rm.surname AS recsname
FROM
members m
LEFT OUTER JOIN members rm ON rm.memid = m.recommendedby
ORDER BY
(m.surname, m.firstname);
```

###### Question 15: Produce a list of all members who have recommended another member

```sql
SELECT
DISTINCT rm.firstname AS firstname,
rm.surname AS surname
FROM
cd.members m
JOIN cd.members rm ON rm.memid = m.recommendedby
ORDER BY
surname,
firstname;
```

###### Question 16: Produce a list of all members, along with their recommender, using no joins.

```sql
SELECT DISTINCT m.firstname || ' ' ||
m.surname AS member, (SELECT rm.firstname || ' ' ||
rm.surname AS recommender FROM cd.members rm WHERE rm.memid = m.recommendedby )
FROM
cd.members m
ORDER BY member;
```

###### Question 17: Count the number of recommendations each member makes.

```sql
SELECT
recommendedby,
COUNT(memid) AS count
FROM
members
WHERE
recommendedby IS NOT NULL
GROUP BY
recommendedby
ORDER BY
recommendedby;
```

###### Question 18: List the total slots booked per facility
 ```sql
SELECT
facid,
SUM(slots) AS "Total Slots"
FROM
bookings
GROUP BY
facid
ORDER BY
facid;
```

###### Question 19: List the total slots booked per facility in a given month
```sql
SELECT
facid,
SUM(slots) AS "Total Slots"
FROM
bookings
WHERE
DATE(starttime) BETWEEN '2012-09-01'
AND '2012-09-30'
GROUP BY
facid
ORDER BY
"Total Slots";
```
###### Question 20: List the total slots booked per facility per month
```sql
SELECT
facid,
EXTRACT(
month
FROM
starttime
) AS month,
SUM(slots) AS "Total Slots"
FROM
bookings
WHERE
EXTRACT (
year
FROM
starttime
) = 2012
GROUP BY
facid,
month
ORDER BY
facid,
month;
```

###### Question 21: Find the count of members who have made at least one booking
```sql
SELECT
COUNT(DISTINCT memid)
FROM
bookings;
```
###### Question 22: List each member's first booking after September 1st 2012
```sql
SELECT
m.surname,
m.firstname,
m.memid,
MIN(b.starttime)
FROM
members m
JOIN bookings b ON m.memid = b.memid
WHERE
DATE(b.starttime) >= '2012-09-01'
GROUP BY
m.surname,
m.firstname,
m.memid
ORDER BY
m.memid;
```
###### Question 23: Produce a list of member names, with each row containing the total member count
```sql
SELECT
total_count.total_rows,
m.firstname,
m.surname
FROM
members m CROSS
JOIN (
SELECT
COUNT(*) AS total_rows
FROM
members m
) AS total_count;
```

###### Question 24: Produce a numbered list of members
```sql
SELECT
ROW_NUMBER() over (
ORDER BY
joindate
),
m.firstname,
m.surname
FROM
members m;
```

###### Question 25: Output the facility id that has the highest number of slots booked, again
```sql
SELECT
facid,
total
FROM
(
SELECT
facid,
sum(slots) total,
rank() over (
ORDER BY
sum(slots) DESC
) rank
FROM
bookings
GROUP BY
facid
) AS ranked
WHERE
rank = 1;
```

###### Question 26: Format the names of members

```sql
SELECT
surname || ', ' || firstname AS name
FROM
members;
```

###### Question 27: Find telephone numbers with parentheses

```sql
SELECT
memid,
telephone
FROM
members
WHERE
telephone LIKE '(%)%';
```
###### Question 27: Count the number of members whose surname starts with each letter of the alphabet

```sql
SELECT
SUBSTRING(surname, 1, 1) AS letter,
COUNT(surname)
FROM
cd.members
GROUP BY
SUBSTRING(surname, 1, 1)
ORDER BY
letter;
```



EOF