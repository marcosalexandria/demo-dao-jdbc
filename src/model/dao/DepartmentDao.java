package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	//método responsavel por inserir no banco de dados o objeto enviado por parametro
	void insert(Department obj);
	//método responsavel por atualizar no banco de dados o objeto enviado por parametro
	void updata(Department obj);
	//método respavel por excluir um objeto recebendo o id dele
	void deleteById(Integer id);
	//metodo que vai retornar um objeto department recebendo o id dele
	Department findById(Integer id);
	//metodo que retorna todos os departamentos
	List<Department> findAll();
}
