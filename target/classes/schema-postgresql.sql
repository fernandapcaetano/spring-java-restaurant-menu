CREATE TABLE IF NOT EXISTS prato (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50),
    descricao TEXT,
    foto_caminho TEXT,
    preco DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS pedido (
    id SERIAL PRIMARY KEY,
    data TIMESTAMP(6),
    mesa INT,
    quantidade_prato INT,
    total_preco_pedido DOUBLE PRECISION
);

CREATE TABLE IF NOT EXISTS pedido_pratos (
    pedido_id BIGINT NOT NULL,
    pratos_id BIGINT NOT NULL,
    PRIMARY KEY (pedido_id, pratos_id),
    FOREIGN KEY (pratos_id) REFERENCES prato (id),
    FOREIGN KEY (pedido_id) REFERENCES pedido (id)
);
