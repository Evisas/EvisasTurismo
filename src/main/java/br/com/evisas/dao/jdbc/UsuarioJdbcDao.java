package br.com.evisas.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
			usuario.setId(rs.getInt("id"));
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

		return jdbcTemplate.update(QueryUtil.getQueryByName("usuario.criar"), parameters);
	}

	@Override
	public Usuario buscarPeloLogin(Usuario usuario) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
//		NamedParameterJdbcTemplate parameters = new NamedParameterJdbcTemplate((DataSource)null);
		parameters.addValue("email", usuario.getEmail());
		parameters.addValue("senha", usuario.getSenha());
		
		return jdbcTemplate.queryForObject(QueryUtil.getQueryByName("usuario.buscar.pelo.login"), parameters, USUARIO_ROW_MAPPER);
	}
	
}
