#!/usr/bin/python


import  Table
import  Column
import  SqlStatement

cDmy = Column.Column('dummy', 'any', 'any',  'any', 'any')

#----------------------------------------------------------
#
#----------------------------------------------------------
def customer(rowCount):
	t = Table.Table('ecomm_shop', 'customer')

	t.add( Column.Column('customer_id', cDmy.DT_NUM, '999',  '',  cDmy.FMT_4d))
	t.add( Column.Column('login_id',    cDmy.DT_STR, 'cust_','',  cDmy.FMT_3d))
	t.add( Column.Column('password',    cDmy.DT_STR, 'pass', '',  cDmy.FMT_3d))

	sql = SqlStatement.SqlStatement(t)

	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
#
#----------------------------------------------------------
def customer_profile(rowCount):
	t = Table.Table('ecomm_shop', 'customer_profile')

	t.add( Column.Column('customer_id',   cDmy.DT_NUM, '999',    '',         cDmy.FMT_4d))
	t.add( Column.Column('dob',           cDmy.DT_AUTO,'',       'CURDATE()',cDmy.FMT_EMPTY))
	t.add( Column.Column('first_name',    cDmy.DT_STR, 'f-',     '',         '{:0>5d}'))
	t.add( Column.Column('gender',        cDmy.DT_STR, '',       'M',        cDmy.FMT_EMPTY))
	t.add( Column.Column('last_name',     cDmy.DT_STR, 'l-',     '',         '{:0>5d}'))
	t.add( Column.Column('middle_name',   cDmy.DT_STR, '',       '',         cDmy.FMT_EMPTY))
	t.add( Column.Column('phone_number',  cDmy.DT_STR, '+91 ',   '',         '{:0>10d}'))
	t.add( Column.Column('phone_verified',cDmy.DT_STR, '',       'N',         cDmy.FMT_EMPTY))

	sql = SqlStatement.SqlStatement(t)

	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
#
#----------------------------------------------------------
def customer_address(rowCount):

	t = Table.Table('ecomm_shop', 'customer_address')

	t.add( Column.Column('customer_address_id',   cDmy.DT_NUM, '999',    '',         cDmy.FMT_4d))
	t.add( Column.Column('customer_id',           cDmy.DT_NUM, '999',    '',         cDmy.FMT_4d))
	t.add( Column.Column('address_type',          cDmy.DT_STR, '',       'R',        cDmy.FMT_EMPTY))
	t.add( Column.Column('apartment_building',    cDmy.DT_STR, 'Apt-',   '',         cDmy.FMT_3d))
	t.add( Column.Column('first_line',            cDmy.DT_STR, '',       ' ',        cDmy.FMT_EMPTY))
	t.add( Column.Column('second_line',           cDmy.DT_STR, '',       ' ',        cDmy.FMT_EMPTY))
	t.add( Column.Column('street',                cDmy.DT_STR, '',       '90 feet rd',cDmy.FMT_EMPTY))
	t.add( Column.Column('city',                  cDmy.DT_STR, 'City-',  '',         cDmy.FMT_3d))
	t.add( Column.Column('state',                 cDmy.DT_STR, '',       'MH',       cDmy.FMT_EMPTY))
	t.add( Column.Column('country',               cDmy.DT_STR, '',       'India',    cDmy.FMT_EMPTY))
	t.add( Column.Column('zip_code',              cDmy.DT_NUM, '400',    '',         cDmy.FMT_3d))

	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
#
#----------------------------------------------------------
def customer_payment_info(rowCount):


	t = Table.Table('ecomm_shop', 'customer_payment_info')
	t.add( Column.Column('customer_payment_info_id',   cDmy.DT_NUM, '999',    '',            cDmy.FMT_4d))
	t.add( Column.Column('customer_id',                cDmy.DT_NUM, '999',    '',            cDmy.FMT_4d))
	t.add( Column.Column('payment_type_code',          cDmy.DT_STR, '',       'credit_card', cDmy.FMT_EMPTY))
	t.add( Column.Column('payment_number',             cDmy.DT_STR, '',       '',            cDmy.FMT_16d))
	t.add( Column.Column('expiry_date_mm_yy',          cDmy.DT_STR, '',       '122020',      cDmy.FMT_EMPTY))

	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def vendor(rowCount):

	t = Table.Table('ecomm_shop', 'vendor')
	t.add( Column.Column('vendor_id',   cDmy.DT_NUM, '999',    '',         cDmy.FMT_4d))
	t.add( Column.Column('vendor_name', cDmy.DT_STR, 'vend-',  '',         cDmy.FMT_4d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def vendor_detail(rowCount):

	t = Table.Table('ecomm_shop', 'vendor_detail')
	t.add( Column.Column('vendor_id',       cDmy.DT_NUM,   '999',   '',         cDmy.FMT_4d))
	t.add( Column.Column('gst_number',      cDmy.DT_STR,  'GST-',   '',         cDmy.FMT_7d))
	t.add( Column.Column('phone_number1',   cDmy.DT_STR,  '+91 ',   '',         cDmy.FMT_10d))
	t.add( Column.Column('phone_number2',   cDmy.DT_STR,  '+91 ',   '',         cDmy.FMT_10d))
	t.add( Column.Column('phone_number3',   cDmy.DT_STR,  '+91 ',   '',         cDmy.FMT_10d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def vendor_product(rowCount):

	t = Table.Table('ecomm_shop', 'vendor_product')
	t.add( Column.Column('vendor_product_id',  cDmy.DT_NUM,   '999',   '',         cDmy.FMT_4d))
	t.add( Column.Column('vendor_id',          cDmy.DT_NUM,   '999',   '',         cDmy.FMT_4d))
	t.add( Column.Column('product_id',         cDmy.DT_NUM,   '999',   '',         cDmy.FMT_4d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def vendor_product_discount(rowCount):

	t = Table.Table('ecomm_shop', 'vendor_product_discount')
	t.add( Column.Column('tbd',   cDmy.tbd, '999',    '',         cDmy.tbd))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def vendor_product_image(rowCount):

	t = Table.Table('ecomm_shop', 'vendor_product_image')
	t.add( Column.Column('tbd',   cDmy.tbd, '999',    '',         cDmy.tbd))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def vendor_product_price(rowCount):

	t = Table.Table('ecomm_shop', 'tbd')
	t.add( Column.Column('tbd',   cDmy.tbd, '999',    '',         cDmy.tbd))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def attribute_key_code(rowCount):

	t = Table.Table('ecomm_shop', 'attribute_key_code')
	t.add( Column.Column('attribute_key_code',   cDmy.DT_STR, 'attr-key-code-',  '',         cDmy.FMT_4d))
	t.add( Column.Column('description',          cDmy.DT_STR, 'attr-desc-',      '',         cDmy.FMT_16d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def category_code(rowCount):

	t = Table.Table('ecomm_shop', 'category_code')
	t.add( Column.Column('category_code',   cDmy.DT_STR, 'cat-code-',  '',         cDmy.FMT_4d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def sub_category_code(rowCount):

	t = Table.Table('ecomm_shop', 'sub_category_code')
	t.add( Column.Column('sub_category_code',   cDmy.DT_STR, 'sub-cat-code-',  '',         cDmy.FMT_4d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def product(rowCount):

	t = Table.Table('ecomm_shop', 'product')
	t.add( Column.Column('product_id',     cDmy.DT_NUM, '999',       '',         cDmy.FMT_4d))
	t.add( Column.Column('product_code',   cDmy.DT_STR, 'pc-',       '',         cDmy.FMT_3d))
	t.add( Column.Column('description',    cDmy.DT_STR, 'prod-desc-','',         cDmy.FMT_16d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def product_attribute_kv(rowCount):

	t = Table.Table('ecomm_shop', 'product_attribute_kv')
	t.add( Column.Column('product_id',          cDmy.DT_NUM, '999',    '',          cDmy.FMT_4d))
	t.add( Column.Column('attribute_key_code',  cDmy.DT_STR, 'attr-key-code-',  '', cDmy.FMT_4d))
	t.add( Column.Column('attribute_value',     cDmy.DT_STR, 'attr-val',    '',     cDmy.FMT_4d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def product_category(rowCount):


	t = Table.Table('ecomm_shop', 'product_category')
	t.add( Column.Column('product_id',      cDmy.DT_NUM,     '999',        '',      cDmy.FMT_4d))
	t.add( Column.Column('iteration',       cDmy.DT_NUM,     '',           '1',     cDmy.FMT_EMPTY))
	t.add( Column.Column('category_code1',  cDmy.DT_STR,     'cat-code-',  '',      cDmy.FMT_4d))
	t.add( Column.Column('category_code2',  cDmy.DT_STR,     'cat-code-',  '',      cDmy.FMT_4d))
	t.add( Column.Column('category_code3',  cDmy.DT_STR,     'cat-code-',  '',      cDmy.FMT_4d))
	t.add( Column.Column('sub_category_code1',  cDmy.DT_STR, 'sub-cat-code-',  '',  cDmy.FMT_4d))
	t.add( Column.Column('sub_category_code2',  cDmy.DT_STR, 'sub-cat-code-',  '',  cDmy.FMT_4d))
	t.add( Column.Column('sub_category_code3',  cDmy.DT_STR, 'sub-cat-code-',  '',  cDmy.FMT_4d))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def shop_artefact(rowCount):

	rowCount=1
	t = Table.Table('ecomm_shop', 'shop_artefact')
	t.add( Column.Column('artefact_name', cDmy.DT_STR, 'artefact-',    '',         cDmy.FMT_2d))
	t.add( Column.Column('file_path',     cDmy.DT_STR, '',             '/var/tmp/',         cDmy.FMT_EMPTY))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def shop_detail(rowCount):

	rowCount=1
	t = Table.Table('ecomm_shop', 'shop_detail')
	t.add( Column.Column('shop_name',   cDmy.DT_STR, '',    'HealtyOptions Inc',         cDmy.FMT_EMPTY))
	t.add( Column.Column('description', cDmy.DT_STR, '',    'HealtyOptions Inc',         cDmy.FMT_EMPTY))
	t.add( Column.Column('motto',       cDmy.DT_STR, '',    'live healthy...live long',  cDmy.FMT_EMPTY))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)


#----------------------------------------------------------
# tbd
#----------------------------------------------------------
def tbd(rowCount):

	t = Table.Table('ecomm_shop', 'tbd')
	t.add( Column.Column('tbd',   cDmy.tbd, '999',    '',         cDmy.tbd))
	sql = SqlStatement.SqlStatement(t)
	return sql.generateInsert() + sql.generateSelect(rowCount)

#----------------------------------------------------------
# Main
#----------------------------------------------------------
rowCount=10

functions = [
"shop_artefact",
"shop_detail",

"category_code",
"sub_category_code",
"attribute_key_code",

"customer",
"customer_profile",
"customer_address",
"customer_payment_info",

"product",
"product_attribute_kv",
"product_category",

"vendor",
"vendor_detail",
"vendor_product",
]

functions2 = [
"shop_detail",
]

for f in functions:
	print("-- ---------------------------")
	str = eval(f+'(rowCount)')
	print(str)


