-- Inspect the table schema

\d+ retail;

-- Q1: Show first 10 rows

SELECT * FROM public.retail
LIMIT 10;

-- Q2: Check # of records

SELECT COUNT(*) FROM public.retail;

-- Q3: number of clients (e.g. unique client ID)

SELECT COUNT(DISTINCT customer_id) FROM public.retail;

-- Q4: invoice date range (e.g. max/min dates)

SELECT MAX(invoice_date) AS max_date, MIN(invoice_date) AS min_date FROM public.retail;

-- Q5: number of SKU/merchants (e.g. unique stock code)

SELECT COUNT(DISTINCT stock_code) FROM public.retail;

-- Q6: Calculate average invoice amount excluding invoices with a negative amount (e.g. canceled orders have negative amount)

SELECT AVG(invoice_amount) AS average_invoice_amount
FROM (
    SELECT invoice_no, SUM(unit_price * quantity) AS invoice_amount
    FROM public.retail
    GROUP BY invoice_no
    HAVING SUM(unit_price * quantity) >= 0
) AS invoice_totals;


-- Q7: Calculate total revenue (e.g. sum of unit_price * quantity)

SELECT SUM(unit_price * quantity) AS total_revenue FROM public.retail;

-- Q8: Calculate total revenue by YYYYMM 

SELECT TO_CHAR(invoice_date, 'YYYYMM') AS year_month, 
       SUM(unit_price * quantity) AS total_revenue
FROM public.retail
GROUP BY TO_CHAR(invoice_date, 'YYYYMM')
ORDER BY year_month;

