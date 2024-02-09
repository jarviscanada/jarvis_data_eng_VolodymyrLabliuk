-- set cd as 1st search schema

SET search_path TO cd;

-- insert

INSERT INTO facilities
VALUES
    (9, 'Spa', 20, 30, 100000, 800);

-- select in insert

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

-- update

UPDATE
    facilities
SET
    initialoutlay = 10000
WHERE
    name = 'Tennis Court 2';

-- update with calculation

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

-- delete all

TRUNCATE TABLE facilities CASCADE;

-- delete condition
DELETE FROM
    cd.members
WHERE
    memid = 37;

-- select with condition

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

-- Select with contains

SELECT
    *
FROM
    facilities
WHERE
    name LIKE '%Tennis%';

-- Select two without OR

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

-- Compare timestamp

SELECT
    memid,
    surname,
    firstname,
    joindate
FROM
    members
WHERE
    DATE(joindate) >= '2012-09-01';


-- Union members surnames and facilities names

SELECT
    surname
FROM
    members
UNION
SELECT
    name
FROM
    facilities;

-- Select with join and condition

SELECT
    starttime
FROM
    members m
        JOIN bookings b ON m.memid = b.memid
WHERE
    surname = 'Farrell'
  AND firstname = 'David';

-- Join with conditions

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

-- Select with same table FK

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

-- Select with recommendedby

SELECT
    DISTINCT rm.firstname AS firstname,
             rm.surname AS surname
FROM
    cd.members m
        JOIN cd.members rm ON rm.memid = m.recommendedby
ORDER BY
    surname,
    firstname;

-- select without join

SELECT DISTINCT m.firstname || ' ' ||
                m.surname AS member, (SELECT rm.firstname || ' ' ||
                                             rm.surname AS recommender FROM cd.members rm WHERE rm.memid = m.recommendedby )
FROM
    cd.members m
ORDER BY member;

-- Count the number of recommendations each member makes.

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

-- List the total slots booked per facility

SELECT
    facid,
    SUM(slots) AS "Total Slots"
FROM
    bookings
GROUP BY
    facid
ORDER BY
    facid;

-- List the total slots booked per facility in a given month

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

-- List the total slots booked per facility per month

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

-- Find the count of members who have made at least one booking

SELECT
    COUNT(DISTINCT memid)
FROM
    bookings;

-- List each member's first booking after September 1st 2012

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

-- Produce a list of member names, with each row containing the total member count

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

-- Produce a numbered list of members

SELECT
    ROW_NUMBER() over (
    ORDER BY
      joindate
  ),
        m.firstname,
    m.surname
FROM
    members m;

-- Output the facility id that has the highest number of slots booked, again

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

-- Format the names of members

SELECT
    surname || ', ' || firstname AS name
FROM
    members;

-- Find telephone numbers with parentheses

SELECT
    memid,
    telephone
FROM
    members
WHERE
    telephone LIKE '(%)%';

-- Count the number of members whose surname starts with each letter of the alphabet

SELECT
    SUBSTRING(surname, 1, 1) AS letter,
    COUNT(surname)
FROM
    cd.members
GROUP BY
    SUBSTRING(surname, 1, 1)
ORDER BY
    letter;


