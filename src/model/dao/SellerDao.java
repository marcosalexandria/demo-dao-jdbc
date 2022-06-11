package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	//método responsavel por inserir no banco de dados o objeto enviado por parametro
	void insert(Seller obj);
	//método responsavel por atualizar no banco de dados o objeto enviado por parametro
	void updata(Seller obj);
	//método respavel por excluir um objeto recebendo o id dele
	void deleteById(Integer id);
	//metodo que vai retornar um objeto seller recebendo o id dele
	Seller findById(Integer id);
	//metodo que retorna todos os seller
	List<Department> findAll();
}
