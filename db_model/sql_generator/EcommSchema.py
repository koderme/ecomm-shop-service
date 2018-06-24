#!/usr/bin/python


import  Table
import  Column
import  SqlStatement

cDummy = Column.Column('dummy', 'any', 'any',  'any', 'any')

def customer(rowCount):
	t = Table.Table('ecomm_shop', 'customer')

	t.add( Column.Column('customer_id', cDummy.DT_NUM, '999',  '',  '{:0>4d}'))
	t.add( Column.Column('login_id',    cDummy.DT_STR, 'cust_','',  '{:0>3d}'))
	t.add( Column.Column('password',    cDummy.DT_STR, 'pass', '',  '{:0>3d}'))

	sql = SqlStatement.SqlStatement(t)

	return sql.generateInsert() + sql.generateSelect(rowCount)

def customer_profile(rowCount):
	t = Table.Table('ecomm_shop', 'customer_profile')

	t.add( Column.Column('customer_id',   cDummy.DT_NUM, '999',    '',         '{:0>4d}'))
	t.add( Column.Column('dob',           cDummy.DT_AUTO,'',       'CURDATE()','{}'))
	t.add( Column.Column('first_name',    cDummy.DT_STR, 'f-',     '',         '{:0>5d}'))
	t.add( Column.Column('gender',        cDummy.DT_STR, '',       'M',        '{}'))
	t.add( Column.Column('last_name',     cDummy.DT_STR, 'l-',     '',         '{:0>5d}'))
	t.add( Column.Column('middle_name',   cDummy.DT_STR, '',       '',         '{}'))
	t.add( Column.Column('phone_number',  cDummy.DT_STR, '+91 ',   '',         '{:0>10d}'))
	t.add( Column.Column('phone_verified',cDummy.DT_STR, '',       'N',         '{}'))

	sql = SqlStatement.SqlStatement(t)

	return sql.generateInsert() + sql.generateSelect(rowCount)


rowCount=10
print(customer(rowCount))
print(customer_profile(rowCount))
