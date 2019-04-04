package br.com.evisas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.evisas.config.businessError.BusinessException;
import br.com.evisas.dao.UsuarioDao;
import br.com.evisas.entity.Usuario;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	EmailService emailService;

	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private MessageSource messageSource;
    
	@Override
	public Usuario buscarPeloLogin(Usuario usuario) {
		Usuario usuarioBuscado = usuarioDao.buscarPeloLogin(usuario);
		if (usuarioBuscado != null) {
			return usuarioBuscado;
		} else {
			throw new BusinessException("msg.erro.login.invalido");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void cadastrar(Usuario usuario) {
		try {
			long id = usuarioDao.criar(usuario);
			usuario.setId(id);
		} catch (DuplicateKeyException ex) {
			throw new BusinessException("email", "msg.erro.email.ja.cadastrado");
		}
	}

	@Override
	public void enviarEmailSenha(Usuario usuario) {
		Usuario usuarioBuscado = usuarioDao.buscarPeloEmail(usuario.getEmail());
		if (usuarioBuscado == null) {
			throw new BusinessException("msg.erro.buscar.email");
		}
		boolean enviou = emailService.enviarEmailSimples(usuarioBuscado.getEmail(), 
							messageSource.getMessage("email.reenvio.senha.assunto", null, LocaleContextHolder.getLocale()), 
							messageSource.getMessage("email.reenvio.senha.texto", new Object[] {usuarioBuscado.getNome(), usuarioBuscado.getSenha()}, LocaleContextHolder.getLocale()));
		
		if (!enviou) {
			throw new BusinessException("msg.erro.envio.email");
		}
	}
}
