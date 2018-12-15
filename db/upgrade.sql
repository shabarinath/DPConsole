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