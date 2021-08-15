package com.ifsc.claudio.renaldo.elana.diovane.dao;

import java.util.List;

import com.ifsc.claudio.renaldo.elana.diovane.controller.ContatoLista;
import com.ifsc.claudio.renaldo.elana.diovane.entity.Contato;

public interface DAO<T> {

	Object get(Long id);

	List<T> getAll();

	int save(T t);

	boolean update(T t, String[] params);

	boolean delete(T t);

	boolean update(com.ifsc.claudio.renaldo.elana.diovane.entity.Contato contato);

	
	

}
