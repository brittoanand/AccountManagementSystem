
INSERT INTO operation_types (operation_type_id, description) VALUES (1, 'PURCHASE')
    ON CONFLICT (operation_type_id) DO NOTHING;

INSERT INTO operation_types (operation_type_id, description) VALUES (2, 'INSTALLMENT PURCHASE')
    ON CONFLICT (operation_type_id) DO NOTHING;

INSERT INTO operation_types (operation_type_id, description) VALUES (3, 'WITHDRAWAL')
    ON CONFLICT (operation_type_id) DO NOTHING;

INSERT INTO operation_types (operation_type_id, description) VALUES (4, 'PAYMENT')
    ON CONFLICT (operation_type_id) DO NOTHING;
