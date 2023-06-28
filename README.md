# Banco

### Creating database
```sql
CREATE TABLE clientes (
    "id" SERIAL PRIMARY KEY,
    "cpf" varchar(255),
    "rg" varchar(255),
    "nome" varchar(255),
    "sobrenome" varchar(255),
    "endereco" varchar(255)
);

CREATE TABLE contas (
     "numero" integer PRIMARY KEY,
     "deposito_inicial" float,
     "saldo" float,
     "tipo" varchar(255),
     "limite" float,
     "montante_minimo" float,
     "deposito_minimo" float,
     "id_cliente" SERIAL
);

ALTER TABLE contas ADD FOREIGN KEY ("id_cliente") REFERENCES clientes ("id") ON DELETE CASCADE;
```

### How to use DAO
```java
public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente(-1, "123", "456", "Name", "Last", "Rua abc");
        ClienteDAO clienteDAO = new ClienteDAO();

        // inserir
        clienteDAO.insere(cliente);

        // listar
        List<Cliente> clientes = clienteDAO.listar();
        for (Cliente c: clientes
        ) {
            System.out.println(c.getNome());
        }
        // atualizar
        Cliente clienteAltera = new Cliente(1, "09555165955", "110207697", "Matheusao", "Reisao", "Rua abc");
        clienteDAO.altera(clienteAltera);

        // remover
        cliente.setId(2);
        clienteDAO.remove(cliente);
    }
}
```

