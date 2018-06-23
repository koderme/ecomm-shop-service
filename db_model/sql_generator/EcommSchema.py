#!/usr/bin/python


import  Table
import  Column
import  SqlStatement

def customer():
	t = Table.Table('ecomm_shop', 'customer')

	c = Column.Column('customer_id', 'number', '999', '{:0>4d}')
	t.add(c)

	c = Column.Column('login_id', 'string', 'cust-', '{:0>4d}')
	t.add(c)

	c = Column.Column('password', 'string', 'pass-', '{}')
	t.add(c)

	sql = SqlStatement.SqlStatement(t)

	print(sql.generateSelect(10))

customer()
