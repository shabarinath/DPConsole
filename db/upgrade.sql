ALTER TABLE kitchen_items ADD COLUMN packing_price DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE orders ADD COLUMN post_commission_amount DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE orders ADD COLUMN dp_received_price DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE kitchens ADD COLUMN mailbox_folder varchar(255) NOT NULL;
ALTER TABLE orders ADD COLUMN restaurant_promo DOUBLE(10, 4) NOT NULL DEFAULT 0;
ALTER TABLE orders ADD COLUMN piggy_bank_coins DOUBLE(10, 4) NOT NULL DEFAULT 0;