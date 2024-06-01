CREATE DATABASE IF NOT EXISTS restaurante;


-- Criação da tabela para itens do cardápio
-- CREATE TABLE IF NOT EXISTS itens_do_cardapio (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY,
--     nome VARCHAR(255) NOT NULL,
--     descricao TEXT,
--     preco DECIMAL(10, 2) NOT NULL
-- );

-- CREATE TABLE IF NOT EXISTS pedido (
--     id BIGINT AUTO_INCREMENT PRIMARY KEY
-- );

-- CREATE TABLE IF NOT EXISTS pedidos_itens (
--     pedido_id BIGINT NOT NULL,
--     item_id BIGINT NOT NULL,
--     PRIMARY KEY (pedido_id, item_id),
--     FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
--     FOREIGN KEY (item_id) REFERENCES itens_do_cardapio(id)
-- );


