package br.com.caelum.financas.teste;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.util.JPAUtil;

public class TesteConsultaCount {
	
	public static void main(String [] args){
		EntityManager manager= new JPAUtil().getEntityManager();
		
		Conta conta = manager.find(Conta.class, 1);
		
		Query query = manager.createQuery("select count(m) from Movimentacao m where m.conta=:pConta");
		
		query.setParameter("pConta", conta);
		
		Long quantidade = (Long) query.getSingleResult();

		System.out.println("Total de movimentações: " + quantidade);
		
		manager.close();	
	}

}
