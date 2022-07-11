# sales-tax

Salestax and total price is calculated for all the items in the input product list.

Salestax for a product is equal to sum of sales tax plus the import duty. 
Total price of each product is equal to sum of salestax plus the price of each product.

In addition to salestaxes(import tax, sales tax) and total price for each item, we need to calculate the total salestaxes and total price of all the products in the list.

#Design
Each product belongs to a category(Food/Medicine/others etc. ). 
And each product has sales tax mapping, where each sales tax entry has a different sales tax percentage.
Also each product has a mapping to import duty. Each product can be mapped to only of the available importduty entry.

H2 database is used to persist the information of salestax,import tax,category and product information.

#CRUD operations
user can add/update/get all the available categories available using endpoint '../category/'
user can add/update/get all the available sales tax categories using '../sales-tax/'
user can add/update/get all the available sales tax categories using '../import-duty/'

user can add/update/get all the products using '../product' endpoint. Each product is mapped to a category,salestax and importduty.

salestax and total price of all individual items and all the items combined is computed and is available in the end point '../price-calculator'.


#Tests
JUnit invokes the service layer to calculate the taxes and total prices of the products in the list. 
No mocking is done. 
New category,salestax,import duty entries are added first and then product are added to the products table.

Junit test uses the sample input provided in case study.
