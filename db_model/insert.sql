-- ---------------------------
insert into ecomm_shop.product_category_code (product_category_code )










-- ---------------------------
insert into ecomm_shop.product_sub_category_code (product_sub_category_code )










-- ---------------------------
insert into ecomm_shop.attribute_key_code (attribute_key_code,description )










-- ---------------------------
insert into ecomm_shop.payment_type_code (payment_type_code )

-- ---------------------------
insert into ecomm_shop.currency_code (currency_code )

-- ---------------------------
insert into ecomm_shop.unit_code (unit_code )

-- ---------------------------
insert into ecomm_shop.discount_type_code (discount_type_code )










-- ---------------------------
insert into ecomm_shop.shop_artefact (artefact_name,file_path )

-- ---------------------------
insert into ecomm_shop.shop_detail (shop_name,description,motto )

-- ---------------------------
insert into ecomm_shop.customer (customer_id,login_id,password )










-- ---------------------------
insert into ecomm_shop.customer_profile (customer_id,dob,first_name,gender,last_name,middle_name,phone_number,phone_verified )










-- ---------------------------
insert into ecomm_shop.customer_address (customer_address_id,customer_id,address_type,apartment_building,first_line,second_line,street,city,state,country,zip_code )










-- ---------------------------
insert into ecomm_shop.customer_payment_info (customer_payment_info_id,customer_id,payment_type_code,payment_number,expiry_date_mm_yy )










-- ---------------------------
insert into ecomm_shop.product (product_id,product_code,description )










-- ---------------------------
insert into ecomm_shop.product_attribute_kv (product_id,attribute_key_code,attribute_value )










-- ---------------------------
insert into ecomm_shop.product_category_rel (product_id,iteration,category_code1,category_code2,category_code3,sub_category_code1,sub_category_code2,sub_category_code3 )










-- ---------------------------
insert into ecomm_shop.vendor (vendor_id,vendor_name )










-- ---------------------------
insert into ecomm_shop.vendor_detail (vendor_id,gst_number,phone_number1,phone_number2,phone_number3 )










-- ---------------------------
insert into ecomm_shop.vendor_product (vendor_product_id,vendor_id,product_id )










-- ---------------------------
insert into ecomm_shop.vendor_product_price (vendor_product_id,vid,is_latest_vid,unit_code,price,currency_code )










-- ---------------------------
insert into ecomm_shop.vendor_product_discount (vendor_product_id,vid,is_latest_vid,discount_value,discount_type_code )










-- ---------------------------
insert into ecomm_shop.vendor_product_image (vendor_product_id,iteration,image_path )









