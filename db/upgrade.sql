USE dpconsole

ALTER TABLE kitchen_items ADD COLUMN packing_price DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE orders ADD COLUMN post_commission_amount DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE orders ADD COLUMN dp_received_price DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE kitchens ADD COLUMN mailbox_folder varchar(255) NOT NULL;
ALTER TABLE orders ADD COLUMN restaurant_promo DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE orders ADD COLUMN piggy_bank_coins DOUBLE(10, 4) NOT NULL DEFAULT 0;

ALTER TABLE kitchen_items ADD COLUMN delivery_partner VARCHAR(255) NOT NULL;
ALTER TABLE kitchen_items DROP KEY name_uk;
ALTER TABLE kitchen_items ADD UNIQUE name_uk(kitchen_id, delivery_partner, item_id);

18-12-2018
==============
ALTER TABLE kitchen_delivery_partners ADD COLUMN discount_percentage DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE kitchen_delivery_partners ADD COLUMN support_charges_percentage DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE kitchen_delivery_partners ADD COLUMN convenience_fee_percentage DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE kitchen_delivery_partners ADD COLUMN max_discount_amount DOUBLE(10, 4) NOT NULL DEFAULT 0;


21-12-2018
=================
ALTER TABLE kitchen_delivery_partners drop column discount_percentage;
ALTER TABLE kitchen_delivery_partners drop column max_discount_amount;
ALTER TABLE orders ADD COLUMN zomato_promo DOUBLE(10, 4) NOT NULL DEFAULT 0;
