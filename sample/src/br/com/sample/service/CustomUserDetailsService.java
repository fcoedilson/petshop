/**
 * 
 */
package br.com.sample.service;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.security.concurrent.SessionAlreadyUsedException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.sample.entity.Usuario;
import br.com.sample.type.StatusUsuario;

@Component()
public class CustomUserDetailsService extends BaseService<Long, Usuario> implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException, 
				DataAccessException,
				SessionAlreadyUsedException {
		
		Usuario user = null;
		if(username == null || username.equals("")){
			return user;
		} else {
			try {
				Query query = em.createNamedQuery("Usuario.findByLogin");
				query.setParameter(1, username);
				query.setParameter(2, StatusUsuario.ATIVO);
				user = (Usuario) query.getSingleResult();
			}catch (NoResultException e) {
				user = null;
			} catch (UsernameNotFoundException e) {
				user = null;
			}catch (DataAccessException e) {
				user = null;
			}catch(SessionAlreadyUsedException e){
				user = null;
			}
		}
		return user;
	}
}
