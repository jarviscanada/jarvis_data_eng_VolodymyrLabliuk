# Introduction

Project focuses on building pipeline to transform, filter and format raw data into the proper form in order to deliver that data to different categories of specialists.

# Implemenation

Project implemented using Jupyter Notebook and Python. Pandas, Numpy and Matplotlib imported for data processing and visalization.

## Architecture

Data dumped into .csv file processed using Python, Numpy and Pandas and visualized in the Jupyter.

## Scripts

Run retail_data_analytics_wrangling.ipynb. Jupyter Notebook required.

## Database Modeling

    Column    |            Type             | Collation | Nullable | Default | Storage  | Stats target | Description
--------------+-----------------------------+-----------+----------+---------+----------+--------------+-------------
 invoice_no   | text                        |           |          |         | extended |              |
 stock_code   | text                        |           |          |         | extended |              |
 description  | text                        |           |          |         | extended |              |
 quantity     | integer                     |           |          |         | plain    |              |
 invoice_date | timestamp without time zone |           |          |         | plain    |              |
 unit_price   | real                        |           |          |         | plain    |              |
 customer_id  | real                        |           |          |         | plain    |              |
 country      | text                        |           |          |         | extended |              |


# Test

No test provided in this project. All results can be seen through the Jupyter Notebook

# Deployment

retail_data_analytics_wrangling.ipynb file pushed to git hub repository

# Improvements

- Improve optimization
