package br.com.evisas.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.evisas.dao.UsuarioDao;
import br.com.evisas.entity.Usuario;
import br.com.evisas.util.QueryUtil;

@Repository
public class UsuarioJdbcDao implements UsuarioDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Usuario> USUARIO_ROW_MAPPER = new RowMapper<Usuario>() {
		@Override
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getLong("id"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
			usuario.setTelefone(rs.getString("telefone"));
			usuario.setSenha(rs.getString("senha"));
			
			return usuario;
		}
	};
	
	@Override
	public long criar(Usuario usuario) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("nome", usuario.getNome());
		parameters.addValue("email", usuario.getEmail());
		parameters.addValue("telefone", usuario.getTelefone());
		parameters.addValue("senha", usuario.getSenha());

		KeyHolder idGerado = new GeneratedKeyHolder();
		
		jdbcTemplate.update(QueryUtil.getQueryByName("usuario.criar"), parameters, idGerado);
		return idGerado.getKey().longValue();
	}

	@Override
	public Usuario buscarPeloLogin(Usuario usuario) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("email", usuario.getEmail());
		parameters.addValue("senha", usuario.getSenha());
		
		List<Usuario> usuarios = jdbcTemplate.query(QueryUtil.getQueryByName("usuario.buscar.pelo.login"), parameters, USUARIO_ROW_MAPPER);
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}

	@Override
	public Usuario buscarPeloEmail(String email) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("email", email);
		
		List<Usuario> usuarios = jdbcTemplate.query(QueryUtil.getQueryByName("usuario.buscar.pelo.email"), parameters, USUARIO_ROW_MAPPER);
		return usuarios.isEmpty() ? null : usuarios.get(0);
	}
}
