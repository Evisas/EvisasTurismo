package br.com.evisas.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.evisas.dao.FuncionarioDao;
import br.com.evisas.entity.Funcionario;
import br.com.evisas.util.QueryUtil;

@Repository
public class FuncionarioJdbcDao implements FuncionarioDao {

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final RowMapper<Funcionario> FUNCIONARIO_ROW_MAPPER = new RowMapper<Funcionario>() {
		@Override
		public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Funcionario funcionario = new Funcionario();
			funcionario.setId(rs.getLong("id"));
			funcionario.setMatricula(rs.getString("matricula"));
			funcionario.setNome(rs.getString("nome"));
			funcionario.setEmail(rs.getString("email"));
			funcionario.setSenha(rs.getString("senha"));
			
			return funcionario;
		}
	};

	@Override
	public Funcionario buscarPeloLogin(Funcionario funcionario) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("matricula", funcionario.getMatricula());
		parameters.addValue("senha", funcionario.getSenha());
		
		List<Funcionario> funcionarios = jdbcTemplate.query(QueryUtil.getQueryByName("funcionario.buscar.pelo.login"), parameters, FUNCIONARIO_ROW_MAPPER);
		return funcionarios.isEmpty() ? null : funcionarios.get(0);
	}
}
