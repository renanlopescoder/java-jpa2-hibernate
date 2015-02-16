package br.com.caelum.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;
import br.com.caelum.financas.util.JPAUtil;

public class TesteJPARelacionamento {

		public static void main(String[] args){
			
			double inicio = System.currentTimeMillis();
			Conta conta = new Conta();
			conta.setTitular("Fulano de Tal3");
			conta.setBanco("Banco Dilma");
			conta.setNumero("12345");
			conta.setAgencia("231");
			
			Movimentacao movimentacao = new Movimentacao();
			movimentacao.setData(Calendar.getInstance());
			movimentacao.setDescricao("Pagamento Inicial");
			movimentacao.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
			movimentacao.setValor(new BigDecimal("1223.99"));
			
			movimentacao.setConta(conta);
			
			EntityManager manager = new JPAUtil().getEntityManager();
			
			manager.getTransaction().begin();
			
			manager.persist(conta);
			
			manager.persist(movimentacao);
			
			manager.getTransaction().commit();
			
			manager.close();
			
			double fim = System.currentTimeMillis();
	        System.out.println("Executado em: " + (fim - inicio)/1000 + "s");
			
		}
	
}
